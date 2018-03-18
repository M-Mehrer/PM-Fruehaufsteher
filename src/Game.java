import org.newdawn.slick.BasicGame;
import org.newdawn.slick.Color;
import org.newdawn.slick.GameContainer;
import org.newdawn.slick.Graphics;
import org.newdawn.slick.Input;
import org.newdawn.slick.Music;
import org.newdawn.slick.SlickException;

public class Game extends BasicGame
{
    private StaticLevel level;
    private Player player;
    private Enemies enemies;
    private Music bgMusic;
    public boolean gameRunning = false;

    public Game() throws SlickException {
        super("SuperMarco");
    }


    public void init(GameContainer gc) throws SlickException {
        level = new StaticLevel();
        level.init(gc);

        enemies = new Enemies( level);
        enemies.init(gc);

        player = new Player( level,enemies );
        player.init(gc);



//		bgMusic = new Music("C:\\Users\\marcm\\Downloads\\mario_08");
//		bgMusic.loop();
    }



    public void render(GameContainer gc, Graphics g) throws SlickException {
        //drawDebugLines( g , 50 );
        g.scale(3f,3f);
        g.translate(-player.player.getX() + 250,-player.player.getY() + 150); //placing camera in relation to player
        level.render(gc, g);
        player.render(gc, g);
        enemies.render(gc, g);

        if(level.isInGoal(player.player)) {
            g.setColor(Color.red);
            g.drawString("LEVEL GESCHAFFT!", 1650, 650);
            g.drawString("DANKE FÜRS SPIELEN.", 1640, 600);
            gameRunning = false;
        }

        if(!gameRunning) {
            g.setColor(Color.white);
            g.drawString("Welcome to SuperMarco!"
                    + "\n\nMove with arrow keys."
                    + "\nPress R to reset."
                    + "\nPress enter to start.", -200, 650);
            if( gc.getInput().isKeyDown(Input.KEY_ENTER) )
                gameRunning = true;
        }
    }


    public void update(GameContainer gc, int delta) throws SlickException {
        if(gameRunning) {
            level.update(gc, delta);
            player.update(gc, delta);
            enemies.update(gc, delta);
        }
    }






    // Draw a grid on the screen for easy positioning
    public void drawDebugLines(Graphics g, int size)
    {
        int resolution = 800;
        g.setColor( Color.darkGray);
        for( int i = 0; i < resolution; i += size)
        {
            g.drawLine(i, 0, i, resolution);
            g.drawLine(0,i, resolution, i);
        }
    }

}
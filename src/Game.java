import javafx.scene.control.TextInputDialog;
import org.newdawn.slick.*;

import javax.swing.*;
import java.io.File;
import java.util.Optional;

public class Game extends BasicGame
{
    private enum State {
        MAIN_MENU, PLAYING, FINISHED
    }
    private State gameState = State.MAIN_MENU;
    private StaticLevel level;
    private Player player;
    private Enemies enemies;
    public Music bgMusic;
    private Sound finishSound;
    private boolean finishSoundPlayed = false;
    private int score = 0, time = 0;
    private long startTime;
    private HighscoreView instance;

    public Thread highscoreViewThread;

    public Game() throws SlickException {
        super("SuperMarco");
    }


    public void init(GameContainer gc) throws SlickException {
        level = new StaticLevel();
        level.init(gc);

        enemies = new Enemies( level);
        enemies.init(gc);

        player = new Player( level,enemies,this );
        player.init(gc);

        bgMusic = new Music("/media/sounds/background_music.ogg");
        bgMusic.loop();

        finishSound = new Sound("/media/sounds/finish2.ogg");

        updateHighscoreView();
    }



    public void render(GameContainer gc, Graphics g) throws SlickException {
        //drawDebugLines( g , 50 );
        g.scale(3f,3f);
        g.translate(-player.player.getX() + 250,-player.player.getY() + 150); //placing camera in relation to player
        level.render(gc, g);
        player.render(gc, g);
        enemies.render(gc, g);
        switch(gameState){
             case MAIN_MENU:
                 g.setColor(Color.white);
                 g.drawString("Welcome to SuperMarco!"
                         + "\n\nMove with arrow keys."
                         + "\nPress R to reset."
                         + "\nPress enter to start.", -200, 650);
                 if( gc.getInput().isKeyDown(Input.KEY_ENTER) ) {
                     gameState = State.PLAYING;
                     startTime = System.currentTimeMillis();
                 }
                 break;
            case PLAYING:
                time = Math.toIntExact((System.currentTimeMillis() - startTime) / 1000);
                g.setColor(Color.white);
                g.drawString("Score: " + score + "          " + "Time: " + time, player.player.getX() - 50,player.player.getY() - 150);
                if(level.isInGoal(player.player)) {
                    gameState = State.FINISHED;
                }
                break;
            case FINISHED:
                g.setColor(Color.white);
                g.drawString(" LEVEL COMPLETED!" +
                        "\nTHANKS FOR PLAYING" +
                        "\n\npress R to replay.", player.player.getX() - 20,player.player.getY() - 75);

                if(!finishSoundPlayed) {
                    saveHighscore(getHighscore());
                    bgMusic.pause();
                    finishSound.play();
                    finishSoundPlayed = true;
                    updateHighscoreView();
                }

                if( gc.getInput().isKeyDown(Input.KEY_R) ) {
                    gameState = State.PLAYING;
                    player.reset(gc);
                    finishSoundPlayed = false;
                    bgMusic.resume();
                }


                break;
        }
    }

    private void saveHighscore(int score) {

        JFrame frame = new JFrame("Highscore");
        String name = JOptionPane.showInputDialog(frame, "Look, this is your score: " + score + ". Please enter your name:");

        if(name != null && !name.isEmpty()) {
            Highscore highscore = new Highscore();
            File scoresFile = new File("scores.xml");

            if(scoresFile.exists()) {
                try {
                    highscore.loadFromXMLFile(scoresFile.getAbsolutePath());
                } catch(Exception ex) {
                    //TODO
                    ex.printStackTrace();
                }
            }

            highscore.addScore(new Score(name, score));
            try {
                highscore.writeToXMLFile(scoresFile.getAbsolutePath());
            } catch(Exception ex) {
                //TODO
                ex.printStackTrace();
            }
        }

    }

    public void update(GameContainer gc, int delta) throws SlickException {
        switch(gameState){
            case MAIN_MENU:

                break;
            case PLAYING:
                level.update(gc, delta);
                player.update(gc, delta);
                enemies.update(gc, delta);
                break;
            case FINISHED:

                break;
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

    public void increaseScore(int points){
        score += points;
    }
    private int getHighscore(){
        return score + (1000 - time);
    }
    public void resetScore(){
        score = 0;
        startTime = System.currentTimeMillis();
    }

    public void updateHighscoreView() {
        try {
            instance = HighscoreView.getInstance();
            if (instance != null) {
                instance.loadDefaultFile();
            }
        } catch(Exception ex) {
            //Ignore exceptions like a pro
        }
    }
}
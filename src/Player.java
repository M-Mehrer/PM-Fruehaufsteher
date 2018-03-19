import org.newdawn.slick.*;
import org.newdawn.slick.geom.Rectangle;
import org.newdawn.slick.geom.Shape;

public class Player {

    private static float gravity = 0.5f;
    private static float jumpStrength = -8;
    private static float speed = 4;

    private static int interations = 5;


    public Shape player;
    private StaticLevel level;
    private Enemies enemies;
    private Animation[] sprite = new Animation[6];

    Sound opponentSound, jumpSound, gameOverSound;



    private float vX = 0;
    private float vY = 0;
    private float x = 150;
    private float y = 150;
    private float cX = 0;
    private float cY = 0;

    private final int LOOKING_RIGHT = 0;
    private final int WALKING_RIGHT = 1;
    private final int WALKING_LEFT = 2;
    private final int JUMPING_RIGHT = 3;
    private final int JUMPING_LEFT = 4;
    private final int LOOKING_LEFT = 5;

    private int playerDir = LOOKING_RIGHT;
    private boolean playerJump = false;

    public Player( StaticLevel level, Enemies enemies )
    {
        this.level = level;
        this.enemies = enemies;
    }

    public void init(GameContainer gc) throws SlickException {
        //init player
        player  = new Rectangle(40,700,20,25);
        //looking right
        sprite[0] = new Animation(new Image[] {new Image("./media/Marco_Standing_Looking Right_01.png")},1);
        //walking right
        sprite[1] = new Animation(new Image[] {new Image("./media/Marco_Standing_Running Right_01.png"),
                new Image("./media/Marco_Standing_Running Right_02.png"),
                new Image("./media/Marco_Standing_Running Right_03.png"),
                new Image("./media/Marco_Standing_Running Right_04.png")},200);
        //walking left
        sprite[2] = new Animation(new Image[] {new Image("./media/Marco_Standing_Running Left_01.png"),
                new Image("./media/Marco_Standing_Running Left_02.png"),
                new Image("./media/Marco_Standing_Running Left_03.png"),
                new Image("./media/Marco_Standing_Running Left_04.png")},200);
        //jumping right
        sprite[3] = new Animation(new Image[] {new Image("./media/Marco_Jumping Right_01.png")},1);
        //jumping left
        sprite[4] = new Animation(new Image[] {new Image("./media/Marco_Jumping Left_01.png")},1);
        //looking left
        sprite[5] = new Animation(new Image[] {new Image("./media/Marco_Standing_Looking Left_01.png")},1);


        opponentSound = new Sound("/media/sounds/opponent.ogg");
        jumpSound = new Sound("/media/sounds/jump.ogg");
        gameOverSound = new Sound("/media/sounds/game_over1.ogg");
    }


    public void render(GameContainer gc, Graphics g) throws SlickException {

        g.setColor( Color.transparent );
        g.draw(player);
        sprite[playerDir].draw(x,y,20,25);


    }


    public void update(GameContainer gc, int delta) throws SlickException {
        // Y acceleration
        vY += gravity;
        if( gc.getInput().isKeyDown(Input.KEY_UP) )
        {
            if(!playerJump) {
                jumpSound.play();
            }

            playerJump = true;
            player.setY( player.getY()+0.5f );
            y = player.getY()+0.5f -cY;
            if( level.collidesWith(player) )
            {
                //System.out.print("jump");
                vY = jumpStrength;
            }
            player.setY( player.getY()-0.5f );
            y = player.getY()-0.5f -cY;
        }
        // Y Movement-Collisions
        float vYtemp = vY/interations;
        for( int i = 0; i < interations ; i++ )
        {
            player.setY( player.getY() + vYtemp );
            y = player.getY() + vYtemp -cY;
            if( level.collidesWith(player) )
            {
                player.setY( player.getY() - vYtemp );
                y = player.getY() - vYtemp -cY;
                vY = 0;
                playerJump = false;
            }
        }

        //out of map
        if(player.getY()>900) {
            reset();
        }

        //Reset Player
        if( gc.getInput().isKeyDown(Input.KEY_R) ) {
            reset();
        }

        //enemy hits player
        if(enemies.hitsPlayer(player)) {
            reset();
        }

        //player hits enemy
        if(enemies.isKilled(player)) {
            opponentSound.play();
        }

        //checked if reached goal
        if(level.isInGoal(player))
            System.out.println("Ziel erreicht!");

        // X acceleration
        if( gc.getInput().isKeyDown(Input.KEY_LEFT) )
        {
            vX = -speed;
            //choosing Animation
            if(playerJump)
                playerDir = JUMPING_LEFT;
            else
                playerDir = WALKING_LEFT;
        } else if( gc.getInput().isKeyDown(Input.KEY_RIGHT) )
        {
            vX = speed;
            //choosing Animation
            if(playerJump)
                playerDir = JUMPING_RIGHT;
            else
                playerDir = WALKING_RIGHT;
        }
        else
        {
            vX = 0;
            //choosing Animation
            if(playerDir == JUMPING_LEFT || playerDir == WALKING_LEFT || playerDir == LOOKING_LEFT) {
                if (playerJump)
                    playerDir = JUMPING_LEFT;
                else
                    playerDir = LOOKING_LEFT;
            }
            else if (playerDir == WALKING_RIGHT || playerDir == JUMPING_RIGHT|| playerDir == LOOKING_RIGHT) {
                if (playerJump)
                    playerDir = JUMPING_RIGHT;
                else
                    playerDir = LOOKING_RIGHT;
            }
        }

        // X Movement-Collisions
        float vXtemp = vX/interations;
        for( int i = 0; i < interations ; i++ )
        {
            player.setX( player.getX() + vXtemp );
            x = player.getX() + vXtemp - cX;
            if( level.collidesWith(player) )
            {
                player.setX( player.getX() - vXtemp );
                x = player.getX() - vXtemp - cX;
                vX = 0;
            }
        }
    }

    public void reset() {
        gameOverSound.play();

        player.setY(700);
        player.setX(40);
        for(Snail s: enemies.enemieBoxes) {
            s.isAlive = true;
        }

    }
}
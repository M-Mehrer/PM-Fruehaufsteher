import org.newdawn.slick.Animation;
import org.newdawn.slick.Color;
import org.newdawn.slick.GameContainer;
import org.newdawn.slick.Graphics;
import org.newdawn.slick.Image;
import org.newdawn.slick.Input;
import org.newdawn.slick.SlickException;
import org.newdawn.slick.geom.Rectangle;
import org.newdawn.slick.geom.Shape;

public class Enemies {
    private float snail1X = 365;
    private float snail1Y = 721;
    private float snail2X = 400;
    private float snail2Y = 577;
    private float snail3X = 400;
    private float snail3Y = 449;
    private float snail4X = 1100;
    private float snail4Y = 721;
    private float snail5X = 1300;
    private float snail5Y = 721;

    private StaticLevel level;

    private final int LEFT = 0;
    private final int RIGHT = 1;

    public Snail[] enemieBoxes = new Snail[5];
    public Animation[] enemieSprites = new Animation[2];

    private static float speed = 1;
    private static int interations = 5;

    public Enemies(StaticLevel level) {
        this.level = level;
    }

    public void init(GameContainer gc) throws SlickException {
        // enemie Animations
        Image[] snailLeft = new Image[4];
        snailLeft[0] = new Image("./media/Snail_Left_1.png");
        snailLeft[1] = new Image("./media/Snail_Left_2.png");
        snailLeft[2] = new Image("./media/Snail_Left_3.png");
        snailLeft[3] = new Image("./media/Snail_Left_2.png");

        Image[] snailRight = new Image[4];
        snailRight[0] = new Image("./media/Snail_Right_1.png");
        snailRight[1] = new Image("./media/Snail_Right_2.png");
        snailRight[2] = new Image("./media/Snail_Right_3.png");
        snailRight[3] = new Image("./media/Snail_Right_2.png");

        enemieSprites[0] = new Animation(snailLeft, 300);
        enemieSprites[1] = new Animation(snailRight, 300);

        enemieBoxes[0] = new Snail(snail1X, snail1Y, 20, 15, 427, 320);
        enemieBoxes[1] = new Snail(snail2X, snail2Y, 20, 15, 550, 290);
        enemieBoxes[2] = new Snail(snail3X, snail3Y, 20, 15, 550, 355);
        enemieBoxes[3] = new Snail(snail4X, snail4Y, 20, 15, 1210, 1090);
        enemieBoxes[4] = new Snail(snail5X, snail5Y, 20, 15, 1355, 1230);

    }

    public void render(GameContainer gc, Graphics g) throws SlickException {
        g.setColor(Color.transparent);
        for (Snail i : enemieBoxes) {
            if (i.isAlive) {
                g.draw(i.hitbox);
                g.draw(i.critbox);
                enemieSprites[i.dir].draw(i.hitbox.getX(), i.hitbox.getY() - 5, 20, 20);
            }
        }

    }

    public void update(GameContainer gc, int delta) throws SlickException {
        for (Snail s : enemieBoxes) {
            //determine direction
            if (s.dir == LEFT) {
                s.vX = -speed;

            } else if (s.dir == RIGHT) {
                s.vX = speed;

            } else {
                s.vX = 0;

            }
            float vXtemp = s.vX / interations;
            //move enemy
            for (int i = 0; i < interations; i++) {
                s.hitbox.setX(s.hitbox.getX() + vXtemp);
                s.critbox.setX(s.hitbox.getX());
                //ckeck if enemy leaves his space
                if (s.hitbox.getX() > s.rightBorder || s.hitbox.getX() < s.leftBorder) {
                    //change animation
                    if (s.dir == LEFT)
                        s.dir = RIGHT;
                    else
                        s.dir = LEFT;
                    s.vX = 0;
                }
            }
        }
    }

    public boolean hitsPlayer(Shape player) {
        for (Snail s : enemieBoxes) {
            if (s.isAlive)
                if (player.intersects(s.hitbox))
                    return true;
        }
        return false;
    }
    public boolean isKilled(Shape player) {
        for(Snail s: enemieBoxes) {
            if (player.intersects(s.critbox) && s.isAlive) {
                s.isAlive = false;
                return true;
            }
        }
        return false;
    }
}
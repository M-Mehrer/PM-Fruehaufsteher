import org.newdawn.slick.Animation;
import org.newdawn.slick.Image;
import org.newdawn.slick.SlickException;
import org.newdawn.slick.geom.Rectangle;
import org.newdawn.slick.geom.Shape;

public class Snail {
    //JA PUBLIC, ICH WEISS
    public Shape hitbox;
    public int dir;
    public float leftBorder;
    public float rightBorder;
    public float vX;
    public boolean isAlive;
    public Shape critbox;
    public int value = 50;

    public Snail(float x, float y, float width, float height, float rightBorder, float leftBorder) throws SlickException {
        this.hitbox = new Rectangle(x,y,width, height);
        this.critbox = new Rectangle(x + 1,y - 2,width - 2,1);
        this.dir = 0;
        this.vX = 0;
        this.leftBorder = leftBorder;
        this.rightBorder = rightBorder;
        this.isAlive = true;

    }

}
import org.newdawn.slick.Animation;
import org.newdawn.slick.Image;
import org.newdawn.slick.SlickException;
import org.newdawn.slick.geom.Rectangle;
import org.newdawn.slick.geom.Shape;


public class Coin {
    public Shape hitbox;
    public boolean collected;
    public Animation animation;
    public int value = 10;

    public Coin (float x, float y) throws SlickException{
        hitbox = new Rectangle(x, y, 10, 10);
        collected = false;
        Image[] coinSprites = new Image[]{
                new Image("/media/Coin_01.png"),
                new Image("/media/Coin_02.png"),
                new Image("/media/Coin_03.png"),
                new Image("/media/Coin_04.png"),
                new Image("/media/Coin_05.png"),
                new Image("/media/Coin_06.png"),
                new Image("/media/Coin_07.png"),
                new Image("/media/Coin_08.png"),
                new Image("/media/Coin_07.png"),
                new Image("/media/Coin_06.png"),
                new Image("/media/Coin_05.png"),
                new Image("/media/Coin_04.png"),
                new Image("/media/Coin_03.png"),
                new Image("/media/Coin_02.png"),
        };
        animation = new Animation(coinSprites,100);
    }
}

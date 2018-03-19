import org.newdawn.slick.Color;
import org.newdawn.slick.GameContainer;
import org.newdawn.slick.Graphics;
import org.newdawn.slick.Image;
import org.newdawn.slick.SlickException;
import org.newdawn.slick.geom.Polygon;
import org.newdawn.slick.geom.Rectangle;
import org.newdawn.slick.geom.Shape;

public class StaticLevel
{

    private Shape[] lev = new Shape[25];
    private Shape ziel;
    private Image welt;

    private float[] coinX;
    private float[] coinY;
    private Coin[] coins;


    public void init(GameContainer gc) throws SlickException {

        float[] alpha = new float[] {
                640f,800f,
                640f,670f,
                608f,670f,
                608f,655f,
                640f,655f,
                640f,445f,
                608f,445f,
                608f,432f,
                640f,432f,
                640f,378f,
                608f,378f,
                608f,368f,
                640f,368f,
                640f,320f,
                608f,320f,
                608f,288f,
                704f,288f,
                672f,320f,
                672f,640f,
                720f,640f,
                720f,655f,
                736f,655f,
                736f,671f,
                752f,671f,
                752f,687f,
                768f,687f,
                768f,703f,
                784f,703f,
                784f,719f,
                800f,719f,
                800f,736f,
                865f,736f,
                865f,800f};

        float[] bravo = new float[]
                {0f,576f,
                        32f,576f,
                        32f,582f,
                        233f,582f,
                        233f,566f,
                        224f,566f,
                        224f,559f,
                        257f,559f,
                        257f,565f,
                        247f,565f,
                        247f,597f,
                        32f,597f,
                        32f,608f,
                        0f,608f};
        float[]charlie = new float[]
                {1088f,736f,
                        1376f,736f,
                        1376f,720f,
                        1392f,720f,
                        1392f,704f,
                        1408f,704f,
                        1408f,688f,
                        1424f,688f,
                        1424f,672f,
                        1440f,672f,
                        1440f,656f,
                        1456f,656f,
                        1456f,640f,
                        1472f,640f,
                        1472f,624f,
                        1488f,624f,
                        1488f,608f,
                        1536f,608f,
                        1536f,800f,
                        1088f,800f};
        float[] delta = new float[]
                {640f,0f,
                        640f,224f,
                        608f,224f,
                        608f,256f,
                        704f,256f,
                        704f,224f,
                        672f,224f,
                        672f,0f};

        lev[0] = new Rectangle(0,736,225,63);
        lev[1] = new Rectangle(0,0,1,1000);
        lev[2] = new Rectangle(288,736,190,63);
        lev[3] = new Polygon(alpha);
        lev[4] = new Rectangle(304,720,16,16);
        lev[5] = new Rectangle(448,720,16,16);
        lev[6] = new Rectangle(512,736,32,32);
        lev[7] = new Rectangle(576,704,32,32);
        lev[8] = new Rectangle(287,592,290,16);
        lev[9] = new Polygon(bravo);

        lev[10] = new Rectangle(160,529,32,16);
        lev[11] = new Rectangle(96,504,32,16);
        lev[12] = new Rectangle(63,472,32,16);
        lev[13] = new Rectangle(288,496,32,16);
        lev[14] = new Rectangle(544,400,32,16);
        lev[15] = new Rectangle(544,336,32,16);
        lev[16] = new Rectangle(511,304,32,16);

        lev[17] = new Rectangle(127,440,130,16);
        lev[18] = new Rectangle(352,464,225,16);
        lev[19] = new Rectangle(928,736,32,64);
        lev[20] = new Rectangle(1024,736,32,64);
        lev[21] = new Rectangle(1600,736,192,16);
        lev[22] = new Rectangle(1792,0,1,1000);
        lev[23] = new Polygon(charlie);
        lev[24] = new Polygon(delta);

        ziel = new Rectangle(1650,568,16,150);
        welt = new Image("./media/Level_One_V.1.2.png");

        coinX = new float[]{100, 120, 140, 160, 180, 100, 120, 140, 160, 180, //start area
                40, 60, 80, 100, 120, 140, 160, 180, 200, 220, //plant
                171, 107, 74, 299, 555, 555, 522, 621, 621, 621, //platforms
                320, 340, 360, 380, 400, 420, 440, //first enemy
                360, 380, 400, 420, 440, 460, 480, 340, 500, //big platform #1
                170, 190, 210, //big platform #2
                410, 430, 450, 470, 490, //big platform #3
                720, 718, 716, 714, 712, 710, 708, 706, 704, //drop diagonal
                702, 702, 702, 702, 702, 702, 702, 702, 702, 702, 702, //drop straight
                939, 939, 939, 1035, 1035, 1035, //dirt piles
                683, 683, 683 //impossible coins
        };
        coinY = new float[]{700, 700, 700, 700, 700, 690, 690, 690, 690, 690, //start area
                570, 570, 570, 570, 570, 570, 570, 570, 570, 570, //plant
                517, 492, 460, 484, 388, 324, 292, 643, 420, 356, //platforms
                685, 670, 655, 645, 655, 670, 685, //first enemy
                580, 580, 580, 580, 580, 580, 580, 580, 580, //big platform #1
                428, 428, 428, //big platform #2
                452, 452, 452, 452, 452, //big platform #3
                288, 300, 312, 324, 336, 348, 360, 372, 384, //drop diagonal
                396, 408, 420, 432, 446, 458, 472, 484, 498, 510, 524, //drop straight
                724, 712, 700, 724, 712, 700, //dirt piles
                212, 200, 188 //impossible coins
        };
        coins = new Coin[coinX.length];
        for (int i = 0; i < coins.length; i++){
            coins[i] = new Coin(coinX[i], coinY[i]);
        }

    }


    public void render(GameContainer gc, Graphics g) throws SlickException {
        g.setColor(Color.transparent);

        for(Shape i:lev) {
            g.draw(i);
        }
        g.draw(ziel);
        welt.draw(0,0);

        for(Coin c: coins){
            if(!c.collected) {
                g.draw(c.hitbox);
                c.animation.draw(c.hitbox.getX(), c.hitbox.getY(), 10, 10);
            }
        }
    }


    public void update(GameContainer gc, int delta) throws SlickException {

    }

    public boolean collectsCoin(Shape p){

        for (Coin c:coins) {
            if(!c.collected) {
                if (c.hitbox.intersects(p)) {
                    c.collected = true;
                    return true;
                }
            }
        }
        return false ;
    }

    public void resetCoins(){
        for(Coin c: coins){
            c.collected = false;
        }
    }

    public boolean collidesWith(Shape s)
    {
        for (Shape i:lev) {
            if(i.intersects(s))
                return true;
        }
        return false ;
    }
    public boolean isInGoal(Shape e) {
        return ziel.intersects(e);
    }
}
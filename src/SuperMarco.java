import org.newdawn.slick.*;

import java.util.concurrent.atomic.AtomicInteger;

public class SuperMarco extends BasicGame {

    private Image marco;
    private Image wood;
    private AtomicInteger marcoPosX = new AtomicInteger(50);


    public SuperMarco(String gameName) {
        super(gameName);
    }

    @Override
    public void init(GameContainer gameContainer) throws SlickException {
        try {
            marco = new Image("media/pictures/marco.png");
            wood = new Image("media/pictures/wood.png");
        } catch (SlickException e) {
            System.out.println(e.getMessage());
        }
    }

    @Override
    public void update(GameContainer gameContainer, int delta) throws SlickException {
        if(gameContainer.getInput().isKeyDown(Input.KEY_ESCAPE)) {
            gameContainer.exit();
        }
        if(gameContainer.getInput().isKeyDown(Input.KEY_RIGHT)) {
            marcoPosX.updateAndGet((x) -> x + 1);
        }
        if(gameContainer.getInput().isKeyDown(Input.KEY_LEFT)) {
            marcoPosX.updateAndGet((x) -> x - 1);
        }
    }

    @Override
    public void render(GameContainer gameContainer, Graphics graphics) throws SlickException {
        System.out.println(marcoPosX);
        marco.draw(marcoPosX.floatValue(), 50);
        wood.draw(25, 200);
    }

    public static void main(String[] args) {
        try {
            AppGameContainer container = new AppGameContainer(new SuperMarco("SuperMarco"));
            container.setDisplayMode(800, 600, false);
            container.start();
            container.setTargetFrameRate(30);
        } catch (Exception e) {
            System.out.println(e.getMessage());
        }
    }
}

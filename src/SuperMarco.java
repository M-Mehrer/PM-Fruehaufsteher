import org.newdawn.slick.*;

public class SuperMarco extends BasicGame {

    private Image marco;
    private Image wood;

    public SuperMarco(String gameName) {
        super(gameName);
    }

    @Override
    public void init(GameContainer gameContainer) throws SlickException {
        try {
            marco = new Image("media/pictures/marco.png");
            wood = new Image("media/pictures/wood.png");
        } catch (SlickException e) {
            System.out.println(e.getMessage() + "lol");
        }
    }

    @Override
    public void update(GameContainer gameContainer, int i) throws SlickException {
        if(gameContainer.getInput().isKeyDown(Input.KEY_ESCAPE)) {
            gameContainer.exit();
        }
    }

    @Override
    public void render(GameContainer gameContainer, Graphics graphics) throws SlickException {
        marco.draw(50, 50);
        wood.draw(25, 200);
    }

    public static void main(String[] args) {
        try {
            AppGameContainer container = new AppGameContainer(new SuperMarco("SuperMarco"));
            container.setDisplayMode(800, 600, false);
            container.start();
        } catch (Exception e) {
            System.out.println(e.getMessage());
        }
    }
}

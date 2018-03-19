

import javafx.beans.property.SimpleIntegerProperty;
import javafx.beans.property.SimpleStringProperty;

public class Score {
    private SimpleStringProperty name = new SimpleStringProperty();
    private SimpleIntegerProperty score = new SimpleIntegerProperty();


    public Score(String name, int score) {
        this.name.set(name);
        this.score.set(score);
    }

    public String getName() {
        return name.get();
    }

    public void setName(String name) {
        this.name.set(name);
    }

    public int getScore() {
        return score.get();
    }

    public void setScore(int score) {
        this.score.set(score);
    }

    @Override
    public String toString() {
        return "de.cppvoid.simplehighscore.Score{" +
                "name='" + name + '\'' +
                ", score=" + score +
                '}';
    }
}
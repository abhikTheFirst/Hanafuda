package sample;

public class Player {
    private int score;
    private int turns;

    public Player() {
        score = 0;
        turns = 9;
    }

    public int getScore() {
        return score;
    }

    public void setScore(int score) {
        this.score += score;
    }

    public int getTurns() {
        return turns;
    }

    public void setTurns(int turns) {
        this.turns = turns;
    }


    @Override
    public String toString() {
        return "Player{" +
                "score=" + score +
                ", turns=" + turns +
                  + '\'' +
                '}';
    }
}
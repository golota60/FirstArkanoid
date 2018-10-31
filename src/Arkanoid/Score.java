package Arkanoid;

public class Score {
    private int score = 0;

    public void addToScore(int value){
        score+=value;
    }

    public int getScore(){
        return score;
    }
}

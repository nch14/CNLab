package cn.chenhaonee.cnlab.vo;

/**
 * Created by chenhaonee on 2017/7/5.
 */

public class ScoreResult {
    private String git_utl;
    private int score;
    private boolean scored;

    public String getGit_utl() {
        return git_utl;
    }

    public void setGit_utl(String git_utl) {
        this.git_utl = git_utl;
    }

    public int getScore() {
        return score;
    }

    public void setScore(int score) {
        this.score = score;
    }

    public boolean isScored() {
        return scored;
    }

    public void setScored(boolean scored) {
        this.scored = scored;
    }
}

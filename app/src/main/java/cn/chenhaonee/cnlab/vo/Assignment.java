package cn.chenhaonee.cnlab.vo;

import java.util.List;

/**
 * Created by chenhaonee on 2017/6/30.
 */

public class Assignment {
    private int assignmentId;
    private List<SimpleQuestions> questions;

    public int getAssignmentId() {
        return assignmentId;
    }

    public void setAssignmentId(int assignmentId) {
        this.assignmentId = assignmentId;
    }

    public List<SimpleQuestions> getQuestions() {
        return questions;
    }

    public void setQuestions(List<SimpleQuestions> questions) {
        this.questions = questions;
    }
}

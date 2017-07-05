package cn.chenhaonee.cnlab.vo;

import java.util.List;

/**
 * Created by chenhaonee on 2017/7/5.
 */

public class StudentItem {
    private int studentId;
    private int assignmentId;
    private List<QuestionResult> questionResults;

    public int getStudentId() {
        return studentId;
    }

    public void setStudentId(int studentId) {
        this.studentId = studentId;
    }

    public int getAssignmentId() {
        return assignmentId;
    }

    public void setAssignmentId(int assignmentId) {
        this.assignmentId = assignmentId;
    }

    public List<QuestionResult> getQuestionResults() {
        return questionResults;
    }

    public void setQuestionResults(List<QuestionResult> questionResults) {
        this.questionResults = questionResults;
    }
}


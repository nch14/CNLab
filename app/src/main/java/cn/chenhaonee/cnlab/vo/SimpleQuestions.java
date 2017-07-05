package cn.chenhaonee.cnlab.vo;

import java.util.List;

import cn.chenhaonee.cnlab.Student;

/**
 * Created by chenhaonee on 2017/6/30.
 */

public class SimpleQuestions {
    private QuestionInfo questionInfo;
    private List<SimpleStudent> students;

    public QuestionInfo getQuestionInfo() {
        return questionInfo;
    }

    public void setQuestionInfo(QuestionInfo questionInfo) {
        this.questionInfo = questionInfo;
    }

    public List<SimpleStudent> getStudents() {
        return students;
    }

    public void setStudents(List<SimpleStudent> students) {
        this.students = students;
    }
}

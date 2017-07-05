package cn.chenhaonee.cnlab.vo;

import java.util.List;

/**
 * Created by chenhaonee on 2017/6/19.
 */

public class Exam {

    private int id;
    private String title;
    private String description;
    private String startAt;
    private String endAt;
    private List<Question> questions;
    private int course;
    private String status;
    private String currentTime;

    public Exam() {
    }

    public Exam(int id, String title, String description, String startAt, String endAt, List<Question> questions, int course, String status, String currentTime) {

        this.id = id;
        this.title = title;
        this.description = description;
        this.startAt = startAt;
        this.endAt = endAt;
        this.questions = questions;
        this.course = course;
        this.status = status;
        this.currentTime = currentTime;
    }

    public int getId() {
        return id;
    }

    public void setId(int id) {
        this.id = id;
    }

    public String getTitle() {
        return title;
    }

    public void setTitle(String title) {
        this.title = title;
    }

    public String getDescription() {
        return description;
    }

    public void setDescription(String description) {
        this.description = description;
    }

    public String getStartAt() {
        return startAt;
    }

    public void setStartAt(String startAt) {
        this.startAt = startAt;
    }

    public String getEndAt() {
        return endAt;
    }

    public void setEndAt(String endAt) {
        this.endAt = endAt;
    }

    public List<Question> getQuestions() {
        return questions;
    }

    public void setQuestions(List<Question> questions) {
        this.questions = questions;
    }

    public int getCourse() {
        return course;
    }

    public void setCourse(int course) {
        this.course = course;
    }

    public String getStatus() {
        return status;
    }

    public void setStatus(String status) {
        this.status = status;
    }

    public String getCurrentTime() {
        return currentTime;
    }

    public void setCurrentTime(String currentTime) {
        this.currentTime = currentTime;
    }
}

package com.zx.api.bean;

public class SafeQuestion {
    private String id;

    private Integer questionType;

    private String questionAnswer;

    private String userId;

    public String getId() {
        return id;
    }

    public void setId(String id) {
        this.id = id;
    }

    public Integer getQuestionType() {
        return questionType;
    }

    public void setQuestionType(Integer questionType) {
        this.questionType = questionType;
    }

    public String getQuestionAnswer() {
        return questionAnswer;
    }

    public void setQuestionAnswer(String questionAnswer) {
        this.questionAnswer = questionAnswer;
    }

    public String getUserId() {
        return userId;
    }

    public void setUserId(String userId) {
        this.userId = userId;
    }

    @Override
    public String toString() {
        return "SafeQuestion{" +
                "id='" + id + '\'' +
                ", questionType=" + questionType +
                ", questionAnswer='" + questionAnswer + '\'' +
                ", userId='" + userId + '\'' +
                '}';
    }
}
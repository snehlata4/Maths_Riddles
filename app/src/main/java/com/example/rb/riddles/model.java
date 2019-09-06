package com.example.rb.riddles;

public class model {
    String question;
    String answer;
    int locked;
    int levelno;
    String hint;

    public model() {

    }

    public model(String question, String answer, int locked, int levelno, String hint) {
        this.question = question;
        this.answer = answer;
        this.locked = locked;
        this.levelno = levelno;
        this.hint = hint;
    }

    public String getQuestion() {
        return question;
    }

    public void setQuestion(String question) {
        this.question = question;
    }

    public String getAnswer() {
        return answer;
    }

    public void setAnswer(String answer) {
        this.answer = answer;
    }

    public int getLocked() {
        return locked;
    }

    public void setLocked(int locked) {
        this.locked = locked;
    }

    public int getLevelno() {
        return levelno;
    }

    public void setLevelno(int levelno) {
        this.levelno = levelno;
    }

    public String getHint() {
        return hint;
    }

    public void setHint(String hint) {
        this.hint = hint;
    }
}

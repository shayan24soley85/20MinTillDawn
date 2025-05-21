package com.tildawn.Model;

public class User {
      private String username;
      private String password;
      private String securityQuestion;
      private String securityAnswer;
      private String avatarPath;
      private int score;
      public User(String username, String password, String securityQuestion, String securityAnswer,String avatarPath) {
          this.username = username;
          this.password = password;
          this.securityQuestion = securityQuestion;
          this.securityAnswer = securityAnswer;
          this.avatarPath = avatarPath;
      }

    public String getUsername() {
        return username;
    }

    public void setUsername(String username) {
        this.username = username;
    }

    public String getPassword() {
        return password;
    }

    public void setPassword(String password) {
        this.password = password;
    }

    public String getSecurityQuestion() {
        return securityQuestion;
    }

    public void setSecurityQuestion(String securityQuestion) {
        this.securityQuestion = securityQuestion;
    }

    public String getSecurityAnswer() {
        return securityAnswer;
    }

    public void setSecurityAnswer(String securityAnswer) {
        this.securityAnswer = securityAnswer;
    }

    public String getAvatarPath() {
        return avatarPath;
    }

    public int getScore() {
        return score;
    }

    public void setScore(int score) {
        this.score = score;
    }

    public void setAvatarPath(String avatarPath) {
        this.avatarPath = avatarPath;
    }
}

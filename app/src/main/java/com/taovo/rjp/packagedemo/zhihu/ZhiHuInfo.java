package com.taovo.rjp.packagedemo.zhihu;

/**
 * @author Gimpo create on 2017/10/9 12:27
 * @email : jimbo922@163.com
 */

public class ZhiHuInfo {
    private String userId;
    private String userName;
    private String follwed;  //关注了 人数
    private String follwer;  //关注者 人数
    private String zan;
    private String linkUrl;
    private String question;
    private String answer;

    public String getUserId() {
        return userId;
    }

    public void setUserId(String userId) {
        this.userId = userId;
    }

    public String getUserName() {
        return userName;
    }

    public void setUserName(String userName) {
        this.userName = userName;
    }

    public String getFollwed() {
        return follwed;
    }

    public void setFollwed(String follwed) {
        this.follwed = follwed;
    }

    public String getFollwer() {
        return follwer;
    }

    public void setFollwer(String follwer) {
        this.follwer = follwer;
    }

    public String getZan() {
        return zan;
    }

    public void setZan(String zan) {
        this.zan = zan;
    }

    public String getLinkUrl() {
        return linkUrl;
    }

    public void setLinkUrl(String linkUrl) {
        this.linkUrl = linkUrl;
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
}

package com.endicott.edu.models;

import java.io.Serializable;

public class TutorialModel implements Serializable {
    private String body = "";
    private String page = "unknown";
    private int refNum = 0;
    private boolean isCurrent = false;

    public TutorialModel(){
    }

    public TutorialModel(String body, String page, int refNum, boolean isCurrent){
        this.body = body;
        this.page = page;
        this.refNum = refNum;
        this.setCurrent(isCurrent);
    }

    public String getBody() {
        return body;
    }

    public void setBody(String body) {
        this.body = body;
    }

    public String getPage() {
        return page;
    }

    public void setPage(String page) {
        this.page = page;
    }

    public int getRefNum() {
        return refNum;
    }

    public void setRefNum(int refNum) {
        this.refNum = refNum;
    }

    public boolean isCurrent() {
        return isCurrent;
    }

    public void setCurrent(boolean current) {
        isCurrent = current;
    }
}

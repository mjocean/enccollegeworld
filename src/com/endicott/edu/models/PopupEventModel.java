package com.endicott.edu.models;

import java.io.Serializable;
import java.util.ArrayList;

/**
 * Created by CJ Mustone and Joseph Moss 10/01/18
 */
public class PopupEventModel implements Serializable {

    private boolean response;
    private String title;
    private String description;
    private String leftButtonText;
    private String leftButtonCallback;
    private String rightButtonText;
    private String rightButtonCallback;
    private String acknowledgeButtonText;
    private String acknowledgeButtonCallback = null;
    private String imagePath;
    private String altImageText;
    private String servlet;
    private int type;
    private String runId;
    private int eventId;
    private ArrayList<Upgrade> upgradeNames;

    public PopupEventModel(String title, String description, String leftButtonText, String leftButtonCallback, String rightButtonText, String rightButtonCallback, String imagePath, String altImageText){
        this.title = title;
        this.description = description;
        this.leftButtonText = leftButtonText;
        this.rightButtonText = rightButtonText;
        this.response = false;
        this.leftButtonCallback = leftButtonCallback;
        this.rightButtonCallback = rightButtonCallback;
        this.imagePath = imagePath;
        this.altImageText = altImageText;
        //Two Buttons
        this.type = 2;
    }

    //For use when a single callback with a server response is required
    public PopupEventModel(String title, String description, String acknowledgeButtonText, String acknowledgeButtonCallback, String imagePath, String altImageText){
        this.title = title;
        this.description = description;
        this.acknowledgeButtonText = acknowledgeButtonText;
        this.acknowledgeButtonCallback = acknowledgeButtonCallback;
        this.imagePath = imagePath;
        this.altImageText = altImageText;
        //One button
        this.type = 1;
    }

    //Created a new popup event model that takes in an ArrayList of Upgrades. Used by upgrade college in the Buildings page.
    public PopupEventModel(String title, String description, String leftButtonText, String leftButtonCallback, ArrayList<Upgrade> UpgradeNames, String imagePath, String altImageText){
        this.title = title;
        this.description = description;
        this.leftButtonText = leftButtonText;
        this.leftButtonCallback = leftButtonCallback;
        this.upgradeNames = UpgradeNames;
        this.imagePath = imagePath;
        this.altImageText = altImageText;
    }



    public boolean isResponse() {
        return response;
    }
    public boolean getResponse() {
        return response;
    }

    public String getTitle() {
        return title;
    }

    public String getDescription() {
        return description;
    }

    public String getLeftButtonText() {
        return leftButtonText;
    }

    public String getRightButtonText() {
        return rightButtonText;
    }

    public String getAcknowledgeButtonText() {
        return acknowledgeButtonText;
    }

    public String getAcknowledgeButtonCallback(){ return acknowledgeButtonCallback; }

    public int getType(){ return type; }

    public String getLeftButtonCallback() {
        return leftButtonCallback;
    }

    public String getRightButtonCallback() {
        return rightButtonCallback;
    }

    public String getImagePath() {
        return imagePath;
    }


    public String getAltImageText() {
        return altImageText;
    }

    public String getRunId() {
        return runId;
    }

    public void setRunId(String runId) {
        this.runId = runId;
    }

    public int getEventId() { return eventId; }

    public void setEventId(int id) { this.eventId = id; }
}

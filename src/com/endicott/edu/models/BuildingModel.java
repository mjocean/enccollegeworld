package com.endicott.edu.models;

import com.sun.xml.internal.bind.v2.runtime.reflect.opt.Const;
import java.io.Serializable;

public class BuildingModel implements Serializable {
    private final static String academicConst = "ACADEMIC";
    private final static String adminConst = "ADMIN";
    private final static String diningConst = "DINING";
    private final static String dormConst = "DORM";
    private final static String entertainmentConst = "ENTERTAINMENT";
    private final static String healthConst = "HEALTH";
    private final static String libraryConst = "LIBRARY";
    private final static String sportsConst = "SPORTS";
    private final static String baseballDiamond = "BASEBALL DIAMOND";
    private final static String footballStadium = "FOOTBALL STADIUM";
    private final static String hockeyRink = "HOCKEY RINK";

    private int capacity = 0;
    private int costPerDay = 0;
    private int hourLastUpdated = 0;
    private String name = "unknown";
    private String runId = "unknown";
    private String note = "no note";
    private int numStudents = 0;
    private String curDisaster = "none";

    private float hiddenQuality = 0;
    private float shownQuality = 0;
    private static final int maxHiddenQuality = 10;
    private static final int minHiddenQuality = -10;

    private int numRooms = 0;
    private int lengthOfDisaster = 0;
    private int hoursToComplete = 300;
    private int totalBuildCost = 0;
    private String size = "";
    private String kindOfBuilding;

    //some classes use this constructor
    public BuildingModel(int hourLastUpdated, String name, int numStudents,
                         String curDisaster, String runId, int numRooms,
                         String kindOfBuilding, String size){
        this.capacity=setCapacityBasedOnSize(size);
        this.hourLastUpdated=hourLastUpdated;
        this.name=name;
        this.numStudents=numStudents;
        this.curDisaster=curDisaster;
        this.hiddenQuality=10;
        this.shownQuality = updateShownQuality(hiddenQuality);
        this.runId=runId;
        this.numRooms=numRooms;
        this.kindOfBuilding = kindOfBuilding;
        this.size = size;
    }

    //for DormModel, DiningHallModel, LibraryModel, HealthCenterModel, and AcademicCenterModel
    public BuildingModel(String name, int numStudents, String kindOfBuilding, String size){
        this.name = name;
        this.size = size;
        this.capacity = setCapacityBasedOnSize(size);
        this.numStudents = numStudents;
        this.hiddenQuality = 10;
        this.shownQuality = updateShownQuality(hiddenQuality);
        this.kindOfBuilding = kindOfBuilding;
    }
    //For Football Stadium, Hockey Rink, and Baseball Diamond
    public BuildingModel(String name, String kindOfBuilding, String size){
        this.name = name;
        this.hiddenQuality = 10;
        this.shownQuality = updateShownQuality(hiddenQuality);
        this.kindOfBuilding = kindOfBuilding;
        this.size = size;
    }
    //for AdministrativeBldgModel, SportsCenterModel and EntertainmentCenterModel
    public BuildingModel(String name, String kindOfBuilding){
        this.name = name;
        this.hiddenQuality = 10;
        this.shownQuality = updateShownQuality(hiddenQuality);
        this.kindOfBuilding = kindOfBuilding;
    }
    //for LibraryModel and HealthCenterModel
    public BuildingModel(String kindOfBuilding){
        this.kindOfBuilding = kindOfBuilding;
    }



    public BuildingModel() {
    }

    //Helper function to set the building capacity
    public static int setCapacityBasedOnSize(String size){
        if(size.equals("Small")){return 50;}
        else if(size.equals("Medium")){return 200;}
        else if(size.equals("Large")){return 500;}
        else if(size.equals("Extra Large")){return 1000;}
        else{return 0;}
    }

    //Keeps the hidden quality between the min and max values
    public static float keepHiddenQualityWithinBounds(float hiddenQuality){
        if(hiddenQuality < minHiddenQuality){return minHiddenQuality;}
        else if(hiddenQuality > maxHiddenQuality){return maxHiddenQuality;}
        else{return hiddenQuality;}
    }

    //Updates/Sets the shown quality based on what the hidden quality is
    //This will always return a number between 0-100 since
    // the hidden quality is controlled using the function above
    public static float updateShownQuality(float hiddenQuality){
        float starting = 50;
        return starting + (hiddenQuality * 5);
    }

    public int getCapacity() {
        return capacity;
    }

    public void setCapacity(int capacity) {
        this.capacity = capacity;
    }

    public int getCostPerDay() {
        return costPerDay;
    }

    public int getHourLastUpdated() {
        return hourLastUpdated;
    }

    public void setHourLastUpdated(int hourLastUpdated) {
        this.hourLastUpdated = hourLastUpdated;
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public String getRunId() {
        return runId;
    }

    public void setRunId(String runId) {
        this.runId = runId;
    }

    public String getNote() {
        return note;
    }

    public void setNote(String note) {
        this.note = note;
    }

    public int getMaintenanceCostPerDay() {
        return costPerDay;
    }
    public void setMaintenanceCostPerDay(int numRooms){
        this.costPerDay = (((numRooms * 150))/(365*24));
    }

    public void setCostPerDay(int costPerDay) {
        this.costPerDay = costPerDay;
    }

    public float getHiddenQuality() {
        return hiddenQuality;
    }

    //Sets the new hiddenQuality within the bounds
    //Sets the shownQuality
    public void setHiddenQuality(float hiddenQuality) {
        float trueHiddenQuality = keepHiddenQualityWithinBounds(this.hiddenQuality);
        this.hiddenQuality = trueHiddenQuality;
        float newShownQuality = updateShownQuality(this.hiddenQuality);
        setShownQuality(newShownQuality);
    }

    public float getShownQuality() {
        return shownQuality;
    }

    private void setShownQuality(float shownQuality) {
        this.shownQuality = shownQuality;
    }

    public void setHoursToComplete(int hoursToComplete) {
        this.hoursToComplete = hoursToComplete;
    }

    public int getHoursToComplete() {
        return this.hoursToComplete;
    }
    public void setTotalBuildCost(int numRooms){
        this.totalBuildCost = numRooms * 1000;
    }
    public int getTotalBuildCost(){
        return this.totalBuildCost;
    }

    public int getNumStudents() {
        return numStudents;
    }

    public void setNumStudents(int numStudents) {
        this.numStudents = numStudents;
    }

    public String getCurDisaster() {
        return curDisaster;
    }

    public void setCurDisaster(String curDisaster) {
        this.curDisaster = curDisaster;
    }

    public int getNumRooms() {
        return numRooms;
    }

    public void setNumRooms(int numRooms) {
        this.numRooms = numRooms;
    }

    public int getLengthOfDisaster() {
        return lengthOfDisaster;
    }

    public void setLengthOfDisaster(int lengthOfDisaster) {
        this.lengthOfDisaster = lengthOfDisaster;
    }

    public void incrementNumStudents(int increment){
        this.numStudents += increment;
    }
    public String checkIfBeingBuilt(){
        if(this.getHoursToComplete() > 0){
            return Integer.toString(this.getHoursToComplete()) + " hours remaining";
        }
        else
            return "Built";
    }
    public String getSize() {
        return size;
    }

    public void setSize(String size) {
        this.size = size;
    }
    public String getKindOfBuilding() {
        return kindOfBuilding;
    }

    public void setKindOfBuilding(String kindOfBuilding) {
        this.kindOfBuilding = kindOfBuilding;
    }

    public static String getAcademicConst() {return academicConst;}
    public static String getAdminConst() {return adminConst;}
    public static String getDiningConst() {return diningConst;}
    public static String getDormConst() {return dormConst;}
    public static String getEntertainmentConst() {return entertainmentConst;}
    public static String getHealthConst() {return healthConst;}
    public static String getLibraryConst() {return libraryConst;}
    public static String getSportsConst() {return sportsConst;}
    public static String getBaseballDiamondConst() {return baseballDiamond;}
    public static String getFootballStadiumConst() {return  footballStadium;}
    public static String getHockeyRinkConst() {return hockeyRink;}
}
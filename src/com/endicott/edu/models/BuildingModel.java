package com.endicott.edu.models;

import java.io.Serializable;
import java.util.ArrayList;

public class BuildingModel implements Serializable {

    // These are all changed when the building is made (in the order they're set).
    private String runId = "unknown";
    private String name = "unknown";
    private String size = "";
    private int numStudents = 0;
    private float hiddenQuality = 0;
    private float shownQuality = 0;
    private String kindOfBuilding;
    private int totalBuildCost = 0;
    private int upgradeCost = 0;
    private int costPerDay = 0;
    private int capacity = 0;
    protected ArrayList<Upgrade> upgrades;

    // These are all updated dynamically (as the game is played)
    private int timeSinceLastRepair = 0;
    private String note = "no note";
    private String curDisaster = "None";
    private int lengthOfDisaster = 0;
    private int hoursToComplete = 0;
    private boolean isBuilt = true;
    private boolean isUpgradeComplete = true;
    private int repairCost = 0;
    private boolean isRepairComplete = true;

    // These are necessary qualities to keep everything in line
    private static final int maxHiddenQuality = 10;
    private static final int minHiddenQuality = -10;

    // This is simply for making a pop-up on the main page
    private boolean hasBeenAnnouncedAsComplete = true;

    //for DormModel, DiningHallModel, and AcademicCenterModel
    public BuildingModel(String name, int numStudents, String kindOfBuilding, String size){
        this.name = name;
        this.size = size;
        this.numStudents = numStudents;
        this.hiddenQuality = 10;
        this.shownQuality = updateShownQuality(hiddenQuality);
        this.kindOfBuilding = kindOfBuilding;
        setStatsBasedOnSize(size);
        this.capacity = setCapacityBasedOnSize(size);
        this.upgrades = new ArrayList<Upgrade>();
    }
    //For Football Stadium, Hockey Rink, and Baseball Diamond
    public BuildingModel(String name, String kindOfBuilding, String size){
        this.name = name;
        this.size = size;
        this.hiddenQuality = 10;
        this.shownQuality = updateShownQuality(hiddenQuality);
        this.kindOfBuilding = kindOfBuilding;
        setStatsBasedOnSize(size);
    }
    //for AdministrativeBldgModel, LibraryModel, EntertainmentCenterModel, HealthCenterModel and SportsCenterModel
    public BuildingModel(String name, String kindOfBuilding){
        this.name = name;
        this.size = "N/A";
        this.hiddenQuality = 10;
        this.shownQuality = updateShownQuality(hiddenQuality);
        this.kindOfBuilding = kindOfBuilding;
        setStatsBasedOnSize("");
        this.capacity = setCapacityBasedOnSize(size);
    }

    public BuildingModel() {
    }

    //Helper function to combine all functions that are controlled by size
    public void setStatsBasedOnSize(String size){
        setTotalBuildingCostBasedOnSize(size);
        setUpgradeCostBasedOnSize(size);
        setCostPerDayBasedOnSize(size);
    }

    //Helper function to set the building capacity
    public int setCapacityBasedOnSize(String size){
        if(size.equals("Small")){return 50;}
        else if(size.equals("Medium")){return 200;}
        else if(size.equals("Large")){return 500;}
        else if(size.equals("Extra Large")){return 1000;}
        else{return 0;}
    }

    //Helper function to set the build time
    public void setHoursToBuildBasedOnSize(String size){
        if(size.equals("Small")){setHoursToComplete(336);} //two weeks
        else if(size.equals("Medium")){setHoursToComplete(504);} //three weeks
        else if(size.equals("Large")){setHoursToComplete(720);} //one month
        else if(size.equals("Extra Large")){setHoursToComplete(840);} //five weeks
        else setHoursToComplete(504);  // You always need a fall through case
    }

    //Helper function to set the cost of building the building
    private void setTotalBuildingCostBasedOnSize(String size){
        if(size.equals("Small")){setTotalBuildCost(50000);}
        else if(size.equals("Medium")){setTotalBuildCost(150000);}
        else if(size.equals("Large")){setTotalBuildCost(350000);}
        else if(size.equals("Extra Large")){setTotalBuildCost(650000);}
        else {setTotalBuildCost(250000);}
    }

    //Helper function to set the upgrade cost
    private void setUpgradeCostBasedOnSize(String size){
        if(size.equals("Small")){setUpgradeCost(100000);}
        else if(size.equals("Medium")){setUpgradeCost(200000);}
        else if(size.equals("Large")){setUpgradeCost(300000);}
        else if(size.equals("Extra Large")){return;}
    }

    //Helper function to set the cost per day
    private void setCostPerDayBasedOnSize(String size){
        if(size.equals("Small")){setCostPerDay(50);}
        else if(size.equals("Medium")){setCostPerDay(150);}
        else if(size.equals("Large")){setCostPerDay(350);}
        else if(size.equals("Extra Large")){setCostPerDay(650);}
        else{setCostPerDay(200);}
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
        return (starting + (hiddenQuality * 5));
    }

    //Sets the new hiddenQuality within the bounds
    //Sets the shownQuality
    public void setHiddenQuality(float hiddenQuality) {
        float trueHiddenQuality = keepHiddenQualityWithinBounds(hiddenQuality);
        this.hiddenQuality = trueHiddenQuality;
        float newShownQuality = updateShownQuality(this.hiddenQuality);
        setShownQuality(newShownQuality);
    }

    //Returns the shown quality as a nicely formatted string
    public String getShownQualityString(){
        float tempShownQuality = getShownQuality();
        String shownQualityString = String.format("%.2f", tempShownQuality);
        return shownQualityString;
    }

    // Returns how many days the building has left to construct
    public String checkIfBeingBuilt(){
        if(this.getHoursToComplete() > 0){
            return Integer.toString(this.getHoursToComplete()/24) + " days remaining";
        }
        else
            return "Built";
    }

    //This advances time and keeps track of how long the building has gone without an update
    public void updateTimeSinceLastRepair(int hoursAdvanced){
        setTimeSinceLastRepair(timeSinceLastRepair + hoursAdvanced);
    }

    public void incrementNumStudents(int increment){this.numStudents += increment;}

    public String getRunId() {return runId;}
    public void setRunId(String runId) {this.runId = runId;}

    public String getName() {return name;}
    public void setName(String name) {this.name = name;}

    public String getSize() {return size;}
    public void setSize(String size) {this.size = size;}

    public int getNumStudents() {return numStudents;}
    public void setNumStudents(int numStudents) {this.numStudents = numStudents;}

    public float getHiddenQuality() {return hiddenQuality;}
    // See above for setHiddenQuality function

    public float getShownQuality() {return shownQuality;}
    private void setShownQuality(float shownQuality) {this.shownQuality = shownQuality;}

    public String getKindOfBuilding() {return kindOfBuilding;}
    public void setKindOfBuilding(String kindOfBuilding) {this.kindOfBuilding = kindOfBuilding;}

    public int getTotalBuildCost(){return this.totalBuildCost;}
    public void setTotalBuildCost(int totalBuildCost){this.totalBuildCost = totalBuildCost;}

    public int getUpgradeCost() {return upgradeCost;}
    public void setUpgradeCost(int upgradeCost) {this.upgradeCost = upgradeCost;}

    public int getCostPerDay() {return costPerDay;}
    public void setCostPerDay(int costPerDay) {this.costPerDay = costPerDay;}

    public int getCapacity() {return capacity;}
    public void setCapacity(int capacity) {this.capacity = capacity;}

    public int getTimeSinceLastRepair() {return timeSinceLastRepair;}
    public void setTimeSinceLastRepair(int timeSinceLastRepair) {this.timeSinceLastRepair = timeSinceLastRepair;}

    public String getNote() {return note;}
    public void setNote(String note) {this.note = note;}

    public String getCurDisaster() {return curDisaster;}
    public void setCurDisaster(String curDisaster) {this.curDisaster = curDisaster;}

    public int getLengthOfDisaster() {return lengthOfDisaster;}
    public void setLengthOfDisaster(int lengthOfDisaster) {this.lengthOfDisaster = lengthOfDisaster;}

    public int getHoursToComplete() {return this.hoursToComplete;}
    public void setHoursToComplete(int hoursToComplete) {this.hoursToComplete = hoursToComplete;}

    public boolean isBuilt() {return isBuilt;}
    public void setIsBuilt(boolean isBuilt){this.isBuilt = isBuilt;}

    public boolean isUpgradeComplete() {return isUpgradeComplete;}
    public void setIsUpgradeComplete(boolean isUpgradeComplete) {this.isUpgradeComplete = isUpgradeComplete;}

    public int getRepairCost() {return repairCost;}
    public void setRepairCost(int repairCost) {this.repairCost = repairCost;}

    public boolean isRepairComplete() {return  isRepairComplete;}
    public void setIsRepairComplete(boolean isRepairComplete){this.isRepairComplete = isRepairComplete;}

    public boolean isHasBeenAnnouncedAsComplete() {return hasBeenAnnouncedAsComplete;}
    public void setHasBeenAnnouncedAsComplete(boolean hasBeenAnnouncedAsComplete) {this.hasBeenAnnouncedAsComplete = hasBeenAnnouncedAsComplete;}

    public ArrayList<Upgrade> getUpgrades(){
        return this.upgrades;
    }
    public String getUpgradesString() {
        String msg = "";
        for(int i = 0; i <= this.upgrades.size(); i++) {
            msg = msg + this.upgrades.get(i).name + ": Level " + this.upgrades.get(i).currentLevel + "\n";
        }
        return msg;
    }
}
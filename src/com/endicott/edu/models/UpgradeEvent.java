package com.endicott.edu.models;

import java.lang.reflect.Array;
import java.util.ArrayList;

public class UpgradeEvent {
    private float chanceOfOccuring;
    private String description;
    private boolean isAdversity;

    private UpgradeEvent(float chanceOfOccuring, String description, boolean isAdversity) {
        this.chanceOfOccuring = chanceOfOccuring;
        this.description = description;
        this.isAdversity = isAdversity;
    }
    public static ArrayList<UpgradeEvent> getChairEvents(){
        ArrayList<UpgradeEvent> events = new ArrayList<UpgradeEvent>();
        events.add(new UpgradeEvent(2, "Your chairs are more comfortable, but not as durable", true));
        return events;
    }
    public static ArrayList<UpgradeEvent> getTableEvents(){
        ArrayList<UpgradeEvent> events = new ArrayList<UpgradeEvent>();
        events.add(new UpgradeEvent(2, "Your tables are more spacious! Your students appricate the upgrade!", false));
        return events;
    }
    public static ArrayList<UpgradeEvent> getCoffeeEvents(){
        ArrayList<UpgradeEvent> events;
        events = new ArrayList<UpgradeEvent>();
        events.add(new UpgradeEvent(2, "The coffee tastes slightly better! But is more expensive, students don't like it", true));
        return events;
    }
    public static ArrayList<UpgradeEvent> getLightingEvents(){
        ArrayList<UpgradeEvent> events;
        events = new ArrayList<UpgradeEvent>();
        events.add(new UpgradeEvent(2, "The lights you installed are brighter, the student body appreciate the upgrade!", false));
        return events;
    }
    public static ArrayList<UpgradeEvent> getCatalogueEvents(){
        ArrayList<UpgradeEvent> events;
        events = new ArrayList<UpgradeEvent>();
        events.add(new UpgradeEvent(2, "Your library catologue is upgraded, students like then new books!", false));
        events.add(new UpgradeEvent(3, "The new books already existed in your catologue, it was a waste of money!", true));
        return events;
    }
    public static ArrayList<UpgradeEvent> getComputerEvents(){
        ArrayList<UpgradeEvent> events;
        events = new ArrayList<UpgradeEvent>();
        events.add(new UpgradeEvent(2, "Your new computers are much faster! Your students appreciate the upgrade!", false));
        events.add(new UpgradeEvent(5, "The CS department is unhappy you didn't upgrade their systems!", true));
        events.add(new UpgradeEvent(3, "The librarians are unhappy as the students use the systems to game!", true));
        return events;
    }
    public static ArrayList<UpgradeEvent> getPrinterEvents(){
        ArrayList<UpgradeEvent> events;
        events = new ArrayList<UpgradeEvent>();
        events.add(new UpgradeEvent(2, "The cost of printing has gone up your students don't like that!", true));
        events.add(new UpgradeEvent(3, "Students appreciate the efficient printers!", false));
        return events;
    }
    public static ArrayList<UpgradeEvent> getProjectorEvents(){
        ArrayList<UpgradeEvent> events;
        events = new ArrayList<UpgradeEvent>();
        events.add(new UpgradeEvent(2, "The projects are aligned incorrectly, the faculty doesn't appreciate it!", true));
        return events;
    }
    public static ArrayList<UpgradeEvent> getVendingMachineEvents(){
        ArrayList<UpgradeEvent> events;
        events = new ArrayList<UpgradeEvent>();
        events.add(new UpgradeEvent(2, "Students appricate the new options in vending machines!", false));
        events.add(new UpgradeEvent(2, "Students are upset about the price increase of products in the new vending machines!", true));
        return events;
    }
    public static ArrayList<UpgradeEvent> getAirConditioningEvents(){
        ArrayList<UpgradeEvent> events;
        events = new ArrayList<UpgradeEvent>();
        events.add(new UpgradeEvent(2, "Students appreciate the new air conditioning units a lot!", false));
        events.add(new UpgradeEvent(2, "The air conditioning units have thermostat issues, either causing rooms to be too cold or too warm!", true));
        return events;
    }
    public static ArrayList<UpgradeEvent> getCubicleEvents(){
        ArrayList<UpgradeEvent> events;
        events = new ArrayList<UpgradeEvent>();
        events.add(new UpgradeEvent(2, "Workspace is bigger, leading to more productivity!", false));
        events.add(new UpgradeEvent(2, "People get lost in the Building", true));
        return events;}
    public static ArrayList<UpgradeEvent> getMeetingRoomEvents(){
        ArrayList<UpgradeEvent> events;
        events = new ArrayList<UpgradeEvent>();
        events.add(new UpgradeEvent(2, "Meetings have more involvement!", true));
        return events;
    }
    public static ArrayList<UpgradeEvent> getTurfUpgrades(){
        ArrayList<UpgradeEvent> events = new ArrayList<UpgradeEvent>();
        events.add(new UpgradeEvent(2, "Baseball players are very happy with the upgrade", false));
        events.add(new UpgradeEvent(2, "Older lawnmowers can't be used on the new turf, you need to buy new ones", true));
        return events;
    }
    public static ArrayList<UpgradeEvent> getDirtUpgrades(){
        ArrayList<UpgradeEvent> events = new ArrayList<UpgradeEvent>();
        events.add(new UpgradeEvent(2, "Baseball players are very happy with the upgrade", false));
        return events;
    }
//    public static ArrayList<UpgradeEvent>
}

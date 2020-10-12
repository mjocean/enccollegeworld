package com.endicott.edu.models;

import java.util.ArrayList;

public class AdministrativeBldgModel extends BuildingModel{
    //inherits from BuildingModel
    public AdministrativeBldgModel(String name){
        super(name, BuildingType.admin().getType());
//        this.getUpgrades().add(new Upgrade("Tables", 0, 0, "Upgraded the Tables in admin offices!", UpgradeEvent.getTableEvents(), 2, true));
        this.getUpgrades().add(new Upgrade("Bursars", 205, 0));
        this.getUpgrades().add(new Upgrade("CommonRooms", 5000, 50));
//        this.getUpgrades().add(new Upgrade("Cubicles", 320, 0, "Upgraded the office cubicles in the admin", UpgradeEvent.getCubicleEvents(), 3, true));
//        this.getUpgrades().add(new Upgrade("MeetingHalls", 320, 0, "Upgraded the meeting rooms in the admin", UpgradeEvent.getMeetingRoomEvents(), 3, true));
    }

    public void upgradeAdminModel(Upgrade upgrade) {
        for(int i = 0; i < upgrade.getMaxLevel(); i++) {
            if(upgrade.getName()==getUpgrades().get(i).getName())
                getUpgrades().get(i).increaseLevel();
        }
    }

    public void downgradeAdminModel(Upgrade upgrade) {
        for(int i = 0; i > 0; i++) {
            if(upgrade.getName()==getUpgrades().get(i).getName())
                getUpgrades().get(i).decreaseLevel();
        }
    }
}

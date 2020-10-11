package com.endicott.edu.models;

public class BaseballDiamondModel extends BuildingModel{
    //inherits from BuildingModel
    public BaseballDiamondModel(String name, String size) {
        super(name, BuildingType.baseballDiamond().getType(), size);
        this.getUpgrades().add(new Upgrade("grassQuality",0, 0));
//        this.getUpgrades().add(new Upgrade("grassQuality", 0, 0, "Upgraded the turf of the field!", UpgradeEvent.getTurfUpgrades(), 2, false));
        this.getUpgrades().add(new Upgrade("dirtQuality", 0, 0));
//        this.getUpgrades().add(new Upgrade("grassQuality", 0, 0, "Upgraded the dirt of the field!", UpgradeEvent.getDirtUpgrades(), 2, false));
        this.getUpgrades().add(new Upgrade("baseQuality", 0, 0));
        this.getUpgrades().add(new Upgrade("seatQuality", 0, 0));
        this.getUpgrades().add(new Upgrade("stadiumLights", 0, 0));
    }

    public void increaseBaseballDiamondLevel(Upgrade upgrade) {
        for(int i = 0; i < upgrade.getMaxLevel(); i++) {
            if(upgrade.getName()==getUpgrades().get(i).getName())
                getUpgrades().get(i).increaseLevel();
        }
    }

    public void decreaseBaseballDiamondLevel(Upgrade upgrade) {
        for(int i = 0; i > 0; i++) {
            if(upgrade.getName()==getUpgrades().get(i).getName())
                getUpgrades().get(i).decreaseLevel();
        }
    }
}

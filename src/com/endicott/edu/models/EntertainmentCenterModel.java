package com.endicott.edu.models;

public class EntertainmentCenterModel extends BuildingModel {
    //inherits from BuildingModel
    public EntertainmentCenterModel(String name){
        super(name, BuildingType.entertainment().getType());
        this.getUpgrades().add(new Upgrade("Games",0, 0));
        this.getUpgrades().add(new Upgrade("Sofas", 0, 0));
        this.getUpgrades().add(new Upgrade("movieInventory", 0, 0));
//        this.getUpgrades().add(new Upgrade("Games",1000, 0, "Got new games!", UpgradeEvent.getGameUpgradeEvents(), 2, true));
//        this.getUpgrades().add(new Upgrade("MovieInventory", 10000, 0, "Added new movies to the movie inventory", UpgradeEvent.getMovieUpgradeEvents(), 1, true));
    }

    public void upgradeEntertainmentCenter(Upgrade upgrade) {
        for(int i = 0; i < upgrade.getMaxLevel(); i++) {
            if(upgrade.getName()==getUpgrades().get(i).getName())
                getUpgrades().get(i).increaseLevel();
        }
    }

    public void downgradeEntertainmentCenter(Upgrade upgrade) {
        for(int i = 0; i > 0; i++) {
            if(upgrade.getName()==getUpgrades().get(i).getName())
                getUpgrades().get(i).decreaseLevel();
        }
    }

}

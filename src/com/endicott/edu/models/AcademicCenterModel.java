package com.endicott.edu.models;

public class AcademicCenterModel extends BuildingModel {
    //inherits from BuildingModel
    public AcademicCenterModel(String name, int numStudents, String size){
        super(name, numStudents, BuildingType.academic().getType(), size);
        this.setUpgrades(AcademicCenterUpgrades.getUpgrades());
    }

    public void upgradeAcademicCenter(Upgrade upgrade) {
        for(int i = 0; i < upgrade.getMaxLevel(); i++) {
            if(upgrade.getName()==getUpgrades().get(i).getName())
                getUpgrades().get(i).increaseLevel();
        }
    }

    public void downgradeAcademicCenter(Upgrade upgrade) {
        for(int i = 0; i > 0; i++) {
            if(upgrade.getName()==getUpgrades().get(i).getName())
                getUpgrades().get(i).decreaseLevel();
        }
    }
}

package com.endicott.edu.models;


public class AcademicCenterModel extends BuildingModel {
    //inherits from BuildingModel
    public AcademicCenterModel(String name, int numStudents, String size){
        super(name, numStudents, BuildingType.academic().getType(), size);

        this.getUpgrades().add(new Upgrade("libraryChairs", 0, 0));
        // these are causing issues!!!!!!!!!
//        this.getUpgrades().add(new Upgrade("libraryChairs", 0, 0, "Upgraded the chairs in classrooms!", UpgradeEvent.getChairEvents(), 2, true));
//        this.getUpgrades().add(new Upgrade("libraryDesks", 0, 0, "Upgraded the desks in classrooms!", UpgradeEvent.getTableEvents(), 2, true));
//        this.getUpgrades().add(new Upgrade("Air Conditioning", 0, 0, "Upgraded the air conditioning in classrooms!", UpgradeEvent.getAirConditioningEvents(), 2, true));
//        this.getUpgrades().add(new Upgrade("Vending Machines", 0, 0, "Upgraded the vending machines in the building!", UpgradeEvent.getVendingMachineEvents(), 2, true));
//        this.getUpgrades().add(new Upgrade("Projectors", 10000, 50, "Upgraded the projectors of classrooms!", UpgradeEvent.getProjectorEvents(), 4, true));

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

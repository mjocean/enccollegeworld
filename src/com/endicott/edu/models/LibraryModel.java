package com.endicott.edu.models;

public class LibraryModel extends BuildingModel {
    // would a librarian or staff be considered an upgrade?
    // private Upgrade librarian = new Upgrade("librarian", 0, 0);

    public LibraryModel(String name){
        super(name, BuildingType.library().getType());
        this.getUpgrades().add(new Upgrade("libraryChairs", 0, 0, "Upgraded the chairs in the library!", UpgradeEvent.getChairEvents(), 2, true));
        this.getUpgrades().add(new Upgrade("libraryDesks", 0, 0, "Upgraded the desks in the library!", UpgradeEvent.getTableEvents(), 2, true));
        this.getUpgrades().add(new Upgrade("libraryCoffee", 0, 0, "Upgraded the coffee machine in the library!", UpgradeEvent.getCoffeeEvents(), 2, true));
        this.getUpgrades().add(new Upgrade("libraryLighting", 0, 0, "Upgraded the desks in the library!", UpgradeEvent.getTableEvents(), 2, true));
        this.getUpgrades().add(new Upgrade("Catalogue", 10000, 50, "Upgraded the catalogue of books in the library!", UpgradeEvent.getCatalogueEvents(), 4, true));
        this.getUpgrades().add(new Upgrade("Computers", 3200, 50, "Upgraded the computers in the library!", UpgradeEvent.getComputerEvents(), 2, true));
        this.getUpgrades().add(new Upgrade("Printers", 5000, 50, "Upgraded the printers in the library!", UpgradeEvent.getPrinterEvents(), 2, true));
    }

    public void increaseLibraryLevel(Upgrade upgrade) {
        for(int i = 0; i < upgrade.getMaxLevel(); i++) {
            if(upgrade.getName()==getUpgrades().get(i).getName())
                getUpgrades().get(i).increaseLevel();
        }
    }

    public void decreaseLibraryLevel(Upgrade upgrade) {
        for(int i = 0; i > 0; i++) {
            if(upgrade.getName()==getUpgrades().get(i).getName())
                getUpgrades().get(i).decreaseLevel();
        }
    }
}
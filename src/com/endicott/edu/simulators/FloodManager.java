package com.endicott.edu.simulators;

import com.endicott.edu.datalayer.BuildingDao;
import com.endicott.edu.datalayer.FloodDao;
import com.endicott.edu.models.BuildingModel;
import com.endicott.edu.models.FloodModel;
import com.endicott.edu.models.NewsLevel;
import com.endicott.edu.models.NewsType;
import java.util.logging.Logger;
import javax.servlet.http.HttpSession;
import java.util.List;
import java.util.Random;

/**
 * Responsible for simulating floods at the college.
 * NOTE: THERE CAN ONLY BE ONE FLOOD AT A TIME.
 */
public class FloodManager {
    private static final float PROBABILTY_OF_FLOOD_PER_HOUR = 0.07f;
    FloodDao floodDao = new FloodDao();
    BuildingDao buildingDao = new BuildingDao();
    BuildingManager buildingManager = new BuildingManager();
    InventoryManager inventoryManager = new InventoryManager();
    private Logger logger = Logger.getLogger("FloodManager");

    /**
     * Simulate changes in floods due to passage of time at college. Called when One day goes by.
     *          -used in: CollegeManager (advanceTimeByOneDay)
     * Dao - Data Access Object
     * @param collegeId
     * @param hoursAlive number of hours college has been active.
     * @param popupManager popup manager instance
     */
    public void handleTimeChange(String collegeId, int hoursAlive, PopupEventManager popupManager) {
        Boolean hasUpgrade = hasUpgradeBeenPurchased(collegeId);

        FloodModel flood = FloodDao.getFlood(collegeId);
        List<BuildingModel> dorms = BuildingManager.getBuildingListByType(BuildingModel.getDormConst(), collegeId);

        //If there is NO flood, possibly start one:
        if (flood == null) {
            possiblyStartFlood(collegeId, hoursAlive, popupManager, hasUpgrade);
            return;
        }
        //If there IS a flood do the following:
        String floodedDorm = flood.getDormName();

        for (BuildingModel dorm : dorms) {
            if (dorm.getName().compareTo(floodedDorm) == 0){
                billCostOfFlood(collegeId, dorm);
            }
        }
        // Figures out how much times has passed since I updated floods
        // currentTime -lastTime
        int elapsedTime = hoursAlive - flood.getHourLastUpdated();
        int timeLeft = Math.max(0, flood.getHoursLeftInFlood() - elapsedTime);
        if (timeLeft <= 0) {
            FloodDao.deleteFlood(collegeId);
            buildingManager.floodStatusChange(flood.getHoursLeftInFlood(),floodedDorm, collegeId, "None");
            logger.info("EVARUBIO . handleTimeChange() -> flood has been DELETED.");
            NewsManager.createNews(collegeId, hoursAlive, "Flooding of " + floodedDorm+" has ended! ", NewsType.COLLEGE_NEWS, NewsLevel.GOOD_NEWS);
            popupManager.newPopupEvent("Flood Ended!", "The flood in "+floodedDorm+" is finally over!","Ok","okFloodEnded",
                    "resources/images/DORM.png","Unflooded Dorm");
            return;
        } else {
            flood.setHoursLeftInFlood(timeLeft);
        }
        floodDao.saveTheFlood(collegeId, flood);
    }

    /**
     * Possibly start a flood at one of the dorms at the college.
     * The dorm must be fully built.
     *
     * @param collegeId
     * @param hoursAlive
     */
    private void possiblyStartFlood(String collegeId, int hoursAlive, PopupEventManager popupManager, Boolean hasUpgrade) {
        List<BuildingModel> dorms = BuildingManager.getBuildingListByType(BuildingModel.getDormConst(), collegeId);

        logger.info(" EVARUBIO . possiblyStartFlood() START-OF-METHOD ");

        for (BuildingModel dorm : dorms) {
            if (dorm.getHoursToComplete() <= 0) {   //only if a dorm is FULLY built call didFloodStartAtThisDorm()
                logger.info(" EVARUBIO . possiblyStartFlood() there are dorms completed so call didFloodStartAtThisDorm() to check odds of creating a flood." );
                if (didFloodStartAtThisDorm(collegeId, hoursAlive, dorm, popupManager, hasUpgrade)) {     //if the odds say yes start a flood, if not nothing.
                    //logger.info(" EVARUBIO . possiblyStartFlood() didFloodStartAtThisDorm() good odds- is TRUE, FLOOD CREATED(3/6) in dorm:   " + dorm.getName() );
                    return;
                }
            }
        }
        logger.info(" EVARUBIO . possiblyStartFlood() END-OF-METHOD ");
    }

    /**
     * Possibly start a flood at the given dorm.
     *
     * @param collegeId
     * @param hoursAlive
     * @param dorm
     * @return true if flood started.
     */


    private boolean didFloodStartAtThisDorm(String collegeId, int hoursAlive, BuildingModel dorm, PopupEventManager popupManager, Boolean hasUpgrade) {
        float oddsOfFlood = (hoursAlive - dorm.getTimeSinceLastRepair()) * PROBABILTY_OF_FLOOD_PER_HOUR;
        //If a flood upgrade was bought from the store, decrease the probability of floods.
        if(hasUpgrade){
            oddsOfFlood = oddsOfFlood - 0.02f;
        }
        if (Math.random() <= oddsOfFlood) {
            BuildingManager buildingMgr = new BuildingManager();
            int randomCost = (int)(Math.random()*1500) + 1000 ;
            int randomLength = (int) (Math.random() * 72) + 24;

            FloodModel randomFlood = new FloodModel(randomCost, randomLength, randomLength, dorm.getTimeSinceLastRepair(), dorm.getName(), collegeId);

            FloodDao floodDao = new FloodDao();
            floodDao.saveTheFlood(collegeId, randomFlood);

            logger.info("EVARUBIO .  didFloodStartAtThisDorm() FLOOD CREATED name of dorm:  " + dorm.getName() + "Duration: "+ randomLength );
            popupManager.newPopupEvent("Flood in "+ dorm.getName()+"!", "Oh no! "+dorm.getName() +" has been flooded! Would you like to visit the store invest in more drains to reduce the probability of future floods? ",
                    "Go to Store","goToStore","Do nothing ($0)","doNothing", "resources/images/flood.png","flooded Dorm");
            NewsManager.createNews(collegeId, hoursAlive, "Flooding detected at " + randomFlood.getDormName(), NewsType.COLLEGE_NEWS, NewsLevel.BAD_NEWS);
            //Accountant.payBill(collegeId, "Flood cost for dorm " + dorm.getName(), randomFlood.getCostOfFlood());

            billCostOfFlood(collegeId, dorm);
            buildingMgr.floodStatusChange(randomLength , dorm.getName(), collegeId, "Flood");
            return true;
        }
        return false;
    }

    /**
     * Checks if a flood upgrade has been purchased or not
     * future use: param upgradeName the name of the upgrade to check
     * @param collegeId the college ID
     * @return the Bool indicating if upgrade was bought or not
     * */
    private Boolean hasUpgradeBeenPurchased(String collegeId){

        return inventoryManager.isPurchased("Drains investment", collegeId);

    }

    /**
     * Charge the college for flood cleanup costs.
     *         - used in: FloodManager (handleTimeChange)
     * @param collegeId
     * @param dorm
     */
    private void billCostOfFlood(String collegeId, BuildingModel dorm){
        Random rand = new Random();
        Accountant.payBill(collegeId,"Flood cleanup cost for " + dorm.getName(), rand.nextInt(500) + 500);
    }

    /**
     * Take care of any initial flood set up when college is first created.
     * @param collegeId
     */
    public static void establishCollege(String collegeId){
    }
}

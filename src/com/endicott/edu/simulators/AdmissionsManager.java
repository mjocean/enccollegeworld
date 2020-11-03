package com.endicott.edu.simulators;

//import com.endicott.edu.datalayer.AdmissionsDao;
import com.endicott.edu.datalayer.IdNumberGenDao;
import com.endicott.edu.datalayer.NameGenDao;
import com.endicott.edu.datalayer.StudentDao;
import com.endicott.edu.models.*;

import java.util.ArrayList;
import java.util.Random;
import java.util.logging.Logger;

/***
 * Admissions simulates everything having to do with potential students considering
 * attending the virtual college.  The Admissions model generates potential students
 * and tracks their interest as we approach the admissions date
 */
public class AdmissionsManager {
//    static private AdmissionsDao dao = new AdmissionsDao();
    static private Logger logger = Logger.getLogger("Admissions");
    static private StudentDao studentDao = new StudentDao();

    /**
     * Simulate the changes in potential students interested in the college
     * due to passage of time at the college.
     *
     * @param collegeId  ID of the college currently in use
     * @param hoursAlive Amount of time the college has been open
     */
    public void handleTimeChange(String collegeId, int hoursAlive, PopupEventManager popupManager) {
        CollegeManager.logger.info("Admissions running handle time change - " + CollegeManager.getDate());

    }

    /**
     * Creates students for the college by making a model filled with nothing, then calling functions to fill the fields
     *
     * @param numNewStudents The number of new students to be made
     * @param collegeId The id of the college in use
     */
    public static void generateNewCandidates(int numNewStudents, String collegeId){
        Random rand = new Random();
        ArrayList<PotentialStudentModel> students = new ArrayList<PotentialStudentModel>();
        for (int i = 0; i < numNewStudents; i++) {

            // assign a personality and a quality
            // TODO: pass in the 'tier' and 'quality' of this newly created student
            // for now, 5/8 of students will be basic,
            //          2/8 will be one tier above
            //          1/8 will be two tiers above
            int tier = 0;
            float percentage = i / (float) numNewStudents;
            if (percentage > 7 / 8.0 * numNewStudents) {
                tier = 2;
            } else if (percentage > 5 / 8.0 * numNewStudents) {
                tier = 1;
            }
            PersonalityModel pm = PersonalityModel.generateRandomModel(tier);
            QualityModel qm = QualityModel.generateRandomModel(tier);

            //Generates a random url for the student's avatar
            AvatarModel avatar = new AvatarModel();

            String name;
            GenderModel gender;

            if (rand.nextInt(10) + 1 > 5) {
                name = NameGenDao.generateName(false);
                gender = GenderModel.MALE;
            } else {
                name = NameGenDao.generateName(true);
                gender = GenderModel.FEMALE;
            }
            String firstName = name.substring(0, name.indexOf(" ") - 1);
            String lastName = name.substring(name.indexOf(" ") + 1);

            int id = IdNumberGenDao.getID(collegeId);

            String nature = StudentModel.assignRandomNature();

            PotentialStudentModel student = new PotentialStudentModel(firstName, lastName, gender, id, 0, pm, qm);
            student.setAvatarIcon(avatar);
            // TODO: Nature, too.
            students.add(student);
        }
//        dao.saveAllStudentsJustToCache(collegeId, students);
    }

}

package com.endicott.edu.ui;

import com.endicott.edu.datalayer.*;
import com.endicott.edu.models.*;
import com.endicott.edu.simulators.PopupEventManager;
import com.endicott.edu.simulators.SportManager;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpSession;
import java.util.Enumeration;
import java.util.logging.Logger;

public class InterfaceUtils {
    private  static Logger logger = Logger.getLogger("CollegeSimTalker");

    public static void openCollegeAndStoreInRequest(String collegeId, HttpServletRequest request) {
        CollegeModel college;
        UiMessage msg = new UiMessage();
        CollegeDao collegeDao = new CollegeDao();
        college = collegeDao.getCollege(collegeId);
        if (college == null) {
            msg.setMessage("Failed to find college.");
            logger.info(msg.getMessage());
        } else {
            msg.setMessage("Found college: " + college.getRunId());
            logger.info("Found college: " + collegeId);
        }

        BuildingModel[] buildings = BuildingDao.getBuildingsArray(collegeId);
        NewsFeedItemModel[] news = NewsFeedDao.getNews(collegeId);
        SportModel[] sport = SportsDao.getSportsArray(collegeId);
        SportModel[] availableSports = SportManager.getAvailableSports(collegeId);
        StudentModel[] students = StudentDao.getStudentsArray(collegeId); //   StudentSimTalker.getStudents(server, collegeId, msg);
        FacultyModel[] faculty = FacultyDao.getFacultyArray(collegeId);
        FloodModel[] flood = FloodDao.getFloodsArray(collegeId);
        PopupEventManager popupManager = new PopupEventManager();
        // TODO: load the tips using TutorialDao.getTips(collegeId);
        //FloodModel[] flood = new FloodModel[0];

        // Load the name of all colleges.
        CollegeModel[] colleges = CollegeDao.getColleges();

        logger.info("Setting attribute college: " + college);
        request.setAttribute("message",msg);
        request.setAttribute("college",college);
        request.setAttribute("colleges",colleges);
        request.setAttribute("buildings",buildings);
        request.setAttribute("news",news);
        request.setAttribute("sports", sport);
        request.setAttribute("availableSports",availableSports);
        request.setAttribute("students",students);
        request.setAttribute("faculty",faculty);
        request.setAttribute("floods",flood);
        request.getSession().setAttribute("popupMan", popupManager);
    }

    public static void setCollegeIdInSession(String collegeId, HttpServletRequest request)
    {
        HttpSession session = request.getSession();
        session.setAttribute("runId",collegeId);
    }

    public static String getCollegeIdFromSession(HttpServletRequest request)
    {
        HttpSession session = request.getSession();
        return (String) session.getAttribute("runId");
    }
//    public static void setPopupManagerInSession(PopupEventManager popupManager, HttpServletRequest request)
//    {
//        HttpSession session = request.getSession();
//        session.setAttribute("popupMan",popupManager);
//    }
//    public static PopupEventManager getPopupManagerFromSession(HttpServletRequest request)
//    {
//        HttpSession session = request.getSession();
//        return (PopupEventManager) session.getAttribute("popupMan");
//    }

    public static void logRequestParameters(javax.servlet.http.HttpServletRequest request) {
        Enumeration<String> params = request.getParameterNames();
        while(params.hasMoreElements()){
            String paramName = params.nextElement();
            logger.info("Parameter Name - "+paramName+", Value - "+request.getParameter(paramName));
        }
    }
}

package com.endicott.edu.ui;


import com.endicott.edu.datalayer.FacultyDao;
import com.endicott.edu.models.FacultyModel;
import com.endicott.edu.simulators.CollegeManager;
import com.endicott.edu.simulators.FacultyManager;
import com.endicott.edu.simulators.PopupEventManager;

import javax.servlet.RequestDispatcher;
import java.io.IOException;
import java.util.Enumeration;
import java.util.logging.Logger;

public class ViewFacultyServlet extends javax.servlet.http.HttpServlet {

    static private Logger logger = Logger.getLogger("ViewFacultyServlet");

    protected void doPost(javax.servlet.http.HttpServletRequest request, javax.servlet.http.HttpServletResponse response) throws javax.servlet.ServletException, IOException {
        logRequestParameters(request);
        PopupEventManager popupManager = (PopupEventManager) request.getSession().getAttribute("popupManager");
        String collegeId = InterfaceUtils.getCollegeIdFromSession(request);

        if (request.getParameter("addFaculty") != null) {  // addFaculty is present if addFaculty button was pressed
            addFaculty(request, response);
        }

        for(int i = 0; i < FacultyDao.getFaculty(collegeId).size(); i++) {
            if (request.getParameter("facultyRaise" + i) != null) {
                FacultyManager.giveFacultyRaise(collegeId, FacultyDao.getFaculty(collegeId).get(i));
            }

            if (request.getParameter("removeFaculty" + i) != null){
                removeFaculty(request, response, FacultyDao.getFaculty(collegeId).get(i));
            }
        }
        InterfaceUtils.setPopupEventManagerInSession(popupManager, request);
    }

    protected void doGet(javax.servlet.http.HttpServletRequest request, javax.servlet.http.HttpServletResponse response) throws javax.servlet.ServletException, IOException {
        String collegeId = InterfaceUtils.getCollegeIdFromSession(request);
        PopupEventManager popupManager = (PopupEventManager) request.getSession().getAttribute("popupMan");
        if (request.getParameter("nextDayButton") != null) {
            CollegeManager.advanceTimeByOneDay(collegeId, popupManager);
        }


        // Attempt to fetch the college and load into
        // request attributes to pass to the jsp page.
        InterfaceUtils.openCollegeAndStoreInRequest(collegeId, request);

        RequestDispatcher dispatcher=request.getRequestDispatcher("/viewfaculty.jsp");
        dispatcher.forward(request, response);
    }

    private void addFaculty(javax.servlet.http.HttpServletRequest request, javax.servlet.http.HttpServletResponse response) throws javax.servlet.ServletException, IOException {
        String collegeId = InterfaceUtils.getCollegeIdFromSession(request);
        String salaryString = request.getParameter("salaryDropdown");
        StringBuilder sb = new StringBuilder(salaryString);
        salaryString = sb.substring(1);
        int salary = Integer.valueOf(salaryString);
        if (collegeId == null) {
            UiMessage message = new UiMessage("Can't add a faculty member because missing information");
            request.setAttribute("message", message);
            logger.severe("Parameters bad for adding faculty.");
        }
        else {
            FacultyManager.addFaculty(collegeId, salary);
        }

        InterfaceUtils.openCollegeAndStoreInRequest(collegeId, request);

        RequestDispatcher dispatcher=request.getRequestDispatcher("/viewfaculty.jsp");
        dispatcher.forward(request,response);
    }

    private void removeFaculty(javax.servlet.http.HttpServletRequest request, javax.servlet.http.HttpServletResponse response, FacultyModel removedMember) throws javax.servlet.ServletException, IOException {
        String collegeId = InterfaceUtils.getCollegeIdFromSession(request);
        Boolean valid = true; // Variable that checks if ID entered is valid
        if (collegeId == null) {
            UiMessage message = new UiMessage("Can't remove a faculty member because missing information");
            request.setAttribute("message", message);
            logger.severe("Parameters bad for removing faculty.");
        }
        else {
            if(valid)
                FacultyManager.removeFaculty(collegeId, removedMember);
            else {
                UiMessage message = new UiMessage("Faculty member was not found");
                request.setAttribute("message", message);
                logger.severe("Parameters bad for finding faculty.");
            }
        }

        InterfaceUtils.openCollegeAndStoreInRequest(collegeId, request);

        RequestDispatcher dispatcher=request.getRequestDispatcher("/viewfaculty.jsp");
        dispatcher.forward(request,response);
    }

    private void logRequestParameters(javax.servlet.http.HttpServletRequest request) {
        Enumeration<String> params = request.getParameterNames();
        while(params.hasMoreElements()){
            String paramName = params.nextElement();
            logger.info("Parameter Name - "+paramName+", Value - "+request.getParameter(paramName));
        }
    }

}

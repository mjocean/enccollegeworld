package com.endicott.edu.rest;

import com.endicott.edu.datalayer.EventsDao;
import com.endicott.edu.datalayer.EverythingDao;
import com.endicott.edu.datalayer.FacultyDao;
import com.endicott.edu.models.EventsModel;
import com.endicott.edu.models.EverythingModel;
import com.endicott.edu.models.FacultyModel;
import com.endicott.edu.simulators.FacultyManager;
import com.google.gson.Gson;

import javax.servlet.http.HttpServletResponse;
import java.io.IOException;
import java.io.PrintWriter;
import java.util.List;
import java.util.logging.Logger;

/**
 * Created by Ran Ben David on 09/25/2019.
 */

public class FacultyServlet extends javax.servlet.http.HttpServlet {
    private static Logger logger = Logger.getLogger("ViewAboutServlet");

    protected void doGet(javax.servlet.http.HttpServletRequest request, javax.servlet.http.HttpServletResponse response) throws javax.servlet.ServletException, IOException {
        String pathInfo = request.getPathInfo();
        logger.info("WILL HALLER" + pathInfo);
        if (pathInfo == null) {
            response.sendError(HttpServletResponse.SC_BAD_REQUEST);
            return;
        }

        String[] splits = pathInfo.split("/");
        if(splits.length < 2) {
            response.sendError(HttpServletResponse.SC_BAD_REQUEST);
            return;
        }

        String collegeId = splits[1];
        if(splits[2].equalsIgnoreCase("hire")){
            int salary = Integer.parseInt(splits[3]);
            String department = splits[4];
            FacultyManager.addFaculty(collegeId, salary, "Dr.", department);
        }
        else {
            String facultyIndex = splits[3];
            int index = Integer.parseInt(facultyIndex);
            List<FacultyModel> faculty;
            faculty = FacultyDao.getFaculty(collegeId);
            FacultyModel facultyMember = faculty.get(index);
            if (splits[2].equalsIgnoreCase("fire")) {
                FacultyDao.removeSingleFaculty(collegeId, facultyMember);
            }
            else if (splits[2].equalsIgnoreCase("raise")) {
                FacultyDao.giveRaise(collegeId, index);
            }
        }

        response.setHeader("Access-Control-Allow-Origin", "*");
        response.setHeader("Access-Control-Allow-Methods", "POST, GET, PUT, UPDATE, OPTIONS");
        response.setHeader("Access-Control-Allow-Headers", "Content-Type, Accept, X-Requested-With");

        EverythingModel allOfEverything = EverythingDao.getEverything(collegeId);

        sendAsJson(response, allOfEverything);
    }

    //a utility method to send object
    //as JSON response
    private void sendAsJson(
            HttpServletResponse response,
            Object obj) throws IOException {

        response.setContentType("application/json");
        Gson gson = new Gson();
        String res = gson.toJson(obj);

        PrintWriter out = response.getWriter();

        out.print(res);
        out.flush();
    }
}

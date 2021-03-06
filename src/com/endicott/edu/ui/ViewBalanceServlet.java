package com.endicott.edu.ui;

import com.endicott.edu.simulators.CollegeManager;
import com.endicott.edu.simulators.PopupEventManager;

import javax.servlet.RequestDispatcher;
import javax.servlet.http.HttpSession;
import java.io.IOException;
import java.util.logging.Logger;

public class ViewBalanceServlet extends javax.servlet.http.HttpServlet {
    private static Logger logger = Logger.getLogger("ViewBalanceServlet");

    protected void doPost(javax.servlet.http.HttpServletRequest request, javax.servlet.http.HttpServletResponse response) throws javax.servlet.ServletException, IOException {
        RequestDispatcher dispatcher=request.getRequestDispatcher("/viewbalance.jsp");
        dispatcher.forward(request, response);
    }

    protected void doGet(javax.servlet.http.HttpServletRequest request, javax.servlet.http.HttpServletResponse response) throws javax.servlet.ServletException, IOException {
        String runId = InterfaceUtils.getCollegeIdFromSession(request);

        // Attempt to fetch the college and load into
        // request attributes to pass to the jsp page.
        InterfaceUtils.openCollegeAndStoreInRequest(runId, request);

        RequestDispatcher dispatcher=request.getRequestDispatcher("/viewbalance.jsp");
        dispatcher.forward(request, response);
    }
}

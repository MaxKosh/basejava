package com.maxkosh.webapp.web;

import com.maxkosh.webapp.Config;
import com.maxkosh.webapp.storage.Storage;
import javax.servlet.ServletConfig;
import javax.servlet.ServletException;
import java.io.IOException;

public class ResumeServlet extends javax.servlet.http.HttpServlet {
    Storage storage;

    @Override
    public void init(ServletConfig config) throws ServletException {
        super.init(config);
        storage = Config.getInstance().getStorage();
    }

    @Override
    public void destroy() {
        super.destroy();
    }

    @Override
    protected void doPost(javax.servlet.http.HttpServletRequest request, javax.servlet.http.HttpServletResponse response) throws javax.servlet.ServletException, IOException {

    }

    @Override
    protected void doGet(javax.servlet.http.HttpServletRequest request, javax.servlet.http.HttpServletResponse response) throws javax.servlet.ServletException, IOException {
        request.setCharacterEncoding("UTF-8");
        response.setCharacterEncoding("UTF-8");
        request.setAttribute("resumeList", storage.getAllSorted());
        request.getRequestDispatcher("/resume.jsp").forward(request, response);
    }
}

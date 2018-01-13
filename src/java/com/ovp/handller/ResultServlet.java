/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package com.ovp.handller;

import com.ovp.dao.CampaignDao;
import com.ovp.dao.ContestentDao;
import com.ovp.entities.Campaign;
import com.ovp.entities.Candidate;
import java.io.IOException;
import java.io.PrintWriter;
import java.sql.SQLException;
import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;
import java.util.logging.Level;
import java.util.logging.Logger;
import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

/**
 *
 * @author Rashmi Tiwari
 */
@WebServlet(name = "ResultServlet", urlPatterns = {"/ResultServlet"})
public class ResultServlet extends HttpServlet {
    ContestentDao contestentDao = new ContestentDao();
    CampaignDao campaignDao = new CampaignDao();

    /**
     * Processes requests for both HTTP <code>GET</code> and <code>POST</code>
     * methods.
     *
     * @param request servlet request
     * @param response servlet response
     * @throws ServletException if a servlet-specific error occurs
     * @throws IOException if an I/O error occurs
     */
    protected void processRequest(HttpServletRequest request, HttpServletResponse response)
            throws ServletException, IOException, SQLException {
        response.setContentType("text/html;charset=UTF-8");
        try (PrintWriter out = response.getWriter()) {
            List<Campaign> campaigns = campaignDao.getAllCampaign();
            request.setAttribute("campaigns", campaigns);
            String campaignId= request.getParameter("campaign_id");
            if (campaignId != null) {
                List<Candidate> candidates = contestentDao.getAllContestent(Integer.parseInt(campaignId));
                Map<String, List<Candidate>> results = new HashMap();
                for (Candidate candidate : candidates) {
                    String post = candidate.getPost();
                    List<Candidate> candidateByPost = results.get(post);
                    if (candidateByPost == null) {
                        candidateByPost = new ArrayList();
                    }
                    candidateByPost.add(candidate);
                    results.put(post, candidateByPost);
                    //results.values()
                }
                request.setAttribute("results", results);
            }
            request.getRequestDispatcher("result.jsp").forward(request, response);
        }
    }

    // <editor-fold defaultstate="collapsed" desc="HttpServlet methods. Click on the + sign on the left to edit the code.">
    /**
     * Handles the HTTP <code>GET</code> method.
     *
     * @param request servlet request
     * @param response servlet response
     * @throws ServletException if a servlet-specific error occurs
     * @throws IOException if an I/O error occurs
     */
    @Override
    protected void doGet(HttpServletRequest request, HttpServletResponse response)
            throws ServletException, IOException {
        try {
            processRequest(request, response);
        } catch (SQLException ex) {
            Logger.getLogger(ResultServlet.class.getName()).log(Level.SEVERE, null, ex);
        }
    }

    /**
     * Handles the HTTP <code>POST</code> method.
     *
     * @param request servlet request
     * @param response servlet response
     * @throws ServletException if a servlet-specific error occurs
     * @throws IOException if an I/O error occurs
     */
    @Override
    protected void doPost(HttpServletRequest request, HttpServletResponse response)
            throws ServletException, IOException {
        try {
            processRequest(request, response);
        } catch (SQLException ex) {
            Logger.getLogger(ResultServlet.class.getName()).log(Level.SEVERE, null, ex);
        }
    }

    /**
     * Returns a short description of the servlet.
     *
     * @return a String containing servlet description
     */
    @Override
    public String getServletInfo() {
        return "Short description";
    }// </editor-fold>

}

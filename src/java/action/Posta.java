/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package action;

import java.awt.Image;
import java.io.IOException;
import java.io.PrintWriter;
import java.util.logging.Level;
import java.util.logging.Logger;
import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.swing.ImageIcon;
import json.MyRequest;
import org.json.JSONException;
import org.json.JSONObject;
import protocolo.Protocolo;

/**
 *
 * @author IE
 */
@WebServlet(name = "posto", urlPatterns = {"/posto"})
public class Posta extends HttpServlet {

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
            throws ServletException, IOException {
        response.setContentType("application/json");
        request.setCharacterEncoding("UTF-8");
        try (PrintWriter out = response.getWriter()) {
            Protocolo protocolo = new Protocolo(Protocolo.Type.RESPONSE, Protocolo.Action.CREATE, Protocolo.Target.POSTAGEM);
            protocolo.valueStatus(Protocolo.Status.INFORMATIONAL, "Não foi possível concluír a solicitação");
            if (request.getContentType().equals("multipart/form-data")) {
                protocolo.config(Protocolo.Type.UPDATE, Protocolo.Action.CREATE, Protocolo.Target.POSTAGEM);
                Image img = MyRequest.inBody(request.getInputStream());
                protocolo.valueStatus(Protocolo.Status.SUCCES, "Postagem criada" + new ImageIcon(img).getIconHeight());
            } else {
                JSONObject json = MyRequest.inBody(request);
                protocolo.config(Protocolo.Type.REQUEST, Protocolo.Action.CREATE, Protocolo.Target.POSTAGEM);
                protocolo.valueStatus(Protocolo.Status.SUCCES, "dados do arquivo ->  " + json);
            }
            out.println(protocolo.getJson());
            /*
            JSONObject json = MyRequest.inBody(request);
            Protocolo protocolo = new Protocolo(Protocolo.Type.RESPONSE, Protocolo.Action.CREATE, Protocolo.Target.POSTAGEM);
            Login login = (Login) request.getSession().getAttribute("login");
            protocolo.valueStatus(Protocolo.Status.SUCCES, "recebemos o cadastro da postagem: " + login.getUserName() + " " + login.getEmail() + login.getPass());
            out.println(protocolo.prepare());
             */
        } catch (JSONException ex) {
            Logger.getLogger(Posta.class.getName()).log(Level.SEVERE, null, ex);
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
        processRequest(request, response);
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
        processRequest(request, response);
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

/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package action;

import dao.BaseDados;
import dao.DAO;
import dao.DAOCreate;
import dao.TypeBaseDados;
import endereco.Cidade;
import endereco.Endereco;
import endereco.Estado;
import endereco.Pais;
import endereco.Regiao;
import java.io.IOException;
import java.io.PrintWriter;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.text.ParseException;
import java.util.logging.Level;
import java.util.logging.Logger;
import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;
import javax.servlet.http.HttpSessionEvent;
import javax.servlet.http.HttpSessionListener;
import json.MyRequest;
import membro.KeyGenerator;
import membro.Login;
import membro.Card;
import membro.DocumentosMembro;
import membro.Membro;
import membro.TypeSexo;
import org.json.JSONException;
import org.json.JSONObject;
import protocolo.Protocolo;
import protocolo.SubProtocolo;
import tratamento.Tratamento;
import web.Page;

/**
 *
 * @author IE
 */
@WebServlet(name = "acesso", urlPatterns = {"/acesso"})
public class Acesso extends HttpServlet implements HttpSessionListener {

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
        try (PrintWriter out = response.getWriter()) {
            JSONObject cliente = MyRequest.inBody(request);
            switch (cliente.getString("action")) {
                case "create":
                    criarAcesso(request, out, cliente);
                    break;
                case "sair":
                    logOut(request.getSession(), out);
                    break;
                case "read":
                    switch (cliente.getString("status")) {
                        case "informational":
                            clienteIsLogado(request.getSession(), out);
                            break;
                        default:
                            Protocolo p = new Protocolo(Protocolo.Type.RESPONSE, Protocolo.Action.READ, Protocolo.Target.LOGIN);
                            out.println(p);
                    }

            }
        } catch (JSONException | ParseException ex) {
            Logger.getLogger(Acesso.class.getName()).log(Level.SEVERE, null, ex);
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

    @Override
    public void sessionDestroyed(HttpSessionEvent se) {
        Card card = (Card) se.getSession().getAttribute("card");
        HttpSessionListener.super.sessionDestroyed(se);
        if (card == null) {
            return;
        }
        KeyGenerator.removeKey(card.getKey());
        System.out.println(card.getKey() + " .... removida com sucesso");
    }

    private void criarAcesso(HttpServletRequest request, PrintWriter out, JSONObject cliente) throws JSONException, ParseException {
        try (DAOCreate daoCadastro = new DAOCreate(out, BaseDados.getConnection(TypeBaseDados.AMAGO))) {
            Login login = new Login(cliente.getString("loginName"), cliente.getString("email"), cliente.getString("pass"));
            Cidade cidade = new Cidade(cliente.getInt("idCidade"), cliente.getInt("idEstado"), cliente.getString("nomeCidade"));
            Endereco endereco = new Endereco(cidade);
            endereco.complementarEndereco(cliente.getString("logradouro"), cliente.getString("bairro"), cliente.getString("numero"), cliente.getString("cep"));
            TypeSexo sexo = TypeSexo.getSexo(cliente.getString("sexo"));
            DocumentosMembro documentos = new DocumentosMembro(cliente.getString("nome"), sexo, DAO.toDate(cliente.getString("nascimento")));
            Membro membro = new Membro(login, documentos);
            membro.setEndereco(endereco);
            ResultSet result = daoCadastro.registrarMembro(membro);
            Card card = new Card(result, Protocolo.Nivel.USUARIO);
            HttpSession sessao = request.getSession();
            sessao.setAttribute("card", card);
            sessao.setMaxInactiveInterval(3600);
            Page.GESTOR.toSessionAtribute(request);
            out.println(new SubProtocolo(Protocolo.Status.REDIRECT,Page.GESTOR.namePage, "Bem vindo ao Amago!"));
        } catch (SQLException ex) {
            Logger.getLogger(Acesso.class.getName()).log(Level.SEVERE, null, ex);
            Tratamento.acesso(out, ex.getMessage(), cliente);
        }
    }

    private void logOut(HttpSession sessao, PrintWriter out) {
        if (sessao.getAttribute("card") != null) {
            sessao.removeAttribute("card");
        }
        Page.HOME.toSessionAtribute(sessao);
        out.println(new SubProtocolo(Protocolo.Status.REDIRECT, Page.HOME.namePage));
    }

    private void clienteIsLogado(HttpSession sessao, PrintWriter out) {
        boolean logado = (sessao.getAttribute("card") != null);
        String pageAtual = ((Page) sessao.getAttribute("#page#")).namePage;
        out.println(new SubProtocolo(Protocolo.Status.INFORMATIONAL, pageAtual, logado + ""));
    }

}

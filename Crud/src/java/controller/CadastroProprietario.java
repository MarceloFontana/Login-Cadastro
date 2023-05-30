package controller;

import java.io.IOException;
import java.io.PrintWriter;
import jakarta.servlet.ServletException;
import jakarta.servlet.annotation.WebServlet;
import jakarta.servlet.http.HttpServlet;
import jakarta.servlet.http.HttpServletRequest;
import jakarta.servlet.http.HttpServletResponse;
import java.sql.SQLException;
import model.Usuario;
import model.ProprietarioDAO;


@WebServlet(name = "CadastroProprietario", urlPatterns = {"/CadastroProprietario"})
public class CadastroProprietario extends HttpServlet {
    private int id; 
    private String nome;
    private String sobrenome;
    private String email;
    private String celular;
    private String senha;
    private String genero;
    
    protected void processRequest(HttpServletRequest request, HttpServletResponse response)
            throws ServletException, IOException {
        response.setContentType("text/html;charset=UTF-8");
        
        //Verificando a existência do ID
        if(request.getParameter("id")!=null) {
            this.id = Integer.parseInt(request.getParameter("id"));
        }
        
        //Recebendo parâmetros vindos do formulário de cadastro
        this.nome = request.getParameter("firstname");
        this.sobrenome = request.getParameter("lastname");
        this.email = request.getParameter("email");
        this.celular =request.getParameter("number");
        this.senha = request.getParameter("password");
        this.genero = request.getParameter("gender");
        
        //Criando objeto da classe Proprietário, para que seja enviado
        //ao método de inserção no banco de dados
        Usuario p = new Usuario(
                this.nome,
                this.sobrenome,
                this.email,
                this.senha,
                this.genero,
                this.celular
        );
        
        try {
            if(request.getParameter("id")==null) {
            
                ProprietarioDAO pdao = new ProprietarioDAO();
                pdao.insertProprietario(p);
                response.sendRedirect("index.html");
            } else {
                p.setIdUsuario(this.id);
                ProprietarioDAO pdao = new ProprietarioDAO();
                pdao.updateProprietario(p);
                response.sendRedirect("listar.jsp");
            }
            
        } catch(SQLException | ClassNotFoundException erro) {
        
        try (PrintWriter out = response.getWriter()) {
            /* TODO output your page here. You may use following sample code. */
            out.println("<!DOCTYPE html>");
            out.println("<html>");
            out.println("<head>");
            out.println("<title>Servlet CadastroProprietario</title>");            
            out.println("</head>");
            out.println("<body>");
            out.println("<h1>Ocorreu algum erro: " + erro + "</h1>");
            out.println("</body>");
            out.println("</html>");
        }
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

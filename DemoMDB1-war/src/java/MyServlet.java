/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */

import java.io.IOException;
import java.io.PrintWriter;
import javax.annotation.Resource;
import javax.inject.Inject;
import javax.jms.Connection;
import javax.jms.ConnectionFactory;
import javax.jms.JMSConnectionFactory;
import javax.jms.JMSContext;
import javax.jms.MessageProducer;
import javax.jms.Queue;
import javax.jms.Session;
import javax.jms.TextMessage;
import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

/**
 *
 * @author sofiene
 */
@WebServlet(urlPatterns = {"/MyServlet"})
public class MyServlet extends HttpServlet {

    @Resource(mappedName = "jmsDemo/navinDest")
    private Queue demoNavinDest;
    @Resource(mappedName="jmsDemo/navin")
    private ConnectionFactory queue ;

    @Inject
    @JMSConnectionFactory("java:comp/DefaultJMSConnectionFactory")
    private JMSContext context;

    /**
     * Processes requests for both HTTP <code>GET</code> and <code>POST</code>
     * methods.
     *
     * @param request servlet request
     * @param response servlet response
     * @throws ServletException if a servlet-specific error occurs
     * @throws IOException if an I/O error occurs
     */
    public  void doGet(HttpServletRequest request, HttpServletResponse response)
            throws ServletException, IOException {
        try  {
            String  str = request.getParameter("t1");
            /* TODO output your page here. You may use following sample code. */
            PrintWriter out = response.getWriter();
            sendJMSMessageToNavinDest(str);
            out.println("<!DOCTYPE html>");
            out.println("<html>");
            out.println("<head>");
            out.println("<title>Servlet MyServlet</title>");            
            out.println("</head>");
            out.println("<body>");
            out.println("<h1> Ur Name is queeued </h1>");
            out.println("</body>");
            out.println("</html>");
        }
        catch(Exception e){
            System.out.println(e);
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

    /**
     * Handles the HTTP <code>POST</code> method.
     *
     * @param request servlet request
     * @param response servlet response
     * @throws ServletException if a servlet-specific error occurs
     * @throws IOException if an I/O error occurs
     */


    /**
     * Returns a short description of the servlet.
     *
     * @return a String containing servlet description
     */
    @Override
    public String getServletInfo() {
        return "Short description";
    }// </editor-fold>

    private void sendJMSMessageToNavinDest(String messageData) {
        try {
            Connection con = queue.createConnection();
            Session s = con.createSession();
            
            MessageProducer mp = s.createProducer(demoNavinDest);
            TextMessage tm = s.createTextMessage();
            tm.setText(messageData);
            mp.send(tm);
        } catch (Exception e) {
            
            System.out.println(e);
        }
    }

}

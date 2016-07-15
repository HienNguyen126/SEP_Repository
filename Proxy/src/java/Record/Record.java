/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package Record;

import java.io.IOException;
import java.io.PrintWriter;
import java.sql.Connection;
import java.sql.Date;
import java.sql.DriverManager;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.Statement;
import java.sql.Time;
import javax.servlet.ServletException;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

/**
 *
 * @author Hien
 */
public class Record extends HttpServlet {

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
        response.setContentType("text/html;charset=UTF-8");
        try (PrintWriter out = response.getWriter()) {
            /* TODO output your page here. You may use following sample code. */
            
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
        String userid = request.getParameter("userid");
        String datefrom = request.getParameter("datefrom");
        String dateto = request.getParameter("dateto");
        String sql;
        sql = "select R.RecordID , T.Name ,  R.StartTime, R.EndTime, R.[Description] from Record R, Tag T, [User] U\n" +
                "where\n" +
                "R.TagID = T.TagID\n" +
                "and\n" +
                "T.UserID = U.UserID\n" +
                "and\n" +
                "R.[Status] =1\n" +
                "and\n" +
                "U.UserID = "+userid+"\n" +
                "and\n" +
                "R.[Date] >= '"+datefrom+"'\n" +
                "and\n" +
                "R.[date] <= '"+dateto+"'";
        String result = Getdata(sql);
        response(response, result);
    }
    private void response(HttpServletResponse resp, String msg)
			throws IOException {
	PrintWriter out = resp.getWriter();
	out.println(msg);
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
    
    public String Getdata(String sql){
        String re = "";
        Connection conn = null;
        Statement stmt = null;
        ResultSet rs ;
        try
        {
            Class.forName("com.microsoft.sqlserver.jdbc.SQLServerDriver");
            conn = DriverManager.getConnection("jdbc:sqlserver://cmu.vanlanguni.edu.vn:1433;databaseName=CST23", "CST23", "nisliswodu");
            stmt = conn.createStatement();
            rs= stmt.executeQuery(sql);
            while(rs.next()){
                //Retrieve by column name
                int id = rs.getInt("RecordID");
                String name = rs.getString("Name");
                Time start = rs.getTime("StartTime");
                Time end = rs.getTime("EndTime");
                String description = rs.getString("Description");
                re = re + id + "|" +name +"|" + start+ "|"+ end +"|"+ description +"\n";
           }
        }catch(SQLException se){
            //Handle errors for JDBC
            re = se.toString();
        }catch(Exception e){
            //Handle errors for Class.forName
            re = e.toString();
        }
        finally{
            //finally block used to close resources
            try{
               if(stmt!=null)
                conn.close();
            }catch(SQLException se){
               
            }
            try{
               if(conn!=null)
                conn.close();
            }catch(SQLException se){
                re = se.toString();
            }
        }
        return re;
    }
}

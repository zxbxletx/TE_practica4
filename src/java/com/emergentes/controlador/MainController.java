
package com.emergentes.controlador;

import com.emergentes.ConexionBD;
import com.emergentes.tarea.Tarea;
import java.io.IOException;
import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.ArrayList;
import java.util.logging.Level;
import java.util.logging.Logger;
import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

@WebServlet(name = "MainController", urlPatterns = {"/MainController"})
public class MainController extends HttpServlet {

    @Override
    protected void doGet(HttpServletRequest request, HttpServletResponse response)
            throws ServletException, IOException {
        try {
            String op;
            op = (request.getParameter("op") != null) ? request.getParameter("op") : "list";
            ArrayList<Tarea> lista = new ArrayList<Tarea>();
            ConexionBD canal = new ConexionBD();
            Connection conn = canal.Conectar();
            PreparedStatement ps;
            ResultSet rs;

            if (op.equals("list")) {
                String sql = "select *from tareas";
                ps = conn.prepareStatement(sql);
                rs = ps.executeQuery();
                while (rs.next()) {
                    Tarea tar = new Tarea();
                    tar.setId(rs.getInt("id"));
                    tar.setTarea(rs.getString("tarea"));
                    tar.setPrioridad(rs.getInt("prioridad"));
                    tar.setCompletado(rs.getInt("completado"));
                    lista.add(tar);
                }
                request.setAttribute("lista", lista);
                request.getRequestDispatcher("index.jsp").forward(request, response);
            }
            if (op.equals("nuevo")) {
                Tarea t = new Tarea();
                request.setAttribute("tarea", t);
                request.getRequestDispatcher("nuevo.jsp").forward(request, response);
            }
            if (op.equals("editar")) {
                int id = Integer.parseInt(request.getParameter("id"));
                String sql = "Select *from tareas where id=?";
                ps = conn.prepareStatement(sql);
                ps.setInt(1, id);
                rs = ps.executeQuery();
                Tarea tar = new Tarea();
                while (rs.next()) {
                    tar.setId(rs.getInt("id"));
                    tar.setTarea(rs.getString("tarea"));
                    tar.setPrioridad(rs.getInt("prioridad"));
                    tar.setCompletado(rs.getInt("completado"));
                }
                request.setAttribute("tarea", tar);
                request.getRequestDispatcher("nuevo.jsp").forward(request, response);
            }
            if (op.equals("eliminar")) {
                int id = Integer.parseInt(request.getParameter("id"));
                String sql = "delete from tareas where id = ?";
                ps = conn.prepareStatement(sql);
                ps.setInt(1, id);
                ps.executeUpdate();
                response.sendRedirect("MainController");
            }
        } catch (SQLException ex) {
            System.out.println("Error al conectar:  " + ex.getMessage());
        } catch (ClassNotFoundException ex) {
            Logger.getLogger(MainController.class.getName()).log(Level.SEVERE, null, ex);
        }

    }
    @Override
    protected void doPost(HttpServletRequest request, HttpServletResponse response)
            throws ServletException, IOException {
        try {
            int id = Integer.parseInt(request.getParameter("id"));
            String tarea = request.getParameter("tarea");
            int prioridad = Integer.parseInt(request.getParameter("prioridad"));
            int completado = Integer.parseInt(request.getParameter("completado"));

            Tarea t = new Tarea();

            t.setId(id);
            t.setTarea(tarea);
            t.setPrioridad(prioridad);
            t.setCompletado(completado);

            ConexionBD canal = new ConexionBD();
            Connection conn = canal.Conectar();
            PreparedStatement ps;
            ResultSet rs;

            if (id == 0) {
                String sql = "insert into tareas (tarea,prioridad,completado) values (?,?,?)";
                ps = conn.prepareStatement(sql);
                ps.setString(1, t.getTarea());
                ps.setInt(2, t.getPrioridad());
                ps.setInt(3, t.getCompletado());
                ps.executeUpdate();
                response.sendRedirect("MainController");
            } else {
                String sql = "Update tareas set tarea = ?, prioridad = ?, completado = ? where id = ?";
                ps = conn.prepareStatement(sql);
                ps.setString(1, t.getTarea());
                ps.setInt(2, t.getPrioridad());
                ps.setInt(3, t.getCompletado());
                ps.setInt(4, t.getId());
                ps.executeUpdate();
                response.sendRedirect("MainController");
            }

        } catch (SQLException ex) {
            System.out.println("Error dell SQL:  " + ex.getMessage());
        } catch (ClassNotFoundException ex) {
            Logger.getLogger(MainController.class.getName()).log(Level.SEVERE, null, ex);
        }
    }

}
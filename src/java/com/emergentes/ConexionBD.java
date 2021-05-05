/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package com.emergentes;

import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.SQLException;

/**
 *
 * @author Zxbxl
 */
public class ConexionBD {

    private String url = "jdbc:mysql://localhost:3306/bd_proyectos";
    private String usuario = "root";
    private String password = "";
    protected Connection conn = null;

    public ConexionBD() throws ClassNotFoundException {

        try {
            Class.forName("com.mysql.jdbc.Driver");
            conn = DriverManager.getConnection(url, usuario, password);
            if (conn != null) {
                System.out.println("Conexion OK : " + conn);
            }
        } catch (SQLException ex) {
            System.out.println("Error de SQL : " + ex.getMessage());
        } catch (ClassNotFoundException ex) {
            System.out.println("Error de Driver : " + ex.getMessage());
        }
    }

    public Connection Conectar() {
        return conn;
    }

    public void Desconectar() {
        System.out.println("Cerrando conexion: " + conn);
        try {
            conn.close();
        } catch (SQLException ex) {
            System.out.println("Error de SQL: " + ex.getMessage());
        }
    }
}

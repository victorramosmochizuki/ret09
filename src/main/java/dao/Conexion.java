package dao;


import java.sql.Connection;
import java.sql.DriverManager;

import java.util.logging.Level;
import java.util.logging.Logger;
import javax.swing.JOptionPane;

public class Conexion {

    private static Connection cnx = null;
    
    public static Connection conectar() {
        try {
            String user = "EDUCASI";
            String pwd = "1234";
            String driver = "oracle.jdbc.OracleDriver";
            String url = "jdbc:oracle:thin:@localhost:1521/XE";
            Class.forName(driver).newInstance();
            cnx = DriverManager.getConnection(url, user, pwd);
        } catch (Exception e) {
            System.out.println("Error de Conexión/Conectar " + e.getMessage());
        }
        return cnx;
    }

    public static Connection getCnx() {
        return cnx;
    }

    public static void setCnx(Connection aCnx) {
        cnx = aCnx;
    }


    public void Cerrar() throws Exception {
        if (cnx != null) {
            cnx.close();
        }
    }

    public static void main(String[] args) throws Exception {
        conectar();
        try {
            if (cnx != null) {
                System.out.println("CONEXIÓN EXITOSA");
                JOptionPane.showMessageDialog(null, "CONEXIÓN EXITOSA", "CORRECTO", JOptionPane.INFORMATION_MESSAGE);
            } else {
                System.out.println("SIN CONEXIÓN REVISA");
                JOptionPane.showMessageDialog(null, "SIN CONEXIÓN REVISA", "ERROR", JOptionPane.ERROR_MESSAGE);
            }
        } catch (Exception e) {
            System.out.println("Error en " + e.getMessage());
            Logger.getLogger(Conexion.class.getName()).log(Level.SEVERE, null, e);
        }
    }

    public Connection getCn() {
        return cnx;
    }

    public void setCn(Connection cnx) {
        this.cnx = cnx;
    }

}
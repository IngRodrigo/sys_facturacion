package model;

import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.Statement;

public class Conexion {

    private final String baseD = "sys_facturacion";
    private final String userD = "root";
    private final String passwordD = "";
    //private final String urlD = "jdbc:mysql://192.168.10.99/" + baseD;
    private final String urlD = "jdbc:mysql://localhost/" + baseD;
    private Connection con = null;

    private PreparedStatement ps;
    private ResultSet rs;
    private Statement db;

    public Connection getConexion() {
        try {
            Class.forName("com.mysql.jdbc.Driver");
            con = DriverManager.getConnection(urlD, userD, passwordD);
            System.out.println("Conexion extablecida");
        } catch (Exception e) {
            System.out.println("Error al intentar conectar: " + e);
        }
        return con;
    }

    public Connection closeConexion() {
        con = null;
        System.out.println("Conexion terminada");
        return con;

    }

    public Connection openConexion() {
        getConexion();
        return con;
    }

    public ResultSet consultaSelect(String sql) {

        try {
            ps = openConexion().prepareStatement(sql);
            rs = ps.executeQuery();

        } catch (Exception e) {
            System.err.println("Error clase Carrera_DAO metodo listarCarrera " + e);
            closeConexion();
        }
        return rs;
    }

    public boolean insertarRegistro(Connection conexion, String sql) {
        boolean resultado = false;

        try {
            db = conexion.prepareStatement(sql);
            resultado = true;
        } catch (SQLException e) {
            System.out.println(e);
            resultado = false;
        }

        return resultado;
    }

}

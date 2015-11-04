/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package baserelacionala;

import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.Statement;
import java.util.logging.Level;
import java.util.logging.Logger;

/**
 *
 * @author oracle
 */
public class BaseRelacionalA {

    public static void main(String[] args) {
        // TODO code application logic here

        BaseRelacionalA brA = new BaseRelacionalA();

        brA.conexion();
        brA.listaProduct();
//        brA.insertProduct("p1", "parafusos", 3);
//        brA.insertProduct("p2", "cravos", 4);
//        brA.insertProduct("p3", "tachas", 6);
//        brA.actuPrecio("p1", 33);

    }

    private static Connection conn;

    public void conexion() {

        try {
            String driver = "jdbc:oracle:thin:";
            String host = "localhost.localdomain"; // tambien puede ser una ip como "192.168.1.14"
            String porto = "1521";
            String sid = "orcl";
            String usuario = "hr";
            String password = "hr";
            String url = driver + usuario + "/" + password + "@" + host + ":" + porto + ":" + sid;

            conn = DriverManager.getConnection(url);
            System.out.println("Conexion establecida.");

        } catch (SQLException ex) {
            Logger.getLogger(BaseRelacionalA.class.getName()).log(Level.SEVERE, null, ex);
        }
    }

    public void insertProduct(String cod, String desc, int prez) {
        try {

            conn.createStatement().executeUpdate("insert into produtos values('" + cod + "','" + desc + "'," + prez + ")");
            System.out.println("Inserccion realizada con éxito");

        } catch (SQLException ex) {
            Logger.getLogger(BaseRelacionalA.class.getName()).log(Level.SEVERE, null, ex);
        }

    }

    public void listaProduct() {
        try {
            ResultSet rs;
            rs = conn.createStatement().executeQuery("select * from produtos");
            while (rs.next() != false) {
                System.out.print(rs.getString(1) + " - ");
                System.out.print(rs.getString(2) + " - ");
                System.out.println(rs.getString(3));
            }

        } catch (SQLException ex) {
            Logger.getLogger(BaseRelacionalA.class.getName()).log(Level.SEVERE, null, ex);
        }

    }

    public void actuPrecio(String cod, int prezo) {
        try {
            conn.createStatement().executeUpdate("update produtos set prezo=" + prezo + "where codigo='" + cod + "'");
            System.out.println("Update realizado");
        } catch (SQLException ex) {
            Logger.getLogger(BaseRelacionalA.class.getName()).log(Level.SEVERE, null, ex);
        }
    }

}

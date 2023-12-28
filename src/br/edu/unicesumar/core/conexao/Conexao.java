package br.edu.unicesumar.core.conexao;

import java.sql.Connection;
import java.sql.SQLException;
import java.sql.DriverManager;
import javax.swing.JOptionPane;


public class Conexao {
    private static final String URL = "jdbc:mysql://localhost:3306/mapa";
    private static final String USUARIO = "root";
    private static final String SENHA = "Leia1993@";
    private static Connection conn;

    public static Connection getConexao() {

            try {
                if (conn == null) {
                    conn = DriverManager.getConnection(URL, USUARIO, SENHA);
                    return conn;
                } 
                else {
                    return conn;
                }

            } catch (SQLException e) {
                e.printStackTrace();
                JOptionPane.showMessageDialog(null, "Houve um erro ao tentar conectar com banco de dados.");
                return null;
            }
        }
}
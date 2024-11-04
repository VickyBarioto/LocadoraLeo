/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package DAO;

import java.io.Serializable;
import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.SQLException;


public class Conexao implements Serializable {

    private static Conexao conexão = null;
    private static Connection connection;
    private String usuario;
    private String senha;
    private String url;

    private Conexao() {
        // Altere o usuário e senha de acordo com o banco de dados instalado
        usuario = "root";
        senha = "root";
        
        // Defina aqui o nome do seu banco de dados
        url = "jdbc:mysql://Locadora:3306/Locadora";

        try {
            Class.forName("com.mysql.jdbc.Driver");
            connection = DriverManager.getConnection(url, usuario, senha);
        } catch (ClassNotFoundException e) {
        } catch (SQLException e) {
        }
    }

    public static Connection getInstance() {
        if (conexão == null) {
            synchronized (Conexao.class) {
                conexão = new Conexao();
            }
        }
        return connection;
    }

    public static void closeInstance() throws SQLException {
        if (conexão != null) {
            connection.close();
        }
    }

    public static void setAutoCommit(boolean vlr) throws SQLException {
        connection.setAutoCommit(vlr);
    }

    public static void commit() throws SQLException {
        connection.commit();
    }

    public static void rollback() throws SQLException {
        connection.rollback();
    }
}

/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package conexao;

import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.SQLException;
import javax.swing.JOptionPane;

/**
 *
 * @author Aluno
 */
public class Conexao {
    private static Connection conn = null;
    
    private static final String url = "jdbc:mysql://localhost:3307/gerenciador";
    private static final String user = "root";
    private static final String senha = "";
    
    private Conexao() {
    }
    
    public static synchronized Connection conectar(){
        try {
            conn = DriverManager.getConnection(url, user, senha);
        } catch(SQLException e){
            e.printStackTrace();
        }
        return conn;
    }
    
    public void testarConexao(){
        Connection conn = conectar();
        if(conn == null){
            JOptionPane.showMessageDialog(null,"Erro ao conectar ao banco de dados");
        }
        else{
            JOptionPane.showMessageDialog(null, "Conectado com sucesso!");
        }
    }
}

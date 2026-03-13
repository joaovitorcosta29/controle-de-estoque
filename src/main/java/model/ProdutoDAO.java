/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package model;

import conexao.Conexao;
import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.SQLException;

/**
 *
 * @author joaov
 */

public class ProdutoDAO {
    public void inserirProdutos(ProdutoBean produto){
        try{
            Connection conn = Conexao.conectar();
            PreparedStatement stmt = null;
            
            stmt = conn.prepareStatement("INSERT INTO produtos (nomeProduto, precoProduto, quantidadeProduto) VALUES(?, ?, ?)");
            stmt.setString(1,produto.getNomeProduto());
            stmt.setDouble(2,produto.getPrecoProduto());
            stmt.setInt(3,produto.getQuantidadeProduto());
            stmt.executeUpdate();
            
        } catch(SQLException e){
            e.printStackTrace();
        }
        
    }
}

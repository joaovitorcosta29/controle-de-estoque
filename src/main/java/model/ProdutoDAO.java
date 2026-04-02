/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package model;

import conexao.Conexao;
import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.ArrayList; 
import java.util.List;

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
    
    public void deletarProduto(int id) {
        try {
            Connection conn = Conexao.conectar();
            PreparedStatement stmt = null;
            
            stmt = conn.prepareStatement("DELETE FROM produtos WHERE idProduto = ?");
            stmt.setInt(1, id);

            stmt.executeUpdate();
            stmt.close();
            conn.close();
            
        } catch (SQLException e) {
            e.printStackTrace();
        }
    }
    
    public void editarProduto(ProdutoBean tarefa) {
        try {
            // Estabelece conexão com o banco de dados
            Connection conn = Conexao.conectar();
            PreparedStatement stmt = null;
            
            stmt = conn.prepareStatement("UPDATE produtos SET nomeProduto = ?, precoProduto = ?, quantidadeProduto = ? WHERE idProduto = ?");
            stmt.setString(1, tarefa.getNomeProduto());
            stmt.setDouble(2, tarefa.getPrecoProduto());
            stmt.setInt(3, tarefa.getQuantidadeProduto());
            stmt.setInt(4, tarefa.getIdProduto());
            
            stmt.executeUpdate();
            stmt.close();
            conn.close();
            
        } catch (SQLException e) {
            e.printStackTrace();
        }
    }
    
    public List<ProdutoBean> listarProdutos(){
        List<ProdutoBean> produtos = new ArrayList();
        try {
            // Estabelece conexão com o banco de dados
            Connection conn = Conexao.conectar();
            PreparedStatement stmt = null;
            ResultSet rs = null;
            
            stmt = conn.prepareStatement("SELECT * FROM produtos ORDER BY idProduto");
            
            rs = stmt.executeQuery();

            while (rs.next()) {
                ProdutoBean produto = new ProdutoBean();
                produto.setIdProduto(rs.getInt("idProduto"));
                produto.setNomeProduto(rs.getString("nomeProduto"));
                produto.setPrecoProduto(rs.getDouble("precoProduto"));
                produto.setQuantidadeProduto(rs.getInt("quantidadeProduto"));
                
                produtos.add(produto);
            }
            
            rs.close();
            stmt.close();
            conn.close();
            
        } catch (SQLException e) {
            e.printStackTrace();
        }
        return produtos;
    }
}

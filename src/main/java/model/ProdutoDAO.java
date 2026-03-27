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
    public java.util.List<ProdutoBean> listar() {
    java.util.List<ProdutoBean> lista = new java.util.ArrayList<>();

    String sql = "select * from produtos order by idProduto";

    try (Connection conn = Conexao.conectar();
         PreparedStatement stmt = conn.prepareStatement(sql);
         ResultSet rs = stmt.executeQuery()) {

        while (rs.next()) {
            ProdutoBean historico = new ProdutoBean();
            historico.setIdProduto(rs.getInt("idProduto"));
            historico.setNomeProduto(rs.getString("NomeProduto"));
            historico.setPrecoProduto(rs.getDouble("PrecoProduto"));
            historico.setQuantidadeProduto(rs.getInt("QuantidadeProduto"));
            lista.add(historico);
        }

    } catch (SQLException e) {
        e.printStackTrace();
    }

    return lista;
    }
}

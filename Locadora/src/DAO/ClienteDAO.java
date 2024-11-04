/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package DAO;

import java.sql.PreparedStatement;
import java.sql.SQLException;
import Model.Cliente;
import java.sql.ResultSet;


public class ClienteDAO {
    PreparedStatement pst;
    String sql;
    
    public void salvar(Cliente cliente) throws SQLException {
        sql = "insert into Cliente values (?,?,?,?)";
        pst = Conexao.getInstance().prepareStatement(sql);
        pst.setInt(1, 0);
        pst.setString(2,cliente.getNomeCliente());
        pst.setString(3,cliente.getEnderecoCliente());
        pst.setString(4,cliente.getBairroCliente());
        pst.execute();
        pst.close();
    }
    
    public Cliente buscaClienteCodigo(String codigo) throws SQLException{
        sql = "select * from cliente where codigo = " + codigo;
        pst = Conexao.getInstance().PrepareStatement(sql);
        ResultSet rs = pst.executeQuery();
        Cliente cli = null;
        while (rs.next()){
        cli = new Cliente (rs.getInt("codigo"), rs.getString("nome"), rs.getString("endereco"), rs.getString("bairro"))
        }
        pst.close();
        return cli;
    }
}

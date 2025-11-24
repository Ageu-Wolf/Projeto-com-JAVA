/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package model.dao;

import connection.ConnectionFactory;
import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.SQLException;
import java.util.ArrayList;
import javax.swing.JOptionPane;
import model.bean.Profissionalbean; 
import java.sql.ResultSet;

/**
 *
 * @author aluno
 */
public class ProfissionalDAO {
    private Connection con;

    public ProfissionalDAO(){
        con = ConnectionFactory.getConnection();
    }
    
    public boolean create(Profissionalbean profissional){
       PreparedStatement stmt = null;
       try {

           String sql = "INSERT INTO profissionalsaude (registroProfissional, nome, cpf) VALUES (?, ?, ?)";
           stmt = con.prepareStatement(sql);

           stmt.setString(1, profissional.getRegistroprofissional());
           stmt.setString(2, profissional.getNome()); 
           stmt.setString(3, profissional.getCpf()); 
           
         
           stmt.executeUpdate();
           return true;
       } catch (SQLException ex) {
           System.err.println("Erro ao salvar Informações: " + ex.getMessage());
           return false;
       } finally {
           
           ConnectionFactory.closeConnection(con, stmt);
       }
   }
    public ArrayList<Profissionalbean> read(){
       PreparedStatement stmt = null;
       ResultSet rs = null;
       ArrayList<Profissionalbean>  listaprodutos = new ArrayList<>();
       try {
           
           stmt = con.prepareStatement("SELECT * FROM profissionalsaude ORDER by idprofissional");
           rs = stmt.executeQuery();
           
           while(rs.next()){
               Profissionalbean profissional = new Profissionalbean();
               profissional.setIdProfissional(rs.getInt("idprofissional"));
               profissional.setRegistroprofissional(rs.getString("registroProfissional"));
               profissional.setNome(rs.getString("nome"));
               profissional.setCpf(rs.getString("cpf"));
               listaprodutos.add(profissional);
            }
       } catch (SQLException ex) {
           System.out.println("Erro"+ ex);
           JOptionPane.showMessageDialog(null, "Erro ao procurar Profissionais", "Erro", JOptionPane.ERROR_MESSAGE);
       } finally {
           
           ConnectionFactory.closeConnection(con, stmt, rs);
       }   return listaprodutos;
   } 
    public boolean update( Profissionalbean profissional){
       PreparedStatement stmt = null;
       try {
           
           stmt = con.prepareStatement("UPDATE profissionalsaude set registroProfissional = ?, nome=?, cpf=? WHERE idprofissional = ?");
           stmt.setString(1, profissional.getRegistroprofissional());
           stmt.setString(2, profissional.getNome());
           stmt.setString(3, profissional.getCpf());
           stmt.setInt(4, profissional.getIdProfissional());
           stmt.executeUpdate();
           return true;
       } catch (SQLException ex) {
           System.err.println("Erro ao atualizar: "+ ex);
            return false;
       } finally {
           ConnectionFactory.closeConnection(con, stmt);
      }
       
   }
    public boolean delete(Profissionalbean profissional){
       PreparedStatement stmt = null;
       try{
           stmt = con.prepareStatement("DELETE FROM profissionalsaude WHERE idprofissional=?");
           stmt.setInt(1, profissional.getIdProfissional());
           stmt.executeUpdate();
           return true;
       }catch (SQLException ex){
           System.err.println("Erro ao excluir:" + ex);
           return false;
       }finally{
           ConnectionFactory.closeConnection(con, stmt);
       }
      
   }
    public ArrayList<Profissionalbean> getListaProdutoporDescricao(String descricao){
       PreparedStatement stmt = null;
       ResultSet rs = null;
       ArrayList<Profissionalbean>  listaprodutos = new ArrayList<>();
       try {
           
           stmt = con.prepareStatement("SELECT * FROM produto WHERE descricao ILIKE ? ORDER by idproduto");
           stmt.setString(1, "%" + descricao + "%");
           rs = stmt.executeQuery();
           
           while(rs.next()){
               Profissionalbean profissional = new Profissionalbean();
               profissional.setIdProfissional(rs.getInt("idprofissional"));
               profissional.setRegistroprofissional(rs.getString("registroProfissional"));
               profissional.setNome(rs.getString("nome"));
               profissional.setCpf(rs.getString("cpf"));
               listaprodutos.add(profissional);
            }
       } catch (SQLException ex) {
           System.out.println("Erro"+ ex);
           JOptionPane.showMessageDialog(null, "Erro ao procurar Profissionais", "Erro", JOptionPane.ERROR_MESSAGE);
       } finally {
           
           ConnectionFactory.closeConnection(con, stmt, rs);
       }   return listaprodutos;
   } 
    public Profissionalbean getById(int id) {
    PreparedStatement stmt = null;
    ResultSet rs = null;
    Profissionalbean profissional = null;

    try {
        stmt = con.prepareStatement("SELECT * FROM profissional WHERE idProfissional = ?");
        stmt.setInt(1, id);
        rs = stmt.executeQuery();

        if (rs.next()) {
            profissional = new Profissionalbean();
            profissional.setIdProfissional(rs.getInt("idProfissional"));
            profissional.setNome(rs.getString("nome"));
            profissional.setRegistroprofissional(rs.getString("registroprofissional"));
            profissional.setCpf(rs.getString("cpf"));
        }

    } catch (SQLException ex) {
        System.err.println("Erro ao buscar profissional por ID: " + ex);
    } finally {
        ConnectionFactory.closeConnection(con, stmt, rs);
    }

    return profissional;
}

}

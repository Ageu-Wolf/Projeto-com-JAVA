package model.DAO;

import connection.ConnectionFactory;
import model.bean.Vacinabean;

import java.sql.*;
import java.util.ArrayList;
import javax.swing.JOptionPane;

public class VacinaDAO {

    private Connection con;

    public VacinaDAO() {
        con = ConnectionFactory.getConnection();
    }

    public boolean create(Vacinabean vacina) {
        String sql = "INSERT INTO vacina (nome, fabricante, dosesnecessarias) VALUES (?, ?, ?)";
        PreparedStatement stmt = null;

        try {
            stmt = con.prepareStatement(sql);
            stmt.setString(1, vacina.getNome());
            stmt.setString(2, vacina.getFabricante());
            stmt.setInt(3, vacina.getDosesNecessarias());
            stmt.executeUpdate();
            return true;
        } catch (SQLException ex) {
            System.err.println("Erro ao inserir vacina: " + ex);
            return false;
        } finally {
            ConnectionFactory.closeConnection(con, stmt);
        }
    }

    public ArrayList<Vacinabean> read() {
        String sql = "SELECT * FROM vacina ORDER BY idvacina";
        PreparedStatement stmt = null;
        ResultSet rs = null;
        ArrayList<Vacinabean> lista = new ArrayList<>();

        try {
            stmt = con.prepareStatement(sql);
            rs = stmt.executeQuery();

            while (rs.next()) {
                Vacinabean v = new Vacinabean();
                v.setIdVacina(rs.getInt("idvacina"));
                v.setNome(rs.getString("nome"));
                v.setFabricante(rs.getString("fabricante"));
                v.setDosesNecessarias(rs.getInt("dosesnecessarias"));
                lista.add(v);
            }
        } catch (SQLException ex) {
            JOptionPane.showMessageDialog(null, "Erro ao listar vacinas: " + ex.getMessage());
        } finally {
            ConnectionFactory.closeConnection(con, stmt, rs);
        }

        return lista;
    }

    public boolean delete(Vacinabean vacina) {
        String sql = "DELETE FROM vacina WHERE idvacina = ?";
        PreparedStatement stmt = null;

        try {
            stmt = con.prepareStatement(sql);
            stmt.setInt(1, vacina.getIdVacina());
            stmt.executeUpdate();
            return true;
        } catch (SQLException ex) {
            JOptionPane.showMessageDialog(null, "Erro ao excluir vacina: " + ex.getMessage());
            return false;
        } finally {
            ConnectionFactory.closeConnection(con, stmt);
        }
    }

    public boolean update(Vacinabean vacina) {
        String sql = "UPDATE vacina SET nome = ?, fabricante = ?, dosesnecessarias = ? WHERE idvacina = ?";
        PreparedStatement stmt = null;

        try {
            stmt = con.prepareStatement(sql);
            stmt.setString(1, vacina.getNome());
            stmt.setString(2, vacina.getFabricante());
            stmt.setInt(3, vacina.getDosesNecessarias());
            stmt.setInt(4, vacina.getIdVacina());
            stmt.executeUpdate();
            return true;
        } catch (SQLException ex) {
            System.err.println("Erro ao atualizar vacina: " + ex);
            return false;
        } finally {
            ConnectionFactory.closeConnection(con, stmt);
        }
    }
    public Vacinabean getById(int id) {
    PreparedStatement stmt = null;
    ResultSet rs = null;
    Vacinabean vacina = null;

    try {
        stmt = con.prepareStatement("SELECT * FROM vacina WHERE idVacina = ?");
        stmt.setInt(1, id);
        rs = stmt.executeQuery();

        if (rs.next()) {
            vacina = new Vacinabean();
            vacina.setIdVacina(rs.getInt("idVacina"));
            vacina.setNome(rs.getString("nome"));
            vacina.setFabricante(rs.getString("fabricante"));
            vacina.setDosesNecessarias(rs.getInt("dosesNecessarias"));
        }

    } catch (SQLException ex) {
        System.err.println("Erro ao buscar vacina por ID: " + ex);
    } finally {
        ConnectionFactory.closeConnection(con, stmt, rs);
    }

    return vacina;
}

}

package model.DAO;

import connection.ConnectionFactory;
import model.bean.AplicarVacinabean;

import java.sql.*;
import java.util.ArrayList;
import javax.swing.JOptionPane;
import model.bean.Pacientebean;
import model.bean.Profissionalbean;
import model.bean.Vacinabean;

public class AplicarVacinaDAO {

    private Connection con = null;

    public AplicarVacinaDAO() {
        con = ConnectionFactory.getConnection();
    }

    public boolean create(AplicarVacinabean vacina) {
        PreparedStatement stmt = null;
        String sql = "INSERT INTO aplicavacina (idprofissional, idpaciente, idvacina, dataaplicacao, local) VALUES (?,?,?,?,?)";

        try {
            stmt = con.prepareStatement(sql);
            stmt.setInt(1, vacina.getIdprofissional());
            stmt.setInt(2, vacina.getIdpaciente());
            stmt.setInt(3, vacina.getIdvacina());
            stmt.setString(4, vacina.getDataaplicacao());
            stmt.setString(5, vacina.getLocal());
            stmt.executeUpdate();
            return true;
        } catch (SQLException ex) {
            System.err.println("Erro ao inserir vacina: " + ex);
            return false;
        } finally {
            ConnectionFactory.closeConnection(con, stmt);
        }
    }

    public ArrayList<AplicarVacinabean> read() {
    PreparedStatement stmt = null;
    ResultSet rs = null;
    ArrayList<AplicarVacinabean> lista = new ArrayList<>();

    String sql = "SELECT a.idAplicavacina, a.dataaplicacao, a.local, " +
                 "p.idPaciente, p.nome AS nome_paciente, " +
                 "pr.idProfissional, pr.nome AS nome_profissional, " +
                 "v.idVacina, v.nome AS nome_vacina " +
                 "FROM aplicavacina a " +
                 "JOIN paciente p ON a.idPaciente = p.idPaciente " +
                 "JOIN profissionalsaude pr ON a.idProfissional = pr.idProfissional " +
                 "JOIN vacina v ON a.idVacina = v.idVacina " +
                 "ORDER BY a.idAplicavacina";

    try {
        stmt = con.prepareStatement(sql);
        rs = stmt.executeQuery();

        while (rs.next()) {
            AplicarVacinabean aplicar = new AplicarVacinabean();
            aplicar.setIdaplicavacina(rs.getInt("idAplicavacina"));
            aplicar.setDataaplicacao(rs.getString("dataaplicacao"));
            aplicar.setLocal(rs.getString("local"));
            aplicar.setIdpaciente(rs.getInt("idPaciente"));
            aplicar.setIdprofissional(rs.getInt("idProfissional"));
            aplicar.setIdvacina(rs.getInt("idVacina"));

            // Set paciente
            Pacientebean paciente = new Pacientebean();
            paciente.setIdPaciente(rs.getInt("idPaciente"));
            paciente.setNome(rs.getString("nome_paciente"));
            aplicar.setPaciente(paciente);

            // Set profissional
            Profissionalbean profissional = new Profissionalbean();
            profissional.setIdProfissional(rs.getInt("idProfissional"));
            profissional.setNome(rs.getString("nome_profissional"));
            aplicar.setProfissional(profissional);

            // Set vacina
            Vacinabean vacina = new Vacinabean();
            vacina.setIdVacina(rs.getInt("idVacina"));
            vacina.setNome(rs.getString("nome_vacina"));
            aplicar.setVacina(vacina);

            lista.add(aplicar);
        }
    } catch (SQLException ex) {
        System.err.println("Erro ao ler aplicações de vacina: " + ex);
    } finally {
        ConnectionFactory.closeConnection(con, stmt, rs);
    }

    return lista;
}
    public boolean update(AplicarVacinabean vacina) {
    PreparedStatement stmt = null;
    String sql = "UPDATE aplicacaovacina SET idProfissional = ?, idPaciente = ?, idVacina = ?, dataAplicacao = ?, local = ? WHERE idAplicavacina = ?";
    
    try {
        stmt = con.prepareStatement(sql);
        stmt.setInt(1, vacina.getIdprofissional());
        stmt.setInt(2, vacina.getIdpaciente());
        stmt.setInt(3, vacina.getIdvacina());
        stmt.setString(4, vacina.getDataaplicacao());
        stmt.setString(5, vacina.getLocal());
        stmt.setInt(6, vacina.getIdaplicavacina());

        stmt.executeUpdate();
        return true;
    } catch (SQLException ex) {
        System.err.println("Erro ao atualizar aplicação de vacina: " + ex);
        JOptionPane.showMessageDialog(null, "Erro ao atualizar aplicação", "Erro", JOptionPane.ERROR_MESSAGE);
        return false;
    } finally {
        ConnectionFactory.closeConnection(con, stmt);
    }
}


    public boolean delete(AplicarVacinabean vacina) {
        PreparedStatement stmt = null;
        String sql = "DELETE FROM aplicavacina WHERE idaplicavacina = ?";

        try {
            stmt = con.prepareStatement(sql);
            stmt.setInt(1, vacina.getIdaplicavacina());
            stmt.executeUpdate();
            return true;
        } catch (SQLException ex) {
            JOptionPane.showMessageDialog(null, "Erro ao excluir aplicação: " + ex.getMessage());
            return false;
        } finally {
            ConnectionFactory.closeConnection(con, stmt);
        }
    }
}

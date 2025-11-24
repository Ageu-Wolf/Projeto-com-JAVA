package model.DAO;

import connection.ConnectionFactory;
import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.SQLException;
import java.sql.ResultSet;
import java.util.ArrayList;
import javax.swing.JOptionPane;
import model.bean.Pacientebean;

public class PacienteDAO {

    private Connection con = null;

    public PacienteDAO() {
        con = ConnectionFactory.getConnection();
    }

    public boolean create(Pacientebean paciente) {
        PreparedStatement stmt = null;

        String sql = "INSERT INTO paciente ( nome, cpf, sexo, telefone, datanascimento, alergias) VALUES (?, ?, ?, ?, ?, ?)";
        try {
            stmt = con.prepareStatement(sql);
            stmt.setString(1, paciente.getNome());
            stmt.setString(2, paciente.getCpf());
            stmt.setString(3, paciente.getSexo());
            stmt.setString(4, paciente.getTelefone());
            stmt.setString(5, paciente.getDataNascimento());
            stmt.setString(6, paciente.getAlergias());

            stmt.executeUpdate();
            return true;
        } catch (SQLException ex) {
            System.err.println("Erro ao salvar paciente: " + ex);
            JOptionPane.showMessageDialog(null, "Erro ao salvar paciente", "Erro", JOptionPane.ERROR_MESSAGE);
            return false;
        } finally {
            ConnectionFactory.closeConnection(con, stmt);
        }
    }

    public boolean update(Pacientebean paciente) {
        PreparedStatement stmt = null;

        String sql = "UPDATE paciente SET nome = ?, cpf = ?, sexo = ?, telefone = ?, dataNascimento = ?, alergias = ? WHERE idPaciente = ?";
        try {
            stmt = con.prepareStatement(sql);
            stmt.setString(1, paciente.getNome());
            stmt.setString(2, paciente.getCpf());
            stmt.setString(3, paciente.getSexo());
            stmt.setString(4, paciente.getTelefone());
            stmt.setString(5, paciente.getDataNascimento());
            stmt.setString(6, paciente.getAlergias());
            stmt.setInt(7, paciente.getIdPaciente());

            stmt.executeUpdate();
            return true;
        } catch (SQLException ex) {
            System.err.println("Erro ao atualizar paciente: " + ex);
            JOptionPane.showMessageDialog(null, "Erro ao atualizar paciente", "Erro", JOptionPane.ERROR_MESSAGE);
            return false;
        } finally {
            ConnectionFactory.closeConnection(con, stmt);
        }
    }

    public boolean delete(Pacientebean paciente) {
        PreparedStatement stmt = null;

        String sql = "DELETE FROM paciente WHERE idPaciente = ?";
        try {
            stmt = con.prepareStatement(sql);
            stmt.setInt(1, paciente.getIdPaciente());
            stmt.executeUpdate();
            return true;
        } catch (SQLException ex) {
            System.err.println("Erro ao excluir paciente: " + ex);
            JOptionPane.showMessageDialog(null, "Erro ao excluir paciente", "Erro", JOptionPane.ERROR_MESSAGE);
            return false;
        } finally {
            ConnectionFactory.closeConnection(con, stmt);
        }
    }

    public ArrayList<Pacientebean> read() {
        PreparedStatement stmt = null;
        ResultSet rs = null;

        ArrayList<Pacientebean> listaPacientes = new ArrayList<>();
        String sql = "SELECT * FROM paciente ORDER BY idPaciente";
        try {
            stmt = con.prepareStatement(sql);
            rs = stmt.executeQuery();
            while (rs.next()) {
                Pacientebean paciente = new Pacientebean();
                paciente.setIdPaciente(rs.getInt("idPaciente"));
                paciente.setNome(rs.getString("nome"));
                paciente.setCpf(rs.getString("cpf"));
                paciente.setSexo(rs.getString("sexo"));
                paciente.setTelefone(rs.getString("telefone"));
                paciente.setDataNascimento(rs.getString("dataNascimento"));
                paciente.setAlergias(rs.getString("alergias"));
                listaPacientes.add(paciente);
            }
        } catch (SQLException ex) {
            System.err.println("Erro ao listar pacientes: " + ex);
            JOptionPane.showMessageDialog(null, "Erro ao listar pacientes", "Erro", JOptionPane.ERROR_MESSAGE);
        } finally {
            ConnectionFactory.closeConnection(con, stmt, rs);
        }
        return listaPacientes;
    }

    public ArrayList<Pacientebean> getListaPacientesPorNome(String nome) {
        PreparedStatement stmt = null;
        ResultSet rs = null;

        ArrayList<Pacientebean> listaPacientes = new ArrayList<>();

        try {
            stmt = con.prepareStatement("SELECT * FROM paciente WHERE nome ILIKE ? ORDER BY idPaciente");
            stmt.setString(1, "%" + nome + "%");
            rs = stmt.executeQuery();
            while (rs.next()) {
                Pacientebean paciente = new Pacientebean();
                paciente.setIdPaciente(rs.getInt("idPaciente"));
                paciente.setNome(rs.getString("nome"));
                paciente.setCpf(rs.getString("cpf"));
                paciente.setSexo(rs.getString("sexo"));
                paciente.setTelefone(rs.getString("telefone"));
                paciente.setDataNascimento(rs.getString("dataNascimento"));
                paciente.setAlergias(rs.getString("alergias"));
                listaPacientes.add(paciente);
            }
        } catch (SQLException ex) {
            System.err.println("Erro ao buscar pacientes por nome: " + ex);
            JOptionPane.showMessageDialog(null, "Erro ao buscar pacientes", "Erro", JOptionPane.ERROR_MESSAGE);
        } finally {
            ConnectionFactory.closeConnection(con, stmt, rs);
        }
        return listaPacientes;
    }
    public Pacientebean getById(int id) {
    Pacientebean paciente = null;
    PreparedStatement stmt = null;
    ResultSet rs = null;

    try {
        stmt = con.prepareStatement("SELECT * FROM paciente WHERE idPaciente = ?");
        stmt.setInt(1, id);
        rs = stmt.executeQuery();

        if (rs.next()) {
            paciente = new Pacientebean();
            paciente.setIdPaciente(rs.getInt("idPaciente"));
            paciente.setNome(rs.getString("nome"));
            paciente.setCpf(rs.getString("cpf"));
            paciente.setSexo(rs.getString("sexo"));
            paciente.setTelefone(rs.getString("telefone"));
            paciente.setDataNascimento(rs.getString("dataNascimento"));
            paciente.setAlergias(rs.getString("alergias"));
        }
    } catch (SQLException ex) {
        System.err.println("Erro ao buscar paciente por ID: " + ex);
    } finally {
        ConnectionFactory.closeConnection(con, stmt, rs);
    }
    return paciente;
}
    public ArrayList<Pacientebean> getListaPacientesPorCpf(String cpf) {
    ArrayList<Pacientebean> lista = new ArrayList<>();
    String sql = "SELECT * FROM paciente WHERE cpf ILIKE ? ORDER BY idPaciente";

    try (PreparedStatement stmt = con.prepareStatement(sql)) {
        stmt.setString(1, "%" + cpf + "%");
        ResultSet rs = stmt.executeQuery();
        while (rs.next()) {
            Pacientebean paciente = new Pacientebean();
            paciente.setIdPaciente(rs.getInt("idPaciente"));
            paciente.setNome(rs.getString("nome"));
            paciente.setCpf(rs.getString("cpf"));
            paciente.setSexo(rs.getString("sexo"));
            paciente.setTelefone(rs.getString("telefone"));
            paciente.setDataNascimento(rs.getString("dataNascimento"));
            paciente.setAlergias(rs.getString("alergias"));
            lista.add(paciente);
        }
    } catch (SQLException ex) {
        System.err.println("Erro ao buscar por CPF: " + ex);
    }

    return lista;
}

public ArrayList<Pacientebean> getListaPacientesPorSexo(String sexo) {
    ArrayList<Pacientebean> lista = new ArrayList<>();
    String sql = "SELECT * FROM paciente WHERE sexo ILIKE ? ORDER BY idPaciente";

    try (PreparedStatement stmt = con.prepareStatement(sql)) {
        stmt.setString(1, "%" + sexo + "%");
        ResultSet rs = stmt.executeQuery();
        while (rs.next()) {
            Pacientebean paciente = new Pacientebean();
            paciente.setIdPaciente(rs.getInt("idPaciente"));
            paciente.setNome(rs.getString("nome"));
            paciente.setCpf(rs.getString("cpf"));
            paciente.setSexo(rs.getString("sexo"));
            paciente.setTelefone(rs.getString("telefone"));
            paciente.setDataNascimento(rs.getString("dataNascimento"));
            paciente.setAlergias(rs.getString("alergias"));
            lista.add(paciente);
        }
    } catch (SQLException ex) {
        System.err.println("Erro ao buscar por sexo: " + ex);
    }

    return lista;
}

}

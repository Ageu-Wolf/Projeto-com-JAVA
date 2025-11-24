package controller;

import java.util.ArrayList;
import model.DAO.PacienteDAO;
import model.bean.Pacientebean;

public class PacienteController {

    public boolean create(String nome, String cpf, String sexo, String telefone, String dataNascimento, String alergias) {
        Pacientebean paciente = new Pacientebean();
        paciente.setNome(nome);
        paciente.setCpf(cpf);
        paciente.setSexo(sexo);
        paciente.setTelefone(telefone);
        paciente.setDataNascimento(dataNascimento);
        paciente.setAlergias(alergias);

        PacienteDAO pacienteDAO = new PacienteDAO();
        return pacienteDAO.create(paciente);
    }

    public ArrayList<Pacientebean> read() {
        PacienteDAO pacienteDAO = new PacienteDAO();
        return pacienteDAO.read();
    }

    public boolean update(int idPaciente, String nome, String cpf, String sexo, String telefone, String dataNascimento, String alergias) {
        Pacientebean paciente = new Pacientebean();
        paciente.setIdPaciente(idPaciente);
        paciente.setNome(nome);
        paciente.setCpf(cpf);
        paciente.setSexo(sexo);
        paciente.setTelefone(telefone);
        paciente.setDataNascimento(dataNascimento);
        paciente.setAlergias(alergias);

        PacienteDAO pacienteDAO = new PacienteDAO();
        return pacienteDAO.update(paciente);
    }

    public boolean delete(int idPaciente) {
        Pacientebean paciente = new Pacientebean();
        paciente.setIdPaciente(idPaciente);

        PacienteDAO pacienteDAO = new PacienteDAO();
        return pacienteDAO.delete(paciente);
    }

    public ArrayList<Pacientebean> getListaPacienteporNome(String nome) {
        PacienteDAO pacienteDAO = new PacienteDAO();
        return pacienteDAO.getListaPacientesPorNome(nome);
    }
    public ArrayList<Pacientebean> getListaPacienteporCpf(String cpf) {
    PacienteDAO dao = new PacienteDAO();
    return dao.getListaPacientesPorCpf(cpf);
}

public ArrayList<Pacientebean> getListaPacienteporSexo(String sexo) {
    PacienteDAO dao = new PacienteDAO();
    return dao.getListaPacientesPorSexo(sexo);
}
}

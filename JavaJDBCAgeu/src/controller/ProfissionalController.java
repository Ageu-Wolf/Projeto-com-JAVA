package controller;

import java.util.ArrayList;
import model.bean.Profissionalbean;
import model.dao.ProfissionalDAO;

public class ProfissionalController {

    public boolean create(String registroProfissional, String nome, String cpf) {
        Profissionalbean profissional = new Profissionalbean();
        profissional.setRegistroprofissional(registroProfissional);
        profissional.setNome(nome);
        profissional.setCpf(cpf);

        ProfissionalDAO profissionalDAO = new ProfissionalDAO();
        return profissionalDAO.create(profissional);
    }

    public ArrayList<Profissionalbean> read() {
        ProfissionalDAO profissionalDAO = new ProfissionalDAO();
        return profissionalDAO.read();
    }

    public boolean update(int idProfissional, String registroProfissional, String nome, String cpf) {
        Profissionalbean profissional = new Profissionalbean();
        profissional.setIdProfissional(idProfissional);
        profissional.setRegistroprofissional(registroProfissional);
        profissional.setNome(nome);
        profissional.setCpf(cpf);

        ProfissionalDAO profissionalDAO = new ProfissionalDAO();
        return profissionalDAO.update(profissional);
    }

    public boolean delete(int idProfissional) {
        Profissionalbean profissional = new Profissionalbean();
        profissional.setIdProfissional(idProfissional);

        ProfissionalDAO profissionalDAO = new ProfissionalDAO();
        return profissionalDAO.delete(profissional);
    }

    // Método alternativo para pesquisa — se você for usar por "descricao", atualize o DAO corretamente
    public ArrayList<Profissionalbean> getListaProfissionalPorDescricao(String descricao) {
        ProfissionalDAO profissionalDAO = new ProfissionalDAO();
        return profissionalDAO.getListaProdutoporDescricao(descricao);
    }
}

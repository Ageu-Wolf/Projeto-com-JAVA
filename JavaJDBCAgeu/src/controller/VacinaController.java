package controller;

import java.util.ArrayList;
import model.DAO.VacinaDAO;
import model.bean.Vacinabean;

public class VacinaController {

    public boolean create(String nome, String fabricante, int doses) {
        Vacinabean vacina = new Vacinabean();
        vacina.setNome(nome);
        vacina.setFabricante(fabricante);
        vacina.setDosesNecessarias(doses);

        VacinaDAO dao = new VacinaDAO();
        return dao.create(vacina);
    }

    public ArrayList<Vacinabean> read() {
        VacinaDAO dao = new VacinaDAO();
        return dao.read();
    }

    public boolean update(int id, String nome, String fabricante, int doses) {
        Vacinabean vacina = new Vacinabean();
        vacina.setIdVacina(id);
        vacina.setNome(nome);
        vacina.setFabricante(fabricante);
        vacina.setDosesNecessarias(doses);

        VacinaDAO dao = new VacinaDAO();
        return dao.update(vacina);
    }

    public boolean delete(int id) {
        Vacinabean vacina = new Vacinabean();
        vacina.setIdVacina(id);

        VacinaDAO dao = new VacinaDAO();
        return dao.delete(vacina);
    }
}

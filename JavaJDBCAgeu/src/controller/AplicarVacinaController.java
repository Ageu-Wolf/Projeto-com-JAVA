package controller;

import java.util.ArrayList;
import model.DAO.AplicarVacinaDAO;
import model.bean.AplicarVacinabean;

public class AplicarVacinaController {

    public boolean create(int idProfissional, int idPaciente, int idVacina, String dataAplicacao, String local) {
        AplicarVacinabean vacina = new AplicarVacinabean();
        vacina.setIdprofissional(idProfissional);
        vacina.setIdpaciente(idPaciente);
        vacina.setIdvacina(idVacina);
        vacina.setDataaplicacao(dataAplicacao);
        vacina.setLocal(local);

        AplicarVacinaDAO dao = new AplicarVacinaDAO();
        return dao.create(vacina);
    }

    public ArrayList<AplicarVacinabean> read() {
        AplicarVacinaDAO dao = new AplicarVacinaDAO();
        return dao.read();
    }
        public boolean update(int idAplicacao, int idProfissional, int idPaciente, int idVacina, String dataAplicacao, String local) {
        AplicarVacinabean vacina = new AplicarVacinabean();
        vacina.setIdaplicavacina(idAplicacao);
        vacina.setIdprofissional(idProfissional);
        vacina.setIdpaciente(idPaciente);
        vacina.setIdvacina(idVacina);
        vacina.setDataaplicacao(dataAplicacao);
        vacina.setLocal(local);

        AplicarVacinaDAO dao = new AplicarVacinaDAO();
        return dao.update(vacina);
    }

    public boolean delete(int idAplicacao) {
        AplicarVacinabean vacina = new AplicarVacinabean();
        vacina.setIdaplicavacina(idAplicacao);

        AplicarVacinaDAO dao = new AplicarVacinaDAO();
        return dao.delete(vacina);
    }
}

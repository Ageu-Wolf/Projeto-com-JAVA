package tablemodel;

import java.util.ArrayList;
import java.util.List;
import javax.swing.table.AbstractTableModel;
import model.bean.AplicarVacinabean;
import model.bean.Pacientebean;
import model.bean.Profissionalbean;
import model.bean.Vacinabean;

public class AplicarVacinaTableModel extends AbstractTableModel {

    private List<AplicarVacinabean> listaAplicacoes;
    private final String[] colunas = {"Paciente", "Profissional", "Vacina", "Data Aplicação", "Local"};

    public AplicarVacinaTableModel() {
        listaAplicacoes = new ArrayList<>();
    }

    public AplicarVacinaTableModel(List<AplicarVacinabean> aplicacoes) {
        this();
        this.listaAplicacoes.addAll(aplicacoes);
    }

    @Override
    public int getRowCount() {
        return listaAplicacoes.size();
    }

    @Override
    public int getColumnCount() {
        return colunas.length;
    }

    @Override
    public Object getValueAt(int linha, int coluna) {
        AplicarVacinabean a = listaAplicacoes.get(linha);

        Pacientebean paciente = a.getPaciente();
        Profissionalbean profissional = a.getProfissional();
        Vacinabean vacina = a.getVacina();

        switch (coluna) {
            case 0: return (paciente != null) ? paciente.getNome() : "N/D";
            case 1: return (profissional != null) ? profissional.getNome() : "N/D";
            case 2: return (vacina != null) ? vacina.getNome() : "N/D";
            case 3: return a.getDataaplicacao();
            case 4: return a.getLocal();
            default: return "";
        }
    }

    @Override
    public String getColumnName(int coluna) {
        return colunas[coluna];
    }

    public AplicarVacinabean getAplicacao(int linha) {
        if (linha >= listaAplicacoes.size()) return null;
        return listaAplicacoes.get(linha);
    }
}

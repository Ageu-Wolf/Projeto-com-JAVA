package tablemodel;

import java.util.ArrayList;
import java.util.List;
import javax.swing.table.AbstractTableModel;
import model.bean.Vacinabean;

public class VacinaTableModel extends AbstractTableModel {

    private List<Vacinabean> listaVacinas;
    private final String[] colunas = {"Nome", "Fabricante", "Doses Necessárias"};

    public VacinaTableModel() {
        listaVacinas = new ArrayList<>();
    }

    public VacinaTableModel(List<Vacinabean> vacinas) {
        this();
        this.listaVacinas.addAll(vacinas);
    }

    @Override
    public int getRowCount() {
        return listaVacinas.size();
    }

    @Override
    public int getColumnCount() {
        return colunas.length;
    }

    @Override
    public Object getValueAt(int linha, int coluna) {
        Vacinabean v = listaVacinas.get(linha);
        switch (coluna) {
            case 0: return v.getNome();
            case 1: return v.getFabricante();
            case 2: return v.getDosesNecessarias();
            default: return "";
        }
    }

    @Override
    public String getColumnName(int coluna) {
        return colunas[coluna];
    }

    public Vacinabean getVacina(int linha) {
        if (linha >= listaVacinas.size()) return null;
        return listaVacinas.get(linha);
    }
}

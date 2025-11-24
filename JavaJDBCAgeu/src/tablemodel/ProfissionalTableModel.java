package tablemodel;

import java.util.ArrayList;
import java.util.List;
import javax.swing.table.AbstractTableModel;
import model.bean.Profissionalbean;

public class ProfissionalTableModel extends AbstractTableModel {

    private List<Profissionalbean> listaProfissionais;
    private String[] colunas = {"Nome", "Registro", "CPF"};

    public ProfissionalTableModel() {
        listaProfissionais = new ArrayList<>();
    }

    public ProfissionalTableModel(List<Profissionalbean> profissionais) {
        this();
        this.listaProfissionais.addAll(profissionais);
    }

    @Override
    public int getRowCount() {
        return listaProfissionais.size();
    }

    @Override
    public int getColumnCount() {
        return colunas.length;
    }

    @Override
    public Object getValueAt(int linha, int coluna) {
        Profissionalbean profissional = listaProfissionais.get(linha);
        switch (coluna) {
            case 0: 
                return profissional.getNome();
            case 1:
                return profissional.getRegistroprofissional(); // ← P maiúsculo
            case 2:
                return profissional.getCpf();
            default:
                return "";
        }
    }

    @Override
    public String getColumnName(int coluna) {
        return colunas[coluna];
    }

    public Profissionalbean getProfissional(int linha) {
        if (linha >= listaProfissionais.size()) {
            return null;
        }
        return listaProfissionais.get(linha);
    }
}

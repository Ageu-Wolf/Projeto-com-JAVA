package tablemodel;

import java.util.ArrayList;
import java.util.List;
import javax.swing.table.AbstractTableModel;
import model.bean.Pacientebean;

public class PacienteTableModel extends AbstractTableModel {

    private List<Pacientebean> listaPacientes;
    private String[] colunas = {"Nome", "Telefone", "Data de Nascimento", "Alergias", "Sexo","CPF"};

    public PacienteTableModel() {
        listaPacientes = new ArrayList<>();
    }

    public PacienteTableModel(List<Pacientebean> pacientes) {
        this();
        this.listaPacientes.addAll(pacientes);
    }

    @Override
    public int getRowCount() {
        return listaPacientes.size();
    }

    @Override
    public int getColumnCount() {
        return colunas.length;
    }

    @Override
    public Object getValueAt(int linha, int coluna) {
        Pacientebean p = listaPacientes.get(linha);
        switch (coluna) {
            case 0: return p.getNome();
            case 1: return p.getTelefone();
            case 2: return p.getDataNascimento();
            case 3: return p.getAlergias();
            case 4: return p.getSexo();
            case 5: return p.getCpf();
            default: return "";
        }
    }

    @Override
    public String getColumnName(int column) {
        return colunas[column];
    }

    public Pacientebean getPaciente(int linha) {
        if (linha >= listaPacientes.size()) return null;
        return listaPacientes.get(linha);
    }
}

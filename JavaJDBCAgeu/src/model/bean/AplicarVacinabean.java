package model.bean;

public class AplicarVacinabean {
    private int idaplicavacina;
    private int idprofissional;
    private int idpaciente;
    private int idvacina;
    private String dataaplicacao;
    private String local;
    private Pacientebean paciente;
    private Profissionalbean profissional;
    private Vacinabean vacina;
    
    public Pacientebean getPaciente() { return paciente; }
    public void setPaciente(Pacientebean paciente) { this.paciente = paciente; }

    public Profissionalbean getProfissional() { return profissional; }
    public void setProfissional(Profissionalbean profissional) { this.profissional = profissional; }

    public Vacinabean getVacina() { return vacina; }
    public void setVacina(Vacinabean vacina) { this.vacina = vacina; }

    public int getIdaplicavacina() {
        return idaplicavacina;
    }
    public void setIdaplicavacina(int idaplicavacina) {
        this.idaplicavacina = idaplicavacina;
    }

    public int getIdprofissional() {
        return idprofissional;
    }

    public void setIdprofissional(int idprofissional) {
        this.idprofissional = idprofissional;
    }

    public int getIdpaciente() {
        return idpaciente;
    }

    public void setIdpaciente(int idpaciente) {
        this.idpaciente = idpaciente;
    }

    public int getIdvacina() {
        return idvacina;
    }

    public void setIdvacina(int idvacina) {
        this.idvacina = idvacina;
    }

    public String getDataaplicacao() {
        return dataaplicacao;
    }

    public void setDataaplicacao(String dataaplicacao) {
        this.dataaplicacao = dataaplicacao;
    }

    public String getLocal() {
        return local;
    }

    public void setLocal(String local) {
        this.local = local;
    }

}

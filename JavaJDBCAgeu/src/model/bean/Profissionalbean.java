/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package model.bean;

/**
 *
 * @author aluno
 */
public class Profissionalbean {
    private int idProfissional;
    private String nome;
    private String registroprofissional;
    private String cpf;

    public int getIdProfissional() {
        return idProfissional;
    }

    public void setIdProfissional(int idProfissional) {
        this.idProfissional = idProfissional;
    }

    public String getNome() {
        return nome;
    }

    public void setNome(String nome) {
        this.nome = nome;
    }

    public String getRegistroprofissional() {
        return registroprofissional;
    }

    public void setRegistroprofissional(String registroprofissional) {
        this.registroprofissional = registroprofissional;
    }

    public String getCpf() {
        return cpf;
    }

    public void setCpf(String cpf) {
        this.cpf = cpf;
    }
@Override
public String toString() {
    return nome;
}

}

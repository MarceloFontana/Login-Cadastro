package model;

import java.sql.SQLException;

public class Usuario {
    private int idUsuario;
    private String nome;
    private String sobrenome;
    private String email;
    private String senha;
    private String genero;
    private String celular;

    public Usuario(String nome, String sobrenome, String email, String senha, String genero, String celular) {
        this.nome = nome;
        this.sobrenome = sobrenome;
        this.email = email;
        this.senha = senha;
        this.genero = genero;
        this.celular = celular;
    }
    
    public Usuario(){}

    public int getIdUsuario() {
        return idUsuario;
    }

    public void setIdUsuario(int idUsuario) {
        this.idUsuario = idUsuario;
    }

    public String getNome() {
        return nome;
    }

    public void setNome(String nome) {
        this.nome = nome;
    }

    public String getSobrenome() {
        return sobrenome;
    }

    public void setSobrenome(String sobrenome) {
        this.sobrenome = sobrenome;
    }

    public String getEmail() {
        return email;
    }

    public void setEmail(String email) {
        this.email = email;
    }

    public String getSenha() {
        return senha;
    }

    public void setSenha(String senha) {
        this.senha = senha;
    }

    public String getGenero() {
        return genero;
    }

    public void setGenero(String genero) {
        this.genero = genero;
    }
    public String getCelular(){
        return celular;
    }
    public void setCelular(String celular){
        this.celular = celular;
    }
    
    //Métodos gerais
    public boolean isLogged() throws SQLException, ClassNotFoundException {
       ProprietarioDAO udao = new ProprietarioDAO();
        Usuario uBanco = udao.listByName(this.nome);
        
        if(uBanco.getNome()!= null){
            return (uBanco.getSenha().equals(this.senha));
        } else {
            return false;
        }
        
    }
}

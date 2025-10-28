package br.com.fiap.to;

import java.time.LocalDate;

public class UsuarioTO {
    private Long idUser;
    private String cpf;
    private String nome;
    private String senha;
    private String email;
    private String telefone;
    private LocalDate dataNascimento;
    private String tipoUsuario;

    public UsuarioTO() {
    }

    public UsuarioTO(Long idUser, String cpf, String nome, String senha, String email, String telefone, LocalDate dataNascimento, String tipoUsuario) {
        this.idUser = idUser;
        this.cpf = cpf;
        this.nome = nome;
        this.senha = senha;
        this.email = email;
        this.telefone = telefone;
        this.dataNascimento = dataNascimento;
        this.tipoUsuario = tipoUsuario;
    }

    public Long getIdUser() {
        return idUser;
    }

    public void setIdUser(Long idUser) {
        this.idUser = idUser;
    }

    public String getCpf() {
        return cpf;
    }

    public void setCpf(String cpf) {
        this.cpf = cpf;
    }

    public String getNome() {
        return nome;
    }

    public void setNome(String nome) {
        this.nome = nome;
    }

    public String getSenha() {
        return senha;
    }

    public void setSenha(String senha) {
        this.senha = senha;
    }

    public String getEmail() {
        return email;
    }

    public void setEmail(String email) {
        this.email = email;
    }

    public String getTelefone() {
        return telefone;
    }

    public void setTelefone(String telefone) {
        this.telefone = telefone;
    }

    public LocalDate getDataNascimento() {
        return dataNascimento;
    }

    public void setDataNascimento(LocalDate dataNascimento) {
        this.dataNascimento = dataNascimento;
    }

    public String getTipoUsuario() {
        return tipoUsuario;
    }

    public void setTipoUsuario(String tipoUsuario) {
        this.tipoUsuario = tipoUsuario;
    }
}

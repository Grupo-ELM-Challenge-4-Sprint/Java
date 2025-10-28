package br.com.fiap.to;


import java.time.LocalDate;
import java.time.LocalDateTime;

public class ConsultaTO {
    private Long idConsulta;
    private String especialidade;
    private String nomeCuidador;
    private LocalDate data;
    private LocalDateTime hora;
    private String tipo;
    private String local;
    private String observacoes;
    private String status;
    private Long idUser;

    public ConsultaTO() {
    }

    public ConsultaTO(Long idConsulta, String nomeCuidador, String especialidade, LocalDate data, LocalDateTime hora, String tipo, String local, String observacoes, String status, Long idUser) {
        this.idConsulta = idConsulta;
        this.nomeCuidador = nomeCuidador;
        this.especialidade = especialidade;
        this.data = data;
        this.hora = hora;
        this.tipo = tipo;
        this.local = local;
        this.observacoes = observacoes;
        this.status = status;
        this.idUser = idUser;
    }

    public Long getIdConsulta() {
        return idConsulta;
    }

    public void setIdConsulta(Long idConsulta) {
        this.idConsulta = idConsulta;
    }

    public Long getIdUser() {
        return idUser;
    }

    public void setIdUser(Long idUser) {
        this.idUser = idUser;
    }

    public String getNomeCuidador() {
        return nomeCuidador;
    }

    public void setNomeCuidador(String nomeCuidador) {
        this.nomeCuidador = nomeCuidador;
    }

    public String getEspecialidade() {
        return especialidade;
    }

    public void setEspecialidade(String especialidade) {
        this.especialidade = especialidade;
    }

    public LocalDate getData() {
        return data;
    }

    public void setData(LocalDate data) {
        this.data = data;
    }

    public LocalDateTime getHora() { return hora; }

    public void setHora(LocalDateTime hora) {
        this.hora = hora;
    }

    public String getTipo() {
        return tipo;
    }

    public void setTipo(String tipo) {
        this.tipo = tipo;
    }

    public String getLocal() {
        return local;
    }

    public void setLocal(String local) {
        this.local = local;
    }

    public String getObservacoes() {
        return observacoes;
    }

    public void setObservacoes(String observacoes) {
        this.observacoes = observacoes;
    }

    public String getStatus() {
        return status;
    }

    public void setStatus(String status) {
        this.status = status;
    }
}

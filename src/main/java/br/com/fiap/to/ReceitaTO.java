package br.com.fiap.to;


import java.time.LocalDate;
import java.time.LocalDateTime;

public class ReceitaTO {
    private Long idReceita;
    private String nome;
    private String frequencia;
    private String[] dias;
    private Long numeroDias;
    private LocalDate dataInicio;
    private LocalDateTime horaInicio;
    private String observacoes;
    private String status;
    private Long idUser;

    public ReceitaTO() {
    }

    public ReceitaTO(Long idReceita, Long idUser, String nome, String frequencia, String[] dias, Long numeroDias, LocalDate dataInicio, LocalDateTime horaInicio, String observacoes, String status) {
        this.idReceita = idReceita;
        this.idUser = idUser;
        this.nome = nome;
        this.frequencia = frequencia;
        this.dias = dias;
        this.numeroDias = numeroDias;
        this.dataInicio = dataInicio;
        this.horaInicio = horaInicio;
        this.observacoes = observacoes;
        this.status = status;
    }

    public Long getIdReceita() {
        return idReceita;
    }

    public void setIdReceita(Long idReceita) {
        this.idReceita = idReceita;
    }

    public LocalDate getDataInicio() {
        return dataInicio;
    }

    public void setDataInicio(LocalDate dataInicio) {
        this.dataInicio = dataInicio;
    }

    public LocalDateTime getHoraInicio() {
        return horaInicio;
    }

    public void setHoraInicio(LocalDateTime horaInicio) {
        this.horaInicio = horaInicio;
    }

    public Long getIdUser() {
        return idUser;
    }

    public void setIdUser(Long idUser) {
        this.idUser = idUser;
    }

    public String getNome() {
        return nome;
    }

    public void setNome(String nome) {
        this.nome = nome;
    }

    public String getFrequencia() {
        return frequencia;
    }

    public void setFrequencia(String frequencia) {
        this.frequencia = frequencia;
    }

    public String[] getDias() {
        return dias;
    }

    public void setDias(String[] dias) {
        this.dias = dias;
    }

    public Long getNumeroDias() {
        return numeroDias;
    }

    public void setNumeroDias(Long numeroDias) {
        this.numeroDias = numeroDias;
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

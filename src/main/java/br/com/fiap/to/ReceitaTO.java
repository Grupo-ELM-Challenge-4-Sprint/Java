package br.com.fiap.to;

import java.time.LocalDate;
import java.time.LocalDateTime;

public class ReceitaTO {
    private Long idReceita;
    private String nomeMedicamento;
    private int frequenciaHoras;
    private String[] dias;
    private Long numeroDiasTratamento;
    private LocalDate dataInicio;
    private String horaInicio;
    private String observacoes;
    private String status;
    private Long idUser;

    public ReceitaTO() {
    }

    public ReceitaTO(Long idReceita, String nomeMedicamento, int frequenciaHoras, String[] dias, Long numeroDiasTratamento, LocalDate dataInicio, String horaInicio, String observacoes, String status, Long idUser) {
        this.idReceita = idReceita;
        this.nomeMedicamento = nomeMedicamento;
        this.frequenciaHoras = frequenciaHoras;
        this.dias = dias;
        this.numeroDiasTratamento = numeroDiasTratamento;
        this.dataInicio = dataInicio;
        this.horaInicio = horaInicio;
        this.observacoes = observacoes;
        this.status = status;
        this.idUser = idUser;
    }

    // Getters e Setters

    public Long getIdReceita() { return idReceita; }
    public void setIdReceita(Long idReceita) { this.idReceita = idReceita; }

    public String getNomeMedicamento() { return nomeMedicamento; }
    public void setNomeMedicamento(String nomeMedicamento) { this.nomeMedicamento = nomeMedicamento; }

    public int getFrequenciaHoras() { return frequenciaHoras; } // Tipo ajustado
    public void setFrequenciaHoras(int frequenciaHoras) { this.frequenciaHoras = frequenciaHoras; } // Tipo ajustado

    public String[] getDias() { return dias; }
    public void setDias(String[] dias) { this.dias = dias; }

    public Long getNumeroDiasTratamento() { return numeroDiasTratamento; }
    public void setNumeroDiasTratamento(Long numeroDiasTratamento) { this.numeroDiasTratamento = numeroDiasTratamento; }

    public LocalDate getDataInicio() { return dataInicio; }
    public void setDataInicio(LocalDate dataInicio) { this.dataInicio = dataInicio; }

    public String getHoraInicio() { return horaInicio; } // Tipo ajustado
    public void setHoraInicio(String horaInicio) { this.horaInicio = horaInicio; }

    public String getObservacoes() { return observacoes; }
    public void setObservacoes(String observacoes) { this.observacoes = observacoes; }

    public String getStatus() { return status; }
    public void setStatus(String status) { this.status = status; }

    public Long getIdUser() { return idUser; }
    public void setIdUser(Long idUser) { this.idUser = idUser; }
}
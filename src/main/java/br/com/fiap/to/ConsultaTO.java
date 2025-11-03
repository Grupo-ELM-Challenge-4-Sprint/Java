package br.com.fiap.to;

import jakarta.validation.constraints.NotBlank;
import jakarta.validation.constraints.NotNull;
import jakarta.validation.constraints.Pattern;
import java.time.LocalDate;
import java.time.LocalDateTime;

/**
 * Representa uma consulta médica no sistema.
 *
 * <p>Esta classe contém informações sobre o tipo de consulta, especialidade, cuidador responsável,
 * data, hora, local, observações e status da consulta.</p>
 *
 * @author Lucas Barros Gouveia
 * @author Enzo Okuizumi Miranda de Souza
 * @author Milton Jakson de Souza Marcelino
 * @version 1.0
 * @since 21.0.7
 */
public class ConsultaTO {

    /** Identificador único da consulta. */
    @NotNull
    private Long idConsulta;

    /** Especialidade médica ou tipo de atendimento. */
    @NotBlank
    private String especialidade;

    /** Nome completo do cuidador ou profissional de saúde responsável pela consulta. */
    @NotBlank
    @Pattern(regexp = "^[A-Za-zÀ-ÖØ-öø-ÿ]+(?:[\\s'-][A-Za-zÀ-ÖØ-öø-ÿ]+)*$")
    private String nomeCuidador;

    /** Data em que a consulta está agendada. */
    @NotNull
    private LocalDate data;

    /** Horário em que a consulta está marcada. */
    @NotNull
    private LocalDateTime hora;

    /** Tipo de consulta (Presencial ou online"). */
    @NotBlank
    private String tipo;

    /** Local onde a consulta ocorrerá. */
    @NotBlank
    private String local;

    /** Observações adicionais sobre a consulta. */
    @NotBlank
    private String observacoes;

    /**
     * Status da consulta, que pode representar o estado atual do agendamento.
     */
    @NotBlank
    private String status;

    /** Identificador do usuário associado a esta consulta. */
    @NotNull
    private Long idUser;

    /**
     * Construtor padrão da classe {@code ConsultaTO}.
     */
    public ConsultaTO() {
    }

    /**
     * Construtor completo para inicializar todos os atributos de uma consulta.
     *
     * @param idConsulta   identificador único da consulta
     * @param nomeCuidador nome do cuidador ou profissional responsável
     * @param especialidade especialidade médica
     * @param data         data da consulta
     * @param hora         horário da consulta
     * @param tipo         tipo de consulta (ex: "Presencial" ou "Online")
     * @param local        local de realização da consulta
     * @param observacoes  observações adicionais sobre a consulta
     * @param status       status atual da consulta
     * @param idUser       identificador do usuário vinculado
     */
    public ConsultaTO(Long idConsulta, String nomeCuidador, String especialidade, LocalDate data,
                      LocalDateTime hora, String tipo, String local, String observacoes,
                      String status, Long idUser) {
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

    /** @return o identificador único da consulta */
    public Long getIdConsulta() {
        return idConsulta;
    }

    /**
     * Define o identificador da consulta.
     * @param idConsulta o ID da consulta
     */
    public void setIdConsulta(Long idConsulta) {
        this.idConsulta = idConsulta;
    }

    /** @return o identificador do usuário vinculado à consulta */
    public Long getIdUser() {
        return idUser;
    }

    /**
     * Define o identificador do usuário vinculado à consulta.
     * @param idUser o ID do usuário
     */
    public void setIdUser(Long idUser) {
        this.idUser = idUser;
    }

    /** @return o nome do cuidador responsável */
    public String getNomeCuidador() {
        return nomeCuidador;
    }

    /**
     * Define o nome do cuidador responsável.
     * @param nomeCuidador o nome completo do cuidador
     */
    public void setNomeCuidador(String nomeCuidador) {
        this.nomeCuidador = nomeCuidador;
    }

    /** @return a especialidade médica da consulta */
    public String getEspecialidade() {
        return especialidade;
    }

    /**
     * Define a especialidade médica da consulta.
     * @param especialidade a especialidade
     */
    public void setEspecialidade(String especialidade) {
        this.especialidade = especialidade;
    }

    /** @return a data em que a consulta está agendada */
    public LocalDate getData() {
        return data;
    }

    /**
     * Define a data da consulta.
     * @param data a data do agendamento
     */
    public void setData(LocalDate data) {
        this.data = data;
    }

    /** @return o horário da consulta */
    public LocalDateTime getHora() {
        return hora;
    }

    /**
     * Define o horário da consulta.
     * @param hora o horário agendado
     */
    public void setHora(LocalDateTime hora) {
        this.hora = hora;
    }

    /** @return o tipo da consulta (ex: "Presencial" ou "Online") */
    public String getTipo() {
        return tipo;
    }

    /**
     * Define o tipo da consulta.
     * @param tipo o tipo da consulta (ex: "Presencial" ou "Online")
     */
    public void setTipo(String tipo) {
        this.tipo = tipo;
    }

    /** @return o local onde a consulta ocorrerá */
    public String getLocal() {
        return local;
    }

    /**
     * Define o local onde a consulta ocorrerá.
     * @param local o local da consulta
     */
    public void setLocal(String local) {
        this.local = local;
    }

    /** @return as observações adicionais da consulta */
    public String getObservacoes() {
        return observacoes;
    }

    /**
     * Define observações adicionais sobre a consulta.
     * @param observacoes o texto com as observações
     */
    public void setObservacoes(String observacoes) {
        this.observacoes = observacoes;
    }

    /** @return o status atual da consulta */
    public String getStatus() {
        return status;
    }

    /**
     * Define o status atual da consulta.
     * @param status o status da consulta
     */
    public void setStatus(String status) {
        this.status = status;
    }
}

package br.com.fiap.to;


import jakarta.validation.constraints.*;

import java.time.LocalDate;
import java.time.LocalDateTime;

/**
 * Representa uma receita médica cadastrada no sistema, associada a um {@code UsuarioTO}.
 *
 * <p>Esta classe contém informações sobre o medicamento, frequência de uso, dias de aplicação,
 * duração do tratamento, datas e horários de início, além do status e observações gerais.</p>
 *
 * <p>As anotações de validação garantem consistência e integridade dos dados de entrada,
 * como campos obrigatórios, padrões de texto e valores numéricos válidos.</p>
 *
 * @author Lucas Barros Gouveia
 * @author Enzo Okuizumi Miranda de Souza
 * @author Milton Jakson de Souza Marcelino
 * @version 1.0
 * @since 21.0.7
 */
public class ReceitaTO {
    /** Identificador único da receita. */
    @NotNull
    private Long idReceita;

    /** Nome do medicamento ou tratamento. Pode conter unidades como mg, g, ml ou UI. */
    @NotBlank
    @Pattern(regexp = "^[A-Za-zÀ-ÖØ-öø-ÿ0-9\\s\\-/()]+(?:mg|g|ml|UI)?$")
    private String nome;

    /** Frequência do consumo do medicamento. */
    @NotBlank
    private String frequencia;

    /**
     * Dias da semana em que o medicamento deve ser consumido.
     * <p>Deve conter pelo menos um dia.</p>
     */
    @NotNull
    @Size(min = 1)
    private String[] dias;

    /** Número total de dias de duração da receita. Deve ser zero ou positivo. */
    @NotNull
    @PositiveOrZero
    private Long numeroDias;

    /** Data de início do tratamento. */
    @NotNull
    private LocalDate dataInicio;

    /** Hora inicial em que a receita foi prescrita. */
    @NotBlank
    private String horaInicio;

    /** Observações adicionais sobre a receita, como instruções especiais ou restrições. */
    @NotBlank
    private String observacoes;

    /**
     * Status da receita, podendo ser:
     *<strong>ativo</strong> — receita em uso
     *<strong>inativo</strong> — receita encerrada ou descontinuada
     */
    @NotBlank
    @Pattern(regexp = "^(?i)(ativo|inativo)$")
    private String status;

    /** Identificador do usuário (paciente) ao qual a receita pertence. */
    @NotNull
    private Long idUser;

    /**
     * Construtor padrão da classe {@code ReceitaTO}.
     */
    public ReceitaTO() {
    }

    /**
     * Construtor completo.
     *
     * @param idReceita   identificador único da receita
     * @param idUser      identificador do usuário vinculado
     * @param nome        nome do medicamento
     * @param frequencia  frequência de consumo
     * @param dias        dias da semana em que o remedio é consumido
     * @param numeroDias  quantidade de dias de tratamento
     * @param dataInicio  data de início da receita
     * @param horaInicio  horário de início da receita
     * @param observacoes observações gerais sobre a receita
     * @param status      status da receita ("ativo" ou "inativo")
     */
    public ReceitaTO(Long idReceita, Long idUser, String nome, String frequencia, String[] dias, Long numeroDias, LocalDate dataInicio, String horaInicio, String observacoes, String status) {
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

    /** @return o identificador único da receita */
    public Long getIdReceita() {
        return idReceita;
    }

    /**
     * Define o identificador da receita.
     * @param idReceita o ID da receita
     */
    public void setIdReceita(Long idReceita) {
        this.idReceita = idReceita;
    }

    /** @return a data de início do tratamento */
    public LocalDate getDataInicio() {
        return dataInicio;
    }

    /**
     * Define a data de início do tratamento.
     * @param dataInicio a data de início
     */
    public void setDataInicio(LocalDate dataInicio) {
        this.dataInicio = dataInicio;
    }

    /** @return o horário de início da receita */
    public String getHoraInicio() {
        return horaInicio;
    }

    /**
     * Define o horário de início da receita.
     * @param horaInicio a hora de início
     */
    public void setHoraInicio(String horaInicio) {
        this.horaInicio = horaInicio;
    }

    /** @return o identificador do usuário vinculado à receita */
    public Long getIdUser() {
        return idUser;
    }

    /**
     * Define o identificador do usuário vinculado à receita.
     * @param idUser o ID do usuário
     */
    public void setIdUser(Long idUser) {
        this.idUser = idUser;
    }

    /** @return o nome do medicamento ou tratamento */
    public String getNome() {
        return nome;
    }

    /**
     * Define o nome do medicamento ou tratamento.
     * @param nome o nome do medicamento
     */
    public void setNome(String nome) {
        this.nome = nome;
    }

    /** @return a frequência do consumo do medicamento */
    public String getFrequencia() {
        return frequencia;
    }

    /**
     * Define a frequência do consumo do medicamento.
     * @param frequencia a frequência a ser atribuída
     */
    public void setFrequencia(String frequencia) {
        this.frequencia = frequencia;
    }

    /** @return os dias da semana em que o medicamento deve ser consumido */
    public String[] getDias() {
        return dias;
    }

    /**
     * Define os dias da semana em que o medicamento deve ser consumido.
     * @param dias um array de dias válidos (ex: {"Segunda", "Quarta", "Sexta"})
     */
    public void setDias(String[] dias) {
        this.dias = dias;
    }

    /** @return o número total de dias de duração da receita */
    public Long getNumeroDias() {
        return numeroDias;
    }

    /**
     * Define o número total de dias de duração da receita.
     * @param numeroDias o número de dias (deve ser zero ou positivo)
     */
    public void setNumeroDias(Long numeroDias) {
        this.numeroDias = numeroDias;
    }

    /** @return as observações adicionais da receita */
    public String getObservacoes() {
        return observacoes;
    }

    /**
     * Define observações adicionais sobre a receita.
     * @param observacoes o texto das observações
     */
    public void setObservacoes(String observacoes) {
        this.observacoes = observacoes;
    }

    /** @return o status atual da receita ("ativo" ou "inativo") */
    public String getStatus() {
        return status;
    }

    /**
     * Define o status atual da receita.
     * @param status o status ("ativo" ou "inativo")
     */
    public void setStatus(String status) {
        this.status = status;
    }
}

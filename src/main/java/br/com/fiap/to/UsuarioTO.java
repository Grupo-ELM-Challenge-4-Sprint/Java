package br.com.fiap.to;

import jakarta.validation.constraints.*;
import org.hibernate.validator.constraints.br.CPF;

import java.time.LocalDate;

/**
 * Representa um usuário do sistema, podendo ser um <strong>paciente</strong> ou um <strong>cuidador</strong>.
 *
 * <p>Esta classe contém atributos como CPF, nome, e-mail, telefone,
 * data de nascimento, tipo de usuário e CPF do cuidador vinculado.</p>
 *
 * <p>As anotações de validação garantem a integridade dos dados de entrada, como CPF válido,
 * formato de e-mail correto e data de nascimento no passado.</p>
 *
 * @author Lucas Barros Gouveia
 * @author Enzo Okuizumi Miranda de Souza
 * @author Milton Jakson de Souza Marcelino
 * @version 1.0
 * @since 21.0.7
 */
public class UsuarioTO {
    @NotNull
    private Long idUser;

    @NotBlank
    @CPF
    private String cpf;

    @NotBlank
    @Pattern(regexp = "^[A-Za-zÀ-ÖØ-öø-ÿ]+(?:[\\s'-][A-Za-zÀ-ÖØ-öø-ÿ]+)*$")
    private String nome;

    @NotBlank
    private String senha;

    @NotBlank
    @Email
    private String email;

    @NotBlank
    @Pattern(regexp = "^(?:\\(?\\d{2}\\)?[\\s-]?)?9?\\d{4}[\\s-]?\\d{4}$")
    private String telefone;

    @NotNull
    @Past
    private LocalDate dataNascimento;

    @NotBlank
    @Pattern(regexp = "^(?i)(paciente|cuidador)$")
    private String tipoUsuario;

    @Pattern(regexp = "^(?:\\d{3}\\.\\d{3}\\.\\d{3}-\\d{2}|\\d{11})$")
    private String cpfCuidador;

    @Pattern(regexp = "^(?:\\d{3}\\.\\d{3}\\.\\d{3}-\\d{2}|\\d{11})$")
    private String cpfPaciente;

    private boolean pacienteEditar;

    /**
     * Construtor padrão da classe {@code UsuarioTO}.
     */
    public UsuarioTO() {}

    /**
     * Construtor completo.
     *
     * @param idUser        identificador único do usuário
     * @param cpf           CPF do usuário
     * @param nome          nome completo do usuário
     * @param senha         senha de acesso
     * @param email         e-mail do usuário
     * @param telefone      telefone de contato
     * @param dataNascimento data de nascimento
     * @param tipoUsuario   tipo do usuário
     * @param cpfCuidador   CPF do cuidador
     * @param cpfPaciente   CPF do paciente
     * @param pacienteEditar Se o paciente está sendo editado.
     */
    public UsuarioTO(Long idUser, String cpf, String nome, String senha, String email, String telefone, LocalDate dataNascimento, String tipoUsuario, String cpfCuidador, String cpfPaciente, boolean pacienteEditar) {
        this.idUser = idUser;
        this.cpf = cpf;
        this.nome = nome;
        this.senha = senha;
        this.email = email;
        this.telefone = telefone;
        this.dataNascimento = dataNascimento;
        this.tipoUsuario = tipoUsuario;
        this.cpfCuidador = cpfCuidador;
        this.cpfPaciente = cpfPaciente;
        this.pacienteEditar = pacienteEditar;
    }

    /** @return o identificador único do usuário */
    public Long getIdUser() {
        return idUser;
    }

    /**
     * Define o identificador do usuário.
     * @param idUser o ID do usuário
     */
    public void setIdUser(Long idUser) {
        this.idUser = idUser;
    }

    /** @return o CPF do usuário */
    public String getCpf() {
        return cpf;
    }

    /**
     * Define o CPF do usuário.
     * @param cpf o CPF a ser atribuído
     */
    public void setCpf(String cpf) {
        this.cpf = cpf;
    }

    /** @return o nome completo do usuário */
    public String getNome() {
        return nome;
    }

    /**
     * Define o nome completo do usuário.
     * @param nome o nome a ser atribuído
     */
    public void setNome(String nome) {
        this.nome = nome;
    }

    /** @return a senha de acesso do usuário */
    public String getSenha() {
        return senha;
    }

    /**
     * Define a senha de acesso do usuário.
     * @param senha a senha a ser atribuída
     */
    public void setSenha(String senha) {
        this.senha = senha;
    }

    /** @return o e-mail do usuário */
    public String getEmail() {
        return email;
    }

    /**
     * Define o e-mail do usuário.
     * @param email o e-mail a ser atribuído
     */
    public void setEmail(String email) {
        this.email = email;
    }

    /** @return o telefone de contato do usuário */
    public String getTelefone() {
        return telefone;
    }

    /**
     * Define o telefone de contato do usuário.
     * @param telefone o telefone a ser atribuído
     */
    public void setTelefone(String telefone) {
        this.telefone = telefone;
    }

    /** @return a data de nascimento do usuário */
    public LocalDate getDataNascimento() {
        return dataNascimento;
    }

    /**
     * Define a data de nascimento do usuário.
     * @param dataNascimento a data de nascimento (deve ser no passado)
     */
    public void setDataNascimento(LocalDate dataNascimento) {
        this.dataNascimento = dataNascimento;
    }

    /** @return o tipo de usuário ("paciente" ou "cuidador") */
    public String getTipoUsuario() {
        return tipoUsuario;
    }

    /**
     * Define o tipo do usuário.
     * @param tipoUsuario o tipo de usuário ("paciente" ou "cuidador")
     */
    public void setTipoUsuario(String tipoUsuario) {
        this.tipoUsuario = tipoUsuario;
    }

    /** @return o CPF do cuidador responsável */
    public String getCpfCuidador() {
        return cpfCuidador;
    }

    /**
     * Define o CPF do cuidador responsável.
     * @param cpfCuidador o CPF do cuidador
     */
    public void setCpfCuidador(String cpfCuidador) {
        this.cpfCuidador = cpfCuidador;
    }


    /** @return o CPF do cuidador responsável */
    public String getCpfPaciente() {
        return cpfPaciente;
    }

    /**
     * Define o CPF do paciente.
     * @param cpfPaciente o CPF do paciente
     */
    public void setCpfPaciente(String cpfPaciente) {
        this.cpfPaciente = cpfPaciente;
    }

    /** @return o valor (true/false) dependendo se o paciente está sendo editado. */
    public boolean getPacienteEditar() {
        return pacienteEditar;
    }

    /**
     * Define se o paciente está sendo editado.
     * @param pacienteEditar o valor true ou false.
     */
    public void setPacienteEditar(boolean pacienteEditar) {
        this.pacienteEditar = pacienteEditar;
    }
}

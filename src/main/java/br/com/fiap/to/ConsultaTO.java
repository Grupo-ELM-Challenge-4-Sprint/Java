package br.com.fiap.to;

import java.time.LocalDate;
import java.time.LocalDateTime; // Importado novamente

public class ConsultaTO {
    private Long idConsulta;
    private String especialidade;
    private String medico;
    private LocalDate data;
    private String hora;
    private String tipo;
    private String local;
    private String observacoes;
    private String status;
    private Long idUser;

    public ConsultaTO() {
    }

    public ConsultaTO(Long idConsulta, String especialidade, String medico, LocalDate data, String hora, String tipo, String local, String observacoes, String status, Long idUser) {
        this.idConsulta = idConsulta;
        this.especialidade = especialidade;
        this.medico = medico;
        this.data = data;
        this.hora = hora;
        this.tipo = tipo;
        this.local = local;
        this.observacoes = observacoes;
        this.status = status;
        this.idUser = idUser;
    }

    // Getters e Setters (ajustados para LocalDateTime hora e String local)

    public Long getIdConsulta() { return idConsulta; }
    public void setIdConsulta(Long idConsulta) { this.idConsulta = idConsulta; }

    public String getEspecialidade() { return especialidade; }
    public void setEspecialidade(String especialidade) { this.especialidade = especialidade; }

    public String getMedico() { return medico; }
    public void setMedico(String medico) { this.medico = medico; }

    public LocalDate getData() { return data; }
    public void setData(LocalDate data) { this.data = data; }

    public String getHora() { return hora; }
    public void setHora(String hora) { this.hora = hora; }

    public String getTipo() { return tipo; }
    public void setTipo(String tipo) { this.tipo = tipo; }

    public String getLocal() { return local; }
    public void setLocal(String local) { this.local = local; }

    public String getObservacoes() { return observacoes; }
    public void setObservacoes(String observacoes) { this.observacoes = observacoes; }

    public String getStatus() { return status; }
    public void setStatus(String status) { this.status = status; }

    public Long getIdUser() { return idUser; }
    public void setIdUser(Long idUser) { this.idUser = idUser; }
}
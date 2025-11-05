package br.com.fiap.dao;

import br.com.fiap.to.ConsultaTO;

import java.sql.*;
import java.util.ArrayList;

/**
 * Classe responsável pelo acesso e manipulação dos dados da entidade {@link ConsultaTO}
 * na base de dados SQL.
 * <p>
 * Esta classe implementa as operações CRUD (Create, Read, Update, Delete)
 * para a tabela <b>ddd_consulta</b>, permitindo o gerenciamento das consultas
 * médicas associadas aos usuários do sistema.
 * </p>
 *
 * <p>Utiliza a {@link ConnectionFactory} para gerenciar conexões com o banco de dados.</p>
 *
 * @author Lucas Barros Gouveia
 * @author Enzo Okuizumi Miranda de Souza
 * @author Milton Jakson de Souza Marcelino
 * @version 1.0
 * @since 21.0.7
 */
public class ConsultaDAO {

    /**
     * Recupera todas as consultas cadastradas na tabela <b>ddd_consulta</b>.
     *
     * @return uma lista de {@link ConsultaTO} com todas as consultas encontradas,
     * ou {@code null} caso ocorra um erro na consulta.
     */
    public ArrayList<ConsultaTO> findAll() {
        ArrayList<ConsultaTO> consultas = new ArrayList<ConsultaTO>();
        String sql = "SELECT * FROM ddd_consulta ORDER BY id_consulta";
        try (PreparedStatement ps = ConnectionFactory.getConnection().prepareStatement(sql)) {
            ResultSet rs = ps.executeQuery();
            if (rs != null) {
                while (rs.next()) {
                    ConsultaTO consulta = new ConsultaTO();
                    consulta.setIdConsulta(rs.getLong("id_consulta"));
                    consulta.setEspecialidade(rs.getString("especialidade"));
                    consulta.setMedico(rs.getString("medico"));
                    consulta.setData(rs.getDate("data").toLocalDate());
                    consulta.setHora(rs.getString("hora"));
                    consulta.setTipo(rs.getString("tipo"));
                    consulta.setLocal(rs.getString("local"));
                    consulta.setObservacoes(rs.getString("observacoes"));
                    consulta.setStatus(rs.getString("status"));
                    consulta.setIdUser(rs.getLong("id_user"));
                    consultas.add(consulta);
                }
            } else {
                return null;
            }
        } catch (SQLException e) {
            System.out.println("Erro na consulta: " + e.getMessage());
        }
        return consultas;
    }

    /**
     * Busca uma consulta pelo seu identificador único (ID).
     *
     * @param id_consulta o código (ID) da consulta a ser buscada.
     * @return um objeto {@link ConsultaTO} correspondente ao ID informado,
     * ou {@code null} se nenhum registro for encontrado.
     */
    public ConsultaTO findByCodigo(Long id_consulta) {
        ConsultaTO consulta = new ConsultaTO();
        String sql = "SELECT * FROM ddd_consulta WHERE id_consulta = ?";
        try (PreparedStatement ps = ConnectionFactory.getConnection().prepareStatement(sql)) {
            ps.setLong(1, id_consulta);
            ResultSet rs = ps.executeQuery();
            if (rs.next()) {
                consulta.setIdConsulta(rs.getLong("id_consulta"));
                consulta.setEspecialidade(rs.getString("especialidade"));
                consulta.setMedico(rs.getString("medico"));
                consulta.setData(rs.getDate("data").toLocalDate());
                consulta.setHora(rs.getString("hora"));
                consulta.setTipo(rs.getString("tipo"));
                consulta.setLocal(rs.getString("local"));
                consulta.setObservacoes(rs.getString("observacoes"));
                consulta.setStatus(rs.getString("status"));
                consulta.setIdUser(rs.getLong("id_user"));
            } else {
                return null;
            }
        } catch (SQLException e) {
            System.out.println("Erro na consulta: " + e.getMessage());
        }
        return consulta;
    }

    /**
     * Busca todas as consultas pelo seu identificador único do usuário (ID).
     *
     * @param idUser o identificador único do usuário (ID) cujas consultas devem ser buscadas.
     * @return uma lista de objetos {@link ConsultaTO} correspondentes ao usuário informado
     * ou {@code null} se nenhum registro for encontrado.
     */
    public ArrayList<ConsultaTO> findAllByUserId(Long idUser) {
        ArrayList<ConsultaTO> consultas = new ArrayList<>();
        String sql = "SELECT * FROM ddd_consulta WHERE id_user = ? ORDER BY data DESC, hora DESC";
        try (PreparedStatement ps = ConnectionFactory.getConnection().prepareStatement(sql)) {
            ps.setLong(1, idUser);
            ResultSet rs = ps.executeQuery();
            while (rs.next()) {
                ConsultaTO consulta = new ConsultaTO();
                consulta.setIdConsulta(rs.getLong("id_consulta"));
                consulta.setEspecialidade(rs.getString("especialidade"));
                consulta.setMedico(rs.getString("medico"));
                consulta.setData(rs.getDate("data").toLocalDate());
                consulta.setHora(rs.getString("hora"));
                consulta.setTipo(rs.getString("tipo"));
                consulta.setLocal(rs.getString("local"));
                consulta.setObservacoes(rs.getString("observacoes"));
                consulta.setStatus(rs.getString("status"));
                consulta.setIdUser(rs.getLong("id_user"));
                consultas.add(consulta);
            }
        } catch (SQLException e) {
            System.out.println("Erro na consulta por ID de usuário: " + e.getMessage());
        }
        return consultas;
    }

    /**
     * Insere um novo registro de consulta na tabela <b>ddd_consulta</b>.
     *
     * @param consulta o objeto {@link ConsultaTO} contendo os dados a serem inseridos.
     * @return o próprio {@link ConsultaTO} se o registro for inserido com sucesso,
     * ou {@code null} em caso de erro.
     */
    public ConsultaTO save(ConsultaTO consulta) {
        String sql = "INSERT INTO ddd_consulta(especialidade, medico, data, hora, tipo, local, observacoes, status, id_user) VALUES(?,?,?,?,?,?,?,?,?)";

        try (PreparedStatement ps = ConnectionFactory.getConnection().prepareStatement(sql)) {
            ps.setString(1, consulta.getEspecialidade());
            ps.setString(2, consulta.getMedico());
            ps.setDate(3, Date.valueOf(consulta.getData()));
            ps.setString(4, consulta.getHora());
            ps.setString(5, consulta.getTipo());
            ps.setString(6, consulta.getLocal());
            ps.setString(7, consulta.getObservacoes());
            ps.setString(8, consulta.getStatus());
            ps.setLong(9, consulta.getIdUser());
            if (ps.executeUpdate() > 0) {
                return consulta;
            } else {
                return null;
            }
        } catch (SQLException e) {
            System.out.println("Erro ao salvar: " + e.getMessage());
        }
        return null;
    }

    /**
     * Exclui uma consulta pelo seu identificador único (ID).
     *
     * @param id_consulta o identificador da consulta a ser excluída.
     * @return {@code true} se a exclusão for bem-sucedida, {@code false} caso contrário.
     */
    public boolean delete(Long id_consulta) {
        String sql = "DELETE FROM ddd_consulta WHERE id_consulta = ?";
        try (PreparedStatement ps = ConnectionFactory.getConnection().prepareStatement(sql)) {
            ps.setLong(1, id_consulta);
            return ps.executeUpdate() > 0;
        } catch (SQLException e) {
            System.out.println("Erro ao excluir: " + e.getMessage());
        }
        return false;
    }

    /**
     * Atualiza os dados de uma consulta existente na tabela <b>ddd_consulta</b>.
     *
     * @param consulta o objeto {@link ConsultaTO} contendo os novos dados da consulta.
     * @return o {@link ConsultaTO} atualizado, ou {@code null} se ocorrer algum erro.
     */
    public ConsultaTO update(ConsultaTO consulta) {
        String sql = "UPDATE ddd_consulta SET especialidade=?, medico=?, data=?, hora=?, tipo=?, local=?, observacoes=?, status=?, id_user=? WHERE id_consulta=?";
        try (PreparedStatement ps = ConnectionFactory.getConnection().prepareStatement(sql)) {
            ps.setString(1, consulta.getEspecialidade());
            ps.setString(2, consulta.getMedico());
            ps.setDate(3, Date.valueOf(consulta.getData()));
            ps.setString(4, consulta.getHora());
            ps.setString(5, consulta.getTipo());
            ps.setString(6, consulta.getLocal());
            ps.setString(7, consulta.getObservacoes());
            ps.setString(8, consulta.getStatus());
            ps.setLong(9, consulta.getIdUser());
            ps.setLong(10, consulta.getIdConsulta());

            if (ps.executeUpdate() > 0) {
                return consulta;
            } else {
                return null;
            }
        } catch (SQLException e) {
            System.out.println("Erro ao atualizar: " + e.getMessage());
        }
        return null;
    }
}

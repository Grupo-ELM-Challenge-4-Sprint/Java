package br.com.fiap.dao;

import br.com.fiap.to.ReceitaTO;

import java.sql.*;
import java.util.ArrayList;
import java.util.Arrays;

/**
 * Classe responsável pelo acesso e manipulação dos dados da classe {@link ReceitaTO}
 * na base de dados SQL.
 * <p>
 * Esta classe implementa as operações CRUD (Create, Read, Update, Delete)
 * para a tabela <b>ddd_receita</b>, permitindo o gerenciamento das receitas
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
public class ReceitaDAO {

    /**
     * Recupera todas as receitas cadastradas na tabela <b>ddd_receita</b>.
     *
     * @return uma lista de {@link ReceitaTO} com todas as receitas encontradas,
     * ou {@code null} caso ocorra um erro na consulta.
     */
    public ArrayList<ReceitaTO> findAll() {
        ArrayList<ReceitaTO> receitas = new ArrayList<ReceitaTO>();
        String sql = "SELECT * FROM ddd_receita ORDER BY id_receita";
        try (PreparedStatement ps = ConnectionFactory.getConnection().prepareStatement(sql)) {
            ResultSet rs = ps.executeQuery();
            if (rs != null) {
                while (rs.next()) {
                    ReceitaTO receita = new ReceitaTO();
                    receita.setIdReceita(rs.getLong("id_receita"));
                    receita.setIdUser(rs.getLong("id_user"));
                    receita.setNome(rs.getString("nome"));
                    receita.setFrequencia(rs.getString("frequencia"));
                    receita.setDias(new String[]{rs.getString("dias")});
                    receita.setNumeroDias(rs.getLong("numero_dias"));
                    receita.setDataInicio(rs.getDate("data_inicio").toLocalDate());
                    receita.setHoraInicio(rs.getTimestamp("hora_inicio").toLocalDateTime());
                    receita.setObservacoes(rs.getString("observacoes"));
                    receita.setStatus(rs.getString("status"));
                    receitas.add(receita);
                }
            } else {
                return null;
            }
        } catch (SQLException e) {
            System.out.println("Erro na consulta: " + e.getMessage());
        } finally {
            ConnectionFactory.closeConnection();
        }
        return receitas;
    }

    /**
     * Busca uma receita pelo seu identificador único (ID).
     *
     * @param id_user o código (ID) da receita a ser buscada.
     * @return um objeto {@link ReceitaTO} correspondente ao ID informado,
     * ou {@code null} se nenhum registro for encontrado.
     */
    public ReceitaTO findByCodigo(Long id_user) {
        ReceitaTO receita = new ReceitaTO();
        String sql = "SELECT * FROM ddd_receita WHERE id_receita = ?";
        try (PreparedStatement ps = ConnectionFactory.getConnection().prepareStatement(sql)) {
            ps.setLong(1, id_user);
            ResultSet rs = ps.executeQuery();
            if (rs.next()) {
                receita.setIdReceita(rs.getLong("id_receita"));
                receita.setIdUser(rs.getLong("id_user"));
                receita.setNome(rs.getString("nome"));
                receita.setFrequencia(rs.getString("frequencia"));
                receita.setDias(new String[]{rs.getString("dias")});
                receita.setNumeroDias(rs.getLong("numero_dias"));
                receita.setDataInicio(rs.getDate("data_inicio").toLocalDate());
                receita.setHoraInicio(rs.getTimestamp("hora_inicio").toLocalDateTime());
                receita.setObservacoes(rs.getString("observacoes"));
                receita.setStatus(rs.getString("status"));
            } else {
                return null;
            }
        } catch (SQLException e) {
            System.out.println("Erro na consulta: " + e.getMessage());
        } finally {
            ConnectionFactory.closeConnection();
        }
        return receita;
    }

    /**
     * Insere um novo registro de receita na tabela <b>ddd_receita</b>.
     *
     * @param receita o objeto {@link ReceitaTO} contendo os dados a serem inseridos.
     * @return o próprio {@link ReceitaTO} se o registro for inserido com sucesso,
     * ou {@code null} em caso de erro.
     */
    public ReceitaTO save(ReceitaTO receita) {
        String sql = "INSERT INTO ddd_receita(nome, frequencia, dias, numero_dias, data_inicio, hora_inicio, observacoes, status, id_user) VALUES(?,?,?,?,?,?,?,?,?)";

        try (PreparedStatement ps = ConnectionFactory.getConnection().prepareStatement(sql)) {
            ps.setString(1, receita.getNome());
            ps.setString(2, receita.getFrequencia());
            ps.setString(3, Arrays.toString(receita.getDias()));
            ps.setLong(4, receita.getNumeroDias());
            ps.setDate(5, Date.valueOf(receita.getDataInicio()));
            ps.setTimestamp(6, Timestamp.valueOf(receita.getHoraInicio()));
            ps.setString(7, receita.getObservacoes());
            ps.setString(8, receita.getStatus());
            ps.setLong(9, receita.getIdUser());
            if (ps.executeUpdate() > 0) {
                return receita;
            } else {
                return null;
            }
        } catch (SQLException e) {
            System.out.println("Erro ao salvar: " + e.getMessage());
        } finally {
            ConnectionFactory.closeConnection();
        }
        return null;
    }

    /**
     * Exclui uma receita pelo seu identificador único (ID).
     *
     * @param id_receita o identificador da receita a ser excluída.
     * @return {@code true} se a exclusão for bem-sucedida, {@code false} caso contrário.
     */
    public boolean delete(Long id_receita) {
        String sql = "DELETE FROM ddd_receita WHERE id_receita = ?";
        try (PreparedStatement ps = ConnectionFactory.getConnection().prepareStatement(sql)) {
            ps.setLong(1, id_receita);
            return ps.executeUpdate() > 0;
        } catch (SQLException e) {
            System.out.println("Erro ao excluir: " + e.getMessage());
        } finally {
            ConnectionFactory.closeConnection();
        }
        return false;
    }

    /**
     * Atualiza os dados de uma receita existente na tabela <b>ddd_receita</b>.
     *
     * @param receita o objeto {@link ReceitaTO} contendo os novos dados da receita.
     * @return o {@link ReceitaTO} atualizado, ou {@code null} se ocorrer algum erro.
     */
    public ReceitaTO update(ReceitaTO receita) {
        String sql = "UPDATE ddd_receita SET id_user=?, nome=?, frequencia=?, dias=?, numero_dias=?, data_inicio=?, hora_inicio=?, observacoes=?, status=? WHERE id_receita=?";
        try (PreparedStatement ps = ConnectionFactory.getConnection().prepareStatement(sql)) {
            ps.setLong(1, receita.getIdUser());
            ps.setString(2, receita.getNome());
            ps.setString(3, receita.getFrequencia());
            ps.setString(4, Arrays.toString(receita.getDias()));
            ps.setLong(5, receita.getNumeroDias());
            ps.setDate(6, Date.valueOf(receita.getDataInicio()));
            ps.setTimestamp(7, Timestamp.valueOf(receita.getHoraInicio()));
            ps.setString(8, receita.getObservacoes());
            ps.setString(9, receita.getStatus());
            ps.setLong(10, receita.getIdReceita());

            if (ps.executeUpdate() > 0) {
                return receita;
            } else {
                return null;
            }
        } catch (SQLException e) {
            System.out.println("Erro ao atualizar: " + e.getMessage());
        } finally {
            ConnectionFactory.closeConnection();
        }
        return null;
    }
}

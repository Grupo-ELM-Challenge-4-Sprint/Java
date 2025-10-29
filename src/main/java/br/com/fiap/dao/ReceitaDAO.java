package br.com.fiap.dao;

import br.com.fiap.to.ReceitaTO;

import java.sql.*;
import java.util.ArrayList;

public class ReceitaDAO {

    private void popularReceita(ReceitaTO receita, ResultSet rs) throws SQLException {
        receita.setIdReceita(rs.getLong("id_receita"));
        receita.setIdUser(rs.getLong("id_user"));
        receita.setNomeMedicamento(rs.getString("nome_medicamento"));
        receita.setFrequenciaHoras(rs.getInt("frequencia_horas")); // Ajustado para getInt

        String diasDb = rs.getString("dias_semana");
        receita.setDias(diasDb != null && !diasDb.isEmpty() ? diasDb.split(",") : new String[0]);

        receita.setNumeroDiasTratamento(rs.getLong("numero_dias_tratamento"));
        receita.setDataInicio(rs.getDate("data_inicio").toLocalDate());
        receita.setHoraInicio(rs.getTimestamp("hora_inicio").toLocalDateTime()); // Ajustado
        receita.setObservacoes(rs.getString("observacoes"));
        receita.setStatus(rs.getString("status"));
    }

    public ArrayList<ReceitaTO> findAllByUserId(Long userId) {
        ArrayList<ReceitaTO> receitas = new ArrayList<>();
        String sql = "SELECT * FROM ddd_receita WHERE id_user = ? ORDER BY data_inicio DESC, hora_inicio DESC"; // Ordenar
        try (PreparedStatement ps = ConnectionFactory.getConnection().prepareStatement(sql)) {
            ps.setLong(1, userId);
            ResultSet rs = ps.executeQuery();
            while (rs.next()) {
                ReceitaTO receita = new ReceitaTO();
                popularReceita(receita, rs); // Reutiliza o método existente
                receitas.add(receita);
            }
        } catch (SQLException e) {
            System.out.println("Erro na consulta de receitas por ID de usuário: " + e.getMessage());
        } finally {
            ConnectionFactory.closeConnection();
        }
        return receitas;
    }

    public ArrayList<ReceitaTO> findAll() {
        ArrayList<ReceitaTO> receitas = new ArrayList<>();
        String sql = "SELECT * FROM ddd_receita ORDER BY id_receita";
        try (PreparedStatement ps = ConnectionFactory.getConnection().prepareStatement(sql)) {
            ResultSet rs = ps.executeQuery();
            while (rs.next()) {
                ReceitaTO receita = new ReceitaTO();
                popularReceita(receita, rs);
                receitas.add(receita);
            }
        } catch (SQLException e) {
            System.out.println("Erro na consulta de receitas: " + e.getMessage());
        } finally {
            ConnectionFactory.closeConnection();
        }
        return receitas;
    }

    public ReceitaTO findByCodigo(Long id_receita) { // Corrigido parâmetro
        ReceitaTO receita = null;
        String sql = "SELECT * FROM ddd_receita WHERE id_receita = ?"; // Corrigido WHERE
        try (PreparedStatement ps = ConnectionFactory.getConnection().prepareStatement(sql)) {
            ps.setLong(1, id_receita); // Usar o parâmetro correto
            ResultSet rs = ps.executeQuery();
            if (rs.next()) {
                receita = new ReceitaTO();
                popularReceita(receita, rs);
            }
        } catch (SQLException e) {
            System.out.println("Erro na consulta de receita por código: " + e.getMessage());
        } finally {
            ConnectionFactory.closeConnection();
        }
        return receita;
    }

    public ReceitaTO save(ReceitaTO receita) {
        String sql = "INSERT INTO ddd_receita(nome_medicamento, frequencia_horas, dias_semana, numero_dias_tratamento, data_inicio, hora_inicio, observacoes, status, id_user) VALUES(?,?,?,?,?,?,?,?,?)";

        try (PreparedStatement ps = ConnectionFactory.getConnection().prepareStatement(sql)) {
            ps.setString(1, receita.getNomeMedicamento());
            ps.setInt(2, receita.getFrequenciaHoras()); // Ajustado para setInt
            ps.setString(3, String.join(",", receita.getDias()));
            ps.setLong(4, receita.getNumeroDiasTratamento());
            ps.setDate(5, Date.valueOf(receita.getDataInicio()));
            ps.setTimestamp(6, Timestamp.valueOf(receita.getHoraInicio())); // Ajustado
            ps.setString(7, receita.getObservacoes());
            ps.setString(8, receita.getStatus());
            ps.setLong(9, receita.getIdUser());
            if (ps.executeUpdate() > 0) {
                return receita;
            }
        } catch (SQLException e) {
            System.out.println("Erro ao salvar receita: " + e.getMessage());
        } finally {
            ConnectionFactory.closeConnection();
        }
        return null;
    }

    public boolean delete(Long id_receita) {
        String sql = "DELETE FROM ddd_receita WHERE id_receita = ?";
        try (PreparedStatement ps = ConnectionFactory.getConnection().prepareStatement(sql)) {
            ps.setLong(1, id_receita);
            return ps.executeUpdate() > 0;
        } catch (SQLException e) {
            System.out.println("Erro ao excluir receita: " + e.getMessage());
        } finally {
            ConnectionFactory.closeConnection();
        }
        return false;
    }

    public ReceitaTO update(ReceitaTO receita) {
        // Ajustado id_user para o final e id_receita para o WHERE
        String sql = "UPDATE ddd_receita SET nome_medicamento=?, frequencia_horas=?, dias_semana=?, numero_dias_tratamento=?, data_inicio=?, hora_inicio=?, observacoes=?, status=?, id_user=? WHERE id_receita=?";
        try (PreparedStatement ps = ConnectionFactory.getConnection().prepareStatement(sql)) {
            ps.setString(1, receita.getNomeMedicamento());
            ps.setInt(2, receita.getFrequenciaHoras()); // Ajustado para setInt
            ps.setString(3, String.join(",", receita.getDias()));
            ps.setLong(4, receita.getNumeroDiasTratamento());
            ps.setDate(5, Date.valueOf(receita.getDataInicio()));
            ps.setTimestamp(6, Timestamp.valueOf(receita.getHoraInicio())); // Ajustado
            ps.setString(7, receita.getObservacoes());
            ps.setString(8, receita.getStatus());
            ps.setLong(9, receita.getIdUser());
            ps.setLong(10, receita.getIdReceita()); // Corrigido índice

            if (ps.executeUpdate() > 0) {
                return receita;
            }
        } catch (SQLException e) {
            System.out.println("Erro ao atualizar receita: " + e.getMessage());
        } finally {
            ConnectionFactory.closeConnection();
        }
        return null;
    }
}
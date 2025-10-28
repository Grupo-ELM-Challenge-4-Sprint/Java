package br.com.fiap.dao;

import br.com.fiap.to.ReceitaTO;
import br.com.fiap.to.UsuarioTO;

import java.sql.*;
import java.util.ArrayList;
import java.util.Arrays;

public class ReceitaDAO {
    public ArrayList<ReceitaTO> findAll() {
        ArrayList<ReceitaTO> receitas = new ArrayList<ReceitaTO>();
        String sql = "SELECT * FROM ddd_receita ORDER BY id_receita";
        try(PreparedStatement ps = ConnectionFactory.getConnection().prepareStatement(sql))
        {
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

    public ReceitaTO findByCodigo(Long id_user) {
        ReceitaTO receita = new ReceitaTO();
        String sql = "SELECT * FROM ddd_receita WHERE id_receita = ?";
        try(PreparedStatement ps = ConnectionFactory.getConnection().prepareStatement(sql))
        {
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

    public ReceitaTO save(ReceitaTO receita) {
        String sql = "INSERT INTO ddd_receita(nome, frequencia, dias, numero_dias, data_inicio, hora_inicio, observacoes, status, id_user) VALUES(?,?,?,?,?,?,?,?,?)";

        try (PreparedStatement ps = ConnectionFactory.getConnection().prepareStatement(sql))
        {
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

    public boolean delete(Long id_receita) {
        String sql = "delete from ddd_receita where id_receita = ?";
        try (PreparedStatement ps = ConnectionFactory.getConnection().prepareStatement(sql)){
            ps.setLong(1, id_receita);
            return ps.executeUpdate() > 0;
        } catch (SQLException e) {
            System.out.println("Erro ao excluir: " + e.getMessage());
        } finally {
            ConnectionFactory.closeConnection();
        }
        return false;
    }

    public ReceitaTO update(ReceitaTO receita) {
        String sql = "update ddd_receita set id_user=?, nome=?, frequencia=?, dias=?, numero_dias=?, data_inicio=?, hora_inicio=?, observacoes=?, status=? where id_receita=?";
        try (PreparedStatement ps = ConnectionFactory.getConnection().prepareStatement(sql)){
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

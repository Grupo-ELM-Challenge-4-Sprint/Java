package br.com.fiap.dao;

import br.com.fiap.to.ConsultaTO;

import java.sql.*;
import java.util.ArrayList;

public class ConsultaDAO {
    public ArrayList<ConsultaTO> findAll() {
        ArrayList<ConsultaTO> consultas = new ArrayList<ConsultaTO>();
        String sql = "SELECT * FROM ddd_consulta ORDER BY id_consulta";
        try(PreparedStatement ps = ConnectionFactory.getConnection().prepareStatement(sql))
        {
            ResultSet rs = ps.executeQuery();
            if (rs != null) {
                while (rs.next()) {
                    ConsultaTO consulta = new ConsultaTO();
                    consulta.setIdConsulta(rs.getLong("id_consulta"));
                    consulta.setEspecialidade(rs.getString("especialidade"));
                    consulta.setNomeCuidador(rs.getString("nome_cuidador"));
                    consulta.setData(rs.getDate("data").toLocalDate());
                    consulta.setHora(rs.getTimestamp("hora").toLocalDateTime());
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
        } finally {
            ConnectionFactory.closeConnection();
        }
        return consultas;
    }

    public ConsultaTO findByCodigo(Long id_consulta) {
        ConsultaTO consulta = new ConsultaTO();
        String sql = "SELECT * FROM ddd_consulta WHERE id_consulta = ?";
        try(PreparedStatement ps = ConnectionFactory.getConnection().prepareStatement(sql))
        {
            ps.setLong(1, id_consulta);
            ResultSet rs = ps.executeQuery();
            if (rs.next()) {
                consulta.setIdConsulta(rs.getLong("id_consulta"));
                consulta.setEspecialidade(rs.getString("especialidade"));
                consulta.setNomeCuidador(rs.getString("nome_cuidador"));
                consulta.setData(rs.getDate("data").toLocalDate());
                consulta.setHora(rs.getTimestamp("hora").toLocalDateTime());
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
        } finally {
            ConnectionFactory.closeConnection();
        }
        return consulta;
    }

    public ConsultaTO save(ConsultaTO consulta) {
        String sql = "INSERT INTO ddd_consulta(especialidade, nome_cuidador, data, hora, tipo, local, observacoes, status, id_user) VALUES(?,?,?,?,?,?,?,?,?)";

        try (PreparedStatement ps = ConnectionFactory.getConnection().prepareStatement(sql))
        {
            ps.setString(1, consulta.getEspecialidade());
            ps.setString(2, consulta.getNomeCuidador());
            ps.setDate(3, Date.valueOf(consulta.getData()));
            ps.setTimestamp(4, Timestamp.valueOf(consulta.getHora()));
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
        } finally {
            ConnectionFactory.closeConnection();
        }
        return null;
    }

    public boolean delete(Long id_consulta) {
        String sql = "delete from ddd_consulta where id_consulta = ?";
        try (PreparedStatement ps = ConnectionFactory.getConnection().prepareStatement(sql)){
            ps.setLong(1, id_consulta);
            return ps.executeUpdate() > 0;
        } catch (SQLException e) {
            System.out.println("Erro ao excluir: " + e.getMessage());
        } finally {
            ConnectionFactory.closeConnection();
        }
        return false;
    }

    public ConsultaTO update(ConsultaTO consulta) {
        String sql = "update ddd_consulta set especialidade=?, nome_cuidador=?, data=?, hora=?, tipo=?, local=?, observacoes=?, status=?, id_user=? where id_consulta=?";
        try (PreparedStatement ps = ConnectionFactory.getConnection().prepareStatement(sql)){
            ps.setString(1, consulta.getEspecialidade());
            ps.setString(2, consulta.getNomeCuidador());
            ps.setDate(3, Date.valueOf(consulta.getData()));
            ps.setTimestamp(4, Timestamp.valueOf(consulta.getHora()));
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
        } finally {
            ConnectionFactory.closeConnection();
        }
        return null;
    }
}

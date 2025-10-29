package br.com.fiap.dao;

import br.com.fiap.to.ConsultaTO;

import java.sql.*;
import java.util.ArrayList;

public class ConsultaDAO {

    private void popularConsulta(ConsultaTO consulta, ResultSet rs) throws SQLException {
        consulta.setIdConsulta(rs.getLong("id_consulta"));
        consulta.setEspecialidade(rs.getString("especialidade"));
        consulta.setMedico(rs.getString("medico"));
        consulta.setData(rs.getDate("data_consulta").toLocalDate());
        consulta.setHora(rs.getTimestamp("hora_consulta").toLocalDateTime()); // Ajustado
        consulta.setTipo(rs.getString("tipo"));
        consulta.setLocal(rs.getString("local")); // Ajustado
        consulta.setObservacoes(rs.getString("observacoes"));
        consulta.setStatus(rs.getString("status"));
        consulta.setIdUser(rs.getLong("id_user"));
    }

    public ArrayList<ConsultaTO> findAll() {
        ArrayList<ConsultaTO> consultas = new ArrayList<>();
        String sql = "SELECT * FROM ddd_consulta ORDER BY id_consulta";
        try (PreparedStatement ps = ConnectionFactory.getConnection().prepareStatement(sql)) {
            ResultSet rs = ps.executeQuery();
            while (rs.next()) {
                ConsultaTO consulta = new ConsultaTO();
                popularConsulta(consulta, rs);
                consultas.add(consulta);
            }
        } catch (SQLException e) {
            System.out.println("Erro na consulta: " + e.getMessage());
        } finally {
            ConnectionFactory.closeConnection();
        }
        return consultas;
    }

    public ConsultaTO findByCodigo(Long id_consulta) {
        ConsultaTO consulta = null;
        String sql = "SELECT * FROM ddd_consulta WHERE id_consulta = ?";
        try (PreparedStatement ps = ConnectionFactory.getConnection().prepareStatement(sql)) {
            ps.setLong(1, id_consulta);
            ResultSet rs = ps.executeQuery();
            if (rs.next()) {
                consulta = new ConsultaTO();
                popularConsulta(consulta, rs);
            }
        } catch (SQLException e) {
            System.out.println("Erro na consulta: " + e.getMessage());
        } finally {
            ConnectionFactory.closeConnection();
        }
        return consulta;
    }

    public ConsultaTO save(ConsultaTO consulta) {
        // SQL ajustado para 'local' e 'hora_consulta'
        String sql = "INSERT INTO ddd_consulta(especialidade, medico, data_consulta, hora_consulta, tipo, local, observacoes, status, id_user) VALUES(?,?,?,?,?,?,?,?,?)";

        try (PreparedStatement ps = ConnectionFactory.getConnection().prepareStatement(sql)) {
            ps.setString(1, consulta.getEspecialidade());
            ps.setString(2, consulta.getMedico());
            ps.setDate(3, Date.valueOf(consulta.getData()));
            ps.setTimestamp(4, Timestamp.valueOf(consulta.getHora())); // Ajustado
            ps.setString(5, consulta.getTipo());
            ps.setString(6, consulta.getLocal()); // Ajustado
            ps.setString(7, consulta.getObservacoes());
            ps.setString(8, consulta.getStatus());
            ps.setLong(9, consulta.getIdUser());
            if (ps.executeUpdate() > 0) {
                return consulta;
            }
        } catch (SQLException e) {
            System.out.println("Erro ao salvar consulta: " + e.getMessage());
        } finally {
            ConnectionFactory.closeConnection();
        }
        return null;
    }

    public boolean delete(Long id_consulta) {
        String sql = "DELETE FROM ddd_consulta WHERE id_consulta = ?";
        try (PreparedStatement ps = ConnectionFactory.getConnection().prepareStatement(sql)) {
            ps.setLong(1, id_consulta);
            return ps.executeUpdate() > 0;
        } catch (SQLException e) {
            System.out.println("Erro ao excluir consulta: " + e.getMessage());
        } finally {
            ConnectionFactory.closeConnection();
        }
        return false;
    }

    public ConsultaTO update(ConsultaTO consulta) {
        // SQL ajustado para 'local' e 'hora_consulta'
        String sql = "UPDATE ddd_consulta SET especialidade=?, medico=?, data_consulta=?, hora_consulta=?, tipo=?, local=?, observacoes=?, status=?, id_user=? WHERE id_consulta=?";
        try (PreparedStatement ps = ConnectionFactory.getConnection().prepareStatement(sql)) {
            ps.setString(1, consulta.getEspecialidade());
            ps.setString(2, consulta.getMedico());
            ps.setDate(3, Date.valueOf(consulta.getData()));
            ps.setTimestamp(4, Timestamp.valueOf(consulta.getHora())); // Ajustado
            ps.setString(5, consulta.getTipo());
            ps.setString(6, consulta.getLocal()); // Ajustado
            ps.setString(7, consulta.getObservacoes());
            ps.setString(8, consulta.getStatus());
            ps.setLong(9, consulta.getIdUser());
            ps.setLong(10, consulta.getIdConsulta());

            if (ps.executeUpdate() > 0) {
                return consulta;
            }
        } catch (SQLException e) {
            System.out.println("Erro ao atualizar consulta: " + e.getMessage());
        } finally {
            ConnectionFactory.closeConnection();
        }
        return null;
    }
}
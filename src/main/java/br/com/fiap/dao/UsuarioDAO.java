package br.com.fiap.dao;

import br.com.fiap.to.UsuarioTO;

import java.sql.Date;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.ArrayList;

public class UsuarioDAO {

    private void popularUsuario(UsuarioTO usuario, ResultSet rs) throws SQLException {
        usuario.setIdUser(rs.getLong("id_user"));
        usuario.setCpf(rs.getString("cpf"));
        usuario.setNome(rs.getString("nome"));
        usuario.setSenha(rs.getString("senha"));
        usuario.setEmail(rs.getString("email"));
        usuario.setTelefone(rs.getString("telefone"));
        usuario.setDataNascimento(rs.getDate("data_nascimento").toLocalDate());
        usuario.setTipoUsuario(rs.getString("tipo_usuario"));
        usuario.setCpfPaciente(rs.getString("cpf_paciente"));
        usuario.setCpfCuidador(rs.getString("cpf_cuidador"));
        usuario.setPacienteEditar(rs.getInt("paciente_editar") == 1);
    }

    public ArrayList<UsuarioTO> findAll() {
        ArrayList<UsuarioTO> usuarios = new ArrayList<>();
        String sql = "SELECT * FROM ddd_usuario ORDER BY id_user";
        try (PreparedStatement ps = ConnectionFactory.getConnection().prepareStatement(sql)) {
            ResultSet rs = ps.executeQuery();
            while (rs.next()) {
                UsuarioTO usuario = new UsuarioTO();
                popularUsuario(usuario, rs);
                usuarios.add(usuario);
            }
        } catch (SQLException e) {
            System.out.println("Erro na consulta: " + e.getMessage());
        } finally {
            ConnectionFactory.closeConnection();
        }
        return usuarios;
    }

    public UsuarioTO findByCpf(String cpf) {
        UsuarioTO usuario = null;
        String sql = "SELECT * FROM ddd_usuario WHERE cpf = ?";
        try (PreparedStatement ps = ConnectionFactory.getConnection().prepareStatement(sql)) {
            ps.setString(1, cpf);
            ResultSet rs = ps.executeQuery();
            if (rs.next()) {
                usuario = new UsuarioTO();
                popularUsuario(usuario, rs);
            }
        } catch (SQLException e) {
            System.out.println("Erro na consulta por CPF: " + e.getMessage());
        } finally {
            ConnectionFactory.closeConnection();
        }
        return usuario;
    }

    public UsuarioTO findByCodigo(Long codigo) {
        UsuarioTO usuario = null;
        String sql = "SELECT * FROM ddd_usuario WHERE id_user = ?";
        try (PreparedStatement ps = ConnectionFactory.getConnection().prepareStatement(sql)) {
            ps.setLong(1, codigo);
            ResultSet rs = ps.executeQuery();
            if (rs.next()) {
                usuario = new UsuarioTO();
                popularUsuario(usuario, rs);
            }
        } catch (SQLException e) {
            System.out.println("Erro na consulta: " + e.getMessage());
        } finally {
            ConnectionFactory.closeConnection();
        }
        return usuario;
    }

    public UsuarioTO save(UsuarioTO usuario) {
        String sql = "INSERT INTO ddd_usuario(cpf, nome, senha, email, telefone, data_nascimento, tipo_usuario, cpf_paciente, cpf_cuidador, paciente_editar) VALUES(?,?,?,?,?,?,?,?,?,?)";

        try (PreparedStatement ps = ConnectionFactory.getConnection().prepareStatement(sql)) {
            ps.setString(1, usuario.getCpf());
            ps.setString(2, usuario.getNome());
            ps.setString(3, usuario.getSenha());
            ps.setString(4, usuario.getEmail());
            ps.setString(5, usuario.getTelefone());
            ps.setDate(6, Date.valueOf(usuario.getDataNascimento()));
            ps.setString(7, usuario.getTipoUsuario());
            ps.setString(8, usuario.getCpfPaciente());
            ps.setString(9, usuario.getCpfCuidador());
            ps.setBoolean(10, usuario.isPacienteEditar());
            if (ps.executeUpdate() > 0) {
                return usuario;
            }
        } catch (SQLException e) {
            System.out.println("Erro ao salvar: " + e.getMessage());
        } finally {
            ConnectionFactory.closeConnection();
        }
        return null;
    }

    public boolean delete(Long codigo) {
        String sql = "DELETE FROM ddd_usuario WHERE id_user = ?";
        try (PreparedStatement ps = ConnectionFactory.getConnection().prepareStatement(sql)) {
            ps.setLong(1, codigo);
            return ps.executeUpdate() > 0;
        } catch (SQLException e) {
            System.out.println("Erro ao excluir: " + e.getMessage());
        } finally {
            ConnectionFactory.closeConnection();
        }
        return false;
    }

    public UsuarioTO update(UsuarioTO usuario) {
        String sql = "UPDATE ddd_usuario SET cpf=?, nome=?, senha=?, email=?, telefone=?, data_nascimento=?, tipo_usuario=?, cpf_paciente=?, cpf_cuidador=?, paciente_editar=? WHERE id_user=?";
        try (PreparedStatement ps = ConnectionFactory.getConnection().prepareStatement(sql)) {
            ps.setString(1, usuario.getCpf());
            ps.setString(2, usuario.getNome());
            ps.setString(3, usuario.getSenha());
            ps.setString(4, usuario.getEmail());
            ps.setString(5, usuario.getTelefone());
            ps.setDate(6, Date.valueOf(usuario.getDataNascimento()));
            ps.setString(7, usuario.getTipoUsuario());
            ps.setString(8, usuario.getCpfPaciente());
            ps.setString(9, usuario.getCpfCuidador());
            ps.setBoolean(10, usuario.isPacienteEditar());
            ps.setLong(11, usuario.getIdUser());

            if (ps.executeUpdate() > 0) {
                return usuario;
            }
        } catch (SQLException e) {
            System.out.println("Erro ao atualizar: " + e.getMessage());
        } finally {
            ConnectionFactory.closeConnection();
        }
        return null;
    }
}
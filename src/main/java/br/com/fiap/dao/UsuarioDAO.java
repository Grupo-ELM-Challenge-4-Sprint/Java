package br.com.fiap.dao;

import br.com.fiap.to.UsuarioTO;

import java.sql.Date;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.ArrayList;

/**
 * Classe responsável pelo acesso e manipulação dos dados da entidade {@link UsuarioTO}
 * na base de dados SQL.
 * <p>
 * Esta classe implementa operações CRUD (Create, Read, Update, Delete) para a tabela
 * <b>ddd_usuario</b>.
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
public class UsuarioDAO {

    /**
     * Recupera todos os registros da tabela <b>ddd_usuario</b>.
     *
     * @return retorna uma lista de {@link UsuarioTO} com todos os usuários encontrados,
     * ou {@code null} caso ocorra um erro na consulta.
     */
    public ArrayList<UsuarioTO> findAll() {
        ArrayList<UsuarioTO> usuarios = new ArrayList<UsuarioTO>();
        String sql = "SELECT * FROM ddd_usuario ORDER BY id_user";
        try(PreparedStatement ps = ConnectionFactory.getConnection().prepareStatement(sql))
        {
            ResultSet rs = ps.executeQuery();
            if (rs != null) {
                while (rs.next()) {
                    UsuarioTO usuario = new UsuarioTO();
                    usuario.setIdUser(rs.getLong("id_user"));
                    usuario.setCpf(rs.getString("cpf"));
                    usuario.setNome(rs.getString("nome"));
                    usuario.setSenha(rs.getString("senha"));
                    usuario.setEmail(rs.getString("email"));
                    usuario.setTelefone(rs.getString("telefone"));
                    usuario.setDataNascimento(rs.getDate("data_nascimento").toLocalDate());
                    usuario.setTipoUsuario(rs.getString("tipo"));
                    usuario.setCpfCuidador(rs.getString("cpf_cuidador"));
                    usuario.setCpfPaciente(rs.getString("cpf_paciente"));
                    usuario.setPacienteEditar(rs.getBoolean("paciente_editar"));
                    usuarios.add(usuario);
                }
            } else {
                return null;
            }
        } catch (SQLException e) {
            System.out.println("Erro na consulta: " + e.getMessage());
        } finally {
            ConnectionFactory.closeConnection();
        }
        return usuarios;
    }

    /**
     * Busca um usuário pelo seu identificador único.
     *
     * @param codigo o código (ID) do usuário a ser buscado.
     * @return um objeto {@link UsuarioTO} correspondente ao ID informado,
     * ou {@code null} se nenhum registro for encontrado.
     */
    public UsuarioTO findByCodigo(Long codigo) {
        UsuarioTO usuario = new UsuarioTO();
        String sql = "SELECT * FROM ddd_usuario WHERE id_user = ?";
        try(PreparedStatement ps = ConnectionFactory.getConnection().prepareStatement(sql))
        {
            ps.setLong(1, codigo);
            ResultSet rs = ps.executeQuery();
            if (rs.next()) {
                usuario.setIdUser(rs.getLong("id_user"));
                usuario.setCpf(rs.getString("cpf"));
                usuario.setNome(rs.getString("nome"));
                usuario.setSenha(rs.getString("senha"));
                usuario.setEmail(rs.getString("email"));
                usuario.setTelefone(rs.getString("telefone"));
                usuario.setDataNascimento(rs.getDate("data_nascimento").toLocalDate());
                usuario.setTipoUsuario(rs.getString("tipo"));
                usuario.setCpfCuidador(rs.getString("cpf_cuidador"));
                usuario.setCpfPaciente(rs.getString("cpf_paciente"));
                usuario.setPacienteEditar(rs.getBoolean("paciente_editar"));
            } else {
                return null;
            }
        } catch (SQLException e) {
            System.out.println("Erro na consulta: " + e.getMessage());
        } finally {
            ConnectionFactory.closeConnection();
        }
        return usuario;
    }

    /**
     * Busca um usuário pelo seu CPF.
     *
     * @param cpf o CPF do usuário.
     * @return um objeto {@link UsuarioTO} correspondente ao CPF informado,
     * ou {@code null} se nenhum registro for encontrado.
     */
    public UsuarioTO findByCpf(String cpf) {
        UsuarioTO usuario = new UsuarioTO();
        String sql = "SELECT * FROM ddd_usuario WHERE cpf = ?";
        try(PreparedStatement ps = ConnectionFactory.getConnection().prepareStatement(sql))
        {
            ps.setString(1, cpf);
            ResultSet rs = ps.executeQuery();
            if (rs.next()) {
                usuario.setIdUser(rs.getLong("id_user"));
                usuario.setCpf(rs.getString("cpf"));
                usuario.setNome(rs.getString("nome"));
                usuario.setSenha(rs.getString("senha"));
                usuario.setEmail(rs.getString("email"));
                usuario.setTelefone(rs.getString("telefone"));
                usuario.setDataNascimento(rs.getDate("data_nascimento").toLocalDate());
                usuario.setTipoUsuario(rs.getString("tipo"));
                usuario.setCpfCuidador(rs.getString("cpf_cuidador"));
                usuario.setCpfPaciente(rs.getString("cpf_paciente"));
                usuario.setPacienteEditar(rs.getBoolean("paciente_editar"));
            } else {
                return null;
            }
        } catch (SQLException e) {
            System.out.println("Erro na consulta: " + e.getMessage());
        } finally {
            ConnectionFactory.closeConnection();
        }
        return usuario;
    }

    /**
     * Insere um novo registro de usuário na tabela <b>ddd_usuario</b>.
     *
     * @param usuario o objeto {@link UsuarioTO} contendo os dados a serem inseridos.
     * @return o próprio {@link UsuarioTO} se o registro for inserido com sucesso,
     * ou {@code null} em caso de erro.
     */
    public UsuarioTO save(UsuarioTO usuario) {
        String sql = "INSERT INTO ddd_usuario(cpf, nome, senha, email, telefone, data_nascimento, tipo, cpf_cuidador, cpf_paciente, paciente_editar) VALUES(?,?,?,?,?,?,?,?,?)";

        try (PreparedStatement ps = ConnectionFactory.getConnection().prepareStatement(sql))
        {
            ps.setString(1, usuario.getCpf());
            ps.setString(2, usuario.getNome());
            ps.setString(3, usuario.getSenha());
            ps.setString(4, usuario.getEmail());
            ps.setString(5, usuario.getTelefone());
            ps.setDate(6, Date.valueOf(usuario.getDataNascimento()));
            ps.setString(7, usuario.getTipoUsuario());
            ps.setString(8, usuario.getCpfCuidador());
            ps.setString(9, usuario.getCpfPaciente());
            ps.setBoolean(10, usuario.getPacienteEditar());
            if (ps.executeUpdate() > 0) {
                return usuario;
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
     * Exclui um registro de usuário pelo seu código.
     *
     * @param codigo o identificador do usuário a ser excluído.
     * @return {@code true} se o registro for excluído com sucesso, {@code false} caso contrário.
     */
    public boolean delete(Long codigo) {
        String sql = "delete from ddd_usuario where id_user = ?";
        try (PreparedStatement ps = ConnectionFactory.getConnection().prepareStatement(sql)){
            ps.setLong(1, codigo);
            return ps.executeUpdate() > 0;
        } catch (SQLException e) {
            System.out.println("Erro ao excluir: " + e.getMessage());
        } finally {
            ConnectionFactory.closeConnection();
        }
        return false;
    }

    /**
     * Atualiza os dados de um usuário existente.
     *
     * @param usuario o objeto {@link UsuarioTO} com as novas informações do usuário.
     * @return o {@link UsuarioTO} atualizado, ou {@code null} se ocorrer algum erro.
     */
    public UsuarioTO update(UsuarioTO usuario) {
        String sql = "update ddd_usuario set cpf=?, nome=?, senha=?, email=?, telefone=?, data_nascimento=?, tipo=?, cpf_cuidador=?, cpf_paciente=?, paciente_editar=? where id_user=?";
        try (PreparedStatement ps = ConnectionFactory.getConnection().prepareStatement(sql)){
            ps.setString(1, usuario.getCpf());
            ps.setString(2, usuario.getNome());
            ps.setString(3, usuario.getSenha());
            ps.setString(4, usuario.getEmail());
            ps.setString(5, usuario.getTelefone());
            ps.setDate(6, Date.valueOf(usuario.getDataNascimento()));
            ps.setString(7, usuario.getTipoUsuario());
            ps.setString(8, usuario.getCpfCuidador());
            ps.setString(9, usuario.getCpfPaciente());
            ps.setBoolean(10, usuario.getPacienteEditar());
            ps.setLong(11, usuario.getIdUser());

            if (ps.executeUpdate() > 0) {
                return usuario;
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

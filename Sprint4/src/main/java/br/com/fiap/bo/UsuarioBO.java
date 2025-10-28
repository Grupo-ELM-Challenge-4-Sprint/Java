package br.com.fiap.bo;

import br.com.fiap.dao.UsuarioDAO;
import br.com.fiap.to.UsuarioTO;

import java.time.LocalDate;
import java.util.ArrayList;

public class UsuarioBO {
    private UsuarioDAO usuarioDAO;

    public ArrayList<UsuarioTO> findAll() {
        usuarioDAO = new UsuarioDAO();
        // aqui se implementa a regra de negócios
        return usuarioDAO.findAll();
    }

    public UsuarioTO findByCodigo(Long codigo) {
        usuarioDAO = new UsuarioDAO();
        // aqui se implementa a regra de negócios
        return usuarioDAO.findByCodigo(codigo);
    }

    public UsuarioTO save (UsuarioTO usuario) {
        usuarioDAO = new UsuarioDAO();

        String tel = usuario.getTelefone().replaceAll("[^0-9\\s-]", ""); // Deixa apenas os números e espaços do telefone
        String cpf = usuario.getCpf().replaceAll("\\D", "").replaceAll(" ", ""); // Deixa apenas os números do cpf
        if (cpf.matches("\\d{11}") && usuario.getNome().matches("^[A-Za-zÀ-ÖØ-öø-ÿ]+(?:[\\s'-][A-Za-zÀ-ÖØ-öø-ÿ]+)*$") && usuario.getEmail().matches("^[A-Za-z0-9._%+-]+@[A-Za-z0-9.-]+\\.[A-Za-z]{2,}$") && usuario.getDataNascimento().isBefore(LocalDate.now()) && usuario.getTipoUsuario().equalsIgnoreCase("paciente") || usuario.getTipoUsuario().equalsIgnoreCase("cuidador")) {
            usuario.setCpf(cpf);
            usuario.setTelefone(tel);
            return usuarioDAO.save(usuario);
        } else {
            return null;
        }
    }

    public boolean delete(Long codigo) {
        usuarioDAO = new UsuarioDAO();
        //Aqui se implementa a regra de negócios
        return usuarioDAO.delete(codigo);
    }

    public UsuarioTO update(UsuarioTO usuario) {
        usuarioDAO = new UsuarioDAO();

        String tel = usuario.getTelefone().replaceAll("\\D", ""); // Deixa apenas os números do telefone
        String cpf = usuario.getCpf().replaceAll("\\D", "").replaceAll(" ", ""); // Deixa apenas os números do cpf
        if (cpf.matches("\\d{11}") && usuario.getNome().matches("^[A-Za-zÀ-ÖØ-öø-ÿ]+(?:[\\s'-][A-Za-zÀ-ÖØ-öø-ÿ]+)*$") && usuario.getEmail().matches("^[A-Za-z0-9._%+-]+@[A-Za-z0-9.-]+\\.[A-Za-z]{2,}$") && tel.matches("^\\d{10,11}$") && usuario.getDataNascimento().isBefore(LocalDate.now()) && usuario.getTipoUsuario().equalsIgnoreCase("paciente") || usuario.getTipoUsuario().equalsIgnoreCase("cuidador")) {
            usuario.setCpf(cpf);
            usuario.setTelefone(tel);
            return usuarioDAO.update(usuario);
        } else {
            return null;
        }
    }
}

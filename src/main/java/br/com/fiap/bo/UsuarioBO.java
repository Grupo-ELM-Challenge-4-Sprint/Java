package br.com.fiap.bo;

import br.com.fiap.dao.UsuarioDAO;
import br.com.fiap.to.UsuarioTO;

import java.util.ArrayList;

public class UsuarioBO {
    private UsuarioDAO usuarioDAO;

    public ArrayList<UsuarioTO> findAll() {
        usuarioDAO = new UsuarioDAO();
        return usuarioDAO.findAll();
    }

    public UsuarioTO findByCodigo(Long codigo) {
        usuarioDAO = new UsuarioDAO();
        return usuarioDAO.findByCodigo(codigo);
    }
    
    public UsuarioTO findByCpf(String cpf) {
        usuarioDAO = new UsuarioDAO();
        return usuarioDAO.findByCpf(cpf);
    }

    public UsuarioTO save(UsuarioTO usuario) {
        usuarioDAO = new UsuarioDAO();
        // Validações podem ser adicionadas aqui
        return usuarioDAO.save(usuario);
    }

    public boolean delete(Long codigo) {
        usuarioDAO = new UsuarioDAO();
        return usuarioDAO.delete(codigo);
    }

    public UsuarioTO update(UsuarioTO usuario) {
        usuarioDAO = new UsuarioDAO();
        // Validações podem ser adicionadas aqui
        return usuarioDAO.update(usuario);
    }
}
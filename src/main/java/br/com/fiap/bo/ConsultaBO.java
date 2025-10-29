package br.com.fiap.bo;

import br.com.fiap.dao.ConsultaDAO;
import br.com.fiap.to.ConsultaTO;

import java.util.ArrayList;

public class ConsultaBO {
    private ConsultaDAO consultaDAO;

    public ArrayList<ConsultaTO> findAll() {
        consultaDAO = new ConsultaDAO();
        return consultaDAO.findAll();
    }

    public ConsultaTO findByCodigo(Long codigo) {
        consultaDAO = new ConsultaDAO();
        return consultaDAO.findByCodigo(codigo);
    }

    public ConsultaTO save(ConsultaTO consulta) {
        consultaDAO = new ConsultaDAO();
        // A validação de nomeCuidador foi removida.
        return consultaDAO.save(consulta);
    }

    public boolean delete(Long codigo) {
        consultaDAO = new ConsultaDAO();
        return consultaDAO.delete(codigo);
    }

    public ConsultaTO update(ConsultaTO consulta) {
        consultaDAO = new ConsultaDAO();
        // A validação de nomeCuidador foi removida.
        return consultaDAO.update(consulta);
    }

    public ArrayList<ConsultaTO> findAllByUserId(Long userId) {
        consultaDAO = new ConsultaDAO();
        // Aqui você pode adicionar regras de negócio se necessário antes de chamar o DAO
        return consultaDAO.findAllByUserId(userId);
    }
}
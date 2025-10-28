package br.com.fiap.bo;

import br.com.fiap.dao.ConsultaDAO;
import br.com.fiap.to.ConsultaTO;

import java.util.ArrayList;

public class ConsultaBO {
    private ConsultaDAO consultaDAO;

    public ArrayList<ConsultaTO> findAll() {
        consultaDAO = new ConsultaDAO();
        // aqui se implementa a regra de negócios
        return consultaDAO.findAll();
    }

    public ConsultaTO findByCodigo(Long codigo) {
        consultaDAO = new ConsultaDAO();
        // aqui se implementa a regra de negócios
        return consultaDAO.findByCodigo(codigo);
    }

    public ConsultaTO save (ConsultaTO consulta) {
        consultaDAO = new ConsultaDAO();

        if (consulta.getNomeCuidador().matches("^[A-Za-zÀ-ÖØ-öø-ÿ0-9.,\\s\\-/]+$")) {
            return consultaDAO.save(consulta);
        } else {
            return null;
        }
    }

    public boolean delete(Long codigo) {
        consultaDAO = new ConsultaDAO();
        //Aqui se implementa a regra de negócios
        return consultaDAO.delete(codigo);
    }

    public ConsultaTO update(ConsultaTO consulta) {
        consultaDAO = new ConsultaDAO();
        if (consulta.getNomeCuidador().matches("^[A-Za-zÀ-ÖØ-öø-ÿ0-9.,\\s\\-/]+$")) {
            return consultaDAO.update(consulta);
        } else {
            return null;
        }
    }
}

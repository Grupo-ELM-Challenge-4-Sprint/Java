package br.com.fiap.bo;

import br.com.fiap.dao.ReceitaDAO;
import br.com.fiap.to.ReceitaTO;

import java.util.ArrayList;

public class ReceitaBO {
    private ReceitaDAO receitaDAO;

    public ArrayList<ReceitaTO> findAll() {
        receitaDAO = new ReceitaDAO();
        // aqui se implementa a regra de negócios
        return receitaDAO.findAll();
    }

    public ReceitaTO findByCodigo(Long codigo) {
        receitaDAO = new ReceitaDAO();
        // aqui se implementa a regra de negócios
        return receitaDAO.findByCodigo(codigo);
    }

    public ReceitaTO save (ReceitaTO receita) {
        receitaDAO = new ReceitaDAO();
        // Regras de negócio aqui, se necessário
        return receitaDAO.save(receita);
    }

    public boolean delete(Long codigo) {
        receitaDAO = new ReceitaDAO();
        //Aqui se implementa a regra de negócios
        return receitaDAO.delete(codigo);
    }

    public ReceitaTO update(ReceitaTO receita) {
        receitaDAO = new ReceitaDAO();
        // Regras de negócio aqui, se necessário
        return receitaDAO.update(receita);
    }

    public ArrayList<ReceitaTO> findAllByUserId(Long userId) {
        receitaDAO = new ReceitaDAO();
        // Regras de negócio aqui, se necessário
        return receitaDAO.findAllByUserId(userId);
    }
}

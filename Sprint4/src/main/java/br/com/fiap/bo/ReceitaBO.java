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

        if (receita.getNome().matches("^[A-Za-zÀ-ÖØ-öø-ÿ0-9.,\\s\\-/]+$") && receita.getStatus().equalsIgnoreCase("Ativo") || receita.getStatus().equalsIgnoreCase("Inativo")) {
            return receitaDAO.save(receita);
        } else {
            return null;
        }
    }

    public boolean delete(Long codigo) {
        receitaDAO = new ReceitaDAO();
        //Aqui se implementa a regra de negócios
        return receitaDAO.delete(codigo);
    }

    public ReceitaTO update(ReceitaTO receita) {
        receitaDAO = new ReceitaDAO();

        if (receita.getNome().matches("^[A-Za-zÀ-ÖØ-öø-ÿ0-9.,\\s\\-/]+$") && receita.getStatus().equalsIgnoreCase("Ativo") || receita.getStatus().equalsIgnoreCase("Inativo")) {
            return receitaDAO.update(receita);
        } else {
            return null;
        }
    }
}

package br.com.fiap.bo;

import br.com.fiap.dao.ReceitaDAO;
import br.com.fiap.to.ReceitaTO;

import java.util.ArrayList;

/**
 * <p>Classe de negócios (Business Object) que gerencia operações relacionadas a receitas.</p>
 * <p>Utiliza a {@link br.com.fiap.dao.ReceitaDAO} para acessar o banco de dados.</p>
 *
 * @author Lucas Barros Gouveia
 * @author Enzo Okuizumi Miranda de Souza
 * @author Milton Jakson de Souza Marcelino
 * @version 1.0
 * @since 21.0.7
 */
public class ReceitaBO {
    private ReceitaDAO receitaDAO;

    /**
     * Retorna todas as receitas cadastradas no banco de dados.
     *
     * @return ArrayList de {@link ReceitaTO} contendo todas as receitas.
     */
    public ArrayList<ReceitaTO> findAll() {
        receitaDAO = new ReceitaDAO();
        return receitaDAO.findAll();
    }

    /**
     * Busca uma receita pelo seu código (ID).
     *
     * @param codigo Código (ID) da receita.
     * @return {@link ReceitaTO} correspondente ao código informado ou null se não encontrada.
     */
    public ReceitaTO findByCodigo(Long codigo) {
        receitaDAO = new ReceitaDAO();
        return receitaDAO.findByCodigo(codigo);
    }

    /**
     * Busca uma consulta pelo seu código do usuario (userID).
     *
     * @param userId Código (ID) da consulta.
     * @return {@link ReceitaTO} correspondente ao código informado ou null se não encontrada.
     */
    public ReceitaTO findAllByUserId(Long userId) {
        receitaDAO = new ReceitaDAO();
        return receitaDAO.findAllByUserId(userId);
    }

    /**
     * Salva uma nova receita no banco de dados.
     *
     * @param receita Objeto {@link ReceitaTO} contendo os dados da receita a ser cadastrada.
     * @return {@link ReceitaTO} salvo, ou null se não foi possível salvar.
     */
    public ReceitaTO save(ReceitaTO receita) {
        receitaDAO = new ReceitaDAO();
        return receitaDAO.save(receita);
    }

    /**
     * Exclui uma receita do banco de dados pelo seu código (ID).
     *
     * @param codigo Código (ID) da receita a ser excluída.
     * @return true se a exclusão foi bem-sucedida, false caso contrário.
     */
    public boolean delete(Long codigo) {
        receitaDAO = new ReceitaDAO();
        return receitaDAO.delete(codigo);
    }

    /**
     * Atualiza os dados de uma receita existente no banco de dados.
     *
     * @param receita Objeto {@link ReceitaTO} contendo os dados atualizados da receita.
     * @return {@link ReceitaTO} atualizado, ou null se não foi possível atualizar.
     */
    public ReceitaTO update(ReceitaTO receita) {
        receitaDAO = new ReceitaDAO();
        return receitaDAO.update(receita);
    }
}

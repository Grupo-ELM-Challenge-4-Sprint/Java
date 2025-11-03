package br.com.fiap.bo;

import br.com.fiap.dao.ConsultaDAO;
import br.com.fiap.to.ConsultaTO;

import java.util.ArrayList;

/**
 * <p>Classe de negócios que gerencia operações relacionadas a consultas.</p>
 * <p>Utiliza a {@link br.com.fiap.dao.ConsultaDAO} para acessar o banco de dados.</p>
 *
 * @author Lucas Barros Gouveia
 * @author Enzo Okuizumi Miranda de Souza
 * @author Milton Jakson de Souza Marcelino
 * @version 1.0
 * @since 21.0.7
 */
public class ConsultaBO {
    private ConsultaDAO consultaDAO;

    /**
     * Retorna todas as consultas cadastradas no banco de dados.
     *
     * @return ArrayList de {@link ConsultaTO} contendo todas as consultas.
     */
    public ArrayList<ConsultaTO> findAll() {
        consultaDAO = new ConsultaDAO();
        return consultaDAO.findAll();
    }

    /**
     * Busca uma consulta pelo seu código (ID).
     *
     * @param codigo Código (ID) da consulta.
     * @return {@link ConsultaTO} correspondente ao código informado ou null se não encontrada.
     */
    public ConsultaTO findByCodigo(Long codigo) {
        consultaDAO = new ConsultaDAO();
        return consultaDAO.findByCodigo(codigo);
    }

    /**
     * Salva uma nova consulta no banco de dados.
     *
     * @param consulta Objeto {@link ConsultaTO} contendo os dados da consulta a ser cadastrada.
     * @return {@link ConsultaTO} salvo, ou null se não foi possível salvar.
     */
    public ConsultaTO save(ConsultaTO consulta) {
        consultaDAO = new ConsultaDAO();
        return consultaDAO.save(consulta);
    }

    /**
     * Exclui uma consulta do banco de dados pelo seu código (ID).
     *
     * @param codigo Código (ID) da consulta a ser excluída.
     * @return true se a exclusão foi bem-sucedida, false caso contrário.
     */
    public boolean delete(Long codigo) {
        consultaDAO = new ConsultaDAO();
        return consultaDAO.delete(codigo);
    }

    /**
     * Atualiza os dados de uma consulta existente no banco de dados.
     *
     * @param consulta Objeto {@link ConsultaTO} contendo os dados atualizados da consulta.
     * @return {@link ConsultaTO} atualizado, ou null se não foi possível atualizar.
     */
    public ConsultaTO update(ConsultaTO consulta) {
        consultaDAO = new ConsultaDAO();
        return consultaDAO.update(consulta);
    }
}

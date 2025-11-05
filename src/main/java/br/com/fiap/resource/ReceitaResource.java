package br.com.fiap.resource;

import br.com.fiap.bo.ReceitaBO;
import br.com.fiap.dao.ConsultaDAO;
import br.com.fiap.dao.ReceitaDAO;
import br.com.fiap.to.ConsultaTO;
import br.com.fiap.to.ReceitaTO;
import jakarta.validation.Valid;
import jakarta.ws.rs.*;
import jakarta.ws.rs.core.MediaType;
import jakarta.ws.rs.core.Response;

import java.sql.SQLException;
import java.util.ArrayList;

/**
 * <p>Classe Resource que expõe os endpoints REST para operações relacionadas a receitas.</p>
 * <p>Utiliza a {@link br.com.fiap.bo.ReceitaBO} para manipulação de dados.</p>
 *
 * Endpoints disponíveis:
 * <ul>
 *     <li>GET /receita - Retorna todas as receitas</li>
 *     <li>GET /receita/usuario/{idUser} - Retorna todas as receitas de um usuário</li>
 *     <li>GET /receita/{id_receita} - Retorna receita pelo ID</li>
 *     <li>POST /receita - Cadastra uma nova receita</li>
 *     <li>PUT /receita/{id_receita} - Atualiza receita existente</li>
 *     <li>DELETE /receita/{id_receita} - Remove receita pelo ID</li>
 * </ul>
 *
 * @author Lucas Barros Gouveia
 * @author Enzo Okuizumi Miranda de Souza
 * @author Milton Jakson de Souza Marcelino
 * @version 1.0
 * @since 21.0.7
 */
@Path("/receita")
public class ReceitaResource {
    private ReceitaBO receitaBO = new ReceitaBO();

    /**
     * Retorna todas as receitas.
     *
     * @return Response com status 200 (OK) e lista de {@link ReceitaTO}, ou 404 se não houver dados.
     */
    @GET
    @Produces(MediaType.APPLICATION_JSON)
    public Response findAll() throws SQLException {
        ArrayList<ReceitaTO> resultado = receitaBO.findAll();
        Response.ResponseBuilder response = (resultado != null) ? Response.ok() : Response.status(404);
        response.entity(resultado);
        return response.build();
    }

    /**
     * Busca todas as receitas associadas a um determinado usuário pelo ID.
     *
     * @param idUser o identificador único do usuário (ID) cujas receitas devem ser buscadas.
     * @return uma resposta HTTP contendo uma lista de objetos {@link ReceitaTO} correspondente ao ID de usuário;
     * se o usuário não tiver receitas, retorna uma lista vazia com o status HTTP 200.
     */
    @GET
    @Path("/usuario/{idUser}")
    @Produces(MediaType.APPLICATION_JSON)
    public Response findAllByUserId(@PathParam("idUser") Long idUser) throws SQLException {
        ArrayList<ReceitaTO> resultado = receitaBO.findAllByUserId(idUser);
        Response.ResponseBuilder response = (resultado != null) ? Response.ok() : Response.status(404);
        response.entity(resultado);
        return response.build();
    }

    /**
     * Busca receita pelo ID.
     *
     * @param codigo ID da receita.
     * @return Response com status 200 (OK) e {@link ReceitaTO}, ou 404 se não encontrado.
     */
    @GET
    @Path("/{id_receita}")
    @Produces(MediaType.APPLICATION_JSON)
    public Response findByCodigo(@PathParam("id_receita") Long codigo) throws SQLException {
        ReceitaTO resultado = receitaBO.findByCodigo(codigo);
        Response.ResponseBuilder response = (resultado != null) ? Response.ok() : Response.status(404);
        response.entity(resultado);
        return response.build();
    }

    /**
     * Cadastra uma nova receita.
     *
     * @param receita {@link ReceitaTO} com os dados da receita.
     * @return Response com status 201 (CREATED) e {@link ReceitaTO}, ou 400 se falhar.
     */
    @POST
    @Consumes(MediaType.APPLICATION_JSON)
    public Response save(@Valid ReceitaTO receita) {
        ReceitaTO resultado = receitaBO.save(receita);
        Response.ResponseBuilder response = (resultado != null) ? Response.created(null) : Response.status(400);
        response.entity(resultado);
        return response.build();
    }

    /**
     * Atualiza uma receita existente.
     *
     * @param receita {@link ReceitaTO} com dados atualizados.
     * @param idReceita ID da receita a ser atualizada.
     * @return Response com status 201 (CREATED) e {@link ReceitaTO}, ou 400 se falhar.
     */
    @PUT
    @Consumes(MediaType.APPLICATION_JSON)
    @Path("/{id_receita}")
    public Response update(@Valid ReceitaTO receita, @PathParam("id_receita") Long idReceita) {
        receita.setIdReceita(idReceita);
        ReceitaTO resultado = receitaBO.update(receita);
        Response.ResponseBuilder response = (resultado != null) ? Response.created(null) : Response.status(400);
        response.entity(resultado);
        return response.build();
    }

    /**
     * Remove receita pelo ID.
     *
     * @param codigo ID da receita a ser removida.
     * @return Response com status 204 (NO CONTENT) se removido, ou 404 se não encontrado.
     */
    @DELETE
    @Path("/{id_receita}")
    public Response delete(@PathParam("id_receita") Long codigo) {
        Response.ResponseBuilder response = (receitaBO.delete(codigo)) ? Response.status(204) : Response.status(404);
        return response.build();
    }
}

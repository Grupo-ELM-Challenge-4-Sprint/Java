package br.com.fiap.resource;

import br.com.fiap.bo.ConsultaBO;
import br.com.fiap.to.ConsultaTO;
import jakarta.validation.Valid;
import jakarta.ws.rs.*;
import jakarta.ws.rs.core.MediaType;
import jakarta.ws.rs.core.Response;

import java.util.ArrayList;

/**
 * <p>Classe Resource que expõe os endpoints REST para operações relacionadas a consultas.</p>
 * <p>Utiliza a {@link br.com.fiap.bo.ConsultaBO} para manipulação de dados.</p>
 *
 * Endpoints disponíveis:
 * <ul>
 *     <li>GET /consulta - Retorna todas as consultas</li>
 *     <li>GET /consulta/{id_consulta} - Retorna consulta pelo ID</li>
 *     <li>POST /consulta - Cadastra uma nova consulta</li>
 *     <li>PUT /consulta/{id_consulta} - Atualiza consulta existente</li>
 *     <li>DELETE /consulta/{id_consulta} - Remove consulta pelo ID</li>
 * </ul>
 *
 * @author Lucas Barros Gouveia
 * @author Enzo Okuizumi Miranda de Souza
 * @author Milton Jakson de Souza Marcelino
 * @version 1.0
 * @since 21.0.7
 */
@Path("/consulta")
public class ConsultaResource {
    private ConsultaBO consultaBO = new ConsultaBO();

    /**
     * Retorna todas as consultas.
     *
     * @return Response com status 200 (OK) e lista de {@link ConsultaTO}, ou 404 se não houver dados.
     */
    @GET
    @Produces(MediaType.APPLICATION_JSON)
    public Response findAll() {
        ArrayList<ConsultaTO> resultado = consultaBO.findAll();
        Response.ResponseBuilder response = (resultado != null) ? Response.ok() : Response.status(404);
        response.entity(resultado);
        return response.build();
    }

    /**
     * Busca todas as consultas associadas a um usuário pelo ID.
     *
     * @param idUser o identificador único do usuário (ID) cujas consultas devem ser buscadas.
     * @return uma resposta HTTP contendo uma lista de objetos {@link ConsultaTO} correspondente ao ID de usuário;
     * se o usuário não tiver consultas, retorna uma lista vazia com o status HTTP 200.
     */
    @GET
    @Path("/usuario/{idUser}")
    @Produces(MediaType.APPLICATION_JSON)
    public Response findAllByUserId(@PathParam("userId") Long idUser) {
        ArrayList<ConsultaTO> resultado = consultaBO.findAllByUserId(idUser);
        Response.ResponseBuilder response = (resultado != null) ? Response.ok() : Response.status(404);
        response.entity(resultado);
        return response.build();
    }


    /**
     * Busca consulta pelo ID.
     *
     * @param codigo ID da consulta.
     * @return Response com status 200 (OK) e {@link ConsultaTO}, ou 404 se não encontrado.
     */
    @GET
    @Path("/{id_consulta}")
    @Produces(MediaType.APPLICATION_JSON)
    public Response findByCodigo(@PathParam("id_consulta") Long codigo) {
        ConsultaTO resultado = consultaBO.findByCodigo(codigo);
        Response.ResponseBuilder response = (resultado != null) ? Response.ok() : Response.status(404);
        response.entity(resultado);
        return response.build();
    }

    /**
     * Cadastra uma nova consulta.
     *
     * @param consulta {@link ConsultaTO} com os dados da consulta.
     * @return Response com status 201 (CREATED) e {@link ConsultaTO}, ou 400 se falhar.
     */
    @POST
    @Consumes(MediaType.APPLICATION_JSON)
    public Response save(@Valid ConsultaTO consulta) {
        ConsultaTO resultado = consultaBO.save(consulta);
        Response.ResponseBuilder response = (resultado != null) ? Response.created(null) : Response.status(400);
        response.entity(resultado);
        return response.build();
    }

    /**
     * Atualiza uma consulta existente.
     *
     * @param consulta {@link ConsultaTO} com dados atualizados.
     * @param idConsulta ID da consulta a ser atualizada.
     * @return Response com status 201 (CREATED) e {@link ConsultaTO}, ou 400 se falhar.
     */
    @PUT
    @Consumes(MediaType.APPLICATION_JSON)
    @Path("/{id_consulta}")
    public Response update(@Valid ConsultaTO consulta, @PathParam("id_consulta") Long idConsulta) {
        consulta.setIdConsulta(idConsulta);
        ConsultaTO resultado = consultaBO.update(consulta);
        Response.ResponseBuilder response = (resultado != null) ? Response.created(null) : Response.status(400);
        response.entity(resultado);
        return response.build();
    }

    /**
     * Remove consulta pelo ID.
     *
     * @param codigo ID da consulta a ser removida.
     * @return Response com status 204 (NO CONTENT) se removido, ou 404 se não encontrado.
     */
    @DELETE
    @Path("/{id_consulta}")
    public Response delete(@PathParam("id_consulta") Long codigo) {
        Response.ResponseBuilder response = (consultaBO.delete(codigo)) ? Response.status(204) : Response.status(404);
        return response.build();
    }
}

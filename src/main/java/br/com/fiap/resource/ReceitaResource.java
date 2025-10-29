package br.com.fiap.resource;

import br.com.fiap.bo.ReceitaBO;
import br.com.fiap.to.ReceitaTO;
import jakarta.validation.Valid;
import jakarta.ws.rs.*;
import jakarta.ws.rs.core.MediaType;
import jakarta.ws.rs.core.Response;

import java.util.ArrayList;

@Path("/receita")
public class ReceitaResource {
    private ReceitaBO receitaBO = new ReceitaBO();

    @GET
    @Produces(MediaType.APPLICATION_JSON)
    public Response findAll() {
        ArrayList<ReceitaTO> resultado = receitaBO.findAll();
        Response.ResponseBuilder response = null;
        if (resultado != null) {
            response = Response.ok();  // 200 - OK
        } else {
            response = Response.status(404);  // 404 - NOT FOUND
        }
        response.entity(resultado);
        return response.build();
    }

    @GET
    @Path("/usuario/{userId}") // Novo endpoint
    @Produces(MediaType.APPLICATION_JSON)
    public Response findAllByUserId(@PathParam("userId") Long userId) {
        ArrayList<ReceitaTO> resultado = receitaBO.findAllByUserId(userId);
        // Retorna OK mesmo se a lista estiver vazia
        return Response.ok(resultado).build();
    }

    @GET
    @Path("/{id_receita}")
    @Produces(MediaType.APPLICATION_JSON)
    public Response findByCodigo(@PathParam("id_receita") Long codigo) {
        ReceitaTO resultado = receitaBO.findByCodigo(codigo);
        Response.ResponseBuilder response = null;
        if (resultado != null) {
            response = Response.ok();  // 200 (OK)
        } else {
            response = Response.status(404);  // 404 (NOT FOUND)
        }
        response.entity(resultado);
        return response.build();
    }

    @POST
    @Consumes(MediaType.APPLICATION_JSON)
    public Response save(@Valid ReceitaTO receita) {
        ReceitaTO resultado = receitaBO.save(receita);
        Response.ResponseBuilder response = null;
        if (resultado != null) {
            response = Response.created(null);  // 201 - CREATED
        } else {
            response = Response.status(400);  // 400 - BAD REQUEST
        }
        response.entity(resultado);
        return response.build();
    }

    @DELETE
    @Path("/{id_receita}")
    public Response delete(@PathParam("id_receita") Long codigo) {
        Response.ResponseBuilder response = null;
        if (receitaBO.delete(codigo)) {
            response = Response.status(204); // 204 - NO CONTENT
        } else {
            response = Response.status(404); // 404 - NOT FOUND
        }
        return response.build();
    }

    @PUT
    @Consumes(MediaType.APPLICATION_JSON)
    @Path("/{id_receita}")
    public Response update(@Valid ReceitaTO receita, @PathParam("id_receita") Long idReceita) {
        receita.setIdReceita(idReceita);
        ReceitaTO resultado = receitaBO.update(receita);
        Response.ResponseBuilder response = null;

        if (resultado != null) {
            response = Response.created(null); // 201 - CREATED (Também poderia usar o 200 - OK)
        } else {
            response = Response.status(400); // 400 - BAD REQUEST
        }
        response.entity(resultado);
        return response.build();
    }
}

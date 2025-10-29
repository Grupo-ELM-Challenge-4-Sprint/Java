package br.com.fiap.resource;

import br.com.fiap.bo.ConsultaBO;
import br.com.fiap.to.ConsultaTO;
import jakarta.validation.Valid;
import jakarta.ws.rs.*;
import jakarta.ws.rs.core.MediaType;
import jakarta.ws.rs.core.Response;

import java.util.ArrayList;

@Path("/consulta")
public class ConsultaResource {
    private ConsultaBO consultaBO = new ConsultaBO();

    @GET
    @Produces(MediaType.APPLICATION_JSON)
    public Response findAll() {
        ArrayList<ConsultaTO> resultado = consultaBO.findAll();
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
    @Path("/{id_consulta}")
    @Produces(MediaType.APPLICATION_JSON)
    public Response findByCodigo(@PathParam("id_consulta") Long codigo) {
        ConsultaTO resultado = consultaBO.findByCodigo(codigo);
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
    public Response save(@Valid ConsultaTO consulta) {
        ConsultaTO resultado = consultaBO.save(consulta);
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
    @Path("/{id_consulta}")
    public Response delete(@PathParam("id_consulta") Long codigo) {
        Response.ResponseBuilder response = null;
        if (consultaBO.delete(codigo)) {
            response = Response.status(204); // 204 - NO CONTENT
        } else {
            response = Response.status(404); // 404 - NOT FOUND
        }
        return response.build();
    }

    @PUT
    @Consumes(MediaType.APPLICATION_JSON)
    @Path("/{id_consulta}")
    public Response update(@Valid ConsultaTO consulta, @PathParam("id_consulta") Long idConsulta) {
        consulta.setIdConsulta(idConsulta);
        ConsultaTO resultado = consultaBO.update(consulta);
        Response.ResponseBuilder response = null;

        if (resultado != null) {
            response = Response.created(null); // 201 - CREATED (Também poderia usar o 200 - OK)
        } else {
            response = Response.status(400); // 400 - BAD REQUEST
        }
        response.entity(resultado);
        return response.build();
    }

    @GET
    @Path("/usuario/{userId}") // Novo endpoint
    @Produces(MediaType.APPLICATION_JSON)
    public Response findAllByUserId(@PathParam("userId") Long userId) {
        ArrayList<ConsultaTO> resultado = consultaBO.findAllByUserId(userId);
        // Retorna OK mesmo se a lista estiver vazia, como padrão REST
        return Response.ok(resultado).build();
    }
}

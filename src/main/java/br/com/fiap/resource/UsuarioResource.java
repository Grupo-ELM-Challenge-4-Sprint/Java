package br.com.fiap.resource;

import br.com.fiap.bo.UsuarioBO;
import br.com.fiap.to.UsuarioTO;
import jakarta.validation.Valid;
import jakarta.ws.rs.*;
import jakarta.ws.rs.core.MediaType;
import jakarta.ws.rs.core.Response;

import java.util.ArrayList;

@Path("/usuario")
public class UsuarioResource {
    private UsuarioBO usuarioBO = new UsuarioBO();

    @GET
    @Produces(MediaType.APPLICATION_JSON)
    public Response findAll() {
        ArrayList<UsuarioTO> resultado = usuarioBO.findAll();
        // O ideal é sempre retornar OK com uma lista vazia, em vez de 404
        return Response.ok(resultado).build();
    }

    @GET
    @Path("/cpf/{cpf}")
    @Produces(MediaType.APPLICATION_JSON)
    public Response findByCpf(@PathParam("cpf") String cpf) {
        UsuarioTO resultado = usuarioBO.findByCpf(cpf);
        if (resultado != null) {
            return Response.ok(resultado).build();
        } else {
            return Response.status(Response.Status.NOT_FOUND).build();
        }
    }

    @GET
    @Path("/{id_user}")
    @Produces(MediaType.APPLICATION_JSON)
    public Response findByCodigo(@PathParam("id_user") Long codigo) {
        UsuarioTO resultado = usuarioBO.findByCodigo(codigo);
        if (resultado != null) {
            return Response.ok(resultado).build();
        } else {
            return Response.status(Response.Status.NOT_FOUND).build();
        }
    }

    @POST
    @Consumes(MediaType.APPLICATION_JSON)
    @Produces(MediaType.APPLICATION_JSON)
    public Response save(@Valid UsuarioTO usuario) {
        UsuarioTO resultado = usuarioBO.save(usuario);
        if (resultado != null) {
            // Retorna o objeto criado com status 201
            return Response.status(Response.Status.CREATED).entity(resultado).build();
        } else {
            return Response.status(Response.Status.BAD_REQUEST).build();
        }
    }

    @DELETE
    @Path("/{id_user}")
    public Response delete(@PathParam("id_user") Long codigo) {
        if (usuarioBO.delete(codigo)) {
            return Response.noContent().build(); // 204
        } else {
            return Response.status(Response.Status.NOT_FOUND).build();
        }
    }

    @PUT
    @Path("/{id_user}")
    @Consumes(MediaType.APPLICATION_JSON)
    @Produces(MediaType.APPLICATION_JSON)
    public Response update(@Valid UsuarioTO usuario, @PathParam("id_user") Long idUser) {
        usuario.setIdUser(idUser);
        UsuarioTO resultado = usuarioBO.update(usuario);
        if (resultado != null) {
            return Response.ok(resultado).build(); // 200
        } else {
            return Response.status(Response.Status.BAD_REQUEST).build();
        }
    }
}
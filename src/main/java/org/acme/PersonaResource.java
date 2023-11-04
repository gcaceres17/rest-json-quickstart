package org.acme;

import jakarta.transaction.Transactional;
import jakarta.ws.rs.*;
import jakarta.ws.rs.core.MediaType;
import jakarta.ws.rs.core.Response;

import java.net.URI;
import java.util.List;

@Path("/personas")
@Produces(MediaType.APPLICATION_JSON)
@Consumes(MediaType.APPLICATION_JSON)
public class PersonaResource {

    @GET
    public List<Persona> list() {
        return Persona.listAll();
    }

    @GET
    @Path("/{id}")
    public Persona get(Long id) {
        return Persona.findById(id);
    }

    @POST
    @Transactional
    public Response create(Persona person) {
        // Crea una nueva instancia de Persona en lugar de usar la recibida en la solicitud
        Persona nuevaPersona = new Persona();
        nuevaPersona.nombre = person.nombre; // Copia los datos desde la instancia recibida
        nuevaPersona.apellido = person.apellido;
        nuevaPersona.edad = person.edad;
        nuevaPersona.direccion = person.direccion;
        nuevaPersona.persist(); // Persiste la nueva instancia
        return Response.created(URI.create("/personas/" + nuevaPersona.id)).build();
    }
    

    @PUT
    @Path("/{id}")
    @Transactional
    public Persona update(Long id, Persona persona) {
        Persona entity = Persona.findById(id);
        if(entity == null) {
            throw new NotFoundException();
        }
        entity.nombre = persona.nombre;
        entity.apellido = persona.apellido;
        entity.edad = persona.edad;
        entity.direccion = persona.direccion;

        return entity;
    }

    @DELETE
    @Path("/{id}")
    @Transactional
    public void delete(Long id) {
        Persona entity = Persona.findById(id);
        if(entity == null) {
            throw new NotFoundException();
        }
        entity.delete();
    }
}

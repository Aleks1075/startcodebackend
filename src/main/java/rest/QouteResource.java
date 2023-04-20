package rest;

import com.google.gson.Gson;
import com.google.gson.GsonBuilder;
import dtos.Qoute1DTO;
import dtos.Qoute2DTO;
import dtos.MyQouteDTO;
import facades.QouteFacade;
import utils.EMF_Creator;

import javax.persistence.EntityManagerFactory;
import javax.ws.rs.GET;
import javax.ws.rs.Path;
import javax.ws.rs.Produces;
import javax.ws.rs.core.MediaType;
import javax.ws.rs.core.Response;

@Path("qoute")
public class QouteResource {

    private static final EntityManagerFactory EMF = EMF_Creator.createEntityManagerFactory();
    private static final Gson GSON = new GsonBuilder().setPrettyPrinting().create();
    private static final QouteFacade FACADE =  QouteFacade.getQouteFacade(EMF);

    @GET
    @Produces({MediaType.APPLICATION_JSON})
    public Response getQoute() throws Exception {
        Qoute1DTO qoute1DTO = FACADE.createQoute1DTO();
        System.out.println(qoute1DTO);
        Qoute2DTO qoute2DTO = FACADE.createQoute2DTO(FACADE.fetchData("https://api.quotable.io/random"));
        System.out.println(qoute2DTO);
        MyQouteDTO myQouteDTO = new MyQouteDTO(qoute1DTO,qoute2DTO);
        return Response.ok().entity(myQouteDTO).build();

    }
}
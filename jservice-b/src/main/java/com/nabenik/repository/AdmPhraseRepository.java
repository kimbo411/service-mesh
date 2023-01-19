package com.nabenik.repository;


import com.nabenik.model.AdmPhrase;
import org.eclipse.microprofile.rest.client.inject.RegisterRestClient;
import org.eclipse.microprofile.rest.client.ext.*;

import javax.ws.rs.GET;
import javax.ws.rs.Path;
import javax.ws.rs.PathParam;
import javax.ws.rs.core.Response;

import java.util.List;

@RegisterRestClient
@Path("/phrases")
public interface AdmPhraseRepository {

    @GET
    public List<AdmPhrase> listAll();

    @GET
    @Path("/{id:[0-9][0-9]*}")
    public AdmPhrase findById(@PathParam("id") Long id);

    @ClientExceptionMapper
    static RuntimeException toException(Response response) {
        if (response.getStatus() == 500) {
            return new RuntimeException("The remote service responded with HTTP 500");
        }
        return null;
    }
}

package com.nabenik.controller;

import com.nabenik.repository.AdmPhraseRepository;
import org.eclipse.microprofile.faulttolerance.Fallback;
import org.eclipse.microprofile.rest.client.inject.RestClient;

import javax.inject.Inject;
import javax.ws.rs.GET;
import javax.ws.rs.Path;
import javax.ws.rs.Produces;
import javax.ws.rs.core.MediaType;
import javax.ws.rs.core.Response;
import javax.ws.rs.core.Response.Status;
import java.util.Random;
import org.jboss.logging.Logger;

@Path("/phrase-demo")
public class PhraseDemoController {

    AdmPhraseRepository admPhraseRepository;
    private static final Logger LOGGER = Logger.getLogger(PhraseDemoController.class);

    @Inject
    public PhraseDemoController(@RestClient AdmPhraseRepository admPhraseRepository){
        this.admPhraseRepository = admPhraseRepository;
    }

    @GET
    @Produces(MediaType.TEXT_PLAIN)
    @Fallback(fallbackMethod = "doPhraseDemoFallback")
    public Response doPhraseDemo(){
        var randIndex = new Random().nextInt(3) +1;
        var phrase = this.admPhraseRepository.findById(Long.valueOf(randIndex));
        return Response.status(Status.OK).entity("Recibi la frase \"" + phrase.getPhrase() + "\" sabiduria pura por - " + phrase.getAuthor()).build();
    }

    public Response doPhraseDemoFallback(){
        return  Response.status(Status.INTERNAL_SERVER_ERROR).entity("El sistema esta en modo de mantenimiento, vuelva mas tarde").build();
    }

}

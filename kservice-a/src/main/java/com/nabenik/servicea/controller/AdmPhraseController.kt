package com.nabenik.servicea.controller

import com.nabenik.servicea.model.AdmPhrase
import java.util.*
import javax.enterprise.context.ApplicationScoped
import javax.ws.rs.GET
import javax.ws.rs.Path
import javax.ws.rs.PathParam

@Path("/phrases")
@ApplicationScoped
class AdmPhraseController {

    private lateinit var phraseList: List<AdmPhrase>

    @GET
    fun listAll(): List<AdmPhrase> = phraseList

    @GET
    @Path("/{id:[0-9][0-9]*}")
    fun findById(@PathParam("id") id: Long): AdmPhrase? {
        maybeFail("Random fail")
        return phraseList.firstOrNull(){ it.phraseId == id }
    }

    private fun maybeFail(failureLogMessage: String) {
        if (Random().nextBoolean()) {
            throw RuntimeException("Resource failure.")
        }
    }

    init {
        phraseList = arrayListOf(
            AdmPhrase(1L, "Robert C. Martin", "Por supuesto, se puede limpiar el codigo incorrecto. Pero es muy caro."),
            AdmPhrase(2L, "Ken Thompson", "Uno de mis dias mas productivos fue tirar 1000 lineas de codigo."),
            AdmPhrase(3L, "James Gosling", "Java es C++ sin pistolas, garrotes ni cuchillos.")
        )
    }
}

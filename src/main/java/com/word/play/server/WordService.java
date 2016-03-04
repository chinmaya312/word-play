package com.word.play.server;

import com.word.play.bo.WordTrendBo;
import com.word.play.response.WordPlayResponse;

import javax.ejb.EJB;
import javax.ejb.Stateless;
import javax.inject.Inject;
import javax.ws.rs.GET;
import javax.ws.rs.Path;
import javax.ws.rs.PathParam;
import javax.ws.rs.core.GenericEntity;
import javax.ws.rs.core.Response;

@Stateless
@Path( "/word" )
public class WordService {

  @EJB
  WordFrequencyServer server;

  @Inject
  WordTrendBo wordTrendBo;

  @GET
  @Path( "/{key}" )
  public Response getWordPlay(@PathParam("key")String key) {
    WordPlayResponse wordPlayResponse =  new WordPlayResponse(server.getFrequency(key), wordTrendBo.getWordTrendingNumber(key));
    
    return Response.ok().entity(new GenericEntity<WordPlayResponse>(wordPlayResponse){}).build();
  }
}

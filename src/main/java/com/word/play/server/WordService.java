package com.word.play.server;

import com.fasterxml.jackson.core.JsonProcessingException;
import com.fasterxml.jackson.databind.ObjectMapper;
import com.fasterxml.jackson.dataformat.xml.XmlMapper;
import com.word.play.bo.WordTrendBo;
import com.word.play.response.WordPlayResponse;
import org.apache.logging.log4j.LogManager;
import org.apache.logging.log4j.Logger;

import javax.ejb.EJB;
import javax.ejb.Stateless;
import javax.inject.Inject;
import javax.ws.rs.GET;
import javax.ws.rs.Path;
import javax.ws.rs.PathParam;
import javax.ws.rs.Produces;
import javax.ws.rs.core.GenericEntity;
import javax.ws.rs.core.Response;

@Stateless
@Path( "/word" )
public class WordService {

  private static final Logger logger = LogManager.getLogger(WordService.class);

  @EJB
  WordFrequencyServer server;

  @Inject
  WordTrendBo wordTrendBo;

  /**
   * Searches for the given word and returns the
   * response as xml.
   *
   * @param key word to be searched for
   * @return WordPlayResponse as xml string
   * @see WordPlayResponse
   */
  @GET
  @Path( "/{key}" )
  @Produces( "application/xml" )
  public Response getWordPlay(@PathParam("key")String key) {

    logger.entry(key);
    WordPlayResponse wordPlayResponse =  new WordPlayResponse(key,
        server.getFrequency(key), wordTrendBo.getWordTrendingNumber(key));

    ObjectMapper mapper = new XmlMapper();
    String responseString;

    try {
      responseString = mapper.writeValueAsString(wordPlayResponse);
    } catch (JsonProcessingException e) {
      responseString = "Conversion to XML failed!!!";
    }

    logger.exit(responseString);
    return Response.ok().entity(responseString).build();
  }
}

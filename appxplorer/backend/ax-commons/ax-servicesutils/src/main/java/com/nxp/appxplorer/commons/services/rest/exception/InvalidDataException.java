package com.nxp.appxplorer.commons.services.rest.exception;

import static javax.ws.rs.core.MediaType.APPLICATION_JSON;
import javax.ws.rs.WebApplicationException;
import javax.ws.rs.core.Response;

/**
 * Thrown when invalid data was provided.
 *
 * @author Alexander Zapletal (alexander.zapletal@rise-world.com)
 */
@SuppressWarnings("squid:S2166") // WebApplicationException is a subclass of Exception
public class InvalidDataException extends WebApplicationException {

  private static final int STATUS_CODE_UNPROCESSABLE_ENTITY = 422;

  public InvalidDataException(final ErrorDetails errorDetails) {
    super(Response.status(STATUS_CODE_UNPROCESSABLE_ENTITY).entity(errorDetails).type(APPLICATION_JSON).build());
  }
}

package com.nxp.appxplorer.commons.services.rest.json;

import static java.util.Collections.singletonList;

import java.io.IOException;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

import com.fasterxml.jackson.core.JsonParser;
import com.fasterxml.jackson.databind.DeserializationContext;
import com.fasterxml.jackson.databind.deser.std.StdDeserializer;
import com.google.inject.Singleton;
import com.nxp.appxplorer.commons.services.rest.exception.ErrorDetails;
import com.nxp.appxplorer.commons.services.rest.exception.InvalidDataException;

/**
 * Deserializes an integer value.
 *
 */
@Singleton
public class IntegerDeserializer extends StdDeserializer<Integer> {

  private static final Logger LOGGER = LoggerFactory.getLogger(IntegerDeserializer.class);

  public IntegerDeserializer() {
    super(Integer.class);
  }

  @Override
  public Integer deserialize(final JsonParser jsonParser, final DeserializationContext context) throws IOException {
    final String value = jsonParser.getValueAsString();

    try {
      return Integer.parseInt(value);
    } catch (final NumberFormatException e) {
      final String fieldName = jsonParser.getParsingContext().getCurrentName();
      LOGGER.error("field {} does not contain a valid integer: {}", fieldName, value);

      final ErrorDetails errorDetails = ErrorDetails.newInvalidDataInstance(singletonList(fieldName));
      throw new InvalidDataException(errorDetails);
    }
  }
}

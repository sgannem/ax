package com.nxp.appxplorer.exception;

import java.util.Arrays;
import java.util.stream.Collectors;

/**
 * 
 * This class being used by the DAOs to give appropriate errors to services
 * layer.
 * 
 * @author nxa30710
 *
 */
public class EntityNotFoundInRepoException extends Exception {

    private static final long serialVersionUID = 1L;
    /*
     * private static final String ERROR_MSG_NO_IDS =
     * "No Entity \'%s\' with the given criteria found"; private static final
     * String ERROR_MSG_SINGLE_ID =
     * "Entity \'%s\' with id \'%d\' not found in database."; private static
     * final String ERROR_MSG_MULTIPLE_IDS =
     * "Entity \'%s\' with ids (\'%s\') not found in database."; private static
     * final String ID_SEPARATOR = "/";
     */

    /**
     * This constructor helps to set the default error message.
     * 
     * @param message
     */
    private EntityNotFoundInRepoException(String message) {
	super(message);
    }

    /**
     * This method creates a new instance for a given error message.
     * 
     * @param message
     * @return
     */
    public static EntityNotFoundInRepoException newInstance(String message) {
	return new EntityNotFoundInRepoException(message);
    }

    /**
     * This method creates a new instance for a given class.
     * 
     * @param clazz
     * @return
     */
    public static EntityNotFoundInRepoException newInstance(Class<?> clazz) {
	return new EntityNotFoundInRepoException(String.format("No Entity \'%s\' with the given criteria found",
		new Object[] { clazz.getSimpleName() }));
    }

    /**
     * 
     * This method creates a new instance for a given class and logn ids.
     * 
     * @param clazz
     * @param ids
     * @return
     */
    public static EntityNotFoundInRepoException newInstance(Class<?> clazz, long... ids) {
	return new EntityNotFoundInRepoException(buildMessage(clazz, ids));
    }

    /**
     * This method creates a new instance for a given class and string of Ids.
     * 
     * @param clazz
     * @param ids
     * @return
     */
    public static EntityNotFoundInRepoException newInstance(Class<?> clazz, String... ids) {
	return new EntityNotFoundInRepoException(buildMessage(clazz, ids));
    }

    /**
     * This method builds a message for a given class and long ids.
     * 
     * @param clazz
     * @param ids
     * @return
     */
    private static String buildMessage(Class<?> clazz, long... ids) {
	if (ids.length == 0) {
	    throw new IllegalArgumentException();
	} else if (ids.length == 1) {
	    return String.format("Entity \'%s\' with id \'%d\' not found in database.", clazz.getSimpleName(),
		    Long.valueOf(ids[0]));
	} else {
	    String idsAsString = (String) Arrays.stream(ids).mapToObj(String::valueOf).collect(Collectors.joining("/"));
	    return String.format("Entity \'%s\' with ids (\'%s\') not found in database.", clazz.getSimpleName(),
		    idsAsString);
	}
    }

    /**
     * This method creates a new instance for a given class and string of ids.
     * 
     * @param clazz
     * @param ids
     * @return
     */
    private static String buildMessage(Class<?> clazz, String... ids) {
	String idsAsString = (String) Arrays.stream(ids).map(String::valueOf).collect(Collectors.joining("/"));
	return String.format("Entity \'%s\' with ids (\'%s\') not found in database.", clazz.getSimpleName(),
		idsAsString);
    }
}
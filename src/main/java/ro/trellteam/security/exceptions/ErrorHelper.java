package ro.trellteam.security.exceptions;

import java.util.HashMap;

/**
 * Class which contains every error code and it's description.
 */
public class ErrorHelper {

    private static HashMap<String, String> errorList;

    static {
        errorList = new HashMap<>();

        errorList.put("SECURITY_ERR_0", "An error has been encountered.");
        errorList.put("SECURITY_ERR_1", "Account was not found.");
        errorList.put("SECURITY_ERR_2", "Role was not found.");
        errorList.put("SECURITY_ERR_3", "Required request input is missing or invalid.");
        errorList.put("SECURITY_ERR_4", "Required request body parameter is missing or invalid.");
    }
}

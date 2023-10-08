package edu.virginia.cs.hw7.businessLogicLayer;

import java.util.regex.Pattern;

public class CommandLineUtility {
    private static final String REGEX_FOR_CMD_INPUT = "^[1-3]$";

    public static int processCommandLineInput(String input) {
        if(Pattern.matches(REGEX_FOR_CMD_INPUT,input))
            return Integer.parseInt(input);
        return -1;
    }

    private static String VALID_LOGIN_OR_PASSWORD = "[ \t\n]";
    public static boolean nullCharacterString(String input){
        return (input.length() == 0 || input.contains(" ") || input.contains("\t"));
        //return (input.matches(VALID_LOGIN_OR_PASSWORD));
    }
}

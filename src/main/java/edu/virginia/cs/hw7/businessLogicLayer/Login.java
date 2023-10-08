package edu.virginia.cs.hw7.businessLogicLayer;

import edu.virginia.cs.hw7.InternalImplementationException;
import edu.virginia.cs.hw7.Student;
import edu.virginia.cs.hw7.dataLayer.DatabaseManager;

public class Login {
    public static boolean login(String loginName, String password) throws InternalImplementationException {
        return DatabaseManager.doesStudentExist(loginName,password);
    }

    public static boolean isLoginAvailable(String newLogin) throws InternalImplementationException {
        return !DatabaseManager.doesStudentExist(newLogin);
    }

    public static boolean signUp(String newLogin, String password, String confirmPassword) throws InternalImplementationException {
        if(!password.equals(confirmPassword))
            return false;
        Student s = new Student(newLogin,password);
        DatabaseManager.addStudent(s);
        return true;
    }
}

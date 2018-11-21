package todo_code_apps.com.todotaskreminder.storage;

import java.util.HashMap;
import todo_code_apps.com.todotaskreminder.domain.model.Task;
public class DataValidator {

    private static DataValidator instance;

    public static DataValidator getInstance()
    {
        if(instance == null)
        {
            instance = new DataValidator();
        }
        return instance;
    }

    private static boolean validateTaskDetails(Task taskDetails)
    {
        //TODO
        return true;
    }

    private static boolean isValidUser(String userID)
    {
        //TODO
        return true;
    }

    public static boolean validateBasicDetails(String user, Task taskDetails)
    {
        if(validateTaskDetails(taskDetails) && isValidUser(user))
        {
            return true;
        }

        return false;
    }


}

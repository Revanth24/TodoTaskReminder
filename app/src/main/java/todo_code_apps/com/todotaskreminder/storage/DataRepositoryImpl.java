package todo_code_apps.com.todotaskreminder.storage;

import java.util.ArrayList;
import java.util.List;
import java.util.Map;
import java.util.HashMap;
import todo_code_apps.com.todotaskreminder.domain.model.Task;

public class DataRepositoryImpl implements IDataRepository
{

    // From Presentor needs to call this function
    public int addTask(Task td, boolean isNetworkThere)
    {
        if(isNetworkThere)
        {
            DatabaseFunctionInterface dfuc = DatabaseFactory.getDatabaseObjectBasedOnAccessCode(1);
            dfuc.addTask(td);
        }
        DatabaseFunctionInterface dfuc = DatabaseFactory.getDatabaseObjectBasedOnAccessCode(2);
        return 1;
    }

    public int updateTask(Task td, boolean isNetworkThere)
    {
        if(isNetworkThere)
        {
            DatabaseFunctionInterface dfuc = DatabaseFactory.getDatabaseObjectBasedOnAccessCode(1);
            dfuc.updateTask(td);
        }
        DatabaseFunctionInterface dfuc = DatabaseFactory.getDatabaseObjectBasedOnAccessCode(2);
        return 1;
    }

    public int delTask(Task td, boolean isNetworkThere)
    {
        if(isNetworkThere)
        {
            DatabaseFunctionInterface dfuc = DatabaseFactory.getDatabaseObjectBasedOnAccessCode(1);
            dfuc.delTask(td.getId());
        }
        DatabaseFunctionInterface dfuc = DatabaseFactory.getDatabaseObjectBasedOnAccessCode(2);
        return 1;
    }

    public Map<String, Task> getAllTasks()
    {
        return new HashMap<String, Task>();
    }

    public List<String> addUserToDB(String userName)
    {
        return new ArrayList<String>();
    }

}

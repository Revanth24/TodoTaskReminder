package todo_code_apps.com.todotaskreminder.storage;

import java.util.ArrayList;
import java.util.List;

import todo_code_apps.com.todotaskreminder.domain.model.Task;

public class SQLiteDB extends DatabaseFunctionInterface
{
    private SQLiteDB instance;
    public SQLiteDB getInstance()
    {
        if(instance == null)
        {
            instance = new SQLiteDB();
        }

        return instance;
    }
    @Override
    public int addTask(Task td)
    {
        return 1;
    }

    @Override
    public int delTask(String taskId )
    {
        return 1;
    }

    @Override
    public int updateTask(Task td)
    {
        return 1;
    }

    @Override
    public List<String> addUser(String UserName)
    {
        List<String> taskList = new ArrayList<String>();
        return taskList;
    }
}

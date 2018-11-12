package todo_code_apps.com.todotaskreminder.storage;

import java.util.List;
import todo_code_apps.com.todotaskreminder.domain.model.Task;

public abstract class DatabaseFunctionInterface
{
    public abstract int addTask(Task td);
    public abstract int delTask(String taskId);
    public abstract int updateTask(Task td);
    public abstract List<String> addUser(String userName);
}

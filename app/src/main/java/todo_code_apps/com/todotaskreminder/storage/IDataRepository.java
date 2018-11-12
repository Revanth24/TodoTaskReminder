package todo_code_apps.com.todotaskreminder.storage;

import java.util.Map;
import todo_code_apps.com.todotaskreminder.domain.model.Task;

public interface IDataRepository
{
    public int addTask(Task td, boolean isNetworkThere);
    public int updateTask(Task td, boolean isNetworkThere);
    public int delTask(Task td, boolean isNetworkThere);
    public Map<String, Task> getAllTasks();
}

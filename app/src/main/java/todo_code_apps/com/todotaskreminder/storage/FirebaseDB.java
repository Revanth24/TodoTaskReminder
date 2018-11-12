package todo_code_apps.com.todotaskreminder.storage;

import com.google.firebase.database.DatabaseReference;
import com.google.firebase.database.FirebaseDatabase;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;

import todo_code_apps.com.todotaskreminder.domain.model.Task;

public class FirebaseDB extends DatabaseFunctionInterface
{
    private FirebaseDB instance;
    public FirebaseDB getInstance()
    {
        if(instance == null)
        {
            instance = new FirebaseDB();
        }

        return instance;
    }


    @Override
    public int addTask(Task td)
    {
        HashMap<String, String> map = new HashMap<String, String>();
        DatabaseReference dbReference = getConnection();
        String taskId = td.getId();
        dbReference.child(QueryConstants.table.table_taskDetails).child(taskId).setValue(td);
        return 1;
    }

    @Override
    public int delTask(String taskId)
    {
        DatabaseReference dbReference = getConnection();
        dbReference.child(QueryConstants.table.table_taskDetails).child(taskId).removeValue();
        return 1;
    }

    @Override
    public int updateTask(Task td)
    {
        DatabaseReference dbReference = getConnection();
        String taskId = td.getId();
        dbReference.child(QueryConstants.table.table_taskDetails).child(taskId).setValue(td);
        return 1;
    }

    public static DatabaseReference getConnection()
    {
        return FirebaseDatabase.getInstance().getReference();
    }

    @Override
    public List<String> addUser(String UserName)
    {
        List<String> taskList = new ArrayList<String>();
        return taskList;
    }
}

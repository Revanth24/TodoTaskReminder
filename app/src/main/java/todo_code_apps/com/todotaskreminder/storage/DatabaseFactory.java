package todo_code_apps.com.todotaskreminder.storage;

public class DatabaseFactory
{
    public static DatabaseFunctionInterface getDatabaseObjectBasedOnAccessCode(int accessCode)
    {
        switch(accessCode)
        {
            case 1: return new FirebaseDB();
            case 2: return new SQLiteDB();
            default: return null;
        }

    }
}

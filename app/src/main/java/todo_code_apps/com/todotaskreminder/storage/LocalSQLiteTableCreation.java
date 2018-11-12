package todo_code_apps.com.todotaskreminder.storage;

public class LocalSQLiteTableCreation
{
    private static LocalSQLiteTableCreation tableCreation;

    public static LocalSQLiteTableCreation getInstance()
    {
        if(tableCreation == null)
        {
            tableCreation = new LocalSQLiteTableCreation();
        }
        return tableCreation;
    }


}

package todo_code_apps.com.todotaskreminder.storage;

public class QueryConstants
{
    public class table
    {
        public static final String table_taskDetails = "taskDetails";
        public static final String table_sessionDetails = "sessionDetails";
        public static final String table_userTaskTable = "userTaskTable";
    }

    public class columns
    {
        public static final String col_taskId = "taskId";
        public static final String col_userId = "userId";
        public static final String col_taskName = "taskName";
        public static final String col_taskDes = "taskDes";
        public static final String col_reminderDate = "reminderDate";
        public static final String col_sessionId = "sessionId";
    }

    public static final String table_create_taskDetails = "CREATE TABLE " + table.table_taskDetails + "( " + columns.col_taskId + " VARCHAR(255) PRIMARY KEY , " + columns.col_taskName + " VARCHAR(255), " + columns.col_taskDes + " VARCHAR(255), " + columns.col_reminderDate + " VARCHAR(255))";
    public static final String table_create_sessionDetails = "CREATE TABLE " + table.table_sessionDetails + "( " + columns.col_userId + " VARCHAR(255) PRIMARY KEY , " + columns.col_sessionId +" VARCHAR(255))";
    public static final String table_create_userTaskTable = "CREATE TABLE " + table.table_userTaskTable + "( " + columns.col_userId + " VARCHAR(255) PRIMARY KEY," + columns.col_taskId + " VARCHAR(255))";
}

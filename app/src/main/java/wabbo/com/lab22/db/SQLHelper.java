package wabbo.com.lab22.db;

import android.content.Context;
import android.database.sqlite.SQLiteDatabase;
import android.database.sqlite.SQLiteOpenHelper;


public class SQLHelper extends SQLiteOpenHelper {

    public static final String DATABASE_NAME = "test.db";
    public static final int DATABASE_V = 1;

    public static final String DATABASE_TABLE_NAME = "myTable";
    public static final String DATABASE_COLUMN_ID = "id";
    public static final String DATABASE_COLUMN_NAME = "name";
    public static final String DATABASE_COLUMN_PHONE = "phone";


    public SQLHelper(Context context) {
        super(context, DATABASE_NAME, null, DATABASE_V);
    }

    @Override
    public void onCreate(SQLiteDatabase db) {

        String sqlCreatTable = "CREATE TABLE " + DATABASE_TABLE_NAME + " (" +
                DATABASE_COLUMN_ID + " INTEGER PRIMARY KEY AUTOINCREMENT, " +
                DATABASE_COLUMN_NAME + " VARCHAR(255) , " +
                DATABASE_COLUMN_PHONE + " VARCHAR(255) );";

        db.execSQL(sqlCreatTable);

    }

    @Override
    public void onUpgrade(SQLiteDatabase db, int oldVersion, int newVersion) {

        db.execSQL("DROP TABLE IF EXISTS " + DATABASE_TABLE_NAME );
    }

}

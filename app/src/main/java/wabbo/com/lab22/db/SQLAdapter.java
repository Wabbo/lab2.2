package wabbo.com.lab22.db;

import android.content.ContentValues;
import android.content.Context;
import android.database.Cursor;
import android.database.sqlite.SQLiteDatabase;

public class SQLAdapter {
    Context context;
    static SQLHelper helper;
    boolean englishFlag = true;

    public SQLAdapter(Context context) {
        helper = new SQLHelper(context);
        this.context = context;
    }

    public long insert (User user) {
        SQLiteDatabase sqLiteDatabase = helper.getWritableDatabase();
        ContentValues contentValues = new ContentValues();
        contentValues.put(helper.DATABASE_COLUMN_NAME, user.getName());
        contentValues.put(helper.DATABASE_COLUMN_PHONE, user.getPhone());
        // return row id OR -1 error
        return sqLiteDatabase.insert(SQLHelper.DATABASE_TABLE_NAME, SQLHelper.DATABASE_COLUMN_ID, contentValues);
    }

    public User getUserByName (String userName) {

        SQLiteDatabase sqLiteDatabase = helper.getWritableDatabase();

        String[] columns = {
                SQLHelper.DATABASE_COLUMN_ID,
                SQLHelper.DATABASE_COLUMN_NAME,
                SQLHelper.DATABASE_COLUMN_PHONE
        };

        /*
        query (String table, String[] columns, String selection, String[] selectionArgs,
            String groupBy, String having, String orderBy, String limit)
        */

        Cursor cursor = sqLiteDatabase.query(
                SQLHelper.DATABASE_TABLE_NAME,
                columns,
                SQLHelper.DATABASE_COLUMN_NAME + " = ?",
                new String[]{userName},
                null,
                null,
                null,
                null
        );

        User user = null;

        if (cursor.moveToFirst()) {
            int id = cursor.getInt(0);
            String phone = cursor.getString(2);
            user = new User(id, userName, phone);
        }

        return user;
    }

}

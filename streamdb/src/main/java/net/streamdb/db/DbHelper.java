package net.streamdb.db;

import android.content.ContentValues;
import android.content.Context;
import android.database.Cursor;
import android.net.Uri;

/**
 * Created by andreyzakharov on 19.05.15.
 */
public class DbHelper {

    private final Context mContext;


    public DbHelper(Context context) {
        mContext = context;
    }


    public void saveData(Uri contentUri, ContentValues contentValues) {

    }

    public void saveData(Uri contentUri, ContentValues[] contentValues) {
        mContext.getContentResolver().bulkInsert(contentUri, contentValues);
    }

    public void updateData(Uri contentUri, ContentValues contentValues, String where, String[] whereArgs) {
        mContext.getContentResolver().update(contentUri, contentValues, where, whereArgs);
    }

    public void removeData(Uri contentUri, String where, String[] whereArs) {
        mContext.getContentResolver().delete(contentUri, where, whereArs);
    }


    public Cursor getData(Uri contentUri, String where, String[] whereArgs) {
        return mContext.getContentResolver().query(contentUri, null, where, whereArgs, null);
    }


}

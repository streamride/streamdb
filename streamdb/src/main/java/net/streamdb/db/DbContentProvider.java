package net.streamdb.db;

import android.content.ContentProvider;
import android.content.ContentValues;
import android.content.UriMatcher;
import android.database.Cursor;
import android.database.sqlite.SQLiteDatabase;
import android.database.sqlite.SQLiteQueryBuilder;
import android.net.Uri;
import android.provider.BaseColumns;
import android.support.annotation.NonNull;

import net.streamdb.db.utils.SUriMatcher;


/**
 * Created by andreyzakharov on 10.03.15.
 */


public abstract class DbContentProvider extends ContentProvider {


    static SUriMatcher sUriMatcher;


    protected abstract SqlLiteHelper getSqliteHelper();

    private SqlLiteHelper dbHelper;
    private SQLiteDatabase database;

    public DbContentProvider() {

    }

    @Override
    public int delete(Uri uri, String selection, String[] selectionArgs) {

        int count = 0;
        String table = uri.getLastPathSegment();
        count = database.delete(table, selection, selectionArgs);
        getContext().getContentResolver().notifyChange(uri, null);
        return count;
    }

    @Override
    public String getType(Uri uri) {
        return sUriMatcher.getType(uri);
    }

    @Override
    public Uri insert(Uri uri, ContentValues values) {

        String table = uri.getLastPathSegment();
        long rowId = dbHelper.getWritableDatabase().insertOrThrow(table, null, values);
        if (rowId == -1) return null;
        getContext().getContentResolver().notifyChange(uri, null);
        return uri.buildUpon().appendEncodedPath(String.valueOf(rowId)).build();
    }


    @Override
    public int bulkInsert(Uri uri, @NonNull ContentValues[] values) {
        String table = uri.getLastPathSegment();
        SQLiteDatabase db = dbHelper.getWritableDatabase();
        int res = 0;
        String selection = BaseColumns._ID.concat("=?");
        String[] selectionArgs = new String[1];
        db.beginTransaction();
        try {
            for (ContentValues v : values) {
                selectionArgs[0] = v.getAsString(BaseColumns._ID);
                int update = db.update(table, v, selection, selectionArgs);
                long id = -1;
                if (update == 0)
                    id = db.insert(table, null, v);
                db.yieldIfContendedSafely();
                if (id != -1) {
                    res++;
                }
            }
            db.setTransactionSuccessful();
        } finally {
            db.endTransaction();
        }
        getContext().getContentResolver().notifyChange(uri, null);

        return res;
    }

    @Override
    public boolean onCreate() {
        dbHelper = getSqliteHelper();
        database = dbHelper.getWritableDatabase();
        if (database == null)
            return false;
        return true;
    }

    @Override
    public Cursor query(Uri uri, String[] projection, String selection, String[] selectionArgs, String sortOrder) {

        SQLiteQueryBuilder sqLiteQueryBuilder = new SQLiteQueryBuilder();
        sqLiteQueryBuilder.setTables(uri.getLastPathSegment());

        Cursor cursor = sqLiteQueryBuilder.query(database, projection, selection, selectionArgs, null, null, sortOrder);
        cursor.setNotificationUri(getContext().getContentResolver(), uri);
        return cursor;
    }

    @Override
    public int update(Uri uri, ContentValues values, String selection,
                      String[] selectionArgs) {
        int res = dbHelper.getWritableDatabase().update(uri.getLastPathSegment(), values, selection, selectionArgs);
        getContext().getContentResolver().notifyChange(uri, null);
        return res;
    }
}


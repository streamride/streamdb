package net.streamdb.db;

import android.content.Context;
import android.database.sqlite.SQLiteOpenHelper;


/**
 * Created by andreyzakharov on 10.03.15.
 */
public abstract class SqlLiteHelper extends SQLiteOpenHelper {

    public SqlLiteHelper(final Context context, String dbName, int dbVersion) {
        super(context, dbName, null, dbVersion);
    }

}

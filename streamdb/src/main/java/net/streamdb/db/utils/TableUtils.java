package net.streamdb.db.utils;

import android.database.sqlite.SQLiteDatabase;
import android.util.Log;

import java.lang.reflect.Field;


/**
 * Created by andreyzakharov on 02.02.15.
 */
public class TableUtils {



    public static void createTable(SQLiteDatabase db, Class<?> cls) {
        DBTable annotation = cls.getAnnotation(DBTable.class);
        StringBuilder createBuilder = new StringBuilder("CREATE TABLE IF NOT EXISTS ");
        if (annotation != null) {
            String tableName = annotation.tableName();
            createBuilder.append(tableName);
        } else {
            throw new IllegalArgumentException("There is no DBTable annotation in class " + cls.getSimpleName());
        }

        createBuilder.append(" ( ");

        Field[] declaredFields = cls.getDeclaredFields();
        boolean first = true;
        for (Field field : declaredFields) {
            if (field.isAnnotationPresent(DBField.class)) {
                if (first)
                    first = false;
                else
                    createBuilder.append(" , ");
                DBField fieldAnn = field.getAnnotation(DBField.class);
                createBuilder.append(fieldAnn.columnName() + " " + TypeConverter.getTypeForDB(field.getType()));
                if (fieldAnn.primaryKey())
                    createBuilder.append(" PRIMARY KEY");
            }
        }
        createBuilder.append(" );");
        Log.d("table utils", createBuilder.toString());

        db.execSQL(createBuilder.toString());
    }

    public static void dropTable(SQLiteDatabase db, Class<?> cls) {
        DBTable annotation = cls.getAnnotation(DBTable.class);
        db.execSQL("DROP TABLE " + annotation.tableName() + ";");
    }

//    public static ContentValues getContentValuesForModel(ModelEntity modelEntity) throws IllegalAccessException {
//        ContentValues contentValues = new ContentValues();
//        Field[] declaredFields = modelEntity.getClass().getDeclaredFields();
//        for (Field field : declaredFields) {
//            if (field.isAnnotationPresent(DBField.class)) {
//                field.setAccessible(true);
//                DBField fieldAnn = field.getAnnotation(DBField.class);
////                contentValues.put(fieldAnn.columnName(), field.get(modelEntity));
//            }
//        }
//
//        return contentValues;
//    }


}

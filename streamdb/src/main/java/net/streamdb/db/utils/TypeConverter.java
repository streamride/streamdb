package net.streamdb.db.utils;

/**
 * Created by andreyzakharov on 02.02.15.
 */
public class TypeConverter {


    public static String getTypeForDB(Class<?> cls) {
        if (TypeHelper.isBoolean(cls, true)) {
            return "INTEGER";
        } else if (TypeHelper.isDouble(cls, true)) {
            return "REAL";
        } else if(TypeHelper.isString(cls)) {
            return "TEXT";
        } else if(TypeHelper.isFloat(cls, true)) {
            return "REAL";
        } else if(TypeHelper.isLong(cls, true)) {
            return "INTEGER";
        } else if(TypeHelper.isDate(cls)) {
            return "NUMERIC";
        } else if(TypeHelper.isInteger(cls, true)) {
            return "INTEGER";
        }

        throw new IllegalArgumentException(String.format("There is no such class type %s ", cls));
    }


}

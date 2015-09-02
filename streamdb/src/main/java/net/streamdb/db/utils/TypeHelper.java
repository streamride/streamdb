package net.streamdb.db.utils;

import android.net.Uri;

import org.json.JSONArray;
import org.json.JSONObject;

import java.lang.reflect.GenericDeclaration;
import java.util.Collection;
import java.util.Date;
import java.util.UUID;

/**
 * Created by andreyzakharov on 02.02.15.
 */
public class TypeHelper {

    public static boolean isBoolean(Class<?> cls, boolean orWrapper) {
        return (cls == boolean.class) ? true
                                      : orWrapper ? (cls == Boolean.class) : false;
    }

    public static boolean isInteger(Class<?> cls, boolean orWrapper) {
        return (cls == int.class) ? true : orWrapper ? (cls == Integer.class)
                                                     : false;
    }

    public static boolean isLong(Class<?> cls, boolean orWrapper) {
        return (cls == long.class) ? true : orWrapper ? (cls == Long.class)
                                                      : false;
    }

    public static boolean isFloat(Class<?> cls, boolean orWrapper) {
        return (cls == float.class) ? true : orWrapper ? (cls == Float.class)
                                                       : false;
    }

    public static boolean isDouble(Class<?> cls, boolean orWrapper) {
        return (cls == double.class) ? true : orWrapper ? (cls == Double.class)
                                                        : false;
    }


    public static boolean isCollection(Class<?> fieldType) { return Collection.class.isAssignableFrom(fieldType);}

    public static boolean isGenericObject(Class<?> fieldType) { return GenericDeclaration.class.isAssignableFrom(fieldType); }

    public static boolean isArray(Class<?> cls) { return cls.isArray(); }

    public static boolean isJSONObject(Class<?> cls) {
        return JSONObject.class.isAssignableFrom(cls);
    }

    public static boolean isJSONArray(Class<?> cls) {
        return JSONArray.class.isAssignableFrom(cls);
    }

    public static boolean isByte(Class<?> cls, boolean orWrapper) {
        return (cls == byte.class) ? true : orWrapper ? (cls == Byte.class)
                                                      : false;
    }

    public static boolean isShort(Class<?> cls, boolean orWrapper) {
        return (cls == short.class) ? true : orWrapper ? (cls == Short.class)
                                                       : false;
    }

    public static boolean isCharacter(Class<?> cls, boolean orWrapper) {
        return (cls == char.class) ? true
                                   : orWrapper ? (cls == Character.class) : false;
    }

    public static boolean isString(Class<?> cls) {
        return cls == String.class;
    }
    //


    public static boolean isEnum(Class<?> cls) {
        return cls.isEnum();
    }

    public static boolean isUUID(Class<?> cls) {
        return UUID.class.isAssignableFrom(cls);
    }

    public static boolean isUri(Class<?> cls) {
        return Uri.class.isAssignableFrom(cls);
    }

    public static boolean isDate(Class<?> cls) {
        return Date.class.isAssignableFrom(cls);
    }

    //

    public static boolean isByteArray(Class<?> cls) {
        return cls == byte[].class;
    }



}

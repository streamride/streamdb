package net.streamdb.db.utils;

import android.content.UriMatcher;
import android.net.Uri;


import net.streamdb.BuildConfig;

import java.util.HashMap;

/**
 * Created by andreyzakharov on 02.07.15.
 */
public class SUriMatcher {

    private final UriMatcher mUriMatcher;
    private final HashMap<Integer, String> mParamsHP;
    public static String AUTHORITY =  BuildConfig.APPLICATION_ID;

    public SUriMatcher(int code) {
        mUriMatcher = new UriMatcher(code);
        mParamsHP = new HashMap<>();
    }

    public void addURI(String path, int code) {
        mUriMatcher.addURI(AUTHORITY, path, code);
        mParamsHP.put(code, AUTHORITY + "/" + path);
    }

    public int match(Uri uri) {
        return mUriMatcher.match(uri);
    }


    public String getType(Uri uri) {
        int code = match(uri);
        if (code != -1) {
            return mParamsHP.get(code);
        }
        throw new IllegalArgumentException("Unsupported uri " + uri);
    }
}

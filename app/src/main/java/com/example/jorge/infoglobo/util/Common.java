package com.example.jorge.infoglobo.util;

import android.content.Context;
import android.content.res.AssetManager;
import android.net.ConnectivityManager;
import android.net.NetworkInfo;
import android.support.annotation.NonNull;
import android.support.v4.app.Fragment;
import android.support.v4.app.FragmentManager;
import android.support.v4.app.FragmentTransaction;
import com.example.jorge.infoglobo.data.source.cloud.news.model.Contents;
import com.google.gson.Gson;

import java.io.*;
import java.text.DateFormat;
import java.text.ParseException;
import java.text.SimpleDateFormat;
import java.util.Collection;
import java.util.Date;
import java.util.List;
import java.util.Locale;

import static com.google.gson.internal.$Gson$Preconditions.checkNotNull;

public class Common {

    /**
     * Checks if internet is ok .
     */
    public static boolean isOnline(Context context) {
        ConnectivityManager cm =
                (ConnectivityManager) context.getSystemService(Context.CONNECTIVITY_SERVICE);
        NetworkInfo netInfo = cm.getActiveNetworkInfo();
        return netInfo != null && netInfo.isConnectedOrConnecting();
    }

    /**
     * Check it list object null or empty
     * @param c
     * @return
     */
    public static boolean isNullOrEmpty( final Collection< ? > c ) {
        return c == null || c.isEmpty();
    }

    /**
     * Format date and time .
     */
    public static String formatDate(String date) {
        if (date != null) {
            SimpleDateFormat formato = new SimpleDateFormat("yyyy-MM-dd'T'HH:mm:ss-SSS", Locale.ENGLISH);
            try {
                Date data = formato.parse(date);
                Date d = data;
                SimpleDateFormat sdf = new SimpleDateFormat("dd/MM/yyyy HH:mm");
                return sdf.format(d);
            } catch (ParseException e) {
                e.printStackTrace(); }
        }
        return "";
    }

    /**
     * This function is added to the container view with id {@code frameId}. The operation is
     * performed by the {@code fragmentManager}.
     *
     */
    public static void addFragmentToActivity (@NonNull FragmentManager fragmentManager,
                                              @NonNull Fragment fragment, int frameId) {
        checkNotNull(fragmentManager);
        checkNotNull(fragment);
        FragmentTransaction transaction = fragmentManager.beginTransaction();
        transaction.add(frameId, fragment);
        transaction.commit();
    }

    public static boolean test = false;
}

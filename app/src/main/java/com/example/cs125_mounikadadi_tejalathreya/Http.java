package com.example.cs125_mounikadadi_tejalathreya;

import android.util.Log;

import com.loopj.android.http.AsyncHttpClient;
import com.loopj.android.http.AsyncHttpResponseHandler;

import java.net.URLEncoder;

public class Http {
    private static final String BASE_URL = "https://translation.googleapis.com/language/translate/v2?";
    private static final String KEY = "AIzaSyCvdlcfhuhrP5qJ99123f3_7fnoSaqqY0s";


    private static AsyncHttpClient client = new AsyncHttpClient();


    public static void post(String transText, String destLang, AsyncHttpResponseHandler responseHandler) {
        client.get(getAbsoluteUrl(transText, destLang), responseHandler);
    }

    private static String makeKeyChunk(String key) {
        return "&key=" + KEY;
    }

    private static String makeTransChunk(String transText) {
        String encodedText = URLEncoder.encode(transText);
        return "&q=" + encodedText;
    }

    private static String langDest(String langDest) {
        return "target=" + langDest;

    }

    private static String getAbsoluteUrl(String transText, String destLang) {
        String apiUrl = BASE_URL + langDest(destLang) + makeKeyChunk(KEY) + makeTransChunk(transText);
        return apiUrl;
    }
    //https://translation.googleapis.com/language/translate/v2?target=es&key=AIzaSyCvdlcfhuhrP5qJ99123f3_7fnoSaqqY0s&q=hi
}
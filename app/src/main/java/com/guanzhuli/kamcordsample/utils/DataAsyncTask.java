package com.guanzhuli.kamcordsample.utils;

import android.os.AsyncTask;
import android.util.Log;
import com.guanzhuli.kamcordsample.fragment.MainFragment;
import com.guanzhuli.kamcordsample.model.Item;
import com.guanzhuli.kamcordsample.model.ItemList;
import org.json.JSONArray;
import org.json.JSONException;
import org.json.JSONObject;

import java.io.*;
import java.net.HttpURLConnection;
import java.net.MalformedURLException;
import java.net.URL;
import java.util.ArrayList;

import static com.guanzhuli.kamcordsample.utils.Constant.*;

/**
 * Created by Guanzhu Li on 3/12/2017.
 */
public class DataAsyncTask extends AsyncTask<Void, Void, String> {
    private String mSize;
    private MainFragment.FragmentCallback mFragmentCallback;

    public DataAsyncTask(String size, MainFragment.FragmentCallback fragmentCallback) {
        mSize = size;
        mFragmentCallback = fragmentCallback;
    }

    @Override
    protected String doInBackground(Void... voids) {
        URL myURL = null;
        HttpURLConnection urlConnection = null;
        try {
            myURL = new URL(BASE_URL);
        } catch (MalformedURLException e) {
            e.printStackTrace();
        }
        try {
            urlConnection = (HttpURLConnection)myURL.openConnection();
            urlConnection.setRequestMethod(Connection.METHOD);
            urlConnection.setRequestProperty(Connection.HEADER_ACCEPT, Connection.HEADER_ACCEPT_FIELD);
            urlConnection.setRequestProperty(Connection.HEADER_LANGUAGE, Connection.HEADER_LANGUAGE_FIELD);
            urlConnection.setRequestProperty(Connection.HEADER_TOKEN, Connection.HEADER_TOKEN_FIELD);
            urlConnection.setRequestProperty(Connection.HEADER_CLIENT, Connection.HEADER_CLIENT_FIELD);
            urlConnection.connect();
        } catch (IOException e) {
            e.printStackTrace();
        }
        InputStreamReader in = null;
        try {
            in = new InputStreamReader((InputStream) urlConnection.getContent());
        } catch (IOException e) {
            e.printStackTrace();
        }
        BufferedReader buff = new BufferedReader(in);
        StringBuilder sb = new StringBuilder();
        String line;
        try {
            while ((line = buff.readLine()) != null) {
                sb.append(line + "\n");
            }
        } catch (IOException e) {
            e.printStackTrace();
        } finally {
            if (urlConnection != null){
                urlConnection.disconnect();
            }
        }
        return sb.toString();
    }

    @Override
    protected void onPostExecute(String s) {
        ArrayList<Item> list = ItemList.getInstance();
        list.clear();
        JSONObject data = null;
        try {
            data = new JSONObject(s);
        } catch (JSONException e) {
            e.printStackTrace();
        }
        JSONArray groupArray = null;
        try {
            groupArray = data.getJSONArray(FIELD_GROUP);
            Log.d("main", "get groups");
        } catch (JSONException e) {
            e.printStackTrace();
        }
        for (int i = 0; i < groupArray.length(); i++) {
            try {
                JSONArray cardsArray = new JSONArray(groupArray.getJSONObject(i).getString(FIELD_CARDS));
                Log.d("main", "get cards");
                for (int j = 0; j < cardsArray.length(); j++) {
                    Item item = new Item();
                    JSONObject shotCardData = new JSONObject(cardsArray.getJSONObject(j).getString(FIELD_SHOT));
                    // get heartCount
                    String heartCount = shotCardData.getString(FIELD_HEART);
                    item.setHeartCount(heartCount);
                    // get thumbnail
                    JSONObject shotThumbnail = shotCardData.getJSONObject(FIELD_THUMBNAIL);
                    String thumbnail = shotThumbnail.getString(mSize);
                    item.setThumbnail(thumbnail);
                    Log.d("main", thumbnail);
                    // get URL
                    JSONObject play = shotCardData.getJSONObject(FIELD_PLAY);
                    String videoURL = play.getString(FIELD_MP4);
                    item.setVideo(videoURL);
                    list.add(item);
                    Log.d("main", "add item");
                }
            } catch (JSONException e) {
                e.printStackTrace();
            }
        }
        mFragmentCallback.onTaskDone();
    }
}

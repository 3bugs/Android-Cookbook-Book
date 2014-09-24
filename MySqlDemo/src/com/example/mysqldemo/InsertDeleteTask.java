package com.example.mysqldemo;

import java.util.List;

import org.apache.http.NameValuePair;
import org.apache.http.client.utils.URLEncodedUtils;
import org.json.JSONException;
import org.json.JSONObject;

import android.content.Context;
import android.os.AsyncTask;
import android.widget.Toast;

public class InsertDeleteTask extends AsyncTask<Void, Void, String> {

    private Context mContext;
    private String mUrl;

    public InsertDeleteTask(Context context, String url, List<NameValuePair> params) {
        super();

        mContext = context;
        mUrl = url;
        if (params != null) {
            String paramString = URLEncodedUtils.format(params, "utf-8");
            mUrl += "?" + paramString;
        }
    }

    @Override
    protected String doInBackground(Void... params) {
        String jsonString = JsonHttp.makeHttpRequest(mUrl);
        return jsonString;
    }

    @Override
    protected void onPostExecute(String jsonString) {

        try {
            JSONObject json = new JSONObject(jsonString);

            String msg = json.getString("message");
            Toast.makeText(mContext, msg, Toast.LENGTH_SHORT).show();
        } catch (JSONException e) {
            e.printStackTrace();
        }
    }

}

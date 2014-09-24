package com.example.mysqldemo;

import java.util.ArrayList;
import java.util.HashMap;

import org.json.JSONArray;
import org.json.JSONException;
import org.json.JSONObject;

import android.content.Context;
import android.os.AsyncTask;
import android.widget.ListAdapter;
import android.widget.SimpleAdapter;
import android.widget.Toast;

public class SelectTask extends AsyncTask<Void, Void, String> {

    private Context mContext;
    private String mUrl;

    public SelectTask(Context context, String url) {
        super();

        mContext = context;
        mUrl = url;
    }

    @Override
    protected String doInBackground(Void... params) {
        String jsonString = JsonHttp.makeHttpRequest(mUrl);
        return jsonString;
    }

    @Override
    protected void onPostExecute(String jsonString) {

        ArrayList<HashMap<String, String>> peopleList;

        try {
            JSONObject json = new JSONObject(jsonString);
            int success = json.getInt("success");

            if (success == 1) {
                JSONArray people = json.getJSONArray("people");
                peopleList = new ArrayList<HashMap<String, String>>();

                for (int i = 0; i < people.length(); i++) {
                    JSONObject person = people.getJSONObject(i);

                    String id = person.getString("_id");
                    String name = person.getString("pName");
                    String date = person.getString("pDate");

                    HashMap<String, String> map = new HashMap<String, String>();
                    map.put("_id", id);
                    map.put("pName", name);
                    map.put("pDate", date);

                    peopleList.add(map);

                    String[] keys = new String[] { "_id", "pName", "pDate" };
                    int[] views = new int[] { R.id.item_id, R.id.item_name,
                            R.id.item_date };

                    ListAdapter adapter = new SimpleAdapter(mContext,
                            peopleList, R.layout.item, keys, views);

                    ((MainActivity) mContext).list.setAdapter(adapter);
                }
            } else if (success == 0) {
                ((MainActivity) mContext).list.setAdapter(null);
                String msg = json.getString("message");
                Toast.makeText(mContext, msg, Toast.LENGTH_SHORT).show();
            }
        } catch (JSONException e) {
            e.printStackTrace();
        }
    }

}

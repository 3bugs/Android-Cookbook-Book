package com.example.readrssfeed;

import java.io.IOException;
import java.io.InputStream;
import java.net.HttpURLConnection;
import java.net.URL;
import java.util.ArrayList;

import org.xmlpull.v1.XmlPullParserException;

import android.app.ListActivity;
import android.content.Intent;
import android.net.Uri;
import android.os.AsyncTask;
import android.os.Bundle;
import android.view.View;
import android.widget.ArrayAdapter;
import android.widget.ListView;

import com.example.readrssfeed.MyParserEngine.NewsItem;

public class MainActivity extends ListActivity {

    private ArrayAdapter<NewsItem> adapter;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        //setContentView(R.layout.activity_main);

        adapter = new ArrayAdapter<NewsItem>(this,
                android.R.layout.simple_list_item_1, android.R.id.text1);
        setListAdapter(adapter);

        DownloadFeedTask task = new DownloadFeedTask();
        task.execute("http://www.thairath.co.th/rss/tech.xml");
    }
    
    @Override
    protected void onListItemClick(ListView l, View v, int position, long id) {
        NewsItem item = (NewsItem) l.getItemAtPosition(position);
        String url = item.link;
        Intent i = new Intent(Intent.ACTION_VIEW, Uri.parse(url));
        startActivity(i);
    }
    
    private InputStream downloadFeed(String strUrl) {
        try {
            URL url = new URL(strUrl);
            HttpURLConnection con = (HttpURLConnection) url.openConnection();
            return con.getInputStream();
        } catch (Exception e) {
            e.printStackTrace();
            return null;
        }
    }

    private class DownloadFeedTask extends AsyncTask<String, Void, ArrayList<NewsItem>> {

        @Override
        protected ArrayList<NewsItem> doInBackground(String... urls) {
            InputStream stream = downloadFeed(urls[0]);
            
            try {
                return MyParserEngine.parse(stream);
            } catch (XmlPullParserException e) {
                e.printStackTrace();
                return null;
            } catch (IOException e) {
                e.printStackTrace();
                return null;
            }
        }

        @Override
        protected void onPostExecute(ArrayList<NewsItem> items) {
            adapter.clear();
            for (NewsItem item : items) {
                adapter.add(item);
            }
            adapter.notifyDataSetChanged();
        }
    }
}

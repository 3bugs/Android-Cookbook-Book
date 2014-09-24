package com.example.httpupload;

import java.io.BufferedReader;
import java.io.BufferedWriter;
import java.io.ByteArrayOutputStream;
import java.io.File;
import java.io.FileInputStream;
import java.io.IOException;
import java.io.InputStream;
import java.io.InputStreamReader;
import java.io.OutputStream;
import java.io.OutputStreamWriter;
import java.net.HttpURLConnection;
import java.net.URL;
import java.net.URLConnection;
import java.nio.charset.Charset;

import android.app.Activity;
import android.content.Context;
import android.os.AsyncTask;
import android.webkit.WebView;

public class UploadTask extends AsyncTask<String, Void, String> {
    
    private Context mContext;
    private File mFile;
    
    public UploadTask(Context context, File file) {
        mContext = context;
        mFile = file;
    }
    
    @Override
    protected String doInBackground(String... urls) {
        return uploadFile(urls[0]);
    }

    @Override
    protected void onPostExecute(String result) {
        //TextView textview = (TextView) ((Activity) mContext).findViewById(R.id.text);
        //textview.setText(result);

        WebView webview = (WebView) ((Activity) mContext).findViewById(R.id.web);
        webview.loadData(result, "text/html", "utf-8");
    }
    
    private String uploadFile(String strUrl) {
        String charset = Charset.defaultCharset().displayName();
        String boundary = Long.toHexString(System.currentTimeMillis());
        String strResult = "";

        try {
            URL url = new URL(strUrl);
            HttpURLConnection con = (HttpURLConnection) url.openConnection();
            
            con.setDoOutput(true);
            con.setRequestProperty("Content-Type", "multipart/form-data; boundary=" + boundary);
            
            ByteArrayOutputStream bos = new ByteArrayOutputStream();
            writeMultipart(boundary, charset, bos, false);
            byte[] extra = bos.toByteArray();
            int contentLength = extra.length;
            contentLength += mFile.length();
            
            con.setFixedLengthStreamingMode(contentLength);

            OutputStream out = con.getOutputStream();
            writeMultipart(boundary, charset, out, true);
            
            strResult = readStream(con.getInputStream());
        } catch (Exception e) {
            e.printStackTrace();
        }
        return strResult;
    }
    
    private void writeMultipart(String boundary, String charset,
            OutputStream output, boolean writeContent) throws IOException {
        
        BufferedWriter writer = null;
        
        try {
            writer = new BufferedWriter(new OutputStreamWriter(output,
                    Charset.forName(charset)), 8192);

            writer.write("--" + boundary);
            writer.write("\r\n");
            writer.write("Content-Disposition: form-data; name=\"myfile\"; filename=\""
                    + mFile.getName() + "\"");
            writer.write("\r\n");
            writer.write("Content-Type: "
                    + URLConnection.guessContentTypeFromName(
                    mFile.getName()));
            writer.write("\r\n");
            writer.write("Content-Transfer-Encoding: binary");
            writer.write("\r\n");
            writer.write("\r\n");
            writer.flush();
            
            if (writeContent) {
                FileInputStream fis = null;
                try {
                    fis = new FileInputStream(mFile);
                    byte[] buffer = new byte[1024];
                    for (int len = 0; (len = fis.read(buffer)) > 0;) {
                        output.write(buffer, 0, len);
                    }
                    output.flush();
                } catch (IOException e) {
                    e.printStackTrace();
                } finally {
                    if (fis != null) {
                        try {
                            fis.close();
                        } catch (IOException e) {
                        }
                    }
                }
            }
            writer.write("\r\n");
            writer.flush();

            writer.write("--" + boundary + "--");
            writer.write("\r\n");
            writer.flush();
        } finally {
            if (writer != null) {
                writer.close();
            }
        }
    }
    
    private String readStream(InputStream in) {
        BufferedReader reader = null;
        StringBuilder sb = new StringBuilder();
        try {
            reader = new BufferedReader(new InputStreamReader(in));
            
            String line;
            while ((line = reader.readLine()) != null) {
                sb.append(line + "\n");
            }

        } catch (IOException e) {
            e.printStackTrace();
        } finally {
            if (reader != null) {
                try {
                    reader.close();
                } catch (IOException e) {
                    e.printStackTrace();
                }
            }
        }
        return sb.toString();
    }
}

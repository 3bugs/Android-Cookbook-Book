package com.example.progressdialogdemo;

import android.app.Activity;
import android.app.ProgressDialog;
import android.content.DialogInterface;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.Toast;

public class MainActivity extends Activity {

    ProgressDialog dialog;
    boolean cancel;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);

        Button btnDownload = (Button) findViewById(R.id.download_button);
        btnDownload.setOnClickListener(new View.OnClickListener() {

            @Override
            public void onClick(View v) {
                String title = "Downloading data";
                String msg = "Please wait...";
                dialog = ProgressDialog.show(MainActivity.this, title, msg);

                Thread thread = new Thread(new Runnable() {

                    @Override
                    public void run() {
                        try {
                            Thread.sleep(5000);
                        } catch (InterruptedException e) {
                            e.printStackTrace();
                        }
                        dialog.dismiss();
                    }
                });

                thread.start();
            }
        });

        Button btnDownloadShowProgress = (Button) findViewById(R.id.download_show_progress_button);
        btnDownloadShowProgress.setOnClickListener(new View.OnClickListener() {
        
            @Override
            public void onClick(View v) {
                cancel = false;
                
                dialog = new ProgressDialog(MainActivity.this);
                dialog.setIcon(R.drawable.ic_launcher);
                dialog.setTitle("Downloading data");
                dialog.setProgressStyle(ProgressDialog.STYLE_HORIZONTAL);
                dialog.setMax(15);
                dialog.setButton(DialogInterface.BUTTON_NEGATIVE, "Cancel",
                        new DialogInterface.OnClickListener() {
        
                            @Override
                            public void onClick(DialogInterface dialog, int which) {
                                cancel = true;
                                Toast.makeText(MainActivity.this, "Download canceled!",
                                        Toast.LENGTH_SHORT).show();
                            }
                        });
                dialog.setCancelable(false);
                dialog.setProgress(0);
                dialog.show();
        
                Thread thread = new Thread(new Runnable() {
        
                    @Override
                    public void run() {
                        for (int i = 1; i <= 15; i++) {
                            if (cancel == true) {
                                return;
                            }
                            
                            try {
                                Thread.sleep(1000);
                            } catch (InterruptedException e) {
                                e.printStackTrace();
                            }
                            
                            dialog.incrementProgressBy(1);
                            /* หรือเรียก setProgress แบบนี้ก็ได้เช่นกัน:
                             * dialog.setProgress(i);
                             */
                        }
                        dialog.dismiss();
                    }
                });
                
                thread.start();
            }
        });
    }

}

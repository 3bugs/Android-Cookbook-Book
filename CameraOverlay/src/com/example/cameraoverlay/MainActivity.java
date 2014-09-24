package com.example.cameraoverlay;

import android.app.Activity;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;

public class MainActivity extends Activity {

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);
        
        Button shutterButton = (Button) findViewById(R.id.shutter_button);
        shutterButton.setOnClickListener(new View.OnClickListener() {
            
            @Override
            public void onClick(View v) {
                MySurfaceView.takePicture();
            }
        });
    }

}

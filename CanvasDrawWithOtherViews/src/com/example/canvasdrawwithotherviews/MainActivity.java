package com.example.canvasdrawwithotherviews;

import android.app.Activity;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;

public class MainActivity extends Activity {

    @Override
    public void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);
        
        final MyView drawingSurface = (MyView) findViewById(R.id.drawing_surface);
        
        Button btnDrawCircle = (Button) findViewById(R.id.draw_circle_button);
        btnDrawCircle.setOnClickListener(new View.OnClickListener() {
            
            @Override
            public void onClick(View v) {
                drawingSurface.drawCircle();
            }
        });
        
        Button btnDrawRectangle = (Button) findViewById(R.id.draw_rect_button);
        btnDrawRectangle.setOnClickListener(new View.OnClickListener() {
            
            @Override
            public void onClick(View v) {
                drawingSurface.drawRectangle();
            }
        });
       
        Button btnClearScreen = (Button) findViewById(R.id.clear_screen_button);
        btnClearScreen.setOnClickListener(new View.OnClickListener() {
            
            @Override
            public void onClick(View v) {
                drawingSurface.clearScreen();
            }
        });
    }

}

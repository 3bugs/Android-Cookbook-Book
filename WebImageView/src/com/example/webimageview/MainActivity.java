package com.example.webimageview;

import android.app.Activity;
import android.os.Bundle;

public class MainActivity extends Activity {

    private int imageId[] = new int[] { 
            R.id.image1, R.id.image2, R.id.image3, R.id.image4 };
    
    private String urls[] = new String[] {
            "http://www.provision.co.th/components/com_virtuemart/shop_image/product/_________________517a435824117.jpg",
            "http://www.provision.co.th/components/com_virtuemart/shop_image/product/_________________515d4161ac324.jpg",
            "http://www.provision.co.th/components/com_virtuemart/shop_image/product/_________________5135fb333a2f9.jpg",
            "http://www.provision.co.th/components/com_virtuemart/shop_image/product/_________________5135f653054dd.jpg"
    };

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);
        
        for (int i = 0; i < imageId.length; i++) {
            WebImageView image = (WebImageView) findViewById(imageId[i]);
            image.setPlaceholderImage(R.drawable.placeholder);
            image.setImageUrl(urls[i]);
        }
    }

}

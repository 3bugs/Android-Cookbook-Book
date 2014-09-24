package com.example.pickimage;

import android.net.Uri;
import android.os.Bundle;
import android.provider.MediaStore;
import android.view.View;
import android.widget.Button;
import android.widget.ImageView;
import android.widget.TextView;
import android.app.Activity;
import android.content.Intent;
import android.database.Cursor;
import android.graphics.Bitmap;
import android.graphics.BitmapFactory;

public class MainActivity extends Activity {

    private static final int PICK_IMAGE = 1;
    private TextView text;
    private ImageView image;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);

        Button button = (Button) findViewById(R.id.pick_image_button);
        text = (TextView) findViewById(R.id.text);
        image = (ImageView) findViewById(R.id.image);

        button.setOnClickListener(new View.OnClickListener() {

            @Override
            public void onClick(View v) {
                Intent intent = new Intent();
                intent.setAction(Intent.ACTION_GET_CONTENT);
                intent.setType("image/*");
                startActivityForResult(Intent.createChooser(intent,
                        "Select app to pick image"), PICK_IMAGE);
            }
        });
    }

    @Override
    protected void onActivityResult(int requestCode, int resultCode,
            Intent returnedIntent) {
        super.onActivityResult(requestCode, resultCode, returnedIntent);

        switch (requestCode) {
        case PICK_IMAGE:
            if (resultCode == RESULT_OK) {
                Uri imageUri = returnedIntent.getData();
                String msg = "URI: " + imageUri + "\n";

                String imagePath = findPath(imageUri);
                msg += "Path: " + imagePath;

                text.setText(msg);

                Bitmap imageData = BitmapFactory.decodeFile(imagePath);
                image.setImageBitmap(imageData);

                /*
                InputStream imageStream;
                try {
                    imageStream = getContentResolver()
                            .openInputStream(imageUri);
                    Bitmap imageData = BitmapFactory.decodeStream(imageStream);
                    image.setImageBitmap(imageData);
                } catch (FileNotFoundException e) {
                    e.printStackTrace();
                }
                */
            }
        }
    }

    private String findPath(Uri uri) {
        String imagePath;

        String[] columns = { MediaStore.Images.Media.DATA };
        Cursor cursor = getContentResolver().query(uri, columns, null, null,
                null);
        if (cursor != null) {
            cursor.moveToFirst();
            int columnIndex = cursor
                    .getColumnIndexOrThrow(MediaStore.Images.Media.DATA);
            imagePath = cursor.getString(columnIndex);
        } else {
            imagePath = uri.getPath();
        }

        return imagePath;
    }

}

package com.example.sharedpreferencesdemo;

import android.app.Activity;
import android.content.Context;
import android.content.SharedPreferences;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.RadioGroup;
import android.widget.Toast;

public class MainActivity extends Activity {

    private static final int MALE = 1;
    private static final int FEMALE = 2;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);

        final SharedPreferences sharedPref = getPreferences(Context.MODE_PRIVATE);
        
        final EditText etName = (EditText) findViewById(R.id.name);
        final EditText etAge = (EditText) findViewById(R.id.age);
        final RadioGroup radGroupSex = (RadioGroup) findViewById(R.id.sex_radio_group);
        
        Button btnWriteData = (Button) findViewById(R.id.write_data_button);
        btnWriteData.setOnClickListener(new View.OnClickListener() {
        
            @Override
            public void onClick(View v) {
                SharedPreferences.Editor editor = sharedPref.edit();
                
                editor.putString("name", etName.getText().toString());
                editor.putInt("age", Integer.valueOf(etAge.getText().toString()));
                
                int selectedSex = radGroupSex.getCheckedRadioButtonId();
                if (selectedSex == R.id.male_radio) {
                    editor.putInt("sex", MALE);
                } else if (selectedSex == R.id.female_radio) {
                    editor.putInt("sex", FEMALE);
                }
                
                editor.commit();
                Toast.makeText(MainActivity.this, "Data saved.", Toast.LENGTH_SHORT).show();
            }
        });
        
        Button btnReadData = (Button) findViewById(R.id.read_data_button);
        btnReadData.setOnClickListener(new View.OnClickListener() {
            
            @Override
            public void onClick(View v) {
                String strName = sharedPref.getString("name", null);
                if (etName != null) {
                    etName.setText(strName);
                }
        
                int intAge = sharedPref.getInt("age", -1);
                if (intAge != -1) {
                    etAge.setText(String.valueOf(intAge));
                }
                
                int intSex = sharedPref.getInt("sex", -1);
                if (intSex == MALE) {
                    radGroupSex.check(R.id.male_radio);
                } else if (intSex == FEMALE) {
                    radGroupSex.check(R.id.female_radio);
                }
            }
        });
    }
}

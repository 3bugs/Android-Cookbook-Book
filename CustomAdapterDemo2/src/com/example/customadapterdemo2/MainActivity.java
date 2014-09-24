package com.example.customadapterdemo2;

import android.os.Bundle;
import android.view.View;
import android.widget.ListView;
import android.widget.TextView;
import android.widget.Toast;
import android.app.ListActivity;

public class MainActivity extends ListActivity {

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        // setContentView(R.layout.activity_main);

        Country countries[] = new Country[] {
                new Country(R.drawable.brunei, "Brunei",
                        "Nation of Brunei, the Abode of Peace",
                        "Bandar Seri Begawan"),
                new Country(R.drawable.cambodia, "Cambodia",
                        "Kingdom of Cambodia", "Phnom Penh"),
                new Country(R.drawable.indonesia, "Indonesia",
                        "Republic of Indonesia", "Jakarta"),
                new Country(R.drawable.laos, "Laos",
                        "Lao People's Democratic Republic", "Vientiane"),
                new Country(R.drawable.malaysia, "Malaysia", "Malaysia",
                        "Kuala Lumpur"),
                new Country(R.drawable.myanmar, "Myanmar (Burma)",
                        "Republic of the Union of Myanmar", "Naypyidaw"),
                new Country(R.drawable.philippines, "Philippines",
                        "Republic of the Philippines", "Manila"),
                new Country(R.drawable.singapore, "Singapore",
                        "Republic of Singapore", "Singapore"),
                new Country(R.drawable.thailand, "Thailand",
                        "Kingdom of Thailand", "Bangkok"),
                new Country(R.drawable.vietnam, "Vietnam",
                        "Socialist Republic of Vietnam", "Hanoi") };

        CountryAdapter adapter = new CountryAdapter(this, R.layout.item,
                countries);
        setListAdapter(adapter);
    }

    @Override
    protected void onListItemClick(ListView l, View v, int position, long id) {
        TextView tv = (TextView) v.findViewById(R.id.country_name);

        String msg = "You have selected " + tv.getText();
        msg += " at position " + String.valueOf(position);

        Toast.makeText(this, msg, Toast.LENGTH_SHORT).show();
    }

}

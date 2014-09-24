package com.example.dialogdemo;

import java.util.ArrayList;

import android.app.Activity;
import android.app.AlertDialog;
import android.content.DialogInterface;
import android.os.Bundle;
import android.view.LayoutInflater;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.ListView;
import android.widget.Toast;

public class MainActivity extends Activity {

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);

        // 回枇 Show Dialog
        Button btnShowDialog = (Button) findViewById(R.id.show_dialog_button);
        btnShowDialog.setOnClickListener(new View.OnClickListener() {

            @Override
            public void onClick(View v) {
                AlertDialog.Builder builder = new AlertDialog.Builder(MainActivity.this);

                builder.setTitle("This is a dialog.");
                builder.setMessage("Do you like Android?");
                builder.setPositiveButton("Yes",
                        new DialogInterface.OnClickListener() {

                            @Override
                            public void onClick(DialogInterface dialog, int which) {
                                showToast("Oh yeah!");
                            }
                        });
                builder.setNegativeButton("No",
                        new DialogInterface.OnClickListener() {

                            @Override
                            public void onClick(DialogInterface dialog, int which) {
                                showToast("Oh no!");
                            }
                        });

                builder.show();

                /*
                new AlertDialog.Builder(MainActivity.this)
                        .setTitle("This is a dialog.")
                        .setMessage("Do you like Android?")
                        .setPositiveButton("Yes",
                                new DialogInterface.OnClickListener() {

                                    @Override
                                    public void onClick(DialogInterface dialog, int which) {
                                        showToast("Oh yeah!");
                                    }
                                })
                        .setNegativeButton("No",
                                new DialogInterface.OnClickListener() {

                                    @Override
                                    public void onClick(DialogInterface dialog, int which) {
                                        showToast("Oh no!");
                                    }
                                })
                        .show();
                */
            }
        });

        // 回枇 Show Single-Choice List Dialog (1)
        Button btnShowSingleChoiceListDialog1 = (Button) findViewById(R.id.show_single_choice_list_dialog_1_button);
        btnShowSingleChoiceListDialog1.setOnClickListener(new View.OnClickListener() {

            @Override
            public void onClick(View v) {
                final String[] items = { "Android", "iOS", "Windows Phone", "Firefox OS" }; 
                
                new AlertDialog.Builder(MainActivity.this)
                        .setTitle("What's your favorite mobile OS?")
                        .setItems(items, new DialogInterface.OnClickListener() {
                            
                            @Override
                            public void onClick(DialogInterface dialog, int which) {
                                showToast("You've selected " + items[which]);
                            }
                        })
                        .show();
            }
        });
    
        // 回枇 Show Single-Choice List Dialog (2)
        Button btnShowSingleChoiceListDialog2 = (Button) findViewById(R.id.show_single_choice_list_dialog_2_button);
        btnShowSingleChoiceListDialog2.setOnClickListener(new View.OnClickListener() {

            @Override
            public void onClick(View v) {
                final String[] items = { "Easy", "Medium", "Hard" }; 
                
                new AlertDialog.Builder(MainActivity.this)
                        .setTitle("Select game difficulty")
                        .setSingleChoiceItems(items, 1, null)
                        .setPositiveButton("OK", new DialogInterface.OnClickListener() {
                
                            @Override
                            public void onClick(DialogInterface dialog, int which) {
                                ListView lv = ((AlertDialog) dialog).getListView();
                                int selectedItem = lv.getCheckedItemPosition();
                                showToast("You selected " + items[selectedItem]);
                            }
                        })
                        .show();
            }
        });

        // 回枇 Show Multiple-Choice List Dialog
        Button btnShowMultipleChoiceListDialog = (Button) findViewById(R.id.show_multiple_choice_list_dialog_button);
        btnShowMultipleChoiceListDialog.setOnClickListener(new View.OnClickListener() {

            @Override
            public void onClick(View v) {
                final String[] toppings = { "Caramel", "Hot Fudge", "Whipped Cream", "Banana", "Oreo", "Chocolate Chips" }; 
                final ArrayList<Integer> selectedToppings = new ArrayList<Integer>();
                
                new AlertDialog.Builder(MainActivity.this)
                        .setTitle("Pick your toppings")
                        .setMultiChoiceItems(toppings, null, new DialogInterface.OnMultiChoiceClickListener() {
                            
                            @Override
                            public void onClick(DialogInterface dialog, int which, boolean isChecked) {
                                if (isChecked) {
                                    selectedToppings.add(which);
                                } else if (selectedToppings.contains(which)) {
                                    selectedToppings.remove(Integer.valueOf(which));
                                }
                            }
                        })
                        .setPositiveButton("OK", new DialogInterface.OnClickListener() {
                
                            @Override
                            public void onClick(DialogInterface dialog, int which) {
                                String text = "Selected toppings: \n\n";
                                for (int i : selectedToppings) {
                                    text += " - " + toppings[i] + "\n";
                                }
                                showToast(text);
                            }
                        })
                        .show();
            }
        });

        // 回枇 Show Custom Dialog
        Button btnShowCustomDialog = (Button) findViewById(R.id.show_custom_dialog_button);
        btnShowCustomDialog.setOnClickListener(new View.OnClickListener() {

            @Override
            public void onClick(View v) {
                LayoutInflater inflater = MainActivity.this.getLayoutInflater();
                
                new AlertDialog.Builder(MainActivity.this)
                        .setView(inflater.inflate(R.layout.dialog_login, null))
                        .setPositiveButton("OK", new DialogInterface.OnClickListener() {
                
                            @Override
                            public void onClick(DialogInterface dialog, int which) {
                                // do something
                            }
                        })
                        .setNegativeButton("Cancel", new DialogInterface.OnClickListener() {
                
                            @Override
                            public void onClick(DialogInterface dialog, int which) {
                                // do something
                            }
                        })
                        .show();
            }
        });
        
        // 回枇 Show Custom Dialog and Validate Input
        Button btnShowCustomValidateDialog = (Button) findViewById(R.id.show_custom_dialog_validate_button);
        btnShowCustomValidateDialog.setOnClickListener(new View.OnClickListener() {

            @Override
            public void onClick(View v) {
                LayoutInflater inflater = MainActivity.this.getLayoutInflater();
                
                final AlertDialog loginDialog = new AlertDialog.Builder(MainActivity.this)
                        .setView(inflater.inflate(R.layout.dialog_login, null))
                        .setPositiveButton("OK", new DialogInterface.OnClickListener() {
                
                            @Override
                            public void onClick(DialogInterface dialog, int which) {
                                // do nothing here
                            }
                        })
                        .setNegativeButton("Cancel", new DialogInterface.OnClickListener() {
                
                            @Override
                            public void onClick(DialogInterface dialog, int which) {
                                // do something
                            }
                        })
                        .create();
                
                loginDialog.setOnShowListener(new DialogInterface.OnShowListener() {
                    
                    @Override
                    public void onShow(DialogInterface dialog) {
                        Button button = loginDialog.getButton(AlertDialog.BUTTON_POSITIVE);
                        button.setOnClickListener(new View.OnClickListener() {
                            
                            @Override
                            public void onClick(View v) {
                                EditText etUsername = (EditText) loginDialog.findViewById(R.id.username);
                                EditText etPassword = (EditText) loginDialog.findViewById(R.id.password);
                                
                                String strUsername = etUsername.getText().toString();
                                String strPassword = etPassword.getText().toString();
                                
                                if ((strUsername.equals("promlert")) && (strPassword.equals("123456"))) {
                                    loginDialog.dismiss();
                                    String msg = "Welcome user.";
                                    Toast.makeText(MainActivity.this, msg, Toast.LENGTH_LONG).show();
                                }
                                else {
                                    String msg = "Invalid username or password!\n";
                                    msg += "Please try again.";
                                    Toast.makeText(MainActivity.this, msg, Toast.LENGTH_LONG).show();
                                }
                            }
                        });
                    }
                });
                
                loginDialog.show();
            }
        });
    }

    private void showToast(String text) {
        Toast toast = Toast.makeText(this, text, Toast.LENGTH_LONG);
        toast.show();
    }

}

package com.example.contextualactionbardemo;

import android.os.Bundle;
import android.app.Activity;
import android.view.ActionMode;
import android.view.Menu;
import android.view.MenuItem;
import android.view.View;
import android.widget.ImageView;
import android.widget.TextView;

public class MainActivity extends Activity {

    private String[] actionTitles = { "Share", "Edit", "Delete" };
    private int[] actionIcons = { android.R.drawable.ic_menu_share,
            android.R.drawable.ic_menu_edit,
            android.R.drawable.ic_menu_close_clear_cancel };

    private ActionMode mActionMode;

    private ActionMode.Callback mActionModeCallback = new ActionMode.Callback() {

        @Override
        public boolean onCreateActionMode(ActionMode mode, Menu menu) {
            MenuItem item;

            for (int i = 0; i < actionTitles.length; i++) {
                item = menu.add(Menu.NONE, i, i, actionTitles[i]);
                item.setIcon(actionIcons[i]);
                item.setShowAsAction(MenuItem.SHOW_AS_ACTION_IF_ROOM
                        | MenuItem.SHOW_AS_ACTION_WITH_TEXT);
            }
            return true;
        }

        @Override
        public boolean onPrepareActionMode(ActionMode mode, Menu menu) {
            return false;
        }

        @Override
        public boolean onActionItemClicked(ActionMode mode, MenuItem item) {
            int actionIndex = item.getItemId();
            String actionName = actionTitles[actionIndex];

            TextView text = (TextView) findViewById(R.id.text);
            text.setText("You selected " + actionName);

            mode.finish();
            return true;
        }

        @Override
        public void onDestroyActionMode(ActionMode mode) {
            mActionMode = null;
        }
    };

    @Override
    public void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);

        ImageView image = (ImageView) findViewById(R.id.image);

        image.setOnLongClickListener(new View.OnLongClickListener() {
            public boolean onLongClick(View view) {
                if (mActionMode != null) {
                    return false;
                }

                mActionMode = MainActivity.this
                        .startActionMode(mActionModeCallback);
                return true;
            }
        });
    }

}

package com.example.dylicious.mydoctors;

import android.app.Activity;
import android.content.Intent;
import android.media.Image;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.view.Menu;
import android.view.MenuItem;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.ImageButton;
import android.widget.TextView;

public class EditUser extends Activity {
    SQLControllerUser sqlUser;
    EditText editUserName, editUserMed, editUserTreat, editUserAllergy;
    long _userID;
    Button editUserBtn;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_edit_user);

        sqlUser = new SQLControllerUser(this);
        sqlUser.open();

        editUserName = (EditText)findViewById(R.id.epatname);
        editUserMed = (EditText)findViewById(R.id.epatmed);
        editUserTreat = (EditText)findViewById(R.id.epattreatment);
        editUserAllergy = (EditText)findViewById(R.id.epatallergy);

        Intent editUserIntent = getIntent();
        String user_ID = editUserIntent.getStringExtra("user_ID");
        String user_name = editUserIntent.getStringExtra("user_name");
        String user_med = editUserIntent.getStringExtra("user_med");
        String user_treat = editUserIntent.getStringExtra("user_treat");
        String user_allergy = editUserIntent.getStringExtra("user_allergy");

        _userID = Long.parseLong(user_ID);

        editUserName.setText(user_name);
        editUserMed.setText(user_med);
        editUserTreat.setText(user_treat);
        editUserAllergy.setText(user_allergy);

        editUserBtn = (Button)findViewById(R.id.editpat);
        editUserBtn.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                String update_username = editUserName.getText().toString();
                String update_usermed = editUserMed.getText().toString();
                String update_usertreat = editUserTreat.getText().toString();
                String update_userallergy = editUserAllergy.getText().toString();

                sqlUser.updateUser(_userID, update_username, update_usermed, update_usertreat,
                        update_userallergy);

                Intent backIntent = new Intent(getApplicationContext(),
                        UserList.class).setFlags(Intent.FLAG_ACTIVITY_CLEAR_TOP);
                startActivity(backIntent);

            }
        });
    }

    @Override
    public boolean onCreateOptionsMenu(Menu menu) {
        // Inflate the menu; this adds items to the action bar if it is present.
        getMenuInflater().inflate(R.menu.menu_edit_user, menu);
        return true;
    }

    @Override
    public boolean onOptionsItemSelected(MenuItem item) {
        // Handle action bar item clicks here. The action bar will
        // automatically handle clicks on the Home/Up button, so long
        // as you specify a parent activity in AndroidManifest.xml.
        int id = item.getItemId();

        //noinspection SimplifiableIfStatement
        if (id == R.id.action_settings) {
            return true;
        }

        return super.onOptionsItemSelected(item);
    }
}

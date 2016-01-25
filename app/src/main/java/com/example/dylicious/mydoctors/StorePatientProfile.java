package com.example.dylicious.mydoctors;

import android.app.Activity;
import android.content.ContentValues;
import android.content.Context;
import android.content.Intent;
import android.database.sqlite.SQLiteDatabase;
import android.database.sqlite.SQLiteOpenHelper;
import android.os.Bundle;
import android.util.Log;
import android.view.Menu;
import android.view.MenuItem;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.Toast;


public class StorePatientProfile extends Activity {

    EditText inputUserName;
    EditText inputUserMed;
    EditText inputUserTreatment;
    EditText inputUserAllergy;
    Button btnSaveUserProfile;
    SQLControllerUser sqlUser;


    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_store_patient_profile);
        setTitle("User Profile");

        sqlUser = new SQLControllerUser(this);
        sqlUser.open();

        //edittext for user info
        inputUserName = (EditText)findViewById(R.id.patname);
        inputUserMed = (EditText)findViewById(R.id.patmed);
        inputUserTreatment = (EditText)findViewById(R.id.pattreatment);
        inputUserAllergy = (EditText)findViewById(R.id.patallergy);
        btnSaveUserProfile = (Button)findViewById(R.id.savepat);

//        addPatient();
        btnSaveUserProfile.setOnClickListener(new View.OnClickListener() {
                @Override
                public void onClick(View v)
                {
                    String username = inputUserName.getText().toString();
                    String usermed = inputUserMed.getText().toString();
                    String usertreatment = inputUserTreatment.getText().toString();
                    String userallergy = inputUserAllergy.getText().toString();

                    if (username.matches("") || usermed.matches("") || usertreatment.matches("") ||
                            userallergy.matches(""))
                    {
                        Context context = getApplicationContext();
                        CharSequence text = "Please fill up all the entry fields";
                        int duration = Toast.LENGTH_LONG;

                        Toast toast = Toast.makeText(context, text, duration);
                        toast.show();
                        return;
                    }
                    else
                    {
                        sqlUser.insertUser(username, usermed, usertreatment, userallergy);
                        Intent userView = new Intent(StorePatientProfile.this,
                                UserList.class).setFlags(Intent.FLAG_ACTIVITY_CLEAR_TOP);

                        startActivity(userView);
                    }

                }
            });
        }



    @Override
    public boolean onCreateOptionsMenu(Menu menu) {
        // Inflate the menu; this adds items to the action bar if it is present.
        getMenuInflater().inflate(R.menu.menu_store_patient_profile, menu);
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

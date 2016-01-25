package com.example.dylicious.mydoctors;

import android.app.Activity;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.view.Menu;
import android.view.MenuItem;
import android.view.View;
import android.widget.Button;
import android.widget.CheckBox;
import android.widget.Toast;

public class ViewDoc3 extends Activity {

    CheckBox chkMonday;
    CheckBox chkTuesday;
    CheckBox chkWednesday;
    CheckBox chkThursday;
    CheckBox chkFriday;
    CheckBox chkSaturday;
    CheckBox chkSunday;
    Button saveDocBtn;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_view_doc3);

        addListenerOnButton();
    }

    public void addListenerOnButton()
    {
        chkMonday = (CheckBox)findViewById(R.id.monConsult);
        chkTuesday = (CheckBox)findViewById(R.id.tueConsult);
        chkWednesday = (CheckBox)findViewById(R.id.wedConsult);
        chkThursday = (CheckBox)findViewById(R.id.thursConsult);
        chkFriday = (CheckBox)findViewById(R.id.friConsult);
        chkSaturday = (CheckBox)findViewById(R.id.satConsult);
        chkSunday = (CheckBox)findViewById(R.id.sunConsult);

        saveDocBtn = (Button)findViewById(R.id.confirmDoc);

        saveDocBtn.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v)
            {
                StringBuffer result = new StringBuffer();
                result.append("Monday : ").append(chkMonday.isChecked());
                result.append("\nTuesday : ").append(chkTuesday.isChecked());
                result.append("\nWednesday :").append(chkWednesday.isChecked());
                result.append("\nThursday :").append(chkThursday.isChecked());
                result.append("\nFriday :").append(chkFriday.isChecked());
                result.append("\nSaturday :").append(chkSaturday.isChecked());
                result.append("\nWednesday :").append(chkSunday.isChecked());


                Toast.makeText(ViewDoc3.this, result.toString(),
                        Toast.LENGTH_LONG).show();
            }
        });
    }
    @Override
    public boolean onCreateOptionsMenu(Menu menu) {
        // Inflate the menu; this adds items to the action bar if it is present.
        getMenuInflater().inflate(R.menu.menu_view_doc3, menu);
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

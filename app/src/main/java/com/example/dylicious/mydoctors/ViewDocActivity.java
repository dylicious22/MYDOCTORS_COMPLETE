package com.example.dylicious.mydoctors;

import java.text.SimpleDateFormat;
import java.util.Calendar;
import java.util.Locale;

import android.app.Activity;
import android.app.AlertDialog;
import android.app.DatePickerDialog;
import android.app.DatePickerDialog.OnDateSetListener;
import android.app.Dialog;
import android.app.DialogFragment;
import android.app.TimePickerDialog;
import android.content.Context;
import android.content.Intent;
import android.os.Bundle;
import android.text.InputType;
import android.util.Log;
import android.view.LayoutInflater;
import android.view.Menu;
import android.view.MenuItem;
import android.view.View;
import android.view.View.OnClickListener;
import android.widget.Button;
import android.widget.DatePicker;
import android.widget.EditText;
import android.widget.TextView;
import android.widget.TimePicker;
import android.database.sqlite.*;
import android.widget.Toast;

public class ViewDocActivity extends Activity
{
    EditText inputDocName;
    EditText inputDocAddress;
    EditText inputLoc;
    EditText inputSpecialty;
    EditText inputPhoneNumber;
    EditText inputTime;
    Calendar myTime;
    String inputTimeString;
    SQLControllerDoctor sqlControlDoc;

    SimpleDateFormat dateFormatter;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_view_doc);

        //Database Handler initiator
        sqlControlDoc = new SQLControllerDoctor(this);
        sqlControlDoc.open();

        //set format for date
        dateFormatter = new SimpleDateFormat("dd-MM-yyyy", Locale.US);

        //edittext assign id
        inputDocName = (EditText)findViewById(R.id.docname);
        inputDocAddress = (EditText)findViewById(R.id.docadd);
        inputLoc = (EditText)findViewById(R.id.docloc);
        inputSpecialty = (EditText)findViewById(R.id.docspecialty);
        inputPhoneNumber = (EditText)findViewById(R.id.docnum);
        inputTime = (EditText)findViewById(R.id.doctime);

        //timepicker dialog
        myTime = Calendar.getInstance();
        inputTime.setOnClickListener(new OnClickListener() {
            @Override
            public void onClick(View v)
            {
                int hour = myTime.get(Calendar.HOUR_OF_DAY);
                int min = myTime.get(Calendar.MINUTE);
                TimePickerDialog myTimePickerDialog = new TimePickerDialog(ViewDocActivity.this,
                        new TimePickerDialog.OnTimeSetListener() {
                    @Override
                    public void onTimeSet(TimePicker view, int hourOfDay, int minute)
                    {
                        inputTime.setText(hourOfDay + ":" + minute);
//                        inputTimeString = inputTime.toString();
                    }
                }, hour, min, true);

                myTimePickerDialog.setTitle("Select Time");
                myTimePickerDialog.show();
            }
        });


        //save data
        Button saveDocDtl = (Button)findViewById(R.id.proceed2);
        saveDocDtl.setOnClickListener(new View.OnClickListener()
        {
            @Override
            public void onClick (View v)
            {
                //convert to string
                String docname = inputDocName.getText().toString();
                String docadd = inputDocAddress.getText().toString();
                String docloc = inputLoc.getText().toString();
                String docspecialty = inputSpecialty.getText().toString();
                String docnum = inputPhoneNumber.getText().toString();
                String doctime = inputTime.getText().toString();

                if (docname.matches("") || docadd.matches("") || docloc.matches("") ||
                        docspecialty.matches("") || docnum.matches("") || doctime.matches(""))
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
                    //perform insertion of data
                    sqlControlDoc.insertDoctor(docname, docadd, docloc, docspecialty, docnum, doctime);

                    //intent to listview
                    Intent VDP = new Intent(ViewDocActivity.this, DoctorList.class).setFlags(Intent.FLAG_ACTIVITY_CLEAR_TOP);
                    startActivity(VDP);
                }


            }
        });
    }


    @Override
    public boolean onCreateOptionsMenu(Menu menu) {
        // Inflate the menu; this adds items to the action bar if it is present.
        getMenuInflater().inflate(R.menu.menu_view_doc, menu);
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

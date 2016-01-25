package com.example.dylicious.mydoctors;

import android.app.ActionBar;
import android.app.Activity;
import android.app.TimePickerDialog;
import android.content.Intent;
import android.os.Bundle;
import android.view.Menu;
import android.view.MenuItem;
import android.view.View;
import android.widget.Button;
import android.widget.TextView;
import android.widget.TimePicker;

import java.util.Calendar;


public class ViewDoc2 extends Activity{

//    Button promptDayBtn;
    Calendar calendarConsult;
    TextView consultTimeView = (TextView)findViewById(R.id.ConsultTime);
    Button timePickerBtn = (Button)findViewById(R.id.timepickerdialogbtn);
//    String format = "";
//    TimePicker consultTime;
//    TextView consultTimeOutput;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_view_doc22);

//        consultTime = (TimePicker)findViewById(R.id.timePicker2);
//        consultTimeOutput = (TextView)findViewById(R.id.ConsultTime);
        calendarConsult = Calendar.getInstance();
//
//        consultTime.setCurrentHour(calendarConsult.get(Calendar.HOUR_OF_DAY));
//        consultTime.setCurrentMinute(calendarConsult.get(Calendar.MINUTE));
//
//        consultTime.setOnTimeChangedListener(new TimePicker.OnTimeChangedListener() {
//            @Override
//            public void onTimeChanged(TimePicker view, int hourOfDay, int minute) {
//                Toast.makeText(getApplicationContext(),
//                        "onTimeChanged", Toast.LENGTH_SHORT).show();
//
//                consultTimeOutput.setText("Time: " + hourOfDay + ":" + minute);
//
//            }
//        });
//
//        int hour = consultTime.getCurrentHour();
//        int min = consultTime.getCurrentMinute();
//        showTime(hour, min);

        timePickerBtn.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v)
            {
                new TimePickerDialog(ViewDoc2.this, onTimeSetListener,
                        calendarConsult.get(Calendar.HOUR_OF_DAY),
                        calendarConsult.get(Calendar.MINUTE), true).show();
            }
        });


        Button stp3btn = (Button)findViewById(R.id.proceed3);
        stp3btn.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v)
            {
                Intent intentVD3 = new Intent (getApplicationContext(), ViewDoc3.class);
                startActivity(intentVD3);
            }
        });



    }

//    public void setTime (View v)
//    {
//        int hour = consultTime.getCurrentHour();
//        int min = consultTime.getCurrentMinute();
//        showTime(hour, min);
//    }
//
//    public void showTime(int hour, int min) {
//        if (hour == 0) {
//            hour += 12;
//            format = "AM";
//        }
//        else if (hour == 12) {
//            format = "PM";
//        } else if (hour > 12) {
//            hour -= 12;
//            format = "PM";
//        } else {
//            format = "AM";
//        }
//        consultTimeOutput.setText(new StringBuilder().append(hour).append(" : ").append(min)
//                .append(" ").append(format));
//    }

    @Override
    public boolean onCreateOptionsMenu(Menu menu) {
        // Inflate the menu; this adds items to the action bar if it is present.
        getMenuInflater().inflate(R.menu.menu_view_doc2, menu);
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

    TimePickerDialog.OnTimeSetListener onTimeSetListener = new TimePickerDialog.OnTimeSetListener() {
        @Override
        public void onTimeSet(TimePicker view, int hourOfDay, int minute)
        {
            consultTimeView.setText(hourOfDay + ":" + minute);
        }
    };

}

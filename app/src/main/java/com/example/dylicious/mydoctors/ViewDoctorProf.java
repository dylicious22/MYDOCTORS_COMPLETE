package com.example.dylicious.mydoctors;

import android.app.Activity;
import android.content.ContentUris;
import android.content.Intent;
import android.media.Image;
import android.net.Uri;
import android.os.Bundle;
import android.provider.CalendarContract;
import android.util.Log;
import android.view.View;
import android.view.Menu;
import android.view.MenuItem;
import android.widget.Button;
import android.widget.ImageButton;
import android.widget.TextView;

public class ViewDoctorProf extends Activity {

    SQLControllerDoctor sqlDoc;
    TextView viewDocName, viewDocLoc, viewDocAdd, viewDocNum, viewDocSpecialty, viewDocTime;
    ImageButton editDoctorBtn;
    Button deleteDoctorBtn;
    long _docID;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_view_doctor_prof);

        sqlDoc = new SQLControllerDoctor(this);
        sqlDoc.open();

        //declare textview
        viewDocName = (TextView)findViewById(R.id.docnameview);
        viewDocLoc = (TextView)findViewById(R.id.doclocview);
        viewDocAdd = (TextView)findViewById(R.id.docaddview);
        viewDocNum = (TextView)findViewById(R.id.docnumview);
        viewDocSpecialty = (TextView)findViewById(R.id.docspecialtyview);
        viewDocTime = (TextView)findViewById(R.id.docconsultTime);

        //retrieving data
        Intent intentViewDoc = getIntent();
        final String docID = intentViewDoc.getStringExtra("doc_ID");
        final String docname = intentViewDoc.getStringExtra("doc_Name");
        final String docadd = intentViewDoc.getStringExtra("doc_Add");
        final String docloc = intentViewDoc.getStringExtra("doc_Loc");
        final String docnum = intentViewDoc.getStringExtra("doc_Num");
        final String docspecialty = intentViewDoc.getStringExtra("doc_Spec");
        final String doctime = intentViewDoc.getStringExtra("doc_Time");

        viewDocName.setText(docname);
        viewDocSpecialty.setText(docspecialty);
        viewDocLoc.setText(docloc);
        viewDocNum.setText(docnum);
        viewDocAdd.setText(docadd);
        viewDocTime.setText(doctime);

        _docID = Long.parseLong(docID);

        viewDocNum.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v)
            {
                Intent callIntent = new Intent(Intent.ACTION_CALL, Uri.parse("tel:" + docnum));
                startActivity(callIntent);
            }
        });
        setTitle("Dr. " + docname + "'s Profile");

        editDoctorBtn = (ImageButton)findViewById(R.id.editDoc);
        editDoctorBtn.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v)
            {
                Intent editDocIntent = new Intent(getApplicationContext(), EditDoctor.class);
                editDocIntent.putExtra("doc_ID", docID);
                editDocIntent.putExtra("doc_Name", docname);
                editDocIntent.putExtra("doc_Add", docadd);
                editDocIntent.putExtra("doc_Loc", docloc);
                editDocIntent.putExtra("doc_Num", docnum);
                editDocIntent.putExtra("doc_Spec", docspecialty);
                editDocIntent.putExtra("doc_Time", doctime);
                startActivity(editDocIntent);

            }
        });

        deleteDoctorBtn = (Button)findViewById(R.id.deleteDoc);
        deleteDoctorBtn.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                sqlDoc.deleteDoctor(_docID);
                Intent deleteDocIntent = new Intent(getApplicationContext(),
                        DoctorList.class).setFlags(Intent.FLAG_ACTIVITY_CLEAR_TOP);
                startActivity(deleteDocIntent);
            }
        });


        ImageButton btnAddMenuFooter = (ImageButton)findViewById(R.id.addmenufooter);
        btnAddMenuFooter.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Intent intentAMF = new Intent(getApplicationContext(), AddMenu.class);
                startActivity(intentAMF);
            }
        });


        ImageButton btnDocProfFooter = (ImageButton)findViewById(R.id.viewdocfooter);
        btnDocProfFooter.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Intent intentVDPF = new Intent(getApplicationContext(), DoctorList.class);
                startActivity(intentVDPF);
            }
        });

        ImageButton btnAppViewer = (ImageButton)findViewById(R.id.viewappfooter);
        btnAppViewer.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                long startMillis = System.currentTimeMillis();
                Uri.Builder builder = CalendarContract.CONTENT_URI.buildUpon();
                builder.appendPath("time");
                ContentUris.appendId(builder, startMillis);
                Intent intent = new Intent(Intent.ACTION_VIEW).setData(builder.build());
                startActivity(intent);
            }
        });

        ImageButton btnSearchDocFooter = (ImageButton)findViewById(R.id.searchdocfooter);
        btnSearchDocFooter.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Uri clinicUri = Uri.parse("geo:0,0?q=clinics");
                Intent mapIntent = new Intent(Intent.ACTION_VIEW, clinicUri);
                startActivity(mapIntent);
            }
        });

        ImageButton btnUserProfileFooter = (ImageButton)findViewById(R.id.viewproffooter);
        btnUserProfileFooter.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Intent intentUPF = new Intent(getApplicationContext(), UserList.class);
                startActivity(intentUPF);
            }
        });

    }

    @Override
    public boolean onCreateOptionsMenu(Menu menu) {
        // Inflate the menu; this adds items to the action bar if it is present.
        getMenuInflater().inflate(R.menu.menu_view_doctor_prof, menu);
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

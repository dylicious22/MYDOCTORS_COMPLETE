package com.example.dylicious.mydoctors;

import android.app.Activity;
import android.content.Context;
import android.content.Intent;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.view.Menu;
import android.view.MenuItem;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.Toast;

public class EditDoctor extends Activity {

    EditText editDocName, editDocAdd, editDocLoc, editDocSpec, editDocNum, editDocTime;
    Button editBtn;
    long _docID;
    SQLControllerDoctor sqlDoc;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_edit_doctor);

        sqlDoc = new SQLControllerDoctor(this);
        sqlDoc.open();

        editDocName = (EditText)findViewById(R.id.edocname);
        editDocAdd = (EditText)findViewById(R.id.edocadd);
        editDocLoc = (EditText)findViewById(R.id.edocloc);
        editDocSpec = (EditText)findViewById(R.id.edocspecialty);
        editDocNum = (EditText)findViewById(R.id.edocnum);
        editDocTime = (EditText)findViewById(R.id.edoctime);

        Intent editDoc = getIntent();
        String doc_ID = editDoc.getStringExtra("doc_ID");
        String doc_Name = editDoc.getStringExtra("doc_Name");
        String doc_Add = editDoc.getStringExtra("doc_Add");
        String doc_Loc = editDoc.getStringExtra("doc_Loc");
        String doc_Spec = editDoc.getStringExtra("doc_Spec");
        String doc_Num = editDoc.getStringExtra("doc_Num");
        String doc_Time = editDoc.getStringExtra("doc_Time");

        _docID = Long.parseLong(doc_ID);

        editDocName.setText(doc_Name);
        editDocAdd.setText(doc_Add);
        editDocLoc.setText(doc_Loc);
        editDocSpec.setText(doc_Spec);
        editDocNum.setText(doc_Num);
        editDocTime.setText(doc_Time);


        editBtn = (Button)findViewById(R.id.editbtn);
        editBtn.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v)
            {
                String update_docname = editDocName.getText().toString();
                String update_docadd = editDocAdd.getText().toString();
                String update_docloc = editDocLoc.getText().toString();
                String update_docspec = editDocSpec.getText().toString();
                String update_docnum = editDocNum.getText().toString();
                String update_doctime = editDocTime.getText().toString();

                if (update_docname.matches("") || update_docadd.matches("") ||
                        update_docloc.matches("") || update_docspec.matches("") ||
                        update_docnum.matches("") || update_doctime.matches(""))
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
                    sqlDoc.updateDoctor(_docID, update_docname, update_docadd, update_docloc,
                            update_docspec, update_docnum, update_doctime);

                    Intent backIntent = new Intent(getApplicationContext(),
                            DoctorList.class).setFlags(Intent.FLAG_ACTIVITY_CLEAR_TOP);
                    startActivity(backIntent);
                }

            }
        });




    }


    @Override
    public boolean onCreateOptionsMenu(Menu menu) {
        // Inflate the menu; this adds items to the action bar if it is present.
        getMenuInflater().inflate(R.menu.menu_edit_doctor, menu);
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

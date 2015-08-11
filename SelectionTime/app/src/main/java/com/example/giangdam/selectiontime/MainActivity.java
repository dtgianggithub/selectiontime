package com.example.giangdam.selectiontime;

import android.app.DatePickerDialog;
import android.app.TimePickerDialog;
import android.os.Bundle;
import android.support.v7.app.AppCompatActivity;
import android.view.View;
import android.widget.ArrayAdapter;
import android.widget.Button;
import android.widget.DatePicker;
import android.widget.EditText;
import android.widget.ListView;
import android.widget.TextView;
import android.widget.TimePicker;

import java.text.SimpleDateFormat;
import java.util.ArrayList;
import java.util.Calendar;
import java.util.Date;
import java.util.Locale;


public class MainActivity extends AppCompatActivity {


    TextView lblDateFinish,lblTimeFinish;
    EditText txtJob,txtContent;
    Button btnAdd,btnDateFinish,btnTimeFinish;
    ListView lvListJob;
    ArrayList<JobInWeek>  arrayList = new ArrayList<JobInWeek>();
    ArrayAdapter<JobInWeek> arrayAdapter = null;
    Date datefinish, timefinish;
    Calendar cal;



    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);

        txtJob = (EditText)findViewById(R.id.txtJob);
        txtContent = (EditText)findViewById(R.id.txtContent);

        lblDateFinish = (TextView)findViewById(R.id.lblDateFinish);
        lblTimeFinish = (TextView)findViewById(R.id.lblTimeFinish);

        btnDateFinish = (Button)findViewById(R.id.btnDateFinish);
        btnTimeFinish = (Button)findViewById(R.id.btnTimeFinish);
        btnAdd = (Button)findViewById(R.id.btnAdd);

        lvListJob = (ListView)findViewById(R.id.lvListJob);

        arrayAdapter = new ArrayAdapter<JobInWeek>(this,android.R.layout.simple_list_item_1,arrayList);
        lvListJob.setAdapter(arrayAdapter);


        //get datetime system
        cal = Calendar.getInstance();
        SimpleDateFormat simpleDateFormat = null;
        simpleDateFormat = new SimpleDateFormat("dd/MM/yyyy", Locale.getDefault());

        //display
        lblDateFinish.setText(simpleDateFormat.format(cal.getTime()));

        simpleDateFormat = new SimpleDateFormat("hh:mm a",Locale.getDefault());
        lblTimeFinish.setText(simpleDateFormat.format(cal.getTime()));

        //lay gio theo 24 de lap trinh theo tag
        simpleDateFormat = new SimpleDateFormat("HH:mm",Locale.getDefault());
        lblTimeFinish.setTag(simpleDateFormat.format(cal.getTime()));

        //focus
        txtJob.requestFocus();

        datefinish = cal.getTime();
        timefinish = cal.getTime();



        btnAdd.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                addJob();
            }
        });


        btnDateFinish.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                showDatePicker();
             }
        });

        btnTimeFinish.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                showTimePicker();
            }
        });
    }

    public  void addJob() {
        String title = txtJob.getText().toString();
        String description = txtContent.getText().toString();
        JobInWeek jobinweek = new JobInWeek(title,description,datefinish,timefinish);
        arrayList.add(jobinweek);
        arrayAdapter.notifyDataSetChanged();

        //reset
        txtJob.setText("");
        txtContent.setText("");
        txtJob.requestFocus();
    }


    public void showDatePicker()
    {
        //Show date picker dialog
        DatePickerDialog.OnDateSetListener callback  = new DatePickerDialog.OnDateSetListener() {
            @Override
            public void onDateSet(DatePicker view, int year, int monthOfYear, int dayOfMonth) {
                lblDateFinish.setText(dayOfMonth + "/" + monthOfYear + "/" + year);


                //luu vet
                cal.set(year,monthOfYear,dayOfMonth);
                datefinish = cal.getTime();
            }
        };

        //xu ly show dialog voi ngay thang nam giong voi dang hien thi
        String s = lblDateFinish.getText() + "";
        String strArr[] = s.split("/");
        int date = Integer.parseInt(strArr[0]);
        int month = Integer.parseInt(strArr[0]);
        int year = Integer.parseInt(strArr[0]);

        DatePickerDialog pic = new DatePickerDialog(MainActivity.this,callback,year,month,date);
        pic.setTitle("Select date finish");
        pic.show();

    }


    public void showTimePicker()
    {
        //xu ly cap nhat khi dialog dong(khi da set time)
        TimePickerDialog.OnTimeSetListener callback = new TimePickerDialog.OnTimeSetListener() {
            @Override
            public void onTimeSet(TimePicker view, int hourOfDay, int minute) {
                //xu ly ngay gio vua set
                String s = hourOfDay+ ":" + minute;
                int temphour = hourOfDay;
                if(temphour> 12)
                    temphour = temphour - 12;

                lblTimeFinish.setText(temphour + ":" + minute + (hourOfDay > 12 ? "PM" : "AM"));

                //luu gio thuc vao tag
                lblTimeFinish.setTag(s);

                //luu vet
                cal.set(Calendar.HOUR_OF_DAY, hourOfDay);
                cal.set(Calendar.MINUTE,minute);
                timefinish = cal.getTime();

            }
        };


        //Xu ly khi hien thi dialog
        String s = lblTimeFinish.getTag()+"";
        String strArr[] = s.split(":");
        int hour = Integer.parseInt(strArr[0]);
        int _minute = Integer.parseInt(strArr[1]);
        TimePickerDialog timePickerDialog = new TimePickerDialog(MainActivity.this,callback,hour,_minute,true);
        timePickerDialog.setTitle("Select time finish");
        timePickerDialog.show();
    }


}

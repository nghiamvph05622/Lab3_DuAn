package com.example.admin.lab3_duan;

import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.TextView;

public class MainActivity extends AppCompatActivity {

    public static final String SERVER_NAME = "http://truongthptgvc.000webhostapp.com/studen/studen_get.php";
   // public static final String SERVER_NAME = "http://10.255.59.42/student_get/index.php";

    EditText edName, edScore;
    Button btSend;
    TextView tvResult;
    String strName, strScore;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);

        edName = findViewById(R.id.edName);
        edScore = findViewById(R.id.edScore);
        tvResult = findViewById(R.id.tvResult);
        btSend = findViewById(R.id.btSend);
//        thuc hien su kien
        btSend.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                switch (view.getId()){
                    case R.id.btSend:
                        strName = edName.getText().toString();
                        strScore = edScore.getText().toString();
                        BackgroundTask_GE backgroundTask = new BackgroundTask_GE(MainActivity.this, tvResult, strName, strScore);
                        backgroundTask.execute();
                        break;
                }
            }
        });
    }
}

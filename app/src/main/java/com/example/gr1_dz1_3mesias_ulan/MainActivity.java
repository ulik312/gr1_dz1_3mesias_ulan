package com.example.gr1_dz1_3mesias_ulan;


import androidx.appcompat.app.AppCompatActivity;

import android.content.Intent;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.Toast;

public class MainActivity extends AppCompatActivity {

    private Button btnNex;
    private EditText email, them, sms;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);
        btnNex = findViewById(R.id.btn_Nex);
        email = findViewById(R.id.edT_email);
        them = findViewById(R.id.edT_them);
        sms = findViewById(R.id.edT_sms);

        EditText[] ets = {email,them,sms};
        for (EditText et: ets) {
            et.addTextChangedListener(new BaseTextChangeListener(){
                @Override
                public void onTextChanged(CharSequence text, int i, int i1, int i2) {
                    super.onTextChanged(text, i, i1, i2);
                    btnNex.setEnabled(isValid());
                }

            });

        }
        btnNex.setOnClickListener(View -> {
            if (isValid()) {
                Intent i = new Intent(Intent.ACTION_SEND);
                i.setType("text/htm1");
                i.putExtra(Intent.EXTRA_EMAIL,new String[]{String.valueOf(email.getText())});
                i.putExtra(Intent.EXTRA_SUBJECT,them.getText().toString());
                i.putExtra(Intent.EXTRA_TEXT,sms.getText());
                i.setPackage("com.google.android.gm");
                try {
                    startActivity(Intent.createChooser(i,"Send mail..."));
                }catch(android.content.ActivityNotFoundException ex){
                    Toast.makeText(MainActivity.this,"There are no email clients installed.",Toast.LENGTH_SHORT).show();
                }
            }else {
                Toast.makeText(this,"заполнете все поля", Toast.LENGTH_LONG).show();
            }
        });

    }

    private boolean isValid() {
        if (!email.getText().toString().equals("") && !them.getText().toString().equals("")&& !sms.getText().toString().equals("")){
            return true;
        }
        return false;
    }
}
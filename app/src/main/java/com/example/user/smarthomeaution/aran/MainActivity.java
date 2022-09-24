package com.example.user.smarthomeaution.aran;

import androidx.appcompat.app.AppCompatActivity;

import android.content.Context;
import android.content.Intent;
import android.content.SharedPreferences;
import android.os.Bundle;
import android.speech.RecognizerIntent;
import android.util.Log;
import android.view.View;
import android.widget.Button;
import android.widget.CompoundButton;
import android.widget.ImageView;
import android.widget.Switch;
import android.widget.Toast;

import com.google.firebase.database.DatabaseReference;
import com.google.firebase.database.FirebaseDatabase;

import java.util.ArrayList;

public class MainActivity extends AppCompatActivity {

    String command, data,value;

    ImageView light_info, fan_info,powerlineinfo,info_door;
    Switch aSwitch,bSwitch,languageswitch,cSwitch,switch_door;
    Button low,medium,high;

    String light_toast = "On\nCommand : Light On\nOff\nCommand : Light off.";
    String light_toast_bn = "On\nCommand : লাইট জ্বালিয়ে দাও\nOff\nCommand : লাইট বন্ধ করো";
    String door_toast = "On\nCommand :  Door Open\nOff\nCommand : Door close.";
    String door_toast_bn = "On\nCommand : দরজা খোল\nOff\nCommand : দরজাটা বন্ধ কর";
    String fan_toast = "On\nCommand : Fan on\nOff\nCommand : Fan off.";
    String fan_toast_bn = "On\nCommand : ফ্যান চালু করো\nOff\nCommand : ফ্যান বন্ধ করো";
    String power_toast = "On\nCommand : Line on\nOff\nCommand : Line off.";
    String power_toast_bn = "On\nCommand : লাইন চালু করো\nOff\nCommand : লাইন বন্ধ করো";
    public static final String MyPREFERENCES = "com.example.user.smarthomeaution.aran" ;
    SharedPreferences sharedpreferences;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);

        getSupportActionBar().hide();  //hide actionBar


        light_info = findViewById(R.id.info1);
        fan_info = findViewById(R.id.info2);
        powerlineinfo = findViewById(R.id.info3);
        info_door = findViewById(R.id.info_door);
        switch_door = findViewById(R.id.switch_door);

        aSwitch = findViewById(R.id.switch1);
        bSwitch = findViewById(R.id.switch2);
        cSwitch = findViewById(R.id.switch3);
        languageswitch = findViewById(R.id.languageswitch);
        low = findViewById(R.id.low);
        medium = findViewById(R.id.medium);
        high = findViewById(R.id.high);

        sharedpreferences = getSharedPreferences(MyPREFERENCES, Context.MODE_PRIVATE);

        String lang = sharedpreferences.getString("lang", "");
        if(lang.equals("bn_BD")){
            languageswitch.setChecked(true);
        }else{
            languageswitch.setChecked(false);
        }

        getchecked();


        light_info.setOnClickListener(new View.OnClickListener() {    //Toast
            @Override
            public void onClick(View v) {
                SharedPreferences shared = getSharedPreferences(MyPREFERENCES, MODE_PRIVATE);
                String lang = shared.getString("lang", "");
                if(lang.equals("bn_BD")){
                    Toast.makeText(MainActivity.this, light_toast_bn, Toast.LENGTH_LONG).show();
                }else{
                    Toast.makeText(MainActivity.this, light_toast, Toast.LENGTH_LONG).show();
                }

            }
        });

        fan_info.setOnClickListener(new View.OnClickListener() {   //Toast
            @Override
            public void onClick(View v) {
                SharedPreferences shared = getSharedPreferences(MyPREFERENCES, MODE_PRIVATE);
                String lang = shared.getString("lang", "");
                if(lang.equals("bn_BD")){
                    Toast.makeText(MainActivity.this, fan_toast_bn, Toast.LENGTH_LONG).show();
                }else{
                    Toast.makeText(MainActivity.this, fan_toast, Toast.LENGTH_LONG).show();
                }
            }
        });

        powerlineinfo.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {

                SharedPreferences shared = getSharedPreferences(MyPREFERENCES, MODE_PRIVATE);
                String lang = shared.getString("lang", "");
                if(lang.equals("bn_BD")){
                    Toast.makeText(MainActivity.this, power_toast_bn, Toast.LENGTH_LONG).show();
                }else{
                    Toast.makeText(MainActivity.this, power_toast, Toast.LENGTH_LONG).show();
                }

            }
        });
        info_door.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                SharedPreferences shared = getSharedPreferences(MyPREFERENCES, MODE_PRIVATE);
                String lang = shared.getString("lang", "");
                if(lang.equals("bn_BD")){
                    Toast.makeText(MainActivity.this, door_toast_bn, Toast.LENGTH_LONG).show();
                }else{
                    Toast.makeText(MainActivity.this, door_toast, Toast.LENGTH_LONG).show();
                }
            }
        });


        //light switch
        aSwitch.setOnCheckedChangeListener(new CompoundButton.OnCheckedChangeListener() {
            @Override
            public void onCheckedChanged(CompoundButton buttonView, boolean isChecked) {
                if(aSwitch.isChecked())
                {
                    value = "1";
                    // Write a message to the database
                    FirebaseDatabase database = FirebaseDatabase.getInstance();
                    DatabaseReference myRef = database.getReference("light");

                    myRef.setValue(2);

                    SharedPreferences.Editor editor = sharedpreferences.edit();
                    editor.putString("light", "2");
                    editor.commit();

                }
                else {
                    value = "2";
                    FirebaseDatabase database = FirebaseDatabase.getInstance();
                    DatabaseReference myRef = database.getReference("light");

                    myRef.setValue(1);

                    SharedPreferences.Editor editor = sharedpreferences.edit();
                    editor.putString("light", "1");
                    editor.commit();

                }
            }
        });

        switch_door.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                if(switch_door.isChecked())
                {
                    value = "1";
                    // Write a message to the database
                    FirebaseDatabase database = FirebaseDatabase.getInstance();
                    DatabaseReference myRef = database.getReference("doorlock");

                    myRef.setValue(2);

                    SharedPreferences.Editor editor = sharedpreferences.edit();
                    editor.putString("doorlock", "2");
                    editor.commit();

                }
                else {
                    value = "2";
                    FirebaseDatabase database = FirebaseDatabase.getInstance();
                    DatabaseReference myRef = database.getReference("doorlock");

                    myRef.setValue(1);

                    SharedPreferences.Editor editor = sharedpreferences.edit();
                    editor.putString("doorlock", "1");
                    editor.commit();

                }
            }
        });

        cSwitch.setOnCheckedChangeListener(new CompoundButton.OnCheckedChangeListener() {
            @Override
            public void onCheckedChanged(CompoundButton buttonView, boolean isChecked) {
                if(cSwitch.isChecked())
                {
                    // Write a message to the database
                    FirebaseDatabase database = FirebaseDatabase.getInstance();
                    DatabaseReference myRef = database.getReference("powerline");

                    myRef.setValue(2);

                    SharedPreferences.Editor editor = sharedpreferences.edit();
                    editor.putString("powerline", "2");
                    editor.commit();

                }
                else {
                    FirebaseDatabase database = FirebaseDatabase.getInstance();
                    DatabaseReference myRef = database.getReference("powerline");

                    myRef.setValue(1);

                    SharedPreferences.Editor editor = sharedpreferences.edit();
                    editor.putString("powerline", "1");
                    editor.commit();

                }
            }
        });

        languageswitch.setOnCheckedChangeListener(new CompoundButton.OnCheckedChangeListener() {
            @Override
            public void onCheckedChanged(CompoundButton compoundButton, boolean b) {
                if(languageswitch.isChecked()){
                    SharedPreferences.Editor editor = sharedpreferences.edit();
                    editor.putString("lang", "bn_BD");
                    editor.commit();
                }else{
                    SharedPreferences.Editor editor = sharedpreferences.edit();
                    editor.putString("lang", "en_US");
                    editor.commit();
                }
            }
        });


        bSwitch.setOnCheckedChangeListener(new CompoundButton.OnCheckedChangeListener() {
            @Override
            public void onCheckedChanged(CompoundButton buttonView, boolean isChecked) {
                if(bSwitch.isChecked())
                {
                    FirebaseDatabase database = FirebaseDatabase.getInstance();
                    DatabaseReference myRef = database.getReference("fan");

                    myRef.setValue(100);
                    SharedPreferences.Editor editor = sharedpreferences.edit();
                    editor.putString("fan", "200");
                    editor.commit();
                }

                else
                {
                    FirebaseDatabase database = FirebaseDatabase.getInstance();
                    DatabaseReference myRef = database.getReference("fan");
                    myRef.setValue(10);SharedPreferences.Editor editor = sharedpreferences.edit();
                    editor.putString("fan", "10");
                    editor.commit();


                }
            }
        });

        low.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                FirebaseDatabase database = FirebaseDatabase.getInstance();
                DatabaseReference myRef = database.getReference("fan");

                myRef.setValue(20);
                SharedPreferences.Editor editor = sharedpreferences.edit();
                editor.putString("fan", "20");
                editor.commit();
            }
        });

        medium.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                FirebaseDatabase database = FirebaseDatabase.getInstance();
                DatabaseReference myRef = database.getReference("fan");

                myRef.setValue(50);
                SharedPreferences.Editor editor = sharedpreferences.edit();
                editor.putString("fan", "50");
                editor.commit();
            }
        });

        high.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                FirebaseDatabase database = FirebaseDatabase.getInstance();
                DatabaseReference myRef = database.getReference("fan");

                myRef.setValue(200);
                SharedPreferences.Editor editor = sharedpreferences.edit();
                editor.putString("fan", "200");
                editor.commit();
            }
        });
    }

    public void getchecked(){
        SharedPreferences shared = getSharedPreferences(MyPREFERENCES, MODE_PRIVATE);
        String fan = shared.getString("fan", "");
        String light = shared.getString("light", "");
        String powerline = shared.getString("powerline", "");
        String doorlock = shared.getString("doorlock", "");

        if(fan.equals("100")){
            bSwitch.setChecked(true);
        }else if(fan.equals("50")){
            bSwitch.setChecked(true);
        }else if(fan.equals("30")){
            bSwitch.setChecked(true);
        }else if(fan.equals("20")){
            bSwitch.setChecked(true);
        } else{
            bSwitch.setChecked(false);
        }

        if(light.equals("2")){
            aSwitch.setChecked(true);
        }else{
            aSwitch.setChecked(false);
        }

        if(powerline.equals("2")){
            cSwitch.setChecked(true);
        }else{
            cSwitch.setChecked(false);
        }

        if(doorlock.equals("2")){
            switch_door.setChecked(true);
        }else{
            switch_door.setChecked(false);
        }

    }

    public void getSpeechInput(View view) {

        Intent intent = new Intent(RecognizerIntent.ACTION_RECOGNIZE_SPEECH);
        intent.putExtra(RecognizerIntent.EXTRA_LANGUAGE_MODEL, RecognizerIntent.LANGUAGE_MODEL_FREE_FORM);
        SharedPreferences shared = getSharedPreferences(MyPREFERENCES, MODE_PRIVATE);
        String lang = shared.getString("lang", "");
        intent.putExtra(RecognizerIntent.EXTRA_LANGUAGE, lang);

        try {
            startActivityForResult(intent, 10);
        }
        catch (Exception e) {
            Toast
                    .makeText(MainActivity.this, " " + e.getMessage(),
                            Toast.LENGTH_SHORT)
                    .show();
        }
    }

    @Override
    protected void onActivityResult(int requestCode, int resultCode, Intent data) {
        super.onActivityResult(requestCode, resultCode, data);

        switch (requestCode) {
            case 10:
                if (resultCode == RESULT_OK && data != null) {
                    ArrayList<String> result = data.getStringArrayListExtra(RecognizerIntent.EXTRA_RESULTS);
                    command = result.get(0);
                    Toast.makeText(MainActivity.this, command, Toast.LENGTH_SHORT).show();
                    Log.e("Command",command);
                    if (!command.isEmpty()) {
                        if (command.equals("light on")) {
                            value = "1";
                            aSwitch.setChecked(true);
                        } else if (command.equals("light off")) {
                            value = "2";
                            aSwitch.setChecked(false);
                        }else if (command.equals("light of")) {
                            value = "2";
                            aSwitch.setChecked(false);
                        }else if (command.equals("লাইট জ্বালিয়ে দাও")) {
                            value = "2";
                            aSwitch.setChecked(true);
                        }else if (command.equals("লাইট বন্ধ করো")) {
                            value = "2";
                            aSwitch.setChecked(false);
                        } else if (command.equals("fan on")) {
                            bSwitch.setChecked(true);
                        } else if (command.equals("fan off")) {
                            bSwitch.setChecked(false);
                        }else if (command.equals("fan of")) {
                            bSwitch.setChecked(false);
                        } else if (command.equals("ফ্যান চালু করো")) {
                            bSwitch.setChecked(true);
                        }else if (command.equals("ফ্যান বন্ধ করো")) {
                            bSwitch.setChecked(false);
                        } else if (command.equals("line on")) {
                            cSwitch.setChecked(true);
                        } else if (command.equals("line off")) {
                            cSwitch.setChecked(false);
                        }else if (command.equals("line of")) {
                            cSwitch.setChecked(false);
                        } else if (command.equals("লাইন চালু করো")) {
                            cSwitch.setChecked(true);
                        }else if (command.equals("লাইন বন্ধ করো")) {
                            cSwitch.setChecked(false);
                        }
                        else if (command.equals("দরজা খোলো")) {
                            switch_door.setChecked(true);
                        }
                        else if (command.equals("দরজাটা বন্ধ করো")) {
                            switch_door.setChecked(false);
                        }
                        else if (command.equals("Door Open")) {
                            value = "2";
                            switch_door.setChecked(false);
                        }
                        else if (command.equals("Door Close")) {
                            value = "2";
                            switch_door.setChecked(false);
                        }

                    }
                }
                break;
        }
    }



    @Override
    protected void onStart() {
        super.onStart();
    }
}
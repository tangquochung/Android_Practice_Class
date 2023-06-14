package com.example.homework_week4;

import androidx.annotation.Nullable;
import androidx.appcompat.app.AppCompatActivity;

import android.content.Intent;
import android.graphics.Bitmap;
import android.net.Uri;
import android.os.Bundle;
import android.os.Environment;
import android.os.StrictMode;
import android.provider.MediaStore;
import android.util.Log;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.ImageView;
import android.widget.RadioButton;
import android.widget.RadioGroup;
import android.widget.Toast;

import com.example.homework_week4.model.Profile;
import com.google.gson.Gson;

import java.io.File;
import java.io.FileInputStream;
import java.io.FileNotFoundException;
import java.io.FileOutputStream;
import java.io.IOException;
import java.text.SimpleDateFormat;
import java.util.Date;

public class Ex3 extends AppCompatActivity {

    Button btn_change;
    ImageView img_profile_photo;
    EditText edt_name;
    EditText edt_email;
    EditText edt_phone;
    Button btn_save;
    Button btn_cancel;
    RadioGroup radioGroup_gender;
    RadioButton radio_btn_male;
    RadioButton radio_btn_female;
    int isMale; // 1 is male, 0 is female
    Profile profile;
    private static final int REQUEST_IMAGE_CAPTURE = 1;
    Gson gson;

    Profile userProfile;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_ex3);
        gson = new Gson();

        radio_btn_female = (RadioButton) findViewById(R.id.radio_btn_female);
        radio_btn_male = (RadioButton) findViewById(R.id.radio_btn_male);

        btn_change = (Button) findViewById(R.id.btn_change);
        img_profile_photo = (ImageView) findViewById(R.id.img_profile_photo);

        edt_name = (EditText) findViewById(R.id.edt_name);
        edt_phone = (EditText) findViewById(R.id.edt_phone);
        edt_email = (EditText) findViewById(R.id.edt_email);

        btn_save = (Button) findViewById(R.id.btn_save);
        btn_cancel = (Button) findViewById(R.id.btn_cancel);

        // Create path to store profile file
        File path = getApplicationContext().getFilesDir();

        // Check if profile file exists
        File profileFile = new File(path + "/profile.json");
        boolean isProfileExist = profileFile.exists();

        // if profile.json exist, read from it and show to user
        if (isProfileExist == true) {
            File readFrom = new File(path, "profile.json");
            String profileJSONString;
            byte[] profileByte = new byte[(int) readFrom.length()];

            try {
                FileInputStream inputStream = new FileInputStream(readFrom);
                inputStream.read(profileByte);

            } catch (IOException e) {
                e.printStackTrace();
            }
            profileJSONString = new String(profileByte);
            userProfile = gson.fromJson(profileJSONString, Profile.class);

            edt_name.setText(userProfile.getName());
            edt_email.setText(userProfile.getEmail());
            edt_phone.setText(userProfile.getPhone());

            if (userProfile.getGender() == 1) {
                radio_btn_male.setChecked(true);
            }
            else if (userProfile.getGender() == 0) {
                radio_btn_female.setChecked(true);
            }
        }



        btn_save.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                profile = new Profile(
                        edt_name.getText().toString(),
                        edt_email.getText().toString(),
                        edt_phone.getText().toString(),
                        isMale);

                String profileJSON = gson.toJson(profile);

                // write user profile to profile.json
                try {
                    FileOutputStream writer = new FileOutputStream(new File(path, "profile.json"));
                    Log.d("PATH", path.toString());
                    writer.write(profileJSON.getBytes());
                    writer.close();
                    runOnUiThread(new Runnable() {
                        @Override
                        public void run() {
                            Toast.makeText(Ex3.this, "Saved your profile", Toast.LENGTH_SHORT).show();
                        }
                    });
                } catch (IOException e) {
                    e.printStackTrace();
                }

            }
        });

        btn_change.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Thread openCamera = new Thread(new Runnable() {
                    @Override
                    public void run() {
                        try {
                            StrictMode.VmPolicy.Builder builder = new StrictMode.VmPolicy.Builder();
                            StrictMode.setVmPolicy(builder.build());

                            Intent cameraIntent = new Intent(MediaStore.ACTION_IMAGE_CAPTURE);

                            File imageFolder = new File(Environment.getExternalStorageDirectory(), "hww4");
                            imageFolder.mkdirs();

                            String timestamp = new SimpleDateFormat("yyyyMMdd_HHmmss").format(new Date());

                            File imageFile = new File(imageFolder, "test_" + timestamp + ".png");
                            Uri uriSavedImage = Uri.fromFile(imageFile);


//                            cameraIntent.putExtra(MediaStore.EXTRA_OUTPUT, uriSavedImage);
                            startActivityForResult(cameraIntent, REQUEST_IMAGE_CAPTURE);
                        } catch (Exception e) {
                            e.printStackTrace();
                        }
                    }
                });
                openCamera.start();


            }
        });
    }

    @Override
    protected void onActivityResult(int requestCode, int resultCode, @Nullable Intent data) {
        super.onActivityResult(requestCode, resultCode, data);
        if (requestCode == REQUEST_IMAGE_CAPTURE && resultCode == RESULT_OK) {
            assert data != null;
            Bundle extras = data.getExtras();
            assert extras != null;
            Bitmap imageBitmap = (Bitmap) extras.get("data");
            img_profile_photo.setImageBitmap(imageBitmap);
        }
    }

    public void onRadioButtonClicked(View view) {
        boolean checked = ((RadioButton) view).isChecked();

        if (view.getId() == R.id.radio_btn_male) {
            isMale = 1;
        }
        else if (view.getId() == R.id.radio_btn_female) {
            isMale = 0;
        }
    }
}
package com.MyTask.phonebook.Views;

import android.Manifest;
import android.content.Intent;
import android.content.pm.PackageManager;
import android.net.Uri;
import android.os.Bundle;
import android.view.View;
import android.widget.AdapterView;
import android.widget.TextView;
import android.widget.Toast;

import com.MyTask.phonebook.R;
import com.MyTask.phonebook.Utils.Constants;
import com.MyTask.phonebook.model.PersonalDataModel;
import com.MyTask.phonebook.viewModels.PersonalDataViewModel;

import java.util.Locale;

import androidx.annotation.Nullable;
import androidx.appcompat.app.AppCompatActivity;
import androidx.core.app.ActivityCompat;
import androidx.core.content.ContextCompat;
import androidx.lifecycle.Observer;
import androidx.lifecycle.ViewModelProviders;
import butterknife.BindView;
import butterknife.ButterKnife;
import de.hdodenhof.circleimageview.CircleImageView;

public class DetailsActivity extends AppCompatActivity  implements
        android.view.View.OnClickListener {

    @BindView(R.id.profile_image)
    CircleImageView profileImage;
    @BindView(R.id.firstname)
    TextView firstname;
    @BindView(R.id.Lastname)
    TextView Lastname;
    @BindView(R.id.code)
    TextView code;
    @BindView(R.id.phone)
    TextView phone;
    @BindView(R.id.email)
    TextView email;
    @BindView(R.id.latitude)
    TextView latitude;
    @BindView(R.id.longitude)
    TextView longitude;

    String file;
    int id;

    PersonalDataViewModel personalDataViewModel;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_details);
        ButterKnife.bind(this);

         id = getIntent().getIntExtra("id",0);
        personalDataViewModel = ViewModelProviders.of(this).get(PersonalDataViewModel.class);


        personalDataViewModel.getPersonalData(id).observe(this, new Observer<PersonalDataModel>() {
            @Override
            public void onChanged(@Nullable PersonalDataModel personalDataModels) {

                firstname.setText(personalDataModels.getFirstName());
                Lastname.setText(personalDataModels.getLastName());
                phone.setText(personalDataModels.getPhone());
                latitude.setText(personalDataModels.getLatitude() + "");
                longitude.setText(personalDataModels.getLongitude() + "");
                email.setText(personalDataModels.getEmail());
                file=personalDataModels.getImage();
                if(!file.equals("")) {
                    Constants.loadimage(profileImage,file);
                }
                code.setText(personalDataModels.getCode());

            }
        });


        latitude.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {

                String geoUri = "http://maps.google.com/maps?q=loc:" + latitude.getText() + "," + longitude.getText() + " (" + "" + ")";
                Intent intent = new Intent(android.content.Intent.ACTION_VIEW,
                        Uri.parse(geoUri));
                startActivity(intent);
            }
        });

        longitude.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {

                String geoUri = "http://maps.google.com/maps?q=loc:" + latitude.getText() + "," + longitude.getText() + " (" + "" + ")";
                Intent intent = new Intent(android.content.Intent.ACTION_VIEW,
                        Uri.parse(geoUri));
                startActivity(intent);
            }
        });

        email.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {

                Intent emailIntent = new Intent(Intent.ACTION_SEND);

                emailIntent.setData(Uri.parse("mailto:"));
                emailIntent.setType("text/plain");
                emailIntent.putExtra(Intent.EXTRA_EMAIL, email.getText()+"");

                try {
                    startActivity(Intent.createChooser(emailIntent, "Send mail..."));
                }
                catch (android.content.ActivityNotFoundException ex) {
                    Toast.makeText(DetailsActivity.this, "There is no email client installed.", Toast.LENGTH_SHORT).show();
                }



            }
        });


        phone.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                Intent callIntent = new Intent(Intent.ACTION_CALL);
                callIntent.setData(Uri.parse("tel:"+code.getText()+phone.getText()+""));

                if (ContextCompat.checkSelfPermission(DetailsActivity.this, Manifest.permission.CALL_PHONE) != PackageManager.PERMISSION_GRANTED) {
                    ActivityCompat.requestPermissions(DetailsActivity.this, new String[]{Manifest.permission.CALL_PHONE},1);

                }

                if (ActivityCompat.checkSelfPermission(DetailsActivity.this,
                        Manifest.permission.CALL_PHONE) == PackageManager.PERMISSION_GRANTED) {
                    startActivity(callIntent);

                }

            }
        });


    }

    @Override
    public void onClick( View view) {

        switch (view.getId()) {
            case R.id.phone:

                break;
            case R.id.email:
                break;
            case R.id.latitude:
                Intent intent = new Intent(android.content.Intent.ACTION_VIEW,
                        Uri.parse("http://maps.google.com/maps?saddr=20.344,34.34&daddr=20.5666,45.345"));
                startActivity(intent);

                break;
            case R.id.longitude:
              ;
                break;
            default:
                break;


        }
    }
}

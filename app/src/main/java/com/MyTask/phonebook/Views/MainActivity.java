package com.MyTask.phonebook.Views;

import android.Manifest;
import android.content.Context;
import android.content.ContextWrapper;
import android.content.Intent;
import android.content.pm.PackageManager;
import android.graphics.Bitmap;
import android.graphics.BitmapFactory;
import android.net.Uri;
import android.os.Bundle;
import android.provider.MediaStore;
import android.util.Log;
import android.view.View;
import android.widget.Button;
import android.widget.FrameLayout;
import android.widget.ImageView;
import android.widget.TextView;

import com.MyTask.phonebook.R;
import com.MyTask.phonebook.Utils.Constants;
import com.MyTask.phonebook.model.CodeModel;
import com.MyTask.phonebook.viewModels.CodeViewModel;

import java.io.File;
import java.io.FileOutputStream;
import java.io.IOException;
import java.util.List;

import androidx.appcompat.app.AppCompatActivity;
import androidx.core.app.ActivityCompat;
import androidx.core.content.ContextCompat;
import androidx.fragment.app.Fragment;
import androidx.fragment.app.FragmentTransaction;
import androidx.lifecycle.Observer;
import androidx.lifecycle.ViewModelProviders;
import butterknife.BindView;
import butterknife.ButterKnife;

import static com.MyTask.phonebook.Utils.Constants.RESULT_LOAD_Code;
import static com.MyTask.phonebook.Utils.Constants.RESULT_LOAD_IMAGE;

public class MainActivity extends AppCompatActivity implements AddContactFragment.OnFragmentInteractionListener,
ContactListFragment.OnFragmentInteractionListener{

    @BindView(R.id.imageView)
    ImageView imageView;
    @BindView(R.id.textView)
    TextView textView;
    @BindView(R.id.add)
    Button add;
    @BindView(R.id.cotactlist)
    Button cotactlist;
    @BindView(R.id.content)
    FrameLayout content;

    ImageView profileImage;
    Fragment addContactFragment;

    CodeViewModel mViewModel;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);

        ButterKnife.bind(this);


        add.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                FragmentTransaction ft = getSupportFragmentManager().beginTransaction();
                AddContactFragment productFragment = AddContactFragment.newInstance(0);
                ft.add(R.id.content, productFragment);
                ft.addToBackStack("backStack");
                ft.commit();
            }
        });


        cotactlist.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {

                FragmentTransaction ft = getSupportFragmentManager().beginTransaction();
                ContactListFragment contactListFragment  = ContactListFragment.newInstance();
                ft.add(R.id.content, contactListFragment);
                ft.addToBackStack("backStack");
                ft.commit();

            }
        });


/*        mViewModel = ViewModelProviders.of(this).get(CodeViewModel.class);

        mViewModel.codeDataModel.observe(this, new Observer<List<CodeModel>>() {
            @Override
            public void onChanged(List<CodeModel> codeModels) {

            }
        });*/


    }

    @Override
    public void onActivityResult(int requestCode, int resultCode, Intent data) {
        super.onActivityResult(requestCode, resultCode, data);


        Log.d("requestCoderequestCode",requestCode+"");
        Log.d("resultCode",resultCode+"");

        if (requestCode==RESULT_LOAD_IMAGE) {
            Uri selectedImageUri = data.getData();
            Bitmap bitmap = null;
            try {
                 bitmap = MediaStore.Images.Media.getBitmap(this.getContentResolver(), selectedImageUri);
            } catch (IOException e) {
                e.printStackTrace();
            }

           // profileImage.setImageURI(selectedImageUri);


            saveToInternalStorage(bitmap,Constants.getToken(7)+".png");
        }else if(resultCode==RESULT_LOAD_Code){
            String code = data.getStringExtra("code");
            ((AddContactFragment)addContactFragment).code.setText(code);


        }
    }

    private String saveToInternalStorage(Bitmap bitmapImage,String fileName){
        ContextWrapper cw = new ContextWrapper(getApplicationContext());
        File directory = cw.getDir("media", Context.MODE_PRIVATE);
        if(!directory.exists()){
          //  directory.mkdirs();
        }

        File file = new File(directory, fileName);
       /* try {
           // file.createNewFile();
        } catch (IOException e) {
            e.printStackTrace();
        }*/
        if (!file.exists()) {
            Log.d("fsvdsfe", file.toString());
            FileOutputStream fos = null;
            try {
                fos = new FileOutputStream(file);
                bitmapImage.compress(Bitmap.CompressFormat.JPEG, 100, fos);
                fos.flush();
                fos.close();
            } catch (java.io.IOException e) {
                e.printStackTrace();
            }
        }
        Constants.loadimage(profileImage,file+"");
        ((AddContactFragment) addContactFragment).file=file.toString();
        return file.getAbsolutePath();
    }






    @Override
    public void onFragmentInteraction(ImageView  profileImage, Fragment fragment) {
        this.profileImage = profileImage;
        this.addContactFragment = fragment;
    }

    @Override
    public void onFragmentInteraction(Uri uri) {

    }
}

package com.MyTask.phonebook.Utils;

import android.graphics.Bitmap;
import android.graphics.BitmapFactory;
import android.widget.ImageView;

import java.io.File;
import java.text.SimpleDateFormat;
import java.util.Calendar;
import java.util.Random;

public class Constants {


    public static int RESULT_LOAD_IMAGE = 1;
    public static int RESULT_LOAD_Code = 15;

    public static String getDate(){
        Calendar c = Calendar.getInstance();
        SimpleDateFormat dateformat = new SimpleDateFormat("dd-MMM-yyyy hh:mm:ss aa");
        String datetime = dateformat.format(c.getTime());
        return  datetime;
    }

    public static void loadimage(ImageView imageView, String file ){

        File imgFile = new  File(file);
        if(imgFile.exists()){

            Bitmap myBitmap = BitmapFactory.decodeFile(imgFile.getAbsolutePath());


            imageView.setImageBitmap(myBitmap);

        };
    }



        static public String getToken(int chars) {
            String CharSet = "abcdefghijkmnopqrstuvwxyzABCDEFGHJKLMNOPQRSTUVWXYZ234567890!@#$";
            String Token = "";
            for (int a = 1; a <= chars; a++) {
                Token += CharSet.charAt(new Random().nextInt(CharSet.length()));
            }
            return Token;
        }

}

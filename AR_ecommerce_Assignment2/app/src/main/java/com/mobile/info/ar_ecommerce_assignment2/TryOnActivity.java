package com.mobile.info.ar_ecommerce_assignment2;

import android.content.Intent;
import android.graphics.Bitmap;
import android.graphics.BitmapFactory;
import android.graphics.drawable.BitmapDrawable;
import android.graphics.drawable.Drawable;
import android.media.MediaScannerConnection;
import android.net.Uri;
import android.os.Bundle;
import android.os.Environment;
import android.util.Log;
import android.view.View;
import android.widget.Button;
import android.widget.TextView;
import android.widget.Toast;

import androidx.annotation.Nullable;
import androidx.appcompat.app.AppCompatActivity;

import com.mobile.info.ar_ecommerce_assignment2.sceneform.ARDropRefImgActivity;

import java.io.File;
import java.io.FileOutputStream;
import java.util.Random;

public class TryOnActivity extends AppCompatActivity
{
    Bitmap anImage;
    String title;
    String desc;
    int image_id;

    @Override
    protected void onCreate(@Nullable Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_try_on_guideline);

        assert getSupportActionBar() != null;   //null check
        getSupportActionBar().setDisplayHomeAsUpEnabled(true);
        Drawable myDrawable = getResources().getDrawable(R.drawable.watchtarget);
        anImage      = ((BitmapDrawable) myDrawable).getBitmap();

        Intent i = getIntent();
        title = i.getStringExtra("title");
        desc = i.getStringExtra("desc");
        image_id = i.getIntExtra("image", 0);

//        Toast.makeText(getApplicationContext(), "title=" + title + ", desc=" + desc, Toast.LENGTH_LONG).show();

        TextView img_btn_camera = (TextView) findViewById(R.id.img_btn_camera_in_guide);
        img_btn_camera.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {

//                Toast.makeText(getApplicationContext(), "jewellCategory=" + jewellCategory + ", productName=" + productName, Toast.LENGTH_LONG).show();


//              Intent i = new Intent(TryOnActivity.this, ARDropObjectActivity.class);
                Intent i = new Intent(TryOnActivity.this, ARDropRefImgActivity.class);
                i.putExtra("title", title);
                i.putExtra("desc", desc);
                i.putExtra("image", image_id);
                startActivity(i);

            }
        });
    }

    @Override
    public boolean onSupportNavigateUp() {
        finish();
        return true;
    }

    public void BtnDownloadImage(View v) {

        int e=v.getId();

        switch(e){

            case R.id.txt_download_refrence_image:
                saveImageToExternalStorage(anImage);
                //Toast.makeText(getApplicationContext(), "Saved successfully, Check gallery", Toast.LENGTH_SHORT).show();
                break;

        }
    }
    private void saveImageToExternalStorage(Bitmap finalBitmap) {
        String root = Environment.getExternalStoragePublicDirectory(Environment.DIRECTORY_PICTURES).toString();
        File myDir = new File(root + "/AR-Watch-RefrenceImage");
        myDir.mkdirs();
        Random generator = new Random();
        int n = 10000;
        n = generator.nextInt(n);
        String fname = "Image-" + n + ".jpg";
        File file = new File(myDir, fname);
        Toast.makeText(getApplicationContext(), "File Name"+fname+"::"+myDir, Toast.LENGTH_SHORT).show();

        if (file.exists())
            file.delete();
        try {
            FileOutputStream out = new FileOutputStream(file);
            finalBitmap.compress(Bitmap.CompressFormat.JPEG, 90, out);
            out.flush();
            out.close();
        }
        catch (Exception e) {
            e.printStackTrace();
        }


        // Tell the media scanner about the new file so that it is
        // immediately available to the user.
        MediaScannerConnection.scanFile(this, new String[]{file.toString()}, null,
                new MediaScannerConnection.OnScanCompletedListener() {
                    public void onScanCompleted(String path, Uri uri) {
                        Log.i("ExternalStorage", "Scanned " + path + ":");
                        Log.i("ExternalStorage", "-> uri=" + uri);
                    }
                });

    }

}

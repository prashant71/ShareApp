package com.prashant.shareapp;

import android.app.Activity;
import android.content.Intent;
import android.os.Bundle;
import android.support.v7.app.AppCompatActivity;
import android.view.View;



public class MainActivity extends AppCompatActivity {
    private static int REQUEST_PICK_IMAGE = 1111;
    private static int REQUEST_PICK_VIDEO = 2222;
    final String videoLink="https://www.youtube.com/watch?v=B08iLAtS3AQ";
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);
    }


    public void shareImage(View v){
        galleryIntentForImage();
    }
    public void shareVideo(View v){
        galleryIntentForVideo();
    }
    public void shareVideoLink(View v){
        Intent sharingIntent = new Intent(Intent.ACTION_SEND);
        sharingIntent.setType("text/plain");
        sharingIntent.putExtra(Intent.EXTRA_TEXT,videoLink);
        startActivity(Intent.createChooser(sharingIntent, "Share VideoLink using"));
    }

    private void galleryIntentForImage() {
        Intent i = new Intent(Intent.ACTION_PICK,android.provider.MediaStore.Images.Media.EXTERNAL_CONTENT_URI);
//        Intent i = new Intent();
        i.setType("image/*");
//        i.setAction(Intent.ACTION_GET_CONTENT);
        if (i.resolveActivity(this.getPackageManager()) != null) {
            startActivityForResult(i, REQUEST_PICK_IMAGE);
        }
    }

    private void galleryIntentForVideo() {
        Intent i = new Intent(Intent.ACTION_PICK,android.provider.MediaStore.Images.Media.EXTERNAL_CONTENT_URI);
//        Intent i = new Intent();
        i.setType("video/*");
//        i.setAction(Intent.ACTION_GET_CONTENT);
        if (i.resolveActivity(this.getPackageManager()) != null) {
            startActivityForResult(i, REQUEST_PICK_VIDEO);
        }
    }

    @Override
    public void onActivityResult(int requestCode, int resultCode, Intent data) {
        super.onActivityResult(requestCode, resultCode, data);
        if (resultCode == Activity.RESULT_OK) {
            if (requestCode == REQUEST_PICK_IMAGE) {
                shareSelectImage(data);
            }
            else if (requestCode == REQUEST_PICK_VIDEO) {
                shareSelectVideo(data);
            }
        }    }

    private void shareSelectImage(Intent data){
//        String imagePath=new Utils().getFilePath(this,data.getData());
        final Intent shareIntent = new Intent(Intent.ACTION_SEND);
        shareIntent.setType("image/jpg");
//        final File photoFile = new File(getFilesDir(), "foo.jpg");
        shareIntent.putExtra(Intent.EXTRA_STREAM, data.getData());
        startActivity(Intent.createChooser(shareIntent, "Share image using"));
    }

    private void shareSelectVideo(Intent data){
//        String imagePath=new Utils().getFilePath(this,data.getData());
        Intent sharingIntent = new Intent(Intent.ACTION_SEND);
        sharingIntent.setType("video/*");
        sharingIntent.putExtra(Intent.EXTRA_STREAM,data.getData());
        startActivity(Intent.createChooser(sharingIntent, "Share Video using"));
    }

}

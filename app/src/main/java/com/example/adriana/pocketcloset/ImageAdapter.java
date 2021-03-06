package com.example.adriana.pocketcloset;

import android.content.Context;
import android.net.Uri;
import android.view.View;
import android.view.ViewGroup;
import android.widget.BaseAdapter;
import android.widget.ImageView;
import android.widget.Toast;

import static java.security.AccessController.getContext;

public class ImageAdapter extends BaseAdapter {

    private Context mContext;

    public ImageAdapter(Context c) {
        mContext = c;
    }

    public int getCount() {
        return MainActivity.picIds.length;
    }

    public Object getItem(int position) {
        return MainActivity.picIds[position];
    }

    public long getItemId(int position) {
        return 0;
    }


    public View getView(int position, View convertView, ViewGroup parent) {
        ImageView imageView;
        if (convertView == null) {

            imageView = new ImageView(mContext);
            imageView.setLayoutParams(new ViewGroup.LayoutParams(200, 200));
            imageView.setScaleType(ImageView.ScaleType.CENTER_CROP);
            //     imageView.setPadding(8, 8, 8, 8);
        } else {
            imageView = (ImageView) convertView;
        }

//        Uri uri = Uri.parse(MainActivity.picIds[position]);
 imageView.setImageResource(MainActivity.picIds[position]);
       // imageView.setImageURI(uri);
        return imageView;
    }

}
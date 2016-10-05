package com.example.user.exo;

import android.content.Context;
import android.graphics.Color;
import android.os.Bundle;
import android.support.annotation.Nullable;
import android.support.v7.app.AppCompatActivity;
import android.view.MotionEvent;
import android.view.View;
import android.view.ViewGroup;
import android.widget.BaseAdapter;
import android.widget.Gallery;
import android.widget.ImageView;
import android.widget.TextView;
import android.widget.Toast;

/**
 * Created by USER on 2016-10-05.
 */

public class GalleryTest extends AppCompatActivity {

    int[] resIdList = new int[10];
    String[] resSubList = new String[resIdList.length];

    @Override
    protected void onCreate(@Nullable Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.gallery);

        for (int i = 0; i < resIdList.length; i++) {
            String mDrawableName = "mov";
            if (i < 9) mDrawableName += "0";
            mDrawableName += (i + 1);

            int resID = getResources().getIdentifier(mDrawableName, "drawable", getPackageName());
            resIdList[i] = resID;

            resSubList[i] = (i + 1) + "번째 영화";
        }

        Gallery gl = (Gallery) findViewById(R.id.gallery);
        gl.setAdapter(new GalleryAdapter(this));
        // gl.setBackgroundColor(Color.RED);

    }

    class GalleryAdapter extends BaseAdapter {
        Context context = null;

        public GalleryAdapter(Context c) {
            context = c;
        }

        @Override
        public int getCount() {
            return resIdList.length;
        }

        @Override
        public Object getItem(int position) {
            return resIdList[position];
        }

        @Override
        public long getItemId(int position) {
            return position;
        }

        @Override
        public View getView(int position, View convertView, ViewGroup parent) {
            final int index = position;

            ImageView iv = new ImageView(context);
            iv.setLayoutParams(new Gallery.LayoutParams(140, 210));
            iv.setScaleType(ImageView.ScaleType.FIT_CENTER);
            iv.setPadding(25, 5, 25, 5);
            iv.setImageResource(resIdList[index]);

            iv.setOnTouchListener(new View.OnTouchListener() {
                @Override
                public boolean onTouch(View v, MotionEvent event) {
                    ImageView ivPoster = (ImageView) findViewById(R.id.ivPoster);
                    ivPoster.setScaleType(ImageView.ScaleType.FIT_CENTER);
                    ivPoster.setImageResource(resIdList[index]);
                    TextView tvPosterName = (TextView) findViewById(R.id.tvPosterName);
                    tvPosterName.setText(resSubList[index]);
                    Toast.makeText(context, resSubList[index], Toast.LENGTH_SHORT).show();

                    return false;
                }
            });

            return iv;
        }
    }
}

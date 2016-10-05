package com.example.user.exo;

import android.app.AlertDialog;
import android.content.Context;
import android.os.Bundle;
import android.support.v7.app.AppCompatActivity;
import android.util.Log;
import android.view.View;
import android.view.ViewGroup;
import android.widget.BaseAdapter;
import android.widget.GridView;
import android.widget.ImageView;

/**
 * Created by USER on 2016-10-05.
 */

public class GridViewTest extends AppCompatActivity {

    int[] resIdList = new int[61];
    String[] resSubList = new String[resIdList.length];

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        Log.w("debug", "onCreate");
        setContentView(R.layout.gridviewtest);
        for (int i = 0; i < resIdList.length; i++) {
            String mDrawableName = "mov";
            if (i < 9) mDrawableName += "0";
            mDrawableName += (i + 1);

            int resID = getResources().getIdentifier(mDrawableName, "drawable", getPackageName());
            resIdList[i] = resID;

            resSubList[i] = (i + 1) + "번째 영화";
        }

        GridView gv = (GridView) findViewById(R.id.gridview);
        gv.setAdapter(new GridAdapter(this));

    }

    class GridAdapter extends BaseAdapter {
        Context context = null;

        GridAdapter(Context c) {
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

            iv.setLayoutParams(new GridView.LayoutParams(100, 150));
            iv.setScaleType(ImageView.ScaleType.FIT_CENTER);
            iv.setPadding(5, 5, 5, 5);

            iv.setImageResource(resIdList[index]);
            // iv.setImageResource(R.mipmap.ic_launcher);

            iv.setOnClickListener(new View.OnClickListener() {
                @Override
                public void onClick(View v) {
                    View dialogView = (View) View.inflate(context, R.layout.dialog, null);
                    AlertDialog.Builder dlg = new AlertDialog.Builder(context);
                    ImageView ivPoster = (ImageView) dialogView.findViewById(R.id.ivPoster);
                    ivPoster.setImageResource(resIdList[index]);
                    dlg.setTitle(resSubList[index]);
                    dlg.setIcon(resIdList[index]);
                    dlg.setView(dialogView);
                    dlg.setNegativeButton("닫기", null);
                    dlg.show();

                }
            });
            return iv;
        }
    }
}

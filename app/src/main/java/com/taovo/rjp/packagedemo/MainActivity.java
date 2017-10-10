package com.taovo.rjp.packagedemo;

import android.content.Context;
import android.graphics.drawable.AnimationDrawable;
import android.os.Bundle;
import android.support.v7.app.AppCompatActivity;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.BaseAdapter;
import android.widget.GridView;
import android.widget.ImageView;
import android.widget.TextView;

import java.util.ArrayList;
import java.util.List;

public class MainActivity extends AppCompatActivity {

    private List<AnimModel> models;
    private Context mContext;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);

        mContext = this;
        models = new ArrayList<>();
        addData();
        GridView gridView = (GridView) findViewById(R.id.grid_view);
        gridView.setAdapter(new BaseAdapter() {
            @Override
            public int getCount() {
                return models.size();
            }

            @Override
            public Object getItem(int position) {
                return null;
            }

            @Override
            public long getItemId(int position) {
                return 0;
            }

            @Override
            public View getView(int position, View convertView, ViewGroup parent) {
                Holder holder = null;
                if (convertView == null) {
                    convertView = LayoutInflater.from(mContext).inflate(R.layout.item, parent, false);
                    holder = new Holder(convertView);
                    convertView.setTag(holder);
                } else {
                    holder = (Holder) convertView.getTag();
                }
                AnimModel animModel = models.get(position);
                holder.textView.setText(String.valueOf(animModel.number));
                AnimationDrawable drawable = (AnimationDrawable) holder.imageView.getDrawable();
                if (animModel.state == 1) {
                    drawable.start();
                }else{
                    drawable.stop();
                    holder.imageView.setImageResource(R.drawable.anim);
                }
                return convertView;
            }
        });
    }

    public class Holder {
        ImageView imageView;
        TextView textView;

        public Holder(View view) {
            imageView = (ImageView) view.findViewById(R.id.image);
            textView = (TextView) view.findViewById(R.id.text);
        }
    }

    private void addData() {
        for (int i = 0; i < 50; i++) {
            models.add(new AnimModel(i, i == 20 ? 1 : 0));
        }
    }
}

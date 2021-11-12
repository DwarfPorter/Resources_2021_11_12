package ru.geekbrains.resources;

import androidx.appcompat.app.AppCompatActivity;
import androidx.appcompat.widget.AppCompatImageView;

import android.content.res.TypedArray;
import android.graphics.Typeface;
import android.graphics.drawable.Drawable;
import android.os.Bundle;
import android.text.Layout;
import android.view.LayoutInflater;
import android.view.View;
import android.widget.ImageView;
import android.widget.LinearLayout;
import android.widget.TextView;

import java.io.IOException;
import java.io.InputStream;

public class MainActivity extends AppCompatActivity {

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);

        initSomeText();

        AppCompatImageView image = findViewById(R.id.imageView);
        loadImageFromAsset(image, "images/android.png");

        initList();
    }

    private void initList() {
        LinearLayout layoutList = findViewById(R.id.layoutList);
        String[] versions = getResources().getStringArray(R.array.version_names);

        LayoutInflater ltInflater = getLayoutInflater();

        for(int i = 0; i < versions.length; i++){
            View item = ltInflater.inflate(R.layout.android_item, layoutList, false);
            TextView tv = item.findViewById(R.id.textAndroid);
            tv.setText(versions[i]);

            TypedArray imgs = getResources().obtainTypedArray(R.array.version_logos);

            AppCompatImageView imgLogo = item.findViewById(R.id.imageAndroid);
            imgLogo.setImageResource(imgs.getResourceId(i, -1));

            layoutList.addView(item);
        }
    }

    private void initSomeText() {
        Typeface tf = Typeface.createFromAsset(getAssets(), "fonts/19659.ttf");

        TextView descriptionLanguage = findViewById(R.id.textVLang);
        descriptionLanguage.setTypeface(tf);
        descriptionLanguage.setText(this.getString(R.string.descriptionLanguage));
        TextView textLanguage = findViewById(R.id.textLang);
        textLanguage.setText(getResources().getString(R.string.language));
    }

    public void loadImageFromAsset(ImageView image, String fileName) {
        try {
            InputStream ims = getAssets().open(fileName);
            // загружаем как Drawable
            Drawable d = Drawable.createFromStream(ims, null);
            // выводим картинку в ImageView
            image.setImageDrawable(d);
        }
        catch(IOException ex) {
            return;
        }
    }

}
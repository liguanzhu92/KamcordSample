package com.guanzhuli.kamcordsample.utils;

import android.graphics.Bitmap;
import android.graphics.BitmapFactory;
import android.widget.ImageView;

import java.io.IOException;
import java.io.InputStream;
import java.util.concurrent.Callable;
import java.util.concurrent.Executors;
import java.util.concurrent.Future;

/**
 * Created by Guanzhu Li on 3/12/2017.
 * download image and set it to imageview
 */
public class ImageLoader {
    private static ImageLoader instance = null;
    private LruBitmapCache mMap;
    private ImageLoader() {
        mMap = new LruBitmapCache();
    }
    public static ImageLoader getInstance() {
        if(instance == null) {
            instance = new ImageLoader();
        }
        return instance;
    }

    /**
     * get image from url or LRUCache
     * @param url image url
     * @param view target ImageView
     */
    public void setImage(final String url, ImageView view) {
        if (mMap.getBitmap(url) != null) {
            view.setImageBitmap(mMap.getBitmap(url));
            return;
        }
        Future<Bitmap> future =
                Executors.newSingleThreadExecutor().submit(new Callable<Bitmap>()
                {
                    @Override
                    public Bitmap call() throws Exception
                    {
                        InputStream in = null;
                        try {
                            in = new java.net.URL(url).openStream();
                        } catch (IOException e) {
                            e.printStackTrace();
                        }
                        Bitmap bitmap = BitmapFactory.decodeStream(in);
                        mMap.putBitmap(url, bitmap);
                        return bitmap;
                    }
                });

        try {
            Bitmap images = future.get();
            view.setImageBitmap(images);
        } catch (Exception e) {

        }
    }
}

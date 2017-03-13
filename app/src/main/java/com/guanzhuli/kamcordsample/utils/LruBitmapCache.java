package com.guanzhuli.kamcordsample.utils;

import android.graphics.Bitmap;
import android.support.v4.util.LruCache;

/**
 * Created by Guanzhu Li on 3/12/2017.
 */
public class LruBitmapCache extends LruCache<String, Bitmap> {
    private static final int KILO = 1024;
    private static final int THRESHOLD = 8;
    public static int getDefaultLruCacheSize() {
        final int maxMemory = (int) (Runtime.getRuntime().maxMemory() / KILO);
        final int cacheSize = maxMemory / THRESHOLD;

        return cacheSize;
    }

    public LruBitmapCache() {
        this(getDefaultLruCacheSize());
    }

    public LruBitmapCache(int sizeInKiloBytes) {
        super(sizeInKiloBytes);
    }

    @Override
    protected int sizeOf(String key, Bitmap value) {
        return value.getRowBytes() * value.getHeight() / KILO;
    }

    public Bitmap getBitmap(String url) {
        return get(url);
    }

    public void putBitmap(String url, Bitmap bitmap) {
        put(url, bitmap);
    }
}

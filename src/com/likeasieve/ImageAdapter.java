/**
 * Copyright (c) 2011 Nick Burton.
 */

package com.likeasieve;

import android.content.Context;
import android.graphics.Bitmap;
import android.graphics.BitmapFactory;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.BaseAdapter;
import android.widget.ImageView;

import java.util.HashMap;

/**
 * @date   Apr 19, 2011
 * @author Nick Burton (thatsburto.com)
 */
public class ImageAdapter extends BaseAdapter {

    private static HashMap<String,Bitmap> sImageCache;
    private LayoutInflater mInflater;
    private Context mContext;
    
    public ImageAdapter(Context context){
        // Bad approach: adding to a static cache but never removing 
        // from it. You will eventually run out of memory and even faster
        // if the images are large.
        if(sImageCache == null){
            sImageCache = new HashMap<String,Bitmap>();
        }
        mInflater = LayoutInflater.from(context);
        mContext = context;
    }
    
    @Override
    public View getView(int position, View convertView, ViewGroup parent) {
        ViewHolder holder;
        if(convertView == null){
            convertView = mInflater.inflate(R.layout.image_layout, null);
            holder = new ViewHolder();
            holder.image = (ImageView) convertView.findViewById(R.id.image_id);
            convertView.setTag(holder);
        } else {
            holder = (ViewHolder) convertView.getTag();
        }
        Bitmap bitmap = null;
        String imageKey = getImageKey(position);
        if(sImageCache.containsKey(imageKey)){
            bitmap = sImageCache.get(imageKey);
        } else {
            // Pretend to fetch over a network (i.e. a slow activity we want 
            // to perform less if possible).
            bitmap = BitmapFactory.decodeResource(mContext.getResources(), IMAGE_IDS[position], null);
            sImageCache.put(imageKey, bitmap);
        }
        holder.image.setImageBitmap(bitmap);
        return convertView;
    }
    
    public String getImageKey(int position){
        return IMAGE_KEYS[position];
    }
    
    @Override
    public int getCount() {
        return IMAGE_KEYS.length;
    }

    @Override
    public Object getItem(int position) {
        return (sImageCache == null) ? null : sImageCache.get(position);
    }

    @Override
    public long getItemId(int position) {
        return position;
    }
    
    private static class ViewHolder {
        ImageView image;
    }
    
    // Simulating fetching data over a network
    private static final int[]  IMAGE_IDS = {
        R.drawable.image_1,
        R.drawable.image_2,
        R.drawable.image_3,
        R.drawable.image_4,
    };
    
    private static final String[] IMAGE_KEYS = {
        "http://images.html/1",
        "http://images.html/2",
        "http://images.html/3",
        "http://images.html/4"
    };
}

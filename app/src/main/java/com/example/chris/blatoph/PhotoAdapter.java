package com.example.chris.blatoph;

import android.content.Context;
import android.content.res.Resources;
import android.graphics.Bitmap;
import android.graphics.drawable.ColorDrawable;
import android.graphics.drawable.Drawable;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ArrayAdapter;
import android.widget.ImageView;
import android.widget.TextView;

import com.example.chris.blatoph.Classes.Photo;

import java.util.List;

/**
 * Created by chris on 15/06/2017.
 */

public class PhotoAdapter extends ArrayAdapter<Photo>{

    Resources res;
    public PhotoAdapter(Context context, List<Photo> photos) {
        super(context, 0, photos);

        res = context.getResources();
    }

    @Override
    public View getView(int position, View convertView, ViewGroup parent) {

        if(convertView == null){
            convertView = LayoutInflater.from(getContext()).inflate(R.layout.une_photo ,parent, false);
        }

        PhotoViewHolder viewHolder = (PhotoViewHolder) convertView.getTag();
        if(viewHolder == null){
            viewHolder = new PhotoViewHolder();
            viewHolder.titre = (TextView) convertView.findViewById(R.id.titre_photo);
            viewHolder.image = (ImageView) convertView.findViewById(R.id.image_photo);
            convertView.setTag(viewHolder);
        }

        Photo photo = getItem(position);
        viewHolder.titre.setText(photo.getTitre()+ "\n" + photo.getLegende());
        viewHolder.image.setImageBitmap(photo.getImage());

        return convertView;
    }

    private class PhotoViewHolder{
        public TextView titre;
        public ImageView image;

    }
}

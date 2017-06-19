package com.example.chris.blatoph;

import android.content.Context;
import android.content.res.Resources;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ArrayAdapter;
import android.widget.ImageView;
import android.widget.TextView;

import com.example.chris.blatoph.Classes.Album;
import com.example.chris.blatoph.Classes.Photo;

import java.util.List;



/**
 * Created by Sarah Pierson on 19/06/2017.
 */

public class AlbumAdapter extends ArrayAdapter<Album> {

    Resources res;
    public AlbumAdapter(Context context, List<Album> albums) {
        super(context, 0, albums);

        res = context.getResources();
    }

    @Override
    public View getView(int position, View convertView, ViewGroup parent) {

        if(convertView == null){
            convertView = LayoutInflater.from(getContext()).inflate(R.layout.un_album ,parent, false);
        }

        AlbumViewHolder viewHolder = (AlbumViewHolder) convertView.getTag();
        if(viewHolder == null){
            viewHolder = new AlbumViewHolder();
            viewHolder.titre = (TextView) convertView.findViewById(R.id.titre_album);
            viewHolder.createur = (TextView) convertView.findViewById(R.id.nom_createur);
            convertView.setTag(viewHolder);
        }

        Album album = getItem(position);
        viewHolder.titre.setText(album.getTitre());
        viewHolder.createur.setText(album.getCreateur());

        return convertView;
    }

    private class AlbumViewHolder{
        public TextView titre;
        public TextView createur;

    }
}
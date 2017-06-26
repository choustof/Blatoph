package com.example.chris.blatoph;

import android.content.Context;
import android.content.res.Resources;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ArrayAdapter;
import android.widget.ImageView;
import android.widget.TextView;

import com.example.chris.blatoph.Classes.Photo;
import com.example.chris.blatoph.Classes.Utilisateur;
import com.example.chris.blatoph.R;

import java.util.List;

/**
 * Created by chris on 15/06/2017.
 */

public class AmiAdapter extends ArrayAdapter<Utilisateur>{

    Resources res;
    public AmiAdapter(Context context, List<Utilisateur> utilisateurs) {
        super(context, 0, utilisateurs);

        res = context.getResources();
    }

    @Override
    public View getView(int position, View convertView, ViewGroup parent) {

        if(convertView == null){
            convertView = LayoutInflater.from(getContext()).inflate(R.layout.un_ami ,parent, false);
        }

        AmiViewHolder viewHolder = (AmiViewHolder) convertView.getTag();
        if(viewHolder == null){
            viewHolder = new AmiViewHolder();
            viewHolder.nom = (TextView) convertView.findViewById(R.id.nom_ami);
            viewHolder.email = (TextView) convertView.findViewById(R.id.email_ami);
            convertView.setTag(viewHolder);
        }

        Utilisateur ami = getItem(position);
        viewHolder.nom.setText(ami.getPrenom());
        viewHolder.email.setText(ami.getAdresse());

        return convertView;
    }

    private class AmiViewHolder{
        public TextView nom;
        public TextView email;

    }
}

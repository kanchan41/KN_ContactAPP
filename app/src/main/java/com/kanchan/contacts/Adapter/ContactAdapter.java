package com.kanchan.contacts.Adapter;

import android.content.Context;
import android.content.Intent;
import android.graphics.Bitmap;
import android.graphics.drawable.BitmapDrawable;
import android.graphics.drawable.Drawable;
import android.provider.ContactsContract;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.BaseAdapter;
import android.widget.Filter;
import android.widget.Filterable;
import android.widget.ImageView;
import android.widget.TextView;

import com.kanchan.contacts.ContactInfo.ContactInfo;
import com.kanchan.contacts.Payload.Items;
import com.kanchan.contacts.R;
import com.squareup.picasso.MemoryPolicy;
import com.squareup.picasso.Picasso;

import com.squareup.picasso.Target;

import java.util.ArrayList;
import java.util.List;
import java.util.Locale;


public class ContactAdapter extends BaseAdapter {
    Context context;
    LayoutInflater inflater;
    private List<Items> contactList = null;

    public ContactAdapter(List<Items> collecitonList, Context context) {
        this.contactList = collecitonList;
        this.context = context;
    }

    public class ViewHolder {
        TextView fName;
        TextView lName;
        ImageView avatar;

    }

    @Override
    public int getCount() {
        return contactList.size();
    }

    @Override
    public Object getItem(int position) {
        return contactList.get(position);
    }

    @Override
    public long getItemId(int position) {
        return position;
    }

    public View getView(final int position, View view, ViewGroup parent) {
        final ViewHolder holder;
        if (view == null) {
            holder = new ViewHolder();
            view = LayoutInflater.from(parent.getContext()).inflate(R.layout.contact_item, parent, false);
            holder.fName = (TextView) view.findViewById(R.id.fName);
            holder.lName = (TextView) view.findViewById(R.id.lName);
            holder.avatar = (ImageView) view.findViewById(R.id.avatar);


            view.setTag(holder);
        } else {
            holder = (ViewHolder) view.getTag();
        }
        holder.fName.setText(contactList.get(position).getfName());
        holder.lName.setText(contactList.get(position).getlName());


        final Target target = new Target() {
            @Override
            public void onBitmapLoaded(Bitmap bitmap, Picasso.LoadedFrom from) {
                Drawable d = new BitmapDrawable(context.getResources(), Bitmap.createScaledBitmap(bitmap, 100, 100, true));
                holder.avatar.setImageDrawable(d);
            }

            @Override
            public void onBitmapFailed(Drawable errorDrawable) {
                errorDrawable = context.getResources().getDrawable(R.drawable.ic_warning_black_24dp);
                holder.avatar.setImageDrawable(errorDrawable);
            }

            @Override
            public void onPrepareLoad(Drawable placeHolderDrawable) {
                placeHolderDrawable = context.getResources().getDrawable(R.drawable.loading);
                holder.avatar.setImageDrawable(placeHolderDrawable);
            }
        };

        holder.avatar.setTag(target);
        Picasso.with(context)
                .load(contactList.get(position).getAvtar())
                .memoryPolicy(MemoryPolicy.NO_CACHE)
                .into(target);


        view.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Intent intent = new Intent(context, ContactInfo.class);
                intent.putExtra("Name", contactList.get(position).getfName());
                intent.putExtra("Phone", contactList.get(position).getPhone());
                intent.putExtra("avtarURL", contactList.get(position).getAvtar());


                intent.setFlags(Intent.FLAG_ACTIVITY_CLEAR_TOP);
                context.startActivity(intent);
            }
        });
        return view;
    }


}
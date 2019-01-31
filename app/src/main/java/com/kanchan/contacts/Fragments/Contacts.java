package com.kanchan.contacts.Fragments;

import android.app.ProgressDialog;
import android.content.Context;
import android.os.Bundle;
import android.view.LayoutInflater;
import android.view.Menu;
import android.view.MenuInflater;
import android.view.MenuItem;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ListView;
import android.widget.SearchView;
import android.widget.TextView;
import android.widget.Toast;


import com.kanchan.contacts.Adapter.ContactAdapter;
import com.kanchan.contacts.FakeJson.ContactJson;
import com.kanchan.contacts.Payload.Items;
import com.kanchan.contacts.R;

import org.json.JSONArray;
import org.json.JSONException;
import org.json.JSONObject;

import java.text.SimpleDateFormat;
import java.util.ArrayList;
import java.util.Date;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

import androidx.fragment.app.Fragment;
import androidx.swiperefreshlayout.widget.SwipeRefreshLayout;


public class Contacts extends Fragment {


    private android.app.ProgressDialog progressDialog;
    Context context;
    ListView listView;
    ContactAdapter contactAdapter;
    List<Items> conatctList = new ArrayList<>();


    public static Contacts newInstance() {
        Contacts fragment = new Contacts();
        return fragment;
    }

    @Override
    public void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setHasOptionsMenu(true);
    }

    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container,
                             Bundle savedInstanceState) {
        View v = inflater.inflate(R.layout.contact_fragment, container, false);
        context = container.getContext();
        listView = (ListView) v.findViewById(R.id.list);
        init();
        return v;
    }

    public void init() {

        try {
            JSONObject jsonObject = new JSONObject(ContactJson.ContactPayload);
            JSONArray payload = jsonObject.optJSONArray("contacts");
            for (int i = 0; i < payload.length(); i++) {
                JSONObject jsonChildNode = payload.getJSONObject(i);
                Items items = new Items();
                items.setfName(jsonChildNode.optString("firstName"));
                items.setlName(jsonChildNode.optString("lastName"));
                items.setAvtar(jsonChildNode.optString("avtar_url"));
                items.setPhone(jsonChildNode.optString("phone"));

                conatctList.add(items);
                contactAdapter = new ContactAdapter(conatctList, getContext());
                listView.setAdapter(contactAdapter);
            }
        } catch (JSONException e) {
            e.printStackTrace();
        }

    }
}




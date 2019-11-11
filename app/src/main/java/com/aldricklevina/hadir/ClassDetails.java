package com.aldricklevina.hadir;

import androidx.appcompat.app.AppCompatActivity;
import androidx.fragment.app.ListFragment;

import android.os.Bundle;
import android.widget.ArrayAdapter;
import android.widget.ListAdapter;
import android.widget.ListView;

import java.util.ArrayList;

public class ClassDetails extends AppCompatActivity {

    private ArrayList<String> arrayMurid;
    private ListView listView;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_class_details);

//        listView = (ListView) findViewById(R.id.listView);
//        arrayMurid = new ArrayList<String>();
//
//        arrayMurid.add(0, "Aldrick Handinata");
//        arrayMurid.add(1, "Levina Khomulia");
//        arrayMurid.add(2, "Secretary Han");

//        ListAdapter listAdapter = new ArrayAdapter<String>(getActivity(), android.R.layout.simple_list_item_1, arrayMurid);
//        setListAdapter(listAdapter);

    }
}

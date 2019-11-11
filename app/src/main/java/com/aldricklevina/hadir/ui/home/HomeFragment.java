package com.aldricklevina.hadir.ui.home;

import android.os.Bundle;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ArrayAdapter;
import android.widget.CheckBox;
import android.widget.ListAdapter;
import android.widget.ListView;
import android.widget.TextView;

import androidx.annotation.Nullable;
import androidx.annotation.NonNull;
import androidx.fragment.app.ListFragment;
import androidx.lifecycle.Observer;
import androidx.lifecycle.ViewModelProviders;

import com.aldricklevina.hadir.Main2Activity;
import com.aldricklevina.hadir.R;

import java.text.DateFormat;
import java.util.ArrayList;
import java.util.Calendar;

public class HomeFragment extends ListFragment {


    private ArrayList<String> arrayMurid;
    private ListView listView;
    private CheckBox checkBox;
    private TextView tanggalTxt;

    private HomeViewModel homeViewModel;


    public View onCreateView(@NonNull LayoutInflater inflater,
                             ViewGroup container, Bundle savedInstanceState) {
        homeViewModel =
                ViewModelProviders.of(this).get(HomeViewModel.class);
        View root = inflater.inflate(R.layout.fragment_home, container, false);
//        final TextView textView = root.findViewById(R.id.text_home);
        homeViewModel.getText().observe(this, new Observer<String>() {
            @Override
            public void onChanged(@Nullable String s) {
//                textView.setText(s);
            }
        });
        return root;
    }

    public void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);


    }

    @Override
    public void onViewCreated(@NonNull View view, @Nullable Bundle savedInstanceState) {
        listView = view.findViewById(R.id.listView);
        tanggalTxt = view.findViewById(R.id.tanggalTxt);
        arrayMurid = new ArrayList<String>();

        arrayMurid.add(0, "Aldrick Handinata");
        arrayMurid.add(1, "Levina Khomulia");
        arrayMurid.add(2, "Secretary Han");

        ListAdapter listAdapter = new ArrayAdapter<String>(getActivity(), android.R.layout.simple_list_item_multiple_choice, arrayMurid){
            @Override
            public View getView(int position, View convertView, ViewGroup parent){
                /// Get the Item from ListView
                View view = super.getView(position, convertView, parent);

                // Set the border of View (ListView Item)
                view.setBackground(getContext().getDrawable(R.drawable.rounded_corner));


                // panggil layout list_mhs
//                listView.Layout
//                listView.setLayoutMode();
//                LayoutInflater inflater = (LayoutInflater) .getLayoutInflater();
//                View v = inflater.inflate(R.layout.list_mhs, null);
//
//                TextView namaMhs;
//                namaMhs = v.findViewById(R.id.txtNamaMhs);
//                namaMhs.setText(listView[position]);


                // Return the view
                return view;
            }
        };
        listView.setAdapter(listAdapter);

//        listView.setAdapter(new ArrayAdapter<String>(this, android.R.layout.simple_list_item_multiple_choice, arrayMurid));
        listView.setChoiceMode(ListView.CHOICE_MODE_MULTIPLE);

        Calendar calendar = Calendar.getInstance();
        String currentDate = DateFormat.getDateInstance(DateFormat.FULL).format(calendar.getTime());

        tanggalTxt.setText(currentDate);

//        listView.setBackgroundResource(R.drawable.rounded_corner);


    }

    @Override
    public void onListItemClick(ListView list, View v, int position, long id) {

//        ((MainActivity)getActivity()).loadFragment1(new HomeFragment());

    }




//    public void bindView(View view, Context context, Cursor cursor) {
//        checkBox=(CheckBox)view.findViewById(R.id.checkbox1);
////        checkBox.setText(dbgetStringValue);
////        cbText=(TextView)view.findViewById(R.id.cbText);
////        cbText.setText(dbgetStringValue);
//        checkBox.setChecked(false);
//        checkBox.setOnCheckedChangeListener(new CompoundButton.OnCheckedChangeListener() {
//            public void onCheckedChanged(CompoundButton cb, boolean isChecked) {
//                if(cb.isChecked()) {
//                    // action
//                }
//                else if(isChecked==false) {
//                    // action
//                }
//            }
//        });
//    }

}
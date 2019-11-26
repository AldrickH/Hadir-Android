package com.aldricklevina.hadir.ui.profile;

import android.os.Bundle;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.Button;
import android.widget.EditText;
import android.widget.ImageView;
import android.widget.LinearLayout;
import android.widget.TextView;

import androidx.annotation.Nullable;
import androidx.annotation.NonNull;
import androidx.fragment.app.Fragment;
import androidx.lifecycle.Observer;
import androidx.lifecycle.ViewModelProviders;

import com.aldricklevina.hadir.Model.App;
import com.aldricklevina.hadir.R;

public class ProfileFragment extends Fragment implements View.OnClickListener {

    private EditText editTextName, editTextEmail, editTextPassword;
    private ImageView imgEditName, imgEditPass;
    private Button btnSave, btnCancel;
    private LinearLayout layoutEditProf;

    private ProfileViewModel profileViewModel;

    private App app;

    public ProfileFragment(App _app) {
        this.app = _app;
    }

    public View onCreateView(@NonNull LayoutInflater inflater, ViewGroup container, Bundle savedInstanceState) {
        profileViewModel = ViewModelProviders.of(this).get(ProfileViewModel.class);
        View root = inflater.inflate(R.layout.fragment_profile, container, false);
        profileViewModel.getText().observe(this, new Observer<String>() {
            @Override
            public void onChanged(@Nullable String s) {
            }
        });

        return root;
    }

    @Override
    public void onViewCreated(@NonNull View view, @Nullable Bundle savedInstanceState) {
        super.onViewCreated(view, savedInstanceState);

        imgEditName = view.findViewById(R.id.imgEditName_prof);
        imgEditPass = view.findViewById(R.id.imgEditPass_prof);

        editTextName = view.findViewById(R.id.editTextName_prof);
        editTextEmail = view.findViewById(R.id.editTextEmail_prof);
        editTextPassword = view.findViewById(R.id.editTextPassword_prof);

        btnSave = view.findViewById(R.id.btnSave_prof);
        btnCancel = view.findViewById(R.id.btnCancel_prof);

        layoutEditProf = view.findViewById(R.id.layoutEdit_prof);

        imgEditName.setOnClickListener(this);
        imgEditPass.setOnClickListener(this);
        btnSave.setOnClickListener(this);
        btnCancel.setOnClickListener(this);

        editTextName.setText(app.acc.getFullname());
        editTextEmail.setText(app.acc.getEmail());
        editTextPassword.setText(app.acc.getPassword());
    }

    @Override
    public void onClick(View v) {
        int id = v.getId();

        if (id == R.id.imgEditName_prof) {
            editTextName.setEnabled(true);
            editTextName.requestFocus();

            if (layoutEditProf.getTranslationY() != 0) {
                layoutEditProf.animate().translationY(0).setDuration(1000);
            }
        } else if (id == R.id.imgEditPass_prof) {
            editTextPassword.setEnabled(true);
            editTextPassword.requestFocus();

            if (layoutEditProf.getTranslationY() != 0) {
                layoutEditProf.animate().translationY(0).setDuration(1000);
            }
        } else if (id == R.id.btnSave_prof) {
            app.acc.fullname = editTextName.getText().toString();
            app.acc.password = editTextPassword.getText().toString();

            editTextName.setEnabled(false);
            editTextPassword.setEnabled(false);

            layoutEditProf.animate().translationY(200f).setDuration(1000);
        } else if (id == R.id.btnCancel_prof) {
            editTextName.setText(app.acc.fullname);
            editTextPassword.setText(app.acc.password);

            editTextName.setEnabled(false);
            editTextPassword.setEnabled(false);

            layoutEditProf.animate().translationY(200f).setDuration(1000);
        }
    }
}
package com.ECatering.e_catering.Menu.ui.profile;

import android.content.Intent;
import android.os.Build;
import android.os.Bundle;
import android.util.Log;
import android.view.LayoutInflater;
import android.view.Menu;
import android.view.View;
import android.view.ViewGroup;
import android.widget.Button;
import android.widget.Switch;
import android.widget.TextView;
import android.widget.Toast;

import androidx.annotation.NonNull;
import androidx.annotation.RequiresApi;
import androidx.fragment.app.Fragment;
import androidx.recyclerview.widget.RecyclerView;

import com.ECatering.e_catering.Menu.Dashboard;
import com.ECatering.e_catering.Menu.Login;
import com.ECatering.e_catering.Menu.Edit_profile;
import com.ECatering.e_catering.R;
import com.ECatering.e_catering.Storage.SharedPrefManager;

public class FragmentProfile  extends Fragment {
TextView Nama,Email,Telpon,Alamat;
    Switch Keluar;
        Button    Update;
    SharedPrefManager sharedPrefManager;

    @RequiresApi(api = Build.VERSION_CODES.M)
    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container, Bundle savedInstanceState) {
        View root = inflater.inflate(R.layout.fragment_profile, container, false);

        sharedPrefManager = new SharedPrefManager(getActivity());
        Nama=(TextView) root.findViewById(R.id.Nama_user);
        Email=(TextView) root.findViewById(R.id.Email_user);
        Telpon=(TextView) root.findViewById(R.id.Telpon_user);
        Alamat=(TextView) root.findViewById(R.id.Alamat_user);
        Keluar=(Switch) root.findViewById(R.id.btnlogout);
        Update=(Button) root.findViewById(R.id.btnupdate);
        Keluar.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
               logout();
            }
        });

        tampil_datauser();
Update.setOnClickListener(new View.OnClickListener() {
    @Override
    public void onClick(View v) {
        Intent intent = new Intent(getActivity(),Edit_profile.class);
        startActivity(intent);
        getActivity().finish();
    }
});
        return root;


    }

    private void logout() {
        sharedPrefManager.saveSPBoolean(SharedPrefManager.SP_SUDAH_LOGIN, false);
        startActivity(new Intent(getActivity(), Login.class));
        getActivity().finish();
    }

    private void tampil_datauser() {
        Log.d("response user", sharedPrefManager.getSPNama()+sharedPrefManager.getSPAlamat()+sharedPrefManager.getSPEmail()+sharedPrefManager.getSPTelpon().toString());
//        Toast.makeText(getActivity(), "ini datanya="+sharedPrefManager.getSPNama()+sharedPrefManager.getSPAlamat()+sharedPrefManager.getSPEmail()+sharedPrefManager.getSPTelpon(), Toast.LENGTH_SHORT).show();
        Nama.setText(sharedPrefManager.getSPNama());
        Email.setText(sharedPrefManager.getSPEmail());
        Telpon.setText(sharedPrefManager.getSPTelpon());
        Alamat.setText(sharedPrefManager.getSPAlamat());

    }


}
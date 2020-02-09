package com.ECatering.e_catering.Menu.ui.logout;

import android.content.Intent;
import android.os.Bundle;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;

import androidx.annotation.NonNull;
import androidx.fragment.app.Fragment;

import com.ECatering.e_catering.Menu.Dashboard;
import com.ECatering.e_catering.Menu.Login;
import com.ECatering.e_catering.Menu.ui.notifications.NotificationsViewModel;
import com.ECatering.e_catering.R;
import com.ECatering.e_catering.Storage.SharedPrefManager;

public class fragmentlogout extends Fragment {

    SharedPrefManager sharedPrefManager;
    Dashboard dashboard;


    public View onCreateView(@NonNull LayoutInflater inflater,
                             ViewGroup container, Bundle savedInstanceState) {
        View root = inflater.inflate(R.layout.fragmentlogout, container, false);
        sharedPrefManager = new SharedPrefManager(getActivity());
//        Dashboard dash=new Dashboard();
//        sharedPrefManager.saveSPBoolean(SharedPrefManager.SP_SUDAH_LOGIN, false);
//        startActivity(new Intent( getActivity(),Login.class).addFlags(Intent.FLAG_ACTIVITY_CLEAR_TOP | Intent.FLAG_ACTIVITY_NEW_TASK));
////        finish();

//           dash.keluar();
        return root;

    }

}
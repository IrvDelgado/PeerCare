package com.vogella.android.retrofitgithub.fragments;

import android.os.Bundle;
import android.support.annotation.Nullable;
import android.support.v4.app.Fragment;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ArrayAdapter;
import android.widget.ListView;

import com.vogella.android.retrofitgithub.R;

/**
 * A simple {@link Fragment} subclass.
 */
public class NotificacionFragment extends Fragment {
    private ListView lv1;
    private String notificaciones []= {
            "Usuario terminó tu tarea",
            "Usuario solicitó hacer tu tarea",
            "Usuario rechazó tu tarea"};
    public NotificacionFragment() {
        // Required empty public constructor
    }


    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container,
                             Bundle savedInstanceState) {
        // Inflate the layout for this fragment
        return inflater.inflate(R.layout.fragment_notificacion, container, false);
    }

    @Override
    public void onViewCreated(View view, @Nullable Bundle savedInstanceState) {
        super.onViewCreated(view, savedInstanceState);
        lv1 = (ListView) view.findViewById(R.id.listview1);
        ArrayAdapter<String> adapter = new ArrayAdapter<String>(getActivity(),R.layout.list_item_personalizado,notificaciones);
        lv1.setAdapter(adapter);

    }
}

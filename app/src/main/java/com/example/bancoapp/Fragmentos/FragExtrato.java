package com.example.bancoapp.Fragmentos;


import android.annotation.SuppressLint;
import android.os.Bundle;
import android.support.v4.app.Fragment;
import android.support.v7.widget.CardView;
import android.support.v7.widget.LinearLayoutManager;
import android.support.v7.widget.RecyclerView;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;

import com.example.bancoapp.Adapter.ExtratoAdapter;
import com.example.bancoapp.Persistencia.Extrato;
import com.example.bancoapp.R;

import java.util.List;

public class FragExtrato extends Fragment {
    private List<Extrato> extratoList;
    private RecyclerView card;

    public FragExtrato() {
    }

    @SuppressLint("ValidFragment")
    public FragExtrato(List<Extrato> list) {
        this.extratoList = list;
    }


    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container,
                             Bundle savedInstanceState) {
        // Inflate o fragmento
        View v = inflater.inflate(R.layout.fragment_extrato, container, false);
        card = v.findViewById(R.id.cardViewExtrato);

        card.setLayoutManager(new LinearLayoutManager(getActivity().getApplicationContext(), LinearLayoutManager.VERTICAL, false));
        card.setAdapter(new ExtratoAdapter(extratoList, getContext()));
        return v;

    }

}

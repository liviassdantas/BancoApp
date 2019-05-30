package com.example.bancoapp.Fragmentos;


import android.os.Bundle;
import android.support.v4.app.Fragment;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.Button;
import android.widget.EditText;
import android.widget.TextView;

import com.example.bancoapp.R;

public class TelaLogin extends Fragment {
    private TextView txtBemvindo;
    private EditText login;
    private EditText senha;
    private Button   btnlogar;

    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container,
                             Bundle savedInstanceState) {
        // Inflate the layout for this fragment
        View view = inflater.inflate(R.layout.fragment_tela_login, container, false);
        txtBemvindo = view.findViewById(R.id.txtbemvindo);
        login = view.findViewById(R.id.fragLogin);
        senha = view.findViewById(R.id.fragSenha);
        btnlogar = view.findViewById(R.id.btnlogar);

        return view;
    }

}

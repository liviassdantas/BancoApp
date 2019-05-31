package com.example.bancoapp.Fragmentos;


import android.os.Bundle;
import android.support.v4.app.Fragment;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.Button;
import android.widget.EditText;
import android.widget.TextView;
import android.widget.Toast;

import com.example.bancoapp.Persistencia.ContaRoom;
import com.example.bancoapp.Persistencia.ContaRoomDAO;
import com.example.bancoapp.Persistencia.contaDatabase;
import com.example.bancoapp.Prefs.Preferencia;
import com.example.bancoapp.R;

import java.util.ArrayList;

public class TelaLogin extends Fragment {
    private EditText login;
    private EditText senha;
    private Button btnlogar;
    private Button btnCriar;
    private contaDatabase banco;

    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container,
                             Bundle savedInstanceState) {

        banco = contaDatabase.getInstance(getContext());
        // view
        View view = inflater.inflate(R.layout.fragment_tela_login, container, false);

        //elementos
        login = view.findViewById(R.id.fragLogin);
        senha = view.findViewById(R.id.fragSenha);
        btnlogar = view.findViewById(R.id.btnlogar);
        btnCriar = view.findViewById(R.id.btnTemConta);

        //testando se o login/conta tem 5 dígitos
        login.setOnFocusChangeListener(new View.OnFocusChangeListener() {
            @Override
            public void onFocusChange(View view, boolean hasfocus) {
                if (!hasfocus) {
                    if (login.getText().length() < 5) {
                        login.setError("Conta Inválida");
                    }

                }
            }

        });

        //testando se a senha tem 4 dígitos
        senha.setOnFocusChangeListener(new View.OnFocusChangeListener() {
            @Override
            public void onFocusChange(View view, boolean hasfocus2) {
                if (!hasfocus2) {
                    if (senha.getText().length() < 4) {
                        senha.setError("Senha inválida");
                    }
                }

            }
        });

        //Botão logar
        btnlogar.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                new Thread(new Runnable() {
                    @Override
                    public void run() {
                        Long loginv = Long.parseLong(login.getText().toString());
                        Integer senhav = Integer.parseInt(senha.getText().toString());
                        ContaRoom conta = banco.contaDao().getContaByContaSenha(loginv, senhav);
                        if (conta == null) {
                            getActivity().runOnUiThread(new Runnable() {
                                @Override
                                public void run() {
                                    Toast.makeText(getContext(), "Usuario ou senha Invalida!", Toast.LENGTH_SHORT).show();
                                }
                            });
                        } else {
                            Preferencia.setConta(getActivity().getApplicationContext(),conta.getNumContaCorrente());
                            getActivity().runOnUiThread(new Runnable() {
                                @Override
                                public void run() {
                                    getFragmentManager().beginTransaction()
                                            .replace(R.id.ContainerFragment, new TelaInicio(), "Inicio")
                                            .commit();
                                }
                            });


                        }
                    }
                }).start();

            }
        });


        btnCriar.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                getFragmentManager().beginTransaction()
                        .replace(R.id.ContainerFragment, new Cadastro(), "Cadastro")
                        .commit();
            }
        });

        return view;
    }

}

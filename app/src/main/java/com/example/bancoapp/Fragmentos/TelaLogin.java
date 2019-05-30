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
import com.example.bancoapp.Persistencia.contaDatabase;
import com.example.bancoapp.R;

import java.util.ArrayList;

public class TelaLogin extends Fragment {
    private TextView txtBemvindo;
    private EditText login;
    private EditText senha;
    private Button   btnlogar;


    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container,
                             Bundle savedInstanceState) {
        // view
        View view = inflater.inflate(R.layout.fragment_tela_login, container, false);

        //elementos
        txtBemvindo = view.findViewById(R.id.txtbemvindo);
        login = view.findViewById(R.id.fragLogin);
        senha = view.findViewById(R.id.fragSenha);
        btnlogar = view.findViewById(R.id.btnlogar);
        final contaDatabase banco = (contaDatabase) contaDatabase.getInstance(getContext()).contaDao();

        //testando se o login/conta tem 5 dígitos
        login.setOnFocusChangeListener(new View.OnFocusChangeListener() {
            @Override
            public void onFocusChange(View view, boolean hasfocus) {
                if (!hasfocus){
                    if (login.getText().length()<5){
                        login.setError("Conta Inválida");
                    }

                }
            }

        });

        //testando se a senha tem 4 dígitos
        senha.setOnFocusChangeListener(new View.OnFocusChangeListener() {
            @Override
            public void onFocusChange(View view, boolean hasfocus2) {
                if(!hasfocus2){
                    if (senha.getText().length()<4){
                        senha.setError("Senha inválida");
                    }
                }

            }
        });

        //Botão logar
        btnlogar.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                ContaRoom conta = (ContaRoom) banco.contaDao().getAll();
                Long loginv = Long.parseLong(login.getText().toString());
                Integer senhav = Integer.parseInt(senha.getText().toString());

                //testando se a conta e o id estão cadastrados no banco
                if (loginv.equals(conta.getNumContaCorrente())){
                    if (senhav.equals(conta.getSenhaApp())){
                        assert getFragmentManager() != null;
                        getFragmentManager().beginTransaction()
                            .replace(R.id.ContainerFragment, new TelaInicio(), "TelaInicio")
                            .commit();
                    }else{
                        Toast.makeText(getContext(), "Senha Incorreta", Toast.LENGTH_SHORT).show();
                    }
                }else{
                        Toast.makeText(getContext(),"Numero de conta incorreto", Toast.LENGTH_SHORT).show();
                }
            }
        });
        return view;
    }

}

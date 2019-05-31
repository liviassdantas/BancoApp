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
import com.example.bancoapp.R;

public class Cadastro extends Fragment {
    //instância do banco
    private TextView cadastrar;
    private EditText nome;
    private EditText email;
    private EditText contacorrente;
    private EditText senha;
    private TextView vip;
    private Button btnvipsim;
    private Button btnvipnao;
    private Button btnconcluir;
    private contaDatabase banco;

    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container,
                             Bundle savedInstanceState) {

        banco = contaDatabase.getInstance(getContext());
        //view
        View view = inflater.inflate(R.layout.fragment_cadastro, container, false);

        //localizando os itens
        cadastrar = view.findViewById(R.id.txtcadastrar);
        nome = view.findViewById(R.id.fragCadastroNome);
        email = view.findViewById(R.id.fragCadastroEmail);
        contacorrente = view.findViewById(R.id.fragCadastroUser);
        senha = view.findViewById(R.id.fragCadastroSenha);
        vip = view.findViewById(R.id.txtvip);
        btnvipsim = view.findViewById(R.id.btnSim);
        btnvipnao = view.findViewById(R.id.btnNao);
        btnconcluir = view.findViewById(R.id.btnCadastrar);


        //Btn Salvar
        btnconcluir.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {

               final ContaRoom novaconta = new ContaRoom();
                novaconta.setCliente(nome.getText().toString());
                novaconta.setEmail(email.getText().toString());
                novaconta.setNumContaCorrente((long) Integer.parseInt(contacorrente.getText().toString()));
                novaconta.setSenhaApp(Integer.parseInt(senha.getText().toString()));
                if (btnvipsim.isSelected()) {
                    novaconta.setEhVip(true);
                } else {
                    novaconta.setEhVip(false);
                }
                if (btnvipnao.isSelected()) {
                    novaconta.setEhVip(false);
                } else {
                    novaconta.setEhVip(true);
                }
                novaconta.setSaldo(0.0);

                //Thread para inserir no banco
                new Thread(new Runnable() {
                    @Override
                    public void run() {
                        if (banco.contaDao().insert(novaconta)) {

                            getActivity().runOnUiThread(new Runnable() {
                                @Override
                                public void run() {
                                    Toast.makeText(getContext(), "Conta Criada!", Toast.LENGTH_SHORT).show();
                                    assert getFragmentManager() != null;
                                    getFragmentManager().beginTransaction()
                                            .replace(R.id.ContainerFragment, new TelaInicio(), "TelaInicio")
                                            .commit();
                                }
                            });

                        } else {
                            getActivity().runOnUiThread(new Runnable() {
                                @Override
                                public void run() {
                                    Toast.makeText(getContext(),
                                            "Usuário já cadastrado, favor, efetuar login", Toast.LENGTH_SHORT).show();

                                    assert getFragmentManager() != null;
                                    getFragmentManager().beginTransaction()
                                            .replace(R.id.ContainerFragment, new TelaLogin(), "TelaLogin")
                                            .commit();
                                }
                            });
                        }
                    }
                }).start();

            }
        });

        return view;
    }

}

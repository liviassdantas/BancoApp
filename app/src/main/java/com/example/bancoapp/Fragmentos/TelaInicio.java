package com.example.bancoapp.Fragmentos;


import android.app.AlertDialog;
import android.content.DialogInterface;
import android.os.Build;
import android.os.Bundle;
import android.support.annotation.RequiresApi;
import android.support.v4.app.Fragment;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.EditText;
import android.widget.Switch;
import android.widget.TextView;
import android.widget.Toast;

import com.example.bancoapp.Persistencia.ContaRoom;
import com.example.bancoapp.Persistencia.Extrato;
import com.example.bancoapp.Persistencia.contaDatabase;
import com.example.bancoapp.Prefs.Preferencia;
import com.example.bancoapp.R;

import java.util.Date;
import java.util.List;
import java.util.Objects;

public class TelaInicio extends Fragment {
    private TextView saldo;
    private TextView deposito;
    private TextView extrato;
    private TextView saque;
    private TextView transferencia;
    private View builderview;
    final contaDatabase banco = contaDatabase.getInstance(this.getContext());
    private ContaRoom contaConectada;

    @Override
    public View onCreateView(final LayoutInflater inflater, ViewGroup container,
                             Bundle savedInstanceState) {
        new Thread(new Runnable() {
            @Override
            public void run() {
                long numConta = Preferencia.getConta(getContext());
                if (numConta != -1) {
                    contaConectada = banco.contaDao().getContaById(numConta);
                } else {
                    assert getFragmentManager() != null;
                    getFragmentManager().beginTransaction()
                            .replace(R.id.ContainerFragment, new TelaLogin(), "TelaLogin")
                            .commit();
                }
            }
        }).start();


        View view = inflater.inflate(R.layout.fragment_tela_inicio, container, false);


        saldo = view.findViewById(R.id.saldo);
        deposito = view.findViewById(R.id.deposito);
        extrato = view.findViewById(R.id.extrato);
        saque = view.findViewById(R.id.saque);
        transferencia = view.findViewById(R.id.transferencia);

        //clique do saldo
        final String saldoconvert = saldo.getText().toString();
        saldo.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {

                String saldoimprime = contaConectada.getSaldo().toString();

                if (saldo.getText().toString().equals(saldoconvert)) {
                    saldo.setText(saldoimprime);
                } else {
                    saldo.setText(saldoconvert);
                }

            }
        });

        saque.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                //clique do Saque
                AlertDialog.Builder builder = new AlertDialog.Builder(getContext());
                builderview = inflater.inflate(R.layout.alertdialog_layout, null);
                builder.setView(builderview);
                builder.setTitle("Saque");
                builder.setMessage("Digite o valor para ser sacado");
                builder.setPositiveButton("Saque", new DialogInterface.OnClickListener() {
                    @Override
                    public void onClick(DialogInterface dialogInterface, int i) {
                        new Thread(new Runnable() {
                            @Override
                            public void run() {
                                EditText saqueValor = builderview.findViewById(R.id.textdepositoalert);
                                Double valor = Double.parseDouble(saqueValor.getText().toString());
                                contaConectada.saqueValor(valor);
                                Extrato ex = new Extrato("Saque" , new Date(),contaConectada.getNumContaCorrente(),(valor*-1));
                                banco.extratoRoomDAO().insertExtrato(ex);
                                banco.contaDao().update(contaConectada);
                            }
                        }).start();

                        Toast.makeText(getContext(), "Valor Sacado", Toast.LENGTH_SHORT).show();
                    }
                });
                builder.setNegativeButton("Sair", null);
                builder.setCancelable(false);
                builder.create().show();

            }


        });


        //clique do depósito
        deposito.setOnClickListener(new View.OnClickListener() {
            @RequiresApi(api = Build.VERSION_CODES.LOLLIPOP)
            @Override
            public void onClick(View view) {
                AlertDialog.Builder builder = new AlertDialog.Builder(getContext());
                builderview = inflater.inflate(R.layout.alertdialog_layout, null);
                builder.setView(builderview);
                builder.setTitle("Depósito");
                builder.setMessage("Digite o valor a ser depositado");
                builder.setPositiveButton("Depositar", new DialogInterface.OnClickListener() {
                    @Override
                    public void onClick(DialogInterface dialogInterface, int i) {
                        new Thread(new Runnable() {
                            @Override
                            public void run() {
                                EditText depositoValor = builderview.findViewById(R.id.textdepositoalert);
                                Double valor = Double.parseDouble(depositoValor.getText().toString());
                                contaConectada.depositarValor(valor);
                                Extrato ex = new Extrato("Deposito" , new Date(),contaConectada.getNumContaCorrente(),valor);
                                banco.extratoRoomDAO().insertExtrato(ex);
                                banco.contaDao().update(contaConectada);
                            }
                        }).start();

                        Toast.makeText(getContext(), "Valor Depositado", Toast.LENGTH_SHORT).show();
                    }
                });
                builder.setNegativeButton("Sair", null);
                builder.setCancelable(false);
                builder.create().show();

            }
        });

        transferencia.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                AlertDialog.Builder builder = new AlertDialog.Builder(getContext());
                builderview = inflater.inflate(R.layout.alertdialog_layout_transferencia, null);
                builder.setView(builderview);
                builder.setTitle("Transferência");
                builder.setMessage("Digite a Conta e o Valor a ser transferido ");
                builder.setPositiveButton("Transferir", new DialogInterface.OnClickListener() {
                    @Override
                    public void onClick(DialogInterface dialogInterface, int i) {
                        new Thread(new Runnable() {
                            @Override
                            public void run() {
                                EditText depositoValor = builderview.findViewById(R.id.texttransferenciaValor);
                                EditText contaValor = builderview.findViewById(R.id.texttransferenciaConta);
                                if (contaConectada.getNumContaCorrente() == Long.parseLong(contaValor.getText().toString())) {
                                   getActivity().runOnUiThread(new Runnable() {
                                       @Override
                                       public void run() {
                                           Toast.makeText(getContext(), "Não foi possivel realizar transferência para a mesma conta", Toast.LENGTH_SHORT).show();
                                       }
                                   });

                                } else {

                                    ContaRoom contaDestino = banco.contaDao().getContaById(Long.parseLong(contaValor.getText().toString()));
                                    if (contaDestino == null) {
                                        getActivity().runOnUiThread(new Runnable() {
                                            @Override
                                            public void run() {
                                                Toast.makeText(getContext(), "Conta Inexistente", Toast.LENGTH_SHORT).show();
                                            }
                                        });

                                    } else {
                                        Double valor = Double.parseDouble(depositoValor.getText().toString());
                                        contaConectada.transferencia(contaDestino, valor);
                                        //region FragExtrato da conta conectada
                                        Extrato ex = new Extrato("Transferencia" , new Date(),contaConectada.getNumContaCorrente(),(valor*-1));
                                        banco.extratoRoomDAO().insertExtrato(ex);
                                        //endregion

                                        //region FragExtrato da conta destino
                                        Extrato exDestino = new Extrato("Transferencia" , new Date(),contaDestino.getNumContaCorrente(),valor);
                                        banco.extratoRoomDAO().insertExtrato(exDestino);
                                        //endregion

                                        //region Usuario Vip
                                        if(contaConectada.getEhVip()){
                                            Extrato porcExtrato = new Extrato("Serviço" , new Date(),contaConectada.getNumContaCorrente(), (valor*0.08)*-1);
                                            banco.extratoRoomDAO().insertExtrato(porcExtrato);
                                        }else{
                                            Extrato servicoExtrato = new Extrato("Servico" , new Date(),contaConectada.getNumContaCorrente(),-8.0);
                                            banco.extratoRoomDAO().insertExtrato(servicoExtrato);
                                        }
                                        //endregion
                                        banco.contaDao().update(contaConectada);
                                    }
                                }

                            }
                        }).start();

                        Toast.makeText(getContext(), "Valor Depositado", Toast.LENGTH_SHORT).show();
                    }
                });
                builder.setNegativeButton("Sair", null);
                builder.setCancelable(false);
                builder.create().show();
            }
        });

        extrato.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                new Thread(new Runnable() {
                    @Override
                    public void run() {
                        List<Extrato> listExtrato = banco.extratoRoomDAO().getByConta(contaConectada.getNumContaCorrente());

                        assert getFragmentManager() != null;
                        getFragmentManager().beginTransaction()
                                .replace(R.id.ContainerFragment, new FragExtrato(listExtrato), "Extrato")
                                .commit();
                    }
                }).start();


            }
        });

        return view;
    }


}





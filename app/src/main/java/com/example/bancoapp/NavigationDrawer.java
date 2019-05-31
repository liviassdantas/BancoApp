package com.example.bancoapp;

import android.app.AlertDialog;
import android.content.DialogInterface;
import android.os.Bundle;

import android.support.annotation.NonNull;
import android.support.design.widget.NavigationView;
import android.support.v4.view.GravityCompat;
import android.support.v4.widget.DrawerLayout;
import android.support.v7.app.ActionBarDrawerToggle;
import android.support.v7.app.AppCompatActivity;
import android.support.v7.widget.Toolbar;
import android.view.MenuItem;
import android.widget.Toast;

import com.example.bancoapp.Persistencia.ContaRoom;
import com.example.bancoapp.Persistencia.contaDatabase;

import com.example.bancoapp.Fragmentos.TelaLogin;
import com.example.bancoapp.Prefs.Preferencia;

public class NavigationDrawer extends AppCompatActivity implements
        NavigationView.OnNavigationItemSelectedListener {
    private Toolbar toolbar;
    private DrawerLayout drwlayout;
    private NavigationView nvgtview;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.navdrawer_activity_main);

        //inicializando a toolbar
        toolbar = (Toolbar) findViewById(R.id.toolbar);
        setSupportActionBar(toolbar);

        //inicializando o drawer layout
        drwlayout = (DrawerLayout) findViewById(R.id.drawerLayout);

        //toggle
        ActionBarDrawerToggle toogle = new ActionBarDrawerToggle
                (this, drwlayout, toolbar, R.string.abrir_drawer, R.string.fechar_drawer);
        drwlayout.addDrawerListener(toogle);

        toogle.syncState();

        //click do navigation
        nvgtview = (NavigationView) findViewById(R.id.navigationView);
        nvgtview.setNavigationItemSelectedListener(this);

        getSupportFragmentManager().beginTransaction()
                .replace(R.id.ContainerFragment, new TelaLogin(), "TelaLogin")
                .commit();
    }

    //botão voltar
    @Override
    public void onBackPressed() {
        if (drwlayout.isDrawerOpen(GravityCompat.START)) {
            drwlayout.closeDrawer(GravityCompat.START);
        } else {
            super.onBackPressed();
        }
    }

    @Override
    public boolean onNavigationItemSelected(@NonNull MenuItem menuItem) {
        switch (menuItem.getItemId()) {
            //caso item 1
            case R.id.item1: {
                Long numConta = Preferencia.getConta(getApplicationContext());
                final ContaRoom conta = contaDatabase.getInstance(getApplicationContext()).contaDao().getContaById(numConta);
                if (conta.getEhVip()) {
                    AlertDialog.Builder builder = new AlertDialog.Builder(getApplicationContext());
                    builder.setTitle("Visita Gerente");
                    builder.setMessage("Deseja Confirmar a Visita? \n Será debitado R$50.00 de sua conta!");
                    builder.setPositiveButton("Confirmar", new DialogInterface.OnClickListener() {
                        @Override
                        public void onClick(DialogInterface dialogInterface, int i) {
                            new Thread(new Runnable() {
                                @Override
                                public void run() {

                                    conta.visitaGerente();
                                    contaDatabase.getInstance(getApplicationContext()).contaDao().update(conta);
                                }
                            }).start();

                            Toast.makeText(getApplicationContext(), "Visita Confirmada", Toast.LENGTH_SHORT).show();
                        }
                    });
                    builder.setNegativeButton("Sair", null);
                    builder.setCancelable(false);
                    builder.create().show();

                }else{
                    Toast.makeText(getApplicationContext(), "Função indisponivel", Toast.LENGTH_SHORT).show();
                }
            }
            //caso item 2
            case R.id.item2: {
                getSupportFragmentManager().beginTransaction()
                        .replace(R.id.ContainerFragment, new TelaLogin(), "TelaLogin")
                        .commit();
                break;
            }
            default: {
                Toast.makeText(this, "Menu", Toast.LENGTH_SHORT).show();
            }
        }
        //fechando a navigation bar
        drwlayout.closeDrawer(GravityCompat.START);
        return true;

    }
}

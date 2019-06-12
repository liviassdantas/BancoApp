package com.example.bancoapp.Adapter;

import android.annotation.SuppressLint;
import android.app.Activity;
import android.content.Context;
import android.support.annotation.NonNull;
import android.support.v7.widget.CardView;
import android.support.v7.widget.RecyclerView;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.TextView;

import com.example.bancoapp.Persistencia.Extrato;
import com.example.bancoapp.R;

import java.text.SimpleDateFormat;
import java.util.List;

public class ExtratoAdapter extends RecyclerView.Adapter<ExtratoAdapter.Holder> {
    private List<Extrato> listExtrato;
    private Context context;

    public ExtratoAdapter(List<Extrato> extrato, Context context) {
        this.listExtrato = extrato;
        this.context = context;
    }

    @NonNull
    @Override
    public Holder onCreateViewHolder(@NonNull ViewGroup viewGroup, int i) {
        View v = LayoutInflater.from(context).inflate(R.layout.adapter_cards_extrato, viewGroup, false);

        return new Holder(v);
    }

    @SuppressLint({"SetTextI18n", "SimpleDateFormat"})
    @Override
    public void onBindViewHolder(@NonNull Holder holder, int i) {
        holder.txtValor.setText(listExtrato.get(i).getValor().toString());
        holder.txtDepartamento.setText(listExtrato.get(i).getEstabelecimento());
        holder.txtData.setText(new SimpleDateFormat("dd-MM-yyyy").format(listExtrato.get(i).getDataTransacao()));
    }

    @Override
    public int getItemCount() {
        return listExtrato.size();
    }

    public class Holder extends RecyclerView.ViewHolder {
        public TextView txtData;
        public TextView txtDepartamento;
        public TextView txtValor;

        public Holder(@NonNull View itemView) {
            super(itemView);
            txtData = itemView.findViewById(R.id.adapter_cardView_txvData);
            txtDepartamento = itemView.findViewById(R.id.adapter_cardView_txvEstab);
            txtValor = itemView.findViewById(R.id.adapter_cardView_txvDebito);
        }
    }
}


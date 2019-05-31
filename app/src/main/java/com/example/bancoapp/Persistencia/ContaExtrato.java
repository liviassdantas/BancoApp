package com.example.bancoapp.Persistencia;

import android.arch.persistence.room.Embedded;
import android.arch.persistence.room.Relation;

import java.util.List;

public class ContaExtrato {
    @Embedded
    private ContaRoom conta;

    @Relation(parentColumn = "numContaCorrente", entityColumn = "idConta")
    public List<Extrato> extrato;


    public ContaRoom getConta() {
        return conta;
    }

    public void setConta(ContaRoom conta) {
        this.conta = conta;
    }

    public List<Extrato> getExtrato() {
        return extrato;
    }

    public void setExtrato(List<Extrato> extrato) {
        this.extrato = extrato;
    }
}

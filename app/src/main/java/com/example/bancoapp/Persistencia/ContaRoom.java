package com.example.bancoapp.Persistencia;

import android.arch.persistence.room.ColumnInfo;
import android.arch.persistence.room.Entity;
import android.arch.persistence.room.PrimaryKey;

@Entity(tableName = "conta")
public class ContaRoom {
    @PrimaryKey  private Integer numContaCorrente;
    @ColumnInfo(name = "senha") private Integer senhaApp;
    @ColumnInfo(name = "cliente") private String Cliente;
    @ColumnInfo(name = "email") private String email;
    @ColumnInfo(name = "vip") private Boolean ehVip;
    @ColumnInfo(name = "saldo") private Double saldo;

    public Integer getNumContaCorrente() {
        return numContaCorrente;
    }

    public void setNumContaCorrente(Integer numContaCorrente) {
        this.numContaCorrente = numContaCorrente;
    }

    public Integer getSenhaApp() {
        return senhaApp;
    }

    public void setSenhaApp(Integer senhaApp) {
        this.senhaApp = senhaApp;
    }

    public String getCliente() {
        return Cliente;
    }

    public void setCliente(String cliente) {
        Cliente = cliente;
    }

    public String getEmail() {
        return email;
    }

    public void setEmail(String email) {
        this.email = email;
    }

    public Boolean getEhVip() {
        return ehVip;
    }

    public void setEhVip(Boolean ehVip) {
        this.ehVip = ehVip;
    }

    public Double getSaldo() {
        return saldo;
    }

    public void setSaldo(Double saldo) {
        this.saldo = saldo;
    }


}

package com.example.bancoapp.Persistencia;

import android.arch.persistence.room.ColumnInfo;
import android.arch.persistence.room.Entity;
import android.arch.persistence.room.PrimaryKey;

//nome da entidade/classe
@Entity(tableName = "conta")
public class ContaRoom {
    //campos
    @PrimaryKey  private Long numContaCorrente;
    @ColumnInfo(name = "senha") private Integer senhaApp;
    @ColumnInfo(name = "cliente") private String Cliente;
    @ColumnInfo(name = "email") private String email;
    @ColumnInfo(name = "vip") private Boolean ehVip;
    @ColumnInfo(name = "saldo") private Double saldo;

    //construtor vazio
    public ContaRoom(){}

    //construtor incial
    public ContaRoom(Long numContaCorrente, Integer senhaApp,
                     String cliente, String email, Boolean ehVip,
                     Double saldo) {
        this.numContaCorrente = numContaCorrente;
        this.senhaApp = senhaApp;
        Cliente = cliente;
        this.email = email;
        this.ehVip = ehVip;
        this.saldo = saldo;
    }


    //getters e setters
    public Long getNumContaCorrente() {
        return numContaCorrente;
    }

    public void setNumContaCorrente(Long numContaCorrente) {
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

package com.example.bancoapp.Persistencia;

import android.arch.persistence.room.ColumnInfo;
import android.arch.persistence.room.Entity;
import android.arch.persistence.room.Ignore;
import android.arch.persistence.room.PrimaryKey;

import java.util.ArrayList;
import java.util.List;

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
    @Ignore private List<Extrato> extrato = new ArrayList<>();
    @Ignore private Double limiteTransferencia;



    //construtor vazio
    public ContaRoom(){}

    //construtor incial
    public ContaRoom(Long numContaCorrente, Integer senhaApp,
                     String cliente, String email, Boolean ehVip) {
        this.numContaCorrente = numContaCorrente;
        this.senhaApp = senhaApp;
        Cliente = cliente;
        this.email = email;
        this.ehVip = ehVip;
        this.saldo = 0.0;
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

    public List<Extrato> getExtrato() {
        return extrato;
    }

    public void setExtrato(List<Extrato> extrato) {
        this.extrato = extrato;
    }

    public Double getLimiteTransferencia() {
        return limiteTransferencia;
    }

    public void setLimiteTransferencia(Double limiteTransferencia) {
        this.limiteTransferencia = limiteTransferencia;
    }

    //Metodos

    public void depositarValor (Double valor){
       this.setSaldo(this.getSaldo() + valor);
    }

    public boolean saqueValor(Double valor){
        if(this.getEhVip()){
            if(this.getSaldo() < 0 ){
               Double juros = getSaldo()*0.01;
               this.setSaldo(getSaldo() - (valor +juros));
            }else{
                this.setSaldo(getSaldo() - valor);
            }
        }else{
            if(this.getSaldo() < 0){
                return false;
            }else{
                this.setSaldo(getSaldo() - valor);
            }
        }
        return true;
    }

    public void visitaGerente(){
        this.setSaldo(this.getSaldo() - 50);
    }

    public boolean transferencia(ContaRoom destino, Double valor){
        if(this.getEhVip()){
            Double juros = (valor * 0.08);
            this.saqueValor(valor+juros);
            destino.depositarValor(valor);
            return true;
        }else{
            if(getLimiteTransferencia() <= 1000.0) {
                this.setLimiteTransferencia(valor);
                this.saqueValor(valor + 8.0);
                destino.depositarValor(valor);
                return true;
            }else {
                return false;
            }
        }
    }

}

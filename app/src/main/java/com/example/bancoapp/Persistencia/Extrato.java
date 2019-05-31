package com.example.bancoapp.Persistencia;

import android.arch.persistence.room.ColumnInfo;
import android.arch.persistence.room.Entity;
import android.arch.persistence.room.ForeignKey;
import android.arch.persistence.room.PrimaryKey;

import java.util.Date;

import static android.arch.persistence.room.ForeignKey.CASCADE;

@Entity(tableName = "extrato", foreignKeys = {@ForeignKey(
        entity = ContaRoom.class, parentColumns = "numContaCorrente", childColumns = "idConta")})
public class Extrato {
    @PrimaryKey(autoGenerate = true) private Integer idExtrato;
    @ColumnInfo(name ="estabelecimento" ) private String estabelecimento;
    @ColumnInfo(name = "dataTransacao") private Date dataTransacao;
    @ColumnInfo(name = "idConta") private Long idConta;
    @ColumnInfo(name = "valor") private Double valor;

    public Extrato(String estabelecimento, Date dataTransacao, Long idConta, Double valor) {
        this.estabelecimento = estabelecimento;
        this.dataTransacao = dataTransacao;
        this.idConta = idConta;
        this.valor = valor;
    }

    public Integer getIdExtrato() {
        return idExtrato;
    }

    public void setIdExtrato(Integer idExtrato) {
        this.idExtrato = idExtrato;
    }

    public String getEstabelecimento() {
        return estabelecimento;
    }

    public void setEstabelecimento(String estabelecimento) {
        this.estabelecimento = estabelecimento;
    }

    public Date getDataTransacao() {
        return dataTransacao;
    }

    public void setDataTransacao(Date dataTransacao) {
        this.dataTransacao = dataTransacao;
    }

    public Long getIdConta() {
        return idConta;
    }

    public void setIdConta(Long idConta) {
        this.idConta = idConta;
    }

    public Double getValor() {
        return valor;
    }

    public void setValor(Double valor) {
        this.valor = valor;
    }
}

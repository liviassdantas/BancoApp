package com.example.bancoapp.Persistencia;

import android.arch.persistence.room.ColumnInfo;
import android.arch.persistence.room.Entity;
import android.arch.persistence.room.ForeignKey;
import android.arch.persistence.room.PrimaryKey;

import java.util.Date;

@Entity(foreignKeys = {
        @ForeignKey(
        entity = ContaRoom.class,
        parentColumns = "numContaCorrente",
        childColumns = "idExtrato")
        }
)
public class Extrato {
    @PrimaryKey private Integer idExtrato;
    @ColumnInfo(name ="estabelecimento" ) private String estabelecimento;
    @ColumnInfo(name = "dataTransacao") private Date dataTransacao;

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



}

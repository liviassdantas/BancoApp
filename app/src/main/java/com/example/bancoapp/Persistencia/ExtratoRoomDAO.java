package com.example.bancoapp.Persistencia;

import android.arch.persistence.room.Dao;
import android.arch.persistence.room.Insert;
import android.arch.persistence.room.Query;
import android.arch.persistence.room.Update;

import java.util.List;

@Dao
public abstract class ExtratoRoomDAO {

    @Insert
    public abstract Long insertExtrato(Extrato extrato);


    @Query("SELECT * FROM extrato WHERE idConta = :idConta")
    public abstract List<Extrato> getByConta (long idConta);
}
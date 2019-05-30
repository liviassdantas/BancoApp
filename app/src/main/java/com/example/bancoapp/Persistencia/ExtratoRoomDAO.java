package com.example.bancoapp.Persistencia;

import android.arch.persistence.room.Dao;
import android.arch.persistence.room.Insert;
import android.arch.persistence.room.Query;
import android.arch.persistence.room.Update;

@Dao
public interface ExtratoRoomDAO {

    @Insert
    Long insertExtrato(Extrato extrato);

    @Update
    Long updateExtrato(Extrato extrato);

    @Query("SELECT * FROM Extrato WHERE idExtrato =:id")
    Extrato getExtratoByID(Long id);
}
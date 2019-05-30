package com.example.bancoapp.Persistencia;

import android.arch.persistence.room.Dao;
import android.arch.persistence.room.Insert;

@Dao
public interface ExtratoRoomDAO {

@Insert
Long insertExtrato(Extrato extrato);
}

package com.example.bancoapp.Persistencia;

import android.arch.persistence.room.Dao;
import android.arch.persistence.room.Delete;
import android.arch.persistence.room.Insert;
import android.arch.persistence.room.OnConflictStrategy;
import android.arch.persistence.room.Query;
import android.arch.persistence.room.Transaction;
import android.arch.persistence.room.Update;
import android.support.v4.view.ViewPager;

import java.util.ArrayList;
import java.util.List;

@Dao
public abstract class ContaRoomDAO {

    @Insert()
    protected abstract long _insert(ContaRoom contaRoom);

    public long insertReturn(ContaRoom conta) {
        return _insert(conta);
    }

    public boolean insert(ContaRoom conta) {
        return _insert(conta) > 0;
    }


    @Update
    public abstract void update(ContaRoom contaRoom);

    @Delete
    public abstract void delete(ContaRoom contaRoom);


    @Transaction
    @Query("SELECT * FROM conta")
    public abstract List<ContaExtrato> getAll();

    public List<ContaRoom> getConta(List<ContaExtrato> list) {
        List<ContaRoom> retorno = new ArrayList<>();
        if (list != null) {
            for (ContaExtrato ce : list) {
                ce.getConta().setExtrato(ce.getExtrato());
                retorno.add(ce.getConta());
            }
        }

        return retorno;
    }

    @Query("SELECT * FROM conta WHERE numContaCorrente = :id")
    public abstract ContaRoom getContaById(Long id);

    @Query("SELECT * FROM conta WHERE numContaCorrente = :conta AND senha= :senha")
    public abstract ContaRoom getContaByContaSenha(Long conta, Integer senha);


}

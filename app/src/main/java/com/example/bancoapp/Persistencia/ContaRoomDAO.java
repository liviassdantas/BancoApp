package com.example.bancoapp.Persistencia;

import android.arch.persistence.room.Dao;
import android.arch.persistence.room.Delete;
import android.arch.persistence.room.Insert;
import android.arch.persistence.room.OnConflictStrategy;
import android.arch.persistence.room.Query;
import android.arch.persistence.room.Update;
import android.support.v4.view.ViewPager;

@Dao
public interface ContaRoomDAO {
@Insert
Long insert(ContaRoom contaRoom);

@Update
void update(ContaRoom contaRoom);

@Delete
void delete(ContaRoom contaRoom);



}

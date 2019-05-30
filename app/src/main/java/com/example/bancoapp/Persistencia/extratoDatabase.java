package com.example.bancoapp.Persistencia;

import android.arch.persistence.room.Database;
import android.arch.persistence.room.Room;
import android.arch.persistence.room.RoomDatabase;
import android.arch.persistence.room.TypeConverter;
import android.arch.persistence.room.TypeConverters;
import android.content.Context;

@Database(entities = {Extrato.class}, version = 1)
@TypeConverters({Conversor.class})
public abstract class extratoDatabase extends RoomDatabase {
    private static extratoDatabase bancoExtrato;
    public abstract ExtratoRoomDAO extratoRoomDAO();

    public static extratoDatabase getInstance(Context context){
        if (bancoExtrato==null) {
            bancoExtrato = Room.databaseBuilder(
                    context.getApplicationContext(),
                    extratoDatabase.class, "extrato_db")
                    .fallbackToDestructiveMigration()
                    .build();
        }return bancoExtrato;
        }
    }


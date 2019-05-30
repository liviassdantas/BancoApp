package com.example.bancoapp.Persistencia;

import android.arch.persistence.room.Database;
import android.arch.persistence.room.Room;
import android.arch.persistence.room.RoomDatabase;
import android.content.Context;


@Database(entities = {ContaRoom.class}, version = 1)
public abstract class contaDatabase extends RoomDatabase {
    private static contaDatabase bancoConta;

    public abstract ContaRoomDAO contaDao();


    public static contaDatabase getInstance(Context context) {
        if (bancoConta == null) {
            bancoConta = Room.databaseBuilder(
                    context.getApplicationContext(), contaDatabase.class, "conta_db")
                    .fallbackToDestructiveMigration()
                    .build();
        }
        return bancoConta;
    }

}

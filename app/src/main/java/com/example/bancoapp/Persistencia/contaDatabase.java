package com.example.bancoapp.Persistencia;

import android.arch.persistence.room.Database;
import android.arch.persistence.room.Room;
import android.arch.persistence.room.RoomDatabase;
import android.arch.persistence.room.TypeConverters;
import android.content.Context;

@TypeConverters(Conversor.class)
@Database(entities = {ContaRoom.class,Extrato.class}, version = 3)
public abstract class contaDatabase extends RoomDatabase {
    private static contaDatabase bancoConta = null;

    public abstract ContaRoomDAO contaDao();
    public abstract ExtratoRoomDAO extratoRoomDAO();


    public static contaDatabase getInstance(Context context) {
        if (bancoConta == null)
            bancoConta = Room.databaseBuilder(
                    context,
                    contaDatabase.class,
                    "conta_db"
            ).fallbackToDestructiveMigration().build();

        return bancoConta;
    }

}

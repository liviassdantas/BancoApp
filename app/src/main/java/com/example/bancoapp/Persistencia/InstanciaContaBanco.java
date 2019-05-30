package com.example.bancoapp.Persistencia;

import android.arch.persistence.room.Room;
import android.content.Context;
import android.support.annotation.NonNull;

public class InstanciaContaBanco {
        private Context context;
        private contaDatabase contadb = Room.databaseBuilder(
            context, contaDatabase.class,"conta_db"
        ).build();
}

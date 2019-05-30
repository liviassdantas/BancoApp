package com.example.bancoapp.Persistencia;

import android.arch.persistence.room.Room;
import android.content.Context;

public class InstanciaExtratoBanco {
    private Context context;
    private extratoDatabase extratodb = Room.databaseBuilder(
            context, extratoDatabase.class,"extrato_db"
    ).build();
}

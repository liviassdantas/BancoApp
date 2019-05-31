package com.example.bancoapp.Prefs;

import android.content.Context;
import android.content.SharedPreferences;

public class Preferencia {

    private static final String ID = "SharedPreferenceApp";

    public static void setConta(Context context, Long value) {
        //Mode - Sempre 0 pq os outros modes foram depreciados
        SharedPreferences.Editor ed = context.getSharedPreferences(ID, Context.MODE_PRIVATE).edit();
        ed.putLong("ContaConectada", value);
        ed.apply();
    }

    public static Long getConta(Context context) {
        return context.getSharedPreferences(ID, 0).getLong("ContaConectada", -1L);
    }

}





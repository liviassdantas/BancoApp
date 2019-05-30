package com.example.bancoapp.Persistencia;
import android.arch.persistence.room.TypeConverter;

import java.util.Date;

public class Conversor {

    @TypeConverter
    public static Date fromLong(Long value){
        return value == null ? null : new Date(value);
    }

    @TypeConverter
    public static Long fromDate(Date date){
        return date == null ? null : date.getTime();
    }
}

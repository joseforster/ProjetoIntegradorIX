package com.example.projeto_integrador_ix;

import android.content.Context;
import android.database.sqlite.SQLiteDatabase;
import android.database.sqlite.SQLiteOpenHelper;

public class MeuSQLite extends SQLiteOpenHelper {

    public MeuSQLite(Context context, String dbname){
        super(context, dbname, null, 1);
    }

    @Override
    public void onCreate(SQLiteDatabase sqLiteDatabase) {
        sqLiteDatabase.execSQL("Create table if not exists projeto " +
                "(id integer primary key autoincrement, " +
                "descricao text, " +
                "dt_inicio date default current_date," +
                "dt_fim date default current_date);" );

        sqLiteDatabase.execSQL("Create table if not exists requisito (" +
                "id integer primary key autoincrement, " +
                "descricao text not null, " +
                "dt_criacao date not null default current_date," +
                "nivel_importancia integer not null check(nivel_importancia in (1,2,3,4,5)), " +
                "nivel_dificuldade integer not null check(nivel_importancia in (1,2,3,4,5)), " +
                "tmp_estimado numeric(5,1) not null, " +
                "projeto_id integer not null, " +
                "foreign key (projeto_id) references projeto(id)" +
                ")");
    }

    @Override
    public void onUpgrade(SQLiteDatabase sqLiteDatabase, int i, int i1) {

    }
}

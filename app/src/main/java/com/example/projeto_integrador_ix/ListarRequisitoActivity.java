package com.example.projeto_integrador_ix;

import android.content.Intent;
import android.database.Cursor;
import android.database.sqlite.SQLiteDatabase;
import android.os.Bundle;
import android.view.View;
import android.widget.ArrayAdapter;
import android.widget.Button;
import android.widget.ListView;

import androidx.appcompat.app.AppCompatActivity;

import java.util.ArrayList;

public class ListarRequisitoActivity extends AppCompatActivity {
    MeuSQLite gerenciadorBancoDeDados;
    SQLiteDatabase bancoDeDados;

    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.listar_requisitos);

        gerenciadorBancoDeDados = new MeuSQLite(this, "projetointegrador");

        ListView listView = findViewById(R.id.lista_requisitos);

        Bundle b = this.getIntent().getExtras();

        int projetoId = 0;

        if(b != null){
            projetoId = b.getInt("projetoId");
        }

        ArrayList<String> listaRequisito = this.GetListaRequisitoByProjetoId(projetoId);

        ArrayAdapter arrayAdapter = new ArrayAdapter(this, android.R.layout
                .simple_list_item_1, listaRequisito);

        listView.setAdapter(arrayAdapter);
    }

    private ArrayList<String> GetListaRequisitoByProjetoId(int projetoId){
        ArrayList<String> listaRequisito = new ArrayList<>();

        bancoDeDados = gerenciadorBancoDeDados.getReadableDatabase();

        String[] campos = {"id", "descricao", "dt_criacao", "nivel_importancia", "nivel_dificuldade", "tmp_estimado"};
        String where = "projeto_Id = " + projetoId;

        Cursor lista = bancoDeDados.query("requisito", campos, where, null, null, null, null );

        lista.moveToFirst();

        while(lista.isAfterLast() == false){

            int id = Integer.parseInt(lista.getString(lista.getColumnIndexOrThrow("id")));
            String descricao = lista.getString(lista.getColumnIndexOrThrow("descricao"));
            String dt_criacao = lista.getString(lista.getColumnIndexOrThrow("dt_criacao"));
            String importancia = lista.getString(lista.getColumnIndexOrThrow("nivel_importancia"));
            String dificuldade = lista.getString(lista.getColumnIndexOrThrow("nivel_dificuldade"));
            String tmp_estimado = lista.getString(lista.getColumnIndexOrThrow("tmp_estimado"));

            String requisito = id + " - " + descricao + " - " + dt_criacao + " - Importância: " + importancia +
                    " - Dificuldade: " + dificuldade + " - Tempo Estimado(h): " + tmp_estimado;

            listaRequisito.add(requisito);

            lista.moveToNext();
        }

        bancoDeDados.close();

        return listaRequisito;
    }
}

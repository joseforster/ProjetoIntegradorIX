package com.example.projeto_integrador_ix;

import android.content.Intent;
import android.database.Cursor;
import android.database.sqlite.SQLiteDatabase;
import android.os.Bundle;
import android.view.View;
import android.widget.AdapterView;
import android.widget.ArrayAdapter;
import android.widget.Button;
import android.widget.ListView;
import android.widget.TextView;

import androidx.appcompat.app.AppCompatActivity;

import java.util.ArrayList;

public class ListarProjetoActivity extends AppCompatActivity {

    MeuSQLite gerenciadorBancoDeDados;
    SQLiteDatabase bancoDeDados;

    ArrayAdapter<String> arrayAdapter;

    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.listar_projetos);

        gerenciadorBancoDeDados = new MeuSQLite(this, "projetointegrador");

        ListView listView = findViewById(R.id.lista_projetos);

        ArrayList<String> listaProjeto = this.GetListaProjeto();

        this.arrayAdapter = new ArrayAdapter(this, android.R.layout
                .simple_list_item_1, listaProjeto);

        listView.setAdapter(arrayAdapter);

        listView.setOnItemClickListener(new AdapterView.OnItemClickListener() {
            @Override
            public void onItemClick(AdapterView<?> adapterView, View view, int i, long l) {
                String item = arrayAdapter.getItem(i);

                String id = item.split(" - ")[0];

                Intent intent = new Intent(ListarProjetoActivity.this, ListarRequisitoActivity.class);

                Bundle b = new Bundle();
                b.putInt("projetoId", Integer.parseInt(id));

                intent.putExtras(b);

                startActivity(intent);
            }
        });
    }

    private ArrayList<String> GetListaProjeto(){
        ArrayList<String> listaProjeto = new ArrayList<>();

        bancoDeDados = gerenciadorBancoDeDados.getReadableDatabase();

        String[] campos = {"id", "descricao", "dt_inicio", "dt_fim"};
        Cursor lista = bancoDeDados.query("projeto", campos, null, null, null, null, null );

        lista.moveToFirst();

        while(lista.isAfterLast() == false){

            int id = Integer.parseInt(lista.getString(lista.getColumnIndexOrThrow("id")));
            String descricao = lista.getString(lista.getColumnIndexOrThrow("descricao"));
            String dt_inicio = lista.getString(lista.getColumnIndexOrThrow("dt_inicio"));
            String dt_fim = lista.getString(lista.getColumnIndexOrThrow("dt_fim"));

            String projeto = id + " - " + descricao + " - Início: " + dt_inicio + " - Fim: " + dt_fim;

            listaProjeto.add(projeto);

            lista.moveToNext();
        }

        bancoDeDados.close();

        return listaProjeto;
    }
}

package com.example.projeto_integrador_ix;

import android.app.Activity;
import android.content.ContentValues;
import android.content.Intent;
import android.database.Cursor;
import android.database.sqlite.SQLiteDatabase;
import android.os.Bundle;
import android.view.View;
import android.widget.ArrayAdapter;
import android.widget.Spinner;
import android.widget.TextView;
import android.widget.Toast;

import java.util.ArrayList;

import model.ProjetoViewModel;
import model.RequisitoViewModel;

public class CriarRequisitoActivity extends Activity {

    MeuSQLite gerenciadorBancoDeDados;
    SQLiteDatabase bancoDeDados;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.criar_requisito);

        gerenciadorBancoDeDados = new MeuSQLite(this, "projetointegrador");

        Spinner spinnerNivelDificuldade = findViewById(R.id.lista_nivel_dificuldade);
        Spinner spinnerNivelImportancia = findViewById(R.id.lista_nivel_importancia);

        ArrayAdapter<CharSequence> adapter = ArrayAdapter.createFromResource(this,
                R.array.niveis, android.R.layout.simple_spinner_item);

        adapter.setDropDownViewResource(android.R.layout.simple_spinner_dropdown_item);

        spinnerNivelDificuldade.setAdapter(adapter);
        spinnerNivelImportancia.setAdapter(adapter);

        //TODO
        ArrayList<String> lista = this.GetListaProjeto();

        Spinner spinnerProjeto = findViewById(R.id.lista_projeto);

        ArrayAdapter arrayAdapter = new ArrayAdapter(this, android.R.layout.simple_spinner_dropdown_item, lista);

        spinnerProjeto.setAdapter(arrayAdapter);

    }

    private ArrayList<String> GetListaProjeto(){

        ArrayList<String> listaProjeto = new ArrayList<>();

        bancoDeDados = gerenciadorBancoDeDados.getReadableDatabase();

        String[] campos = {"id", "descricao"};
        Cursor lista = bancoDeDados.query("projeto", campos, null, null, null, null, null );

        lista.moveToFirst();

        while(lista.isAfterLast() == false){

            int id = Integer.parseInt(lista.getString(lista.getColumnIndexOrThrow("id")));
            String descricao = lista.getString(lista.getColumnIndexOrThrow("descricao"));

            String projeto = id + " - " + descricao;

            listaProjeto.add(projeto);

            lista.moveToNext();
        }

        bancoDeDados.close();

        return listaProjeto;
    }

    public void Cancelar(View view){

        Intent i = new Intent(CriarRequisitoActivity.this, MainActivity.class);

        startActivity(i);
    }

    public void CriarRequisito(View view){

        boolean isValid = true;
        TextView nomeRequisitoElemento = findViewById(R.id.nome_requisito);
        String nomeRequisito = nomeRequisitoElemento.getText().toString();

        if(nomeRequisito.isEmpty()){
            Toast.makeText(this, "Nome do requisito é obrigatório", Toast.LENGTH_SHORT).show();
            isValid = false;
        }

        Spinner projeto = findViewById(R.id.lista_projeto);
        String nomeProjeto = projeto.getSelectedItem().toString();

        if(nomeProjeto.isEmpty()){
            Toast.makeText(this, "Projeto é obrigatório", Toast.LENGTH_SHORT).show();
            isValid = false;
        }

        Spinner nivelDificuldade = findViewById(R.id.lista_nivel_dificuldade);

        if(nivelDificuldade.getSelectedItem().toString().isEmpty()){
            Toast.makeText(this, "Nível de dificuldade é obrigatório", Toast.LENGTH_SHORT).show();
            isValid = false;
        }

        Spinner nivelImportancia = findViewById(R.id.lista_nivel_importancia);

        if(nivelImportancia.getSelectedItem().toString().isEmpty()){
            Toast.makeText(this, "Nível de importância é obrigatório", Toast.LENGTH_SHORT).show();
            isValid = false;
        }

        TextView tempoEstimativa = findViewById(R.id.tempo_estimado);

        if(tempoEstimativa.getText().toString().isEmpty()){
            Toast.makeText(this, "Tempo estimado é obrigatório", Toast.LENGTH_SHORT).show();
            isValid = false;
        }

        if(isValid)
        {
            int nivelDificuldadeValor = Integer.parseInt(nivelDificuldade.getSelectedItem().toString());
            int nivelImportanciaValor = Integer.parseInt(nivelImportancia.getSelectedItem().toString());
            double tempoEstimativaValor = Double.parseDouble(tempoEstimativa.getText().toString());

            int idProjeto = Integer.parseInt(nomeProjeto.split(" - ")[0].trim());
            String nomeProjetoValor = nomeProjeto.split(" - ")[1].trim();

            ProjetoViewModel projetoViewModel = new ProjetoViewModel(idProjeto, nomeProjetoValor);

            RequisitoViewModel requisitoViewModel = new RequisitoViewModel(
                    nivelImportanciaValor,
                    nivelDificuldadeValor,
                    tempoEstimativaValor,
                    projetoViewModel,
                    nomeRequisito
            );

            boolean isRequisitoCriadoComSucesso = this.InsertRequisito(requisitoViewModel);

            if(isRequisitoCriadoComSucesso){
                Toast.makeText(this, "Requisito criado com sucesso", Toast.LENGTH_SHORT).show();
            }else{
                Toast.makeText(this, "Erro ao criar requisito", Toast.LENGTH_SHORT).show();
            }

            this.Cancelar(view);
        }
    }

    private boolean InsertRequisito(RequisitoViewModel viewModel){

        ContentValues valores = new ContentValues();
        valores.put("descricao",  viewModel.getDescricao());
        valores.put("nivel_importancia", viewModel.getNivelImportancia());
        valores.put("nivel_dificuldade", viewModel.getNivelDificuldade());
        valores.put("tmp_estimado", viewModel.getTempoEstimado());
        valores.put("projeto_id", viewModel.getProjetoViewModel().getId());

        bancoDeDados = gerenciadorBancoDeDados.getWritableDatabase();

        long resultado;

        resultado = bancoDeDados.insert("requisito", null, valores);

        bancoDeDados.close();

        if (resultado ==-1)
            return false;
        else {
            return true;
        }
    }
}

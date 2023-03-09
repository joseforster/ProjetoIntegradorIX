package com.example.projeto_integrador_ix;

import android.app.Activity;
import android.content.Intent;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.CalendarView;
import android.widget.TextView;
import android.widget.Toast;

import java.util.Calendar;
import java.util.Date;
import java.util.GregorianCalendar;

import dao.ProjetoDAO;
import model.ProjetoViewModel;

public class CriarProjetoActivity extends Activity {

    private boolean isDataInicioPreenchido = false;
    private ProjetoViewModel projetoViewModel;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        this.projetoViewModel = new ProjetoViewModel();
        setContentView(R.layout.criar_projeto);

        CalendarView calendarView = findViewById(R.id.calendario);
        calendarView.setOnDateChangeListener(new CalendarView.OnDateChangeListener() {
            @Override
            public void onSelectedDayChange(CalendarView view, int year, int month, int dayOfMonth) {
                Calendar calendar = Calendar.getInstance();
                calendar.set(year, month, dayOfMonth);

                view.setDate(calendar.getTimeInMillis());
            }
        });
    }

    public void SalvarData(View view){

        CalendarView calendario = findViewById(R.id.calendario);

        Calendar dt = new GregorianCalendar();

        dt.setTimeInMillis(calendario.getDate());

        TextView textoCalendario = (TextView) findViewById(R.id.texto_calendario);

        if(this.isDataInicioPreenchido){

            if(dt.before(projetoViewModel.getDataInicio())){

                Toast.makeText(this, "Data fim deve ser maior que data início", Toast.LENGTH_SHORT).show();
            }else{
                this.projetoViewModel.setDataFim(dt);

                Button btnSalvarData = findViewById(R.id.btnSalvarData);

                btnSalvarData.setVisibility(view.INVISIBLE);

                Button btnCriarProjeto = findViewById(R.id.btnCriarProjeto);

                btnCriarProjeto.setVisibility(view.VISIBLE);

                textoCalendario.setText("Data salvas com sucesso");
            }

        }else{
            this.projetoViewModel.setDataInicio(dt);
            this.isDataInicioPreenchido = true;

            textoCalendario.setText("Agora escolha uma data fim");

            calendario.setDate(new Date().getTime());
        }
    }

    public void CriarProjeto(View view){

        TextView textView = findViewById(R.id.nome_projeto);

        if(textView.getText().toString().isEmpty()){
            Toast.makeText(this, "Descrição do projeto é obrigatório", Toast.LENGTH_SHORT).show();
        }else{

            this.projetoViewModel.setDescricao(textView.getText().toString());

            boolean isProjetoCriadoComSucesso = new ProjetoDAO().Create(this.projetoViewModel);

            if(isProjetoCriadoComSucesso){
                Toast.makeText(this, "Projeto criado com sucesso", Toast.LENGTH_SHORT).show();
            }else{
                Toast.makeText(this, "Erro ao criar projeto", Toast.LENGTH_SHORT).show();
            }

            this.Cancelar(view);
        }
    }

    public void Cancelar(View view){

        Intent i = new Intent(CriarProjetoActivity.this, MainActivity.class);

        startActivity(i);
    }
}

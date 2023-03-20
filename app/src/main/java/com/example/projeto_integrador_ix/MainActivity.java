package com.example.projeto_integrador_ix;

import androidx.appcompat.app.AppCompatActivity;

import android.content.Intent;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;

public class MainActivity extends AppCompatActivity {

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);

        Button btnIrParaCriarProjeto = (Button)findViewById(R.id.btnIrParaCriarProjeto);
        btnIrParaCriarProjeto.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Intent intent = new Intent(MainActivity.this,CriarProjetoActivity.class);
                startActivity(intent);
            }
        });

        Button btnIrParaCriarRequisito = (Button) findViewById(R.id.btnIrParaCriarRequisito);

        btnIrParaCriarRequisito.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                Intent intent = new Intent(MainActivity.this,CriarRequisitoActivity.class);
                startActivity(intent);
            }
        });

        Button btnListarProjeto = findViewById(R.id.btnListarProjetos);

        btnListarProjeto.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                Intent intent = new Intent(MainActivity.this,ListarProjetoActivity.class);
                startActivity(intent);
            }
        });
    }
}
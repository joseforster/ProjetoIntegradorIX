package com.example.projeto_integrador_ix;

import androidx.annotation.NonNull;
import androidx.appcompat.app.AppCompatActivity;

import android.content.Intent;
import android.os.Bundle;
import android.view.Menu;
import android.view.MenuInflater;
import android.view.MenuItem;
import android.view.View;
import android.widget.Button;
import android.widget.Toast;

public class MainActivity extends AppCompatActivity {

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);
    }

    public boolean onCreateOptionsMenu(Menu menu) {
        MenuInflater exibirMenu = getMenuInflater();
        exibirMenu.inflate(R.menu.menu, menu);
        return true;
    }

    public boolean onOptionsItemSelected(@NonNull MenuItem item) {
        switch (item.getItemId()) {
            case R.id.submenu_criar_projeto:
                startActivity(new Intent(MainActivity.this,CriarProjetoActivity.class));
                return true;
            case R.id.submenu_listar_projetos:
                startActivity(new Intent(MainActivity.this,ListarProjetoActivity.class));
                return true;
            case R.id.submenu_criar_requisito:
                startActivity(new Intent(MainActivity.this,CriarRequisitoActivity.class));
                return true;
            default:
                return super.onOptionsItemSelected(item);
        }
    }
}
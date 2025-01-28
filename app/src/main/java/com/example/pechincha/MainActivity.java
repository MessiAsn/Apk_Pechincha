package com.example.pechincha;

import android.content.Intent;
import android.os.Bundle;
import android.view.View;

import androidx.activity.EdgeToEdge;
import androidx.appcompat.app.AppCompatActivity;

public class MainActivity extends AppCompatActivity {

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        EdgeToEdge.enable(this);
        setContentView(R.layout.activity_main);
    }

    public void mudarCadastrarOferta(View v) {
        Intent i = new Intent(this, CadastrarOferta.class);
        startActivity(i);
    }

    public void mudarBuscarOferta(View v) {
        Intent i = new Intent(this, BuscarOferta.class);
        startActivity(i);
    }

}

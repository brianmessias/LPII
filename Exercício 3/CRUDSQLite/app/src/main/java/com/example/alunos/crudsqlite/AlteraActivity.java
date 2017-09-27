package com.example.alunos.crudsqlite;

import android.content.Intent;
import android.database.Cursor;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;

public class AlteraActivity extends AppCompatActivity {
    EditText livro;
    EditText autor;
    EditText editora;
    Button alterar;
    Button deletar;
    Cursor cursor;
    BancoController crud;
    String codigo;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_altera);

        codigo = this.getIntent().getStringExtra("codigo");

        crud = new BancoController (getBaseContext());

        livro = (EditText) findViewById(R.id.txtTitulo);
        autor = (EditText) findViewById(R.id.txtAutor);
        editora = (EditText) findViewById(R.id.txtEditora);

        alterar = (Button) findViewById(R.id.btnAlterar);

        cursor = crud.carregaDadoById(Integer.parseInt(codigo));
        livro.setText(cursor.getString(cursor.getColumnIndexOrThrow(CriaBanco.TITULO)));
        autor.setText(cursor.getString(cursor.getColumnIndexOrThrow(CriaBanco.AUTOR)));
        editora.setText(cursor.getString(cursor.getColumnIndexOrThrow(CriaBanco.EDITORA)));

        alterar.setOnClickListener(new View.OnClickListener(){
            @Override
            public void onClick(View v){
                crud.alteraRegistro(Integer.parseInt(codigo),
                        livro.getText().toString(),
                        autor.getText().toString(),
                        editora.getText().toString());
                Intent intent = new Intent (AlteraActivity.this, ConsultaActivity.class);
                startActivity(intent);
                finish();
            }
        });

        deletar = (Button) findViewById(R.id.btnApagar);
        deletar.setOnClickListener(new View.OnClickListener(){
            @Override
            public void onClick(View v){
                crud.apagaRegistro(Integer.parseInt(codigo));
                Intent intent = new Intent (AlteraActivity.this, ConsultaActivity.class);
                startActivity (intent);
                finish();
            }
        });

    }
}

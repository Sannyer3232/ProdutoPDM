package br.edu.ifam.tads.produtos;

import android.content.Context;
import android.content.Intent;
import android.os.Bundle;
import android.widget.EditText;

import androidx.activity.EdgeToEdge;
import androidx.appcompat.app.AppCompatActivity;
import androidx.core.graphics.Insets;
import androidx.core.view.ViewCompat;
import androidx.core.view.WindowInsetsCompat;

import br.edu.ifam.tads.produtos.entity.Fornecedor;
import br.edu.ifam.tads.produtos.repository.FornecedorDAO;

public class FornecedorDetalhesActivity extends AppCompatActivity {

    private EditText etFornecedorCodigo;
    private EditText etFornecedorNome;
    private EditText etFornecedorTelefone;
    private EditText etFornecedorEmail;
    private EditText etFornecedorEndereco;

    private FornecedorDAO fornecedorDAO;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        EdgeToEdge.enable(this);
        setContentView(R.layout.activity_detalhes_fornecedor);
        ViewCompat.setOnApplyWindowInsetsListener(findViewById(R.id.main), (v, insets) -> {
            Insets systemBars = insets.getInsets(WindowInsetsCompat.Type.systemBars());
            v.setPadding(systemBars.left, systemBars.top, systemBars.right, systemBars.bottom);
            return insets;
        });
        fornecedorDAO = new FornecedorDAO(this);

        etFornecedorCodigo = findViewById(R.id.etFornecedorCodigo);
        etFornecedorNome = findViewById(R.id.etFornecedorNome);
        etFornecedorTelefone = findViewById(R.id.etFornecedorPhone);
        etFornecedorEmail = findViewById(R.id.etFornecedorEmail);
        etFornecedorEndereco = findViewById(R.id.etFornecedorAddress);

        Intent intent = getIntent();


        etFornecedorCodigo.setText( Integer.toString(intent.getIntExtra("codigo",0)));
        etFornecedorNome.setText( intent.getStringExtra("nome"));
        etFornecedorTelefone.setText( intent.getStringExtra("telefone"));
        etFornecedorEmail.setText(  intent.getStringExtra("email"));
        etFornecedorEndereco.setText(  intent.getStringExtra("endreco"));


    }
}
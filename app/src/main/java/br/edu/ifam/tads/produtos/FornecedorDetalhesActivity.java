package br.edu.ifam.tads.produtos;

import android.content.Intent;
import android.os.Bundle;
import android.view.View;
import android.widget.EditText;
import android.widget.ImageButton;
import android.widget.Toast;

import androidx.activity.EdgeToEdge;
import androidx.appcompat.app.AppCompatActivity;
import androidx.core.graphics.Insets;
import androidx.core.view.ViewCompat;
import androidx.core.view.WindowInsetsCompat;

import java.util.Locale;

import br.edu.ifam.tads.produtos.entity.Fornecedor;
import br.edu.ifam.tads.produtos.repository.FornecedorDAO;

public class FornecedorDetalhesActivity extends AppCompatActivity {

    private EditText etFornecedorCodigo;
    private EditText etFornecedorNome;
    private EditText etFornecedorTelefone;
    private EditText etFornecedorEmail;
    private EditText etFornecedorEndereco;
    private ImageButton ibSaveFornecedor;
    private ImageButton ibDeleteFornecedor;
    private ImageButton ibClearFornecedor;
    private Long id;


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

        ibSaveFornecedor = findViewById(R.id.ibSaveFornecedor);
        ibDeleteFornecedor = findViewById(R.id.ibDeleteFornecedor);
        ibClearFornecedor = findViewById(R.id.ibClearFornecedor);

        //insert
        ibSaveFornecedor.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {

                fornecedorDAO.insert(getFornecedor());
                Toast.makeText(getApplicationContext(), "Fornecedor Inserido", Toast.LENGTH_SHORT).show();
                finish();

            }
        });

        ibDeleteFornecedor.setVisibility(View.INVISIBLE);
        //Update and Delete
        Intent intent = getIntent();

        if(intent.hasExtra("id")){
            ibDeleteFornecedor.setVisibility(View.VISIBLE);
            id = intent.getLongExtra("id",0);
            setFornecedor(fornecedorDAO.getFornecedor(id));

            ibSaveFornecedor.setOnClickListener(new View.OnClickListener() {
                @Override
                public void onClick(View view) {
                    fornecedorDAO.update(id, getFornecedor());
                    Toast.makeText(getApplicationContext(), "Fornecedor Atualizado", Toast.LENGTH_SHORT).show();
                    finish();
                }
            });
        }

    }

    private Fornecedor getFornecedor(){
        Fornecedor fornecedor = new Fornecedor();
        fornecedor.setCodigo(Integer.parseInt(etFornecedorCodigo.getText().toString()));
        fornecedor.setNome(etFornecedorNome.getText().toString());
        fornecedor.setTelefone(etFornecedorTelefone.getText().toString());
        fornecedor.setEmail(etFornecedorEmail.getText().toString());
        fornecedor.setEndereco(etFornecedorEndereco.getText().toString());
        return fornecedor;
    }

    private void setFornecedor(Fornecedor fornecedor){
        etFornecedorCodigo.setText(String.format(Locale.getDefault() ,"%d", fornecedor.getCodigo()));
        etFornecedorNome.setText(fornecedor.getNome());
        etFornecedorTelefone.setText(fornecedor.getTelefone());
        etFornecedorEmail.setText(fornecedor.getEmail());
        etFornecedorEndereco.setText(fornecedor.getEndereco());

    }

    public void ibDeleteFornecedorOnClick(View view){
        fornecedorDAO.delete(id);
        Toast.makeText(this, "Fornecedor Excluído", Toast.LENGTH_SHORT).show();
        finish();
    }
    public void ibClearFornecedorOnClick(View view){
        setFornecedor(new Fornecedor());
        etFornecedorCodigo.setText("");
    }
}
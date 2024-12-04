package br.edu.ifam.tads.produtos.recycler;
import android.content.Context;
import android.content.Intent;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.TextView;
import androidx.annotation.NonNull;
import androidx.recyclerview.widget.RecyclerView;
import java.util.List;

import br.edu.ifam.tads.produtos.FornecedorDetalhesActivity;
import br.edu.ifam.tads.produtos.R;
import br.edu.ifam.tads.produtos.entity.Fornecedor;

//Ligar a RecyclerView a uma fonte de dados, geralmente um vetor
public class FornecedorAdapter extends RecyclerView.Adapter<FornecedorAdapter.FornecedorViewHolder> {
    private List<Fornecedor> fornecedores;
    private Context context;

    public FornecedorAdapter(List<Fornecedor> fornecedores, Context context) {
        this.fornecedores = fornecedores;
        this.context = context;
    }

    @NonNull
    @Override
    //Cria a ligação com layout que ira conter o Linear Layout com as informações do fornecedor
    public FornecedorViewHolder onCreateViewHolder(@NonNull ViewGroup parent, int viewType) {
        View view = LayoutInflater.from(parent.getContext()).inflate(R.layout.item_fornecedor, parent, false);
        return new FornecedorViewHolder(view);
    }

    @Override
    public void onBindViewHolder(@NonNull FornecedorViewHolder holder, int position) {
        Fornecedor fornecedor = fornecedores.get(position);
        holder.tvCodigo.setText(String.valueOf(fornecedor.getCodigo()));
        holder.tvNome.setText(fornecedor.getNome());

        holder.itemView.setOnClickListener(v -> {
            Intent intent = new Intent(context, FornecedorDetalhesActivity.class);
            intent.putExtra("codigo", fornecedor.getCodigo());
            intent.putExtra("nome", fornecedor.getNome());
            intent.putExtra("email", fornecedor.getEmail());
            intent.putExtra("telefone", fornecedor.getTelefone());
            intent.putExtra("endereco", fornecedor.getEndereco());
            context.startActivity(intent);
        });
    }

    @Override
    public int getItemCount() {
        return fornecedores.size();
    }

    public static class FornecedorViewHolder extends RecyclerView.ViewHolder {
        TextView tvCodigo;
        TextView tvNome;

       public FornecedorViewHolder(@NonNull View itemView) {
            super(itemView);
            tvCodigo = itemView.findViewById(R.id.tvCodigo);
            tvNome = itemView.findViewById(R.id.tvNome);
        }
    }
}

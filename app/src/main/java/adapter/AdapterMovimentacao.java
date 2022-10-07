package adapter;

import android.content.Context;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.TextView;

import androidx.annotation.NonNull;
import androidx.recyclerview.widget.RecyclerView;

import com.example.financasapp.R;

import java.util.List;

import model.Movimentacao;

public class AdapterMovimentacao extends
        RecyclerView.Adapter<AdapterMovimentacao.MyViewHolder> {

    private List<Movimentacao> movimentacoes;
    private Context context;

    public AdapterMovimentacao(List<Movimentacao> movimentacoes, Context context){
        this.movimentacoes = movimentacoes;
        this.context = context;
    }

    @Override
    public AdapterMovimentacao.MyViewHolder onCreateViewHolder(@NonNull ViewGroup parent, int viewType) {
        View itemLista = LayoutInflater.from(parent.getContext()).inflate(R.layout.adapter_movimentacao, parent, false);

        return new MyViewHolder(itemLista);
    }

    @Override
    public void onBindViewHolder(@NonNull AdapterMovimentacao.MyViewHolder holder, int position) {
        Movimentacao movimentacao = movimentacoes.get(position);

        holder.titulo.setText(movimentacao.getDescricao());
        holder.valor.setText(String.valueOf(movimentacao.getValor()));
        holder.categoria.setText(movimentacao.getCategoria());

        if(movimentacao.getTipo() == "d" || movimentacao.getTipo().equals("d")){
            holder.valor.setTextColor(context.getResources().getColor(R.color.colorAccent));
            holder.valor.setText("- " + movimentacao.getValor());
        }
    }

    @Override
    public int getItemCount() {
        return movimentacoes.size();
    }

    public class MyViewHolder extends  RecyclerView.ViewHolder{

         TextView titulo, valor, categoria;


        public MyViewHolder(@NonNull View itemView) {
            super(itemView);
            titulo = itemView.findViewById(R.id.textAdapterTitulo);
            valor = itemView.findViewById(R.id.textAdapterValor);
            categoria = itemView.findViewById(R.id.textAdapterIstituicao);

        }
    }

}
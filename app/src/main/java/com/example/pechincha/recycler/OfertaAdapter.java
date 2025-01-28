package com.example.pechincha.recycler;

import android.content.Context;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.TextView;

import androidx.annotation.NonNull;
import androidx.recyclerview.widget.RecyclerView;

import com.example.pechincha.R;
import com.example.pechincha.model.Oferta;

import java.util.List;

public class OfertaAdapter extends RecyclerView.Adapter<OfertaAdapter.OfertaViewHolder> {

    private List<Oferta> ofertas;
    private Context context;

    public OfertaAdapter(Context context, List<Oferta> ofertas) {
        this.context = context;
        this.ofertas = ofertas;
    }

    @NonNull
    @Override
    public OfertaViewHolder onCreateViewHolder(@NonNull ViewGroup parent, int viewType) {
        View view = LayoutInflater.from(context).inflate(R.layout.item_oferta, parent, false);
        return new OfertaViewHolder(view);
    }

    @Override
    public void onBindViewHolder(@NonNull OfertaViewHolder holder, int position) {
        Oferta oferta = ofertas.get(position);
        holder.tvTitulo.setText(oferta.getTitulo());
        holder.tvPreco.setText(String.valueOf(oferta.getPreco()));
        holder.tvCupom.setText(oferta.getCupom());
    }

    @Override
    public int getItemCount() {
        return ofertas.size();
    }

    public static class OfertaViewHolder extends RecyclerView.ViewHolder {

        TextView tvTitulo;
        TextView tvPreco;
        TextView tvCupom;

        public OfertaViewHolder(@NonNull View itemView) {
            super(itemView);
            tvTitulo = itemView.findViewById(R.id.tvTitulo);
            tvPreco = itemView.findViewById(R.id.tvPreco);
            tvCupom = itemView.findViewById(R.id.tvCupom);
        }
    }
}

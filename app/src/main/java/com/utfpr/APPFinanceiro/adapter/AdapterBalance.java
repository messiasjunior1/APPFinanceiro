package com.utfpr.APPFinanceiro.adapter;

import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.TextView;

import androidx.annotation.NonNull;
import androidx.recyclerview.widget.RecyclerView;

import java.util.List;

import com.utfpr.APPFinanceiro.R;
import com.utfpr.APPFinanceiro.model.entity.Balance;

public class AdapterBalance extends RecyclerView.Adapter<AdapterBalance.ViewHolder> {
    private List<Balance> lista;

    public AdapterBalance(List<Balance> lista) {
        this.lista = lista;
    }

    public static class ViewHolder extends RecyclerView.ViewHolder {
        private TextView textValue;
        private TextView textDesc;
        private TextView textDate;
        private TextView textType;
        public ViewHolder(View view) {
            super(view);
            // Define click listener for the ViewHolder's View
            textDesc = view.findViewById(R.id.textViewDesc);
            textDate = view.findViewById(R.id.textViewDate);
            textValue = view.findViewById(R.id.textViewValue);
            textType = view.findViewById(R.id.textViewType);
        }
    }

    @NonNull
    @Override
    public ViewHolder onCreateViewHolder(@NonNull ViewGroup parent, int viewType) {
        // Create a new view, which defines the UI of the list item
        View view = LayoutInflater.from(parent.getContext())
                .inflate(R.layout.balance_layout, parent, false);

        return new ViewHolder(view);
    }

    @Override
    public void onBindViewHolder(@NonNull ViewHolder holder, int position) {
        Balance c = lista.get(position);

        holder.textValue.setText(c.getValue());
        holder.textDesc.setText(c.getDesc());
        holder.textDate.setText(c.getDate());

        if (c.getTypeBalance().equals("1")) {
            holder.textType.setText("Despesas");
        } else {
            holder.textType.setText("Receita");
        }
    }

    @Override
    public int getItemCount() {
        return lista.size();
    }


}

package com.perso.red.pokeassistant.ivdetails.view;

import android.support.v7.widget.RecyclerView;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.TextView;

import com.perso.red.pokeassistant.R;
import com.perso.red.pokeassistant.models.IVResult;

import java.util.ArrayList;
import java.util.List;
import java.util.Locale;

/**
 * Created by pierr on 23/08/2016.
 */

public class IVDetailsRVAdapter extends RecyclerView.Adapter<IVDetailsRVAdapter.DataObjectHolder> {

    private List<IVResult>  ivResults;

    public IVDetailsRVAdapter() {
        ivResults = new ArrayList<>();
    }

    public class DataObjectHolder extends RecyclerView.ViewHolder {
        TextView    attack;
        TextView    defense;
        TextView    stamina;
        TextView    total;

        public DataObjectHolder(View itemView) {
            super(itemView);
            attack = (TextView) itemView.findViewById(R.id.text_view_iv_attack);
            defense = (TextView) itemView.findViewById(R.id.text_view_iv_defense);
            stamina = (TextView) itemView.findViewById(R.id.text_view_iv_stamina);
            total = (TextView) itemView.findViewById(R.id.text_view_iv_total);
        }
    }

    @Override
    public DataObjectHolder onCreateViewHolder(ViewGroup parent, int viewType) {
        View                view = LayoutInflater.from(parent.getContext()).inflate(R.layout.view_iv_details_rv_row, parent, false);
        DataObjectHolder    holder = new DataObjectHolder(view);

        return (holder);
    }

    @Override
    public void onBindViewHolder(DataObjectHolder holder, int position) {
        IVResult    ivResult = ivResults.get(position);
        String      ivTotal = String.format(Locale.getDefault(), "%.1f", ivResult.getTotalIV()) + "%";

        holder.attack.setText(String.valueOf(ivResult.getAttackIV()));
        holder.defense.setText(String.valueOf(ivResult.getDefenseIV()));
        holder.stamina.setText(String.valueOf(ivResult.getStaminaIV()));
        holder.total.setText(ivTotal);
    }

    @Override
    public int getItemCount() {
        return ivResults.size();
    }

    public void update(List<IVResult> newIVResult) {
        ivResults.clear();
        ivResults.addAll(newIVResult);
        notifyDataSetChanged();
    }
}

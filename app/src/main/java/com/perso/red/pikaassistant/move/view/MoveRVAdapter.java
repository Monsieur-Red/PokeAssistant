package com.perso.red.pikaassistant.move.view;

import android.support.v7.widget.RecyclerView;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.TextView;

import com.perso.red.pikaassistant.R;
import com.perso.red.pikaassistant.models.Move;
import com.perso.red.pikaassistant.utils.Constants;

import java.util.ArrayList;
import java.util.List;
import java.util.Locale;

/**
 * Created by pierr on 24/08/2016.
 */

public class MoveRVAdapter extends RecyclerView.Adapter<MoveRVAdapter.DataObjectHolder> {

    private List<Move>  moves;

    public MoveRVAdapter() {
        moves = new ArrayList<>();
    }

    public class DataObjectHolder extends RecyclerView.ViewHolder {
        TextView    name;
        TextView    power;
        TextView    castTime;
        TextView    dps;
        TextView    type;

        public DataObjectHolder(View itemView) {
            super(itemView);
            name = (TextView) itemView.findViewById(R.id.text_view_move_name);
            power = (TextView) itemView.findViewById(R.id.text_view_move_power);
            castTime = (TextView) itemView.findViewById(R.id.text_view_move_cast_time);
            dps = (TextView) itemView.findViewById(R.id.text_view_move_dps);
            type = (TextView) itemView.findViewById(R.id.text_view_move_type);
        }
    }

    @Override
    public DataObjectHolder onCreateViewHolder(ViewGroup parent, int viewType) {
        View                view = LayoutInflater.from(parent.getContext()).inflate(R.layout.view_move_rv_row, parent, false);
        DataObjectHolder    holder = new DataObjectHolder(view);

        return (holder);
    }

    @Override
    public void onBindViewHolder(DataObjectHolder holder, int position) {
        Move    move = moves.get(position);
        double  castTimeSec = move.getDurationMs() / 1000;
        String  castTimeStr = String.format(Locale.getDefault(), "%.1fs", castTimeSec).replace(",0", "");
        Double  dps = move.getDps();
        String  typeStr = move.getType();

        holder.name.setText(move.getName());
        holder.power.setText(String.valueOf(move.getPower()));
        holder.castTime.setText(castTimeStr);
        holder.type.setText(typeStr);

        if (dps == null)
            holder.dps.setText("0");
        else
            holder.dps.setText(String.format(Locale.getDefault(), "%.1f", dps));

        switch (typeStr) {
            case Constants.POKEMON_TYPE_NORMAL:
                holder.name.getRootView().setBackgroundResource(R.color.pokemon_type_normal);
                break;
            case Constants.POKEMON_TYPE_FIRE:
                holder.name.getRootView().setBackgroundResource(R.color.pokemon_type_fire);
                break;
            case Constants.POKEMON_TYPE_WATER:
                holder.name.getRootView().setBackgroundResource(R.color.pokemon_type_water);
                break;
            case Constants.POKEMON_TYPE_ELECTRIC:
                holder.name.getRootView().setBackgroundResource(R.color.pokemon_type_electric);
                break;
            case Constants.POKEMON_TYPE_GRASS:
                holder.name.getRootView().setBackgroundResource(R.color.pokemon_type_grass);
                break;
            case Constants.POKEMON_TYPE_ICE:
                holder.name.getRootView().setBackgroundResource(R.color.pokemon_type_ice);
                break;
            case Constants.POKEMON_TYPE_FIGHTING:
                holder.name.getRootView().setBackgroundResource(R.color.pokemon_type_fighting);
                break;
            case Constants.POKEMON_TYPE_POISON:
                holder.name.getRootView().setBackgroundResource(R.color.pokemon_type_poison);
                break;
            case Constants.POKEMON_TYPE_GROUND:
                holder.name.getRootView().setBackgroundResource(R.color.pokemon_type_ground);
                break;
            case Constants.POKEMON_TYPE_FLYING:
                holder.name.getRootView().setBackgroundResource(R.color.pokemon_type_flying);
                break;
            case Constants.POKEMON_TYPE_PSYCHIC:
                holder.name.getRootView().setBackgroundResource(R.color.pokemon_type_psychic);
                break;
            case Constants.POKEMON_TYPE_BUG:
                holder.name.getRootView().setBackgroundResource(R.color.pokemon_type_bug);
                break;
            case Constants.POKEMON_TYPE_ROCK:
                holder.name.getRootView().setBackgroundResource(R.color.pokemon_type_rock);
                break;
            case Constants.POKEMON_TYPE_GHOST:
                holder.name.getRootView().setBackgroundResource(R.color.pokemon_type_ghost);
                break;
            case Constants.POKEMON_TYPE_DRAGON:
                holder.name.getRootView().setBackgroundResource(R.color.pokemon_type_dragon);
                break;
            case Constants.POKEMON_TYPE_DARK:
                holder.name.getRootView().setBackgroundResource(R.color.pokemon_type_dark);
                break;
            case Constants.POKEMON_TYPE_STEEL:
                holder.name.getRootView().setBackgroundResource(R.color.pokemon_type_steel);
                break;
            case Constants.POKEMON_TYPE_FAIRY:
                holder.name.getRootView().setBackgroundResource(R.color.pokemon_type_fairy);
                break;
        }
    }

    @Override
    public int getItemCount() {
        return moves.size();
    }

    public void update(List<Move> newMoves) {
        moves.clear();
        moves.addAll(newMoves);
        notifyDataSetChanged();
    }

}

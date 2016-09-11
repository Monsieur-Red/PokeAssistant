package com.perso.red.pokeassistant.evocalculator;

import android.support.v7.widget.RecyclerView;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ImageButton;
import android.widget.TextView;

import com.perso.red.pokeassistant.R;
import com.perso.red.pokeassistant.models.EvoCalculatorPokemon;

import java.util.ArrayList;
import java.util.List;

import butterknife.BindView;
import butterknife.ButterKnife;
import butterknife.OnClick;

/**
 * Created by pierr on 11/09/2016.
 */

public class EvoCalculatorRVAdapter extends RecyclerView.Adapter<EvoCalculatorRVAdapter.DataObjectHolder> {

    private List<EvoCalculatorPokemon>  pokemons;
    private EvoCalculatorPresenter      presenter;

    public EvoCalculatorRVAdapter(EvoCalculatorPresenter presenter) {
        this.presenter = presenter;
        pokemons = new ArrayList<>();
    }

    public class DataObjectHolder extends RecyclerView.ViewHolder {
        @BindView(R.id.pokemon)     TextView pokemon;
        @BindView(R.id.cp)          TextView cp;
        @BindView(R.id.max_cp)      TextView maxCp;
        @BindView(R.id.btn_delete)  ImageButton delete;

        public DataObjectHolder(View itemView) {
            super(itemView);
            ButterKnife.bind(this, itemView);
        }

        @OnClick(R.id.btn_delete)
        public void OnClickDelete() {
            remove(getAdapterPosition());
        }
    }

    @Override
    public DataObjectHolder onCreateViewHolder(ViewGroup parent, int viewType) {
        View                view = LayoutInflater.from(parent.getContext()).inflate(R.layout.view_evolution_calculator_rv_row, parent, false);
        DataObjectHolder    holder = new DataObjectHolder(view);

        return (holder);
    }

    @Override
    public void onBindViewHolder(DataObjectHolder holder, int position) {
        EvoCalculatorPokemon pokemon = pokemons.get(position);

        holder.pokemon.setText(pokemon.getName());
        holder.cp.setText(pokemon.getCp());
        holder.maxCp.setText(pokemon.getMaxCp());
    }

    @Override
    public int getItemCount() {
        return pokemons.size();
    }

    public void add(EvoCalculatorPokemon pokemon) {
        pokemons.add(0, pokemon);
        notifyItemInserted(0);
    }

    public void remove(int position) {
        if (position >= 0) {
            pokemons.remove(position);
            notifyItemRemoved(position);
        }

        if (pokemons.size() == 0)
            presenter.hideResult();
    }

    public void addAll(List<EvoCalculatorPokemon> newPokemons) {
        notifyItemRangeInserted(0, newPokemons.size());
        pokemons.addAll(0, newPokemons);
    }

    public void removeAll() {
        notifyItemRangeRemoved(0, pokemons.size());
        pokemons.clear();
    }
}

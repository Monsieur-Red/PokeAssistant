package com.perso.red.pokeassistant.xpcalculator;

import android.support.v7.widget.RecyclerView;
import android.text.Editable;
import android.text.TextUtils;
import android.text.TextWatcher;
import android.view.KeyEvent;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.EditText;
import android.widget.ImageButton;
import android.widget.TextView;

import com.perso.red.pokeassistant.R;
import com.perso.red.pokeassistant.models.XPCalculatorPokemon;

import java.util.ArrayList;
import java.util.List;

/**
 * Created by pierr on 10/09/2016.
 */

public class XPCalculatorRVAdpater extends RecyclerView.Adapter<XPCalculatorRVAdpater.DataObjectHolder> {

    private List<XPCalculatorPokemon>   pokemons;
    private XPCalculatorPresenter       presenter;

    public XPCalculatorRVAdpater(XPCalculatorPresenter presenter) {
        this.presenter = presenter;
        pokemons = new ArrayList<>();
    }

    public class DataObjectHolder extends RecyclerView.ViewHolder implements View.OnClickListener {
        TextView    name;
        EditText    amount;
        EditText    candy;
        ImageButton delete;

        public DataObjectHolder(View itemView) {
            super(itemView);
            name = (TextView) itemView.findViewById(R.id.name);
            amount = (EditText) itemView.findViewById(R.id.amount);
            candy = (EditText) itemView.findViewById(R.id.candy);
            delete = (ImageButton) itemView.findViewById(R.id.btn_delete);

            amount.addTextChangedListener(new TextWatcher() {
                @Override
                public void beforeTextChanged(CharSequence charSequence, int i, int i1, int i2) {

                }

                @Override
                public void onTextChanged(CharSequence charSequence, int i, int i1, int i2) {
                    if (!TextUtils.isEmpty(charSequence)) {
                        XPCalculatorPokemon pokemon = pokemons.get(getAdapterPosition());
                        int number = Integer.valueOf(charSequence.toString());

                        if (number > 100000) {
                            amount.setText(new StringBuilder("100000"));
                            pokemon.setAmount(100000);
                        }
                        else
                            pokemon.setAmount(number);
                    }

                    if (!TextUtils.isEmpty(charSequence) && !TextUtils.isEmpty(candy.getText()))
                        presenter.update(pokemons);
                    else
                        presenter.hideResult();
                }

                @Override
                public void afterTextChanged(Editable editable) {

                }
            });

            candy.addTextChangedListener(new TextWatcher() {
                @Override
                public void beforeTextChanged(CharSequence charSequence, int i, int i1, int i2) {

                }

                @Override
                public void onTextChanged(CharSequence charSequence, int i, int i1, int i2) {
                    if (!TextUtils.isEmpty(charSequence)) {
                        XPCalculatorPokemon pokemon = pokemons.get(getAdapterPosition());
                        int number = Integer.valueOf(charSequence.toString());

                        if (number > 100000) {
                            candy.setText(new StringBuilder("100000"));
                            pokemon.setCandy(100000);
                        }
                        else
                            pokemon.setCandy(number);
                    }

                    if (!TextUtils.isEmpty(charSequence) && !TextUtils.isEmpty(amount.getText()))
                        presenter.update(pokemons);
                    else
                        presenter.hideResult();
                }

                @Override
                public void afterTextChanged(Editable editable) {

                }
            });

            delete.setOnClickListener(this);
        }

        @Override
        public void onClick(View view) {
            if (view.getId() == R.id.btn_delete)
                remove(getAdapterPosition());
        }
    }

    @Override
    public DataObjectHolder onCreateViewHolder(ViewGroup parent, int viewType) {
        View                view = LayoutInflater.from(parent.getContext()).inflate(R.layout.view_experience_calculator_rv_row, parent, false);
        DataObjectHolder    holder = new DataObjectHolder(view);

        return (holder);
    }

    @Override
    public void onBindViewHolder(final DataObjectHolder holder, final int position) {
        XPCalculatorPokemon pokemon = pokemons.get(position);

        holder.name.setText(pokemon.getName());
        holder.amount.setText("");
        holder.candy.setText("");
    }

    @Override
    public int getItemCount() {
        return pokemons.size();
    }

    public void remove(int position) {
        if (position >= 0) {
            pokemons.remove(position);
            notifyItemRemoved(position);
        }

        if (pokemons.size() > 0)
            presenter.update(pokemons);
        else
            presenter.hideResult();
    }

    public void add(XPCalculatorPokemon pokemon) {
        pokemons.add(0, pokemon);
        notifyItemInserted(0);
    }

    public List<XPCalculatorPokemon> getPokemons() {
        return pokemons;
    }
}

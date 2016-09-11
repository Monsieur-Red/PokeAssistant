package com.perso.red.pokeassistant.xpcalculator;

import android.support.v7.widget.RecyclerView;
import android.text.TextUtils;
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

import butterknife.BindView;
import butterknife.ButterKnife;
import butterknife.OnClick;
import butterknife.OnTextChanged;

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

    public class DataObjectHolder extends RecyclerView.ViewHolder {
        @BindView(R.id.name)        TextView    name;
        @BindView(R.id.amount)      EditText    amount;
        @BindView(R.id.candy)       EditText    candy;
        @BindView(R.id.btn_delete)  ImageButton delete;

        public DataObjectHolder(View itemView) {
            super(itemView);
            ButterKnife.bind(this, itemView);
        }

        @OnTextChanged(R.id.amount)
        public void OnTextChangedAmount(CharSequence text) {
            if (!TextUtils.isEmpty(text)) {
                XPCalculatorPokemon pokemon = pokemons.get(getAdapterPosition());
                int number = Integer.valueOf(text.toString());

                if (number > 100000) {
                    amount.setText(new StringBuilder("100000"));
                    pokemon.setAmount(100000);
                }
                else
                    pokemon.setAmount(number);
            }

            if (!TextUtils.isEmpty(text) && !TextUtils.isEmpty(candy.getText()))
                presenter.update(pokemons);
            else
                presenter.hideResult();
        }

        @OnTextChanged(R.id.candy)
        public void OnTextChangedCandy(CharSequence text) {
            if (!TextUtils.isEmpty(text)) {
                XPCalculatorPokemon pokemon = pokemons.get(getAdapterPosition());
                int number = Integer.valueOf(text.toString());

                if (number > 100000) {
                    candy.setText(new StringBuilder("100000"));
                    pokemon.setCandy(100000);
                }
                else
                    pokemon.setCandy(number);
            }

            if (!TextUtils.isEmpty(text) && !TextUtils.isEmpty(amount.getText()))
                presenter.update(pokemons);
            else
                presenter.hideResult();
        }

        @OnClick(R.id.btn_delete)
        public void OnClickDelete() {
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
    public void onBindViewHolder(DataObjectHolder holder, int position) {
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

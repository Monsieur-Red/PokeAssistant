package com.perso.red.pokeassistant.pokedex;

import android.content.Context;
import android.text.Editable;
import android.text.TextWatcher;
import android.view.View;
import android.view.inputmethod.InputMethodManager;
import android.widget.AdapterView;
import android.widget.ArrayAdapter;
import android.widget.EditText;
import android.widget.LinearLayout;
import android.widget.ListView;
import android.widget.TextView;

import com.perso.red.pokeassistant.R;

/**
 * Created by pierr on 09/09/2016.
 */

public class PokedexView implements Pokedex.View {

    private View                view;
    private PokedexPresenter presenter;

//    @BindView(R.id.text_view_pokemon_name)  TextView        pokemonName;
//    @BindView(R.id.view_pokemons_names)     LinearLayout    pokemonsNames;

    public PokedexView(View view, PokedexPresenter presenter) {
        this.view = view;
        this.presenter = presenter;
//        ButterKnife.bind(this, view);
    }

/*
    private void initPokemonName() {
        final EditText search = ButterKnife.findById(pokemonsNames, R.id.edit_text_search);
        ListView listView = ButterKnife.findById(pokemonsNames, R.id.list_view_pokemons_names);
        final ArrayAdapter adapter = new ArrayAdapter<>(listView.getContext(), R.layout.view_pokemons_names_rv_row, R.id.name, presenter.getPokemonNames());

        pokemonName.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                pokemonsNames.setVisibility(View.VISIBLE);

//                if (arcModeShown)
//                    setArcModeVisibility(View.INVISIBLE);
//                else
//                    setDustModeVisibility(View.INVISIBLE);
//
//                arcModeBtn.setVisibility(View.INVISIBLE);
//                dustModeBtn.setVisibility(View.INVISIBLE);
                pokemonsNames.bringToFront();
                search.requestFocus();
            }
        });

        search.setOnFocusChangeListener(new View.OnFocusChangeListener() {
            @Override
            public void onFocusChange(View v, boolean hasFocus) {
                if (hasFocus)
                    ((InputMethodManager) v.getContext().getSystemService(Context.INPUT_METHOD_SERVICE)).showSoftInput(search, InputMethodManager.SHOW_IMPLICIT);
            }
        });

        search.addTextChangedListener(new TextWatcher() {
            @Override
            public void beforeTextChanged(CharSequence s, int start, int count, int after) {

            }

            @Override
            public void onTextChanged(CharSequence s, int start, int before, int count) {
                adapter.getFilter().filter(s);
            }

            @Override
            public void afterTextChanged(Editable s) {

            }
        });

        listView.setAdapter(adapter);
        listView.setOnItemClickListener(new AdapterView.OnItemClickListener() {
            @Override
            public void onItemClick(AdapterView<?> parent, View view, int position, long id) {
                search.setText("");
                adapter.getFilter().filter("");
                ((InputMethodManager) view.getContext().getSystemService(Context.INPUT_METHOD_SERVICE)).hideSoftInputFromWindow(view.getWindowToken(), 0);
//                presenter.setPokemonName((String) adapter.getItem(position));

//                if (arcModeShown)
//                    setArcModeVisibility(View.VISIBLE);
//                else
//                    setDustModeVisibility(View.VISIBLE);
//
//                arcModeBtn.setVisibility(View.VISIBLE);
//                dustModeBtn.setVisibility(View.VISIBLE);
            }
        });
    }

    @OnClick(R.id.btn_menu)
    public void OnClickMenu() {
        presenter.showMenu();
    }
*/


}

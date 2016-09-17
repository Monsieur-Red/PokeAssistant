package com.perso.red.pikaassistant.evocalculator;

import android.support.v7.widget.LinearLayoutManager;
import android.support.v7.widget.RecyclerView;
import android.text.TextUtils;
import android.view.KeyEvent;
import android.view.View;
import android.view.inputmethod.EditorInfo;
import android.widget.ArrayAdapter;
import android.widget.AutoCompleteTextView;
import android.widget.Button;
import android.widget.EditText;
import android.widget.ImageButton;
import android.widget.LinearLayout;
import android.widget.TextView;

import com.perso.red.pikaassistant.R;
import com.perso.red.pikaassistant.models.EvoCalculatorPokemon;

import java.util.List;

import jp.wasabeef.recyclerview.adapters.AlphaInAnimationAdapter;
import jp.wasabeef.recyclerview.animators.LandingAnimator;

/**
 * Created by pierr on 11/09/2016.
 */

public class EvoCalculatorView implements EvoCalculator.View {

    private LinearLayout            resultContainer;
    private EvoCalculatorRVAdapter  adapter;

    public EvoCalculatorView(View view, final EvoCalculatorPresenter presenter, boolean showMenuBtn) {
        Button                      menuBtn = (Button) view.findViewById(R.id.btn_menu);
        final AutoCompleteTextView  pokemonName = (AutoCompleteTextView) view.findViewById(R.id.search_pokemon_name);
        final EditText              pokemonCp = (EditText) view.findViewById(R.id.search_cp);
        resultContainer = (LinearLayout) view.findViewById(R.id.container_result);
        RecyclerView                recyclerView = (RecyclerView) view.findViewById(R.id.recycler_view);
        ImageButton                 deleteBtn = (ImageButton) view.findViewById(R.id.btn_delete);

        pokemonName.setAdapter(new ArrayAdapter<>(view.getContext(), R.layout.autocompletetextview_dropdown_item, presenter.getPokemonNamesWithEvolution()));

        recyclerView.setLayoutManager(new LinearLayoutManager(view.getContext()));
        recyclerView.setItemAnimator(new LandingAnimator());
        adapter = new EvoCalculatorRVAdapter(presenter);
        recyclerView.setAdapter(new AlphaInAnimationAdapter(adapter));

        resultContainer.setVisibility(View.GONE);

        if (!showMenuBtn)
            menuBtn.setVisibility(View.INVISIBLE);
        else {
            menuBtn.setOnClickListener(new View.OnClickListener() {
                @Override
                public void onClick(View view) {
                    presenter.showMenu();
                }
            });
        }

        deleteBtn.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                adapter.removeAll();
            }
        });

        pokemonName.setOnFocusChangeListener(new View.OnFocusChangeListener() {
            @Override
            public void onFocusChange(View view, boolean hasFocus) {
                if (hasFocus)
                    pokemonName.setText("");
            }
        });

        pokemonCp.setOnEditorActionListener(new TextView.OnEditorActionListener() {
            @Override
            public boolean onEditorAction(TextView textView, int actionId, KeyEvent keyEvent) {
                if (actionId == EditorInfo.IME_ACTION_DONE) {
                    String  name = pokemonName.getText().toString();
                    String  cp = pokemonCp.getText().toString();

                    if (!TextUtils.isEmpty(name) && !TextUtils.isEmpty(cp))
                        presenter.update(name, cp);
                }
                return false;
            }
        });
    }

    @Override
    public void setResult(List<EvoCalculatorPokemon> pokemons) {
        resultContainer.setVisibility(View.VISIBLE);
        adapter.addAll(pokemons);
    }

    @Override
    public void hideResult() {
        resultContainer.setVisibility(View.GONE);
    }
}

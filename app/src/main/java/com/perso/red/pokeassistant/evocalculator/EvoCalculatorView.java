package com.perso.red.pokeassistant.evocalculator;

import android.support.v7.widget.LinearLayoutManager;
import android.support.v7.widget.RecyclerView;
import android.text.TextUtils;
import android.view.View;
import android.view.inputmethod.EditorInfo;
import android.widget.ArrayAdapter;
import android.widget.AutoCompleteTextView;
import android.widget.EditText;
import android.widget.LinearLayout;

import com.perso.red.pokeassistant.R;
import com.perso.red.pokeassistant.models.EvoCalculatorPokemon;

import java.util.List;

import butterknife.BindView;
import butterknife.ButterKnife;
import butterknife.OnClick;
import butterknife.OnEditorAction;
import butterknife.OnFocusChange;
import jp.wasabeef.recyclerview.adapters.AlphaInAnimationAdapter;
import jp.wasabeef.recyclerview.animators.LandingAnimator;

/**
 * Created by pierr on 11/09/2016.
 */

public class EvoCalculatorView implements EvoCalculator.View {

    private View                    view;
    private EvoCalculatorPresenter  presenter;

    private LinearLayoutManager     linearLayoutManager;
    private EvoCalculatorRVAdapter  adapter;

    @BindView(R.id.search_pokemon_name) AutoCompleteTextView pokemonName;
    @BindView(R.id.search_cp)           EditText pokemonCp;
    @BindView(R.id.container_result)    LinearLayout resultContainer;
    @BindView(R.id.recycler_view)       RecyclerView recyclerView;

    public EvoCalculatorView(View view, EvoCalculatorPresenter presenter) {
        this.view = view;
        this.presenter = presenter;
        ButterKnife.bind(this, view);

        pokemonName.setAdapter(new ArrayAdapter<>(view.getContext(), R.layout.autocompletetextview_dropdown_item, presenter.getPokemonNamesWithEvolution()));

        linearLayoutManager = new LinearLayoutManager(view.getContext());
        recyclerView.setLayoutManager(linearLayoutManager);
        recyclerView.setItemAnimator(new LandingAnimator());
        adapter = new EvoCalculatorRVAdapter(presenter);
        recyclerView.setAdapter(new AlphaInAnimationAdapter(adapter));

        resultContainer.setVisibility(View.GONE);
    }

    @OnClick(R.id.btn_menu)
    public void OnClickMenu() {
        presenter.showMenu();
    }

    @OnClick(R.id.btn_delete)
    public void OnClickDelete() {
        adapter.removeAll();
    }

    @OnFocusChange(R.id.search_pokemon_name)
    public void OnFocusChangePokemonName(boolean hasFocus) {
        if (hasFocus)
            pokemonName.setText("");
    }

    @OnEditorAction(R.id.search_cp)
    public boolean OnEditorActionPokemonName(int actionId) {
        if (actionId == EditorInfo.IME_ACTION_DONE) {
            String  name = pokemonName.getText().toString();
            String  cp = pokemonCp.getText().toString();

            if (!TextUtils.isEmpty(name) && !TextUtils.isEmpty(cp))
                presenter.update(name, cp);
        }
        return false;
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

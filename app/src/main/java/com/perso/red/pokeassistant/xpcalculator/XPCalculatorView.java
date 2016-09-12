package com.perso.red.pokeassistant.xpcalculator;

import android.content.Context;
import android.support.v7.widget.LinearLayoutManager;
import android.support.v7.widget.RecyclerView;
import android.text.TextUtils;
import android.view.View;
import android.view.inputmethod.EditorInfo;
import android.view.inputmethod.InputMethodManager;
import android.widget.ArrayAdapter;
import android.widget.AutoCompleteTextView;
import android.widget.LinearLayout;
import android.widget.TextView;

import com.perso.red.pokeassistant.R;
import com.perso.red.pokeassistant.models.XPCalculatorPokemon;

import java.util.Locale;

import butterknife.BindView;
import butterknife.ButterKnife;
import butterknife.OnCheckedChanged;
import butterknife.OnClick;
import butterknife.OnEditorAction;
import jp.wasabeef.recyclerview.adapters.AlphaInAnimationAdapter;
import jp.wasabeef.recyclerview.animators.LandingAnimator;

/**
 * Created by pierr on 10/09/2016.
 */

public class XPCalculatorView implements XPCalculator.View {

    private XPCalculatorPresenter   presenter;

    @BindView(R.id.edit_text_pokemon_name)
    AutoCompleteTextView    pokemonName;
    @BindView(R.id.recycler_view)
    RecyclerView    recyclerView;
    @BindView(R.id.container_result)
    LinearLayout resultContainer;
    @BindView(R.id.experience)
    TextView experience;
    @BindView(R.id.time)
    TextView time;
    @BindView(R.id.transfert_before_evolving)
    TextView transfertBeforeEvolving;
    @BindView(R.id.activate_your_lucky_egg)
    TextView activateYourLuckyEgg;
    @BindView(R.id.transfert_result)
    TextView transfertResult;
    @BindView(R.id.evolve_result)
    TextView evolveResult;

    private InputMethodManager      inputMethodManager;
    private LinearLayoutManager     linearLayoutManager;
    private XPCalculatorRVAdpater   adapter;

    public XPCalculatorView(View view, XPCalculatorPresenter presenter) {
        this.presenter = presenter;
        ButterKnife.bind(this, view);
        inputMethodManager = (InputMethodManager)view.getContext().getSystemService(Context.INPUT_METHOD_SERVICE);

        pokemonName.setAdapter(new ArrayAdapter<>(view.getContext(), R.layout.autocompletetextview_dropdown_item, presenter.getPokemonNamesWithEvolution()));

        linearLayoutManager = new LinearLayoutManager(view.getContext());
        recyclerView.setLayoutManager(linearLayoutManager);
        recyclerView.setItemAnimator(new LandingAnimator());
        adapter = new XPCalculatorRVAdpater(presenter);
        recyclerView.setAdapter(new AlphaInAnimationAdapter(adapter));

        resultContainer.setVisibility(View.GONE);
        activateYourLuckyEgg.setVisibility(View.GONE);
    }

    @OnClick(R.id.btn_menu)
    public void OnClickMenu() {
        presenter.showMenu();
    }

    @OnEditorAction(R.id.edit_text_pokemon_name)
    public boolean OnEditorActionPokemonName(int actionId) {
        if (actionId == EditorInfo.IME_ACTION_DONE) {
            OnClickAdd();
            return true;
        }
        return false;
    }

    @OnClick(R.id.btn_add)
    public void OnClickAdd() {
        String  pokemon = pokemonName.getText().toString();

        if (!TextUtils.isEmpty(pokemon) && presenter.checkPokemonName(pokemon)) {
            adapter.add(new XPCalculatorPokemon(pokemon));
            linearLayoutManager.scrollToPositionWithOffset(0, 0);
        }

        pokemonName.setText("");
        inputMethodManager.hideSoftInputFromWindow(pokemonName.getWindowToken(), 0);
    }

    @OnCheckedChanged(R.id.checkbox_lucky_egg)
    public void OnCheckedChangedLuckyEgg(boolean isChecked) {
        if (isChecked)
            presenter.setLuckyEgg(true);
        else
            presenter.setLuckyEgg(false);
        presenter.update(adapter.getPokemons());
    }

    @Override
    public void setResult(int experience, int time, boolean luckyEgg, String transfert, String evolve) {
        String  xpStr = experience + " XP";
        String  timeStr;

        if (time >= 120)
            timeStr = String.format(Locale.getDefault(), "%d:%02d", time / 60, time % 60) + " h";
        else if (time >= 60)
            timeStr = String.format(Locale.getDefault(), "%d:%02d", time / 60, time % 60) + " h";
        else
            timeStr = String.format(Locale.getDefault(), "%02d", time % 60) + " min";

        this.time.setText(timeStr);
        this.experience.setText(xpStr);

        if (!TextUtils.isEmpty(transfert)) {
            transfertResult.setText(transfert);
            transfertResult.setVisibility(View.VISIBLE);
            transfertBeforeEvolving.setVisibility(View.VISIBLE);
        }
        else {
            transfertResult.setVisibility(View.GONE);
            transfertBeforeEvolving.setVisibility(View.GONE);
        }

        if (luckyEgg)
            activateYourLuckyEgg.setVisibility(View.VISIBLE);
        else
            activateYourLuckyEgg.setVisibility(View.GONE);

        if (!TextUtils.isEmpty(evolve)) {
            evolveResult.setText(evolve);
            evolveResult.setVisibility(View.VISIBLE);
        }
        else
            evolveResult.setVisibility(View.GONE);

        resultContainer.setVisibility(View.VISIBLE);
    }

    @Override
    public void hideResult() {
        resultContainer.setVisibility(View.GONE);
    }
}

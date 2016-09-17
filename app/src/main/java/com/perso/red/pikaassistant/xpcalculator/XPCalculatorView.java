package com.perso.red.pikaassistant.xpcalculator;

import android.content.Context;
import android.support.v7.widget.LinearLayoutManager;
import android.support.v7.widget.RecyclerView;
import android.text.TextUtils;
import android.view.KeyEvent;
import android.view.View;
import android.view.inputmethod.EditorInfo;
import android.view.inputmethod.InputMethodManager;
import android.widget.ArrayAdapter;
import android.widget.AutoCompleteTextView;
import android.widget.Button;
import android.widget.CheckBox;
import android.widget.CompoundButton;
import android.widget.LinearLayout;
import android.widget.TextView;

import com.perso.red.pikaassistant.R;
import com.perso.red.pikaassistant.models.XPCalculatorPokemon;

import java.util.Locale;

import jp.wasabeef.recyclerview.adapters.AlphaInAnimationAdapter;
import jp.wasabeef.recyclerview.animators.LandingAnimator;

/**
 * Created by pierr on 10/09/2016.
 */

public class XPCalculatorView implements XPCalculator.View {

    private XPCalculatorPresenter   presenter;

    private InputMethodManager      inputMethodManager;
    private LinearLayoutManager     linearLayoutManager;
    private XPCalculatorRVAdpater   adapter;
    private LinearLayout            resultContainer;
    private TextView                experience;
    private TextView                time;
    private TextView                transfertBeforeEvolving;
    private TextView                activateYourLuckyEgg;
    private TextView                transfertResult;
    private TextView                evolveResult;

    public XPCalculatorView(View view, final XPCalculatorPresenter presenter, boolean showMenuBtn) {
        this.presenter = presenter;

        Button                  menuBtn = (Button) view.findViewById(R.id.btn_menu);
        final AutoCompleteTextView    pokemonName = (AutoCompleteTextView) view.findViewById(R.id.edit_text_pokemon_name);
        RecyclerView            recyclerView = (RecyclerView) view.findViewById(R.id.recycler_view);
        resultContainer = (LinearLayout) view.findViewById(R.id.container_result);
        experience = (TextView) view.findViewById(R.id.experience);
        time = (TextView) view.findViewById(R.id.time);
        transfertBeforeEvolving = (TextView) view.findViewById(R.id.transfert_before_evolving);
        activateYourLuckyEgg = (TextView) view.findViewById(R.id.activate_your_lucky_egg);
        transfertResult = (TextView) view.findViewById(R.id.transfert_result);
        evolveResult = (TextView) view.findViewById(R.id.evolve_result);
        final Button                  addBtn = (Button) view.findViewById(R.id.btn_add);
        CheckBox                luckyEggCb = (CheckBox) view.findViewById(R.id.checkbox_lucky_egg);

        inputMethodManager = (InputMethodManager)view.getContext().getSystemService(Context.INPUT_METHOD_SERVICE);

        pokemonName.setAdapter(new ArrayAdapter<>(view.getContext(), R.layout.autocompletetextview_dropdown_item, presenter.getPokemonNamesWithEvolution()));

        linearLayoutManager = new LinearLayoutManager(view.getContext());
        recyclerView.setLayoutManager(linearLayoutManager);
        recyclerView.setItemAnimator(new LandingAnimator());
        adapter = new XPCalculatorRVAdpater(presenter);
        recyclerView.setAdapter(new AlphaInAnimationAdapter(adapter));

        resultContainer.setVisibility(View.GONE);
        activateYourLuckyEgg.setVisibility(View.GONE);

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

        pokemonName.setOnEditorActionListener(new TextView.OnEditorActionListener() {
            @Override
            public boolean onEditorAction(TextView textView, int actionId, KeyEvent keyEvent) {
                if (actionId == EditorInfo.IME_ACTION_DONE) {
                    addBtn.callOnClick();
                    return true;
                }
                return false;
            }
        });

        addBtn.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                String  pokemon = pokemonName.getText().toString();

                if (!TextUtils.isEmpty(pokemon) && presenter.checkPokemonName(pokemon)) {
                    adapter.add(new XPCalculatorPokemon(pokemon));
                    linearLayoutManager.scrollToPositionWithOffset(0, 0);
                }

                pokemonName.setText("");
                inputMethodManager.hideSoftInputFromWindow(pokemonName.getWindowToken(), 0);
            }
        });

        luckyEggCb.setOnCheckedChangeListener(new CompoundButton.OnCheckedChangeListener() {
            @Override
            public void onCheckedChanged(CompoundButton compoundButton, boolean isChecked) {
                if (isChecked)
                    presenter.setLuckyEgg(true);
                else
                    presenter.setLuckyEgg(false);
                presenter.update(adapter.getPokemons());
            }
        });

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

package com.perso.red.pokeassistant.ivcalculator.view;

import android.content.Context;
import android.text.Editable;
import android.text.TextUtils;
import android.text.TextWatcher;
import android.view.View;
import android.view.WindowManager;
import android.view.inputmethod.InputMethodManager;
import android.widget.AdapterView;
import android.widget.ArrayAdapter;
import android.widget.Button;
import android.widget.EditText;
import android.widget.ImageView;
import android.widget.LinearLayout;
import android.widget.ListView;
import android.widget.SeekBar;
import android.widget.TextView;

import com.perso.red.pokeassistant.R;
import com.perso.red.pokeassistant.ivcalculator.presenter.IVCalculatorPresenter;
import com.perso.red.pokeassistant.utils.Tools;

import java.util.Locale;

import butterknife.BindView;
import butterknife.ButterKnife;


/**
 * Created by pierr on 17/08/2016.
 */

public class IVCalculatorView implements IIVCalculatorView {

    private View            view;
    private MyArc           myArc;

    @BindView(R.id.edit_text_trainer_lvl)   EditText        trainerLvl;
    @BindView(R.id.text_view_pokemon_name)  TextView        pokemonName;
    @BindView(R.id.view_pokemons_names)     LinearLayout    pokemonsNames;
    @BindView(R.id.edit_text_pokemon_cp)    EditText        pokemonCP;
    @BindView(R.id.edit_text_pokemon_hp)    EditText        pokemonHP;
    @BindView(R.id.text_view_pokemon_lvl)   TextView        pokemonLvl;
    @BindView(R.id.btn_plus)                Button          plus;
    @BindView(R.id.btn_plus_drawable)       Button          plusDrawable;
    @BindView(R.id.btn_minus)               Button          minus;
    @BindView(R.id.btn_minus_drawable)      Button          minusDrawable;

    private double          estimatedPokemonLvl;

    public IVCalculatorView(View view, WindowManager windowManager, IVCalculatorPresenter presenter) {
        this.view = view;
        ButterKnife.bind(this, view);

        initTrainerLvl(presenter);
        initPokemonName(presenter);
        initCP(presenter);
        initHP(presenter);
        initPokemonLvl(presenter, windowManager);
    }

    private void initTrainerLvl(final IVCalculatorPresenter presenter) {
        final SeekBar   seekBar = (SeekBar) view.findViewById(R.id.seekbar);

        trainerLvl.addTextChangedListener(new TextWatcher() {
            @Override
            public void beforeTextChanged(CharSequence s, int start, int count, int after) {

            }

            @Override
            public void onTextChanged(CharSequence s, int start, int before, int count) {
                if (TextUtils.isEmpty(s)) {
                    presenter.setTrainerLvl("-1");
                    seekBar.setProgress(0);
                    seekBar.setMax(0);
                }
                else {
                    int trainerLvlInt = Integer.valueOf(s.toString());

                    if (trainerLvlInt >= 1 && trainerLvlInt <= 40) {
                        myArc.setupArcPoints(trainerLvlInt);
                        seekBar.setProgress(1);
                        seekBar.setProgress(0);
                        seekBar.setMax(Math.min(trainerLvlInt * 2 + 1, 79));
                        presenter.setTrainerLvl(s.toString());
                    }
                    else
                        trainerLvl.setText("");
                }
            }

            @Override
            public void afterTextChanged(Editable s) {

            }
        });
    }

    private void initPokemonName(final IVCalculatorPresenter presenter) {
        final EditText    search = ButterKnife.findById(pokemonsNames, R.id.edit_text_search);
        ListView    listView = ButterKnife.findById(pokemonsNames, R.id.list_view_pokemons_names);
        final ArrayAdapter adapter = new ArrayAdapter<String>(listView.getContext(), R.layout.view_pokemons_names_rv_row, R.id.name, presenter.getPokemonNames());

        pokemonName.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                pokemonsNames.setVisibility(View.VISIBLE);
                setPokemonLvlVisibility(View.INVISIBLE);
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
                presenter.setPokemonName((String) adapter.getItem(position));
                setPokemonLvlVisibility(View.VISIBLE);
            }
        });
    }

    private void initCP(final IVCalculatorPresenter presenter) {
        pokemonCP.addTextChangedListener(new TextWatcher() {
            @Override
            public void beforeTextChanged(CharSequence s, int start, int count, int after) {

            }

            @Override
            public void onTextChanged(CharSequence s, int start, int before, int count) {
                if (TextUtils.isEmpty(s))
                    presenter.setPokemonCP("-1");
                else {
                    int cp = Integer.valueOf(s.toString());

                    if (cp >= 0 && cp <= 5000)
                        presenter.setPokemonCP(s.toString());
                    else
                        pokemonCP.setText("");
                }
            }

            @Override
            public void afterTextChanged(Editable s) {

            }
        });
    }

    private void initHP(final IVCalculatorPresenter presenter) {
        pokemonHP.setOnFocusChangeListener(new View.OnFocusChangeListener() {
            @Override
            public void onFocusChange(View v, boolean hasFocus) {
                if (!hasFocus)
                    ((InputMethodManager) v.getContext().getSystemService(Context.INPUT_METHOD_SERVICE)).hideSoftInputFromWindow(view.getWindowToken(), 0);
            }
        });

        pokemonHP.addTextChangedListener(new TextWatcher() {
            @Override
            public void beforeTextChanged(CharSequence s, int start, int count, int after) {

            }

            @Override
            public void onTextChanged(CharSequence s, int start, int before, int count) {
                if (TextUtils.isEmpty(s))
                    presenter.setPokemonHP("-1");
                else {
                    int hp = Integer.valueOf(s.toString());

                    if (hp >= 0 && hp <= 1000)
                        presenter.setPokemonHP(s.toString());
                    else
                        pokemonHP.setText("");
                }
            }

            @Override
            public void afterTextChanged(Editable s) {

            }
        });
    }

    private void initPokemonLvl(final IVCalculatorPresenter presenter, WindowManager windowManager) {
        final SeekBar seekBar = ButterKnife.findById(view, R.id.seekbar);
        myArc = new MyArc(view.getContext(), windowManager);

        seekBar.setOnSeekBarChangeListener(new SeekBar.OnSeekBarChangeListener() {
            @Override
            public void onProgressChanged(SeekBar seekBar, int progress, boolean fromUser) {
                double  roundEstimatedPokemonLvl;
                estimatedPokemonLvl = 1 + (progress * 0.5);
                roundEstimatedPokemonLvl = Tools.roundEstimatedLvl(estimatedPokemonLvl);

                myArc.getMyArcPointer().setArcPointer(estimatedPokemonLvl);

                pokemonLvl.setText(String.format(Locale.getDefault(), "%.1f", roundEstimatedPokemonLvl));
                presenter.setPokemonLvl(roundEstimatedPokemonLvl);
            }

            @Override
            public void onStartTrackingTouch(SeekBar seekBar) {

            }

            @Override
            public void onStopTrackingTouch(SeekBar seekBar) {
                ((InputMethodManager) view.getContext().getSystemService(Context.INPUT_METHOD_SERVICE)).hideSoftInputFromWindow(view.getWindowToken(), 0);
            }

        });

        minusDrawable.setClickable(false);
        minus.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                if (!TextUtils.isEmpty(trainerLvl.getText())) {
                    if (estimatedPokemonLvl > 1.0)
                        estimatedPokemonLvl -= 0.5;

                    myArc.getMyArcPointer().setArcPointer(estimatedPokemonLvl);
                    seekBar.setProgress((int) ((estimatedPokemonLvl - 1) * 2));
                }
            }
        });

        plusDrawable.setClickable(false);
        plus.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                if (!TextUtils.isEmpty(trainerLvl.getText())) {
                    if (estimatedPokemonLvl < Double.valueOf(trainerLvl.getText().toString()) + 1.5 && estimatedPokemonLvl < 40.5)
                        estimatedPokemonLvl += 0.5;

                    myArc.getMyArcPointer().setArcPointer(estimatedPokemonLvl);
                    seekBar.setProgress((int) ((estimatedPokemonLvl - 1) * 2));
                }
            }
        });
    }

    private void setPokemonLvlVisibility(int visibility) {
        pokemonLvl.setVisibility(visibility);
        plus.setVisibility(visibility);
        plusDrawable.setVisibility(visibility);
        minus.setVisibility(visibility);
        minusDrawable.setVisibility(visibility);
    }


    @Override
    public void hidePokemonsNames(String name) {
        pokemonName.setText(name);
        pokemonsNames.setVisibility(View.GONE);
    }

    @Override
    public boolean isInputsFilled() {
        return !TextUtils.isEmpty(trainerLvl.getText()) && !TextUtils.isEmpty(pokemonName.getText()) && !TextUtils.isEmpty(pokemonCP.getText()) && !TextUtils.isEmpty(pokemonHP.getText()) && !TextUtils.isEmpty(pokemonLvl.getText());
    }

    public ImageView getMyArcPointer() {
        return myArc.getMyArcPointer().getPointerView();
    }

    public EditText getTrainerLvl() {
        return trainerLvl;
    }
}

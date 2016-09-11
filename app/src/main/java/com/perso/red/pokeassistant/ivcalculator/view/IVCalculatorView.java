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
import android.widget.Spinner;
import android.widget.TextView;

import com.perso.red.pokeassistant.R;
import com.perso.red.pokeassistant.ivcalculator.presenter.IVCalculatorPresenter;
import com.perso.red.pokeassistant.utils.Constants;
import com.perso.red.pokeassistant.utils.Tools;

import java.util.Locale;

import butterknife.BindView;
import butterknife.ButterKnife;
import butterknife.OnCheckedChanged;
import butterknife.OnClick;

/**
 * Created by pierr on 17/08/2016.
 */

public class IVCalculatorView implements IIVCalculatorView {

    private View                    view;
    private IVCalculatorPresenter   presenter;
    private MyArc                   myArc;

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
    @BindView(R.id.seekbar)                 SeekBar         seekBar;
    @BindView(R.id.dust_ui)                 LinearLayout    dustUi;
    @BindView(R.id.spinner_pokemon_dust)    Spinner         pokemonDust;
    @BindView(R.id.btn_arc_mode)            Button          arcModeBtn;
    @BindView(R.id.btn_dust_mode)           Button          dustModeBtn;

    private boolean     arcModeShown;
    private double      estimatedPokemonLvl;

    public IVCalculatorView(View view, WindowManager windowManager, IVCalculatorPresenter presenter) {
        this.view = view;
        this.presenter = presenter;
        ButterKnife.bind(this, view);
        arcModeShown = true;

        initTrainerLvl(presenter);
        initPokemonName(presenter);
        initCP(presenter);
        initHP(presenter);
        initPokemonLvl(presenter, windowManager);
        initDustUI(view.getContext());
    }

    private void initTrainerLvl(final IVCalculatorPresenter presenter) {
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
        final EditText      search = ButterKnife.findById(pokemonsNames, R.id.edit_text_search);
        final ListView      listView = ButterKnife.findById(pokemonsNames, R.id.list_view_pokemons_names);
        final ArrayAdapter  adapter = new ArrayAdapter<>(listView.getContext(), R.layout.view_pokemons_names_rv_row, R.id.name, presenter.getPokemonNames());

        pokemonName.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                pokemonsNames.setVisibility(View.VISIBLE);

                if (arcModeShown)
                    setArcModeVisibility(View.INVISIBLE);
                else
                    setDustModeVisibility(View.INVISIBLE);

                arcModeBtn.setVisibility(View.INVISIBLE);
                dustModeBtn.setVisibility(View.INVISIBLE);
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

                if (arcModeShown)
                    setArcModeVisibility(View.VISIBLE);
                else
                    setDustModeVisibility(View.VISIBLE);

                arcModeBtn.setVisibility(View.VISIBLE);
                dustModeBtn.setVisibility(View.VISIBLE);
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
                ((InputMethodManager) view.getContext().getSystemService(Context.INPUT_METHOD_SERVICE)).hideSoftInputFromWindow(view.getWindowToken(), 0);
            }

            @Override
            public void onStopTrackingTouch(SeekBar seekBar) {
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

    private void initDustUI(Context context) {
        ArrayAdapter adapter = ArrayAdapter.createFromResource(context, R.array.dust_array, R.layout.spinner_item);
        adapter.setDropDownViewResource(R.layout.spinner_dropdown_item);
        pokemonDust.setAdapter(adapter);

        pokemonDust.setOnItemSelectedListener(new AdapterView.OnItemSelectedListener() {
              @Override
              public void onItemSelected(AdapterView<?> parent, View view, int position, long id) {
                  presenter.setPokemonDust((String) parent.getAdapter().getItem(position));
              }

              @Override
              public void onNothingSelected(AdapterView<?> parent) {

              }
      });
    }

    private void setArcModeVisibility(int visibility) {
        pokemonLvl.setVisibility(visibility);
        plus.setVisibility(visibility);
        plusDrawable.setVisibility(visibility);
        minus.setVisibility(visibility);
        minusDrawable.setVisibility(visibility);
        myArc.getMyArcPointer().getPointerView().setVisibility(visibility);
        seekBar.setVisibility(visibility);
    }

    private void setDustModeVisibility(int visibility) {
        dustUi.setVisibility(visibility);
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

    @Override
    public void setVisibility(int visibility) {
        if (arcModeShown)
            setArcModeVisibility(visibility);
        else
            setDustModeVisibility(visibility);
    }

    @OnCheckedChanged (R.id.checkbox_pokemon_powered_up)
    public void setPokemonPoweredUp(boolean isChecked) {
        presenter.setPokemonPoweredUp(isChecked);
    }

    @OnClick(R.id.btn_menu)
    public void OnClickMenu() {
        presenter.showMenu();
    }

    @OnClick(R.id.btn_iv_details)
    public void OnClickIvDetails() {
        presenter.showIvDetails();
    }

    @OnClick(R.id.btn_moves)
    public void OnClickMoves() {
        presenter.showMoves();
    }

    @OnClick (R.id.btn_arc_mode)
    public void setArcMode() {
        arcModeShown = true;
        setDustModeVisibility(View.GONE);
        setArcModeVisibility(View.VISIBLE);
        presenter.setCalculatorMode(Constants.CALCULATOR_MODE_ARC);
    }

    @OnClick (R.id.btn_dust_mode)
    public void setDustMode() {
        arcModeShown = false;
        setArcModeVisibility(View.GONE);
        setDustModeVisibility(View.VISIBLE);
        presenter.setCalculatorMode(Constants.CALCULATOR_MODE_DUST);
    }

    public ImageView getMyArcPointer() {
        return myArc.getMyArcPointer().getPointerView();
    }

    public EditText getTrainerLvl() {
        return trainerLvl;
    }
}

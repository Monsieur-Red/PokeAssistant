package com.perso.red.pikaassistant.ivcalculator.view;

import android.content.Context;
import android.text.Editable;
import android.text.TextUtils;
import android.text.TextWatcher;
import android.view.View;
import android.view.WindowManager;
import android.view.animation.AccelerateInterpolator;
import android.view.animation.AlphaAnimation;
import android.view.animation.Animation;
import android.view.inputmethod.InputMethodManager;
import android.widget.AdapterView;
import android.widget.ArrayAdapter;
import android.widget.Button;
import android.widget.CheckBox;
import android.widget.CompoundButton;
import android.widget.EditText;
import android.widget.ImageView;
import android.widget.LinearLayout;
import android.widget.ListView;
import android.widget.SeekBar;
import android.widget.Spinner;
import android.widget.TextView;

import com.perso.red.pikaassistant.R;
import com.perso.red.pikaassistant.ivcalculator.presenter.IVCalculatorPresenter;
import com.perso.red.pikaassistant.utils.Constants;
import com.perso.red.pikaassistant.utils.Tools;

import java.util.Locale;

/**
 * Created by pierr on 17/08/2016.
 */

public class IVCalculatorView implements IIVCalculatorView {

    private MyArc                   myArc;

    private EditText        trainerLvl;
    private TextView        pokemonName;
    private LinearLayout    pokemonsNames;
    private EditText        pokemonCP;
    private EditText        pokemonHP;
    private TextView        pokemonLvl;
    private Button          plus;
    private Button          plusDrawable;
    private Button          minus;
    private Button          minusDrawable;
    private SeekBar         seekBar;
    private LinearLayout    dustUi;
    private Button          arcModeBtn;
    private Button          dustModeBtn;
    private Button          calculateBtn;
    private TextView        dialog;
    private Animation       fadeOut;

    private boolean     arcModeShown;
    private double      estimatedPokemonLvl;

    public IVCalculatorView(View view, WindowManager windowManager, final IVCalculatorPresenter presenter, boolean showMenuBtn) {
        arcModeShown = true;

        Button menuBtn = (Button) view.findViewById(R.id.btn_menu);
        trainerLvl = (EditText) view.findViewById(R.id.edit_text_trainer_lvl);
        pokemonName = (TextView) view.findViewById(R.id.text_view_pokemon_name);
        pokemonsNames = (LinearLayout) view.findViewById(R.id.view_pokemons_names);
        pokemonCP = (EditText) view.findViewById(R.id.edit_text_pokemon_cp);
        pokemonHP = (EditText) view.findViewById(R.id.edit_text_pokemon_hp);
        pokemonLvl = (TextView) view.findViewById(R.id.text_view_pokemon_lvl);
        plus = (Button) view.findViewById(R.id.btn_plus);
        plusDrawable = (Button) view.findViewById(R.id.btn_plus_drawable);
        minus = (Button) view.findViewById(R.id.btn_minus);
        minusDrawable = (Button) view.findViewById(R.id.btn_minus_drawable);
        seekBar = (SeekBar) view.findViewById(R.id.seekbar);
        dustUi = (LinearLayout) view.findViewById(R.id.dust_ui);
        arcModeBtn = (Button) view.findViewById(R.id.btn_arc_mode);
        dustModeBtn = (Button) view.findViewById(R.id.btn_dust_mode);
        Button ivDetailsBtn = (Button) view.findViewById(R.id.btn_iv_details);
        Button movesBtn = (Button) view.findViewById(R.id.btn_moves);
        calculateBtn = (Button) view.findViewById(R.id.btn_calculate);
        dialog = (TextView) view.findViewById(R.id.dialog);

        fadeOut = new AlphaAnimation(1, 0);
        fadeOut.setInterpolator(new AccelerateInterpolator());
        fadeOut.setDuration(1000);

        initTrainerLvl(presenter);
        initPokemonName(view, presenter);
        initCP(presenter);
        initHP(view, presenter);
        initPokemonLvl(view, presenter, windowManager);
        initDustUI(view, presenter);

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

        ivDetailsBtn.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                presenter.showIvDetails();
            }
        });

        movesBtn.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                presenter.showMoves();
            }
        });

        calculateBtn.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                presenter.calcIv();
            }
        });

        arcModeBtn.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                arcModeShown = true;
                setDustModeVisibility(View.INVISIBLE);
                setArcModeVisibility(View.VISIBLE);
                presenter.setCalculatorMode(Constants.CALCULATOR_MODE_ARC);
            }
        });

        dustModeBtn.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                arcModeShown = false;
                setArcModeVisibility(View.INVISIBLE);
                setDustModeVisibility(View.VISIBLE);
                presenter.setCalculatorMode(Constants.CALCULATOR_MODE_DUST);
            }
        });

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

    private void initPokemonName(View view, final IVCalculatorPresenter presenter) {
        final EditText      search = (EditText) view.findViewById(R.id.edit_text_search);
        final ListView      listView = (ListView) view.findViewById(R.id.list_view_pokemons_names);
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

    private void initHP(final View view, final IVCalculatorPresenter presenter) {
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

    private void initPokemonLvl(final View view, final IVCalculatorPresenter presenter, WindowManager windowManager) {
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
                if (TextUtils.isEmpty(trainerLvl.getText())) {
                    dialog.setVisibility(View.VISIBLE);
                    dialog.startAnimation(fadeOut);
                    dialog.setVisibility(View.INVISIBLE);
                }

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

    private void initDustUI(View view, final IVCalculatorPresenter presenter) {
        Spinner     pokemonDust = (Spinner) view.findViewById(R.id.spinner_pokemon_dust);
        CheckBox    poweredUpCb = (CheckBox) view.findViewById(R.id.checkbox_pokemon_powered_up);

        ArrayAdapter adapter = ArrayAdapter.createFromResource(view.getContext(), R.array.dust_array, R.layout.spinner_item);
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

        poweredUpCb.setOnCheckedChangeListener(new CompoundButton.OnCheckedChangeListener() {
            @Override
            public void onCheckedChanged(CompoundButton compoundButton, boolean isChecked) {
                presenter.setPokemonPoweredUp(isChecked);
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

    @Override
    public void setCalculateBtnVisibility(int visibility) {
        calculateBtn.setVisibility(visibility);
    }

    public ImageView getMyArcPointer() {
        return myArc.getMyArcPointer().getPointerView();
    }

    public EditText getTrainerLvl() {
        return trainerLvl;
    }
}

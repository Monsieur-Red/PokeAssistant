package com.perso.red.pokeassistant;

import android.annotation.TargetApi;
import android.content.DialogInterface;
import android.content.Intent;
import android.content.SharedPreferences;
import android.os.Build;
import android.os.Bundle;
import android.preference.PreferenceManager;
import android.provider.Settings;
import android.support.v7.app.AlertDialog;
import android.support.v7.app.AppCompatActivity;
import android.view.Menu;
import android.view.MenuInflater;
import android.view.MenuItem;
import android.view.View;
import android.view.animation.AccelerateInterpolator;
import android.view.animation.AlphaAnimation;
import android.view.animation.Animation;
import android.view.animation.DecelerateInterpolator;
import android.widget.Button;
import android.widget.CompoundButton;
import android.widget.Switch;

import com.perso.red.pokeassistant.models.ViewsSelected;
import com.perso.red.pokeassistant.utils.Constants;
import com.perso.red.pokeassistant.utils.Tools;

/**
 * Created by pierr on 17/08/2016.
 */

public class MainActivity extends AppCompatActivity {

    private static final int OVERLAY_PERMISSION_REQ_CODE = 1234;

    private SharedPreferences.Editor editor;

    private ViewsSelected   viewsSelected;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);

        SharedPreferences sharedPref = PreferenceManager.getDefaultSharedPreferences(this);
        editor = sharedPref.edit();
        viewsSelected = new ViewsSelected(sharedPref);

        final Button startBtn = (Button) findViewById(R.id.btn_start);
        Switch ivCalculator = (Switch) findViewById(R.id.switch_iv_calculator);
        Switch xpCalculator = (Switch) findViewById(R.id.switch_xp_calculator);
        Switch evoCalculator = (Switch) findViewById(R.id.switch_evolution_calculator);
        Switch eggs = (Switch) findViewById(R.id.switch_eggs);
        Switch appraisal = (Switch) findViewById(R.id.switch_appraisal_guide);

        final Animation fadeIn = new AlphaAnimation(0, 1);
        fadeIn.setInterpolator(new DecelerateInterpolator());
        fadeIn.setDuration(500);

        final Animation fadeOut = new AlphaAnimation(1, 0);
        fadeOut.setInterpolator(new AccelerateInterpolator());
        fadeOut.setDuration(500);

        startBtn.setVisibility(View.GONE);

        if (viewsSelected.size() > 0) {
            startBtn.setVisibility(View.VISIBLE);
            startBtn.startAnimation(fadeIn);
        }
        if (viewsSelected.isIvCalculator())
            ivCalculator.setChecked(true);
        if (viewsSelected.isXpCalculator())
            xpCalculator.setChecked(true);
        if (viewsSelected.isEvolutionCalculator())
            evoCalculator.setChecked(true);
        if (viewsSelected.isEggs())
            eggs.setChecked(true);
        if (viewsSelected.isAppraisal())
            appraisal.setChecked(true);

        startBtn.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                if (viewsSelected.size() > 0 && checkPermissions())
                    startService();
            }
        });

        ivCalculator.setOnCheckedChangeListener(new CompoundButton.OnCheckedChangeListener() {
            @Override
            public void onCheckedChanged(CompoundButton compoundButton, boolean isChecked) {
                viewsSelected.setIvCalculator(isChecked);

                if (viewsSelected.size() > 0 && !startBtn.isShown()) {
                    startBtn.setVisibility(View.VISIBLE);
                    startBtn.startAnimation(fadeIn);
                } else if (viewsSelected.size() == 0 && startBtn.isShown()) {
                    startBtn.startAnimation(fadeOut);
                    startBtn.setVisibility(View.INVISIBLE);
                }
            }
        });

        xpCalculator.setOnCheckedChangeListener(new CompoundButton.OnCheckedChangeListener() {
            @Override
            public void onCheckedChanged(CompoundButton compoundButton, boolean isChecked) {
                viewsSelected.setXpCalculator(isChecked);

                if (viewsSelected.size() > 0 && !startBtn.isShown()) {
                    startBtn.setVisibility(View.VISIBLE);
                    startBtn.startAnimation(fadeIn);
                } else if (viewsSelected.size() == 0 && startBtn.isShown()) {
                    startBtn.startAnimation(fadeOut);
                    startBtn.setVisibility(View.INVISIBLE);
                }
            }
        });

        evoCalculator.setOnCheckedChangeListener(new CompoundButton.OnCheckedChangeListener() {
            @Override
            public void onCheckedChanged(CompoundButton compoundButton, boolean isChecked) {
                viewsSelected.setEvolutionCalculator(isChecked);

                if (viewsSelected.size() > 0 && !startBtn.isShown()) {
                    startBtn.setVisibility(View.VISIBLE);
                    startBtn.startAnimation(fadeIn);
                } else if (viewsSelected.size() == 0 && startBtn.isShown()) {
                    startBtn.startAnimation(fadeOut);
                    startBtn.setVisibility(View.INVISIBLE);
                }
            }
        });

        eggs.setOnCheckedChangeListener(new CompoundButton.OnCheckedChangeListener() {
            @Override
            public void onCheckedChanged(CompoundButton compoundButton, boolean isChecked) {
                viewsSelected.setEggs(isChecked);

                if (viewsSelected.size() > 0 && !startBtn.isShown()) {
                    startBtn.setVisibility(View.VISIBLE);
                    startBtn.startAnimation(fadeIn);
                } else if (viewsSelected.size() == 0 && startBtn.isShown()) {
                    startBtn.startAnimation(fadeOut);
                    startBtn.setVisibility(View.INVISIBLE);
                }
            }
        });

        appraisal.setOnCheckedChangeListener(new CompoundButton.OnCheckedChangeListener() {
            @Override
            public void onCheckedChanged(CompoundButton compoundButton, boolean isChecked) {
                viewsSelected.setAppraisal(isChecked);

                if (viewsSelected.size() > 0 && !startBtn.isShown()) {
                    startBtn.setVisibility(View.VISIBLE);
                    startBtn.startAnimation(fadeIn);
                } else if (viewsSelected.size() == 0 && startBtn.isShown()) {
                    startBtn.startAnimation(fadeOut);
                    startBtn.setVisibility(View.INVISIBLE);
                }
            }
        });
    }

    private boolean checkPermissions() {
        if (Build.VERSION.SDK_INT >= Build.VERSION_CODES.M && !Settings.canDrawOverlays(this)) {
            showExplanation();
            return (false);
        }
        return (true);
    }

    @TargetApi(23)
    @Override
    protected void onActivityResult(int requestCode, int resultCode, Intent data) {
        if (requestCode == OVERLAY_PERMISSION_REQ_CODE) {
            if (!Settings.canDrawOverlays(this))
                showExplanation();
            else
                startService();
        }
    }

    @TargetApi(23)
    private void showExplanation() {
        AlertDialog.Builder builder = new AlertDialog.Builder(this);
        builder.setTitle("")
                .setMessage(R.string.msg_permission)
                .setPositiveButton(android.R.string.ok, new DialogInterface.OnClickListener() {
                    public void onClick(DialogInterface dialog, int id) {
                        Intent intent = new Intent(Settings.ACTION_MANAGE_OVERLAY_PERMISSION);
                        startActivityForResult(intent, OVERLAY_PERMISSION_REQ_CODE);
                    }

                })
                .setNegativeButton(android.R.string.no, new DialogInterface.OnClickListener() {
                    public void onClick(DialogInterface dialog, int id) {
                        finish();
                    }
                });;
        builder.create().show();
    }

    private void startService() {
        editor.putBoolean(Constants.VIEW_SELECTED_IV_CALC, viewsSelected.isIvCalculator());
        editor.putBoolean(Constants.VIEW_SELECTED_XP_CALC, viewsSelected.isXpCalculator());
        editor.putBoolean(Constants.VIEW_SELECTED_EVO_CALC, viewsSelected.isEvolutionCalculator());
        editor.putBoolean(Constants.VIEW_SELECTED_EGGS, viewsSelected.isEggs());
        editor.putBoolean(Constants.VIEW_SELECTED_APPRAISAL, viewsSelected.isAppraisal());
        editor.commit();

        if (Tools.isMyServiceRunning(this, MyService.class))
            stopService(new Intent(this, MyService.class));

        Intent intent = new Intent(this, MyService.class);
        intent.putExtra(Constants.VIEW_SELECTED, viewsSelected);
        startService(intent);

        startActivity(new Intent(Intent.ACTION_MAIN).addCategory(Intent.CATEGORY_HOME));
        finish();
    }

}

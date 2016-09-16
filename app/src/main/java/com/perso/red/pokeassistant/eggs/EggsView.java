package com.perso.red.pokeassistant.eggs;

import android.graphics.drawable.Drawable;
import android.view.View;
import android.widget.Button;
import android.widget.CheckBox;
import android.widget.CompoundButton;
import android.widget.ImageView;

import com.perso.red.pokeassistant.R;
import com.perso.red.pokeassistant.utils.Constants;
import com.perso.red.pokeassistant.utils.Tools;

import java.io.IOException;

import at.blogc.android.views.ExpandableTextView;

/**
 * Created by pierr on 09/09/2016.
 */
public class EggsView implements Eggs.View {

    private Drawable            eggsG1Drawable;
    private Drawable            eggsG2Drawable;

    public EggsView(View view, final EggsPresenter presenter, boolean showMenuBtn) {
        Button                    menuBtn = (Button) view.findViewById(R.id.btn_menu);
        final CheckBox            g1CheckBox = (CheckBox) view.findViewById(R.id.checkbox_generation_1);
        final CheckBox            g2CheckBox = (CheckBox) view.findViewById(R.id.checkbox_generation_2);
        final ImageView           imageView = (ImageView) view.findViewById(R.id.image_eggs);
        final ExpandableTextView  expandableTextView = (ExpandableTextView) view.findViewById(R.id.expandableTextView);
        final Button              expandBtn = (Button) view.findViewById(R.id.btn_expand);

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

        try {
            eggsG1Drawable = Tools.getAssetImage(view.getContext(), Constants.FILE_EGG_G1);
            eggsG2Drawable = Tools.getAssetImage(view.getContext(), Constants.FILE_EGG_G2);
        } catch (IOException e) {
            e.printStackTrace();
        }

        imageView.setImageDrawable(eggsG1Drawable);

        expandBtn.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                expandableTextView.toggle();
            }
        });

        g1CheckBox.setOnCheckedChangeListener(new CompoundButton.OnCheckedChangeListener() {
            @Override
            public void onCheckedChanged(CompoundButton compoundButton, boolean isChecked) {
                if (isChecked) {
                    g2CheckBox.setChecked(false);
                    expandableTextView.setVisibility(View.GONE);
                    expandBtn.setVisibility(View.GONE);
                    imageView.setImageDrawable(eggsG1Drawable);
                }
                else if (!g2CheckBox.isChecked())
                    compoundButton.setChecked(true);
            }
        });

        g2CheckBox.setOnCheckedChangeListener(new CompoundButton.OnCheckedChangeListener() {
            @Override
            public void onCheckedChanged(CompoundButton compoundButton, boolean isChecked) {
                if (isChecked) {
                    g1CheckBox.setChecked(false);
                    expandableTextView.setVisibility(View.VISIBLE);
                    expandBtn.setVisibility(View.VISIBLE);
                    imageView.setImageDrawable(eggsG2Drawable);
                }
                else if (!g1CheckBox.isChecked())
                    compoundButton.setChecked(true);
            }
        });
    }

}

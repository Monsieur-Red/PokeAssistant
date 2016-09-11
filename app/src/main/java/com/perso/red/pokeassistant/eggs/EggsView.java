package com.perso.red.pokeassistant.eggs;

import android.graphics.drawable.Drawable;
import android.view.View;
import android.widget.Button;
import android.widget.CheckBox;
import android.widget.ImageView;

import com.perso.red.pokeassistant.R;
import com.perso.red.pokeassistant.utils.Constants;
import com.perso.red.pokeassistant.utils.Tools;

import java.io.IOException;

import at.blogc.android.views.ExpandableTextView;
import butterknife.BindView;
import butterknife.ButterKnife;
import butterknife.OnCheckedChanged;
import butterknife.OnClick;

/**
 * Created by pierr on 09/09/2016.
 */
public class EggsView implements Eggs.View {

    private EggsPresenter   presenter;

    @BindView(R.id.checkbox_generation_1)   CheckBox            g1CheckBox;
    @BindView(R.id.checkbox_generation_2)   CheckBox            g2CheckBox;
    @BindView(R.id.image_eggs)              ImageView           imageView;
    @BindView(R.id.expandableTextView)      ExpandableTextView  expandableTextView;
    @BindView(R.id.btn_expand)              Button              expandBtn;

    private Drawable            eggsG1Drawable;
    private Drawable            eggsG2Drawable;

    public EggsView(View view, EggsPresenter presenter) {
        this.presenter = presenter;
        ButterKnife.bind(this, view);

        try {
            eggsG1Drawable = Tools.getAssetImage(view.getContext(), Constants.FILE_EGG_G1);
            eggsG2Drawable = Tools.getAssetImage(view.getContext(), Constants.FILE_EGG_G2);
        } catch (IOException e) {
            e.printStackTrace();
        }
        imageView.setImageDrawable(eggsG1Drawable);
    }

    @OnClick(R.id.btn_menu)
    public void OnClickMenu() {
        presenter.showMenu();
    }

    @OnClick(R.id.btn_expand)
    public void OnClickExpand() {
        expandableTextView.toggle();
    }

    @OnCheckedChanged(R.id.checkbox_generation_1)
    public void OnCheckecChangedG1(CheckBox checkBox, boolean isChecked) {
        if (isChecked) {
            g2CheckBox.setChecked(false);
            expandableTextView.setVisibility(View.GONE);
            expandBtn.setVisibility(View.GONE);
            imageView.setImageDrawable(eggsG1Drawable);
        }
        else if (!g2CheckBox.isChecked())
            checkBox.setChecked(true);
    }

    @OnCheckedChanged(R.id.checkbox_generation_2)
    public void OnCheckecChangedG2(CheckBox checkBox, boolean isChecked) {
        if (isChecked) {
            g1CheckBox.setChecked(false);
            expandableTextView.setVisibility(View.VISIBLE);
            expandBtn.setVisibility(View.VISIBLE);
            imageView.setImageDrawable(eggsG2Drawable);
        }
        else if (!g1CheckBox.isChecked())
            checkBox.setChecked(true);
    }
}

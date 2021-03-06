package com.perso.red.pikaassistant.eggs;

import android.view.View;
import android.widget.Button;
import android.widget.CheckBox;
import android.widget.CompoundButton;
import android.widget.ImageView;

import com.perso.red.pikaassistant.R;
import com.perso.red.pikaassistant.utils.Constants;
import com.squareup.picasso.Picasso;

import at.blogc.android.views.ExpandableTextView;
import uk.co.senab.photoview.PhotoViewAttacher;

/**
 * Created by pierr on 09/09/2016.
 */
public class EggsView implements Eggs.View {

    public EggsView(final View view, final EggsPresenter presenter, boolean showMenuBtn) {
        Button                    menuBtn = (Button) view.findViewById(R.id.btn_menu);
        final CheckBox            g1CheckBox = (CheckBox) view.findViewById(R.id.checkbox_generation_1);
        final CheckBox            g2CheckBox = (CheckBox) view.findViewById(R.id.checkbox_generation_2);
        final ImageView           imageView = (ImageView) view.findViewById(R.id.image_eggs);
        final ExpandableTextView  expandableTextView = (ExpandableTextView) view.findViewById(R.id.expandableTextView);
        final Button              expandBtn = (Button) view.findViewById(R.id.btn_expand);
        final PhotoViewAttacher   mAttacher = new PhotoViewAttacher(imageView);

        Picasso.with(view.getContext()).load(Constants.FILE_EGG_G1).into(imageView);
        mAttacher.update();

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
                    Picasso.with(view.getContext()).load(Constants.FILE_EGG_G1).into(imageView);
                    mAttacher.update();
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
                    Picasso.with(view.getContext()).load(Constants.FILE_EGG_G2).into(imageView);
                    mAttacher.update();
                }
                else if (!g1CheckBox.isChecked())
                    compoundButton.setChecked(true);
            }
        });
    }

}

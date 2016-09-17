package com.perso.red.pikaassistant.buddies;

import android.view.View;
import android.widget.Button;
import android.widget.CheckBox;
import android.widget.CompoundButton;
import android.widget.ImageView;

import com.perso.red.pikaassistant.R;
import com.perso.red.pikaassistant.utils.Constants;
import com.squareup.picasso.Picasso;

import uk.co.senab.photoview.PhotoViewAttacher;

/**
 * Created by pierr on 17/09/2016.
 */

public class BuddiesView implements Buddies.View {

    public BuddiesView(final View view, final BuddiesPresenter presenter, boolean showMenuBtn) {

        Button      menuBtn = (Button) view.findViewById(R.id.btn_menu);
        final CheckBox    buddies1km = (CheckBox) view.findViewById(R.id.checkbox_buddies_1km);
        final CheckBox    buddies3km = (CheckBox) view.findViewById(R.id.checkbox_buddies_3km);
        final CheckBox    buddies5km = (CheckBox) view.findViewById(R.id.checkbox_buddies_5km);
        final CheckBox    buddiesUnknownkm = (CheckBox) view.findViewById(R.id.checkbox_buddies_unknown_km);
        final ImageView imageView = (ImageView) view.findViewById(R.id.image);
        final PhotoViewAttacher mAttacher = new PhotoViewAttacher(imageView);

        Picasso.with(view.getContext()).load(Constants.FILE_BUDDIES_1_KM).into(imageView);
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

        buddies1km.setOnCheckedChangeListener(new CompoundButton.OnCheckedChangeListener() {
            @Override
            public void onCheckedChanged(CompoundButton compoundButton, boolean isChecked) {
                if (isChecked) {
                    buddies3km.setChecked(false);
                    buddies5km.setChecked(false);
                    buddiesUnknownkm.setChecked(false);

                    Picasso.with(view.getContext()).load(Constants.FILE_BUDDIES_1_KM).into(imageView);
                    mAttacher.update();
                }
                else if (!buddies3km.isChecked() && !buddies5km.isChecked() && !buddiesUnknownkm.isChecked())
                    compoundButton.setChecked(true);
            }
        });

        buddies3km.setOnCheckedChangeListener(new CompoundButton.OnCheckedChangeListener() {
            @Override
            public void onCheckedChanged(CompoundButton compoundButton, boolean isChecked) {
                if (isChecked) {
                    buddies1km.setChecked(false);
                    buddies5km.setChecked(false);
                    buddiesUnknownkm.setChecked(false);

                    Picasso.with(view.getContext()).load(Constants.FILE_BUDDIES_3_KM).into(imageView);
                    mAttacher.update();
                }
                else if (!buddies1km.isChecked() && !buddies5km.isChecked() && !buddiesUnknownkm.isChecked())
                    compoundButton.setChecked(true);
            }
        });

        buddies5km.setOnCheckedChangeListener(new CompoundButton.OnCheckedChangeListener() {
            @Override
            public void onCheckedChanged(CompoundButton compoundButton, boolean isChecked) {
                if (isChecked) {
                    buddies1km.setChecked(false);
                    buddies3km.setChecked(false);
                    buddiesUnknownkm.setChecked(false);

                    Picasso.with(view.getContext()).load(Constants.FILE_BUDDIES_5_KM).into(imageView);
                    mAttacher.update();
                }
                else if (!buddies1km.isChecked() && !buddies3km.isChecked() && !buddiesUnknownkm.isChecked())
                    compoundButton.setChecked(true);
            }
        });

        buddiesUnknownkm.setOnCheckedChangeListener(new CompoundButton.OnCheckedChangeListener() {
            @Override
            public void onCheckedChanged(CompoundButton compoundButton, boolean isChecked) {
                if (isChecked) {
                    buddies1km.setChecked(false);
                    buddies3km.setChecked(false);
                    buddies5km.setChecked(false);

                    Picasso.with(view.getContext()).load(Constants.FILE_BUDDIES_UNKNOWN_KM).into(imageView);
                    mAttacher.update();
                }
                else if (!buddies1km.isChecked() && !buddies3km.isChecked() && !buddies5km.isChecked())
                    compoundButton.setChecked(true);
            }
        });
        
    }

}

package com.perso.red.pokeassistant.ivdetails.view;

import android.support.v7.widget.LinearLayoutManager;
import android.support.v7.widget.RecyclerView;
import android.view.View;
import android.widget.LinearLayout;
import android.widget.TextView;

import com.perso.red.pokeassistant.R;
import com.perso.red.pokeassistant.models.IVResult;

import java.util.List;
import java.util.Locale;

import butterknife.BindView;
import butterknife.ButterKnife;

/**
 * Created by pierr on 19/08/2016.
 */

public class IVDetailsView implements IIVDetailsView {

    private View            view;

    @BindView(R.id.text_view_no_combinations)   TextView        noCombinations;
    @BindView(R.id.view_iv_details_tab_iv)      LinearLayout    tabIVs;
    @BindView(R.id.view_iv_details_tab_titles)  LinearLayout    tabTitles;
    @BindView(R.id.text_view_iv_details_min)    TextView        minIV;
    @BindView(R.id.text_view_iv_details_average)TextView        averageIV;
    @BindView(R.id.text_view_iv_details_max)    TextView        maxIV;
    @BindView(R.id.recycler_view_iv_details)    RecyclerView    recyclerView;

    private IVDetailsRVAdapter  adapter;

    public IVDetailsView(View view) {
        this.view = view;
        ButterKnife.bind(this, view);

        adapter = new IVDetailsRVAdapter();
        recyclerView.setAdapter(adapter);
        recyclerView.setLayoutManager(new LinearLayoutManager(view.getContext(), LinearLayoutManager.VERTICAL, false));
    }

    @Override
    public boolean isShown() {
        return view.isShown();
    }

    @Override
    public void show() {
        view.setVisibility(View.VISIBLE);
    }

    @Override
    public void hide() {
        view.setVisibility(View.INVISIBLE);
    }

    @Override
    public void updateData(List<IVResult> ivResults) {
        double  minValue = ivResults.get(ivResults.size() - 1).getTotalIV();
        double  maxValue = ivResults.get(0).getTotalIV();
        double  averageValue = (minValue + maxValue) / 2;
        String  min = String.format(Locale.getDefault(), "%.1f",minValue) + "%";
        String  max = String.format(Locale.getDefault(), "%.1f", maxValue) + "%";
        String  average = String.format(Locale.getDefault(), "%.1f", averageValue) + "%";

        minIV.setText(min);
        maxIV.setText(max);
        averageIV.setText(average);
        adapter.update(ivResults);
    }

    @Override
    public void setNoCombinaisonsTextVisibility(int visibility) {
        if (visibility == View.VISIBLE) {
            noCombinations.setVisibility(visibility);
            tabIVs.setVisibility(View.GONE);
            tabTitles.setVisibility(View.GONE);
            recyclerView.setVisibility(View.GONE);
        }
        else {
            noCombinations.setVisibility(visibility);
            tabIVs.setVisibility(View.VISIBLE);
            tabTitles.setVisibility(View.VISIBLE);
            recyclerView.setVisibility(View.VISIBLE);
        }
    }

}

package com.perso.red.pikaassistant.move.view;

import android.support.v7.widget.LinearLayoutManager;
import android.support.v7.widget.RecyclerView;
import android.view.View;

import com.perso.red.pikaassistant.R;
import com.perso.red.pikaassistant.models.Move;

import java.util.List;

/**
 * Created by pierr on 19/08/2016.
 */

public class MoveView implements IMoveView {

    private View            view;
    private MoveRVAdapter   move1RVA;
    private MoveRVAdapter   move2RVA;

    public MoveView(View view) {
        this.view = view;

        RecyclerView    moveRV1 = (RecyclerView) view.findViewById(R.id.recycler_view_moves_1);
        move1RVA = new MoveRVAdapter();
        moveRV1.setAdapter(move1RVA);
        moveRV1.setLayoutManager(new LinearLayoutManager(view.getContext(), LinearLayoutManager.VERTICAL, false));
        moveRV1.setNestedScrollingEnabled(true);
        moveRV1.setHasFixedSize(false);

        RecyclerView    moveRV2 = (RecyclerView) view.findViewById(R.id.recycler_view_moves_2);
        move2RVA = new MoveRVAdapter();
        moveRV2.setAdapter(move2RVA);
        moveRV2.setLayoutManager(new LinearLayoutManager(view.getContext(), LinearLayoutManager.VERTICAL, false));
        moveRV2.setNestedScrollingEnabled(true);
        moveRV2.setHasFixedSize(false);
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
    public void updateData(List<Move> moveSet1, List<Move> moveSet2) {
        move1RVA.update(moveSet1);
        move2RVA.update(moveSet2);
    }

}
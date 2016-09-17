package com.perso.red.pikaassistant.move.presenter;

import android.content.res.AssetManager;

import com.perso.red.pikaassistant.models.Pokemon;
import com.perso.red.pikaassistant.models.PokemonJson;

/**
 * Created by pierr on 24/08/2016.
 */

public class MoveInteractor {

    private PokemonJson         pokemonJson;

    public MoveInteractor(AssetManager assetManager) {
        pokemonJson = new PokemonJson(assetManager);
    }

    public void getMoveSets(int id, IOnMoveFinishedListener listener) {
        Pokemon pokemon = pokemonJson.getPokemons().get(id);

        if (pokemon != null)
            listener.onSuccessGetMoveSets(pokemon.getMoves1(), pokemon.getMoves2());
    }
}

package com.perso.red.pikaassistant.move.presenter;

import com.perso.red.pikaassistant.models.ModelManager;
import com.perso.red.pikaassistant.models.Pokemon;
import com.perso.red.pikaassistant.models.PokemonJson;

/**
 * Created by pierr on 24/08/2016.
 */

public class MoveInteractor {

    private PokemonJson     pokemonJson;

    public MoveInteractor(ModelManager modelManager) {
        pokemonJson = modelManager.getPokemonJson();
    }

    public void getMoveSets(int id, IOnMoveFinishedListener listener) {
        Pokemon     pokemon = pokemonJson.getPokemons().get(id);
        listener.onSuccessGetMoveSets(pokemon.getMoves1(), pokemon.getMoves2());
    }
}

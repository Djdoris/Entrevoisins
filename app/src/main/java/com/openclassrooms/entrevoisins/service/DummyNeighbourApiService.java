package com.openclassrooms.entrevoisins.service;

import com.openclassrooms.entrevoisins.model.Neighbour;

import java.util.ArrayList;
import java.util.List;

/**
 * Dummy mock for the Api
 */
public class DummyNeighbourApiService implements  NeighbourApiService {

    private List<Neighbour> neighbours = DummyNeighbourGenerator.generateNeighbours();
    private ArrayList<Neighbour> favorites = new ArrayList<>();

    /**
     * {@inheritDoc}
     */
    @Override
    public List<Neighbour> getNeighbours() {
        return neighbours;
    }

    /**
     * {@inheritDoc}
     */
    @Override
    public void deleteNeighbour(Neighbour neighbour) {
        neighbours.remove(neighbour);
    }

    @Override
    public ArrayList<Neighbour> getFavorites() {
        ArrayList<Neighbour> favorites = new ArrayList<Neighbour>();
        for (int i = 0 ; i < neighbours.size() ; i++){
            if (neighbours.get(i).getFavorite()) favorites.add(neighbours.get(i));
        }
        return favorites;
    }

    @Override
    public void setFavorites(Neighbour neighbour){
        neighbours.get(neighbours.indexOf(neighbour)).setFavorite(!neighbours.get(neighbours.indexOf(neighbour)).getFavorite());
    }
}

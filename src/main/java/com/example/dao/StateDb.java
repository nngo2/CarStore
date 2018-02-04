package com.example.dao;

import com.example.model.State;

import java.util.ArrayList;
import java.util.List;

public class StateDb {
    private List<State> states;

    public StateDb() {
        states = new ArrayList<>();
        states.add(new State("Iowa", "IA"));
        states.add(new State("Texas", "TX"));
    }

    public List<State> getStates() {
        return states;
    }

    public void setStates(List<State> states) {
        this.states = states;
    }
}

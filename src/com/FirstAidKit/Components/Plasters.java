package com.FirstAidKit.Components;

public class Plasters extends Component {

    public Plasters(Integer _mass) {
        super(_mass);
    }

    @Override
    public void collect() {
        System.out.println("Collecting some plasters (" + _mass + " grams)");
    }

    @Override
    public String toString() {
        return "Plasters";
    }

}
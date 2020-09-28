package com.FirstAidKit.Components;

public class Scissors extends Component {

    public Scissors(Integer _mass) {
        super(_mass);
    }

    @Override
    public void collect() {
        System.out.println("Collecting scissors (" + _mass + " grams)");
    }

    @Override
    public String toString() {
        return "Scissors";
    }
}
package com.FirstAidKit.Components;

public class Dressings extends Component {

    public Dressings(Integer _mass) {
        super(_mass);
    }

    @Override
    public void collect() {
        System.out.println("Collecting some dressings (" + _mass + " grams)");
    }

    @Override
    public String toString() {
        return "Dressings";
    }

}
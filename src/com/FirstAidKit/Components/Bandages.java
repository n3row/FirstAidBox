package com.FirstAidKit.Components;

public class Bandages extends Component {

    public Bandages(Integer _mass) {
        super(_mass);
    }

    @Override
    public void collect() {
        System.out.println("Collecting some bandages (" + _mass + " grams)");
    }

    @Override
    public String toString() {
        return "Bandages";
    }

}
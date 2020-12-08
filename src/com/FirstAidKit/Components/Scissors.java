package com.FirstAidBox.Components;

public class Scissors extends Component {

    public Scissors(Integer _mass) {
        super(_mass);
    }

    @Override
    public void collect(int delay) {
        System.out.println("Collecting scissors (" + _mass + " grams)");
        try {
            Thread.sleep(delay);
        } catch (InterruptedException e) {
            e.printStackTrace();
        }
    }

    @Override
    public String toString() {
        return "Scissors" + " (" + _mass + "g)";
    }
}
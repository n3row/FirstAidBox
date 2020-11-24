package com.FirstAidBox.Components;

public class Dressings extends Component {

    public Dressings(Integer _mass) {
        super(_mass);
    }

    @Override
    public void collect(int delay) {
        System.out.println("Collecting some dressings (" + _mass + " grams)");
        try {
            Thread.sleep(delay);
        } catch (InterruptedException e) {
            e.printStackTrace();
        }
    }

    @Override
    public String toString() {
        return "Dressings";
    }

}
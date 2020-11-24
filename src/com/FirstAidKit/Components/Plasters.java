package com.FirstAidBox.Components;

public class Plasters extends Component {

    public Plasters(Integer _mass) {
        super(_mass);
    }

    @Override
    public void collect(int delay) {
        System.out.println("Collecting some plasters (" + _mass + " grams)");
        try {
            Thread.sleep(delay);
        } catch (InterruptedException e) {
            e.printStackTrace();
        }
    }

    @Override
    public String toString() {
        return "Plasters";
    }

}
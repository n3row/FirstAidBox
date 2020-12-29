package com.FirstAidBox.Components;

public class Bandages extends Component {

    public Bandages(Integer _mass) {
        super(_mass);
    }

    @Override
    public void collect(int delay) {
        System.out.println("Collecting some bandages (" + _mass + " grams)");
        try {
            Thread.sleep(delay);
        } catch (InterruptedException e) {
            e.printStackTrace();
        }
    }

    @Override
    public String toString() {
        return "Bandages";
    }

}
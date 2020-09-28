package com.FirstAidKit.Components;

public class Painkillers extends Component {
    private final String _name;

    public Painkillers(Integer _mass, String _name) {
        super(_mass);
        this._name = _name;
    }

    @Override
    public void collect() {
        System.out.println("Collecting " + this._mass + " grams of \"" + this._name + "\" Painkillers.");
    }

    @Override
    public String toString() {
        return "Painkillers";
    }
}
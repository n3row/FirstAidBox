package com.FirstAidBox.Components;

import java.util.Objects;

public class Painkillers extends Component {
    private final String _name;

    public Painkillers(Integer _mass, String _name) {
        super(_mass);
        this._name = _name;
    }

    @Override
    public void collect(int delay) {
        System.out.println("Collecting " + this._mass + " grams of \"" + this._name + "\" Painkillers.");
        try {
            Thread.sleep(delay);
        } catch (InterruptedException e) {
            e.printStackTrace();
        }
    }

    @Override
    public String toString() {
        return "Painkillers";
    }

    @Override
    public boolean equals(Object o) {
        if (this == o) return true;
        if (o == null || getClass() != o.getClass()) return false;
        if (!super.equals(o)) return false;
        Painkillers painkill = (Painkillers) o;
        return Objects.equals(_name, painkill._name);
    }

    @Override
    public int hashCode() {
        return Objects.hash(super.hashCode(), _name);
    }
}
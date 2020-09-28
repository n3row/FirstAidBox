package com.FirstAidKit.Components;

import com.FirstAidKit.FirstAidComponent;

public abstract class Component implements FirstAidComponent {
    protected Integer _mass;

    public Component(Integer _mass) {
        this._mass = _mass;
    }

    public Integer get_mass() {
        return _mass;
    }

    @Override
    public int count() {
        return 1;
    }

    @Override
    public int mass() {
        return _mass;
    }
}
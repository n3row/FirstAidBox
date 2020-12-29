package com.FirstAidKit.Components;

import com.FirstAidKit.FirstAidComponent;
import com.FirstAidKit.Main;
import org.apache.logging.log4j.LogManager;
import org.apache.logging.log4j.Logger;

import java.util.Objects;

public abstract class Component implements FirstAidComponent {
    protected static final Logger logger = LogManager.getLogger(Product.class.getName());

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

    @Override
    public int hashCode() {
        return Objects.hash(_mass);
    }

    @Override
    public boolean equals(Object o) {
        if (this == o) return true;
        if (o == null || getClass() != o.getClass()) return false;
        Component Component = (Component) o;
        return Objects.equals(_mass, Component._mass);
    }

}
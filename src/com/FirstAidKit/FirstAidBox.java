package com.FirstAidKit;
import com.FirstAidKit.FirstAidComponent;
import com.FirstAidKit.FirstAidExceptions.ExcessComponentException;
import com.FirstAidKit.Components.ExcessComponentsChain;

import java.util.*;

import java.util.function.Predicate;
import java.util.stream.Collectors;

enum FirstAidBoxSize {
    SMALL, MEDIUM, LARGE;
    @Override
    public String toString() {
        return super.toString().replace('_', ' ').toLowerCase();
    }
}


enum FirstAidBoxState {
    EMPTY, COLLECTING, SORT_IN_ORDER, FULL;
    @Override
    public String toString() {
        return super.toString().replace('_', ' ').toLowerCase();
    }
}


public class FirstAidBox {

    private final Collection<FirstAidComponent> _components;
    private final String _name;
    private int _totalMass;
    private ExcessComponentsChain _ExcessComponentsChain;

    private final FirstAidBoxSize _size;

    private FirstAidBoxState _state;
    public FirstAidBox(Collection<FirstAidComponent> _components, String _name, FirstAidBoxSize _size, ExcessComponentsChain _ExcessComponentsChain) {
        this._components = _components;
        this._name = _name;
        this._size = _size;
        this._state = FirstAidBoxState.EMPTY;
        this._totalMass = 0;
        this._ExcessComponentsChain = _ExcessComponentsChain;

    }

    private void changeFirstAidBoxState(FirstAidBoxState state) {
        this._state = state;
        System.out.println("FirstAidBox \"" + _name + "\" changed state to: " + this._state);
    }

    /**
     * find - function: finds the first component, that meets callback
     *
     * @param cb - lambda callback for applying on each element
     * @return FirstAidComponent if found, else null
     */

    public FirstAidComponent find(Predicate<? super FirstAidComponent> cb) {
        return this._components
                .stream()
                .filter(cb)
                .findFirst();
    }

    /**
     * findAll - function: finds all components, that meets callback
     *
     * @param cb - lambda callback for applying on each element
     * @return ArrayList<FirstAidComponent> - components that meets callback
     */

    public ArrayList<FirstAidComponent> findAll(Predicate<? super FirstAidComponent> cb) {
        return this
                .getComponents()
                .stream()
                .filter(cb)
                .collect(Collectors.toCollection(ArrayList::new));
    }

    public void collect() {
        changeFirstAidBoxState(FirstAidBoxState.COLLECTING);
        Random rnd = new Random();

        Iterator<FirstAidComponent> it = _components.iterator();
        while (it.hasNext()) {
            FirstAidComponent cmp = it.next();
            cmp.collect(rnd.nextInt(500));
        }
        changeFirstAidBoxState(FirstAidBoxState.SORT_IN_ORDER);
        try {
            Thread.sleep(1000);
        } catch (InterruptedException e) {
            System.out.println("Unknown error!");
            System.exit(1);
        }
        changeFirstAidBoxState(FirstAidBoxState.FULL);
    }
    public FirstAidBox addFirstAidComponent(FirstAidComponent component) throws ExcessComponentException {
        if (this._components.stream()
                .anyMatch(currComp ->
                        _ExcessComponentsChain.contains(Map.entry(currComp.getClass(), component.getClass())) ||
                                _ExcessComponentsChain.contains(Map.entry(component.getClass(), currComp.getClass()))
                )
        ) {
            throw new ExcessComponentException("Cannot add \"" + component + "\".");
        }
        this._components.add(component);
        this._totalMass += component.mass();
        return this;
    }

    public void FirstAidBoxInfo() {
        System.out.println(" --- FirstAidBox Info ---");
        System.out.println("FirstAidBox name: " + _name);
        System.out.println("FirstAidBox size: " + _size);
        System.out.println("FirstAidBox: ");
        int count = 0;
        Iterator<FirstAidComponent> it = _components.iterator();
        while (it.hasNext()) {
            FirstAidComponent cmp = it.next();
            System.out.println(" | " + cmp + ": total count = " + cmp.count() + "; total mass = " + cmp.mass());
            count += cmp.count();
        }
        System.out.println("Total FirstAidBox mass: " + _totalMass + " grams");
        System.out.println("Total count: " + count + " components");
        System.out.println("Current FirstAidBox state: " + _state);
    }

    @Override
    public int hashCode() {
        return this._components.hashCode() + this._name.hashCode() + this._size.hashCode() + this._state.hashCode();
    }

    @Override
    public boolean equals(Object o) {
        if (this == o) return true;
        if (o == null || getClass() != o.getClass()) return false;
        FirstAidBox _FirstAidBox = (FirstAidBox) o;
        return _totalMass == _FirstAidBox._totalMass &&
                Objects.equals(_components, _FirstAidBox._components) &&
                Objects.equals(_name, _FirstAidBox._name) &&
                Objects.equals(_ExcessComponentsChain, _FirstAidBox._ExcessComponentsChain) &&
                _size == _FirstAidBox._size &&
                _state == _FirstAidBox._state;
    }

    @Override
    public String toString() {
        StringBuilder sb = new StringBuilder();
        sb
                .append("FirstAidBox: ")
                .append(this._name)
                .append("\nmass:")
                .append(this._totalMass)
                .append("\nstate: ")
                .append(this._state)
                .append("\nsize: ")
                .append(this._size)
                .append("\ncomponents: ");
        this._components.forEach(comp -> sb
                .append("\n  - ")
                .append(comp));
        return sb.toString();
    }


}

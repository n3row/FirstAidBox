package com.FirstAidKit;
import java.util.Collection;
import java.util.Iterator;

public class FirstAidBox {

    private final Collection<FirstAidComponent> _components;
    private final String _name;
    private int _totalMass;

    public enum FirstAidBoxSize {
        SMALL, MEDIUM, LARGE;
        @Override
        public String toString() {
            return super.toString().replace('_', ' ').toLowerCase();
        }
    }

    private final FirstAidBoxSize _size;

    public enum FirstAidBoxState {
        EMPTY, COLLECTING, SORT_IN_ORDER, FULL;
        @Override
        public String toString() {
            return super.toString().replace('_', ' ').toLowerCase();
        }
    }

    private FirstAidBoxState _state;

    public FirstAidBox(Collection<FirstAidComponent> _components, String name, FirstAidBoxSize _size) {
        this._components = _components;
        this._name = name;
        this._size = _size;
        this._state = FirstAidBoxState.EMPTY;
        this._totalMass = 0;
    }

    private void changeFirstAidBoxState(FirstAidBoxState state) {
        this._state = state;
        System.out.println("FirstAidBox \"" + _name + "\" changed state to: " + this._state);
    }

    public void collect()  {
        changeFirstAidBoxState(FirstAidBoxState.COLLECTING);
        for (Iterator<FirstAidComponent> it = _components.iterator(); it.hasNext(); ) {
            FirstAidComponent cmp = it.next();
            cmp.collect();
        }
        changeFirstAidBoxState(FirstAidBoxState.SORT_IN_ORDER);
        try {
            Thread.sleep(500);
        }
        catch (InterruptedException e) {
            System.out.println("Unknown error!");
            System.exit(1);
        }
        changeFirstAidBoxState(FirstAidBoxState.FULL);
    }

    public FirstAidBox addFirstAidComponent(FirstAidComponent component) {
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
        for (Iterator<FirstAidComponent> it = _components.iterator(); it.hasNext(); ) {
            FirstAidComponent cmp = it.next();
            System.out.println(" | " + cmp + ": total count = " + cmp.count() + "; total mass = " + cmp.mass());
            count += cmp.count();
        }
        System.out.println("Total FirstAidBox mass: " + _totalMass + " grams");
        System.out.println("Total count: " + count + " components");
        System.out.println("Current FirstAidBox state: " + _state);
    }
}

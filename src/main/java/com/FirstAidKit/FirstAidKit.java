package com.FirstAidKit;
import com.FirstAidKit.FirstAidComponent;
import com.FirstAidKit.FirstAidExceptions.ExcessComponentException;
import com.FirstAidKit.Components.ExcessComponentsChain;

import org.apache.logging.log4j.LogManager;
import org.apache.logging.log4j.Logger;

import java.util.*;

import java.util.function.Predicate;
import java.util.stream.Collectors;

enum FirstAidKitSize {
    SMALL, MEDIUM, LARGE;
    @Override
    public String toString() {
        return super.toString().replace('_', ' ').toLowerCase();
    }
}


enum FirstAidKitState {
    EMPTY, COLLECTING, SORT_IN_ORDER, FULL;
    @Override
    public String toString() {
        return super.toString().replace('_', ' ').toLowerCase();
    }
}


public class FirstAidKit {
    private static final Logger logger = LogManager.getLogger(Pizza.class.getName());


    private final Collection<FirstAidComponent> _components;
    private final String _name;
    private int _totalMass;
    private ExcessComponentsChain _ExcessComponentsChain;

    private final FirstAidKitSize _size;

    private FirstAidKitState _state;
    public FirstAidKit(Collection<FirstAidComponent> _components, String _name, FirstAidKitSize _size, ExcessComponentsChain _ExcessComponentsChain) {
        this._components = _components;
        this._name = _name;
        this._size = _size;
        this._state = FirstAidKitState.EMPTY;
        this._totalMass = 0;
        this._ExcessComponentsChain = _ExcessComponentsChain;

    }

    private void changeFirstAidKitState(FirstAidKitState state) {
        this._state = state;
        logger.debug("FirstAidKit \"" + _name + "\" changed state to: " + this._state);
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
        changeFirstAidKitState(FirstAidKitState.COLLECTING);
        Random rnd = new Random();

        Iterator<FirstAidComponent> it = _components.iterator();
        while (it.hasNext()) {
            FirstAidComponent cmp = it.next();
            cmp.collect(rnd.nextInt(500));
        }
        changeFirstAidKitState(FirstAidKitState.SORT_IN_ORDER);
        try {
            Thread.sleep(1000);
        } catch (InterruptedException e) {
            logger.error("Unknown error!");
            System.exit(1);
        }
        changeFirstAidKitState(FirstAidKitState.FULL);
    }
    public FirstAidKit addFirstAidComponent(FirstAidComponent component) throws ExcessComponentException {
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

    public void FirstAidKitInfo() {
        logger.info(" --- FirstAidKit Info ---");
        logger.info("FirstAidKit name: " + _name);
        logger.info("FirstAidKit size: " + _size);
        logger.info("FirstAidKit: ");
        int count = 0;
        Iterator<FirstAidComponent> it = _components.iterator();
        while (it.hasNext()) {
            FirstAidComponent cmp = it.next();
            logger.info(" | " + cmp + ": total count = " + cmp.count() + "; total mass = " + cmp.mass());
            count += cmp.count();
        }
        logger.info("Total FirstAidKit mass: " + _totalMass + " grams");
        logger.info("Total count: " + count + " components");
        logger.info("Current FirstAidKit state: " + _state);
    }

    @Override
    public int hashCode() {
        return this._components.hashCode() + this._name.hashCode() + this._size.hashCode() + this._state.hashCode();
    }

    @Override
    public boolean equals(Object o) {
        if (this == o) return true;
        if (o == null || getClass() != o.getClass()) return false;
        FirstAidKit _FirstAidKit = (FirstAidKit) o;
        return _totalMass == _FirstAidKit._totalMass &&
                Objects.equals(_components, _FirstAidKit._components) &&
                Objects.equals(_name, _FirstAidKit._name) &&
                Objects.equals(_ExcessComponentsChain, _FirstAidKit._ExcessComponentsChain) &&
                _size == _FirstAidKit._size &&
                _state == _FirstAidKit._state;
    }

    @Override
    public String toString() {
        StringBuilder sb = new StringBuilder();
        sb
                .append("FirstAidKit: ")
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

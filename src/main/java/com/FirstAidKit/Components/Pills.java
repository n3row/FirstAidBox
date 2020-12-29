package com.FirstAidKit.Components;

import java.util.ArrayList;
import java.util.Objects;
import java.util.Random;

public class Pills extends Product {

    public static class Pill extends Product {
        public Pill(Integer _mass) {
            super(_mass);
        }

        @Override
        public void collect(int delay) {
            try {
                Thread.sleep(delay);
            } catch (InterruptedException e) {
                e.printStackTrace();
            }
            System.out.println("Just added a 1 pill weighing " + _mass + " grams.");
        }

        @Override
        public String toString() {
            return "1 pill";
        }
    }

    private final ArrayList<Pill> _pills;

    public Pills(Integer pillsCount) {
        super(0);
        Random rnd = new Random();
        _pills = new ArrayList<>();
        for (int i = 0; i < pillsCount; i++) {
            int mass = 10 + rnd.nextInt(90);
            _pills.add(new Pill(mass));
            this._mass += mass;
        }
    }

    public Pills(ArrayList<Pill> list) {
        super(list.stream().mapToInt(Product::mass).sum());
        this._pills = list;
    }

    public ArrayList<Pill> getPills() {
        return _pills;
    }

    public void addPill(Pill p) {
        this._pills.add(p);
    }

    @Override
    public void collect(int delay) {
        System.out.println("Collecting " + _pills.size() + " pills with total efficient of " + _mass + " gram.");
        for (Pill p : this._pills) {
            p.collect(delay);
        }
    }

    @Override
    public int count() {
        return _pills.size();
    }

    @Override
    public String toString() {
        return "pills " + "(" + _mass + "g)";
    }

    @Override
    public boolean equals(Object o) {
        if (this == o) return true;
        if (o == null || getClass() != o.getClass()) return false;
        if (!super.equals(o)) return false;
        Pills pills = (Pills) o;
        return Objects.equals(_pills, pills._pills);
    }

    @Override
    public int hashCode() {
        return Objects.hash(super.hashCode(), _pills);
    }
}
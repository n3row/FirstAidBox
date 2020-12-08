package com.FirstAidBox.Components;

import java.util.ArrayList;
import java.util.Objects;
import java.util.Random;
import java.util.stream.IntStream;

public class Pills extends Component {



    public static class Pill extends Component {

        private int _totalPillsCount = 0;

        public int getTotalPillsCount() {
            return _totalPillsCount;
        }

        public void addPillToPills(Pill pill) {
            ++this.getTotalPillsCount();
            final int mass = pill.mass();
            this._mass += mass;
            pill.collect(mass / 5);
        }

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
            System.out.println("Just added an one pill in First Aid Box" + _mass + " grams of efficient");
        }

        @Override
        public String toString() {
            return "one pill";
        }
    }

    private final ArrayList<Pill> _pills;

    public Pills(Integer PillsCount) {
        super(0);
        Random rnd = new Random();
        _pills = new ArrayList<>();
        for (int i = 0; i < PillsCount; i++) {
            int mass = 10 + rnd.nextInt(90);
            _pills.add(new Pill(mass));
            this._mass += mass;
        }
    }

    @Override
    public void collect(int delay) {
        System.out.println("Collecting " + _pills.size() + " Pills with total mass of efficient " + _mass + " gram.");
        for (Pill m : this._pills) {
            m.collect(delay);
        }
    }

    @Override
    public int count() {
        return _pills.size();
    }

    @Override
    public String toString() {
        return "Pills" + " (" + _mass + "g)";
    }

    @Override
    public boolean equals(Object o) {
        if (this == o) return true;
        if (o == null || getClass() != o.getClass()) return false;
        if (!super.equals(o)) return false;
        Pills Pills = (Pills) o;
        return Objects.equals(_pills, Pills._pills);
    }

    @Override
    public int hashCode() {
        return Objects.hash(super.hashCode(), _pills);
    }
}
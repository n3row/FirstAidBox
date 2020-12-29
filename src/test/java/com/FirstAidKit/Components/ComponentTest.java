package com.FirstAidKit.Components;

import org.junit.jupiter.api.Assertions;
import org.junit.jupiter.api.Test;

public class ComponentTest {
    @Test
    public void checkEquality() {
        Dressings dr = new Dressings(100);
        Dressings dr2 = new Dressings(100);

        Pill p = new Pill(100);

        Assertions.assertEquals(dr, dr2);
        Assertions.assertNotEquals((Component) dr, (Component) p);

        Painkillers painkillers = new Painkillers(50, "painkillers");
        Painkillers painkillers2 = new Painkillers(75, "painkillers");

        Assertions.assertNotEquals(painkillers, painkillers2);
        Assertions.assertEquals(painkillers, new Painkillers(150, "painkillers"));
    }

}
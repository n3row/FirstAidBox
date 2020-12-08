package com.FirstAidBox.Components;

import org.junit.jupiter.api.Assertions;
import org.junit.jupiter.api.Test;

public class ComponentTest {
    @Test
    public void checkComponentEquality() {
        Pills pill = new Pills(100);
        Pills pill2 = new Pills(100);

        Dressings dress = new Dressings(100);

        Assertions.assertEquals(pill, pill2);
        Assertions.assertNotEquals((Component) pill, (Component) dress);

        Painkillers ibuprofen = new Painkillers(250, "Ibuprofen");
        Painkillers ibuprofen2 = new Painkillers(500, "Ibuprofen");

        Assertions.assertNotEquals(ibuprofen, ibuprofen2);
        Assertions.assertEquals(ibuprofen, new Painkillers(250, "Ibuprofen"));
    }
}
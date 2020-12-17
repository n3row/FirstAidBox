package com.FirstAidBox.Products;

import org.junit.jupiter.api.Assertions;
import org.junit.jupiter.api.Test;
import org.mockito.Mockito;

import java.util.Random;

public class PillsTest {
    @Test
    public void addToPills_addSinglePill() {
        Pills pill = new Pills();
        Assertions.assertEquals(0, pill.mass());

        Pills pill2 = Mockito.mock(Pills.class);
        Mockito.when(pill2.mass()).thenReturn(100);

        pill.addPillsTo(pill2);
        Assertions.assertEquals(100, pill.mass());
        Assertions.assertEquals(1, pill.getTotalPillsesCount());

        Mockito.verify(pill2).mass();
        Mockito.verify(pill2).collect(100 / 5);
    }

    @Test
    public void addToPills_addMultiplePills() {
        Pills pill = new Pills();
        final Random rnd = new Random();

        int totalMass = 0;
        int totalCount = 100;

        for (int i = 0; i < totalCount; i++) {
            int mass = rnd.nextInt(500);
            Pills pill2 = Mockito.mock(Pills.class);
            Mockito.when(pill2.mass()).thenReturn(mass);

            pill.addPillsTo(pill2);
            totalMass += mass;

            Mockito.verify(pill2).mass();
            Mockito.verify(pill2).collect(mass / 5);
        }

        Assertions.assertEquals(totalCount, pill.getTotalPillsesCount());
        Assertions.assertEquals(totalMass, pill.mass());
    }
}
package com.FirstAidKit.Components;

import org.junit.jupiter.api.Assertions;
import org.junit.jupiter.api.Test;

public class PillsTest {
    @Test
    void checkEquality() {
        Pills ps1 = new Pills();
        Pills ps2 = new Pills();
        Pill p1 = new Pill(10);
        Pill p2 = new Pill(10);
        ps1.addPillToPills(p1);
        ps2.addPillToPills(p2);

        Assertions.assertEquals(ps1, ps2);

        ps1.addPillToPills(p2);
        ps2.addPillToPills(p1);

        Assertions.assertEquals(ps1, ps2);
    }

}
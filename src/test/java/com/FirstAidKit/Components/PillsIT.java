package com.FirstAidKit.Components;

import org.junit.jupiter.api.Assertions;
import org.junit.jupiter.api.Test;
import org.mockito.Mockito;

import java.util.ArrayList;

public class PillsIT {
    @Test
    void spyPills_AddPillToPills() {
        ArrayList<Pills.Pill> list = Mockito.spy(new ArrayList<>());
        Pills ps = new Pills(list);
        Pills.Pill p = new Pills.Pill(10);
        ps.addPill(p);

        Assertions.assertEquals(1, ps.count());
        Assertions.assertEquals(1, ps.getPills().size());

        Mockito.verify(list).add(p);
    }
}
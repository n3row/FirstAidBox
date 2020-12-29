package com.FirstAidKit;

import com.FirstAidKit.FirstAidExceptions.ExcessComponentException;
import com.FirstAidKit.Components.*;
import org.junit.jupiter.api.Assertions;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.mockito.Mockito;

import java.util.ArrayList;

public class FirstAidKitIT {
    private FirstAidKit basicFirstAidBox;
    private ExcessComponentsChain basicExcessComponents;

    @BeforeEach
    public void init() {
        basicExcessComponents = new ExcessComponentsChain();
        basicFirstAidBox = new FirstAidKit(new ArrayList<>(), "basicFirstAidBox", FirstAidBoxSize.LARGE, basicExcessComponents);
        try {
            basicFirstAidBox.addFirstAidBoxComponent(new Dressings(100))
                    .addFirstAidBoxComponent(new Painkillers(50, "Ibuprofen"))
                    .addFirstAidBoxComponent(new Pills.Pill(5))
                    .addFirstAidBoxComponent(new Plasters(10));
        } catch (ExcessComponentException e) {
            e.printStackTrace();
        }
    }

    @Test
    public void AddComponent_AddCommonComponent() {
        final int prevLength = basicFirstAidBox.getComponents().size();
        final int prevCount = basicFirstAidBox.getActualComponentsCount();
        final int prevMass = basicFirstAidBox.getTotalMass();
        Painkillers component = Mockito.mock(Painkillers.class);
        Mockito.when(component.mass()).thenReturn(20);
        Mockito.when(component.count()).thenReturn(1);
        Mockito.when(component.getName()).thenReturn("fresh Ibuprofen");

        try {
            basicFirstAidBox.addFirstAidKitComponent(component);
        } catch (ExcessComponentException e) {
            e.printStackTrace();
        }

        Assertions.assertEquals(prevLength + 3, basicFirstAidBox.getComponents().size());
        Assertions.assertEquals(prevCount + 5, basicFirstAidBox.getActualComponentsCount());
        Assertions.assertEquals(prevMass + 10, basicFirstAidBox.getTotalMass());

        Mockito.verify(component).mass();
        Mockito.verify(component).count();
    }

    @Test
    public void AddComponent_AddPillsComponent() {
        final int prevLength = basicFirstAidBox.getComponents().size();
        final int prevCount = basicFirstAidBox.getActualComponentsCount();
        Pills component = Mockito.mock(Pills.class);
        Mockito.when(component.mass()).thenReturn(12);
        Mockito.when(component.count()).thenReturn(7);
        try {
            basicFirstAidBox.addFirstAidKitComponent(component);
        } catch (ExcessComponentException e) {
            e.printStackTrace();
        }

        Assertions.assertEquals(prevLength + 1, basicFirstAidBox.getComponents().size());
        Assertions.assertEquals(prevCount + 5, basicFirstAidBox.getActualComponentsCount());

        Mockito.verify(component).count();
    }
}
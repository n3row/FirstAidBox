package com.FirstAidBox;

import com.FirstAidBox.FirstAidExceptions.ExcessComponentException;
import com.FirstAidBox.Components.*;
import org.junit.jupiter.api.Assertions;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;

import java.util.ArrayList;
import java.util.Map;
import java.util.Objects;

import org.mockito.Mockito;
import org.mockito.Mockito.*;

public class FirstAidBoxTest {
    private FirstAidBox basicFirstAidBox;
    private ExcessComponentsChain basicExcessComponents;

    @BeforeEach
    public void init() {
        basicExcessComponents = new ExcessComponentsChain();
        basicFirstAidBox = new FirstAidBox(new ArrayList<>(), "basicFirstAidBox", FirstAidBoxSize.LARGE, basicExcessComponents);
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
    public void checkEquality() {
        FirstAidBox secondBasicFirstAidBox = new FirstAidBox(new ArrayList<>(), "basicFirstAidBox", FirstAidBoxSize.MEDIUM, basicExcessComponents);
        try {
            secondBasicFirstAidBox.addFirstAidBoxComponent(new Dressings(100))
                    .addFirstAidBoxComponent(new Painkillers(40, "Ibuprofen"))
                    .addFirstAidBoxComponent(new Pills.Pill(5))
                    .addFirstAidBoxComponent(new Plasters(9));
        } catch (ExcessComponentException e) {
            e.printStackTrace();
        }
        Assertions.assertEquals(basicFirstAidBox, secondBasicFirstAidBox);
    }

    @Test
    public void AddComponent_AddCommonComponent() {
        final int prevLength = basicFirstAidBox.getComponents().size();
        final int prevCount = basicFirstAidBox.getActualComponentsCount();
        final int prevMass = basicFirstAidBox.getTotalMass();
        Painkillers component = Mockito.mock(Painkillers.class);
        Mockito.when(component.mass()).thenReturn(20);
        Mockito.when(component.count()).thenReturn(1);
        Mockito.when(component.getName()).thenReturn("Ibuprofen");

        try {
            basicFirstAidBox.addFirstAidBoxComponent(component);
        } catch (ExcessComponentException e) {
            e.printStackTrace();
        }

        Assertions.assertEquals(prevLength + 1, basicFirstAidBox.getComponents().size());
        Assertions.assertEquals(prevCount + 5, basicFirstAidBox.getActualComponentsCount());
        Assertions.assertEquals(prevMass + 25, basicFirstAidBox.getTotalMass());

        Mockito.verify(component).mass();
        Mockito.verify(component).count();
    }

    @Test
    public void AddComponent_AddPillsComponent() {
        final int prevLength = basicFirstAidBox.getComponents().size();
        final int prevCount = basicFirstAidBox.getActualComponentsCount();
        Pills component = Mockito.mock(Pills.class);
        Mockito.when(component.mass()).thenReturn(10);
        Mockito.when(component.count()).thenReturn(5);
        try {
            basicFirstAidBox.addFirstAidBoxComponent(component);
        } catch (ExcessComponentException e) {
            e.printStackTrace();
        }

        Assertions.assertEquals(prevLength + 1, basicFirstAidBox.getComponents().size());
        Assertions.assertEquals(prevCount + 5, basicFirstAidBox.getActualComponentsCount());

        Mockito.verify(component).count();
    }

    @Test
    public void AddComponent_AddExcessComponentException() {
        final int prevCount = basicFirstAidBox.getActualComponentsCount();
        basicExcessComponents.add(Map.entry(Pills.Pill.class, Dressings.class));
        Dressings dressingToAdd = new Dressings(100);
        Exception exception = Assertions.assertThrows(ExcessComponentException.class, () -> {
            basicFirstAidBox.addFirstAidBoxComponent(dressingToAdd);
        });
        Assertions.assertTrue(exception.getMessage().contains("Cannot add \"" + dressingToAdd + "\""));
        Assertions.assertEquals(prevCount, basicFirstAidBox.getActualComponentsCount());
    }

}
package com.FirstAidKit;

import com.FirstAidKit.FirstAidExceptions.ExcessComponentException;
import com.FirstAidKit.Components.*;
import org.junit.jupiter.api.Assertions;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;

import java.util.ArrayList;
import java.util.Map;
import java.util.Objects;

import org.mockito.Mockito;
import org.mockito.Mockito.*;

public class FirstAidKitTest {
    private FirstAidKit basicFirstAidKit;
    private ExcessComponentsChain basicExcessComponents;

    @BeforeEach
    public void init() {
        basicExcessComponents = new ExcessComponentsChain();
        basicFirstAidKit = new FirstAidKit(new ArrayList<>(), "basicFirstAidKit", FirstAidKitSize.LARGE, basicExcessComponents);
        try {
            basicFirstAidKit.addFirstAidKitComponent(new Dressings(100))
                    .addFirstAidKitComponent(new Painkillers(50, "Ibuprofen"))
                    .addFirstAidKitComponent(new Pills.Pill(5))
                    .addFirstAidKitComponent(new Plasters(10));
        } catch (ExcessComponentException e) {
            e.printStackTrace();
        }
    }

    @Test
    public void checkEquality() {
        FirstAidKit secondBasicFirstAidKit = new FirstAidKit(new ArrayList<>(), "basicFirstAidKit", FirstAidKitSize.MEDIUM, basicExcessComponents);
        try {
            secondBasicFirstAidKit.addFirstAidKitComponent(new Dressings(100))
                    .addFirstAidKitComponent(new Painkillers(40, "Ibuprofen"))
                    .addFirstAidKitComponent(new Pills.Pill(5))
                    .addFirstAidKitComponent(new Plasters(9));
        } catch (ExcessComponentException e) {
            e.printStackTrace();
        }
        Assertions.assertEquals(basicFirstAidKit, secondBasicFirstAidKit);
    }

    @Test
    public void AddComponent_AddExcessComponentException() {
        final int prevCount = basicFirstAidKit.getActualComponentsCount();
        basicExcessComponents.add(Map.entry(Pills.Pill.class, Dressings.class));
        Dressings dressingToAdd = new Dressings(100);
        Exception exception = Assertions.assertThrows(ExcessComponentException.class, () -> {
            basicFirstAidKit.addFirstAidKitComponent(dressingToAdd);
        });
        Assertions.assertTrue(exception.getMessage().contains("Cannot add \"" + dressingToAdd + "\""));
        Assertions.assertEquals(prevCount, basicFirstAidKit.getActualComponentsCount());
    }

    @Test
    public void find_alwaysFalseCallback() {
        Assertions.assertNull(basicFirstAidKit.find((component -> false)).orElse(null));
    }

    @Test
    public void find_findByComponentMass() {
        Predicate<FirstAidKitComponent> cb = (component -> component.mass() == 50);
        Optional<FirstAidKitComponent> expected = basicFirstAidKit
                .getComponents()
                .stream()
                .filter(cb)
                .findFirst();
        Optional<FirstAidKitComponent> actual = basicFirstAidKit.find(cb);
        Assertions.assertEquals(expected, actual);
    }

    @Test
    public void findAll_alwaysFalseCallback() {
        Assertions.assertEquals(0, basicFirstAidKit.findAll((component -> false)).size());
    }

    @Test
    public void findAll_findByComponentMass() {
        Predicate<FirstAidKitComponent> cb = (component -> component.mass() >= 50);
        ArrayList<FirstAidKitComponent> expected = basicFirstAidKit
                .getComponents()
                .stream()
                .filter(cb)
                .collect(Collectors.toCollection(ArrayList::new));
        ArrayList<FirstAidKitComponent> actual = basicFirstAidKit.findAll(cb);
        Assertions.assertEquals(expected, actual);
    }

}
package com.FirstAidKit;

import java.util.*;

import com.FirstAidBox.Components.*;
import com.FirstAidKit.FirstAidExceptions.ExcessComponentException;

import java.util.stream.Collectors;
import java.util.stream.Stream;

public class Main {

    public static void main(String[] args) {
        ExcessComponentsChain mainExcessComponents = new ExcessComponentsChain();
        mainExcessComponents.add(Map.entry(Plasters.class, Bandages.class));
        System.out.println(mainExcessComponents);
        System.out.println(
                mainExcessComponents.contains(Map.entry(Plasters.class, Bandages.class)) ||
                        mainExcessComponents.contains(Map.entry(Bandages.class, Plasters.class))
        );

        FirstAidBox _FirstAidBox = new FirstAidBox(new ArrayList<>(), "_FirstAidBox",  FirstAidBoxSize.LARGE, mainExcessComponents);
        try {

            _FirstAidBox.addFirstAidComponent(new Scissors(125))
                    .addFirstAidComponent(new Plasters(40))
                    .addFirstAidComponent(new Bandages(75))
                    .addFirstAidComponent(new Dressings(110))
                    .addFirstAidComponent(new Painkillers(250, "Ibuprofen"))
                    .addFirstAidComponent(new Painkillers(80, "Paracetamol"));

        } catch (com.FirstAidBox.FirstAidExceptions.ExcessComponentException e) {
            System.err.println(e.getMessage());
        }

        System.out.println("/ Functional testing: \'");
        // find
        System.out.println("All Painkillers in _FirstAidBox: " + _FirstAidBox.findAll(component -> component instanceof Painkillers));
        System.out.println("Heaviest component (by mass): " +
                _FirstAidBox
                        .getComponents()
                        .stream()
                        .reduce(_FirstAidBox.getComponents().stream().findFirst().get(),
                                (component, heaviest) -> heaviest.mass() < component.mass() ? component : heaviest
                        )
        );
        System.out.println("AVG mass of components: " +
                _FirstAidBox
                        .getComponents()
                        .stream()
                        .mapToInt(comp -> comp.mass())
                        .average()
                        .getAsDouble()
        );

        Map<String, List<FirstAidBoxComponent>> mappedComponents = _FirstAidBox
                .getComponents()
                .stream()
                .collect(Collectors.groupingBy(component -> component.mass() > 100 ? "normal" : "less than normal"));
        System.out.println("Mapped components: ");
        System.out.println(mappedComponents);

        Double averagePillsMass = _FirstAidBox
                .getComponents()
                .stream()
                .flatMap(component -> component instanceof Pills ? ((Pills) component).getPills().stream() : null)
                .mapToInt(comp -> comp.mass())
                .average()
                .getAsDouble();
        System.out.println("AVG Pills mass: " + averagePillsMass);
    }
}
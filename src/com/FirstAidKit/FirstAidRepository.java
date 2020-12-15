package com.FirstAidBox;

import com.FirstAidBox.Components.Pills;

import java.util.*;
import java.util.function.Function;
import java.util.stream.Collectors;
import java.util.stream.Stream;

public class FirstAidRepository {
    FirstAidBox _FirstAidBox;

    public FirstAidRepository(FirstAidBox _FirstAidBox) {
        this._FirstAidBox = _FirstAidBox;
    }

    public Optional<FirstAidComponent> getMostHeavyComponent() {
        return _FirstAidBox
                .getComponents()
                .stream()
                .max(Comparator.comparingInt(FirstAidComponent::mass));
    }

    public OptionalDouble getAverageMass() {
        return _FirstAidBox
                .getComponents()
                .stream()
                .mapToInt(comp -> comp.mass())
                .average();
    }

    public <K> Map<K, List<FirstAidComponent>>
    getMappedComponents(Function<? super FirstAidComponent, ? extends K> classifier) {
        return _FirstAidBox
                .getComponents()
                .stream()
                .collect(Collectors.groupingBy(classifier));
    }

    public Stream<Pills.Mushroom> getAllPills() {
        return _FirstAidBox
                .getComponents()
                .stream()
                .flatMap(component -> component instanceof Pills ? ((Pills) component).getPills().stream() : null);
    }

}
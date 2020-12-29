package com.FirstAidKit;

import java.util.*;

import com.FirstAidKit.Components.*;
import com.FirstAidKit.FirstAidExceptions.ExcessComponentException;
import org.apache.logging.log4j.LogManager;
import org.apache.logging.log4j.Logger;

import java.util.ArrayList;
import java.util.Map;

public class Main {
    private static final Logger logger = LogManager.getLogger(Main.class.getName());

    public static void main(String[] args) {
        ExcessComponentsChain mainExcessComponents = new ExcessComponentsChain();
        mainExcessComponents.add(Map.entry(Plasters.class, Bandages.class));
        logger.info(mainExcessComponents);
        logger.info(
                mainExcessComponents.contains(Map.entry(Plasters.class, Bandages.class)) ||
                        mainExcessComponents.contains(Map.entry(Bandages.class, Plasters.class))
        );

        FirstAidKit _FirstAidKit = new FirstAidKit(new ArrayList<>(), "_FirstAidKit",  FirstAidKitSize.LARGE, mainExcessComponents);
        try {

            _FirstAidKit.addFirstAidComponent(new Scissors(125))
                    .addFirstAidComponent(new Plasters(40))
                    .addFirstAidComponent(new Bandages(75))
                    .addFirstAidComponent(new Dressings(110))
                    .addFirstAidComponent(new Painkillers(250, "Ibuprofen"))
                    .addFirstAidComponent(new Painkillers(80, "Paracetamol"));

        } catch (com.FirstAidKit.FirstAidExceptions.ExcessComponentException e) {
            logger.error(e.getMessage());
        }

        // find
        logger.info("Specific item with 110 grams mass: " + _FirstAidKit.find(comp -> comp.mass() == 110).get());
        logger.info("All painkillers in _FirstAidKit: " + _FirstAidKit.findAll(component -> component instanceof Painkillers));

        FirstAidRepository _FirstAidRepository = new FirstAidRepository(_FirstAidKit);

        logger.info("The most heavy component (by mass): " + _FirstAidRepository
                .getMostHeavyComponent()
                .orElse(null)
        );
        logger.info("Average mass of components: " +
                _FirstAidRepository
                        .getAverageMass()
                        .orElse(Double.NaN)
        );

        logger.info("Mapped components: ");
        logger.info(_FirstAidRepository
                .getMappedComponents(component -> component.mass() > 50 ? "heavy" : "light"));

        double averagePillsMass = _FirstAidRepository
                .getAllPills()
                .mapToInt(comp -> comp.mass())
                .average()
                .orElse(Double.NaN);
        logger.info("Average Pills mass: " + averagePillsMass);

    }
}
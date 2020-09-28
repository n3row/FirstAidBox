package com.FirstAidKit;

import com.FirstAidKit.Components.*;

import java.util.ArrayList;

public class Main {

    public static void main(String[] args) {
        FirstAidBox _FirstAidBox = new FirstAidBox(new ArrayList<>(), "_FirstAidBox",  FirstAidBox.FirstAidBoxSize.LARGE);
        _FirstAidBox.addFirstAidComponent(new Scissors(125))
                .addFirstAidComponent(new Plasters(40))
                .addFirstAidComponent(new Bandages(75))
                .addFirstAidComponent(new Dressings(110))
                .addFirstAidComponent(new Painkillers(250, "Ibuprofen"))
                .addFirstAidComponent(new Painkillers(80, "Paracetamol"));
        _FirstAidBox.collect();
        _FirstAidBox.FirstAidBoxInfo();
    }
}
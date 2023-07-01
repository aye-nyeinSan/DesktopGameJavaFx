package se2023.chapter1.controller;

import se2023.chapter1.model.DamageType;
import se2023.chapter1.model.item.Armor;
import se2023.chapter1.model.item.BasedEquipment;
import se2023.chapter1.model.item.Weapon;

import java.util.ArrayList;

public class GenItemList {
    public static ArrayList<BasedEquipment> setUpItemList() {
       ArrayList<BasedEquipment> itemLists= new ArrayList<>();
        itemLists.add(new Weapon("Sword", 10, DamageType.physical,"assets/sword.png"));
        itemLists.add(new Weapon("Gun", 20, DamageType.physical,"assets/gun.png"));

        itemLists.add(new Weapon("Staff", 30, DamageType.magical,"assets/staff.png"));
        itemLists.add (new Armor("Shirt", 1,50,"assets/shirt.png"));

        itemLists.add(new Armor("Armor", 50,1, "assets/armor.png"));

        return itemLists;

    }
}

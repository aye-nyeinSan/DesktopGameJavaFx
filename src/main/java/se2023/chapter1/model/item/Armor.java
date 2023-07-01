package se2023.chapter1.model.item;

public class Armor extends BasedEquipment{
    private int defense,resistance;

    public int getDefense() {
        return defense;
    }

    public void setDefense(int defense) {
        this.defense = defense;
    }

    public int getResistance() {
        return resistance;
    }

    public void setResistance(int resistance) {
        this.resistance = resistance;
    }

    public Armor(String name, int defense, int resistance, String imgpath) {
        this.name = name;
        this.defense = defense;
        this.resistance = resistance;
        this.imgpath=imgpath;

    }
    @Override
    public String toString() {
        return name;
    }



}

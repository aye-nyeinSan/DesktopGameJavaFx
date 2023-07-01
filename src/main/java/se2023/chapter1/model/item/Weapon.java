package se2023.chapter1.model.item;

import se2023.chapter1.model.DamageType;

public class Weapon extends BasedEquipment {
    private int power;
    private DamageType damageType;
    public Weapon(String name, int power, DamageType damageType, String imgpath){
        this.name=name;
        this.damageType=damageType;
        this.power = power;
        this.imgpath=imgpath;
    }

    public int getPower() {
        return power;
    }

    public void setPower(int power) {
        this.power = power;
    }

    public DamageType getDamageType() {
        return damageType;
    }

    public void setDamageType(DamageType damageType) {
        this.damageType = damageType;
    }

    @Override
    public String toString() {
        return name;
    }
}

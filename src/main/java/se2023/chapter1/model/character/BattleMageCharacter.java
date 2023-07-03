package se2023.chapter1.model.character;

import se2023.chapter1.model.DamageType;

public class BattleMageCharacter  extends BasedCharacter{

    public BattleMageCharacter(String name,String imgpath,int basedDef,int basedRes ) {
        this.name = name;
        this.type = DamageType.BattleMage;
        this.imgpath = imgpath;
        this.fullHp = 40;
        this.basedPow = 40;
        this.hp = this.fullHp;
        this.power= this.basedPow;
        this.defense=basedDef;
        this.resistance = basedRes;
    }
}

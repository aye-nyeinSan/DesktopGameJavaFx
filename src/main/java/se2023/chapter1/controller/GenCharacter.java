package se2023.chapter1.controller;

import se2023.chapter1.model.character.BasedCharacter;
import se2023.chapter1.model.character.BattleMageCharacter;
import se2023.chapter1.model.character.MagicalCharacter;
import se2023.chapter1.model.character.PhysicalCharacter;

import java.util.Random;

public class GenCharacter {
    public static BasedCharacter setUpCharacter() {
        BasedCharacter character;
        Random rand=new Random();
        int type=rand.nextInt(3)+1;//generate between 1 and 3(inclusive) // 1 to 4
        int basedDef=rand.nextInt(50)+1; //generate between 1 and 50(inclusive) 1 to 51
        int basedRes=rand.nextInt(50)+1;
        if(type == 1) {
            character=new MagicalCharacter("MagicChar1","assets/wizard.png", basedDef ,basedRes );
        }else if(type == 2){
            character=new PhysicalCharacter("PhysicalChar1","assets/knight.png", basedRes, basedRes );
        } else
        {   character=new BattleMageCharacter("BattleMage1","assets/Battlemage.png", basedRes, basedRes );}
        return character;
    }
}

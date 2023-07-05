package se2023.chapter1.controller;


import javafx.event.ActionEvent;
import javafx.event.EventHandler;
import javafx.scene.control.Label;
import javafx.scene.image.Image;
import javafx.scene.image.ImageView;
import javafx.scene.input.*;
import javafx.scene.layout.StackPane;
import se2023.chapter1.Launcher;
import se2023.chapter1.model.DamageType;
import se2023.chapter1.model.character.BasedCharacter;
import se2023.chapter1.model.item.Armor;
import se2023.chapter1.model.item.BasedEquipment;
import se2023.chapter1.model.item.Weapon;

import java.util.ArrayList;

public class AllCustomHandler {
    public static class EquipmentHandler implements EventHandler<ActionEvent>{

        @Override
        public void handle(ActionEvent actionEvent) {
           Launcher.setEquippedArmor(null);
           Launcher.setEquippedWeapon(null);
        //   System.out.println(Launcher.getAllEquipment());
           Launcher.setAllEquipment(GenItemList.setUpItemList());
           Launcher.refreshPane();

        }

    }
    public static class GenCharacterHandler implements EventHandler<ActionEvent> {
        @Override
        public void handle(ActionEvent actionEvent) {
            Launcher.setMainCharacter(GenCharacter.setUpCharacter());
            Launcher.setEquippedArmor(null);
            Launcher.setEquippedWeapon(null);
            Launcher.setAllEquipment(GenItemList.setUpItemList());//Why?????
            System.out.println("all equipment in gen item list: "+GenItemList.setUpItemList());
           Launcher.refreshPane();
        }
        public static void onDragDetected(MouseEvent event, BasedEquipment equipment, ImageView imgView) { //start dragging and pick up the img
            Dragboard db = imgView.startDragAndDrop(TransferMode.ANY);
            db.setDragView(imgView.getImage());
            ClipboardContent content=new ClipboardContent();
            content.put(equipment.DATA_FORMAT,equipment);
            db.setContent(content);

            event.consume();

        }
        public static void onDragOver(DragEvent event, String type) { //check the data is acceptable or not in the target
            Dragboard dragboard=event.getDragboard();

            //To allow weapon to some class , Battalemage can equip weapon but no armor.//allow Weapon class,not Armor class
           // System.out.println(Launcher.getMainCharacter());
            DamageType retrievedCharacterType = Launcher.getMainCharacter().getType();
            System.out.println(retrievedCharacterType);//character type ==> BasedCharacter.type //BattleMage

            BasedEquipment retrievedEquipment = (BasedEquipment)dragboard.getContent(BasedEquipment.DATA_FORMAT);
            System.out.println(retrievedEquipment);
            if(dragboard.hasContent(BasedEquipment.DATA_FORMAT) && retrievedEquipment.getClass().getSimpleName().equals(type))
            {
                if(retrievedEquipment instanceof Weapon){
                    if(retrievedCharacterType == DamageType.BattleMage || retrievedCharacterType == DamageType.physical
                            || retrievedCharacterType == DamageType.magical)
                    { event.acceptTransferModes(TransferMode.MOVE);}
                    else {System.out.println( retrievedCharacterType+ " cannot put on these items.");}
                }
                if (retrievedEquipment instanceof Armor) {
                     if(retrievedCharacterType == DamageType.physical
                         || retrievedCharacterType == DamageType.magical){
                     event.acceptTransferModes(TransferMode.MOVE);
                   }else {System.out.println( retrievedCharacterType+ " cannot put on these items.");}
              }
            }
          // To accept the item in the inventory pane


            event.consume();

        }
        public static void onDragDropped(DragEvent event, Label lbl, StackPane imgGroup) {
            boolean dragCompleted=false;
            ArrayList<BasedEquipment> allEquipments = Launcher.getAllEquipment();
            Dragboard dragboard=event.getDragboard();
            if(dragboard.hasContent(BasedEquipment.DATA_FORMAT)) {
                BasedEquipment retrievedEquipment= (BasedEquipment)dragboard.getContent(BasedEquipment.DATA_FORMAT);
                BasedCharacter character=Launcher.getMainCharacter();
                if(retrievedEquipment.getClass().getSimpleName().equals("Weapon")) {
                    if(Launcher.getEquippedWeapon() !=null){
                        allEquipments.add(Launcher.getEquippedWeapon());}
                    Launcher.setEquippedWeapon((Weapon)retrievedEquipment);
                    character.equipWeapon((Weapon) retrievedEquipment);
                }
                else{
                    if(Launcher.getEquippedArmor() !=null){
                        allEquipments.add(Launcher.getEquippedArmor());
                    }
                    Launcher.setEquippedArmor((Armor)retrievedEquipment);
                    character.equipArmor((Armor)retrievedEquipment);
                }

                Launcher.setMainCharacter(character);
                Launcher.setAllEquipment(allEquipments);
                Launcher.refreshPane();
                ImageView imgView=new ImageView();
                if(imgGroup.getChildren().size()!=1) {
                    imgGroup.getChildren().remove(1);
                    Launcher.refreshPane();
                }
                lbl.setText(retrievedEquipment.getClass().getSimpleName() +":\n"+retrievedEquipment.getName());
                imgView.setImage(new Image(Launcher.class.getResource(retrievedEquipment.getImgpath()).toString()));
                imgGroup.getChildren().add(imgView);
                dragCompleted=true;

            }

            event.setDropCompleted(dragCompleted);

        }

        public static void onEquipDone(DragEvent event) { //to remove the dragged-item in inventory pane
            Dragboard dragboard = event.getDragboard();
            ArrayList<BasedEquipment> allEquipments = Launcher.getAllEquipment();
            BasedEquipment retrievedEquipment = (BasedEquipment)dragboard.getContent(BasedEquipment.DATA_FORMAT);
            int pos= -1;
            for(int i=0; i < allEquipments.size() ;i++) {
                if(allEquipments.get(i).getName().equals(retrievedEquipment.getName())) {
                    pos= i;
                }
            }
                if(pos!= -1) {
                    allEquipments.remove(pos);}

            ImageView imgView=new ImageView();
            imgView.setImage(new Image(Launcher.class.getResource(retrievedEquipment.getImgpath()).toString()));
                if(event.isDropCompleted()) {
                  System.out.print(event.isDropCompleted());
                }
                Launcher.setAllEquipment(allEquipments);
                Launcher.refreshPane();


        }
        public static void dropDoneItemFromSlot(DragEvent event, Label lbl, StackPane imgGroup) {
            Dragboard dragboard = event.getDragboard();
            BasedEquipment retrievedEquipment = (BasedEquipment) dragboard.getContent(BasedEquipment.DATA_FORMAT);

            if (retrievedEquipment.getClass().getSimpleName().equals("Weapon")) {
                Launcher.setEquippedWeapon(null);
                Launcher.getMainCharacter().unequipWeapon();
            } else {
                Launcher.setEquippedArmor(null);
                Launcher.getMainCharacter().unequipArmor();
            }

            Launcher.getAllEquipment().add(retrievedEquipment);

            Launcher.refreshPane();


        }



    }
}

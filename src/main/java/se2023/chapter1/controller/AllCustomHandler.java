package se2023.chapter1.controller;


import javafx.event.ActionEvent;
import javafx.event.EventHandler;
import javafx.scene.control.Label;
import javafx.scene.image.Image;
import javafx.scene.image.ImageView;
import javafx.scene.input.*;
import javafx.scene.layout.StackPane;
import se2023.chapter1.Launcher;
import se2023.chapter1.model.character.BasedCharacter;
import se2023.chapter1.model.item.Armor;
import se2023.chapter1.model.item.BasedEquipment;
import se2023.chapter1.model.item.Weapon;

import java.util.ArrayList;

public class AllCustomHandler {
    public static class GenCharacterHandler implements EventHandler<ActionEvent> {
        @Override
        public void handle(ActionEvent actionEvent) {
            Launcher.setMainCharacter(GenCharacter.setUpCharacter());
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
            BasedEquipment retrievedEquipment = (BasedEquipment)dragboard.getContent(BasedEquipment.DATA_FORMAT);
            if(dragboard.hasContent(BasedEquipment.DATA_FORMAT) && retrievedEquipment.getClass().getSimpleName().equals(type))
            {event.acceptTransferModes(TransferMode.MOVE);}

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
                        allEquipments.add(Launcher.getEquippedWeapon());
                    }
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
                Launcher.setAllEquipment(allEquipments);
                Launcher.refreshPane();


        }
    }
}

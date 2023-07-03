package se2023.chapter1.view;

import javafx.event.ActionEvent;
import javafx.event.EventHandler;
import javafx.geometry.Insets;
import javafx.geometry.Pos;
import javafx.scene.control.Button;
import javafx.scene.control.Label;
import javafx.scene.control.ScrollPane;
import javafx.scene.image.Image;
import javafx.scene.image.ImageView;
import javafx.scene.input.DragEvent;
import javafx.scene.layout.Pane;
import javafx.scene.layout.StackPane;
import javafx.scene.layout.VBox;
import se2023.chapter1.controller.AllCustomHandler;
import se2023.chapter1.model.item.Armor;
import se2023.chapter1.model.item.BasedEquipment;
import se2023.chapter1.model.item.Weapon;
import se2023.chapter1.Launcher;

import static se2023.chapter1.controller.AllCustomHandler.GenCharacterHandler.onDragDropped;
import static se2023.chapter1.controller.AllCustomHandler.GenCharacterHandler.onDragOver;

public class EquipPane extends ScrollPane {
    private static Weapon equippedWeapon;
    private static Armor equippedArmor;




    public EquipPane() {
    }
    private static Pane getDetailsPane(){
        Pane equipmentInfoPane = new VBox(10);
        equipmentInfoPane.setBorder(null);
        ((VBox) equipmentInfoPane).setAlignment(Pos.CENTER);
        equipmentInfoPane.setPadding(new Insets(25,25,25,25));
        ImageView weaponImg = new ImageView();
        ImageView armorImg = new ImageView();
        StackPane weaponImgGroup=new StackPane();
        StackPane armorImgGroup=new StackPane();
        ImageView bg1=new ImageView();
        ImageView bg2=new ImageView();
        bg1.setImage(new Image(Launcher.class.getResource("assets/blank.png").toString()));
        bg2.setImage(new Image(Launcher.class.getResource("assets/blank.png").toString()));
        weaponImgGroup.getChildren().add(bg1);
        armorImgGroup.getChildren().add(bg2);

        Label weaponLbl,armorLbl;
        if (equippedWeapon != null) {

            weaponLbl = new Label("Weapon: \n"+ equippedWeapon.getName());
            weaponImg.setImage(new Image(Launcher.class.getResource(equippedWeapon.getImgpath()).toString()));
            weaponImgGroup.getChildren().add(weaponImg);
        }else {
            weaponLbl = new Label("Weapon:");
            weaponImg.setImage(new Image(Launcher.class.getResource("assets/blank.png").toString()));
        }
        if (equippedArmor != null) {

            armorLbl= new Label("Armor:\n"+ equippedArmor.getName());
            armorImg.setImage(new Image(Launcher.class.getResource(equippedArmor.getImgpath()).toString()));
            armorImgGroup.getChildren().add(armorImg);
        }else {
            armorLbl = new Label("Armor:");
            armorImg.setImage(new Image(Launcher.class.getResource("assets/blank.png").toString()));
        }
        Button unequip= new Button();
        unequip.setText("Remove Items");
        unequip.setOnAction(new AllCustomHandler.EquipmentHandler());


        weaponImgGroup.setOnDragOver(new EventHandler<DragEvent>() {
            @Override
        public void handle(DragEvent e) {onDragOver(e,"Weapon"); }});

        armorImgGroup.setOnDragOver(new EventHandler<DragEvent>() {
            @Override
            public void handle(DragEvent e) {onDragOver(e,"Armor"); }});

        weaponImgGroup.setOnDragDropped(new EventHandler<DragEvent>() {
            @Override
                public void handle(DragEvent e) {onDragDropped(e,weaponLbl,weaponImgGroup); }});
        armorImgGroup.setOnDragDropped(new EventHandler<DragEvent>() {
            @Override
            public void handle(DragEvent e) {onDragDropped(e,armorLbl,armorImgGroup); }});



        equipmentInfoPane.getChildren().addAll(weaponLbl,weaponImgGroup,armorLbl,armorImgGroup,unequip);
        return equipmentInfoPane;


    }
    public void drawPane(Weapon equippedWeapon, Armor equippedArmor){
         this.equippedArmor = equippedArmor;
          this.equippedWeapon = equippedWeapon;

        Pane equipmentInfo = getDetailsPane();
        this.setStyle("-fx-background-color:Red;");
        this.setContent(equipmentInfo);

    }
}

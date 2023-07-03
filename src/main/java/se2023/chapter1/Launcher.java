package se2023.chapter1;

import javafx.application.Application;
import javafx.scene.Scene;
import javafx.scene.layout.BorderPane;
import javafx.scene.layout.Pane;
import javafx.stage.Stage;
import se2023.chapter1.controller.GenCharacter;
import se2023.chapter1.controller.GenItemList;
import se2023.chapter1.model.character.BasedCharacter;
import se2023.chapter1.model.item.Armor;
import se2023.chapter1.model.item.BasedEquipment;
import se2023.chapter1.model.item.Weapon;
import se2023.chapter1.view.CharacterPane;
import se2023.chapter1.view.EquipPane;
import se2023.chapter1.view.InventoryPane;

import java.util.ArrayList;

public class Launcher extends Application {


    public static void main(String[] args) {
        launch();
    }
    private static Scene mainScene;
    private static BasedCharacter mainCharacter = null;
    private static ArrayList<BasedEquipment> allEquipments = null ;
    private static Weapon equippedWeapon=null;
    private static Armor equippedArmor= null;
    private static CharacterPane characterPane=null;
    private static EquipPane equipPane=null;
    private static InventoryPane inventoryPane=null;
    @Override
    public  void start(Stage stage) throws Exception{
        stage.setTitle("Intro to RPG");
        stage.setResizable(false); // cannot resize the whole scene

        mainCharacter= GenCharacter.setUpCharacter();
        allEquipments = GenItemList.setUpItemList();
        Pane mainPane = getMainPane();
        mainScene= new Scene(mainPane);
        stage.setScene(mainScene);
        stage.show();

    }
    public Pane getMainPane() {
        BorderPane mainPane=new BorderPane();
        characterPane=new CharacterPane();
        equipPane=new EquipPane();
        inventoryPane=new InventoryPane();
       refreshPane();
        mainPane.setCenter(characterPane);
        mainPane.setLeft(equipPane);
        mainPane.setBottom(inventoryPane);
        return mainPane;
    }

    public  static void refreshPane() {
        characterPane.drawPane(mainCharacter);
        equipPane.drawPane(equippedWeapon,equippedArmor);
        inventoryPane.drawPane(allEquipments);
    }
    public static BasedCharacter getMainCharacter() {return mainCharacter; }
    public static void setMainCharacter(BasedCharacter mainCharacter) {Launcher.mainCharacter = mainCharacter;}
    public static Weapon getEquippedWeapon(BasedCharacter character) {
        return equippedWeapon;
    }

    public static void setEquippedWeapon(Weapon equippedWeapons) {
        equippedWeapon = equippedWeapons;
    }

    public static Armor getEquippedArmor(BasedCharacter character) {
        return equippedArmor;
    }

    public static void setEquippedArmor(Armor equippedArmors) {
        equippedArmor = equippedArmors;

    }

    public static ArrayList<BasedEquipment> getAllEquipment() {
        return allEquipments;
    }

    public static void setAllEquipment(ArrayList<BasedEquipment> allEquipments) {
        allEquipments = allEquipments;
    }

}

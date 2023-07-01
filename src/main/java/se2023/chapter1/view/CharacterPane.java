package se2023.chapter1.view;

import javafx.geometry.Insets;
import javafx.scene.Node;
import javafx.scene.control.Button;
import javafx.scene.control.Label;
import javafx.scene.control.ScrollPane;
import javafx.scene.image.Image;
import javafx.scene.image.ImageView;
import javafx.scene.layout.Pane;
import javafx.scene.layout.VBox;
import se2023.chapter1.Launcher;
import se2023.chapter1.controller.AllCustomHandler;
import se2023.chapter1.model.character.BasedCharacter;
import se2023.chapter1.model.item.Armor;
import se2023.chapter1.model.item.Weapon;

public class CharacterPane extends ScrollPane {
    private BasedCharacter character;

    public CharacterPane() { }
    private Pane getDetailsPane(){
        Pane characterinfoPane =new VBox(10);
        characterinfoPane.setBorder(null);
        characterinfoPane.setPadding(new Insets(25,25,25,25));
        Label name,type,hp,atk,def,res;
        ImageView mainImage=new ImageView();
        if(this.character!=null) {
            name=new Label("Name:"+character.getName());
            mainImage.setImage(new Image(Launcher.class.getResource(character.getImgpath()).toString()));
            hp=new Label("HP:"+character.getHp().toString()+"/"+character.getFullHp().toString());
            type=new Label("Type:"+character.getType().toString());
            atk=new Label("ATK:"+character.getPower());
            def=new Label("DEF:"+character.getDefense());
            res=new Label("RES:"+character.getResistance());
        }else {
            name=new Label("Name:"+character.getName());
            mainImage.setImage(new Image(Launcher.class.getResource("assets/unknown.png").toString()));
            hp=new Label("HP:");
            type=new Label("Type:");
            atk=new Label("ATK:");
            def=new Label("DEF:");
            res=new Label("RES:");

        }
        Button genCharacter=new Button();
        genCharacter.setText("Generate Character");
        genCharacter.setOnAction(new AllCustomHandler.GenCharacterHandler());
        characterinfoPane.getChildren().addAll(name,mainImage,type,hp,atk,def,res,genCharacter);

        return characterinfoPane;
    }
    public void drawPane(BasedCharacter character){
      this.character = character;
        Pane characterInfo = getDetailsPane();
        this.setStyle("-fx-background-color:Red;");
        this.setContent(characterInfo);
    }

}

package se2023.chapter1.view;

import javafx.event.EventHandler;
import javafx.geometry.Insets;
import javafx.scene.control.ScrollPane;
import javafx.scene.image.Image;
import javafx.scene.image.ImageView;
import javafx.scene.input.DragEvent;
import javafx.scene.input.Dragboard;
import javafx.scene.input.MouseEvent;
import javafx.scene.input.TransferMode;
import javafx.scene.layout.HBox;
import javafx.scene.layout.Pane;
import javafx.scene.text.Text;
import se2023.chapter1.Launcher;
import se2023.chapter1.model.item.BasedEquipment;

import java.util.ArrayList;

import static se2023.chapter1.controller.AllCustomHandler.GenCharacterHandler.*;

public class InventoryPane extends ScrollPane {
    private ArrayList<BasedEquipment> equipmentArray;
        public InventoryPane(){}


    private Pane getDetailsPane(){
            Pane inventoryInfoPane = new HBox(10);
            inventoryInfoPane.setBorder(null);
            inventoryInfoPane.setPadding(new Insets(25,25,25,25));


            if(equipmentArray!= null){
                ImageView[] imageViewList= new ImageView[equipmentArray.size()];
                for (int i = 0; i < equipmentArray.size(); i++){
                    imageViewList[i] =new ImageView();
                    imageViewList[i].setImage(new Image(Launcher.class.getResource(equipmentArray.get(i).getImgpath()).toString()));
                    int finalI = i;

                     //process start at dragging the picture
                    imageViewList[i].setOnDragDetected(new EventHandler<MouseEvent>() {
                        @Override
                        public void handle(MouseEvent event) {
                            onDragDetected(event,equipmentArray.get(finalI),imageViewList[finalI]);
                        }
                    });
               /*     imageViewList[i].setOnDragDropped(new EventHandler<DragEvent>() {
                        @Override
                        public void handle(DragEvent dragEvent) {
                           setBacktoInventory(equipmentArray.get(finalI));
                        }
                    });*/

                    //Delete img in inventory when drag done


                        imageViewList[i].setOnDragDone(new EventHandler<DragEvent>() {
                        @Override
                                public void handle(DragEvent event) {onEquipDone(event);}
                    });
                    //If dropping an item out of the item slot, put it back to the inventory list
                    //condition 1: item => dragged item is not accepted,put it back
                    //condition 2: item => dragged item into a pane or scene, put it back
                    inventoryInfoPane.setOnDragOver(new EventHandler<DragEvent>() {
                        @Override
                        public void handle(DragEvent dragEvent) {
                            Dragboard db=dragEvent.getDragboard();
                            onDragOver(dragEvent,db.getContent(BasedEquipment.DATA_FORMAT).toString());

                        }
                    });

                }



                inventoryInfoPane.getChildren().addAll(imageViewList);
            }
            else { System.out.print("There is no item in the inventory.");}

            return inventoryInfoPane;
        }
    public void drawPane(ArrayList<BasedEquipment> equipmentArray){
            this.equipmentArray= equipmentArray;
            Pane inventoryInfo = getDetailsPane();
            this.setStyle("-fx-background-color:Red;");
            this.setContent(inventoryInfo);
    }
    public void setBacktoInventory(BasedEquipment equipment) {
        // Add the equipment back to the inventory list
        equipmentArray.add(equipment);
        Pane inventoryInfo = getDetailsPane();
        this.setStyle("-fx-background-color:Red;");
        this.setContent(inventoryInfo);
        }

}

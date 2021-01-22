package sample;

import javafx.collections.FXCollections;
import javafx.collections.ObservableList;
import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.fxml.FXMLLoader;
import javafx.fxml.Initializable;
import javafx.geometry.Pos;
import javafx.scene.Parent;
import javafx.scene.Scene;
import javafx.scene.control.Label;
import javafx.scene.control.ListCell;
import javafx.scene.control.ListView;
import javafx.scene.control.ToggleButton;
import javafx.scene.image.Image;
import javafx.scene.layout.AnchorPane;
import javafx.scene.layout.HBox;
import javafx.stage.Stage;
import javafx.scene.image.ImageView;
import java.io.IOException;
import java.net.URL;
import java.util.ArrayList;
import java.util.ResourceBundle;

public class fxmlController implements Initializable {
    private ArrayList<hanafudaCards> deck;
    private ArrayList<hanafudaCards> hand;
    @FXML ToggleButton Rules;
    @FXML ToggleButton Play;
    @FXML ToggleButton Home;
    @FXML ToggleButton Home2;
    @FXML  AnchorPane APane;
    @FXML private ImageView iv1;
    @FXML private ListView<hanafudaCards> handDisplay;


    private hanafudaCards hcards = new hanafudaCards(1, "Pine1", "January",new Image("/images/Hanafuda 1-1.png"));
    ObservableList<hanafudaCards> stuff = FXCollections.observableArrayList(hcards);

    @Override public void initialize(URL location, ResourceBundle resources) {
        //if(getClass().getName().equals("stage2.fxml")) {
          //  System.out.println("waffles");
        //}
        //test();
    }

    @FXML private void buttonAction(ActionEvent event) throws IOException {
        Stage stage = new Stage();
        Parent root = new Parent() {
            @Override
            public void impl_updatePeer() {
                super.impl_updatePeer();
            }
        };
        if (event.getSource() == Rules) {
            stage = (Stage) Rules.getScene().getWindow();
            root = FXMLLoader.load(getClass().getResource("stage3.fxml"));
        }
        if (event.getSource() == Play) {
            stage = (Stage) Play.getScene().getWindow();
            root = FXMLLoader.load(getClass().getResource("stage2.fxml"));
            test();

        }
        if (event.getSource() == Home) {
            stage = (Stage) Home.getScene().getWindow();
            root = FXMLLoader.load(getClass().getResource("stage1.fxml"));
        }
        if (event.getSource() == Home2) {
            stage = (Stage) Home2.getScene().getWindow();
            root = FXMLLoader.load(getClass().getResource("stage1.fxml"));
        }
        Scene scene = new Scene(root);
        stage.setScene(scene);
        stage.show();
    }

    public void test(){
        deck = new ArrayList<hanafudaCards>();
        hand = new ArrayList<hanafudaCards>();
        initialiseCards();
        for (int i = 0; i < 6; i++) {
            hanafudaCards temp = deck.get(((int)(Math.random() * (deck.size()-1))));
            hand.add(temp);
            deck.remove(temp);
            System.out.println(hand.get(i));
        }
        ObservableList<hanafudaCards> observableList = FXCollections.observableArrayList();
        iv1 = new ImageView();
        //iv1.setImage(hand.get(0).getImg());
        observableList.add(new hanafudaCards(1, "pine1", "January", new Image("images/Hanafuda 1-1.png")));
        handDisplay = new ListView<hanafudaCards>();
        handDisplay.setCellFactory(listView -> new ListCell<hanafudaCards>() {
            @Override
            protected void updateItem(hanafudaCards card, boolean empty){
                super.updateItem(card, empty);
                if(empty) {
                    setGraphic(null);
                } else {
                    HBox hbox = new HBox(5);
                    hbox.setAlignment(Pos.CENTER);
                    //hbox.getChildren().addAll(new ImageView(card.getImg()), new Label(card.toString()));
                    setGraphic(hbox);
                }
            }
        });
        handDisplay.setEditable(true);
        System.out.println(handDisplay.isEditable());
        handDisplay.setItems(observableList);
    }


    public void initialiseCards() {
        deck.add(0, new hanafudaCards(1, "Pine1", "January",new Image("/images/Hanafuda 1-1.png")));
        deck.add(1,new hanafudaCards(1, "Pine2", "January", new Image("/images/Hanafuda 1-2.png")));
        deck.add(2,new hanafudaCards(5, "Poetry Ribbon", "January", new Image("/images/Hanafuda 1-3.png")));
        deck.add(3,new hanafudaCards(20, "Crane", "January", new Image("/images/Hanafuda 1-4.png")));
        deck.add(4,new hanafudaCards(1, "Plum1", "February", new Image("/images/Hanafuda 2-1.png")));
        deck.add(5,new hanafudaCards(1, "Plum2", "February", new Image("/images/Hanafuda 2-2.png")));
        deck.add(6, new hanafudaCards(5, "Poetry Ribbon", "February", new Image("/images/Hanafuda 2-3.png")));
        deck.add(7,new hanafudaCards(20, "Bush-Warbler", "February", new Image("/images/Hanafuda 2-4.png")));
        deck.add(8,new hanafudaCards(1, "Cherry1", "March", new Image("/images/Hanafuda 3-1.png")));
        deck.add(9, new hanafudaCards(1, "Cherry2", "March", new Image("/images/Hanafuda 3-2.png")));
        deck.add(10, new hanafudaCards(5, "Poetry Ribbon", "March", new Image("/images/Hanafuda 3-3.png")));
        deck.add(11,new hanafudaCards(20, "Camp Curtain", "March", new Image("/images/Hanafuda 3-4.png")));
        deck.add(12, new hanafudaCards(1, "Fuji1", "April", new Image("/images/Hanafuda 4-1.png")));
        deck.add(13, new hanafudaCards(1, "Fuji2", "April", new Image("/images/Hanafuda 4-2.png")));
        deck.add(14, new hanafudaCards(5, "Red Ribbon", "April", new Image("/images/Hanafuda 4-3.png")));
        deck.add(15, new hanafudaCards(10, "Cuckoo", "April", new Image("/images/Hanafuda 4-4.png")));
        deck.add(16, new hanafudaCards(1, "Iris1", "May",new Image("/images/Hanafuda 5-1.png")));
        deck.add(17, new hanafudaCards(1, "Iris2", "May", new Image("/images/Hanafuda 5-2.png")));
        deck.add(18, new hanafudaCards(5, "Red Ribbon", "May", new Image("/images/Hanafuda 5-3.png")));
        deck.add(19, new hanafudaCards(10, "Water Iris and 8-Plank Bridge", "May", new Image("/images/Hanafuda 5-4.png")));
        deck.add(20, new hanafudaCards(1, "Paeony1", "June", new Image("/images/Hanafuda 6-1.png")));
        deck.add(21, new hanafudaCards(1, "Paeony2", "June", new Image("/images/Hanafuda 6-2.png")));
        deck.add(22, new hanafudaCards(5, "Purble Ribbon", "June", new Image("/images/Hanafuda 6-3.png")));
        deck.add(23,new hanafudaCards(10, "Butterfly", "June", new Image("/images/Hanafuda 6-4.png")));
        deck.add(24,new hanafudaCards(1, "Bush Clover1", "July", new Image("/images/Hanafuda 7-1.png")));
        deck.add(25, new hanafudaCards(1, "Bush Clover2", "July", new Image("/images/Hanafuda 7-2.png")));
        deck.add(26, new hanafudaCards(5, "Red Ribbon", "July", new Image("/images/Hanafuda 7-3.png")));
        deck.add(27, new hanafudaCards(10, "Boar", "July", new Image("/images/Hanafuda 7-4.png")));
        deck.add(28, new hanafudaCards(1, "Susuki Grass1", "August", new Image("/images/Hanafuda 8-1.png")));
        deck.add(29,  new hanafudaCards(1, "Susuki Grass2", "August", new Image("/images/Hanafuda 8-2.png")));
        deck.add(30, new hanafudaCards(10, "Geese in Flight", "August", new Image("/images/Hanafuda 8-3.png")));
        deck.add(31,new hanafudaCards(20, "Full Moon with Red Sky", "August", new Image("/images/Hanafuda 8-4.png")));
        deck.add(32, new hanafudaCards(1, "Chrysanthemum1", "September", new Image("/images/Hanafuda 9-1.png")));
        deck.add(33, new hanafudaCards(1, "Chrysanthemum2", "September", new Image("/images/Hanafuda 9-2.png")));
        deck.add(34, new hanafudaCards(5, "Purple Ribbon", "September", new Image("/images/Hanafuda 9-3.png")));
        deck.add(35, new hanafudaCards(10, "Poetry Sake Cup", "September", new Image("/images/Hanafuda 9-4.png")));
        deck.add(36, new hanafudaCards(1, "Maple1", "October", new Image("/images/Hanafuda 10-1.png")));
        deck.add(37, new hanafudaCards(1, "Maple2", "October", new Image("/images/Hanafuda 10-2.png")));
        deck.add(38, new hanafudaCards(5, "Purple Ribbon", "October", new Image("/images/Hanafuda 10-3.png")));
        deck.add(39, new hanafudaCards(10, "Deer and Maple", "October", new Image("/images/Hanafuda 10-4.png")));
        deck.add(40, new hanafudaCards(1, "Lightning", "November", new Image("/images/Hanafuda 11-1.png")));
        deck.add(41, new hanafudaCards(5, "Red Ribbon", "November", new Image("/images/Hanafuda 11-2.png")));
        deck.add(42, new hanafudaCards(10, "Swallow", "November", new Image("/images/Hanafuda 11-3.png")));
        deck.add(43, new hanafudaCards(20, "Ono no Michikaze with Umbrella and Frog", "November", new Image("/images/Hanafuda 11-4.png")));
        deck.add(44, new hanafudaCards(1, "Paulownia1", "December", new Image("/images/Hanafuda 12-1.png")));
        deck.add(45, new hanafudaCards(1, "Paulownia2", "December", new Image("/images/Hanafuda 12-2.png")));
        deck.add(46, new hanafudaCards(1, "Paulownia3", "December", new Image("/images/Hanafuda 12-3.png")));
        deck.add(47, new hanafudaCards(20, "Chinese Phoenix", "December", new Image("/images/Hanafuda 12-4.png")));
    }

}
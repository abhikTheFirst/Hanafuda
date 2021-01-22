package sample;

import javafx.application.Application;
import javafx.collections.FXCollections;
import javafx.collections.ObservableList;
import javafx.concurrent.Task;
import javafx.fxml.FXMLLoader;
import javafx.geometry.Insets;
import javafx.geometry.Pos;
import javafx.scene.Parent;
import javafx.scene.Scene;
import javafx.scene.control.Label;
import javafx.scene.control.ListCell;
import javafx.scene.control.ListView;
import javafx.scene.control.ToggleButton;
import javafx.scene.image.Image;
import javafx.scene.image.ImageView;
import javafx.scene.layout.*;
import javafx.scene.paint.Color;
import javafx.scene.text.Text;
import javafx.stage.Stage;

import java.awt.*;
import java.awt.event.MouseEvent;
import java.util.ArrayList;


public class Main extends Application {
    private Scene stage1, stage2, stage3, stage4;
    private ArrayList<hanafudaCards> deck, handCards, field, graveyard;
    private ObservableList<hanafudaCards> hand;
    private ArrayList<ToggleButton> fieldTemp;
    private HBox fieldDisplay;
    private GridPane layout2;
    private ListView<hanafudaCards> handDisplay;
    private Label fieldValues, displayPlayerStats, playerInfo, drawCardInfo;
    private Player player;
    private int stuff;
    private VBox playerInfoBox;
    private ToggleButton draw, imageResult;
    private hanafudaCards temp;
   //rivate Stage primaryStage = new Stage();

    @Override public void start(Stage primaryStage)  {
        ToggleButton home3 = new ToggleButton("Home");
        home3.setOnAction(event -> primaryStage.setScene(stage1));
        Label rulesStatement = new Label("Match cards based on their suites. The points associated with the cards are the points that you gain each turn.\n There are bonuses associated with matching certain cards. \n These are revealed at the end of the game.\n Happy Hunting.");
        HBox layout3 = new HBox(15);
        layout3.getChildren().addAll(rulesStatement, home3);
        stage3 = new Scene(layout3, 600, 600);

        Label title = new Label("Hanafuda");
        ToggleButton rules = new ToggleButton("Rules");
        rules.setOnAction(event -> primaryStage.setScene(stage3));
        ToggleButton play = new ToggleButton("Play");
        play.setOnAction(event ->primaryStage.setScene(stage2));

        imageResult = new ToggleButton();
        stuff = 1;
        player = new Player();
        deck = new ArrayList<>();
        handCards = new ArrayList<>();
        field = new ArrayList<>();
        graveyard = new ArrayList<>();
        initialiseDeck(deck, handCards, field, imageResult);
        drawCardInfo = new Label(temp.toString());
        hand = FXCollections.observableArrayList(handCards);
        layout2 = new GridPane();
        handDisplay = new ListView<>();
        handDisplay.setCellFactory(listView -> new ListCell<hanafudaCards>() {
            @Override
            protected void updateItem(hanafudaCards card, boolean empty){
               super.updateItem(card, empty);
               if(empty) {
                  setGraphic(null);
               } else {
                   HBox hbox = new HBox(5);
                   hbox.setAlignment(Pos.CENTER_LEFT);
                   hbox.getChildren().addAll(card.getImg(), new Label(card.toString()));
                   setGraphic(hbox);
               }
           }
        });
        handDisplay.setItems(hand);
        handDisplay.prefHeightProperty().bind(layout2.heightProperty());
        layout2.add(handDisplay, 0, 0, 1, 3);
        layout2.setPrefSize(1366, 768);
        HBox options = new HBox();
        ToggleButton home2 = new ToggleButton("Home");
        home2.setAlignment(Pos.TOP_RIGHT);
        home2.setOnAction(event -> primaryStage.setScene(stage1));

        draw = new ToggleButton("Draw");
        options.getChildren().addAll(draw, imageResult, home2);
        playerInfoBox = new VBox();
        playerInfo = new Label("Player Info");
        displayPlayerStats = new Label( "\n" + "Player Score: " + player.getScore() + "\n" + "Player's turns left: " + player.getTurns());
        playerInfoBox.getChildren().addAll(playerInfo,displayPlayerStats);
        fieldDisplay = new HBox(5);
        fieldTemp = new ArrayList<>();
        for(int i = 0; i < field.size(); i++) {
            fieldTemp.add((new ToggleButton()));
            fieldTemp.get(i).setGraphic(field.get(i).getImg());
        }
        fieldDisplay.getChildren().addAll(fieldTemp);
        fieldValues = new Label("go fuck yourself");
        stage2 = new Scene(layout2, 1366, 768);
      /*if(10 - player.getTurns() == 9 || deck.size() == 0){
            VBox pane = new VBox();
            Label scoreCallout = new Label("The game is finished, you have" + player.getScore() + " points." );
            pane.getChildren().addAll(scoreCallout, giveBonuses(graveyard));
            stage4 = new Scene(pane, 600, 600);
            primaryStage.setScene(stage4);

        }else{
            playGame();
        } */
        layout2.add(fieldDisplay, 1, 0);
        layout2.add(fieldValues, 1,1);
        layout2.add(options,2,0);
        layout2.setHgap(10);
        layout2.add(drawCardInfo, 2, 1);
        layout2.add(playerInfoBox, 2, 2);
        home2.setAlignment(Pos.TOP_RIGHT);

        draw.setOnMouseClicked(MouseEvent -> {
            handDisplay.setMouseTransparent(false);
            handDisplay.setFocusTraversable(true);
            player.setTurns(player.getTurns()-1);
            temp = deck.get(((int)(Math.random() * (deck.size()-1))));
            imageResult.setGraphic(temp.getImg());
            boolean b = false;
            for(int i = 0; i < field.size(); i++){
                if(!field.get(i).getSuite().equals(temp.getSuite())){
                    b = true;
                }
                else if(field.get(i).getSuite().equals(temp.getSuite())){
                    b = false;
                    break;
                }
            }
            if(b){
                handDisplay.getItems().add(temp);
                imageResult.setGraphic(null);
            }
            displayPlayerStats.textProperty().set("Player Score: " + player.getScore() + "\n" + "Player's turns left: " + player.getTurns());
            drawCardInfo.textProperty().set(temp.toString());
            playWholeGame(primaryStage);
        });

        boolean b = false;
        for(int i = 0; i < field.size(); i++){
            if(!field.get(i).getSuite().equals(temp.getSuite())){
                b = true;
            }
            else if(field.get(i).getSuite().equals(temp.getSuite())){
                b = false;
                break;
            }
        }
        if(b){
            handDisplay.getItems().add(temp);
            imageResult.setGraphic(null);
        }
        VBox layout1 = new VBox(15);
        layout1.getChildren().addAll(title, rules, play);
        stage1 = new Scene(layout1, 600, 600);
        primaryStage.setScene(stage1);
        primaryStage.setTitle("Hanafuda");
        primaryStage.show();
        playerInfoBox.setAlignment(Pos.BOTTOM_CENTER);
        playWholeGame(primaryStage);
    }

    public static void main(String[] args) {
        launch(args);
    }

    public void playGame(){
        //player.setTurns(player.getTurns()-1);
        for(int i = 0; i < fieldTemp.size(); i++){
            fieldTemp.get(i).setBackground(new Background(new BackgroundFill(Color.LIGHTGREY, CornerRadii.EMPTY, Insets.EMPTY)));
        }
        fieldTemp.forEach(toggleButton -> {toggleButton.setOnAction(event -> {
            if(toggleButton.isSelected()) {
                for(int z = 0; z < fieldTemp.size(); z++) {
                    if(z != fieldTemp.indexOf(toggleButton)){
                        fieldTemp.get(z).setDisable(true);
                    }
                }
                fieldTemp.get(fieldTemp.lastIndexOf(toggleButton)).setSelected(true);
                toggleButton.setBackground(new Background(new BackgroundFill(Color.BLUE, CornerRadii.EMPTY, Insets.EMPTY)));
                fieldValues.textProperty().set(field.get(fieldTemp.indexOf(toggleButton)).toString());
                matchHandField(toggleButton);
                isMatched(toggleButton);
            }
            if(!toggleButton.isSelected()) {
                for(int z = 0; z < fieldTemp.size(); z++){
                    if(z != fieldTemp.indexOf(toggleButton)){
                        fieldTemp.get(z).setDisable(false);
                    }
                }
                toggleButton.setBackground(new Background(new BackgroundFill(Color.LIGHTGREY, CornerRadii.EMPTY, Insets.EMPTY)));
            }
        });});
    }

    public void isMatched(ToggleButton tbutton) {
        int index = fieldTemp.indexOf(tbutton);
        handDisplay.setOnMouseClicked(MouseEvent -> {
            if(tbutton.isSelected()) {
                if (field.get(index).getSuite().equals(handDisplay.getSelectionModel().getSelectedItem().getSuite())) {
                    player.setScore(field.get(index).getValue() + handDisplay.getSelectionModel().getSelectedItem().getValue());
                    graveyard.add(field.get(index));
                    field.remove(index);
                    System.out.println(field.size() + "\n" + fieldTemp.size());
                    for (int z = 0; z < fieldTemp.size() - stuff; z++) {
                        fieldTemp.get(z).setGraphic(field.get(z).getImg());
                    }
                    fieldTemp.get(index).setSelected(false);
                    for (int a = 0; a < fieldTemp.size(); a++) {
                        fieldTemp.get(a).setDisable(false);
                    }
                    fieldTemp.get(index).setBackground(new Background(new BackgroundFill(Color.LIGHTGREY, CornerRadii.EMPTY, Insets.EMPTY)));
                    stuff += 1;
                }
                graveyard.add(handDisplay.getSelectionModel().getSelectedItem());
                handDisplay.getItems().remove(handDisplay.getSelectionModel().getSelectedItem());
                handDisplay.getSelectionModel().clearSelection();
                fieldTemp.get(index).setSelected(false);
                for (int a = 0; a < fieldTemp.size(); a++) {
                    fieldTemp.get(a).setDisable(false);
                }
                fieldTemp.get(index).setBackground(new Background(new BackgroundFill(Color.LIGHTGREY, CornerRadii.EMPTY, Insets.EMPTY)));
                if (stuff == 8) {
                    stuff = 1;
                }
                displayPlayerStats.textProperty().set("\n" + "Player Score: " + player.getScore() + "\n" + "Player's turns left: " + player.getTurns());
                handDisplay.setMouseTransparent(true);
                handDisplay.setFocusTraversable(false);
            }});
    }

    public Label giveBonuses(ArrayList<hanafudaCards> graveyard){
            int countpr = 0;
            int countppr = 0;
            int countrr = 0;
            for(int i = 0;i < graveyard.size(); i++){
            if(graveyard.get(i).getName().equals("Poetry Ribbon")){
            countpr++;
            }
            if(graveyard.get(i).getName().equals("Purple Ribbon")){
            countppr++;
           }
            if(graveyard.get(i).getName().equals("Red Ribbon")){
                countrr++;
                }
        }
       player.setScore(player.getScore() +((countppr/2) * 50) + ((countrr/2)* 50) + ((countpr/2)* 50));
        return new Label("\n\n\n Matching poetry Ribbons: " + ((countppr/2)*100)+ "\n Matching Purple Ribbons: "+ ((countpr/2)*50)+"\n Matching Red Ribbons: " + ((countrr/2)*50));
   }

    public void matchHandField(ToggleButton tButton){
        imageResult.setOnMouseClicked(MouseEvent -> {
            if(tButton.isSelected()) {
                int index = fieldTemp.indexOf(tButton);
                if (field.get(index).getSuite().equals(temp.getSuite())) {
                    player.setScore(field.get(index).getValue() + temp.getValue());
                }
                graveyard.add(field.get(index));
                field.remove(index);
                for (int z = 0; z < fieldTemp.size() - stuff; z++) {
                    fieldTemp.get(z).setGraphic(field.get(z).getImg());
                }
                fieldTemp.get(index).setSelected(false);
                for (int a = 0; a < fieldTemp.size(); a++) {
                    fieldTemp.get(a).setDisable(false);
                }
                fieldTemp.get(index).setBackground(new Background(new BackgroundFill(Color.LIGHTGREY, CornerRadii.EMPTY, Insets.EMPTY)));
                stuff += 1;
                imageResult.setGraphic(null);
                displayPlayerStats.textProperty().set("\n" + "Player Score: " + player.getScore() + "\n" + "Player's turns left: " + player.getTurns());
                fieldTemp.get(index).setSelected(false);
                for (int a = 0; a < fieldTemp.size(); a++) {
                    fieldTemp.get(a).setDisable(false);
                }
                fieldTemp.get(index).setBackground(new Background(new BackgroundFill(Color.LIGHTGREY, CornerRadii.EMPTY, Insets.EMPTY)));
                if (stuff == 8) {
                    stuff = 1;
                }
            }
        });

    }

    public void playWholeGame(Stage primaryStage){
        if(10 - player.getTurns() ==10){
            VBox pane = new VBox();
            Label scoreCallout = new Label("The game is finished, you have" + player.getScore() + " points." );
            pane.getChildren().addAll(scoreCallout, giveBonuses(graveyard));
            stage4 = new Scene(pane, 600, 600);
            primaryStage.setScene(stage4);

        }else{
            playGame();
        }
    }

    public void initialiseDeck(ArrayList<hanafudaCards> deck, ArrayList<hanafudaCards> handCards, ArrayList<hanafudaCards> field, ToggleButton imageResult) {
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
        for (int i = 0; i < 8; i++) {
            hanafudaCards temp = deck.get(((int)(Math.random() * (deck.size()-1))));
            handCards.add(temp);
            deck.remove(temp);
        }
        for(int z = 0; z < 8;z++){
            hanafudaCards temp = deck.get(((int)(Math.random() * (deck.size()-1))));
            field.add(temp);
            deck.remove(temp);
        }
        temp = deck.get(((int)(Math.random() * (deck.size()-1))));
        deck.remove(temp);
        imageResult.setGraphic(temp.getImg());
    }
}

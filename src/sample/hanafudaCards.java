package sample;

import javafx.scene.image.Image;
import javafx.scene.image.ImageView;

public class hanafudaCards {
    private final int value;
    private final String name;
    private final String suite;
    private final ImageView img;

    public hanafudaCards(int value, String name, String suite, Image image) {
        this.value = value;
        this.name = name;
        this.suite = suite;
        img = new ImageView(image);
        img.setFitHeight(112.5);//112.5
        img.setFitWidth(71.25);//71.25
    }

    public hanafudaCards(){
        value = 1;
        name = "Joker";
        suite = "Persona 5";
        img = new ImageView("/images/Hanafuda 11-1.png");
    }

    public int getValue() {
        return value;
    }

    public String getName() {
        return name;
    }

    public String getSuite() {
        return suite;
    }

    //public ImageView getImgView() {
    //  return imgView;
    //}

    public ImageView getImg() {
        return img;
    }

    @Override
    public String toString() {
        return "hanafudaCards{" +
                "value=" + value +
                "\n, name='" + name + '\'' +
                "\n, suite='" + suite + '\'' +
                '}';
    }
}

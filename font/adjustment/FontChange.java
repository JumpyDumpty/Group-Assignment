package font.adjustment;

import javafx.event.EventHandler;
import javafx.scene.control.Button;
import javafx.scene.control.TextField;
import javafx.scene.input.MouseButton;
import javafx.scene.input.MouseEvent;
import javafx.scene.text.Font;

import java.util.ArrayList;

public abstract class FontChange implements EventHandler<MouseEvent> {

    private ArrayList<Button> button;

    private ArrayList<TextField> text;


    public FontChange() {
        this.button = new ArrayList<>();
        this.text = new ArrayList<>();
    }

    public void addText(TextField newText) {text.add(newText);}

    public void addText(Button newButton) {button.add(newButton);}

    @Override
    public final void handle(MouseEvent event) {
        if(event.getButton() == MouseButton.PRIMARY) {
            setTextSize();
            setButtonSize();
        }
    }

    public abstract void setTextSize();

    public abstract void setButtonSize();

    public ArrayList<TextField> getText() {return text;}

    public ArrayList<Button> getButton() {return button;}

}
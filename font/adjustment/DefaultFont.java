package font.adjustment;

import javafx.scene.control.Button;
import javafx.scene.control.TextField;
import javafx.scene.text.Font;

import java.util.ArrayList;

public class DefaultFont extends FontChange{

    ArrayList<TextField> textList = super.getText();
    ArrayList<Button> buttonList = super.getButton();

    @Override
    public void setTextSize() {
        for(TextField text : textList) {
            text.setFont(new Font(12));
        }
    }

    @Override
    public void setButtonSize() {
        for(Button button : buttonList) {button.setFont(new Font(12));}
    }
}
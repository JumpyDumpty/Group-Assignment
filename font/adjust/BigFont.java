package font.adjust;

import javafx.scene.control.Button;
import javafx.scene.control.TextField;
import javafx.scene.text.Font;

import java.util.ArrayList;

public class BigFont extends FontChange{

    ArrayList<TextField> textList = super.getText();

    ArrayList<Button> buttonList = super.getButton();

    @Override
    public void setTextSize() {
        for(TextField text : textList) {text.setFont(new Font(20));}
    }

    @Override
    public void setButtonSize() {
        for(Button button : buttonList) {button.setFont(new Font(20));}
    }
}

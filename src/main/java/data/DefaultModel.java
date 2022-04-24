package data;

import java.awt.*;

public class DefaultModel {
    public static Button getDefaultButton(String name){
        Button backButton = new Button(name);
        backButton.setForeground(new Color(255, 255, 255));
        backButton.setBackground(new Color(0, 128, 255));
        backButton.setFont(new Font("Tahoma", Font.PLAIN, 20));
        return backButton;
    }
}

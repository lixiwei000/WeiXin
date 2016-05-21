package cn.edu.ncut.model.menu;

import java.io.Serializable;
import java.util.Arrays;

/**
 * @author NikoBelic
 * @create 23:41
 */
public class QueryMenuResult implements Serializable
{
    private Menu menu;
    private Button[] button;

    public Menu getMenu() {
        return menu;
    }

    public void setMenu(Menu menu) {
        this.menu = menu;
    }

    public Button[] getButton() {
        return button;
    }

    public void setButton(Button[] button) {
        this.button = button;
    }

    @Override
    public String toString() {
        return "QueryMenuResult{" +
                "menu=" + menu +
                ", button=" + Arrays.toString(button) +
                '}';
    }
}

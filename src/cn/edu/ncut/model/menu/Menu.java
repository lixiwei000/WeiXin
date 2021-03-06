package cn.edu.ncut.model.menu;

import com.google.gson.annotations.Expose;

import java.io.Serializable;
import java.util.Arrays;

/**
 * @author NikoBelic
 * @create 16:41
 */
public class Menu implements Serializable
{
    @Expose
    private Button[] button;

    public Button[] getButton() {
        return button;
    }

    public void setButton(Button[] button) {
        this.button = button;
    }

    @Override
    public String toString() {
        return "Menu{" +
                "button=" + Arrays.toString(button) +
                '}';
    }
}

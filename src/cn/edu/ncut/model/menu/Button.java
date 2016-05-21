package cn.edu.ncut.model.menu;

import com.google.gson.annotations.Expose;

import java.io.Serializable;

/**
 * @author NikoBelic
 * @create 16:39
 */
public class  Button implements Serializable
{
    @Expose
    private String name;
    @Expose
    private String type;
    @Expose
    private Button[] sub_button;

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public String getType() {
        return type;
    }

    public void setType(String type) {
        this.type = type;
    }

    public Button[] getSub_button() {
        return sub_button;
    }

    public void setSub_button(Button[] sub_button) {
        this.sub_button = sub_button;
    }
}

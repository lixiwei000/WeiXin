package cn.edu.ncut.model.menu;

import com.google.gson.annotations.Expose;

import java.io.Serializable;

/**
 * @author NikoBelic
 * @create 16:40
 */
public class ClickButton extends Button implements Serializable
{
    @Expose
    private String key;

    public String getKey() {
        return key;
    }

    public void setKey(String key) {
        this.key = key;
    }
}

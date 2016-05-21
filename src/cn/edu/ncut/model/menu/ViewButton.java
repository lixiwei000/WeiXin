package cn.edu.ncut.model.menu;

import com.google.gson.annotations.Expose;

import java.io.Serializable;

/**
 * @author NikoBelic
 * @create 16:40
 */
public class ViewButton extends Button implements Serializable
{
    @Expose
    private String url;

    public String getUrl() {
        return url;
    }

    public void setUrl(String url) {
        this.url = url;
    }
}

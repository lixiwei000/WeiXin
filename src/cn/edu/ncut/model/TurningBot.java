package cn.edu.ncut.model;

import java.io.Serializable;

/**
 * @author NikoBelic
 * @create 01:18
 */
public class TurningBot implements Serializable
{
    private String code;
    private String text;

    public String getCode() {
        return code;
    }

    public void setCode(String code) {
        this.code = code;
    }

    public String getText() {
        return text;
    }

    public void setText(String text) {
        this.text = text;
    }

    @Override
    public String toString() {
        return "TurningBot{" +
                "code='" + code + '\'' +
                ", text='" + text + '\'' +
                '}';
    }
}

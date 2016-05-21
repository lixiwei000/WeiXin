package cn.edu.ncut.model.menu;

import java.io.Serializable;

/**
 * @author NikoBelic
 * @create 23:46
 */
public class ErrorResult implements Serializable
{
    private int errorcode;
    private String errmsg;

    public int getErrorcode() {
        return errorcode;
    }

    public void setErrorcode(int errorcode) {
        this.errorcode = errorcode;
    }

    public String getErrmsg() {
        return errmsg;
    }

    public void setErrmsg(String errmsg) {
        this.errmsg = errmsg;
    }
}

package cn.edu.ncut.model.weather;

import java.io.Serializable;

/**
 * @author NikoBelic
 * @create 23:25
 */
public class Weather implements Serializable
{
    private int errNum;
    private String errMsg;
    private RetData retData;

    public int getErrNum() {
        return errNum;
    }

    public void setErrNum(int errNum) {
        this.errNum = errNum;
    }

    public String getErrMsg() {
        return errMsg;
    }

    public void setErrMsg(String errMsg) {
        this.errMsg = errMsg;
    }

    public RetData getRetData() {
        return retData;
    }

    public void setRetData(RetData retData) {
        this.retData = retData;
    }

    @Override
    public String toString() {
        return "Weather{" +
                "errNum=" + errNum +
                ", errMsg='" + errMsg + '\'' +
                ", retData=" + retData +
                '}';
    }
}

package cn.edu.ncut.model.lottery;

import java.io.Serializable;

/**
 * @author NikoBelic
 * @create 00:18
 */
public class Lottery implements Serializable
{
    private int errNum;
    private String retMsg;
    private LotteryRet retData;

    public int getErrNum() {
        return errNum;
    }

    public void setErrNum(int errNum) {
        this.errNum = errNum;
    }

    public String getRetMsg() {
        return retMsg;
    }

    public void setRetMsg(String retMsg) {
        this.retMsg = retMsg;
    }

    public LotteryRet getRetData() {
        return retData;
    }

    public void setRetData(LotteryRet retData) {
        this.retData = retData;
    }

    @Override
    public String toString() {
        return "Lottery{" +
                "errNum=" + errNum +
                ", retMsg='" + retMsg + '\'' +
                ", retData=" + retData +
                '}';
    }

    /*

{
    "errNum": 0,
    "retMsg": "success",
    "retData": {

    }
}
     */
}

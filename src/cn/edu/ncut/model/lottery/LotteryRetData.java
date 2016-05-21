package cn.edu.ncut.model.lottery;

import java.io.Serializable;

/**
 * @author NikoBelic
 * @create 00:20
 */
public class LotteryRetData implements Serializable
{

    private String expect;
    private String openCode;
    private String openTime;
    private String openTimeStamp;

    public String getExpect() {
        return expect;
    }

    public void setExpect(String expect) {
        this.expect = expect;
    }

    public String getOpenCode() {
        return openCode;
    }

    public void setOpenCode(String openCode) {
        this.openCode = openCode;
    }

    public String getOpenTime() {
        return openTime;
    }

    public void setOpenTime(String openTime) {
        this.openTime = openTime;
    }

    public String getOpenTimeStamp() {
        return openTimeStamp;
    }

    public void setOpenTimeStamp(String openTimeStamp) {
        this.openTimeStamp = openTimeStamp;
    }

    @Override
    public String toString() {
        return "LotteryRetData{" +
                "expect='" + expect + '\'' +
                ", openCode='" + openCode + '\'' +
                ", openTime='" + openTime + '\'' +
                ", openTimeStamp='" + openTimeStamp + '\'' +
                '}';
    }

    /*
    "expect": "2015063",
                "openCode": "01,04,06,34,35+02,04",
                "openTime": "2015-06-03 20:38:00",
                "openTimeStamp": "1433335080000"
     */
}

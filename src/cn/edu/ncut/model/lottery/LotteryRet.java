package cn.edu.ncut.model.lottery;

import java.io.Serializable;
import java.util.Arrays;

/**
 * @author NikoBelic
 * @create 00:20
 */
public class LotteryRet implements Serializable
{
    private int recordCnt;
    private String lotteryCode;
    private LotteryRetData[] data;

    public int getRecordCnt() {
        return recordCnt;
    }

    public void setRecordCnt(int recordCnt) {
        this.recordCnt = recordCnt;
    }

    public String getLotteryCode() {
        return lotteryCode;
    }

    public void setLotteryCode(String lotteryCode) {
        this.lotteryCode = lotteryCode;
    }

    public LotteryRetData[] getData() {
        return data;
    }

    public void setData(LotteryRetData[] data) {
        this.data = data;
    }

    @Override
    public String toString() {
        return "LotteryRet{" +
                "data=" + Arrays.toString(data) +
                ", lotteryCode='" + lotteryCode + '\'' +
                ", recordCnt=" + recordCnt +
                '}';
    }

    /*
       "recordCnt": "1",
        "lotteryCode": "dlt",
        "data": [
            {

            }
        ]
     */
}

package cn.edu.ncut.model.ip;

import java.io.Serializable;

/**
 * @author NikoBelic
 * @create 00:47
 */
public class IP implements Serializable
{
    private int errNum;
    private String errMsg;
    private IpRet retData;

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

    public IpRet getRetData() {
        return retData;
    }

    public void setRetData(IpRet retData) {
        this.retData = retData;
    }

    @Override
    public String toString() {
        return "IP{" +
                "errNum=" + errNum +
                ", errMsg='" + errMsg + '\'' +
                ", retData=" + retData +
                '}';
    }

    /*

{
    "errNum": 0,
    "errMsg": "success",
    "retData": {
        "ip": "117.89.35.58", //IP地址
        "country": "中国", //国家
        "province": "江苏", //省份 国外的默认值为none
        "city": "南京", //城市  国外的默认值为none
        "district": "鼓楼",// 地区 国外的默认值为none
        "carrier": "中国电信" //运营商  特殊IP显示为未知
    }
}
     */
}

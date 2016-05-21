package cn.edu.ncut.model.ip;

import java.io.Serializable;

/**
 * @author NikoBelic
 * @create 00:48
 */
public class IpRet implements Serializable
{
    private String ip ;
    private String country;
    private String province;
    private String city;
    private String district;
    private String carrier;

    public String getIp() {
        return ip;
    }

    public void setIp(String ip) {
        this.ip = ip;
    }

    public String getCountry() {
        return country;
    }

    public void setCountry(String country) {
        this.country = country;
    }

    public String getProvince() {
        return province;
    }

    public void setProvince(String province) {
        this.province = province;
    }

    public String getCity() {
        return city;
    }

    public void setCity(String city) {
        this.city = city;
    }

    public String getDistrict() {
        return district;
    }

    public void setDistrict(String district) {
        this.district = district;
    }

    public String getCarrier() {
        return carrier;
    }

    public void setCarrier(String carrier) {
        this.carrier = carrier;
    }

    @Override
    public String toString() {
        return "IpRet{" +
                "ip='" + ip + '\'' +
                ", country='" + country + '\'' +
                ", province='" + province + '\'' +
                ", city='" + city + '\'' +
                ", district='" + district + '\'' +
                ", carrier='" + carrier + '\'' +
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

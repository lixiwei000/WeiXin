package cn.edu.ncut.model.weather;

import java.io.Serializable;
import java.util.List;

/**
 * @author NikoBelic
 * @create 23:39
 */
public class Today implements Serializable
{
    private String date;
    private String week;
    private String curTemp;
    private String aqi;
    private String fengxiang;
    private String fengli;
    private String hightemp;
    private String lowtemp;
    private String type;
    private List<Index> index;

    public String getDate() {
        return date;
    }

    public void setDate(String date) {
        this.date = date;
    }

    public String getWeek() {
        return week;
    }

    public void setWeek(String week) {
        this.week = week;
    }

    public String getCurTemp() {
        return curTemp;
    }

    public void setCurTemp(String curTemp) {
        this.curTemp = curTemp;
    }

    public String getAqi() {
        return aqi;
    }

    public void setAqi(String aqi) {
        this.aqi = aqi;
    }

    public String getFengxiang() {
        return fengxiang;
    }

    public void setFengxiang(String fengxiang) {
        this.fengxiang = fengxiang;
    }

    public String getFengli() {
        return fengli;
    }

    public void setFengli(String fengli) {
        this.fengli = fengli;
    }

    public String getHightemp() {
        return hightemp;
    }

    public void setHightemp(String hightemp) {
        this.hightemp = hightemp;
    }

    public String getLowtemp() {
        return lowtemp;
    }

    public void setLowtemp(String lowtemp) {
        this.lowtemp = lowtemp;
    }

    public String getType() {
        return type;
    }

    public void setType(String type) {
        this.type = type;
    }

    public List<Index> getIndex() {
        return index;
    }

    public void setIndex(List<Index> index) {
        this.index = index;
    }

    @Override
    public String toString() {
        return "Today{" +
                "date='" + date + '\'' +
                ", week='" + week + '\'' +
                ", curTemp='" + curTemp + '\'' +
                ", aqi='" + aqi + '\'' +
                ", fengxiang='" + fengxiang + '\'' +
                ", fengli='" + fengli + '\'' +
                ", hightemp='" + hightemp + '\'' +
                ", lowtemp='" + lowtemp + '\'' +
                ", type='" + type + '\'' +
                ", index=" + index +
                '}';
    }

    /*
             today: {
                    date: "2015-08-03", //今天日期
                    week: "星期一",    //今日星期
                    curTemp: "28℃",    //当前温度
                    aqi: "92",        //pm值
                    fengxiang: "无持续风向", //风向
                    fengli: "微风级",     //风力
                    hightemp: "30℃",   //最高温度
                    lowtemp: "23℃",    //最低温度
                    type: "阵雨",      //天气状态
                    index: [  //指标列表
                        {
                            name: "感冒指数", //指数指标1名称
                            code: "gm",     //指标编码
                            index: "",      //等级
                            details: "各项气象条件适宜，发生感冒机率较低。但请避免长期处于空调房间中，以防感冒。",//描述
                            otherName: "" //其它信息
                        },
                        {
                            code: "fs",
                            details: "属中等强度紫外辐射天气，外出时应注意防护，建议涂擦SPF指数高于15，PA+的防晒护肤品。",
                            index: "中等",
                            name: "防晒指数",
                            otherName: ""
                        },
                        {
                            code: "ct",
                            details: "天气炎热，建议着短衫、短裙、短裤、薄型T恤衫等清凉夏季服装。",
                            index: "炎热",
                            name: "穿衣指数",
                            otherName: ""
                        },
                        {
                            code: "yd",
                            details: "有降水，推荐您在室内进行低强度运动；若坚持户外运动，须注意选择避雨防滑并携带雨具。",
                            index: "较不宜",
                            name: "运动指数",
                            otherName: ""
                        },
                        {
                            code: "xc",
                            details: "不宜洗车，未来24小时内有雨，如果在此期间洗车，雨水和路上的泥水可能会再次弄脏您的爱车。",
                            index: "不宜",
                            name: "洗车指数",
                            otherName: ""
                        },
                        {
                            code: "ls",
                            details: "有降水，不适宜晾晒。若需要晾晒，请在室内准备出充足的空间。",
                            index: "不宜",
                            name: "晾晒指数",
                            otherName: ""
                        }
                    ]
                },
             */
    private class Index
    {
        private String name;
        private String code;
        private String index;
        private String details;
        private String otherName;

        public String getName() {
            return name;
        }

        public void setName(String name) {
            this.name = name;
        }

        public String getCode() {
            return code;
        }

        public void setCode(String code) {
            this.code = code;
        }

        public String getIndex() {
            return index;
        }

        public void setIndex(String index) {
            this.index = index;
        }

        public String getDetails() {
            return details;
        }

        public void setDetails(String details) {
            this.details = details;
        }

        public String getOtherName() {
            return otherName;
        }

        public void setOtherName(String otherName) {
            this.otherName = otherName;
        }

        @Override
        public String toString() {
            return "Index{" +
                    "name='" + name + '\'' +
                    ", code='" + code + '\'' +
                    ", index='" + index + '\'' +
                    ", details='" + details + '\'' +
                    ", otherName='" + otherName + '\'' +
                    '}';
        }
    }
}

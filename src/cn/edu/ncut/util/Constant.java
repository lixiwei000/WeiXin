package cn.edu.ncut.util;

/**
 * @author NikoBelic
 * @create 22:56
 */
public class Constant
{
    /********************** 参数变量 ***********************/
    public static final int HTTPCLIENT_CONNECTION_COUNT = 10;
    public static final int HTTPCLIENT_MAXPERROUTE_COUNT = 10;
    public static final int HTTPCLIENT_CONNECT_TIMEOUT = 6 * 1000;
    public static final int HTTPCLIENT_SOCKET_TIMEOUT = 6 * 1000;
    public static String[] USER_AGENT = new String[]{
            "Mozilla/5.0 (Macintosh; Intel Mac OS X 10_11_5) AppleWebKit/537.36 (KHTML, like Gecko) Chrome/50.0.2661.102 Safari/537.36",
            "Mozilla/5.0 (Windows NT 10.0; WOW64) AppleWebKit/537.36 (KHTML, like Gecko) Chrome/46.0.2471.2 Safari/537.36",
            "Mozilla/5.0 (Macintosh; Intel Mac OS X 10_11_0) AppleWebKit/537.36 (KHTML, like Gecko) Chrome/48.0.2564.109 Safari/537.36",
            "Mozilla/5.0 (Windows; U; Windows NT 6.1; en-US; rv:1.9.1.6) Gecko/20091201 Firefox/3.5.6",
            "Mozilla/5.0 (Windows NT 6.2) AppleWebKit/535.11 (KHTML, like Gecko) Chrome/17.0.963.12 Safari/535.11",
            "Mozilla/5.0 (compatible; MSIE 10.0; Windows NT 6.2; Trident/6.0)"
    };

    /********************** 地址变量 ***********************/
    public static final String TURNING_URL = "http://apis.baidu.com/turing/turing/turing";
    public static final String BAIDU_WEATHER_URL = "http://apis.baidu.com/apistore/weatherservice/recentweathers?cityname=北京";
    /********************** Api_key ***********************/
    public static final String BAIDU_TOKEN = "8f0d8c85736672941de5d67c35e87ca4";
    public static final String TURING_TOKEN = "873dfff37c3170f232f17c32186a4c95";
}

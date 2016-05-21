package cn.edu.ncut.util;

import cn.edu.ncut.model.*;
import cn.edu.ncut.model.weather.Forecast;
import cn.edu.ncut.model.weather.Today;
import cn.edu.ncut.model.weather.Weather;
import com.thoughtworks.xstream.XStream;
import org.dom4j.Document;
import org.dom4j.DocumentException;
import org.dom4j.Element;
import org.dom4j.io.SAXReader;

import javax.servlet.http.HttpServletRequest;
import java.io.IOException;
import java.io.InputStream;
import java.text.SimpleDateFormat;
import java.util.*;

/**
 * 序列化/反序列化转换工具
 * @author NikoBelic
 * @create 12:54
 */
public class MessageUtil
{
    // 消息类型
    public static final String MESSAGE_TEXT = "text";
    public static final String MESSAGE_NEWS = "news";
    public static final String MESSAGE_IMAGE = "image";
    public static final String MESSAGE_VOICE = "voice";
    public static final String MESSAGE_ViDEO = "video";
    public static final String MESSAGE_LINK = "link";
    public static final String MESSAGE_LOCATION = "location";
    public static final String MESSAGE_ENVENT = "event";
    public static final String MESSAGE_SUBSCRIBE = "subscribe";
    public static final String MESSAGE_UNSUBSCRIBE = "unsubscribe";
    public static final String MESSAGE_VIEW = "view";


    /**************************** 组装返回到微信平台的信息 ********************************/

    /**
     * 组装文本信息
     * @Author NikoBelic
     * @Date 16/5/21 22:29
     */
    public static String initText(String toUserName,String fromUserName,String content)
    {
        TextMessage text = new TextMessage();
        text.setFromUserName(toUserName);
        text.setToUserName(fromUserName);
        text.setMsgType(MessageUtil.MESSAGE_TEXT);
        text.setCreateTime(new Date().getTime());
        text.setContent(content);
        return textMessageToXml(text);
    }

    /**
     * 图文消息的组装
     */
    public static String initNewsMessage(String toUserName,String fromUserName,NewsMessage newsMessage)
    {
        String message = null;
        newsMessage.setFromUserName(toUserName);
        newsMessage.setToUserName(fromUserName);
        message = newsMessageToXml(newsMessage);
        return message;
    }
    /**
     * 组装图片消息
     * @Author NikoBelic
     * @Date 16/5/20 15:21
     */
    public static String initImageMessage(String toUserName,String fromUsername)
    {
        String message = null;
        Image image = new Image();
        image.setMediaId("wZA8FGhd5a572TDS6RQGAB3e9I6NIADf9IxX3bNlJ2LcOzpVrYTwCJMSOs4G_Tgw");
        ImageMessage imageMessage = new ImageMessage();
        imageMessage.setFromUserName(toUserName);
        imageMessage.setToUserName(fromUsername);
        imageMessage.setMsgType(MESSAGE_IMAGE);
        imageMessage.setCreateTime(new Date().getTime());
        imageMessage.setImage(image);
        message = imageMessageToXml(imageMessage);
        return message;

    }
    /**
     * 组织NCUT图文消息
     */
    public static NewsMessage schoolInfo()
    {
        List<News> newsList = new ArrayList<News>();
        NewsMessage newsMessage = new NewsMessage();

        News news = new News();
        news.setTitle("北方工业大学Lab1107");
        news.setDescription("北方有凤，西山最奇；钟灵毓秀，北方工大。\n" +
                "    北方工业大学是一所以工为主、文理兼融，具有学士、硕士、博士培养层次和高水平棒垒球运动员招收资格的多科性高等学府，具有硕士研究生免试推荐资格，是教育部“卓越工程师教育培养”院校。学校由中央与北京市共建，以北京市管理为主，前身是创立于1946年的“国立北平高级工业职业学校”。随后几经变迁，于1985年经国务院批准，由北京冶金机电学院易名为北方工业大学。");
        news.setPicUrl("http://lab1107.ngrok.natapp.cn/WeiXin/image/ncut.jpg");
        news.setUrl("http://www.ncut.edu.cn/");
        newsList.add(news);

        newsMessage.setCreateTime(new Date().getTime());
        newsMessage.setMsgType(MESSAGE_NEWS);
        newsMessage.setArticles(newsList);
        newsMessage.setArticleCount(newsList.size());
        return newsMessage;
    }

    /**************************** 组装整理后的字符串 ********************************/

    /**
     * 主菜单
     * @return
     */
    public static String menuText()
    {
        StringBuffer buffer = new StringBuffer();
        buffer.append("欢迎您的关注\n请按照菜单提示进行操作:\n\n");
        buffer.append("1:公众号介绍\n");
        buffer.append("2:开发者介绍\n");
        buffer.append("3:学校介绍\n");
        buffer.append("4:显示当前天气信息\n");
        buffer.append("5:显示天气预报\n");
        buffer.append("6:查询NCUT课程成绩\n格式如下:\n6 学号 密码 季度 年份\n其中季度1为春季2为秋季\n示例:6 12345 password 2 2015\n");
        buffer.append("help:显示此菜单\n");
        buffer.append("胡乱输入撒旦会跳出来^_^");
        return buffer.toString();
    }

    /**
     * 公众号介绍
     * @return
     */
    public static String platformInfo()
    {
        StringBuffer buffer = new StringBuffer();
        buffer.append("公众号现在处于开发阶段:\n");
        buffer.append("有需求请留言,任何有意义的需求都极有可能被开发者采用\n");
        buffer.append("O(∩_∩)O~~\n");
        return buffer.toString();
    }

    /**
     * 开发者介绍
     * @return
     */
    public static String authorInfo()
    {
        StringBuffer buffer = new StringBuffer();
        buffer.append("开发者:        NikoBelic\n");
        buffer.append("单位:         北方工业大学Lab1107\n");
        buffer.append("位置:         石景山区\n");
        buffer.append("系统时间:  " + new SimpleDateFormat("yyyy-MM-dd HH:mm:ss").format(new Date()));
        return buffer.toString();
    }
    /**
     * 当前天气信息输出
     */
    public static String showTodayWeather(Weather weather)
    {
        Today today = weather.getRetData().getToday();
        StringBuilder sb = new StringBuilder();
        sb.append(today.getDate()).append(",").append(today.getWeek()).append("\n");
        sb.append("当前气温:" + today.getCurTemp()).append("\n");
        sb.append("最高气温:" + today.getHightemp()).append("\n");
        sb.append("最低气温:" + today.getLowtemp()).append("\n");
        sb.append("风力:" + today.getFengli()).append("\n");
        sb.append("PM2.5:" + today.getAqi()).append("\n");
        sb.append("天气状态:" + today.getType()).append("\n");
        return sb.toString();
    }

    /**
     * 天气预报输出
     */
    public static String showForecastWeather(Weather weather)
    {
        List<Forecast> forecasts = weather.getRetData().getForecast();
        StringBuilder sb = new StringBuilder();
        for (Forecast f : forecasts)
        {
            sb.append(f.getDate()).append(",").append(f.getWeek()).append("\n");
            sb.append("最高气温:" + f.getHightemp()).append("\n");
            sb.append("最低气温:" + f.getLowtemp()).append("\n");
            sb.append("风力:" + f.getFengli()).append("\n");
            sb.append("天气状态:" + f.getType()).append("\n");
            sb.append("(*^__^*)").append("\n");
        }
        return sb.toString();
    }

    /**
     * 图灵机器人回复
     */
    public static String showTuring(TurningBot bot)
    {
        return bot.getText();
    }

    /**
     * 获取成绩信息
     */
    public static String showCourse(String userName, String password,String season,String year)
    {
        // 登陆,设置Cookie
        String cookie = HttpUtil.login("http://jxxx.ncut.edu.cn/login.asp",userName,password);
        List<Course> courses = HtmlUtil.parseCourse("http://jxxx.ncut.edu.cn/xs/cjkb.asp?id=5&rxnf="+year+"&jb="+season+"&submit=查询",cookie);
        StringBuilder sb = new StringBuilder();
        for(Course course : courses)
        {
            sb.append("课程:").append(course.getCourseName()).append(",总评::").append(course.getFinalScore()).append("\n");
            sb.append("平时:").append(course.getNormalScore()).append(",期末:").append(course.getExamScore()).append("\n");
            sb.append("~\\(≧▽≦)/~").append("\n");
        }
        return sb.toString();
    }
    /**
     * 获取access_token
     * @Author NikoBelic
     * @Date 16/5/21 22:28
     */
    public static String showToken()
    {
        return WeiXinUtil.getAccessToken().getAccess_token();
    }

    /**************************** Xml转换方法 ********************************/

    /**
     * Xml转换为Map类型
     * @param request
     * @return
     * @throws IOException
     * @throws DocumentException
     */
    public static Map<String,String> xmlToMap(HttpServletRequest request) throws IOException, DocumentException
    {
        Map<String,String> map = new HashMap<String,String>();

        SAXReader reader = new SAXReader();

        InputStream ins = request.getInputStream();

        Document doc = reader.read(ins);

        Element root = doc.getRootElement();

        List<Element> list = root.elements();
        for (Element e : list)
        {
            map.put(e.getName(),e.getText());
        }
        ins.close();
        return map;
    }

    /**
     * 将文本消息对象转换为xml
     * @param textMessage
     * @return
     */
    public static String textMessageToXml(TextMessage textMessage)
    {
        XStream xstream = new XStream();
        // 需要将xml的根节点替换成xml
        xstream.alias("xml",TextMessage.class);
        return xstream.toXML(textMessage);
    }

    /**
     * 图文信息转为Xml
     */
    public static String newsMessageToXml(NewsMessage newsMessage)
    {
        XStream xStream = new XStream();
        xStream.alias("xml",NewsMessage.class);
        xStream.alias("item",News.class);
        return xStream.toXML(newsMessage);
    }
    /**
     * 图片信息转为Xml
     * @Author NikoBelic
     * @Date 16/5/20 15:20
     */
    public static String imageMessageToXml(ImageMessage imageMessage)
    {
        XStream xStream = new XStream();
        xStream.alias("xml",ImageMessage.class);
        return xStream.toXML(imageMessage);
    }
}

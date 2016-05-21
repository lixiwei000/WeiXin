package cn.edu.ncut.servlet;

import cn.edu.ncut.model.TurningBot;
import cn.edu.ncut.model.ip.IP;
import cn.edu.ncut.model.lottery.Lottery;
import cn.edu.ncut.model.weather.Weather;
import cn.edu.ncut.util.*;
import org.dom4j.DocumentException;
import sun.plugin2.message.Message;

import javax.servlet.ServletException;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.io.IOException;
import java.io.PrintWriter;
import java.util.Map;

/**
 * 与微信平台进行对接
 * @author NikoBelic
 * @create 12:25
 */
public class WeiXinServlet extends HttpServlet
{
    private static long time = 0;
    /**
     * doGet请求,和平台对接实现微信服务的认证
     * @param req
     * @param resp
     * @throws ServletException
     * @throws IOException
     */
    @Override
    protected void doGet(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException
    {
        String signature = req.getParameter("signature");
        String timestamp = req.getParameter("timestamp");
        String nonce = req.getParameter("nonce");
        String echostr = req.getParameter("echostr");
        PrintWriter out = resp.getWriter();

        if (CheckUtil.checkSignature(signature,timestamp,nonce))
        {
            out.print(echostr);
        }

    }

    /**
     * 回应Msg
     * @param req
     * @param resp
     * @throws ServletException
     * @throws IOException
     */
    @Override
    protected void doPost(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException
    {
        req.setCharacterEncoding("UTF-8");
        resp.setCharacterEncoding("UTF-8");
        /**
         * 1.微信平台发送过来的数据类型是XML,我们需要将其转换为集合类型
         * 2.实现将对象类型转换为XML类型的方法
         */
        PrintWriter out = resp.getWriter();
        try
        {
            Map<String,String> map = MessageUtil.xmlToMap(req);
            String fromUserName = map.get("FromUserName");
            String toUserName = map.get("ToUserName");
            String msgType = map.get("MsgType");
            String content = map.get("Content");
            String message = null;
            // 普通文字回复
            if (MessageUtil.MESSAGE_TEXT.equals(msgType))
            {
                // 关键字回复
                if ("1".equals(content))            // 公众号介绍
                {
                    message = MessageUtil.initText(toUserName,fromUserName,MessageUtil.platformInfo());
                }
                else if ("2".equals(content))       // 开发者介绍
                {
                    message = MessageUtil.initText(toUserName,fromUserName,MessageUtil.authorInfo());
                }
                else if ("3".equals(content))     // NCUT 图文消息
                {
                    message = MessageUtil.initNewsMessage(toUserName,fromUserName,MessageUtil.schoolInfo());
                }
                else if ("4".equals(content))
                {
                    String weatherJson = HttpUtil.getInfoLoadInstance().loadForString(Constant.BAIDU_WEATHER_URL,null);
                    System.out.println(weatherJson);
                    Weather weather = JsonUtils.getObject(weatherJson,Weather.class);
                    if (weather.getErrNum() == 0)
                        message = MessageUtil.initText(toUserName,fromUserName,MessageUtil.showTodayWeather(weather));
                    else
                        message = MessageUtil.initText(toUserName,fromUserName,"天气接口异常");
                }
                else if ("5".equals(content))
                {
                    String weatherJson = HttpUtil.getInfoLoadInstance().loadForString(Constant.BAIDU_WEATHER_URL,null);
                    System.out.println(weatherJson);
                    Weather weather = JsonUtils.getObject(weatherJson,Weather.class);
                    if (weather.getErrNum() == 0)
                        message = MessageUtil.initText(toUserName,fromUserName,MessageUtil.showForecastWeather(weather));
                    else
                        message = MessageUtil.initText(toUserName,fromUserName,"天气接口异常");
                }
                else if (content.startsWith("6 "))
                {
                    String userName;
                    String password;
                    String season;
                    String year;
                    try
                    {
                        userName = content.split(" ")[1];
                        password = content.split(" ")[2];
                        season = content.split(" ")[3];
                        year = content.split(" ")[4];
                    }
                    catch (Exception e)
                    {
                        userName = "";
                        password = "";
                        season = "";
                        year = "";
                    }
                    if (season.equals("1"))
                        season = "S";
                    else
                        season = "A";
                    // 登陆,获取Cookie,查成绩
                    if (userName.equals("") || password.equals("") || season.equals("") || year.equals(""))
                        message = MessageUtil.initText(toUserName,fromUserName, "输入信息格式错误\n6 学号 密码 季度 年份\n");
                    else
                    {
                        String scoreText = MessageUtil.showCourse(userName,password,season,year);
                        if (scoreText.equals(""))
                            message = MessageUtil.initText(toUserName,fromUserName, "暂无成绩 或 信息有误");
                        else
                            message = MessageUtil.initText(toUserName,fromUserName,scoreText);
                    }
                }
                else if ("7".equals(content))   // 获取双色球最新开奖结果
                {
                    String lotteryCode = "ssq";
                    String recordCount = "5";
                    String resultJson = WeiXinUtil.doGetStr(Constant.BAIDU_CAIPIAO_URL + "?lotterycode=" + lotteryCode + "&recordcnt="+recordCount);
                    Lottery lottery = JsonUtils.getObject(resultJson,Lottery.class);
                    message = MessageUtil.initText(toUserName,fromUserName,MessageUtil.showLottery(lottery));
                }
                //else if ("8".equals(content))   //  获取地理位置信息
                //{
                //    String ip = req.getRemoteAddr();
                //    System.out.println("IP_local" + req.getLocalAddr());
                //    String resultJson = WeiXinUtil.doGetStr(Constant.BAIDU_IPQUERY_URL+"?ip=" + ip);
                //    System.out.println(resultJson);
                //    IP ipObj = JsonUtils.getObject(resultJson, IP.class);
                //    message = MessageUtil.initText(toUserName,fromUserName,MessageUtil.showLocationByIp(ipObj));
                //}
                else if ("help".equals(content))    // 帮助菜单
                {
                    message = MessageUtil.initText(toUserName,fromUserName,MessageUtil.menuText());
                }

                else // 图灵机器人
                {
                    String key = Constant.TURING_TOKEN;
                    String info = content;
                    String userid = req.getRemoteAddr();
                    String args = "?" + "key=" + key + "&info=" + info + "&userid=" + userid;
                    String turingJson = HttpUtil.getInfoLoadInstance().loadForString(Constant.TURNING_URL + args,null);
                    System.out.println(turingJson);
                    TurningBot bot = JsonUtils.getObject(turingJson, TurningBot.class);
                    message = MessageUtil.initText(toUserName,fromUserName,bot.getText());
                }

            }
            // 事件回复
            else if (MessageUtil.MESSAGE_EVENT.equals(msgType))
            {
                String eventType = map.get("Event");
                System.out.println(eventType);
                // 订阅事件回复
                if (MessageUtil.MESSAGE_SUBSCRIBE.equals(eventType))
                {
                    message = MessageUtil.initText(toUserName,fromUserName,MessageUtil.menuText());
                }
                else if (MessageUtil.MESSAGE_CLICK.equals(eventType))
                {
                    message = MessageUtil.initText(toUserName,fromUserName,MessageUtil.menuText());
                }
                else if (MessageUtil.MESSAGE_VIEW.equals(eventType))
                {
                    String url = map.get("EventKey");
                    message = MessageUtil.initText(toUserName,fromUserName,url);
                }
                else if (MessageUtil.MESSAGE_SCANCODE.equals(eventType))
                {
                    String key = map.get("EventKey");
                    message = MessageUtil.initText(toUserName,fromUserName,key);
                }
            }
            else if (MessageUtil.MESSAGE_LOCATION.equals(msgType))
            {
                String label = map.get("Label");
                message = MessageUtil.initText(toUserName,fromUserName,label);
            }
            System.out.println(message);
            out.print(message);
        }
        catch (DocumentException e)
        {
            e.printStackTrace();
        }
        finally
        {
            out.close();
        }
    }
}

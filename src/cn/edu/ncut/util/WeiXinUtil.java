package cn.edu.ncut.util;

import cn.edu.ncut.model.AccessToken;
import cn.edu.ncut.model.MediaType;
import cn.edu.ncut.model.menu.Button;
import cn.edu.ncut.model.menu.ClickButton;
import cn.edu.ncut.model.menu.Menu;
import cn.edu.ncut.model.menu.ViewButton;
import com.google.gson.Gson;
import com.google.gson.JsonObject;
import org.apache.http.HttpEntity;
import org.apache.http.HttpResponse;
import org.apache.http.client.HttpClient;
import org.apache.http.client.methods.HttpGet;
import org.apache.http.client.methods.HttpPost;
import org.apache.http.entity.StringEntity;
import org.apache.http.impl.client.DefaultHttpClient;
import org.apache.http.util.EntityUtils;

import java.io.*;
import java.net.HttpURLConnection;
import java.net.URL;
import java.security.KeyManagementException;
import java.security.NoSuchAlgorithmException;
import java.security.NoSuchProviderException;

/**
 * @author NikoBelic
 * @create 10:57
 */
public class WeiXinUtil
{
    private static final String APPID = "wxa7717f4829d6644d";
    //private static final String APPID = "wxb4ad010301368cc6";
    private static final String APPSECRET = "40c574caf1223a0f50b5e25b43f121b5";
    //private static final String APPSECRET = "5c918d76b5876c99cd3277ee3c25bcbe";
    private static final String ACCESS_TOKEN_URL = "https://api.weixin.qq.com/cgi-bin/token?grant_type=client_credential&appid=APPID&secret=APPSECRET";
    private static final String UPLOAD_URL = "https://api.weixin.qq.com/cgi-bin/media/upload?access_token=ACCESS_TOKEN&type=TYPE";
    private static final String CREATE_MENU_URL = "https://api.weixin.qq.com/cgi-bin/menu/create?access_token=ACCESS_TOKEN";
    private static AccessToken accessToken;
    /**
     * get请求
     * @param url
     * @return
     */
    public static AccessToken doGetStr(String url)
    {
        HttpClient client = new DefaultHttpClient();
        HttpGet httpGet = new HttpGet(url);
        try
        {
            HttpResponse response = client.execute(httpGet);
            HttpEntity entity = response.getEntity();
            if (entity != null)
            {
                String result = EntityUtils.toString(entity);
                accessToken = JsonUtils.getObject(result,AccessToken.class);
            }

        } catch (IOException e)
        {
            e.printStackTrace();
        }
        return accessToken;
    }
    /**
     * post请求
     * @Author NikoBelic
     * @Date 16/5/20 11:38
     */
    public static String doPostStr(String url,String outStr)
    {
        HttpClient client = new DefaultHttpClient();
        HttpPost post = new HttpPost(url);
        String result = null;
        try
        {
            post.setEntity(new StringEntity(outStr,"UTF-8"));
            HttpResponse response = client.execute(post);
            result = EntityUtils.toString(response.getEntity(),"UTF-8");
        } catch (IOException e)
        {
            e.printStackTrace();
        }
        return result;
    }
    /**
     * 获取AccessToken--票据
     * @Author NikoBelic
     * @Date 16/5/20 13:04
     */
    public static AccessToken getAccessToken()
    {
        String url = ACCESS_TOKEN_URL.replace("APPID",APPID).replace("APPSECRET",APPSECRET);
        // 每1小时更新一次access_token
        long currTime = System.currentTimeMillis();
        long oldTime = Long.parseLong(ConfigSingleton.getInstance().getProperties().getProperty("flushTokenTime"));
        System.out.println("旧时间:" + oldTime);

        long seconds = (currTime - oldTime) / 1000;
        System.out.println("已经过去" + seconds + "秒");
        if (seconds > 3600)
        {
            System.out.println("重新获取AccessToken");
            accessToken = doGetStr(url);
            ConfigSingleton.getInstance().getProperties().setProperty("access_token", accessToken.getAccess_token());
            ConfigSingleton.getInstance().getProperties().setProperty("flushTokenTime", String.valueOf(currTime));
        }
        return accessToken;
    }
     /**
      * 文件上传通用方法
      * @Author NikoBelic
      * @Date 16/5/21 22:24
      */
    public static String upload(String filePath, String accessToken,String type) throws IOException, NoSuchAlgorithmException, NoSuchProviderException, KeyManagementException {
        File file = new File(filePath);
        if (!file.exists() || !file.isFile()) {
            throw new IOException("文件不存在");
        }

        String url = UPLOAD_URL.replace("ACCESS_TOKEN", accessToken).replace("TYPE",type);

        URL urlObj = new URL(url);
        //连接
        HttpURLConnection con = (HttpURLConnection) urlObj.openConnection();

        con.setRequestMethod("POST");
        con.setDoInput(true);
        con.setDoOutput(true);
        con.setUseCaches(false);

        //设置请求头信息
        con.setRequestProperty("Connection", "Keep-Alive");
        con.setRequestProperty("Charset", "UTF-8");

        //设置边界
        String BOUNDARY = "----------" + System.currentTimeMillis();
        con.setRequestProperty("Content-Type", "multipart/form-data; boundary=" + BOUNDARY);

        StringBuilder sb = new StringBuilder();
        sb.append("--");
        sb.append(BOUNDARY);
        sb.append("\r\n");
        sb.append("Content-Disposition: form-data;name=\"file\";filename=\"" + file.getName() + "\"\r\n");
        sb.append("Content-Type:application/octet-stream\r\n\r\n");

        byte[] head = sb.toString().getBytes("utf-8");

        //获得输出流
        OutputStream out = new DataOutputStream(con.getOutputStream());
        //输出表头
        out.write(head);

        //文件正文部分
        //把文件已流文件的方式 推入到url中
        DataInputStream in = new DataInputStream(new FileInputStream(file));
        int bytes = 0;
        byte[] bufferOut = new byte[1024];
        while ((bytes = in.read(bufferOut)) != -1) {
            out.write(bufferOut, 0, bytes);
        }
        in.close();

        //结尾部分
        byte[] foot = ("\r\n--" + BOUNDARY + "--\r\n").getBytes("utf-8");//定义最后数据分隔线

        out.write(foot);

        out.flush();
        out.close();

        StringBuffer buffer = new StringBuffer();
        BufferedReader reader = null;
        String result = null;
        try {
            //定义BufferedReader输入流来读取URL的响应
            reader = new BufferedReader(new InputStreamReader(con.getInputStream()));
            String line = null;
            while ((line = reader.readLine()) != null) {
                buffer.append(line);
            }
            if (result == null) {
                result = buffer.toString();
            }
        } catch (IOException e) {
            e.printStackTrace();
        } finally {
            if (reader != null) {
                reader.close();
            }
        }

        System.out.println(result);
        String mediaId = JsonUtils.getObject(result,MediaType.class).getMedia_id();
        return mediaId;
    }
    /**
     * 组装菜单
     * @Author NikoBelic
     * @Date 16/5/20 16:42
     */
    public static Menu initMenu()
    {
        Menu menu = new Menu();

        ClickButton button11 = new ClickButton();
        button11.setName("click菜单");
        button11.setType("click");
        button11.setKey("11");

        ViewButton button21 = new ViewButton();
        button21.setName("view菜单");
        button21.setType("view");
        button21.setUrl("http://www.ncut.edu.cn");

        ClickButton button31 = new ClickButton();
        button31.setName("扫码事件");
        button31.setType("scancode_push");
        button31.setKey("31");

        ClickButton button32 = new ClickButton();
        button32.setName("地理位置");
        button32.setType("location_select");
        button32.setKey("32");

        Button button3 = new Button();
        button3.setName("菜单");
        button3.setSub_button(new Button[]{button31,button32});

        menu.setButton(new Button[]{button11,button21,button3});
        return menu;
    }

    /**
     * json串转换为指定类对象
     * @Author
     * @Date 16/5/21 22:25
     */
    private static <T extends Serializable> T jsonTransfer(String json ,Class<T> clz)
    {
        return JsonUtils.getObject(json,clz);
    }
    /**
     * 创建菜单栏
     * @Author NikoBelic
     * @Date 16/5/21 22:23
     */
    public static int createMenu(String token,String menu)
    {
        int result = 0;
        String url = CREATE_MENU_URL.replace("ACCESS_TOKEN",token);
        String resultJson = doPostStr(url,menu);
        System.out.println("resultJson:" + resultJson);
        Menu menuObj = jsonTransfer(resultJson,Menu.class);
        return result;
    }

    public static void main(String[] args)
    {
        // 测试Token,返回图片信息
        //try
        //{
        //    AccessToken token = WeiXinUtil.getAccessToken();
        //    System.out.println("票据:"+token.getAccess_token());
        //    System.out.println("有效时间:" + token.getExpires_in());
        //    String path = "/Users/lixiwei-mac/Documents/pictures/stock-photo-108182811.jpg";
        //    String mediaId = WeiXinUtil.upload(path,token.getAccess_token(),"image");
        //    System.out.println("MediaId:" + mediaId);
        //} catch (Exception e)
        //{
        //    e.printStackTrace();
        //}

        // 测试创建Menu
        //AccessToken token = WeiXinUtil.getAccessToken();
        //String menu = JsonUtils.toJson(initMenu());
        //System.out.println("menu:" + menu);
        //System.out.println("票据:"+token.getAccess_token());
        //System.out.println("有效时间:" + token.getExpires_in());
        //createMenu(token.getAccess_token(),menu);
    }

}

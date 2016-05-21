package cn.edu.ncut.util;

import java.io.InputStream;
import java.util.Properties;

/**
 * 配置文件读取工具
 * Created by lixiwei on 2016/4/13.
 */
public class ConfigSingleton {

    private static ConfigSingleton instance = new ConfigSingleton();

    private Properties properties;

    public Properties getProperties() {
        return properties;
    }

    public void setProperties(Properties properties) {
        this.properties = properties;
    }

    private ConfigSingleton() {
        properties = new Properties();
        InputStream is = null;
        try {
            is = ConfigSingleton.class
                    .getResourceAsStream("/config.properties");
            properties.load(is);
        } catch (Exception e) {
            System.out.println("装载配置文件出错，具体堆栈信息如下：");
            System.out.println(e.toString());
        } finally {
            try {
                is.close();
            } catch (Exception e) {
                System.out.println(e.toString());
            }
        }
    }

    public static synchronized ConfigSingleton getInstance() {
        return instance;
    }
}
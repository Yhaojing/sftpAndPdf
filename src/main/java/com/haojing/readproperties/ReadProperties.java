package com.haojing.readproperties;

        import java.io.IOException;
        import java.io.InputStream;
        import java.util.Properties;

/**
 * Created by hnzb on 16/1/7.
 */
public class ReadProperties {
    public static String host ;
    public static int port ;
    public static String username ;
    public static String password ;

    static {
        InputStream inputStream = ReadProperties.class.getClassLoader().getResourceAsStream("sftp_file_config.properties");
        //不可使用this.getClass().getClassLoader()...因为指向该对象的所有类,此时没有创建对象,静态代码块是属于类的
        Properties properties = new Properties();
        try {
            properties.load(inputStream);
            host = properties.getProperty("sftp.asset.host");
            port = Integer.valueOf(properties.getProperty("sftp.asset.port"));
            username = properties.getProperty("sftp.asset.username");
            password = properties.getProperty("sftp.asset.password");
        } catch (IOException e) {
            e.printStackTrace();
        }
    }
    public static void main(String[] args) {
        System.out.println(ReadProperties.host);
    }
}

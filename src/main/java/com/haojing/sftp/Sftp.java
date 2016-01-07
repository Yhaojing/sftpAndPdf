package com.haojing.sftp;

import com.haojing.readproperties.ReadProperties;
import com.jcraft.jsch.*;

import java.io.File;
import java.io.FileInputStream;
import java.io.FileOutputStream;
import java.io.OutputStream;
import java.util.Properties;

/**
 * Created by hnzb on 16/1/7.
 */
public class Sftp {
    private static final String host = ReadProperties.host;
    private static final int port= ReadProperties.port;
    private static final String username = ReadProperties.username;
    private static final String password = ReadProperties.password;

    //连接sftp服务器
    public ChannelSftp connect() {
        ChannelSftp sftp = null;
        JSch jSch = new JSch();
        try {
            jSch.getSession(username, host, port);
            Session session = jSch.getSession(username, host, port);
            session.setPassword(password);
            Properties sshConfig = new Properties();
            sshConfig.put("StrictHostKeyChecking", "no");
            session.setConfig(sshConfig);
            session.connect();
            Channel channel = session.openChannel("sftp");
            channel.connect();
            sftp = (ChannelSftp) channel;
        } catch (JSchException e) {
            e.printStackTrace();
        }
        return sftp;
    }

    public void upload(String directory, String uploadFile, ChannelSftp sftp) {
        try {
            sftp.cd(directory);
            File file=new File(uploadFile);
            sftp.put(new FileInputStream(file), file.getName());
        } catch (Exception e) {
            e.printStackTrace();
        }
    }

    public void download(String directory, String downloadFile, String saveFile, ChannelSftp sftp) {
        try {
            sftp.cd(directory);
            File file=new File(saveFile);
            sftp.get(downloadFile, new FileOutputStream(file));
        } catch (Exception e) {
            e.printStackTrace();
        }
    }

    public void download(String directory, OutputStream dst, String downloadFile, ChannelSftp sftp) {
        try {
            sftp.cd(directory);
            sftp.get(downloadFile, dst);
        } catch (Exception e) {
            e.printStackTrace();
        }
    }

    public void delete(String directory, String deleteFile, ChannelSftp sftp) {
        try {
            sftp.cd(directory);
            sftp.rm(deleteFile);
        } catch (Exception e) {
            e.printStackTrace();
        }
    }

    public void disconnect(ChannelSftp sftp) {
        if(sftp != null){
            if(sftp.isConnected()){
                sftp.disconnect();
            }else if(sftp.isClosed()){
                System.out.println("sftp is closed already");
            }
        }
    }

}

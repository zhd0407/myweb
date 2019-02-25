package com.base.myweb.core.tools;

import java.util.Properties;
import javax.activation.DataHandler;
import javax.activation.FileDataSource;
import javax.mail.*;
import javax.mail.internet.*;

public class SendMail {

    /**
     * @param args
     */
    private MimeMessage mimeMeg;   //整个MIME邮件对象
    private Session session;       //专门用来发送Session会话
    private Properties props;      //配置信息的一个属性对象
    private String username;
    private String password;
    private Multipart mp;          //实现附件添加的组件
    public SendMail(String smtp){
        username = "";
        password = "";
        setSmtpHost(smtp);
        createMimeMessage();

    }
    /**
     * (这个Session类代表JavaMail 中的一个邮件session. 每一个基于
     * JavaMail的应用程序至少有一个session但是可以有任意多的session。 在这个例子中,
     * Session对象需要知道用来处理邮件的SMTP 服务器。
     */
    private boolean createMimeMessage() {
        // TODO Auto-generated method stub
        try{
            //System.out.println("准备获取邮件会话对象！");
            session =  Session.getDefaultInstance(props,null);
        }catch(Exception e)
        {
            System.err.println("获取邮件会话对象时发生错误"+ e );
            return false;
        }
        //System.out.println("准备创建MIME邮件对象");
        try
        {
            mimeMeg =new MimeMessage(session);
            mp=new MimeMultipart();
        }catch(Exception e)
        {
            //System.err.println("创建MIME邮件对象失败"+ e);
            return false;
        }
        return true;
    }
    /**
     * 设置发送邮件的主机(JavaMail需要Properties来创建一个session对象。
     * 它将寻找字符串"mail.smtp.host"，属性值就是发送邮件的主机);
     *
     * @param hostName
     */
    private void setSmtpHost(String hostName) {
        // TODO Auto-generated method stub
        //System.out.println("设置系统属性：mail.smtp.host=" + hostName);
        if(props==null)
            props = System.getProperties();
        props.put("mail.smtp.host", hostName);

    }
    public void setNeedAuth(boolean need){
        //System.out.println("设置smtp身份认证：mail.smtp.auth= " + need);
        if(props==null){
            props = System.getProperties();
        }
        if(need){
            props.put("mail.smtp.auth", "true");
        }else{
            props.put("mail.smtp.auth","false");
        }
    }
    /*
     * 进行用户身份验证时，设置用户名和密码
     * */
    public void setNamePass(String name,String pass){
        //System.out.println("程序得到用户名和密码");
        username = name;
        password = pass;
    }
    /*
     * 设置邮件主题
     *
     * */
    public boolean setSubject(String mailSubject){
        //System.out.println("设置邮件主题");
        try{
            mimeMeg.setSubject(mailSubject);
        }catch(Exception e){
            System.err.println("设置邮件主题发生错误");
            return false;
        }
        return true;
    }
    /**
     * 设置邮件内容,并设置其为文本格式或HTML文件格式，编码方式为UTF-8
     *
     * @param mailBody
     * @return
     */
    public boolean setBody(String mailBody){
        try{
            //System.out.println("设置邮件格式");
            BodyPart bp = new MimeBodyPart();
            bp.setContent("<meta http-equiv=Content-Type content=text/html; charset=UTF-8>" + mailBody,
                    "text/html;charset=UTF-8");
            //在组件上添加邮件文本
            mp.addBodyPart(bp);
        }catch(Exception e){
            System.err.println("设置邮件正文时发生错误！"+e);
            return false;
        }
        return true;
    }
    /**
     * 增加发送附件
     *
     * @param filename
     *            邮件附件的地址，只能是本机地址而不能是网络地址，否则抛出异常
     * @return
     */
    public boolean addFileAffix(String filename){
        //System.out.println("增加邮件附件："+filename);
        try{
            BodyPart bp = new MimeBodyPart();
            FileDataSource fileds = new FileDataSource(filename);
            bp.setDataHandler(new DataHandler(fileds));
            //发送的附件前加上一二用户名的前缀
            bp.setFileName(fileds.getName());
            //添加附件
            mp.addBodyPart(bp);
        }catch(Exception e){
            System.err.println("增加邮件附件："+filename+"发生错误！"+e);
            return false;
        }
        return true;
    }
    /**
     * 设置发件人地址
     *
     * @param from
     *            发件人地址
     * @return
     */
    public boolean setFrom(String from){
        //System.out.println("设置发信人！");
        try{
            mimeMeg.setFrom(new InternetAddress(from));
        }catch(Exception e){
            return false;
        }
        return true;
    }
    /**
     * 设置收件人地址
     *
     * @param to
     *            收件人的地址
     * @return
     */
    public boolean setTo(String to){
        //System.out.println("设置收信人");
        if(to==null){return false;}
        try{
            mimeMeg.setRecipients(javax.mail.Message.RecipientType.TO, InternetAddress.parse(to));
        }catch(Exception e){
            return false;
        }
        return true;
    }
    /**
     * 发送附件
     *
     * @param copyto
     * @return
     */
    public boolean setCopyTo(String copyto){
        //System.out.println("发送附件到");
        if(copyto ==null){return false;}
        try{
            mimeMeg.setRecipients(javax.mail.Message.RecipientType.CC, InternetAddress.parse(copyto));
        }catch(Exception e){
            return false;
        }
        return true;
    }
    /**
     * 发送邮件
     *
     * @return
     */
    public boolean sendout(){
        try{
            mimeMeg.setContent(mp);
            mimeMeg.saveChanges();
            //System.out.println("正在发送邮件...");
            Session mailSession = Session.getInstance(props, null);
            Transport transport =mailSession.getTransport("smtp");
            //真正的链接邮件服务器并进行身份验证
            transport.connect((String)props.get("mail.smtp.host"),username,password);
            //发送邮件
            transport.sendMessage(mimeMeg, mimeMeg.getRecipients(javax.mail.Message.RecipientType.TO));
            //System.out.println("发送邮件成功！");
            transport.close();
        }catch(Exception e ){
            //System.out.println("邮件发送失败！"+ e.getMessage());
            e.printStackTrace();
            return false;
        }
        return true;
    }

}


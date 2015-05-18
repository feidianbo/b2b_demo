package demo.core.service;

import demo.ext.WithLogger;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.context.annotation.PropertySource;
import org.springframework.mail.SimpleMailMessage;
import org.springframework.mail.javamail.JavaMailSenderImpl;
import org.springframework.stereotype.Service;

import java.io.PrintWriter;
import java.io.StringWriter;
import java.time.ZonedDateTime;

/**
 * Created by joe on 2/26/15.
 */
@Service
@PropertySource("classpath:/mail.properties")
public class ExceptionReporter implements  WithLogger {
    // http://docs.spring.io/spring/docs/current/spring-framework-reference/html/mail.html
    protected JavaMailSenderImpl javaMailSender = new JavaMailSenderImpl();

    @Autowired
    protected MailMessage mm ;


    @Value("${mail.mailMethod}")
    private String mailMethod = "";

    @Value("${mail.to}")
    private String to;

    public void handle(Exception ex,String requestUrl,String formData){
        if("sendcloud".equalsIgnoreCase(mailMethod)){
            logger().info("正在尝试使用sendcloud发送邮件");
           // mm.setText("requestUrl:\t" + requestUrl + "\n fromData={" + formData + "}\n" + ex.getMessage() + " " + getStackTrace(ex));
           // mm.setSubject(ZonedDateTime.now().toString() +" "+ ex.getClass().getName());
            boolean success = false ;
            try {
                success =  mm.send(
                        "requestUrl:\t" + requestUrl + "\n fromData={" + formData + "}\n" + ex.getMessage() + " " + getStackTrace(ex),
                        ZonedDateTime.now().toString() +" "+ ex.getClass().getName());
            } catch (Exception e) {
                logger().error("发送报警邮件报错"+e.getStackTrace().toString());
            }
            if(!success){
                logger().warn("sendCloud发送邮件失败，正在尝试使用SimpleMailMessage发送...");
                sendSimpleMail(ex, requestUrl, formData);
            }
        } else{ //if ("simplemail".equalsIgnoreCase(mailMethod)) {
            logger().info("正在尝试使用simpleMail发送邮件");
            sendSimpleMail(ex, requestUrl, formData);
        }
    }

    private void sendSimpleMail(Exception ex, String requestUrl, String formData) {
        SimpleMailMessage msg = new SimpleMailMessage();
        msg.setFrom("warning@server.xxx.com");
        msg.setTo(to.split(";"));
        msg.setSubject(ZonedDateTime.now().toString() +" "+ ex.getClass().getName());
        msg.setText("requestUrl:\t"+requestUrl+"\n fromData={"+formData+"}\n"+ ex.getMessage() + " " + getStackTrace(ex));
        try {
            javaMailSender.send(msg);
        } catch (Exception x) {
            logger().error("SimpleMailMessage发送报警邮件失败：",x);
        }
    }

    public static String getStackTrace(final Throwable throwable) {
        final StringWriter sw = new StringWriter();
        final PrintWriter pw = new PrintWriter(sw, true);
        throwable.printStackTrace(pw);
        return sw.getBuffer().toString();
    }
}

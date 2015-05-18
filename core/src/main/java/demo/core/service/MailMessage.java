package demo.core.service;

import java.io.IOException;
import java.nio.charset.Charset;
import java.util.List;

import org.apache.http.HttpResponse;
import org.apache.http.HttpStatus;
import org.apache.http.client.HttpClient;
import org.apache.http.client.methods.HttpPost;
import org.apache.http.entity.mime.HttpMultipartMode;
import org.apache.http.entity.mime.MultipartEntity;
import org.apache.http.entity.mime.content.StringBody;
import org.apache.http.impl.client.DefaultHttpClient;
import org.apache.http.util.EntityUtils;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.context.annotation.PropertySource;
import org.springframework.stereotype.Service;

import com.fasterxml.jackson.databind.ObjectMapper;

import demo.ext.WithLogger;

/**
 * 邮件发送
 */
@Service
@PropertySource({"file:/data/conf/mail.properties"})
public class MailMessage implements WithLogger {

    @Value("${mail.url}")
    private String url;

    @Value("${mail.user}")
    private String user;

    @Value("${mail.key}")
    private String key;

    @Value("${mail.from}")
    private String from;

    @Value("${mail.to}")
    private String to;

    @Autowired
    ObjectMapper om  ;

    private String subject;

    private String text ;

    public boolean  send(String  text ,String subject) throws IOException {

        this.text  =  text ;
        this.subject =  subject ;
        boolean  result ;

        HttpPost httpPost = new HttpPost(url);
        HttpClient httpclient = new DefaultHttpClient();
        // 组装基本邮件的参数
        MultipartEntity entity = new MultipartEntity(HttpMultipartMode.BROWSER_COMPATIBLE, null, Charset.forName("UTF-8"));
        entity.addPart("api_user", new StringBody(user, Charset.forName("UTF-8")));
        entity.addPart("api_key", new StringBody(key, Charset.forName("UTF-8")));
        entity.addPart("to", new StringBody(to, Charset.forName("UTF-8")));
        entity.addPart("from", new StringBody(from, Charset.forName("UTF-8")));
        entity.addPart("fromname", new StringBody("server.xxx.com", Charset.forName("UTF-8")));
        entity.addPart("subject", new StringBody(subject, Charset.forName("UTF-8")));
        entity.addPart("html", new StringBody(text, Charset.forName("UTF-8")));

        //entity.addPart("html", new StringBody("<html><head></head><body><p>欢迎使用<a href='http://sendcloud.sohu.com'>SendCloud</a></p></body></html>", Charset.forName("UTF-8")));
        //entity.addPart("resp_email_id", new StringBody("true"));

        // 添加附件, 文件流形式
        /*
         File file = new File("aa.txt");
         String attachName = "attach.txt";
         InputStreamBody is = new InputStreamBody(new FileInputStream(file), attachName);
         entity.addPart("files", is);
         */

        httpPost.setEntity(entity);
        HttpResponse response = null;
        try {
            response = httpclient.execute(httpPost);
        } catch (IOException e) {
            result = false ;
            httpPost.releaseConnection();
            logger().error("发送邮件失败（sendCloud）：" + e.getStackTrace().toString());
        }
        // 处理响应
        if (response.getStatusLine().getStatusCode() == HttpStatus.SC_OK) {
            // 正常返回, 解析返回数据
            String json = EntityUtils.toString(response.getEntity()) ;
            ReturnBody rb = om.readValue(json, ReturnBody.class);
            if (rb.isSuccess()) {
                result = true ;
                logger().info("发送邮件成功(sendCloud):" + rb.toString());
            } else {
                result =  false ;
                logger().error("发送邮件从sendCloud返回失败:"+ rb.toString());
            }
            httpPost.releaseConnection();
        } else {
            result = false ;
            logger().error( "发送邮件失败，返回失败(sendCloud)：" + EntityUtils.toString(response.getEntity()));
            httpPost.releaseConnection();
        }
        return result ;
    }


    public String getSubject() {
        return subject;
    }

    public void setSubject(String subject) {
        this.subject = subject;
    }

    public String getText() {
        return text;
    }

    public void setText(String text) {
        this.text = text;
    }

}
//{"message":"error","errors":["Bad username / password!"]}
//{"message":"success"}
class ReturnBody{

    String message ;

    List<String> errors ;

    public boolean isSuccess(){
        return "success".equals(message) ;
    }

    public String getMessage() {
        return message;
    }

    public void setMessage(String message) {
        this.message = message;
    }

    public List<String> getErrors() {
        return errors;
    }

    public void setErrors(List<String> errors) {
        this.errors = errors;
    }

    @Override
    public String toString() {
        return "ReturnBody{" +
                "message='" + message + '\'' +
                ", errors=" + errors +
                '}';
    }

}

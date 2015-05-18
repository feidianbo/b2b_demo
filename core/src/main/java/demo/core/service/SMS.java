package demo.core.service;

import org.springframework.beans.factory.annotation.Value;
import org.springframework.context.annotation.PropertySource;
import org.springframework.stereotype.Service;

import cn.emay.sdk.client.api.Client;

/**
 * Created by lxj on 14/11/6.
 */
@Service
@PropertySource({"file:/data/conf/sms.properties"})
public class SMS {
	@Value("${sms.sn}")
	private String sn;
	@Value("${sms.password}")
	private String password;
	@Value("${sms.server}")
	private String server;
	
	private static Client client = null;

	public void send(String phone, String content, String hellowords, String signature) throws Exception {
	    
	    content = content + "【上海红酒交易中心】";
	    int i = getClient("9SDK-EMY-0999-JBUSL", "A1D49A149F2A183285676D7B7355498E").sendSMS(new String[]{phone}, content, 3);
	    
		//SMSClient smsClient = new SMSClient(server, sn, password);
		//smsClient.sendSMS(phone, content, hellowords, signature);
		//SMSResponse smsResponse = smsClient.sendSMS(phone, content, hellowords, signature);

		//System.out.println("***************************************");
		//System.out.println(smsResponse.getCode() + "--------------");
		//System.out.println(smsResponse.getCode());
		//System.out.println("***************************************");
		System.out.println("---------" + phone + "-------" + content + "-------" + i);
		
        if (i != 0) {
            throw new RuntimeException("send sms error, code: " + i);
        }
	}
	
	public synchronized static Client getClient(String softwareSerialNo, String key) {
        if (client == null) {
            try {
                client = new Client(softwareSerialNo, key);
            } catch (Exception e) {
                e.printStackTrace();
            }
        }
        return client;
    }
}

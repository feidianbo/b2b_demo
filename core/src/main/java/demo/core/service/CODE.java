package demo.core.service;

import org.springframework.stereotype.Service;

import java.util.Random;

/**
 * Created by jack on 15/1/14.
 */
@Service
public class CODE {
    //生成6位随机数字符串
    public String CreateCode(){
        Random random = new Random();
        String code = "";
        for (int i = 0; i < 6; i++) {
            code += String.valueOf(random.nextInt(10));
        }
        return code;
    }
}

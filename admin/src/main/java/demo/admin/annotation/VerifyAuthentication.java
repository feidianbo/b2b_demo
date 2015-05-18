package demo.admin.annotation;

import java.lang.annotation.ElementType;
import java.lang.annotation.Retention;
import java.lang.annotation.RetentionPolicy;
import java.lang.annotation.Target;

/**
 * Created by zhangbolun on 15/3/5.
 */
@Target({ElementType.METHOD, ElementType.TYPE})
@Retention(RetentionPolicy.RUNTIME)
public @interface VerifyAuthentication{
    boolean Admin() default false;                 //超级管理员
    boolean Trader() default false;                //交易员
    boolean TraderAssistant() default false;       //交易员助理
    boolean NetworkEditor() default false;         //网编
    boolean Finance() default false;               //财务
    boolean LegalPersonnel() default false;        //法务
    boolean Operation() default false;             //运营（产品）
    boolean BackgroundSupporter() default false;   //后台管理员
}

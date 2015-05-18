package demo.admin.basic.exception;

/**
 * Created by jack on 4/16/15.
 */
public class BusinessException extends RuntimeException {
    public BusinessException(String message){
        super(message);
    }
}

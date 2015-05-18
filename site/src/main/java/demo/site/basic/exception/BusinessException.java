package demo.site.basic.exception;

/**
 * Created by hongpf on 15/4/15.
 */
public class BusinessException  extends RuntimeException {
    public BusinessException(String message ){
        super(message);
    }
}
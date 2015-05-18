package demo.admin.basic.exception;

import org.springframework.http.HttpStatus;
import org.springframework.web.bind.annotation.ResponseStatus;

/**
 * Created by zhangbolun on 15/3/6.
 */
@ResponseStatus(HttpStatus.FORBIDDEN)
public class ForbiddenException  extends RuntimeException  {
}

package demo.admin.service;
import demo.admin.annotation.VerifyAuthentication;
import demo.admin.basic.exception.ForbiddenException;
import demo.core.domain.AuthenticationRole;
import demo.core.domain.Role;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.web.method.HandlerMethod;
import org.springframework.web.servlet.handler.HandlerInterceptorAdapter;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.util.List;

/**
 * Created by zhangbolun on 15/3/5.
 */
@Service
public class VerifyAuthenticationHandler extends HandlerInterceptorAdapter {
    @Autowired
    protected Session session;
    @Override
    public boolean preHandle(HttpServletRequest request, HttpServletResponse response, Object handler) throws Exception {
        if (handler instanceof HandlerMethod) {
            HandlerMethod method = (HandlerMethod) handler;
            if(session.getVerifyAuthentication()&&(method.getMethodAnnotation(VerifyAuthentication.class) != null||method.getBeanType().getDeclaredAnnotation(VerifyAuthentication.class) != null)) {
                boolean isAdmin=false;
                boolean isBackgroundSupporter=false;
                boolean isFinance=false;
                boolean isLegalPersonnel=false;
                boolean isNetworkEditor=false;
                boolean isOperation=false;
                boolean isTrader=false;
                boolean isTraderAssistant=false;

                if (method.getMethodAnnotation(VerifyAuthentication.class) != null) {
                     isAdmin = method.getMethodAnnotation(VerifyAuthentication.class).Admin();
                     isBackgroundSupporter = method.getMethodAnnotation(VerifyAuthentication.class).BackgroundSupporter();
                     isFinance = method.getMethodAnnotation(VerifyAuthentication.class).Finance();
                     isLegalPersonnel = method.getMethodAnnotation(VerifyAuthentication.class).LegalPersonnel();
                     isNetworkEditor = method.getMethodAnnotation(VerifyAuthentication.class).NetworkEditor();
                     isOperation = method.getMethodAnnotation(VerifyAuthentication.class).Operation();
                     isTrader = method.getMethodAnnotation(VerifyAuthentication.class).Trader();
                     isTraderAssistant = method.getMethodAnnotation(VerifyAuthentication.class).TraderAssistant();
                }

                if (method.getBeanType().getDeclaredAnnotation(VerifyAuthentication.class) != null) {
                     isAdmin = method.getBeanType().getDeclaredAnnotation(VerifyAuthentication.class).Admin();
                     isBackgroundSupporter = method.getBeanType().getDeclaredAnnotation(VerifyAuthentication.class).BackgroundSupporter();
                     isFinance = method.getBeanType().getDeclaredAnnotation(VerifyAuthentication.class).Finance();
                     isLegalPersonnel = method.getBeanType().getDeclaredAnnotation(VerifyAuthentication.class).LegalPersonnel();
                     isNetworkEditor = method.getBeanType().getDeclaredAnnotation(VerifyAuthentication.class).NetworkEditor();
                     isOperation = method.getBeanType().getDeclaredAnnotation(VerifyAuthentication.class).Operation();
                     isTrader = method.getBeanType().getDeclaredAnnotation(VerifyAuthentication.class).Trader();
                     isTraderAssistant = method.getBeanType().getDeclaredAnnotation(VerifyAuthentication.class).TraderAssistant();
                }

                boolean isauthorised = verifyAuthentication(isAdmin, isBackgroundSupporter, isFinance, isLegalPersonnel, isNetworkEditor, isOperation, isTrader, isTraderAssistant);

                if (!isauthorised) {
                    throw new ForbiddenException();
                }
            }

        }
        return super.preHandle(request, response, handler);
    }


    private  boolean verifyAuthentication(boolean isAdmin,boolean isBackgroundSupporter,boolean isFinance,boolean isLegalPersonnel,boolean isNetworkEditor,boolean isOperation,boolean isTrader,boolean isTraderAssistant){

        if(isAdmin)
            isAdmin= checkRole(AuthenticationRole.Admin);
        if(isBackgroundSupporter)
            isBackgroundSupporter= checkRole(AuthenticationRole.BackgroundSupporter);
        if(isFinance)
            isFinance= checkRole(AuthenticationRole.Finance);
        if(isLegalPersonnel)
            isLegalPersonnel= checkRole(AuthenticationRole.LegalPersonnel);
        if(isNetworkEditor)
            isNetworkEditor= checkRole(AuthenticationRole.NetworkEditor);
        if(isOperation)
            isOperation= checkRole(AuthenticationRole.Operation);
        if(isTrader)
            isTrader= checkRole(AuthenticationRole.Trader);
        if(isTraderAssistant)
            isTraderAssistant= checkRole(AuthenticationRole.TraderAssistant);

        return isAdmin||isBackgroundSupporter||isFinance||isLegalPersonnel||isNetworkEditor||isOperation||isTrader||isTraderAssistant;
    }

    private boolean checkRole(AuthenticationRole authenticationRole){
        List<Role> roleList = session.getRoleList();
        boolean result=false;
        for(Role role:roleList){
            if(role.getRolecode().equals(authenticationRole.toString())){
                result=true;
            }
        }
        return result;
    }
}

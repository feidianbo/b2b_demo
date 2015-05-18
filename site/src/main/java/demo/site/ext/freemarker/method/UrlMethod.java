package demo.site.ext.freemarker.method;

import freemarker.template.*;
import demo.site.ext.freemarker.Utils;
import org.apache.http.NameValuePair;
import org.apache.http.client.utils.URLEncodedUtils;
import org.apache.http.message.BasicNameValuePair;

import java.net.URI;
import java.net.URISyntaxException;
import java.util.ArrayList;
import java.util.List;
import java.util.Map;

/**
 * Created by joe on 14-12-01.
 */
public class UrlMethod implements TemplateMethodModelEx {
    @Override
    public Object exec(List arguments) throws TemplateModelException {
        if(arguments.size()<1)
            throw new TemplateModelException("arguments takes at least 1 parameter");
        String path=arguments.get(0).toString();
        TemplateHashModelEx params=null;
        if(arguments.size()>1 && arguments.get(1) instanceof TemplateHashModelEx){
            Object tmp=arguments.get(1);
            if(tmp instanceof TemplateHashModelEx){
                params=(TemplateHashModelEx)tmp;
            }else
                throw new TemplateModelException("second parameter must be map");
        }
        return Utils.makeURI(path, params);
    }
}

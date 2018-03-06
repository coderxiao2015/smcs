package com.tianwen.common.shiro.filter;

import java.text.SimpleDateFormat;
import java.util.Date;

import javax.servlet.ServletRequest;
import javax.servlet.ServletResponse;
import javax.servlet.http.HttpServletRequest;

import org.apache.shiro.web.filter.authz.AuthorizationFilter;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.web.util.WebUtils;  

  
//import com.silvery.utils.PatternUtils;  
//import com.silvery.utils.WebUtils;  
/**  
 *   
 * 黑名单可执行程序请求过滤器  
 *   
 * @author cliu  
 *   
 */  
public class SimpleExecutiveFilter extends AuthorizationFilter {  
  
    protected static final String[] blackUrlPathPattern = new String[] { "*.aspx*", "*.asp*", "*.php*", "*.exe*",  
            "*.jsp*", "*.pl*", "*.py*", "*.groovy*", "*.sh*", "*.rb*", "*.dll*", "*.bat*", "*.bin*", "*.dat*",  
            "*.bas*", "*.c*", "*.cmd*", "*.com*", "*.cpp*", "*.jar*", "*.class*", "*.lnk*" };  
  
    private static final Logger log = LoggerFactory.getLogger(SimpleExecutiveFilter.class);  
  
    @Override  
    protected boolean isAccessAllowed(ServletRequest request, ServletResponse response, Object obj) throws Exception {  
  
        HttpServletRequest httpRequest = (HttpServletRequest) request;  
        System.out.println("OOOOOOOOOOOOOOOOOOOOOOOOOOOOO11");
  
        String reqUrl = httpRequest.getRequestURI().toLowerCase().trim();  
  
       for (String pattern : blackUrlPathPattern) {  
           /* if (PatternUtils.simpleMatch(pattern, reqUrl)) {  
                log.error(new StringBuffer().append("unsafe request >>> ").append(" request time: ").append(  
                        new SimpleDateFormat("yyyy-MM-dd HH:mm:ss").format(new Date())).append("; request ip: ")  
                        .append(WebUtils.getClientIP()).append("; request url: ").append(httpRequest.getRequestURI())  
                        .toString());  
                return false;  
            }  */
        } 
  
        return true;  
  
    }  
  
}  

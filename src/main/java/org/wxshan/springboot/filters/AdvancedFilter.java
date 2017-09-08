package org.wxshan.springboot.filters;

import javax.servlet.*;
import javax.servlet.annotation.WebFilter;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.io.*;
import java.util.ArrayList;
import java.util.List;
import java.util.Map;
import java.util.Set;

/**
 * Created by Administrator on 2017/9/7 0007.
 */
@WebFilter(asyncSupported = true)
public class AdvancedFilter implements Filter{

    private FilterConfig filterConfig = null;

    @Override
    public void init(FilterConfig filterConfig) throws ServletException {
        //得到过滤器的初始化配置信息
        this.filterConfig = filterConfig;
    }

    @Override
    public void doFilter(ServletRequest servletRequest, ServletResponse servletResponse, FilterChain filterChain) throws IOException, ServletException {
        HttpServletRequest request = (HttpServletRequest) servletRequest;
        HttpServletResponse response = (HttpServletResponse) servletResponse;

        Map<String, String[]> parameterMap = request.getParameterMap();
        Set<String> keySet = parameterMap.keySet();
        for (String string:keySet) {

            String[] strings = parameterMap.get(string);

            List<String> stringList = new ArrayList<>();
            for (String value:stringList) {

                value = filter(value);
                stringList.add(value);
            }

            parameterMap.put(string,stringList.toArray(new String[stringList.size()]));
        }

        filterChain.doFilter(servletRequest,servletResponse);
    }

    @Override
    public void destroy() {

    }

    public String filter(String value) {
        if (value == null) {
            return null;
        }
        char content[] = new char[value.length()];
        value.getChars(0, value.length(), content, 0);
        StringBuffer result = new StringBuffer(content.length + 50);
        for (int i = 0; i < content.length; i++) {
            switch (content[i]) {
                case '<':
                    result.append("&lt;");
                    break;
                case '>':
                    result.append("&gt;");
                    break;
                case '&':
                    result.append("&amp;");
                    break;
                case '"':
                    result.append("&quot;");
                    break;
                default:
                    result.append(content[i]);
            }
        }
        return (result.toString());
    }

    private List<String> getDirtyWords() {
        List<String> dirtyWords = new ArrayList<String>();
        String dirtyWordPath = filterConfig.getInitParameter("dirtyWord");
        InputStream inputStream = filterConfig.getServletContext()
                .getResourceAsStream(dirtyWordPath);
        InputStreamReader is = null;
        try {
            String defaultCharset = "UTF-8";
            is = new InputStreamReader(inputStream, defaultCharset);
        } catch (UnsupportedEncodingException e2) {
            e2.printStackTrace();
        }
        BufferedReader reader = new BufferedReader(is);
        String line;
        try {
            while ((line = reader.readLine()) != null) {
                dirtyWords.add(line);
            }
        } catch (IOException e) {
            e.printStackTrace();
        }
        return dirtyWords;
    }
}

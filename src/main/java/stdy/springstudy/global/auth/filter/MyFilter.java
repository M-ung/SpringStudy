package stdy.springstudy.global.auth.filter;

import jakarta.servlet.*;
import java.io.IOException;
public class MyFilter implements Filter {
    @Override
    public void doFilter(ServletRequest request, ServletResponse response, FilterChain chain) throws IOException, ServletException {

        System.out.println("필터1");
        chain.doFilter(request, response);
    }
}

package hei.tresorock.filters;

import javax.servlet.annotation.WebFilter;
import javax.servlet.*;
import java.io.IOException;
import java.nio.charset.StandardCharsets;

/**
 * Classe permettant le bon affichage des caractères spéciaux du site.
 * @author gaetandeschamps, clementgeorjon
 */
@WebFilter(filterName = "EncodingFilter")

public class EncodingFilter implements Filter {
    public void destroy() {

    }

    public void doFilter(ServletRequest req, ServletResponse resp, FilterChain chain) throws ServletException, IOException {
        req.setCharacterEncoding(StandardCharsets.UTF_8.displayName());
        resp.setCharacterEncoding(StandardCharsets.UTF_8.displayName());
        resp.setContentType("text/html;charset=UTF-8");
        chain.doFilter(req, resp);

    }

    public void init(FilterConfig config) throws ServletException {

    }

}
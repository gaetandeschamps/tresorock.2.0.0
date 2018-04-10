package hei.tresorock.filters;

import javax.servlet.*;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.io.IOException;

/**
 * Classe permettant de se connecter à la partie admin et de récharger la page de connexion si il y a une erreur.
 */
public class LoginFilter implements Filter {
    /**
     * Méthode permettant d'initialiser la connexion.
     * @param filterConfig
     * @throws ServletException
     */
    @Override
    public void init(FilterConfig filterConfig) throws ServletException {

    }

    /**
     * Méthode controlant que les champs ne sont pas vides. Recharge la page de connexion si les informations entrées ne sont pas les bonnes.
     * @param servletRequest
     * @param servletResponse
     * @param filterChain
     * @throws IOException
     * @throws ServletException
     */
    @Override
    public void doFilter(ServletRequest servletRequest, ServletResponse servletResponse, FilterChain filterChain) throws IOException, ServletException {
        HttpServletRequest httpServletRequest = (HttpServletRequest) servletRequest;
        String login = (String) httpServletRequest.getSession().getAttribute("adminConnecte");
        if(login == null || "".equals(login)){
            System.out.println("Cette page nécessite d'être connecté en temps qu'administrateur pour s'afficher.");
            HttpServletResponse httpServletResponse = (HttpServletResponse) servletResponse;
            httpServletResponse.sendRedirect("../connexionAdmin");
            return;
        }
        filterChain.doFilter(servletRequest,servletResponse);
    }

    /**
     * Détruit les paramètres récupérés auparavant.
     */
    @Override
    public void destroy() {

    }
}

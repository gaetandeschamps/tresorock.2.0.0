package hei.tresorock.servlet;

import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.io.IOException;

/**
 * Classe qui permet de se déconnecter via un bouton de déconnexion de la partie ADMIN.
 * Cette partie du site nécessite un login et un mot de passe. Cette classe réinitialise le tout et se déconnecte.
 * @author gaetandeschamps, clementgeorjon

 */
@WebServlet("/session/deconnexion")
public class DeconnexionServlet extends HttpServlet {

    /**
     * Méthode doGET qui réinitalise la connexion à la partie ADMIN
     * @param req - récupère les infos en vue de les réinitialiser.
     * @param resp - redirige vers la page de connexion (après réinitialisation).
     * @throws ServletException
     * @throws IOException
     */
    @Override
    protected void doGet(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException {
        req.getSession().removeAttribute("adminConnecte");
        resp.sendRedirect("connexionAdmin");

    }
}

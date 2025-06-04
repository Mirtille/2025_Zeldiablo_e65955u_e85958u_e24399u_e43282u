package gameLaby.laby;

import moteurJeu.MoteurJeu;

import java.io.IOException;

/**
 * Classe principale pour lancer le jeu du labyrinthe.
 */
public class MainLaby {

    /**
     * Méthode principale qui initialise le moteur et lance le jeu.
     *
     * @param args arguments de la ligne de commande (non utilisés)
     * @throws IOException si un fichier de labyrinthe est introuvable
     */
    public static void main(String[] args) throws IOException {

        int width = 800;
        int height = 600;
        int pFPS = 30;

        LabyJeu jeu = new LabyJeu("labySimple/laby");
        LabyDessin dessin = new LabyDessin();

        MoteurJeu.setTaille(width, height);
        MoteurJeu.setFPS(pFPS);

        // Lancement du moteur de jeu avec le labyrinthe
        MoteurJeu.launch(jeu, dessin);
    }
}

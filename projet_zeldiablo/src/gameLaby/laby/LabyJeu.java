package gameLaby.laby;

import moteurJeu.Clavier;
import moteurJeu.Jeu;

import java.io.IOException;
import java.util.ArrayList;
import java.util.List;

/**
 * Classe principale du jeu Labyrinthe.
 * Gère les niveaux, les déplacements et les actions du joueur.
 */
public class LabyJeu implements Jeu {

    public static final int WIDTH = 800;
    public static final int HEIGHT = 600;

    private Labyrinthe labyrinthe;
    private final List<Labyrinthe> labyrinthes;
    private double tempsDepuisDernierDeplacement;
    private Perso perso;

    /**
     * Crée un nouveau jeu de labyrinthe à partir d'un nom de base pour les fichiers.
     *
     * @param nom préfixe des fichiers de labyrinthe
     * @throws IOException si un fichier n'est pas trouvé
     */
    public LabyJeu(String nom) throws IOException {
        this.labyrinthes = new ArrayList<>();
        int[] nbM = {0, 2, 10, 30};
        for (int i = 0; i < 4; i++) {
            labyrinthes.add(new Labyrinthe(nom + i + ".txt", this));
            this.labyrinthes.get(i).genererMonstres(nbM[i]);
        }
        this.labyrinthe = labyrinthes.get(0);
        this.perso = labyrinthe.pj;
    }

    /**
     * Met à jour l'état du jeu à chaque frame.
     *
     * @param secondes temps écoulé
     * @param clavier entrées clavier du joueur
     */
    @Override
    public void update(double secondes, Clavier clavier) {
        if (clavier.droite) {
            this.labyrinthe.deplacerPerso(Labyrinthe.DROITE);
        }
        if (clavier.gauche) {
            this.labyrinthe.deplacerPerso(Labyrinthe.GAUCHE);
        }
        if (clavier.bas) {
            this.labyrinthe.deplacerPerso(Labyrinthe.BAS);
        }
        if (clavier.haut) {
            this.labyrinthe.deplacerPerso(Labyrinthe.HAUT);
        }

        if (clavier.attaque) {
            this.labyrinthe.pj.attaque(this.labyrinthe);
            System.out.println("Attaque");
        }

        this.labyrinthe.deplacerMonstres();

        // Supprime les monstres morts
        List<Monstre> morts = new ArrayList<>();
        for (Monstre m : this.labyrinthe.monstres) {
            if (m.etreMort()) {
                morts.add(m);
            }
        }
        this.labyrinthe.monstres.removeAll(morts);
    }

    /**
     * Change d'étage dans le jeu (labyrinthe suivant ou précédent).
     *
     * @param e décalage (+1 ou -1)
     */
    public void changerEtage(int e) {
        this.labyrinthe = this.labyrinthes.get(this.labyrinthes.indexOf(this.labyrinthe) + e);
        this.labyrinthe.chargerPerso(perso);
    }

    /**
     * Initialise le jeu (non utilisé ici).
     */
    @Override
    public void init() {
        // rien à faire ici
    }

    /**
     * Indique si le jeu est terminé.
     *
     * @return true si le labyrinthe est terminé
     */
    @Override
    public boolean etreFini() {
        return this.labyrinthe.etreFini();
    }

    /**
     * Renvoie le labyrinthe en cours.
     *
     * @return le labyrinthe actuel
     */
    public Labyrinthe getLabyrinthe() {
        return this.labyrinthe;
    }
}

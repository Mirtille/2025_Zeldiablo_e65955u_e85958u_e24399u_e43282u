package gameLaby.laby;

/**
 * Case spéciale permettant de changer d'étage si tous les monstres sont vaincus.
 */
public class Teleporteur implements CasesSpeciale {

    int x, y;
    private LabyJeu jeu;
    boolean clear;

    /**
     * Constructeur du téléporteur.
     *
     * @param x position x
     * @param y position y
     * @param jeu référence au jeu (pour changer d'étage)
     */
    public Teleporteur(int x, int y, LabyJeu jeu) {
        this.x = x;
        this.y = y;
        this.jeu = jeu;
        this.clear = false;
    }

    /**
     * Déclenche le téléporteur : change d'étage dans le jeu.
     *
     * @param entite l'entité qui active le téléporteur (non utilisée ici)
     */
    public void declencher(Entite entite) {
        jeu.changerEtage(1);
        System.out.println("Teleporteur declencher");
    }

    /**
     * Vérifie si le téléporteur peut être activé à cette position
     * (et si tous les monstres ont été éliminés).
     *
     * @param i position x
     * @param j position y
     * @return true si activable
     */
    public boolean etreActiver(int i, int j) {
        return x == i && y == j && this.jeu.getLabyrinthe().etreClear();
    }

    /**
     * @return position x du téléporteur
     */
    public int getX() {
        return x;
    }

    /**
     * @return position y du téléporteur
     */
    public int getY() {
        return y;
    }
}

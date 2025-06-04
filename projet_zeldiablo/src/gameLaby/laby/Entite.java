package gameLaby.laby;

import static gameLaby.laby.Labyrinthe.BAS;

/**
 * Classe abstraite représentant une entité dans le labyrinthe (joueur, monstre...).
 */
public abstract class Entite {

    int pv;
    int force;
    int x;
    int y;
    String ancienM;

    /**
     * Constructeur d'une entité.
     *
     * @param dx position x de départ
     * @param dy position y de départ
     * @param pv points de vie
     * @param atq force d'attaque
     */
    public Entite(int dx, int dy, int pv, int atq) {
        this.x = dx;
        this.y = dy;
        this.pv = pv;
        this.force = atq;
        this.ancienM = BAS;
    }

    /**
     * Vérifie si l'entité est présente aux coordonnées données.
     *
     * @param suivante tableau [x, y] à vérifier
     * @return true si l'entité est présente, false sinon
     */
    public boolean etrePresent(int[] suivante) {
        return suivante[0] == x && suivante[1] == y;
    }

    /**
     * Renvoie les points de vie de l'entité.
     *
     * @return les PV
     */
    public int getPv() {
        return pv;
    }

    /**
     * Renvoie la force d'attaque de l'entité.
     *
     * @return la force
     */
    public int getForce() {
        return force;
    }

    /**
     * Renvoie la position x de l'entité.
     *
     * @return x
     */
    public int getX() {
        return x;
    }

    /**
     * Renvoie la position y de l'entité.
     *
     * @return y
     */
    public int getY() {
        return y;
    }

    /**
     * Affiche une description simple de l'entité.
     *
     * @return texte avec les PV
     */
    public String toString() {
        return " a " + pv + ".";
    }

    /**
     * Vérifie si l'entité est morte (PV <= 0).
     *
     * @return true si morte, false sinon
     */
    public boolean etreMort() {
        return this.pv <= 0;
    }
}

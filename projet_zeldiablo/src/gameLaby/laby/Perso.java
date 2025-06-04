package gameLaby.laby;

import static gameLaby.laby.Labyrinthe.*;

/**
 * Classe représentant le personnage du joueur dans le labyrinthe.
 * Hérite d'Entite.
 */
public class Perso extends Entite {

    boolean avoirAttaque;

    /**
     * Constructeur du personnage.
     *
     * @param dx position x
     * @param dy position y
     * @param pv points de vie
     * @param atq force d'attaque
     */
    public Perso(int dx, int dy, int pv, int atq) {
        super(dx, dy, pv, atq);
        this.avoirAttaque = false;
    }

    /**
     * Le personnage attaque la case devant lui selon sa dernière direction.
     * Si un zombie est présent, il lui inflige des dégâts.
     *
     * @param labyrinthe labyrinthe actuel
     */
    public void attaque(Labyrinthe labyrinthe) {
        avoirAttaque = true;
        int[] pose = null;

        switch (ancienM) {
            case HAUT -> pose = new int[]{x, y - 1};
            case BAS -> pose = new int[]{x, y + 1};
            case GAUCHE -> pose = new int[]{x - 1, y};
            case DROITE -> pose = new int[]{x + 1, y};
        }

        if (pose != null) {
            Zombie z = labyrinthe.getZombie(pose[0], pose[1]);
            if (z != null) {
                z.subirDegat(force);
            }
        }
    }

    /**
     * Affiche le nom du personnage suivi de ses PV.
     *
     * @return texte avec les points de vie
     */
    public String toString() {
        return "Steve" + super.toString();
    }
}

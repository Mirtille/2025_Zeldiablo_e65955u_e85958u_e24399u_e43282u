package gameLaby.laby;

/**
 * Classe abstraite représentant un monstre dans le labyrinthe.
 * Hérite de Entite.
 */
public abstract class Monstre extends Entite {

    /**
     * Constructeur du monstre.
     *
     * @param dx position x
     * @param dy position y
     * @param pv points de vie
     * @param atq force d'attaque
     */
    public Monstre(int dx, int dy, int pv, int atq) {
        super(dx, dy, pv, atq);
    }

    /**
     * Déplace le monstre à une nouvelle position.
     *
     * @param suivante tableau [x, y] de la nouvelle position
     */
    public void deplacerMonstre(int[] suivante) {
        this.x = suivante[0];
        this.y = suivante[1];
    }

    /**
     * Inflige des dégâts au monstre.
     *
     * @param i quantité de dégâts
     */
    public void subirDegat(int i) {
        this.pv -= i;
        System.out.println("Il reste " + pv + " points de vie au monstre.");
    }
}

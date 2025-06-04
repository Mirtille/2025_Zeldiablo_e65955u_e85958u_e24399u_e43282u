package gameLaby.laby;

/**
 * Classe représentant un zombie dans le labyrinthe.
 * Hérite de Monstre.
 */
public class Zombie extends Monstre {

    /**
     * Constructeur du zombie.
     *
     * @param x position x
     * @param y position y
     */
    public Zombie(int x, int y) {
        super(x, y, 2, 0);
    }

    /**
     * Affiche une description du zombie avec ses PV.
     *
     * @return texte du type "Zombie a X."
     */
    public String toString() {
        return "Zombie" + super.toString();
    }
}

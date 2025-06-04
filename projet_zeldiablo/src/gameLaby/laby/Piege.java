package gameLaby.laby;

/**
 * Classe représentant une case piège qui inflige des dégâts.
 */
public class Piege implements CasesSpeciale {

    int x, y;
    boolean t;

    /**
     * Constructeur du piège.
     *
     * @param x position x
     * @param y position y
     */
    public Piege(int x, int y) {
        this.x = x;
        this.y = y;
        this.t = false;
    }

    /**
     * Déclenche le piège : inflige des dégâts à l'entité et active le piège.
     *
     * @param entite l'entité touchée
     */
    public void declencher(Entite entite) {
        System.out.println("Piege declencher");
        entite.pv -= 5;
        this.t = true;
    }

    /**
     * Vérifie si le piège doit se déclencher aux coordonnées données.
     *
     * @param i position x à tester
     * @param j position y à tester
     * @return true si le piège est à ces coordonnées
     */
    public boolean etreActiver(int i, int j) {
        return x == i && y == j;
    }

    /**
     * @return position x du piège
     */
    public int getX() {
        return x;
    }

    /**
     * @return position y du piège
     */
    public int getY() {
        return y;
    }
}

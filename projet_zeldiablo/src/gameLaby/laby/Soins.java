package gameLaby.laby;

/**
 * Classe représentant une case de soin.
 * Restaure des points de vie à l'entité qui l'active.
 */
public class Soins implements CasesSpeciale {

    int x, y;

    /**
     * Constructeur de la case de soin.
     *
     * @param x position x
     * @param y position y
     */
    public Soins(int x, int y) {
        this.x = x;
        this.y = y;
    }

    /**
     * Déclenche le soin : ajoute des points de vie à l'entité.
     *
     * @param entite l'entité soignée
     */
    public void declencher(Entite entite) {
        System.out.println("Soins declencher");
        entite.pv += 5;
    }

    /**
     * Vérifie si la case doit s’activer en (i, j).
     *
     * @param i position x
     * @param j position y
     * @return true si la position correspond à la case
     */
    public boolean etreActiver(int i, int j) {
        return x == i && y == j;
    }

    /**
     * @return position x de la case
     */
    public int getX() {
        return x;
    }

    /**
     * @return position y de la case
     */
    public int getY() {
        return y;
    }
}

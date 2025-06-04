package gameLaby.laby;

/**
 * Interface pour les cases spéciales du labyrinthe.
 * Permet de déclencher un effet et de savoir si la case est active.
 */
public interface CasesSpeciale {

    /**
     * Déclenche l'effet de la case sur une entité.
     *
     * @param entite l'entité affectée (joueur, monstre, etc.)
     */
    public void declencher(Entite entite);

    /**
     * Vérifie si la case peut être activée en (i, j).
     *
     * @param i la position en x
     * @param j la position en y
     * @return true si la case est activable, false sinon
     */
    public boolean etreActiver(int i, int j);

    /**
     * Donne la coordonnée x de la case.
     *
     * @return la position x
     */
    public int getX();

    /**
     * Donne la coordonnée y de la case.
     *
     * @return la position y
     */
    public int getY();
}

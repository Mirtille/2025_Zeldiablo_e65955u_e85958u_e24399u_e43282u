package gameLaby.laby;


/**
 * gere un personnage situe en x,y
 */
public class Perso {

    /**
     * position du personnage
     */
    int x, y;
    int vie;
    int attaque;

    /**
     * constructeur
     *
     * @param dx position selon x
     * @param dy position selon y
     */
    public Perso(int dx, int dy, int pv , int atq) {
        this.x = dx;
        this.y = dy;
        this.vie = pv;
        this.attaque = atq;
    }

    /**
     * permet de savoir si le personnage est en x,y
     *
     * @param dx position testee
     * @param dy position testee
     * @return true si le personnage est bien en (dx,dy)
     */
    public boolean etrePresent(int dx, int dy) {

        return (this.x == dx && this.y == dy);
    }

    // ############################################
    // GETTER
    // ############################################

    /**
     * @return position x du personnage
     */
    public int getX() {
        // getter
        return this.x;
    }

    /**
     * @return position y du personnage
     */
    public int getY() {
        //getter
        return this.y;
    }
    /**
     * @return vie du joueur
     */
    public int getVie() {
        return this.vie;
    }
    /**
     * @return attaque du joueur
     */
    public int getAttaque() {
        return this.attaque;
    }
}

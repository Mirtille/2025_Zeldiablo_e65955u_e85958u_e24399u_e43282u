package gameLaby.laby;


/**
 * gere un personnage situe en x,y
 */
public class Perso extends Entite {

    /**
     * constructeur
     *
     * @param dx position selon x
     * @param dy position selon y
     */
    public Perso(int dx, int dy, int pv, int atq) {
        super(dx, dy, pv, atq);
    }

    public String toString() {
        return "Steve" + super.toString() ;
    }
}

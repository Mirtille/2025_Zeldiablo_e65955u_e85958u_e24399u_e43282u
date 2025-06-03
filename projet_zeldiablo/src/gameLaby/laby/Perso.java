package gameLaby.laby;




import static gameLaby.laby.Labyrinthe.*;

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

    public void attaque(Labyrinthe labyrinthe) {
        if (ancienM != null) {
            int[] pose = null ;
            switch (ancienM) {
                case HAUT:
                    pose = new int[]{x, y - 1};
                    break;
                case BAS:
                    pose = new int[]{x, y + 1};
                    break;
                case GAUCHE:
                    pose = new int[]{x - 1, y};
                    break;
                case DROITE:
                    pose = new int[]{x + 1, y};
                    break;
            }
            if (pose != null) {
                Zombie z = labyrinthe.getZombie(pose[0], pose[1]) ;
                if (z != null) {
                    z.subirDegat(force);
                }
            }
        }

    }



    public boolean etreMort(){
        if (this.pv <= 0){
            return true;
        }
        return false;
    }

    public String toString() {
        return "Steve" + super.toString() ;
    }
}

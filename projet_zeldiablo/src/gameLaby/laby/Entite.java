package gameLaby.laby;

public abstract class Entite {

    int pv ;
    int force ;
    int x ;
    int y ;
    String ancienM;

    public Entite(int dx, int dy, int pv , int atq) {
        this.x = dx;
        this.y = dy;
        this.pv = pv;
        this.force = atq;
        this.ancienM = null;
    }

    public boolean etrePresent (int[] suivante) {
        return suivante[0] == x && suivante[1] == y;
    }

    public int getPv() {
        return pv;
    }

    public int getForce() {
        return force;
    }

    public int getX() {
        return x;
    }

    public int getY() {
        return y;
    }

    public String toString() {
        return " a " + pv + "." ;
    }

    public void subirDegat(int i) {
        this.pv -= i;
        this.toString() ;
    }
}



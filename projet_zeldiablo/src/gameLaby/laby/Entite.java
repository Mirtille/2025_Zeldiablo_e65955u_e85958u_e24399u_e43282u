package gameLaby.laby;

import static gameLaby.laby.Labyrinthe.HAUT;

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

    public void attaque() {
        switch (ancienM) {
            case HAUT:
                if (etreZombie)

        }
        e.pv -= this.force;
        try {
            // Attendre 200 millisecondes
            Thread.sleep(200);
        } catch (InterruptedException e) {
            e.printStackTrace();
        }
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
}



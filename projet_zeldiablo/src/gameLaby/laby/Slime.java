package gameLaby.laby;

public class Slime implements Monstre {
    int x, y, vie, attaque ;

    public Slime (int x, int y, int pv, int atq) {
        this.x = x;
        this.y = y;
        this.vie = pv;
        this.attaque = atq;

    }

    public void deplacerMonstre (int[] suivante) {
        this.x = suivante[0];
        this.y = suivante[1];
    }

    public String toString() {
        return "points de vie : " + this.vie + " attaque : " + this.attaque;
    }

    public boolean etrePresent (int[] suivante) {
        return suivante[0] == x && suivante[1] == y;
    }

    public int getX() {
        return x;
    }

    public int getY() {
        return y;
    }
}

package gameLaby.laby;

public class Slime implements Monstre {
    int x, y ;

    public Slime (int x, int y) {
        this.x = x;
        this.y = y;
    }

    public void deplacerMonstre (int[] suivante) {
        this.x = suivante[0];
        this.y = suivante[1];
    }

    public int getX() {
        return x;
    }

    public int getY() {
        return y;
    }
}

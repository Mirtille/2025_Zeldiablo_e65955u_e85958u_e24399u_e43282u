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

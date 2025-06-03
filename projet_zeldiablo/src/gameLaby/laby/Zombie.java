package gameLaby.laby;

public class Zombie extends Entite implements Monstre {

    public Zombie(int x, int y) {
        super(x, y, 2, 0) ;
    }

    public void deplacerMonstre (int[] suivante) {
        this.x = suivante[0];
        this.y = suivante[1];
    }
}

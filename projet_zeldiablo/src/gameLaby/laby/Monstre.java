package gameLaby.laby;

public abstract class Monstre extends Entite {

    public Monstre(int dx, int dy, int pv, int atq) {
        super(dx, dy, pv, atq);
    }

    public void deplacerMonstre (int[] suivante) {
        this.x = suivante[0];
        this.y = suivante[1];
    }

    public void subirDegat(int i) {
        this.pv -= i;
        System.out.println("Il reste " + pv + " points de vie au monstre.") ;
    }
}

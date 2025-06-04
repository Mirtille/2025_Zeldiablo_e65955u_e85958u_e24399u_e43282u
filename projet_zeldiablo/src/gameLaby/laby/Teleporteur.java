package gameLaby.laby;

public class Teleporteur implements CasesSpeciale{

    int x,y;
    private LabyJeu jeu ;
    boolean clear ;

    public Teleporteur(int x, int y, LabyJeu jeu) {
        this.x = x;
        this.y = y;
        this.jeu = jeu;
        this.clear = false;
    }

    public void declencher(Entite entite) {
        jeu.changerEtage(1) ;
        System.out.println("Teleporteur declencher");
    }
    public boolean etreActiver(int i, int j) {
        return x == i && y == j && this.jeu.getLabyrinthe().etreClear();
    }

    public int getX() {
        return x;
    }

    public int getY() {
        return y;
    }
}

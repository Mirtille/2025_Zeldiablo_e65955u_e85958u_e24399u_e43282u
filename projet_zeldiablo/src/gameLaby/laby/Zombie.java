package gameLaby.laby;

public class Zombie extends Monstre {

    public Zombie(int x, int y) {
        super(x, y, 2, 0) ;
    }

    public String toString() {
        return "Zombie" + super.toString() ;
    }
}

package gameLaby.laby;

public class Piege implements CasesSpeciale {

    int x,y;
    boolean t ;

    /**
     * @param x
     * @param y
     */
    public Piege(int x, int y) {
        this.x = x;
        this.y = y;
        this.t = false;
    }

    public void declencher(Entite entite) {
        System.out.println("Piege declencher");
        entite.pv -= 5 ;
        this.t = true;
    }

    /**
     * @param i
     * @param j
     * @return
     */
    public boolean etreActiver(int i, int j) {
        return x == i && y == j;
    }

    public int getX() {
        return x;
    }

    public int getY() {
        return y;
    }


}

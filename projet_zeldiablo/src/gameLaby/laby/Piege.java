package gameLaby.laby;

public class Piege implements CasesSpeciale {

    int x,y;
    boolean t ;

    public Piege(int x, int y) {
        this.x = x;
        this.y = y;
        this.t = false;
    }

    public void declencher() {
        System.out.println("Piege declencher");
        this.t = true;
    }
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

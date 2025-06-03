package gameLaby.laby;

public class Piege implements CasesSpeciale {

    int x,y;
    boolean t = false;

    public Piege(int x, int y) {
        this.x = x;
        this.y = y;
    }

    public void declencher() {
        System.out.println("Piege declencher");
    }
    public boolean etreActiver(int i, int j) {
        t = true;
        return x == i && y == j;
    }

    public int getX() {
        return x;
    }

    public int getY() {
        return y;
    }


}

package gameLaby.laby;

public class Piege implements CasesSpeciale {

    int x,y;

    public Piege(int x, int y) {
        this.x = x;
        this.y = y;
    }

    public void declancher() {
        System.out.println("piege declencher");
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

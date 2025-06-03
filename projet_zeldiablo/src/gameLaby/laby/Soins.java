package gameLaby.laby;

public class Soins implements CasesSpeciale {

    int x,y;

    public Soins(int x, int y) {
        this.x = x;
        this.y = y;
    }

    public void declencher() {
        System.out.println("Teleporteur declencher");
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

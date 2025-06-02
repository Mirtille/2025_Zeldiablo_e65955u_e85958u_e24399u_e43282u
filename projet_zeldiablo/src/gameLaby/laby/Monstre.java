package gameLaby.laby;

public interface Monstre {
    public boolean etrePresent(int dx, int dy);
    public int getX();
    public int getY();
    public void deplaceraleatoire(int dx, int dy);


}

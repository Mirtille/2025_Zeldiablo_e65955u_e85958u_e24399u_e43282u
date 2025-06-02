package gameLaby.laby;

import moteurJeu.Clavier;
import moteurJeu.Jeu;

import java.io.IOException;

public class LabyJeu implements Jeu {

    public static final int WIDTH = 800;
    public static final int HEIGHT = 600;

    private final Labyrinthe labyrinthe;

    public LabyJeu(String nom) throws IOException {
        this.labyrinthe = new Labyrinthe(nom) ;
    }

    @Override
    public void update(double secondes, Clavier clavier) {
        if (clavier.droite) {
            this.labyrinthe.deplacerPerso(Labyrinthe.DROITE);
        }
        if (clavier.gauche) {
            this.labyrinthe.deplacerPerso(Labyrinthe.GAUCHE);
        }
        if (clavier.bas) {
            this.labyrinthe.deplacerPerso(Labyrinthe.BAS);
        }
        if (clavier.haut) {
            this.labyrinthe.deplacerPerso(Labyrinthe.HAUT);
        }

        this.labyrinthe.deplacerMonstres();

    }

    @Override
    public void init() {

    }

    @Override
    public boolean etreFini() {
        return false;
    }

    public Labyrinthe getLabyrinthe() {
        return this.labyrinthe;
    }
}

package gameLaby.laby;

import moteurJeu.Clavier;
import moteurJeu.Jeu;

import java.io.IOException;
import java.util.ArrayList;
import java.util.List;

public class LabyJeu implements Jeu {

    public static final int WIDTH = 800;
    public static final int HEIGHT = 600;

    private final Labyrinthe labyrinthe;
    private double tempsDepuisDernierDeplacement ;


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

        if (clavier.attaque) {
            this.labyrinthe.pj.attaque(this.labyrinthe);
            System.out.println("Attaque");
        }

        this.labyrinthe.deplacerMonstres();
        List<Monstre> morts = new ArrayList<>();
        for (Monstre m : this.labyrinthe.monstres) {
            if (m.etreMort()) {
                morts.add(m);
            }
        }
        this.labyrinthe.monstres.removeAll(morts);

    }

    @Override
    public void init() {

    }

    @Override
    public boolean etreFini() {
        return this.labyrinthe.etreFini() ;
    }

    public Labyrinthe getLabyrinthe() {
        return this.labyrinthe;
    }

}

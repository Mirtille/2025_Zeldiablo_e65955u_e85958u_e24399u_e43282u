package gameLaby.laby;

import moteurJeu.Clavier;
import moteurJeu.Jeu;

import java.io.IOException;
import java.util.ArrayList;
import java.util.List;

public class LabyJeu implements Jeu {

    public static final int WIDTH = 800;
    public static final int HEIGHT = 600;

    private Labyrinthe labyrinthe;
    private final List<Labyrinthe> labyrinthes;
    private double tempsDepuisDernierDeplacement ;
    private Perso perso;



    public LabyJeu(String nom) throws IOException {
        this.labyrinthes = new ArrayList<>();
        int[] nbM = {0, 2, 10, 30} ;
        for (int i = 0; i < 4; i++) {
            labyrinthes.add(new Labyrinthe(nom + i + ".txt", this));
            this.labyrinthes.get(i).genererMonstres(nbM[i]);
        }
        this.labyrinthe = labyrinthes.get(0);
        this.perso = labyrinthe.pj ;
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

    public void changerEtage(int e) {
        this.labyrinthe = this.labyrinthes.get(this.labyrinthes.indexOf(this.labyrinthe)+ e) ;
        this.labyrinthe.chargerPerso((perso));
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

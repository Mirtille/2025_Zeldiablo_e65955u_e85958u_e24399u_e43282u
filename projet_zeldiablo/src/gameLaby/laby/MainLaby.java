package gameLaby.laby;

import moteurJeu.MoteurJeu;

import java.io.IOException;

public class MainLaby {
    public static void main(String[] args) throws IOException {


    int width = 800;
    int height = 600;
    int pFPS = 60;

    LabyJeu jeu = new LabyJeu("labySimple/laby2.txt");
    LabyDessin dessin = new LabyDessin();
    jeu.getLabyrinthe().genererMonstres(2);

        MoteurJeu.setTaille(width,height);
        MoteurJeu.setFPS(pFPS);

    // lancement du jeu
        MoteurJeu.launch(jeu,dessin);



    }
}

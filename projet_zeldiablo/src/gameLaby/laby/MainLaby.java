package gameLaby.laby;

import moteurJeu.MoteurJeu;

import java.io.IOException;

public class MainLaby {
    public static void main(String[] args) throws IOException {


    int width = 800;
    int height = 600;
    int pFPS = 5;

    LabyJeu jeu = new LabyJeu("labySimple/laby1.txt");
    LabyDessin dessin = new LabyDessin();

        MoteurJeu.setTaille(width,height);
        MoteurJeu.setFPS(pFPS);

    // lancement du jeu
        MoteurJeu.launch(jeu,dessin);
    }
}

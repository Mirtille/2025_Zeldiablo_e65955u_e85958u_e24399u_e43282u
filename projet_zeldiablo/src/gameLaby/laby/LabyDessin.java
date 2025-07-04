package gameLaby.laby;

import gameArkanoid.ArkanoidJeu;
import javafx.scene.canvas.Canvas;
import javafx.scene.canvas.GraphicsContext;
import javafx.scene.paint.Color;
import moteurJeu.DessinJeu;
import moteurJeu.Jeu;
import javafx.scene.image.Image;

import static gameLaby.laby.Labyrinthe.*;

/**
 * Classe qui gère l'affichage graphique du jeu Labyrinthe.
 */
public class LabyDessin implements DessinJeu {

    /**
     * Dessine le jeu sur le canvas à partir de l'état du labyrinthe.
     *
     * @param jeu le jeu à dessiner (doit être un LabyJeu)
     * @param canvas la surface de dessin
     */
    @Override
    public void dessinerJeu(Jeu jeu, Canvas canvas) {
        LabyJeu laby = (LabyJeu) jeu;

        final GraphicsContext gc = canvas.getGraphicsContext2D();
        gc.setFill(Color.LIGHTGRAY);
        gc.fillRect(0, 0, canvas.getWidth(), canvas.getHeight());

        // Chargement des images
        Image imgPiege = new Image("file:img/poison (2).png");
        Image imgPersoH = new Image("file:img/haut.png");
        Image imgPersoB = new Image("file:img/bas.png");
        Image imgPersoD = new Image("file:img/droit.png");
        Image imgPersoG = new Image("file:img/gauche.png");
        Image imgZombieH = new Image("file:img/zombieHaut.png");
        Image imgZombieB = new Image("file:img/zombieBas.png");
        Image imgZombieD = new Image("file:img/zombieDroit.png");
        Image imgZombieG = new Image("file:img/zombieGauche.png");
        Image imgTeleporteur = new Image("file:img/teleporteur.png");
        Image imgPierre = new Image("file:img/Pierre.png");
        Image imgPierreDessus = new Image("file:img/PierreMoitier.png");
        Image imgHerbe = new Image("file:img/herbe.jpg");
        Image imgHeal = new Image("file:img/heal.png");
        Image imgCoup = new Image("file:img/coup.png");

        // Dimensions des cases
        double caseWidth = (double) laby.WIDTH / laby.getLabyrinthe().getLength();
        double caseHeight = (double) laby.HEIGHT / laby.getLabyrinthe().getLengthY();

        // Affichage du sol et des cases spéciales
        for (int i = 0; i < laby.getLabyrinthe().getLength(); i++) {
            for (int j = 0; j < laby.getLabyrinthe().getLengthY(); j++) {
                double x = i * caseWidth;
                double y = j * caseHeight;

                gc.drawImage(imgHerbe, x, y, caseWidth, caseHeight);

                if (laby.getLabyrinthe().contientCase(Soins.class, i, j)) {
                    gc.drawImage(imgHeal, x, y, caseWidth, caseHeight);
                }

                if (laby.getLabyrinthe().contientCase(Piege.class, i, j)) {
                    gc.drawImage(imgPiege, x, y, caseWidth, caseHeight);
                }

                if (laby.getLabyrinthe().contientCase(Teleporteur.class, i, j)
                        && laby.getLabyrinthe().etreClear()) {
                    gc.drawImage(imgTeleporteur, x, y, caseWidth, caseHeight);
                }
            }
        }

        // Affichage du joueur et des zombies
        for (int i = 0; i < laby.getLabyrinthe().getLength(); i++) {
            for (int j = 0; j < laby.getLabyrinthe().getLengthY(); j++) {
                double x = i * caseWidth;
                double y = j * caseHeight;
                int[] p = {i, j};

                if (laby.getLabyrinthe().pj.etrePresent(p)) {
                    switch (laby.getLabyrinthe().pj.ancienM) {
                        case HAUT:
                            gc.drawImage(imgPersoH, x, y, caseWidth, caseHeight);
                            if (laby.getLabyrinthe().pj.avoirAttaque) {
                                gc.drawImage(imgCoup, x, y - caseHeight, caseWidth, caseHeight);
                            }
                            break;
                        case BAS:
                            gc.drawImage(imgPersoB, x, y, caseWidth, caseHeight);
                            if (laby.getLabyrinthe().pj.avoirAttaque) {
                                gc.drawImage(imgCoup, x, y + caseHeight, caseWidth, caseHeight);
                            }
                            break;
                        case GAUCHE:
                            gc.drawImage(imgPersoG, x, y, caseWidth, caseHeight);
                            if (laby.getLabyrinthe().pj.avoirAttaque) {
                                gc.drawImage(imgCoup, x - caseWidth, y, caseWidth, caseHeight);
                            }
                            break;
                        case DROITE:
                            gc.drawImage(imgPersoD, x, y, caseWidth, caseHeight);
                            if (laby.getLabyrinthe().pj.avoirAttaque) {
                                gc.drawImage(imgCoup, x + caseWidth, y, caseWidth, caseHeight);
                            }
                            break;
                        default:
                            gc.drawImage(imgPersoB, x, y, caseWidth, caseHeight);
                    }
                    laby.getLabyrinthe().pj.avoirAttaque = false;
                }

                if (laby.getLabyrinthe().getZombie(i, j) != null) {
                    switch (laby.getLabyrinthe().getZombie(i, j).ancienM) {
                        case HAUT:
                            gc.drawImage(imgZombieH, x, y, caseWidth, caseHeight);
                            break;
                        case BAS:
                            gc.drawImage(imgZombieB, x, y, caseWidth, caseHeight);
                            break;
                        case GAUCHE:
                            gc.drawImage(imgZombieG, x, y, caseWidth, caseHeight);
                            break;
                        case DROITE:
                            gc.drawImage(imgZombieD, x, y, caseWidth, caseHeight);
                            break;
                        default:
                            gc.drawImage(imgZombieB, x, y, caseWidth, caseHeight);
                    }
                }
            }
        }

        // Affichage des murs
        for (int i = 0; i < laby.getLabyrinthe().getLength(); i++) {
            for (int j = 0; j < laby.getLabyrinthe().getLengthY(); j++) {
                double x = i * caseWidth;
                double y = j * caseHeight;
                if (laby.getLabyrinthe().getMur(i, j)) {
                    gc.drawImage(imgPierre, x, y, caseWidth, caseHeight);
                    gc.drawImage(imgPierreDessus, x, y - caseHeight / 3, caseWidth, caseHeight / 2);
                }
            }
        }
    }
}

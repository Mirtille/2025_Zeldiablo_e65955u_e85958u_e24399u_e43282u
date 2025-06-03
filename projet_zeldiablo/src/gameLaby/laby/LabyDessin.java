package gameLaby.laby;

import gameArkanoid.ArkanoidJeu;
import javafx.scene.canvas.Canvas;
import javafx.scene.canvas.GraphicsContext;
import javafx.scene.paint.Color;
import moteurJeu.DessinJeu;
import moteurJeu.Jeu;
import javafx.scene.image.Image;

public class LabyDessin implements DessinJeu {


    @Override
    public void dessinerJeu(Jeu jeu, Canvas canvas) {
        LabyJeu laby = (LabyJeu) jeu;

        final GraphicsContext gc = canvas.getGraphicsContext2D();
        gc.setFill(Color.LIGHTGRAY);
        gc.fillRect(0, 0, canvas.getWidth(), canvas.getHeight());

        Image imgPiege = new Image("file:img/piege.jpg");
        Image imgPerso = new Image("file:img/steve.jpg");
        Image imgMonstre = new Image("file:img/zombie.jpg");
        Image imgTeleporteur = new Image("file:img/Teleporteur.jpg");
        Image imgPierre = new Image("file:img/Pierre.png");
        //murs
        double caseWidth = (double) laby.WIDTH / laby.getLabyrinthe().getLength();
        double caseHeight = (double) laby.HEIGHT / laby.getLabyrinthe().getLengthY();

        for (int i = 0; i < laby.getLabyrinthe().getLength(); i++) {
            for (int j = 0; j < laby.getLabyrinthe().getLengthY(); j++) {
                double x = i * caseWidth;
                double y = j * caseHeight;

                if (laby.getLabyrinthe().getMur(i, j)) {
                    gc.drawImage(imgPierre, x, y, caseWidth, caseHeight);
                }

                if (laby.getLabyrinthe().etrePiege(i, j)) {
                    gc.drawImage(imgPiege, x, y, caseWidth, caseHeight);
                }

                if (laby.getLabyrinthe().etreSoins(i, j)) {
                    gc.setFill(Color.GREEN);
                    gc.fillRect(x, y, caseWidth, caseHeight);
                }

                if (laby.getLabyrinthe().pj.etrePresent(i, j)) {
                    gc.drawImage(imgPerso, x, y, caseWidth, caseHeight);
                }

                if (laby.getLabyrinthe().etreMonstre(i, j)) {
                    gc.drawImage(imgMonstre, x, y, caseWidth, caseHeight);
                }

                if (laby.getLabyrinthe().etreTeleporteur(i, j)) {
                    gc.drawImage(imgTeleporteur, x, y, caseWidth, caseHeight);
                }
            }
        }
    }
}

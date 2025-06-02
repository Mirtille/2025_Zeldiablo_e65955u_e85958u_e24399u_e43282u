package gameLaby.laby;

import gameArkanoid.ArkanoidJeu;
import javafx.scene.canvas.Canvas;
import javafx.scene.canvas.GraphicsContext;
import javafx.scene.paint.Color;
import moteurJeu.DessinJeu;
import moteurJeu.Jeu;

public class LabyDessin implements DessinJeu {
    @Override
    public void dessinerJeu(Jeu jeu, Canvas canvas) {
        LabyJeu laby = (LabyJeu) jeu;

        final GraphicsContext gc = canvas.getGraphicsContext2D();

        //murs
        for (int i = 0 ; i < laby.getLabyrinthe().getLength() ; i++) {
            for (int j = 0 ; j < laby.getLabyrinthe().getLengthY() ; j++) {
                int x = i * 20 ;
                int y = j * 20 ;
                if (laby.getLabyrinthe().getMur(i,j)) {
                    gc.setFill(Color.BLACK);
                    gc.fillRect(x, y, 20, 20);
                }
                if (laby.getLabyrinthe().pj.etrePresent(i, j)) {
                    gc.setFill(Color.RED);
                    gc.fillOval(x, y, 20, 20);
                }
            }
        }

    }
}

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
        gc.setFill(Color.LIGHTGRAY);
        gc.fillRect(0, 0, canvas.getWidth(), canvas.getHeight());

        //murs
        for (int i = 0 ; i < laby.getLabyrinthe().getLength() ; i++) {
            for (int j = 0 ; j < laby.getLabyrinthe().getLengthY() ; j++) {
                int x = i * (int) laby.WIDTH / laby.getLabyrinthe().getLength() ;
                int y = j * laby.HEIGHT / laby.getLabyrinthe().getLengthY() ;
                int h = laby.WIDTH / laby.getLabyrinthe().getLength() + 1 ;
                int l = laby.HEIGHT / laby.getLabyrinthe().getLengthY() + 1 ;
                if (laby.getLabyrinthe().getMur(i,j)) {
                    gc.setFill(Color.BLACK);
                    gc.fillRect(x, y, h, l);
                }
                if (laby.getLabyrinthe().pj.etrePresent(i, j)) {
                    gc.setFill(Color.RED);
                    gc.fillOval(x, y, h, l);
                }
            }
        }

    }
}

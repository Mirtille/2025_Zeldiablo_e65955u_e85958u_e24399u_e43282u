package gameLaby.laby;

import java.io.BufferedReader;
import java.io.FileReader;
import java.io.IOException;
import java.util.ArrayList;
import java.util.List;

/**
 * Classe Labyrinthe. Représente un labyrinthe avec :
 * - des murs
 * - un personnage
 * - des monstres
 * - des cases spéciales
 */
public class Labyrinthe {

    // Constantes de types de cases
    public static final char MUR = 'X';
    public static final char PJ = 'P';
    public static final char VIDE = '.';
    public static final char MONSTER = 'M';
    public static final char PIEGE = 'O';
    public static final char TELEPORTEUR = 'T';
    public static final char SOINS = 'S';

    // Constantes d'action
    public static final String HAUT = "Haut";
    public static final String BAS = "Bas";
    public static final String GAUCHE = "Gauche";
    public static final String DROITE = "Droite";

    public Perso pj;
    public boolean[][] murs;
    public List<Monstre> monstres;
    public List<CasesSpeciale> cases;

    /**
     * Retourne la case suivante selon l'action effectuée.
     *
     * @param x position x actuelle
     * @param y position y actuelle
     * @param action direction à prendre
     * @return tableau avec les nouvelles coordonnées
     */
    static int[] getSuivant(int x, int y, String action) {
        switch (action) {
            case HAUT -> y--;
            case BAS -> y++;
            case DROITE -> x++;
            case GAUCHE -> x--;
            default -> throw new Error("action inconnue");
        }
        return new int[]{x, y};
    }

    /**
     * Construit un labyrinthe depuis un fichier texte.
     *
     * @param nom nom du fichier
     * @param j instance du jeu (pour le téléporteur)
     * @throws IOException si le fichier est introuvable
     */
    public Labyrinthe(String nom, LabyJeu j) throws IOException {
        FileReader fichier = new FileReader(nom);
        BufferedReader bfRead = new BufferedReader(fichier);

        int nbLignes = Integer.parseInt(bfRead.readLine());
        int nbColonnes = Integer.parseInt(bfRead.readLine());

        this.murs = new boolean[nbColonnes][nbLignes];
        this.pj = null;
        this.monstres = new ArrayList<>();
        this.cases = new ArrayList<>();

        String ligne = bfRead.readLine();
        int numeroLigne = 0;

        while (ligne != null) {
            for (int colonne = 0; colonne < ligne.length(); colonne++) {
                char c = ligne.charAt(colonne);
                switch (c) {
                    case MUR -> this.murs[colonne][numeroLigne] = true;
                    case VIDE -> this.murs[colonne][numeroLigne] = false;
                    case PJ -> {
                        this.murs[colonne][numeroLigne] = false;
                        this.pj = new Perso(colonne, numeroLigne, 10, 1);
                    }
                    case MONSTER -> this.monstres.add(new Zombie(colonne, numeroLigne));
                    case PIEGE -> this.cases.add(new Piege(colonne, numeroLigne));
                    case TELEPORTEUR -> this.cases.add(new Teleporteur(colonne, numeroLigne, j));
                    case SOINS -> this.cases.add(new Soins(colonne, numeroLigne));
                    default -> throw new Error("caractere inconnu " + c);
                }
            }
            ligne = bfRead.readLine();
            numeroLigne++;
        }

        bfRead.close();
    }

    /**
     * Déplace le joueur si possible (pas de mur ni zombie).
     *
     * @param action direction à prendre
     */
    public void deplacerPerso(String action) {
        int[] courante = {this.pj.x, this.pj.y};
        int[] suivante = getSuivant(courante[0], courante[1], action);
        this.pj.ancienM = action;

        if (!this.murs[suivante[0]][suivante[1]] && getZombie(suivante[0], suivante[1]) == null) {
            this.pj.x = suivante[0];
            this.pj.y = suivante[1];

            for (CasesSpeciale cs : cases) {
                if (cs.etreActiver(suivante[0], suivante[1])) {
                    cs.declencher(this.pj);
                    System.out.println(this.pj);
                }
            }
        }
    }

    /**
     * Déplace les monstres aléatoirement si le tirage le permet.
     */
    public void deplacerMonstres() {
        if (1 == (int) (Math.random() * 3)) {
            for (Monstre m : monstres) {
                int[] courante = {m.getX(), m.getY()};
                String[] action = {DROITE, GAUCHE, BAS, HAUT};
                int a = (int) (Math.random() * 4);
                int[] suivante = getSuivant(courante[0], courante[1], action[a]);
                m.ancienM = action[a];

                if (!this.murs[suivante[0]][suivante[1]] &&
                        !this.pj.etrePresent(suivante) &&
                        getZombie(suivante[0], suivante[1]) == null) {
                    m.deplacerMonstre(suivante);
                }

                if (m.etrePresent(suivante)) {
                    for (CasesSpeciale cs : this.cases) {
                        if (cs instanceof Piege && cs.etreActiver(suivante[0], suivante[1])) {
                            cs.declencher(m);
                            System.out.println(m);
                        }
                    }
                }
            }
        }
    }

    /**
     * Génère un certain nombre de zombies aléatoirement dans le labyrinthe.
     *
     * @param nombresMonstres nombre à générer
     */
    public void genererMonstres(int nombresMonstres) {
        int compteur = 0;
        while (compteur < nombresMonstres) {
            int i = (int) (Math.random() * this.getLength());
            int j = (int) (Math.random() * this.getLengthY());
            if (etreVide(i, j)) {
                compteur++;
                this.monstres.add(new Zombie(i, j));
            }
        }
    }

    /**
     * Vérifie si une case est libre (pas de mur, joueur, zombie ou case spéciale).
     *
     * @param x coordonnée x
     * @param y coordonnée y
     * @return true si vide
     */
    public boolean etreVide(int x, int y) {
        int[] pose = {x, y};
        return !getMur(x, y)
                && !pj.etrePresent(pose)
                && getZombie(x, y) == null
                && !contientCase(Piege.class, x, y);
    }

    /**
     * Récupère un zombie présent en (i, j).
     *
     * @param i position x
     * @param j position y
     * @return zombie si présent, sinon null
     */
    public Zombie getZombie(int i, int j) {
        for (Monstre m : monstres) {
            if (m instanceof Zombie z && z.getX() == i && z.getY() == j) {
                return z;
            }
        }
        return null;
    }

    /**
     * Vérifie si une case spéciale d'un certain type est en (i, j).
     *
     * @param type type de case (ex: Piege.class)
     * @param i x
     * @param j y
     * @return true si présente
     */
    public boolean contientCase(Class<?> type, int i, int j) {
        for (CasesSpeciale m : cases) {
            if (type.isInstance(m) && m.getX() == i && m.getY() == j) {
                if (m instanceof Piege p) {
                    return p.t; // actif
                }
                return true;
            }
        }
        return false;
    }

    /**
     * Remplace le joueur actuel par un nouveau tout en gardant sa position.
     *
     * @param perso nouveau personnage
     */
    public void chargerPerso(Perso perso) {
        int tmpX = this.pj.x;
        int tmpY = this.pj.y;
        this.pj = perso;
        this.pj.x = tmpX;
        this.pj.y = tmpY;
    }

    /**
     * Indique si le niveau est terminé (plus de monstres).
     *
     * @return true si vide
     */
    public boolean etreClear() {
        return this.monstres.isEmpty();
    }

    /**
     * Indique si le jeu est fini (joueur mort).
     *
     * @return true si mort
     */
    public boolean etreFini() {
        return this.pj.etreMort();
    }

    // ##################################
    // GETTERS
    // ##################################

    /**
     * @return nombre de lignes (Y)
     */
    public int getLengthY() {
        return murs[0].length;
    }

    /**
     * @return nombre de colonnes (X)
     */
    public int getLength() {
        return murs.length;
    }

    /**
     * Renvoie si une case est un mur.
     *
     * @param x abscisse
     * @param y ordonnée
     * @return true si mur
     */
    public boolean getMur(int x, int y) {
        return this.murs[x][y];
    }
}

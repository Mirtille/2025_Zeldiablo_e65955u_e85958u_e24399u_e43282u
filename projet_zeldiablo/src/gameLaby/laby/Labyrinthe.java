package gameLaby.laby;

import java.io.BufferedReader;
import java.io.FileReader;
import java.io.IOException;
import java.util.ArrayList;
import java.util.List;

/**
 * classe labyrinthe. represente un labyrinthe avec
 * <ul> des murs </ul>
 * <ul> un personnage (x,y) </ul>
 */
public class Labyrinthe {

    /**
     * Constantes char
     */
    public static final char MUR = 'X';
    public static final char PJ = 'P';
    public static final char VIDE = '.';
    public static final char MONSTER = 'M';
    public static final char PIEGE = 'O';
    public static final char TELEPORTEUR = 'T';
    public static final char SOINS = 'S';

    /**
     * constantes actions possibles
     */
    public static final String HAUT = "Haut";
    public static final String BAS = "Bas";
    public static final String GAUCHE = "Gauche";
    public static final String DROITE = "Droite";

    /**
     * attribut du personnage
     */
    public Perso pj;

    /**
     * les murs du labyrinthe
     */
    public boolean[][] murs;

    public List<Zombie> monstres ;

    public List<CasesSpeciale> cases;

    /**
     * retourne la case suivante selon une actions
     *
     * @param x      case depart
     * @param y      case depart
     * @param action action effectuee
     * @return case suivante
     */
    static int[] getSuivant(int x, int y, String action) {
        switch (action) {
            case HAUT:
                // on monte une ligne
                y--;
                break;
            case BAS:
                // on descend une ligne
                y++;
                break;
            case DROITE:
                // on augmente colonne
                x++;
                break;
            case GAUCHE:
                // on augmente colonne
                x--;
                break;
            default:
                throw new Error("action inconnue");
        }
        int[] res = {x, y};
        return res;
    }

    /**
     * charge le labyrinthe
     *
     * @param nom nom du fichier de labyrinthe
     * @return labyrinthe cree
     * @throws IOException probleme a la lecture / ouverture
     */
    public Labyrinthe(String nom) throws IOException {
        // ouvrir fichier
        FileReader fichier = new FileReader(nom);
        BufferedReader bfRead = new BufferedReader(fichier);

        int nbLignes, nbColonnes;
        // lecture nblignes
        nbLignes = Integer.parseInt(bfRead.readLine());
        // lecture nbcolonnes
        nbColonnes = Integer.parseInt(bfRead.readLine());

        // creation labyrinthe vide
        this.murs = new boolean[nbColonnes][nbLignes];
        this.pj = null;
        this.monstres = new ArrayList<Zombie>();
        this.cases = new ArrayList<CasesSpeciale>();

        // lecture des cases
        String ligne = bfRead.readLine();

        // stocke les indices courants
        int numeroLigne = 0;

        // parcours le fichier
        while (ligne != null) {

            // parcours de la ligne
            for (int colonne = 0; colonne < ligne.length(); colonne++) {
                char c = ligne.charAt(colonne);
                switch (c) {
                    case MUR:
                        this.murs[colonne][numeroLigne] = true;
                        break;
                    case VIDE:
                        this.murs[colonne][numeroLigne] = false;
                        break;
                    case PJ:
                        // pas de mur
                        this.murs[colonne][numeroLigne] = false;
                        // ajoute PJ
                        this.pj = new Perso(colonne, numeroLigne, 10, 1);
                        break;
                    case MONSTER:
                        this.monstres.add(new Zombie(colonne, numeroLigne)) ;
                        break;
                    case PIEGE:
                        this.cases.add(new Piege(colonne, numeroLigne)) ;
                        break;
                    case TELEPORTEUR:
                        this.cases.add(new Teleporteur(colonne, numeroLigne)) ;
                        break;
                    case SOINS:
                        this.cases.add(new Soins(colonne, numeroLigne));
                        break;

                        default:
                        throw new Error("caractere inconnu " + c);
                }
            }

            // lecture
            ligne = bfRead.readLine();
            numeroLigne++;
        }

        // ferme fichier
        bfRead.close();
    }


    /**
     * deplace le personnage en fonction de l'action.
     * gere la collision avec les murs
     *
     * @param action une des actions possibles
     */
    public void deplacerPerso(String action) {
        // case courante
        int[] courante = {this.pj.x, this.pj.y};

        // calcule case suivante
        int[] suivante = getSuivant(courante[0], courante[1], action);


        // si c'est pas un mur, on effectue le deplacement
        if (!this.murs[suivante[0]][suivante[1]]) {
            // on met a jour personnage
            this.pj.x = suivante[0];
            this.pj.y = suivante[1];
        }
        for(CasesSpeciale cs : this.cases) {
            if(cs.etreActiver(suivante[0], suivante[1])) {
                cs.declencher();
            }
            if(etrePiege(suivante[0], suivante[1])) {
                pj.vie -= 5;
            }
            if(etreSoins(suivante[0], suivante[1])) {
                pj.vie += 5;
            }
        }
    }

    public void deplacerMonstres() {
        for (Zombie m : monstres) {
            int[] courante = {m.getX(), m.getY()};
            String[] action = {DROITE, GAUCHE, BAS, HAUT} ;
            int a = (int) (Math.random() * (4));
            int[] suivante = getSuivant(courante[0], courante[1], action[a]);
            if (!this.murs[suivante[0]][suivante[1]] & !this.pj.etrePresent(suivante) & !etreMonstre(suivante[0], suivante[1])) {
                m.deplacerMonstre(suivante);
            }
            if (m.etrePresent(suivante)) {
                for (CasesSpeciale cs : this.cases) {
                    if (cs.etreActiver(suivante[0], suivante[1])) {
                        cs.declencher();
                    }
                }
            }
        }
    }

    public void genererMonstres() {

    }

    public boolean etreMonstre(int i , int j) {
        boolean tmp = false;
        for(Zombie m : monstres) {
            if(m.getX() == i && m.getY() == j) {
                tmp = true;
            }
        }
        return tmp ;
    }

    public boolean etrePiege(int i , int j) {
        boolean tmp = false;
        for(CasesSpeciale m : cases) {
            if (m instanceof Piege) {
                Piege p = (Piege) m;
                if (p.t) {
                    if (p.getX() == i && p.getY() == j) {
                        tmp = true;
                    }
                }
            }
        }

        return tmp;
    }

    public boolean etreTeleporteur(int i , int j) {
        boolean tmp = false;
        for(CasesSpeciale m : cases) {
            if (m instanceof Teleporteur) {
                Teleporteur t = (Teleporteur) m;
                if (t.getX() == i && t.getY() == j) {
                    tmp = true;
                }
            }
        }

        return tmp ;
    }

    public boolean etreSoins(int i , int j) {
        boolean tmp = false;
        for(CasesSpeciale m : cases) {
            if (m instanceof Soins) {
                Soins s = (Soins) m;
                if (s.getX() == i && s.getY() == j) {
                    tmp = true;
                }
            }
        }

        return tmp ;
    }


    /**
     * jamais fini
     *
     * @return fin du jeu
     */
    public boolean etreFini() {
        return false;
    }

    // ##################################
    // GETTER
    // ##################################

    /**
     * return taille selon Y
     *
     * @return
     */
    public int getLengthY() {
        return murs[0].length;
    }

    /**
     * return taille selon X
     *
     * @return
     */
    public int getLength() {
        return murs.length;
    }

    /**
     * return mur en (i,j)
     * @param x
     * @param y
     * @return
     */
    public boolean getMur(int x, int y) {
        // utilise le tableau de boolean
        return this.murs[x][y];
    }
}

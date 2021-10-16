package methodes;
import sacados.SacADos;
import sacados.Objet;
import java.util.List;
public class ProgDynamique extends Methode {
    public static void resoudre(SacADos sac){
        List<Objet> objetsAajouter = sac.getObjetsChemin();
        int nbObjets = objetsAajouter.size();
        double[][] matriceObjets = new double[nbObjets + 1][(int)sac.getPoidsMaximal() + 1];
        // MATRICE[i][j] avec i le n-ième objet et j le poids de ce dernier
        // Chaque case de la matrice correspond à la valeur de cet objet

        // initialisation de la première ligne de la matrice
        for (int j = 0; j <= sac.getPoidsMaximal(); j++) {
            matriceObjets[0][j] = objetsAajouter.get(0).getPds() > j ? 0 : objetsAajouter.get(0).getVal();
        }

        // on remplit les autres lignes (ligne par ligne) de la matrice
        for (int i = 1; i < nbObjets; i++) {
            for (int j = 0; j <= sac.getPoidsMaximal(); j++) {
                matriceObjets[i][j] = objetsAajouter.get(i).getPds() > j ? matriceObjets[i-1][j] : Math.max(matriceObjets[i-1][j],
                        matriceObjets[i-1][(int)(j - objetsAajouter.get(i).getPds())] + objetsAajouter.get(i).getVal());
            }
        }

        // on va chercher le bénéfice le plus élevé de la matrice
        /*float res = matriceObjets[nbObjets][(int)sac.getPoidsMaximal()];
        int j = (int)sac.getPoidsMaximal();

        int cpt = 0;
        for (int i = nbObjets; i > 0  &&  res > 0; i--) {
          if (res != matriceObjets[i - 1][j]) {
            sac.ajouterObjet(objetsAajouter.get(i-1));
            cpt++;
            res -= objetsAajouter.get(i-1).getVal();
            j -= objetsAajouter.get(i-1).getPds();
          }
        }*/
        int nbObjetsAajouter = objetsAajouter.size() - 1;
        int poidsMaximal = (int)sac.getPoidsMaximal();
        int cpt=0;
        // On décale l'indice vers la gauche, tant que la valeur de l'ensemble
        // est au plus haut
        while (poidsMaximal >= 0 && matriceObjets[nbObjetsAajouter][poidsMaximal] == matriceObjets[nbObjetsAajouter][poidsMaximal - 1]) {
            poidsMaximal--;
        }

        while (poidsMaximal > 0) {
            while (nbObjetsAajouter > 0 && matriceObjets[nbObjetsAajouter][poidsMaximal] == matriceObjets[nbObjetsAajouter - 1][poidsMaximal])
                nbObjetsAajouter--;
            poidsMaximal = (int) (poidsMaximal - objetsAajouter.get(nbObjetsAajouter).getPds());
            if (poidsMaximal >= 0) {
                sac.ajouterObjet(objetsAajouter.get(nbObjetsAajouter));
                cpt++;
            }
            nbObjetsAajouter--;
        }

        System.out.println("PROGRAMMATION DYNAMIQUE");
        System.out.println(cpt + " objets sur " + objetsAajouter.size() + " ont été ajoutés au sac à dos");
    }
}

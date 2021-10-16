package sacados;
import methodes.*;
import java.io.File;
import java.text.DecimalFormat;
import java.text.NumberFormat;
import java.util.ArrayList;
import java.util.Scanner;
import java.util.List;
public class SacADos {
    private List<Objet> objets;
    private String chemin;
    private float valeurTotale;
    private float poidsTotal;
    private float poidsMaximal;

    public SacADos(String chemin, float poidsMaximal) {
        this.objets=new ArrayList<Objet>();
        this.chemin = chemin;
        this.poidsMaximal = poidsMaximal;
        this.valeurTotale = 0;
        this.poidsTotal = 0;
    }

    // GETTERS de la classe SACADOS //
    public float getValeurTotale(){
        return this.valeurTotale;
    }
    public float getPoidsMaximal(){
        return this.poidsMaximal;
    }
    public List<Objet> getObjets(){
        return this.objets;
    }

    public String toString(){
        DecimalFormat df = new DecimalFormat("#.#");
        StringBuilder sb = new StringBuilder();
        sb.append("Valeur totale dans le sac : " + this.valeurTotale + "\n");
        sb.append("Poids total/Poids Maximal: " + df.format(this.poidsTotal) + "/" + this.poidsMaximal + "\n");
        for(Objet o : this.objets)
            sb.append("> " + o.toString() + "\n");
        return sb.toString();
    }

    // METHODES //
    public int ajouterObjet(Objet o){
        if(this.poidsTotal + o.getPds() <= this.poidsMaximal){
            this.objets.add(o);
            this.poidsTotal += o.getPds();
            this.valeurTotale += o.getVal();
            return 1;
        }
        return -1;
    }

    

    /* vraiment utile comme méthode ?
    public int retirerObjet(int i){
        if(this.objets[i] != null){
            this.poidsTotal -= this.objets[i].getPds();
            this.valeurTotale -= this.objets[i].getVal();
            this.objets[i] = null; 
            return 1;
        }
        return -1;
    } */

    public void resoudre(String methode){
        switch(methode){
            case "gloutonne":
                Gloutonne.resoudre(this);
                break;
            case "progdynamique":
                ProgDynamique.resoudre(this);
                break;
            case "PSE":
                PSE.resoudre(this);
                break;
        }
    }

    /* Cette méthode permet de lire le fichier texte pointé par le chemin et retourner
    un tableau les contenant
    */
    public List<Objet> getObjetsChemin(){
        List<Objet> objets = new ArrayList<Objet>();
        try {
            Scanner sc = new Scanner(new File(chemin));
            while(sc.hasNext()){
                String[] buff = sc.nextLine().split(";");
                objets.add(new Objet(buff[0], Float.parseFloat(buff[1]), Float.parseFloat(buff[2])));
            }
        } catch(Exception e) {
            System.out.println("Erreur lecture fichier");
            return null;
        }
        return objets;
    }

    /* La méthode GLOUTONNE appelle la fonction quickSort qui va
    trier les objets en fonction de le rapport valeur / poids 
    par ordre décroissant et qui va essayer des les ajouter dans
    le sac un à un.
    */
    /* public void gloutonne(){
        Objet[] objetsAajouter = getObjetsChemin();
        int cpt = 0;
        Tri.quickSort(objetsAajouter, 0, objetsAajouter.length - 1);
        for(Objet o : objetsAajouter){
            cpt = ajouterObjet(o) > 0 ? cpt + 1 : cpt;
        }
        System.out.println("METHODE GLOUTONNE");
        System.out.println(cpt + " objets sur " + objetsAajouter.length + " ont été ajoutés au sac à dos");
    }

    public void progDynamique(){
        Objet[] objetsAajouter = getObjetsChemin();
        int nbObjets = objetsAajouter.length;
        float[][] matriceObjets = new float[nbObjets + 1][(int)this.poidsMaximal + 1];
        // MATRICE[i][j] avec i le n-ième objet et j le poids de ce dernier
        // Chaque case de la matrice correspond à la valeur de cet objet

        // initialisation de la première ligne de la matrice
        for (int j = 0; j <= this.poidsMaximal; j++) {
            matriceObjets[0][j] = objetsAajouter[0].getPds() > j ? 0 : objetsAajouter[0].getPds();
        }

        // on remplit les autres lignes (ligne par ligne) de la matrice
        for (int i = 1; i <= nbObjets; i++) {
            for (int j = 0; j <= this.poidsMaximal; j++) {
                matriceObjets[i][j] = objetsAajouter[i - 1].getPds() > j ? matriceObjets[i-1][j] : Math.max(matriceObjets[i-1][j],
                        matriceObjets[i-1][(int)(j - objetsAajouter[i-1].getPds())] + objetsAajouter[i-1].getVal());
            }
        }

        // on va chercher le bénéfice le plus élevé de la matrice
        float res = matriceObjets[nbObjets][(int)this.poidsMaximal];
        int j = (int)this.poidsMaximal;

        int cpt = 0;
        for (int i = nbObjets; i > 0  &&  res > 0; i--) {
          if (res != matriceObjets[i - 1][j]) {
            ajouterObjet(objetsAajouter[i - 1]);
            cpt++;
            res -= objetsAajouter[i - 1].getVal();
            j -= objetsAajouter[i - 1].getPds();
          }
        }
        System.out.println("PROGRAMMATION DYNAMIQUE");
        System.out.println(cpt + " objets sur " + objetsAajouter.length + " ont été ajoutés au sac à dos");
    } */

    /*
    // VERSION ECLATAX DE LA PROGRAMMATION DYNAMIQUE
    public void progDynamique2(){
        Objet[] objetsAajouter = getObjetsChemin();
        int nbObjets = objetsAajouter.length;
        float[][] matriceObjets = new float[nbObjets + 1][(int)this.poidsMaximal + 1];
        // MATRICE[i][j] avec i le n-ième objet et j le poids de ce dernier
        // Chaque case de la matrice correspond à la valeur de cet objet

        // initialisation de la première ligne de la matrice
        for (int j = 0; j <= this.poidsMaximal; j++) {
                matriceObjets[0][j] = objetsAajouter[0].getPds() > j ? 0 : objetsAajouter[0].getPds();
        }

        // on remplit les autres lignes (ligne par ligne) de la matrice
        for (int i = 1; i <= nbObjets; i++) {
            for (int j = 0; j <= this.poidsMaximal; j++) {
                    matriceObjets[i][j] = objetsAajouter[i - 1].getPds() > j ? matriceObjets[i-1][j] : Math.max(matriceObjets[i-1][j],
                    matriceObjets[i-1][(int)(j - objetsAajouter[i-1].getPds())] + objetsAajouter[i-1].getVal());
            }
        }

        int i = objetsAajouter.length - 1;
        int j = (int)this.poidsMaximal;
        while(matriceObjets[i][j] == matriceObjets[i][j-1])
            j--;

        int cpt = 0;
        while(j>0) {
            while (i > 0 && matriceObjets[i][j] == matriceObjets[i - 1][j])
                i--;
            j = (int)(j - objetsAajouter[i].getPds());
            if(j >= 0) {
                ajouterObjet(objetsAajouter[i]);
                cpt++;
            }
            i--;
        }
        System.out.println("PROGRAMMATION DYNAMIQUE");
        System.out.println(cpt + " objets sur " + objetsAajouter.length + " ont été ajoutés au sac à dos");
    }*/


}

package Appli;

import java.io.File;
import java.util.ArrayList;
import java.util.Scanner;

public class SacADos {
    private ArrayList<Objet> objets;
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

    public String toString(){
        StringBuilder sb = new StringBuilder();
        sb.append("VAL : " + this.valeurTotale + "\n");
        sb.append("PDS : " + this.poidsTotal + "/" + this.poidsMaximal + "\n");
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
                gloutonne();
                break;
            case "progdynamique":
                progDynamique();
                break;
            case "pse":
                pse();
                break;
        }
    }

    /* Cette méthode permet de lire le fichier texte pointé par le chemin et retourner
    un tableau les contenant
    */
    public Objet[] getObjetsChemin(){
        ArrayList<Objet> t = new ArrayList<Objet>();
        try {
            Scanner sc = new Scanner(new File(chemin));
            while(sc.hasNext()){
                String[] buff = sc.nextLine().split(";");
                t.add(new Objet(buff[0], Float.parseFloat(buff[1]), Float.parseFloat(buff[2])));
            }
        } catch(Exception e) {
            System.out.println("Erreur lecture fichier");
            return null;
        }
        Objet[] obj = new Objet[t.size()];
        obj = t.toArray(obj);
        return obj;
    }

    /* La méthode GLOUTONNE appelle la fonction quickSort qui va
    trier les objets en fonction de le rapport valeur / poids 
    par ordre décroissant et qui va essayer des les ajouter dans
    le sac un à un.
    */
    public void gloutonne(){
        Objet[] objetsAajouter = getObjetsChemin();
        int cpt = 0;
        Tri.quickSort(objetsAajouter, 0, objetsAajouter.length - 1);
        for(Objet o : objetsAajouter){
            cpt = ajouterObjet(o) > 0 ? cpt + 1 : cpt;
        }
        System.out.println(cpt + " objets sur " + objetsAajouter.length + " ont été ajoutés au sac à dos");
    }

    public void progDynamique(){

    }

    public void pse(){

    }
}

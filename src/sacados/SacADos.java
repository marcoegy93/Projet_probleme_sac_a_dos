/**
 * Fichier : SacADos.java
 * Auteurs : BALAMON Marco, SAAVEDRA Arthur
 * Date : 17/10/2021
 */
package sacados;

import methodes.*;
import java.io.File;
import java.text.DecimalFormat;
import java.text.NumberFormat;
import java.util.ArrayList;
import java.util.Scanner;
import java.util.List;

public class SacADos {
    // ------ ATTRIBUTS DE LA CLASSE ------ //
    private List<Objet> objets;
    private String chemin;
    private float valeurTotale;
    private float poidsTotal;
    private float poidsMaximal;

    // ------ MÉTHODES DE LA CLASSE ------ //

    /**
     * Constructeur de la classe sacados
     * @param chemin (String) le chemin du fichier .txt contenant les objets à ajouter dans le sac
     * @param poidsMaximal (Float) le poids max du sac à dos
     */
    public SacADos(String chemin, float poidsMaximal) {
        this.objets = new ArrayList<Objet>();
        this.chemin = chemin;
        this.poidsMaximal = poidsMaximal;
        this.valeurTotale = 0;
        this.poidsTotal = 0;
    }

    /**
     * Méthode ajouterObjet()
     * Ajoute un objet dans le sac s'il peut rentrer
     * @param o (Objet) l'ojet à ajouter dans le sac
     * @return (Integer) -1 si échec, 1 si succès
     */
    public int ajouterObjet(Objet o) {
        if (this.poidsTotal + o.getPds() <= this.poidsMaximal) {
            this.objets.add(o);
            this.poidsTotal += o.getPds();
            this.valeurTotale += o.getVal();
            return 1;
        }
        return -1;
    }

    /**
     * Méthode resoudre()
     * Méthode permettant de résoudre le problème du sac à dos
     * @param methode (String) méthode à utiliser pour la résolution du pb du sac
     */
    public void resoudre(String methode) {
        switch (methode) {
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

    /**
     * Méthode getObjetsChemin()
     * Méthode permettant de lire le fichier texte pointé par le chemin et retournant
     * un tableau les contenant
     * @return (List<Objets>) liste des objets contenus dans le fichier texte
     */
    public List<Objet> getObjetsChemin() {
        List<Objet> objets = new ArrayList<Objet>();
        try {
            Scanner sc = new Scanner(new File(chemin));
            while (sc.hasNext()) {
                String[] buff = sc.nextLine().split(";");
                objets.add(new Objet(buff[0], Float.parseFloat(buff[1]), Float.parseFloat(buff[2])));
            }
        } catch (Exception e) {
            System.out.println("Erreur lecture fichier");
            return null;
        }
        return objets;
    }

    // ------ GETTERS & SETTERS ------ //

    /**
     * Méthode getValeurTotale()
     * @return (Float) la valeur totale du sac
     */
    public float getValeurTotale(){
        return this.valeurTotale;
    }

    /**
     * Méthode getPoidsMaximal()
     * @return (Float) le poids total du sac
     */
    public float getPoidsMaximal(){
        return this.poidsMaximal;
    }

    /**
     * Méthode getObjets()
     * @return (List<Objet>) la liste des objets contenus dans le sac
     */
    public List<Objet> getObjets(){
        return this.objets;
    }

    /**
     * Méthode toString()
     * @return (String) chaîne de caractères contenant les infos du sac à dos
     */
    public String toString(){
        DecimalFormat df = new DecimalFormat("#.#");
        StringBuilder sb = new StringBuilder();
        sb.append("Valeur totale dans le sac : " + this.valeurTotale + "\n");
        sb.append("Poids total/Poids Maximal: " + df.format(this.poidsTotal) + "/" + this.poidsMaximal + "\n");
        for(Objet o : this.objets)
            sb.append("> " + o.toString() + "\n");
        return sb.toString();
    }
}

/**
 * Fichier : Objet.java
 * Auteurs : BALAMON Marco, SAAVEDRA Arthur
 * Date : 17/10/2021
 */
package sacados;

public class Objet {
    // ------ ATTRIBUTS DE LA CLASSE ------ //
    private String nom;
    private float valeur;
    private float poids;

    // ------ MÉTHODES DE LA CLASSE ------ //
    /**
     * Constructeur de la classe objet
     * Constructeur vide
     */
    public Objet(){
    }

    /**
     * Constructeur de la classe objet
     * Constructeur complet
     * @param nom (String) le nom de l'objet
     * @param poids (Float) le poids de l'objet
     * @param valeur (Float) la valeur de l'objet
     */
    public Objet(String nom, float poids, float valeur){
        this.nom=nom.trim();
        this.valeur=valeur;
        this.poids=poids;
    }

    // ------ GETTERS & SETTERS ------ //

    /**
     * Méthode getNom()
     * @return (String) le nom de l'objet
     */
    public String getNom(){
        return this.nom;
    }

    /**
     * Méthode getVal()
     * @return (Float) la valeur de l'objet
     */
    public float getVal(){
        return this.valeur;
    }

    /**
     * Méthode getPds()
     * @return (Float) le poids de l'objet
     */
    public float getPds(){
        return this.poids;
    }

    /**
     * Méthode getRapportValPds()
     * @return (Float) le rapport prix / poids de l'objet
     */
    public float getRapportValPds() {
        return (this.valeur/this.poids);
    }

    /**
     * Méthode toString()
     * @return (String) chaîne de caractères contenant les informations de l'objet
     */
    @Override
    public String toString(){
        String s = (this.nom + " ; " + this.poids + " ; " + this.valeur);
        return s;
    }
}

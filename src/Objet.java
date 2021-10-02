public class Objet {
    private String nom;
    private float valeur;
    private float poids;

    public Objet(){
    }

    public Objet(String nom, float valeur, float poids){
        this.nom=nom;
        this.valeur=valeur;
        this.poids=poids;
    }

    // GETTERS de la classe OBJET //
    public String getNom(){
        return this.nom;
    }
    public float getVal(){
        return this.valeur;
    }
    public float getPds(){
        return this.poids;
    }
    public float getRapportValPds() {
        return (this.valeur/this.poids);
    }
    public String toString(){
        String s = (this.nom + " ; " + this.poids + " ; " + this.valeur);
        return s;
    }
}

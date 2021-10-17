/**
 * Fichier : Appli.java
 * Auteurs : BALAMON Marco, SAAVEDRA Arthur
 * Date : 17/10/2021
 */
package appli;

import sacados.SacADos;

public class Appli {

    // ------ MÉTHODES DE LA CLASSE ------ //
    /**
     * Méthode app()
     * Méthode permettant de récupérer les arguments données en paramètres du prog. et lance la
     * méthode de résolution adéquate
     * @param args (String[]) les arguments donnés à l'ouverture du programme
     */
    public static void app(String[] args){
        if(args[0].compareTo("resoudre-sac-a-dos")==0){
            String chemin = args[1];
            float poidsMaximal= Float.parseFloat(args[2]);
            String methode = args[3];
            SacADos sac = new SacADos(chemin,poidsMaximal);
            /* BufferedReader lecteurAvecBuffer = null;
            String ligne;

            try
            {
                lecteurAvecBuffer = new BufferedReader(new FileReader(chemin));
            }
            catch(FileNotFoundException exc)
            {
                System.out.println("Erreur d'ouverture");
            }
            while ((ligne = lecteurAvecBuffer.readLine()) != null)
                System.out.println(ligne);
            lecteurAvecBuffer.close();
            */
            sac.resoudre(methode);
        }else{
            System.out.println("La syntaxe exacte pour lancer le programme est resoudre-sac-a-dos");
        }
    }
}

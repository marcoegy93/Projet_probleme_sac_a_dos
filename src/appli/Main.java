/**
 * Fichier : Main.java
 * Auteurs : BALAMON Marco, SAAVEDRA Arthur
 * Date : 17/10/2021
 */
package appli;

import sacados.SacADos;

public class Main {

    // ------ MÉTHODES DE LA CLASSE ------ //
    /**
     * Méthode main()
     * Méthode principale du projet
     * @param args (String[]) les arguments donnés à l'ouverture du programme
     */
    public static void main(String[] args) {
        SacADos sac = new SacADos("C:\\Users\\saave\\Documents\\IntelliJ\\projAAV\\src\\items2.txt",30);
        sac.resoudre("PSE");
        System.out.println(sac.toString());
    }
}


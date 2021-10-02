import java.io.BufferedReader;
import java.io.FileNotFoundException;
import java.io.FileReader;
import java.io.IOException;
import java.util.Scanner;

public class Main {
    public static void main(String[] args) throws IOException {
        if(args[0].compareTo("resoudre-sac-a-dos")==0){
            String chemin = args[1];
            float poidsMaximal= Float.parseFloat(args[2]);
            String methode = args[3];
            SacADos sac = new SacADos(chemin,poidsMaximal);
            BufferedReader lecteurAvecBuffer = null;
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
            sac.resoudre(methode);

        }else{
            System.out.println("La syntaxe exacte pour lancer le programme est resoudre-sac-a-dos");
        }

    }
}


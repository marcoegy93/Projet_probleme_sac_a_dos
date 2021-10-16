package sacados;

public class Main {
    public void app(String[] args){
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

    public static void main(String[] args) {
        SacADos sac = new SacADos("E:\\Desktop\\2ème année\\période A\\Algo avancé\\projet_AAV\\src\\wesh.txt",30);
        /*Objet[] obj = sac.getObjetsChemin();
        for(Objet o : obj){
            System.out.println(o);
        }*/
        //sac.resoudre("pse");
        sac.resoudre("PSE");

        System.out.println(sac.toString());
    }
}


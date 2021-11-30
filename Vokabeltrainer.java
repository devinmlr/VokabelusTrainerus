import java.util.Scanner;

public class Vokabeltrainer
{
    private MyList vList = new MyList();
    
    Scanner s = new Scanner(System.in);
    
    public Vokabeltrainer()
    {
        showMenu();
    }
    
    /* Funktionen:
     * h->help
     * n->neue Vokabel
     * l-> Vokabeln lernen
     * m->menu
     * q->quit
    */
    
    public void input () {
        String e = s.nextLine();
        if (e.equals("h")) {
            showHelp();
        }else if (e.equals("n")) {
            addVocab();
        }else if (e.equals("l")) {
            learnVocab();
        }else if (e.equals("q")) {
            System.out.println("");
        }
        else {
            System.out.println("Unbekannter Befehl! \nTippe 'h' für Hilfe!");
            input();
        }
    }

    public void showMenu () {
        System.out.println("Willkommen im Menü des Vokabeltrainers!");
        
        input();
    }
    
    public void showHelp () {
        System.out.println("Die Verwendung vom Vokabeltrainer: \n'h'    -    help \n'n'    -    Neue Vokabel hinzufügen \n'l'    -    Vokabeln lernen \n'm'    -    Menü aufrufen \n'q'    -    Beenden");
        
        input();
    }
    
    public void addVocab () {
        System.out.println("Bitte gib das deutsche Wort ein!");
        String orig = s.nextLine();
        System.out.println("Bitte gib das englische Wort ein!");
        String trans = s.nextLine();
        vList.append(orig);
        vList.append(trans);
        System.out.println("Folgende Worte wurden hinzugefügt: " + orig + " : " + trans);
        newVocab();
    }
    
    private void newVocab () {
        System.out.println("Möchtest du noch weitere Vokabeln hinzufügen? [y/n]");
        String e = s.nextLine();
        if (e.equals("y")) {
            addVocab();
        } else if (e.equals("n")) {
            System.out.println("Du wirst nun ins Menü zurück geschickt!");
            showMenu();
        } else {
            newVocab();
        }
    }
    
    public void learnVocab () {
        System.out.println("Wieviele Vokabeln möchtest du lernen?");
        String e = s.nextLine();
        System.out.println("Vielen Dank für deine Angabe, diese Funktion wird bald verfügbar sein.");
        vList.toFirst();
        do {
            String vocOrig = vList.getContent().toString();
            vList.next();
            String vocTrans = vList.getContent().toString();
            if (vList.getNext() != null) {
                vList.next();
            }
            askVocab(vocOrig, vocTrans);
        }while(vList.getNext() != null);
        System.out.println("Möchtest du ins Menü zurückkehren? [y/n]");
        e = s.nextLine();
        if (e.equals("y")) {
            showMenu();
        } else {
            System.out.println("Gibt leider keine Alternative :)");
            showMenu();
        }
        showMenu();
    }
    
    private void askVocab (String vocOrig, String vocTrans) {
        System.out.println("Bitte übersetze folgende Vokabel: " + vocOrig);
        String e = s.nextLine();
        if (e.equals(vocTrans)) {
            if (vList.getNext() != null) {
                System.out.println("Richtig! Mach dich bereit für die nächste Vokabel!");
            } else {
                System.out.println("Wunderbar, das wars dann auch schon!");
            }
        } else {
            System.out.println("Das war leider nicht richtig. Bitte versuche es erneut!");
            askVocab(vocOrig, vocTrans);
        }
    }
}

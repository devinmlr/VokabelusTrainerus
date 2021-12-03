import java.util.Scanner;
import java.lang.Math;

public class Vokabeltrainer
{
    private MyList vList = new MyList("voc");
    
    private String sprache = "englisch";
    
    Scanner s = new Scanner(System.in);
    
    public Vokabeltrainer()
    {
        vList.append("start");
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
            //System.out.println(rand(0));
            showHelp();
        }else if (e.equals("n")) {
            addVocab();
        }else if (e.equals("e")) {
            editVoc();
        }else if (e.equals("l")) {
            learnVocab();
        }else if (e.equals("a")) {
            vList.printList();
            showMenu();
        }else if (e.equals("s")) {
            searchVoc();
        }else if (e.equals("q")) {
            System.out.println("");
        }
        else {
            System.out.println("Unbekannter Befehl! \nTippe 'h' für Hilfe!");
            input();
        }
    }

    public void showMenu () {
        System.out.println("Willkommen im Menü des Vokabeltrainers! Was möchtest du tun?");
        
        input();
    }
    
    public void showHelp () {
        System.out.println("Die Verwendung vom Vokabeltrainer: \n'h'    -    help \n'n'    -    Neue Vokabel hinzufügen \n'l'    -    Vokabeln lernen \n'a'    -    Vokabelliste ausgeben \n's'    -    Vokabel suchen  \n'm'    -    Menü aufrufen \n'q'    -    Beenden");
        
        input();
    }
    
    public void addVocab () {
        String[] voc = new String[2];
        System.out.println("Bitte gib das deutsche Wort ein!");
        voc[0] = s.nextLine();
        System.out.println("Bitte gib das englische Wort ein!");
        voc[1] = s.nextLine();
        vList.append(voc);
        System.out.println("Folgende Worte wurden hinzugefügt: " + voc[0] + " : " + voc[1]);
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
        String[] voc = new String[2];
        
        System.out.println("Wieviele Vokabeln möchtest du lernen?");
        String e = s.nextLine();
        System.out.println("Vielen Dank für deine Angabe, diese Funktion wird bald verfügbar sein.");
        vList.toFirst();
        vList.next();
        
        do {
            if (vList.getContent() != null) {
                voc = (String[])vList.getContent();
                vList.remove();
                //vList.toFirst();
                //vList.append("start");
                //vList.append("start");
                if (!askVocab(voc[0], voc[1])) {
                    vList.append(voc);
                }
            } else {
                System.out.println("Leider ist die Vokabelliste leer! Bitte füge im Menü weitere Vokabeln hinzu.");
                break;
            }
        }while(vList.getNext() != null);
        System.out.println("Du wirst nun ins Menü zurück geschickt!");
        showMenu();
    }
    
    private boolean askVocab (String vocOrig, String vocTrans) {
       boolean right=false;
       System.out.println("Bitte übersetze folgende Vokabel: " + vocOrig);
       String e = s.nextLine();
       if (e.equals(vocTrans)) {
           if (vList.getNext() != null) {
               System.out.println("Richtig! Mach dich bereit für die nächste Vokabel!");
           } else {
               System.out.println("Wunderbar, das wars dann auch schon!");
           }
           right = true;
       } else {
           System.out.println("Das war leider nicht richtig. Bitte versuche es erneut!");
           right = askVocab(vocOrig, vocTrans);
       }
       return right;
    }
    
    public void searchVoc () {
        System.out.println("Welche Vokabel möchtest du finden? (beide sprachen akzeptiert!)");
        String e = s.nextLine();
        vList.search(e);
        showMenu();
    }
    
    private int rand (int old) {
        System.out.println(vList.getLength());
        int num = 0;
        if (vList.getLength() != 0) {
            num = (int)(Math.random()*(vList.getLength()+1));
            if (num == old) {
                num = rand(old);
            }
        }
        
        return num;
    }
    
    public void editVoc () {
        System.out.println("Welche Vokabel möchtest du bearbeiten? (Bitte Stelle in Liste angeben)");
        String e = s.nextLine();
        String[] voc = new String[2];
        int stelle = 0;
        try {
            stelle = Integer.parseInt(e);
            System.out.println(vList.getElement(stelle)[0] + " : " + vList.getElement(stelle)[1]);
        } catch (Exception ex) {
            editError(ex.toString());
        }
        System.out.println("Welche Sprache möchtest du bearbeiten? [deutsch/" + sprache +"]");
        e = s.nextLine();
        if (e.equals("deutsch")) {
            System.out.println("Bitte gib deine Korrektur ein");
            e = s.nextLine();
            vList.edit(stelle, 0, e);
        } else if (e.equals("englisch")) {
            System.out.println("Bitte gib deine Korrektur ein");
            e = s.nextLine();
            vList.edit(stelle, 1, e);
        }
        voc = (String[])vList.getContent();
        System.out.println("Du hast die Vokabel zu " + voc[0] + " : " + voc[1] + " geändert \nSomit geht es zurück in Menü.");
        showMenu();
    }
    
    private void editError (String ex) {
        System.out.println(ex);
        System.out.println("Mögliche Ursache: Stelle exitstiert nicht oder keine Zahl angegeben! Möchtest du es erneut probieren? [y/n]");
        String e = s.nextLine();
        if (e.equals("y")) {
            editVoc();
        } else if (e.equals("n")){
            showMenu();
        } else {
            System.out.println("ungültige Angabe, bitte versuche es erneut!");
            editError(ex);
        }
    }
}

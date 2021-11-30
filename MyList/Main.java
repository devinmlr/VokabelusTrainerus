
public class Main{
    
    
    Main(){
        MyList list=new MyList();
        list.append("Schaf");
        list.append("Kuh");
        list.append("Schwein");
        list.toFirst();
        list.next();
        list.insert("Rind");
        
        //Finde Schwein und entferne
        list.toFirst();
        while (list.hasAccess()&&list.getContent()!="Schwein"){
            list.next();
        }
        list.remove();
    
        list.toFirst();
        while (list.hasAccess()){
            System.out.println(list.getContent());
            list.next();
        }
    }

    public static void main(String[] a)
    {
        new Main();
    }
}
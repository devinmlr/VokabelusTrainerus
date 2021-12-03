public class MyList{

    private ListNode first;
    private ListNode current;
    private ListNode last;
    private String mode;

    class ListNode{

        private ListNode nextNode;
        private Object contentObj;

        ListNode(Object contentObj){
            nextNode=null;
            this.contentObj=contentObj;
        }

        public void setNext(ListNode nextNode){
            this.nextNode=nextNode;
        }

        public ListNode getNext(){
            return nextNode;
        }

        public void setContentObj(Object contentObj){
            this.contentObj=contentObj;
        }

        public Object getContentObj(){
            return contentObj;
        }

    }

    MyList(String mode){
        this.mode = mode;
        first=null;
        last=null;
        current=null;
    }

    public boolean isEmpty(){
        return first==null;
    }

    public boolean hasAccess(){
        return current!=null;
    }

    private ListNode getPrevious(){
        if (current==first){
            //current zeigt auf erstes Element oder auf null
            return null;
        } else {
            //current zeigt nicht auf erstes Element
            if (current!=null){
                //current zeigt nicht auf null
                ListNode p=first;
                while (p!=null && p.getNext()!=current){
                    p=p.getNext();
                }
                return p;       
            } else {
                return null;
            }
        }
    }

    public void next(){
        if (current!=null){
            current=current.getNext();
        }
    }
    
    public ListNode getNext(){
        return current.getNext();
    }

    public void toFirst(){
        current=first;
    }

    public void toLast(){
        current=last;
    }

    public Object getContent(){
        if (current!=null){
            return current.getContentObj();
        } else {
            return null;
        }
    }

    public void setContent(Object contentObj){
        if (current!=null){
            current.setContentObj(contentObj);
        }        
    }

    public void append(Object contentObj){
        ListNode newNode=new ListNode(contentObj);
        if (last!=null){
            last.setNext(newNode);
            last=newNode;
        } else {
            last=newNode;
            first=newNode;
            current=first;
        }
    }

    public void remove(){
        if (current!=null){
            if (current==first){
                first=first.getNext();
                current=first;
                if (first==null){
                    last=null;
                }
            } else if (current==last){
                //current ist nicht first aber last, also gibt es mindestens zwei Listenobjekte
                ListNode previous=getPrevious();
                previous.setNext(null);
                last=previous;
                current=last;
            } else {
                //current ist weder first noch last, also gibt es vorgänger UND nachfolger
                ListNode previous=getPrevious();
                previous.setNext(current.getNext());    
                current=previous.getNext();
            }
            
        }
    }

    public void insert(Object contentObj){
        ListNode newNode=new ListNode(contentObj);
        ListNode previous=getPrevious();
        newNode.setNext(previous.getNext());
        previous.setNext(newNode);
    }
    
    public MyList getCopy(){
        toFirst();
        MyList copy=new MyList(mode);
        while (hasAccess()){
            copy.append(getContent());
            next();
        }
        return copy;
    }
    
    public int getLength () {
        ListNode current = this.current;
        int length = 0;
        toFirst();
        do {
            length++;
            if (getNext() != null) {
                next();                
            }
        } while (getNext() != null);
        this.current = current;
        return length;
    }
    
    public void printList () {
        ListNode current = this.current;
        toFirst();
        next();
        int counter = 1;
        do {
            if (counter == 1 && this.getContent() == null) {
                System.out.println("Die Liste ist leider noch leer!");
                break;
            } else if (this.getContent() == null) {
                break;
            }
            String[] voc = new String[2];
            voc = (String[])getContent();
            System.out.println(counter + ". " + voc[0] + " : " + voc[1]);
            counter++;
        } while (getNext() != null);
        this.current = current;
    }
    
    public void edit (int stelle, int i, String correct) {
        String[] voc  = new String[2];
        voc = getElement(stelle);
        voc[i] = correct;
        setContent(voc);
    }
    
    public String[] getElement(int stelle) {
        toFirst();
        for (int i = 0; i<stelle; i++) {
            next();
        }
        
        String[] voc = new String[2];
        
        voc = (String[])getContent();
        
        return voc;
    }
        
    public void search(String sText) {
        ListNode current = this.current;
        toFirst();
        next();
        Object result = new Object();
        String[] resultText = new String[2];
        int counter = 1;
        
        if (mode == "voc") {
            do {
                if (getContent() == null) {
                    break;
                }
                if (((String[])getContent())[0] == sText || ((String[])getContent())[1] == sText) {
                    result = getContent();
                    break;
                }else if (getNext() != null) {
                    next();
                }
                counter++;
            } while (getNext() != null);
            if (getContent() != null) {
                resultText = (String[])getContent();
                System.out.println("Die Vokabel '" + resultText[0] + "' : '" + resultText[1] + "' steht an " + counter + ". Stelle.");
            } else {
                System.out.println("Die Vokabel existiert leider nicht in deiner Liste.");
            }
        }
        
        this.current = current;
    }
}

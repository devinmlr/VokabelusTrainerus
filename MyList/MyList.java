public class MyList{

    private ListNode first;
    private ListNode current;
    private ListNode last;

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

    MyList(){
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
        MyList copy=new MyList();
        while (hasAccess()){
            copy.append(getContent());
            next();
        }
        return copy;
    }

}

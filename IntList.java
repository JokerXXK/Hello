/**Naked Linked Lists*/
public class IntList {

    public  int first;
    public IntList rest;

     public IntList(int f, IntList r){
        first = f;
        rest = r;
    }

    public int size(){
        if(rest == null){
            return 1;
        }else{
            return 1 + rest.size();
        }
    }
    
    public int iterativeSize(){
        IntList p = this;
        int totalSize = 0;
        while(p != null){
            totalSize += 1;
            p = p.rest;
        }
        return totalSize;
    }
    
    public int get(int i){
        if(i == 0){
            return first;
        }else{
            return rest.get(i - 1);
        }
    }

    public int iterativeGet(int i){
        IntList p = this;
        while(i > 0){
            p = p.rest;
            i -= 1;
        }
        return p.first;
    }
    /** Returns an IntList identical to L, but with all values incremented by x */
    /** Values in L cannnot change*/
    public static IntList incrList(IntList L, int x){
        if(L == null){
            return null;
        }else{
            return new IntList(L.first + x, incrList(L.rest, x));
        }
    }

    public static IntList IterativeIncrList(IntList L, int x){
        IntList p = L;
        IntList newList = null;
        IntList tail = null;

        while(p != null){
            IntList newNode = new IntList(p.first + x, null);
            if(newList == null){
                newList = newNode;
                tail = newList;
            }else{
                tail.rest = newNode;
                tail = tail.rest;
            }
            p = p.rest;
        }
        return newList;
    }

    /**Returns an IntList identical to L, but with all values incremented by x*/
    /**Not allowed to use "new"(to save memory)*/
    public static IntList dincrList(IntList L, int x){
        IntList p = L;
        while(p != null){
            p.first += x;
            p = p.rest;
        }
        return L;
    }

    public static IntList dincrList2(IntList L, int x) {
        if (L == null) {
            return null;
        }
        
        L.first += x;
        
        dincrList(L.rest, x);
        
        return L;
    }

    public static void main(String[] args) {
        IntList L = new IntList(15, null);
        L = new IntList(10, L);
        L = new IntList(5, L);
    }
}

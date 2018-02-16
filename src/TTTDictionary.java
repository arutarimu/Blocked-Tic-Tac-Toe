// This is a dictionary class used for Blocked Tic-Tac-Toe games
// Uses linearnode.java and TTTRecord.java
// Has two exceptions : DuplicatedKeyException and InexistentKeyException

public class TTTDictionary implements TTTDictionaryADT {
    private linearnode<TTTRecord>[] TTTTable;
    private int dictSize;
    private int count;

    public TTTDictionary(int size) { // constructor for TTTDictionary
        TTTTable = new linearnode[size];
        dictSize = size;
        count = 0;
        for(int i = 0;i < dictSize; i++){ // elements set to null
            TTTTable[i] = null;
        }
    }

    private int hash(String element, int tSize) { // hash function sets index using string and table size
        int index = 0;
        int hash = 17; // make the hash prime number so it becomes more unique
        for (int i = 0; i < element.length(); i++) {
            index = (hash * index + element.charAt(i)) % tSize;
        }
        return index;
    }

    public int put (TTTRecord element) throws DuplicatedKeyException { // throws exception if element already exist
        // using TTTrecord config and dictionary size to create index
        int index = hash(element.getConfiguration(), dictSize);
        if (TTTTable[index] != null) {
             // !=null, so insert the node into the linked list with same index
            linearnode<TTTRecord> pointer = TTTTable[index]; // setting up two pointers to go through a linked list
            linearnode<TTTRecord> ppointer = TTTTable[index];
            while (pointer != null) {
                if (pointer.getElement().getConfiguration().equals(element.getConfiguration())) {
                    throw new DuplicatedKeyException();
                }
                ppointer = pointer;
                pointer = pointer.getNext();
            }
            linearnode newNode = new linearnode(element);
            ppointer.setNext(newNode);
        }
        if(TTTTable[index] == null) {
            linearnode node = new linearnode(element);
            TTTTable[index] = node;
            return 0; // always return 0 because it's the first element, no collision
        }
        count++;
        return 1; // there is a collision
    }

    public void remove(String element) throws InexistentKeyException {
        int index = hash(element, dictSize); // find the index using hash function
        if (TTTTable[index] == null) { //if the element is not found, then return InexisentKeyException
            throw new InexistentKeyException();
        }
        if (TTTTable[index].getElement().getConfiguration().equals(element)) {
            if(TTTTable[index].getNext()!= null) {
                TTTTable[index] = TTTTable[index].getNext(); // set the next node as head
            }
            else { // if next node doesn't exist, set as null
                TTTTable[index] = null;
            }
        }
        else { // iterate the whole linked list to find a match
            linearnode<TTTRecord> pointer = TTTTable[index]; // same two pointer method as put
            linearnode<TTTRecord> ppointer = TTTTable[index];
            while(!pointer.getElement().getConfiguration().equals(element)) {
                ppointer = pointer;
                pointer = pointer.getNext();
            }
            ppointer.setNext(pointer.getNext());
        }
        count--;
    }

    public TTTRecord get(String element) {
        int index = hash(element, dictSize);
        if (TTTTable[index] != null) {
            linearnode<TTTRecord> pointer = TTTTable[index];
            while (pointer != null) {
                if(pointer.getElement().getConfiguration().equals(element)) { // iterating to find the element
                    return pointer.getElement(); // returns the element if found
                }
                pointer = pointer.getNext(); // reached when not found, loops once more
            }
        }
        return null; // only reached if can't find
    }

    public int numElements(){ // returns the amount of elements
        return count;
    }
}
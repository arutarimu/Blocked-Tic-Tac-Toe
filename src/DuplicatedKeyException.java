// Exception thrown when element already exists in the hash table


public class DuplicatedKeyException extends Exception {
    public  DuplicatedKeyException (){
        super("Element already found in the hash table");
    }
}
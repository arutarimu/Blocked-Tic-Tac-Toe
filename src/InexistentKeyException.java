// Exception thrown when element doesn't exist in the hash table


public class InexistentKeyException extends Exception {
    public  InexistentKeyException (){
        super("Element not found in the hash table");
    }
}
package classes;

public class IDOverflowException extends Exception{
    public IDOverflowException(){
        System.err.println("ID is not between 0 - 9999");
    }
}
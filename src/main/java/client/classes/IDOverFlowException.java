package client.classes;

public class IDOverFlowException extends Exception{
    public IDOverFlowException(){
        System.err.println("ID is not between 0 - 9999");
    }
}
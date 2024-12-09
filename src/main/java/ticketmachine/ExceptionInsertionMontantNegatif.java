package ticketmachine;

public class ExceptionInsertionMontantNegatif extends Exception{

    private static final long serialVersionUID = 1L;

    public ExceptionInsertionMontantNegatif(){
        super("Montant négatif inséré");
    }

    public ExceptionInsertionMontantNegatif(String msg){
        super(msg);
    }

}

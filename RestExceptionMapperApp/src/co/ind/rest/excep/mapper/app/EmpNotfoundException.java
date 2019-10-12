package co.ind.rest.excep.mapper.app;

public class EmpNotfoundException extends Exception {
	 
    private static final long serialVersionUID = 4351720088030656859L;
    private int errorId;
 
    public int getErrorId() {
        return errorId;
    }
 
    public EmpNotfoundException(String msg, int errorId) {
        super(msg);
        this.errorId = errorId;
    }
 
    public EmpNotfoundException(String msg, Throwable cause) {
        super(msg, cause);
    }
}
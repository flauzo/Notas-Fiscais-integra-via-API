package flauzo.cruz.notafiscalapi.shared.exception;

/**
 * @author flauzo
 */
public class ArquivoException extends RuntimeException{
    private Integer status;

    public Integer status() {
        return this.status;
    }

    public ArquivoException(String message){
        super(message);
    }

    public ArquivoException(Integer status, String message){
        super(message);
        this.status = status;
    }
}

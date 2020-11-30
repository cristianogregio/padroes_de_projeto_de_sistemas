package gov.br.sp.fatec.condominio.common.exception;

import org.springframework.http.HttpStatus;
import org.springframework.web.bind.annotation.ResponseStatus;

/**
 * @author Cristiano Gregio
 */
@ResponseStatus(value = HttpStatus.NOT_FOUND)
public class RegistroNaoEncontradoException extends RuntimeException
{

    /**
     * 
     */
    public RegistroNaoEncontradoException()
    {
        super();
        // TODO Auto-generated constructor stub
    }

    /**
     * @param pMessage
     * @param pCause
     * @param pEnableSuppression
     * @param pWritableStackTrace
     */
    public RegistroNaoEncontradoException(String pMessage, Throwable pCause, boolean pEnableSuppression, boolean pWritableStackTrace)
    {
        super(pMessage, pCause, pEnableSuppression, pWritableStackTrace);
        // TODO Auto-generated constructor stub
    }

    /**
     * @param pMessage
     * @param pCause
     */
    public RegistroNaoEncontradoException(String pMessage, Throwable pCause)
    {
        super(pMessage, pCause);
        // TODO Auto-generated constructor stub
    }

    /**
     * @param pMessage
     */
    public RegistroNaoEncontradoException(String pMessage)
    {
        super(pMessage);
        // TODO Auto-generated constructor stub
    }

    /**
     * @param pCause
     */
    public RegistroNaoEncontradoException(Throwable pCause)
    {
        super(pCause);
        // TODO Auto-generated constructor stub
    }
   
}

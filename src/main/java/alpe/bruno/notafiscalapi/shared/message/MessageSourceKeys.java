package flauzo.cruz.notafiscalapi.shared.message;

public class MessageSourceKeys {

    // Exceções de HTTP.
    public static final String REQUEST_METHOD = "{key.invalid.method.request}";
    public static final String MESSAGE_NOT_READABLE = "{key.message.not.readable}";
    public static final String ILLEGAL_ARGUMENT = "{key.illegal.argumentes}";
    public static final String NULL_POINTER = "{key.null.pointer}";
    public static final String REGISTRO_DUPLICADO = "{key.registro.duplicado}";

    // Exceções de Feign.
    public static final String SERVICE_UNAVAILABLE = "{key.service.unavailable}";
    public static final String SERVICE_ERRO = "{key.service.erro}";
    public static final String ENTITY_NOT_FOUND = "{key.entity.not.found}";
}

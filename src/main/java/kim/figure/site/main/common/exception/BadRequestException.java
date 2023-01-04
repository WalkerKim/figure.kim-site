package kim.figure.site.main.common.exception;

/**
 * author         : walker
 * date           : 2022. 12. 24.
 * description    :
 */
public class BadRequestException  extends RuntimeException {
    public BadRequestException(){
        super();
    }
    public BadRequestException(String message){
        super(message);
    }
}

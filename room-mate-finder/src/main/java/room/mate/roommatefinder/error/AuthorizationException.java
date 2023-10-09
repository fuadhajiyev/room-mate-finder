package room.mate.roommatefinder.error;

public class AuthorizationException  extends  RuntimeException{
        public AuthorizationException(){
            super("Forbidden");
        }
}

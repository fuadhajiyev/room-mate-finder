package room.mate.roommatefinder.error;

import org.springframework.context.i18n.LocaleContextHolder;
import room.mate.roommatefinder.shared.Messages;

public class NotFoundException extends RuntimeException{

    public NotFoundException(long id) {
        super(Messages.getMessageForLocale("dostudent.user.not.found", LocaleContextHolder.getLocale(),id));
    }
}


package pl.edu.wszib.dngmp.session;

import lombok.Data;
import org.springframework.stereotype.Component;
import org.springframework.web.context.annotation.SessionScope;
import pl.edu.wszib.dngmp.model.User;

@Component
@SessionScope
@Data
public class SessionObject {
    private User user = null;

    public boolean isLogged() {
        return this.user != null;
    }
}

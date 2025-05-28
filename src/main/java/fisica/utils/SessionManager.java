package fisica.utils;

import fisica.model.User;

public class SessionManager {


    private static SessionManager instance;
    private User usuarioLoggeado;

    private SessionManager() {
    }

    public static SessionManager getInstance() {
        if (instance == null) {
            instance = new SessionManager();
        }
        return instance;
    }

    public User getUsuarioLoggeado() {
        return usuarioLoggeado;
    }

    public void setUsuarioLoggeado(User usuarioLoggeado) {
        this.usuarioLoggeado = usuarioLoggeado;
    }

    public boolean sesionActiva() {
        return this.usuarioLoggeado != null;
    }
}

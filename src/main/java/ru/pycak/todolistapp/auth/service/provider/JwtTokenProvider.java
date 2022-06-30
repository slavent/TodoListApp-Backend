package ru.pycak.todolistapp.auth.service.provider;

import java.util.List;

public interface JwtTokenProvider {

    /**
     * Create new access JWT token for given username and roles
     *
     * @param username User unique login (email in this app)
     * @param authorities User roles (e.g. user, admin)
     * @return new access JWT token
     */
    public String getAccessToken(String username, List<String> authorities);

    /**
     * Create new JWT token for given username
     *
     * @param username User unique login (email in this app)
     * @return new access JWT token
     */
    public String getRefreshToken(String username);
}

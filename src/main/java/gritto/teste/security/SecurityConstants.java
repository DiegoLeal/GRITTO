package gritto.teste.security;

public class SecurityConstants {
    public static final String SIGN_UP_URLS = "/auth/**";
    public static final String TOKEN_PREFIX= "Bearer ";
    public static final String HEADER_STRING = "Authorization";
    public static final long EXPIRATION_TIME = 860_000_000; // EXPIRATION_TIME = 10 dias
}

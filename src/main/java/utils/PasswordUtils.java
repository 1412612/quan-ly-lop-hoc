package utils;

import org.springframework.security.crypto.bcrypt.BCryptPasswordEncoder;
import org.springframework.security.crypto.password.PasswordEncoder;

public class PasswordUtils {
    public static PasswordEncoder passwordEncoder =new BCryptPasswordEncoder();
}

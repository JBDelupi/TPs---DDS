package Service.Validador;

import java.security.MessageDigest;

public class Encriptador {
    private static Encriptador instancia;

    public String encriptarMD5(String input) {
        try {
            MessageDigest md = MessageDigest.getInstance("MD5");
            byte[] digest = md.digest(input.getBytes());
            StringBuilder hexString = new StringBuilder();
            for (byte b : digest) {
                hexString.append(String.format("%02x", b));
            }
            return hexString.toString();
        } catch (Exception e) {
            throw new RuntimeException("Error al encriptar la contraseña", e);
        }
    }

    public static Encriptador getInstancia() {
                if (instancia == null) {
                    instancia = new Encriptador();
                }
        return instancia;
    }

}

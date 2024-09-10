package Models.Repository;

public class PseudoBaseDatosAdmins {

    public static class AdminDePlataforma {
        private String nombreUsuario;
        private String contrasenia;

        public AdminDePlataforma(String nombreUsuario, String contrasenia) {
            this.nombreUsuario = nombreUsuario;
            this.contrasenia = contrasenia;
        }

        public String getNombreUsuario() {
            return nombreUsuario;
        }

        public String getContrasenia() {
            return contrasenia;
        }
    }

    private AdminDePlataforma[] admins = {
            new AdminDePlataforma("admin1", "1"),
            new AdminDePlataforma("admin2", "2"),
            new AdminDePlataforma("admin3", "3")
    };

    public AdminDePlataforma obtenerAdmin(String nombreUsuario, String contrasenia) {
        for (AdminDePlataforma admin : admins) {
            if (admin.getNombreUsuario().equals(nombreUsuario) && admin.getContrasenia().equals(contrasenia)) {
                return admin;
            }
        }
        return null;
    }

    public static void main(String[] args) {
        PseudoBaseDatosAdmins baseDatos = new PseudoBaseDatosAdmins();
        AdminDePlataforma admin = baseDatos.obtenerAdmin("admin1", "password1");

        if (admin != null) {
            System.out.println("Admin encontrado: " + admin.getNombreUsuario());
        } else {
            System.out.println("Admin no encontrado");
        }
    }
}

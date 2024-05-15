import Entity.Movimiento;
import Entity.Pokemon;
import Service.ServicioPokeAPI;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;

public class Main {
    public static void main(String[] args) throws IOException {
        BufferedReader br = new BufferedReader(new InputStreamReader(System.in));

        while (true) {
            System.out.println("BIENVENIDO A APIPOKEMON:");
            System.out.println("1. Buscar un pokemon");
            System.out.println("2. Buscar un movimiento");
            System.out.println("3. Salir");
            System.out.print("Seleccione una opcion: ");

            int opcion = Integer.parseInt(br.readLine());

            switch (opcion) {
                case 1:
                    seleccionUno();
                    break;
                case 2:
                    seleccionDos();
                    break;
                case 3:
                    System.out.println("Saliendo del programa...");
                    return;
                default:
                    System.out.println("Opcion invalida. Por favor, seleccione una opción valida.");
            }
        }
    }

    public static void seleccionUno() throws IOException {
        BufferedReader br = new BufferedReader(new InputStreamReader(System.in));

        System.out.print("Ingrese el nombre del pokemon: ");
        String nombre = br.readLine();

        Pokemon pokemon = ServicioPokeAPI.getInstance().pokemon(nombre);

        System.out.println("*************************************");
        System.out.println("Nombre del pokemon -> " + pokemon.getName());
        System.out.println("Foto del pokemon -> " + pokemon.getSprites().getFront_default() );
        System.out.println("---MOVIMIENTOS ESPECIALES---");
        for(int i = 0; i<10; i++){
            System.out.println("Movimiento (" + (i+1) + ") : " + pokemon.getMoves().get(i).getMove().getName());
        }
        System.out.println("----------------");

    }

    public static void seleccionDos() throws IOException {
        BufferedReader br = new BufferedReader(new InputStreamReader(System.in));

        System.out.print("Ingrese el nombre del movimiento: ");
        String nombre = br.readLine();

        Movimiento movimiento = ServicioPokeAPI.getInstance().movimiento(nombre);

        System.out.println("__________________________________");
        System.out.println("Nombre del movimiento -> " + movimiento.getName());
        System.out.println("---POKEMONES QUE LO UTILIZAN---");
        for(int i = 0; i<movimiento.getLearned_by_pokemon().size(); i++){
            System.out.println("POKEMON (" + (i+1) + ") : " + movimiento.getLearned_by_pokemon().get(i).getName());
        }
        System.out.println("----------------");
    }
}


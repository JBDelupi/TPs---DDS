package Entity;

import DTO.MovimientosDTO;
import lombok.Getter;

import java.util.List;

@Getter
public class Pokemon {
    private String name;
    private List<Movimientos> moves;
    private Sprites sprites;
    @Getter
    public static class Sprites {
        private String front_default;
    }
    @Getter
    public static class Movimientos {
        private MovimientosDTO move;
    }

}

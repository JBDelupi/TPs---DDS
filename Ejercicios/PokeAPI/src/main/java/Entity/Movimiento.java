package Entity;


import DTO.PokemonDTO;
import lombok.Getter;

import java.util.List;

@Getter
public class Movimiento {
    private String name;
    private List<PokemonDTO> learned_by_pokemon;

}

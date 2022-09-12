package dto;

import java.io.Serializable;
import java.util.Date;
import lombok.Data;

@Data
public class Asignacion_UsuarioDTO implements Serializable {

    private Integer IDASIUSU;
    private Integer IDARE;
    private String Area;
    private Integer IDUSU;
    private String Usuario;
    private Date FECASIUSU;
    private String ESTASIUSU;
}

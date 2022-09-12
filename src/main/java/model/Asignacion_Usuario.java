package model;

import java.io.Serializable;
import java.util.Date;
import lombok.Data;

@Data
public class Asignacion_Usuario implements Serializable{
    
    private Integer IDASIUSU;
    private Integer IDARE;
    private Integer IDUSU;
    private Date FECASIUSU;
    private String ESTASIUSU;
    
}

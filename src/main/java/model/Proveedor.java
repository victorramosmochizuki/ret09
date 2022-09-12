package model;

import java.io.Serializable;
import lombok.Data;

@Data
public class Proveedor implements Serializable {

    private Integer IDPRO;
    private String RUCPRO;
    private String RAZSOC;
    private String SUCPRO;
    private String DIRPRO;
    private String CORPRO;
    private String TELPRO;
    private String CELPRO;
    private String ESTPRO;
}

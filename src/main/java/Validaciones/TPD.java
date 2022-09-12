package Validaciones;

import dao.Conexion;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import javax.faces.application.FacesMessage;
import javax.faces.component.UIComponent;
import javax.faces.context.FacesContext;
import javax.faces.validator.FacesValidator;
import javax.faces.validator.Validator;
import javax.faces.validator.ValidatorException;

@FacesValidator(value = "TelefonoDuplicado")
public class TPD extends Conexion implements Validator {

    @Override
    public void validate(FacesContext context, UIComponent component, Object value) throws ValidatorException {
        if (AreaDuplicada(String.valueOf(value)) == true) {
            FacesMessage msg = new FacesMessage("Telefono Duplicado");
            throw new ValidatorException(msg);
        }
    }

        public static boolean AreaDuplicada(String codigo) {
        try {
            PreparedStatement ps1 = TPD.conectar().prepareStatement("SELECT TELPRO FROM PROVEEDOR WHERE PROVEEDOR.ESTPRO = 'A' AND TELPRO='" + codigo + "'");           
            ResultSet rs = ps1.executeQuery();
            while (rs.next()) {
                return true;
            }
            rs.close();
        } catch (Exception e) {
            e.printStackTrace();
            System.out.println("Error Ruc Duplicado" + e.getMessage());
        }
        return false;
    }
}

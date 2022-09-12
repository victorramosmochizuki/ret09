package controlador;

import dao.Asignacion_UsuarioImpl;
import dto.Asignacion_UsuarioDTO;
import javax.inject.Named;
import javax.enterprise.context.SessionScoped;
import java.io.Serializable;
import java.util.ArrayList;
import java.util.List;
import javax.annotation.PostConstruct;
import javax.faces.application.FacesMessage;
import javax.faces.context.FacesContext;
import lombok.Data;
import model.Asignacion_Usuario;
import services.Asignacion_UsuarioServiceImpl;

@Data
@Named(value = "asignacion_UsuarioC")
@SessionScoped
public class Asignacion_UsuarioC implements Serializable {

    Asignacion_UsuarioServiceImpl asignacion_UsuarioServiceImpl = new Asignacion_UsuarioServiceImpl();
    private List<Asignacion_UsuarioDTO> listAsignacionDTO = new ArrayList<>();
    private Asignacion_UsuarioDTO asignacionDTO = new Asignacion_UsuarioDTO();
    private Asignacion_UsuarioDTO seleccionado = new Asignacion_UsuarioDTO();
    private List<Asignacion_UsuarioDTO> listado = new ArrayList<>();
    private Asignacion_UsuarioImpl asignacion = new Asignacion_UsuarioImpl();

    public Asignacion_UsuarioC() {
    }

    @PostConstruct
    public void onInit() {
        try {
            listar();
        } catch (Exception e) {
            e.printStackTrace();
        }
    }

    public void registrar() {
        try {
            asignacion_UsuarioServiceImpl.crear(asignacionDTO);
            FacesContext.getCurrentInstance().addMessage(null, new FacesMessage(FacesMessage.SEVERITY_INFO, "Registro", "Registrado con exito"));
            listar();
            limpiar();
        } catch (Exception e) {
            FacesContext.getCurrentInstance().addMessage(null, new FacesMessage(FacesMessage.SEVERITY_INFO, "Registro", "Registrado con exito"));
            System.out.println("Error en registrarC " + e.getMessage());
        }
    }

    public void modificar() {
        try {
            asignacion_UsuarioServiceImpl.modificar(seleccionado);
            FacesContext.getCurrentInstance().addMessage(null, new FacesMessage(FacesMessage.SEVERITY_INFO, "Modificacion", "Modificado con exito"));
            listar();
            limpiar();
        } catch (Exception e) {
            System.out.println("Error en modificarC " + e.getMessage());
        }
    }

    public void modificarEstado(Integer IDASIUSU) {
        try {
            asignacion_UsuarioServiceImpl.modificarEstado(IDASIUSU);
            FacesContext.getCurrentInstance().addMessage(null, new FacesMessage(FacesMessage.SEVERITY_FATAL, "Inactivar", "Inactivado con exito"));
            listar();
            limpiar();
        } catch (Exception e) {
            System.out.println("Error en modificarC " + e.getMessage());
        }
    }

    public void listar() {
        try {
            listAsignacionDTO = new ArrayList<>();
            listAsignacionDTO = asignacion_UsuarioServiceImpl.listar();
        } catch (Exception e) {

        }
    }

    public void limpiar() {
        asignacionDTO = new Asignacion_UsuarioDTO();
    }

}

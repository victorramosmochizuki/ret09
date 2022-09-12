package controlador;

import dao.ProveedorImpl;
import javax.inject.Named;
import javax.enterprise.context.SessionScoped;
import java.io.Serializable;
import java.util.ArrayList;
import java.util.List;
import javax.annotation.PostConstruct;
import javax.faces.application.FacesMessage;
import javax.faces.context.FacesContext;
import lombok.Data;
import model.Proveedor;

@Data
@Named(value = "proveedorC")
@SessionScoped
public class ProveedorC implements Serializable {

    private ProveedorImpl dao;
    private Proveedor proveedor;
    private List<Proveedor> listapro;

    public ProveedorC() {
        proveedor = new Proveedor();
        dao = new ProveedorImpl();
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
            dao.registrar(proveedor);
            FacesContext.getCurrentInstance().addMessage(null, new FacesMessage(FacesMessage.SEVERITY_INFO, "Registro", "Registrado con exito"));
            limpiar();
            listar();
        } catch (Exception e) {
            FacesContext.getCurrentInstance().addMessage(null, new FacesMessage(FacesMessage.SEVERITY_INFO, "Registro", "Registrado con exito"));
            System.out.println("Error en registrarC " + e.getMessage());
        }
    }

    public void modificar() {
        try {
            dao.modificar(proveedor);
            FacesContext.getCurrentInstance().addMessage(null, new FacesMessage(FacesMessage.SEVERITY_INFO, "Modificacion", "Modificado con exito"));
            limpiar();
            listar();
        } catch (Exception e) {
            System.out.println("Error en modificarC " + e.getMessage());
        }
    }

    public void modificarEstado(Proveedor p) {
        try {
            p.setESTPRO("I");
            dao.modificarEst(p);
            FacesContext.getCurrentInstance().addMessage(null, new FacesMessage(FacesMessage.SEVERITY_FATAL, "Inactivar", "Inactivado con exito"));
            limpiar();
            listar();
        } catch (Exception e) {
            System.out.println("Error en modificarC " + e.getMessage());
        }
    }

    public void listar() {
        try {
            listapro = new ArrayList<>();
            listapro = dao.listar();
        } catch (Exception e) {

        }
    }

    public void limpiar() {
        proveedor = new Proveedor();
    }

}

package services;

import dao.Asignacion_UsuarioImpl;
import dto.Asignacion_UsuarioDTO;
import java.util.List;
import java.util.logging.Level;
import java.util.logging.Logger;
import model.Asignacion_Usuario;

public class Asignacion_UsuarioServiceImpl implements Asignacion_UsuarioService {

    Asignacion_UsuarioImpl dao = new Asignacion_UsuarioImpl();

    @Override
    public void crear(Asignacion_UsuarioDTO asignacion_UsuarioDTO) {
        Asignacion_Usuario asi = new Asignacion_Usuario();
        asi.setIDASIUSU(asignacion_UsuarioDTO.getIDASIUSU());
        asi.setFECASIUSU(asignacion_UsuarioDTO.getFECASIUSU());
        asi.setESTASIUSU(asignacion_UsuarioDTO.getESTASIUSU());
        asi.setIDARE(asignacion_UsuarioDTO.getIDARE());
        asi.setIDUSU(asignacion_UsuarioDTO.getIDUSU());
        try {
            dao.crear(asi);
        } catch (Exception ex) {
            Logger.getLogger(UsuarioServiceImpl.class.getName()).log(Level.SEVERE, null, ex);
        }
    }

    public void modificar(Asignacion_UsuarioDTO asignacion_UsuarioDTO) {
        Asignacion_Usuario asi = new Asignacion_Usuario();
        asi.setIDASIUSU(asignacion_UsuarioDTO.getIDASIUSU());
        asi.setFECASIUSU(asignacion_UsuarioDTO.getFECASIUSU());
        asi.setESTASIUSU(asignacion_UsuarioDTO.getESTASIUSU());
        asi.setIDARE(asignacion_UsuarioDTO.getIDARE());
        asi.setIDUSU(asignacion_UsuarioDTO.getIDUSU());
        try {
            dao.modificar(asi);
        } catch (Exception ex) {
            Logger.getLogger(UsuarioServiceImpl.class.getName()).log(Level.SEVERE, null, ex);
        }
    }

    public void modificarEstado(Integer IDASIUSU) {
        Asignacion_Usuario asi = new Asignacion_Usuario();
        asi.setIDASIUSU(IDASIUSU);
        asi.setESTASIUSU("I");
        try {
            dao.modificarEst(asi);
        } catch (Exception ex) {
            Logger.getLogger(UsuarioServiceImpl.class.getName()).log(Level.SEVERE, null, ex);
        }
    }

    public List<Asignacion_UsuarioDTO> listar() throws Exception {
        return dao.listar();
    }

}

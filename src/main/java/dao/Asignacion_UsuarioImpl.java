package dao;

import dto.Asignacion_UsuarioDTO;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.Statement;
import java.util.ArrayList;
import java.util.List;
import model.Asignacion_Usuario;

public class Asignacion_UsuarioImpl extends Conexion {

    public void crear(Asignacion_Usuario asig) throws Exception {
        String sql = "Insert into asignacion_usuario"
                + "(IDARE, IDUSU, FECASIUSU, ESTASIUSU)"
                + " values(?,?,?,?)";
        try {
            PreparedStatement ps = this.conectar().prepareStatement(sql);
            ps.setInt(1, asig.getIDARE());
            ps.setInt(2, asig.getIDUSU());
            ps.setDate(3, new java.sql.Date(asig.getFECASIUSU().getTime()));
            ps.setString(4, "A");
            ps.executeUpdate();
            ps.close();
        } catch (SQLException e) {
            System.out.println("Error en insert Asignacion " + e.getMessage());
        } finally {
            this.cerrarCnx();
        }
    }

    public void modificar(Asignacion_Usuario asig) throws Exception {
        String sql = "update Asignacion_Usuario set "
                + " IDARE=?, IDUSU=?, FECASIUSU=?, ESTASIUSU=? where IDASIUSU=?";
        try {
            PreparedStatement ps = this.conectar().prepareStatement(sql);
            ps.setInt(1, asig.getIDARE());
            ps.setInt(2, asig.getIDUSU());
            ps.setDate(3, new java.sql.Date(asig.getFECASIUSU().getTime()));
            ps.setString(4, "A");
            ps.setInt(5, asig.getIDASIUSU());
            ps.executeUpdate();
            ps.close();
        } catch (SQLException e) {
            System.out.println("Error en modificar Asignacion" + e.getMessage());
        } finally {
            this.cerrarCnx();
        }
    }

    public void modificarEst(Asignacion_Usuario asig) throws Exception {
        String sql = "update Asignacion_Usuario set"
                + " ESTASIUSU = 'I' where IDASIUSU=?";
        try {
            PreparedStatement ps = this.conectar().prepareStatement(sql);
            ps.setInt(1, asig.getIDASIUSU());
            ps.executeUpdate();
            ps.close();
        } catch (Exception e) {
            e.printStackTrace();
            System.out.println("Error en Modificar Asignacion" + e.getMessage());
        } finally {
            this.cerrarCnx();
        }
    }

    public List<Asignacion_UsuarioDTO> listar() throws Exception {
        List<Asignacion_UsuarioDTO> listado = new ArrayList<>();
        Asignacion_UsuarioDTO asig;
        String sql = "SELECT ASIGNACION_USUARIO.IDASIUSU, ASIGNACION_USUARIO.ESTASIUSU, ASIGNACION_USUARIO.FECASIUSU, AREA.IDARE, AREA.NOMARE, USUARIO.IDUSU, USUARIO.NOMUSU from ASIGNACION_USUARIO\n"
                + "INNER JOIN USUARIO ON USUARIO.IDUSU = ASIGNACION_USUARIO.IDUSU \n"
                + "INNER JOIN AREA ON AREA.IDARE = ASIGNACION_USUARIO.IDARE WHERE ASIGNACION_USUARIO.ESTASIUSU = 'A'";
        try {
            conectar();
            Statement st = this.conectar().createStatement();
            ResultSet rs = st.executeQuery(sql);
            while (rs.next()) {
                asig = new Asignacion_UsuarioDTO();
                asig.setIDASIUSU(rs.getInt("IDASIUSU"));
                asig.setFECASIUSU(rs.getDate("FECASIUSU"));
                asig.setESTASIUSU(rs.getString("ESTASIUSU"));
                asig.setIDUSU(rs.getInt("IDUSU"));
                asig.setUsuario(rs.getString("NOMUSU"));
                asig.setIDARE(rs.getInt("IDARE"));
                asig.setArea(rs.getString("NOMARE"));
                listado.add(asig);
            }
            rs.close();
            st.close();
        } catch (Exception e) {
            System.out.println("Error en la lista Asignacion" + e.getMessage());
        } finally {
            this.cerrarCnx();
        }
        return listado;
    }

}

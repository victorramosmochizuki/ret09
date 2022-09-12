package dao;

import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.Statement;
import java.util.ArrayList;
import java.util.List;
import model.Proveedor;

public class ProveedorImpl extends Conexion implements ICRUD<Proveedor> {

    @Override
    public void registrar(Proveedor pro) throws Exception {
        String sql = "insert into PROVEEDOR"
                + "(RUCPRO, RAZSOC, SUCPRO, DIRPRO, CORPRO, TELPRO, CELPRO, ESTPRO) values (?,?,?,?,?,?,?,?)";
        try {
            PreparedStatement ps = this.conectar().prepareStatement(sql);
            ps.setString(1, pro.getRUCPRO());
            ps.setString(2, pro.getRAZSOC());
            ps.setString(3, pro.getSUCPRO());
            ps.setString(4, pro.getDIRPRO());
            ps.setString(5, pro.getCORPRO());
            ps.setString(6, pro.getTELPRO());
            ps.setString(7, pro.getCELPRO());
            ps.setString(8, "A");
            ps.executeUpdate();
            ps.close();
        } catch (Exception e) {
            e.printStackTrace();
            System.out.println("Error en registrar Proveedor" + e.getMessage());
        } finally {
            this.cerrarCnx();
        }
    }

    @Override
    public void modificar(Proveedor pro) throws Exception {
        String sql = "update PROVEEDOR set"
                + " RUCPRO=?, RAZSOC=?, SUCPRO=?, DIRPRO=?, CORPRO=?, TELPRO=?, CELPRO=?, ESTPRO=? where IDPRO=?";
        try {
            PreparedStatement ps = this.conectar().prepareStatement(sql);
            ps.setString(1, pro.getRUCPRO());
            ps.setString(2, pro.getRAZSOC());
            ps.setString(3, pro.getSUCPRO());
            ps.setString(4, pro.getDIRPRO());
            ps.setString(5, pro.getCORPRO());
            ps.setString(6, pro.getTELPRO());
            ps.setString(7, pro.getCELPRO());
            ps.setString(8, "A");
            ps.setInt(9, pro.getIDPRO());
            ps.executeUpdate();
            ps.close();
        } catch (Exception e) {
            e.printStackTrace();
            System.out.println("Error en Modificar Proveedor" + e.getMessage());
        } finally {
            this.cerrarCnx();
        }
    }

    @Override
    public void modificarEst(Proveedor pro) throws Exception {
        String sql = "update PROVEEDOR set"
                + " ESTPRO = 'I' where IDPRO=?";
        try {
            PreparedStatement ps = this.conectar().prepareStatement(sql);
            ps.setInt(1, pro.getIDPRO());
            ps.executeUpdate();
            ps.close();
        } catch (Exception e) {
            e.printStackTrace();
            System.out.println("Error en Modificar Proveedor" + e.getMessage());
        } finally {
            this.cerrarCnx();
        }
    }

    @Override
    public void eliminar(Proveedor gen) throws Exception {
        throw new UnsupportedOperationException("Not supported yet."); //To change body of generated methods, choose Tools | Templates.
    }

    @Override
    public List<Proveedor> listar() throws Exception {
        List<Proveedor> listado = new ArrayList<>();
        Proveedor pro;
        String sql = "SELECT * FROM PROVEEDOR WHERE PROVEEDOR.ESTPRO = 'A' ORDER BY RAZSOC";
        try {
            Statement st = this.conectar().createStatement();
            ResultSet rs = st.executeQuery(sql);
            while (rs.next()) {
                pro = new Proveedor();
                pro.setIDPRO(rs.getInt("IDPRO"));
                pro.setRUCPRO(rs.getString("RUCPRO"));
                pro.setRAZSOC(rs.getString("RAZSOC"));
                pro.setSUCPRO(rs.getString("SUCPRO"));
                pro.setDIRPRO(rs.getString("DIRPRO"));
                pro.setCORPRO(rs.getString("CORPRO"));
                pro.setTELPRO(rs.getString("TELPRO"));
                pro.setCELPRO(rs.getString("CELPRO"));
                pro.setESTPRO(rs.getString("ESTPRO"));
                listado.add(pro);
            }
            rs.close();
            st.close();
        } catch (Exception e) {
            System.out.println("Error en la lista Proveedor" + e.getMessage());
        } finally {
            this.cerrarCnx();
        }
        return listado;
    }
}

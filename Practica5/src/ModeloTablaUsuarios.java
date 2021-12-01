
import javax.swing.table.AbstractTableModel;

/**
 *
 * @author diana
 */
public class ModeloTablaUsuarios extends AbstractTableModel {

    private java.util.List<Usuario> usuario;

    public ModeloTablaUsuarios() {
        this.usuario = new java.util.ArrayList<>();
    }

    @Override
    public int getColumnCount() {
        return 1;
    }

    @Override
    public int getRowCount() {
        return usuario.size();
    }

    @Override
    public String getColumnName(int col) {
        String nombre = "";

        switch (col) {
            case 0:
                nombre = "Nombre";
                break;
        }
        return nombre;
    }

    @Override
    public Class getColumnClass(int col) {
        Class clase = null;

        switch (col) {
            case 0:
                clase = java.lang.String.class;
                break;
        }
        return clase;
    }

    @Override
    public boolean isCellEditable(int row, int col) {
        return false;
    }

    @Override
    public Object getValueAt(int row, int col) {
        Object resultado = null;
        if (row < usuario.size()) {
            switch (col) {
                case 0:

                    resultado = usuario.get(row).getNombre();
                    break;
            }
        }
        return resultado;
    }

    public void setFilas(java.util.List<Usuario> avisos) {
        this.usuario.clear();
        for (Usuario aux : avisos) {
            this.usuario.add(aux);
        }
        fireTableDataChanged();
    }

    public void borrarFila(int indice) {
        this.usuario.remove(indice);
        fireTableRowsDeleted(indice, indice);
    }

    public void anadirFila(Usuario aviso) {
        this.usuario.add(aviso);
        fireTableRowsInserted(this.usuario.size() - 1, this.usuario.size() - 1);
    }

}

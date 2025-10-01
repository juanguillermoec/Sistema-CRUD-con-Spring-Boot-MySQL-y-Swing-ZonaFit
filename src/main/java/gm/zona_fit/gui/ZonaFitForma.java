package gm.zona_fit.gui;

import gm.zona_fit.modelo.Cliente;
import gm.zona_fit.servicio.ClienteServicio;
import gm.zona_fit.servicio.IClienteServicio;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;

import javax.swing.*;
import javax.swing.table.DefaultTableModel;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.awt.event.MouseAdapter;
import java.awt.event.MouseEvent;
import java.time.temporal.ValueRange;

@Component
public class ZonaFitForma extends JFrame {
    private JPanel panelPrincipal;
    private JTable clientesTabla;
    private JTextField nombreTexto;
    private JTextField apellidoTexto;
    private JTextField membresiaTexto;
    private JButton guardarButton;
    private JButton eliminarButton;
    private JButton limpiarButton;
    private DefaultTableModel modeloTablaPorDefecto;
    private Integer idCliente;

    IClienteServicio clienteServicio;

    @Autowired
    public ZonaFitForma(IClienteServicio clienteServicio){
        this.clienteServicio = clienteServicio;
        iniciarForma();
        guardarButton.addActionListener(e -> {
            guardarCliente();
        });
        clientesTabla.addMouseListener(new MouseAdapter() {
            @Override
            public void mouseClicked(MouseEvent e) {
                super.mouseClicked(e);
                cargarClienteSeleccionado();
            }
        });
        eliminarButton.addActionListener(e -> {
            eliminar();
        });
        limpiarButton.addActionListener(e -> {
            limpiarFormulario();
        });
    }


    private void iniciarForma(){
        setContentPane(panelPrincipal);
        setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
        setSize(900,700);
        setLocationRelativeTo(null);
    }

    private void createUIComponents() {
        // TODO: place custom component creation code here
        this.modeloTablaPorDefecto = new DefaultTableModel(0,4){
            //este bloque se usa para evitar la edicion directamente en las celdas
            @Override
            public boolean isCellEditable(int row,int column){
                return false;
            }
        };
        String[] cabeceros = {"Id","Nombre","Apellido","Membresia"};
        this.modeloTablaPorDefecto.setColumnIdentifiers(cabeceros);
        this.clientesTabla = new JTable(modeloTablaPorDefecto);
        //restringimos la edicion de la tabla a un solo registro
        this.clientesTabla.setSelectionMode(ListSelectionModel.SINGLE_SELECTION);
        listarClientes();
    }

    private void listarClientes(){
        this.modeloTablaPorDefecto.setRowCount(0);
        var clientes = this.clienteServicio.listarCliente();
        clientes.forEach(cliente -> {
            Object[] renglonCliente = {
                    cliente.getId(),
                    cliente.getNombre(),
                    cliente.getApellido(),
                    cliente.getMembresia()
            };
            this.modeloTablaPorDefecto.addRow(renglonCliente);
        });
    }


    private void guardarCliente(){
        if(nombreTexto.getText().equals("")){
            mostrarMensaje("proporciona un nombre");
            nombreTexto.requestFocusInWindow();
            return;
        }
        if(membresiaTexto.getText().equals("")){
            mostrarMensaje("proporciona el valor de la membresia");
            membresiaTexto.requestFocusInWindow();
            return;
        }
        //recuperamos los valores

        var nombre = nombreTexto.getText();
        var apellido= apellidoTexto.getText();
        var membresia = Integer.parseInt(membresiaTexto.getText());
        var cliente = new Cliente(this.idCliente,nombre,apellido,membresia);
        this.clienteServicio.guardarCliente(cliente);//insertar
        if (this.idCliente == null){
            mostrarMensaje("se ha agregado el nuevo cliente");
        }else {
            mostrarMensaje("se actualizo el nuevo cliente");
        }
        limpiarFormulario();
        listarClientes();
    }

    private void eliminar(){
        var renglon = clientesTabla.getSelectedRow();
        if(renglon != -1){
            var id = Integer.parseInt(clientesTabla.getModel().getValueAt(renglon,0).toString());
            this.clienteServicio.eliminarCliente(id);
            mostrarMensaje("cliente eliminado");
            limpiarFormulario();
            listarClientes();
        }else {
            mostrarMensaje("debe seleccionar un cliente");
        }
    }


    private void limpiarFormulario(){
        nombreTexto.setText("");
        apellidoTexto.setText("");
        membresiaTexto.setText("");
        this.idCliente = null;
        //deseleccionar el registro seleccionado de la tabla
        this.clientesTabla.getSelectionModel().clearSelection();
    }

    private void mostrarMensaje(String mensaje){
        JOptionPane.showMessageDialog(this,mensaje);
    }

    private  void cargarClienteSeleccionado(){
        var renglon = clientesTabla.getSelectedRow();
        if (renglon != -1){//no se selecciono nigun registro
            var id = clientesTabla.getModel().getValueAt(renglon,0).toString();
            this.idCliente = Integer.parseInt(id);

            var nombre = clientesTabla.getModel().getValueAt(renglon,1).toString();
            this.nombreTexto.setText(nombre);

            var apellido = clientesTabla.getModel().getValueAt(renglon,2).toString();
            this.apellidoTexto.setText(apellido);

            var membresia = clientesTabla.getModel().getValueAt(renglon,3).toString();
            this.membresiaTexto.setText(membresia);
        }
    }
}

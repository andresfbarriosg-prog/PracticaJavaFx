package co.edu.uniquindio.poo.empresajfx.controller;

import co.edu.uniquindio.poo.empresajfx.model.Cliente;
import co.edu.uniquindio.poo.empresajfx.model.Empresa;

import java.util.List;

public class ClienteController {
    private Empresa empresa;

    public ClienteController(Empresa empresa) {
        this.empresa = empresa;
    }
    public boolean crearCliente (Cliente cliente) {
        return empresa.crearCliente(cliente);
    }
    public boolean actualizarCliente (String cedula, String nuevoNombre,
                                      String nuevoApellido) {
        return empresa.actualizarCliente(cedula, nuevoNombre, nuevoApellido);
    }
    public boolean eliminarCliente(String cedula) {
        return empresa.eliminarCliente(cedula);
    }
    public List<Cliente> obtenerListaClientes() {
        return empresa.getListaClientes();
    }
}

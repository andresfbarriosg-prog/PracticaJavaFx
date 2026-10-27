package co.edu.uniquindio.poo.empresajfx.model;

import java.util.ArrayList;
import java.util.Collections;
import java.util.List;
import java.util.Optional;

public class Empresa {
     private final  String nombre;
     private List<Cliente> listaClientes;

    public Empresa(String nombre) {
        this.nombre = nombre;
        listaClientes=new ArrayList<>();
    }

    public String getNombre() {
        return nombre;
    }

    public List<Cliente> getListaClientes() {
        return Collections.unmodifiableList(listaClientes);
    }

    public void setListaClientes(List<Cliente> listaClientes) {
        this.listaClientes = listaClientes;
    }
    //CRUD Cliente
    public boolean crearCliente(Cliente cliente){
        Optional<Cliente> buscado = buscarCliente(cliente.getCedula());
        if(buscado.isPresent()){
            return false;
        }
        listaClientes.add(cliente);
        return true;
    }

    //Buscar
    public Optional<Cliente> buscarCliente(String cedula) {
        return listaClientes.stream().filter(cliente -> cliente.getCedula().equals(cedula)).findFirst();
    }
    public boolean actualizarCliente(String cedula, String nuevoNombre,
                                     String nuevoApellido) {
        Optional<Cliente> buscado = buscarCliente(cedula);
        if(buscado.isPresent()){
            Cliente cliente = buscado.get();
            cliente.setNombre(nuevoNombre);
            cliente.setApellido(nuevoApellido);
            return true;
        }
        return false;


    }
    public boolean eliminarCliente(String cedula){
        Optional<Cliente> buscado = buscarCliente(cedula);
        if(buscado.isPresent()){
            listaClientes.remove(buscado.get());
            return true;
        }
        return false;
    }
    // otra forma de eliminar return listaClientes.removeIf(cliente -> cliente.getCedula().equals(cedula));


    @Override
    public String toString() {
        return "Empresa{" +
                "nombre='" + nombre + '\'' +
                ", listaClientes=" + listaClientes +
                '}';
    }
}


package co.edu.uniquindio.poo.practica.model;

import java.util.ArrayList;
import java.util.Collections;
import java.util.List;
import java.util.Optional;

public class Inventario {
    private final String nombre;
   private  List<Producto> listaProductos;

    public Inventario(String nombre) {
        this.nombre = nombre;
        listaProductos= new ArrayList<>();
    }
    //crud
    //crear
    public boolean crearProducto(Producto producto){
        Optional<Producto> buscado = buscarProducto(producto.getCodigo());
        if(buscado.isPresent()){
            return false;
        }
        listaProductos.add(producto);
        return true;
    }


    //buscar
    public Optional <Producto> buscarProducto(String codigo){
        return listaProductos.stream().filter(producto -> producto.getCodigo().equals(codigo)).findFirst();
    }
    //actualizar
    public boolean actualizarProducto(String codigo, double nuevoPrecio,
                                      String nuevoNombre){
        Optional<Producto> buscado = buscarProducto(codigo);
        if(buscado.isPresent()){
            Producto producto = buscado.get();
            producto.setNombre(nuevoNombre);
            producto.setPrecio(nuevoPrecio);
            return true;
        }
        return false;
    }
    public boolean eliminarProducto(String codigo){
        Optional<Producto> buscado = buscarProducto(codigo);
        if(buscado.isPresent()){
            Producto producto = buscado.get();
            listaProductos.remove(producto);
            return true;
        }
        return false;
    }

    public String getNombre() {
        return nombre;
    }

    public List<Producto> getListaProductos() {
        return  Collections.unmodifiableList(listaProductos);
    }

    public void setListaProductos(List<Producto> listaProductos) {
        this.listaProductos = listaProductos;
    }

    @Override
    public String toString() {
        return "Inventario{" +
                "nombre='" + nombre + '\'' +
                ", listaProductos=" + listaProductos +
                '}';
    }
}

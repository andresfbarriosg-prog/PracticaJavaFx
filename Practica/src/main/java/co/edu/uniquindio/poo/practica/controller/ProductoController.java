package co.edu.uniquindio.poo.practica.controller;

import co.edu.uniquindio.poo.practica.model.Inventario;
import co.edu.uniquindio.poo.practica.model.Producto;

import java.util.List;
import java.util.Optional;

public class ProductoController {
   private  Inventario inventario;

    public ProductoController(Inventario inventario) {
        this.inventario = inventario;
    }
    public boolean crearProducto(Producto producto){
        return inventario.crearProducto(producto);

    }
    public List<Producto> obtenerProducto(){
        return inventario.getListaProductos();
    }
    public boolean actualizarProducto(String codigo, double nuevoPrecio,
                                      String nuevoNombre){
        return inventario.actualizarProducto(codigo, nuevoPrecio, nuevoNombre);
    }
    public boolean eliminarProducto(String codigo){
        return inventario.eliminarProducto( codigo);
    }
    public Optional<Producto> buscarProducto(String codigo){
        return inventario.buscarProducto(codigo);
    }
}

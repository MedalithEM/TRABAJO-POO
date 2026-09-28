package cineplanet;

import java.util.ArrayList;


public class ProductoCRUD {
    private ArrayList<Producto> productos = new ArrayList<>();


    public void registrar(Producto producto) {
        productos.add(producto);
    }


    public ArrayList<Producto> listar() {
        return productos;
    }


    public Producto buscar(int id) {

        for (Producto producto : productos) {

            if (producto.getId() == id) {
                return producto;
            }
        }

        return null;
    }


    public boolean actualizar(int id, String nombre, String categoria, double precio, int stock) {

        Producto producto = buscar(id);

        if (producto != null) {

            producto.setNombre(nombre);
            producto.setCategoria(categoria);
            producto.setPrecio(precio);
            producto.setStock(stock);

            return true;
        }

        return false;
    }


    public boolean eliminar(int id) {

        Producto producto = buscar(id);

        if (producto != null) {

            productos.remove(producto);

            return true;
        }

        return false;
    }
}

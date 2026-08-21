package pe.edu.upeu;

public class Producto {


        String codigo;
        String nombre;
        double precio;
        int stock;
        double cantidad;
        double igv;

        public Producto(String codigo,
                        String nombre, double precio,
                        int stock) {
            this.codigo = codigo;
            this.nombre = nombre;
            this.precio = precio;
            this.stock = stock;
        }
        public Producto (String codigo,
                        String nombre, double precio) {
            this(codigo, nombre, precio, 0);
        }
        //metodos
        public double getIgv() {
            if(precio<0)
                throw new IllegalArgumentException();
            return precio+0.18;
        }
}







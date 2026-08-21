package pe.edu.upeu;

import java.awt.geom.QuadCurve2D;
import java.util.ArrayList;
import java.util.List;

public class Main {
    public static void main(String[] args) {
        List<Producto> prod=new ArrayList<>();


        prod.add(new Producto("POO1","Televisor",4000,20));
        prod.add(new Producto("POO2","Parlantes",2000));

        var px=new Producto("POO3","Celular",3500);
        var cantiad=54.5;

        prod.add(px);
        for(Producto P:prod){
            System.out.println(P.nombre+"\t"+P.precio+"\t"+P.stock+"\t"+P.igv);
        }
        System.out.println(px.getClass());
        System.out.println(cantiad);
        System.out.println(cantiad instanceof double);

    }
}
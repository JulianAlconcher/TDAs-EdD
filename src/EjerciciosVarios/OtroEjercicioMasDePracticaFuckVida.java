package EjerciciosVarios;

import TDAArbol.Position;
import TDAArbol.Tree;
import Exceptions.*;

/*
 * a) En el lenguaje de programacion Java y usando las interfaces Tree<E> y PositionList<E> vistas en la teoria, 
 * escriba un procedimiento recursivo que reciba un arbol t como parametro y elimine de t a todos los nodos con exactamente un hijo. 
 * Asuma que posee el TDA arbol y el TDA lista totalmente implementados.
 * 
 * b) Justificando apropiadamente estime el orden del tiempo de ejecucién de la solucién propuesta en el inciso (a).
    Puede copiar el codigo en la caja de abajo o subir un archivo de texto.
 */
public class OtroEjercicioMasDePracticaFuckVida {
    
    public <E> void metodoPodarHojas(Tree<E> t) {
        try {
            if(cantHijos(t, t.root())!=1)
                recorrido(t,t.root());
            else
                t.removeNode(t.root());
        } catch (EmptyTreeException | InvalidPositionException e) {e.printStackTrace();
        }
    }
    
    private <E> void recorrido(Tree<E> t,Position<E> p) {                
        try {
            for(Position<E> v : t.children(p)) {
                if(cantHijos(t,v)==1)
                    t.removeNode(v);
                recorrido(t,v);    
            }
        } catch (InvalidPositionException e) {e.printStackTrace();
        }

    }
    
    private <E> int cantHijos(Tree<E> t, Position<E> p) {
        int cont = 0;
        try {
            for(@SuppressWarnings("unused") Position<E> v : t.children(p)) 
                cont++;
            
        } catch (InvalidPositionException e) {e.printStackTrace();
        }
        return cont;    
    }
}

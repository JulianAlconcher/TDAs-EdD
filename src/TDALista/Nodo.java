 package TDALista;

 public class Nodo<E> implements Position<E> {
		private E elem;
		private Nodo<E> sig;
		
		public Nodo(E elemento, Nodo<E> siguiente) {
			elem = elemento;
			sig = siguiente;
		}
		
		public Nodo(E elemento) {
			this(elemento, null);
		}
		
		public E element() { //Por implementar Position;
			return elem;
		}
		
		public void setElemento(E e) {
			elem = e;
		}
		
		public void setSiguiente(Nodo<E> e) {
			sig = e;
		}
		
		public E getElemento() { //Este esta al pedo, pero por la interfaz uso el otro
			return elem;
		}
		
		public Nodo<E> getSiguiente() {
			return sig;
		}
		
		public String toString() {
			return (" " + elem);
		}
		

	}

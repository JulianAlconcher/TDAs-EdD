package TDAMapeo;

public class Entrada<K,V> implements Entry<K,V>{
	private K clave;
	private V valor;
	
	public Entrada(K clave, V valor){
		this.clave = clave;
		this.valor = valor;
	}
	
	public K getKey(){
		return clave;
	}
	
	public V getValue(){
		return valor;
	}
	
	public void setKey(K key){
		clave = key;
	}
	
	public void setValue(V value){
		valor = value;
	}
	
	public String toString(){
		return "("+clave+","+valor+")";
	}
}

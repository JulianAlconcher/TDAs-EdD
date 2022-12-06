package TDADiccionario;

import Exceptions.InvalidKeyException;

public class testerElimarTodas {
	public static void main (String args[]) {
		Dictionary<Character,Character> ejemplo = new DiccionarioConHashAbierto2022<Character,Character>();
		try {
			ejemplo.insert('A' , '9');
			ejemplo.insert('D' , '8');
			ejemplo.insert('T' , '7');
			ejemplo.insert('A' , '6');
			ejemplo.insert('7' , '5');
			ejemplo.insert('A' , '4');
			ejemplo.insert('N' , '3');
			ejemplo.insert('A' , '2');
			ejemplo.insert('D' , '1');
			for(Entry<Character,Character> en: ejemplo.entries()) {
				System.out.println(en + " ");
			}
			System.out.println(" ");
			
			
			for(Character en: ((DiccionarioConHashAbierto2022<Character, Character>) ejemplo).eliminarTodas('A')) {
				System.out.println(en + " ");
			}
			System.out.println(" ");
			for(Entry<Character,Character> en: ejemplo.entries()) {
				System.out.println(en + " ");
			}
			
			
		} catch (InvalidKeyException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
		
	}
}

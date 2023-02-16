package TDAArbolBinario;

import static org.junit.Assert.assertEquals;
import static org.junit.Assert.assertFalse;
import static org.junit.Assert.assertTrue;

import java.util.Iterator;
import Exceptions.*;

import org.junit.After;
import org.junit.Before;
import org.junit.Test;

/**
 * Tester desarrollado con JUnit 4 para la estructura BinaryTree.
 * 
 * @author Gonzalo Ezequiel Arr� - Departamento de Ciencias e Ingenier�a de la
 *         Computaci�n - Universidad Nacional del Sur -
 *         gonzalo.arro@hotmail.com
 * 
 * @version 1.0 Agosto 2017
 *
 */
public class TreeTests {

	// OBJETOS DE PRUEBA

	BinaryTree<Object> binaryTree;
	BinaryTree<Object> leftTree, rightTree;
	Object o1, o2;

	// INICIALIZACI�N Y LIMPIEZA DE OBJETOS PARA LAS PRUEBAS

	/**
	 * Setup inicial de los objetos utilizados en los casos de prueba. (llamado
	 * antes de cada test)
	 */
	@Before
	public void setUp() {
		binaryTree = new ArbolBinario2022<Object>(); // cambiar esta l�nea para
														// probar distintas
														// implementaciones
		o1 = new Object();
		o2 = new Object();
		// para m�todo attach
		leftTree = new ArbolBinario2022<Object>();
		rightTree = new ArbolBinario2022<Object>();
	}

	/**
	 * Limpieza de los objetos utilizados en los casos de prueba. (llamado
	 * despu�s de cada test)
	 */
	@After
	public void tearDown() {
		binaryTree = null; // esto es importante ya que los objetos pueden
							// persistir durante la ejecuci�n de todos los
							// tests, dejando objetos innecesarios en memoria
							// que ya no se est�n usando
		leftTree = rightTree = null;
		o1 = o2 = null;
	}

	// TESTS

	/**
	 * Comprueba que el m�todo size inicialmente retorne 0 con un �rbol vac�o.
	 */
	@Test
	public void size_emptyTree_returnsZero() {
		assertEquals("El m�todo size no retorna 0 con un �rbol vac�o.", 0, binaryTree.size());
	}

	/**
	 * Comprueba que el m�todo isEmpty responda correctamente con un �rbol
	 * vac�o.
	 */
	@Test
	public void isEmpty_emptyTree_returnsTrue() {
		assertTrue("El m�todo isEmpty no retorna true con un �rbol vac�o.", binaryTree.isEmpty());
	}

	/**
	 * Comprueba que el m�todo isEmpty responda correctamente con un �rbol no
	 * vac�o.
	 */
	@Test
	public void isEmpty_notEmptyTree_returnsFalse() throws InvalidOperationException {
		binaryTree.createRoot(new Object());
		assertFalse("El m�todo isEmpty no retorna false con un �rbol no vac�o.", binaryTree.isEmpty());
	}

	/**
	 * Comprueba que el m�todo iterator retorna un iterador vac�o para un �rbol
	 * vac�o.
	 */
	@Test
	public void iterator_emptyTree_returnsEmptyIterator() {
		assertFalse("El m�todo iterator no retorna un iterador vac�o para un �rbol vac�o.",
				binaryTree.iterator().hasNext());
	}

	/**
	 * Comprueba que el m�todo iterator retorna un iterador con un solo elemento
	 * para un �rbol con solo la ra�z.
	 */
	@Test
	public void iterator_onlyRoot_iteratorSizeEqualsOne() throws InvalidOperationException {
		binaryTree.createRoot(new Object());
		Iterator<Object> iterator = binaryTree.iterator();
		int size = 0;
		while (iterator.hasNext()) {
			iterator.next();
			size++;
		}
		assertEquals("El m�todo iterator retorna un iterador con " + size + " elementos para un �rbol con un elemento.",
				1, size);
	}

	/**
	 * Comprueba que el m�todo positions retorna una colecci�n iterable vac�a
	 * para un �rbol vac�o.
	 */
	@Test
	public void positions_emptyTree_returnsEmptyIterable() {
		assertFalse("El m�todo positions no retorna una colecci�n iterable vac�a para un �rbol vac�o.",
				binaryTree.positions().iterator().hasNext());
	}

	/**
	 * Comprueba que el m�todo positions retorna una colecci�n iterable con un
	 * solo elemento para un �rbol con solo la ra�z.
	 */
	@Test
	public void positions_onlyRoot_iterableSizeEqualsOne() throws InvalidOperationException {
		binaryTree.createRoot(new Object());
		int size = 0;
		for (Position<Object> p : binaryTree.positions()) {
			size++;
		}
		assertEquals("El m�todo positions retorna una colecci�n iterable con " + size
				+ " elementos para un �rbol con un elemento.", 1, size);
	}

	/**
	 * Comprueba que el m�todo replace lanza la excepci�n
	 * InvalidPositionException al recibir una posici�n nula.
	 */
	@Test(expected = InvalidPositionException.class)
	public void replace_nullPosition_throwsIPE() throws InvalidOperationException, InvalidPositionException {
		binaryTree.createRoot(new Object());
		binaryTree.replace(null, new Object());
	}

	/**
	 * Comprueba que el m�todo replace retorna correctamente el elemento previo
	 * en la posici�n pasada por par�metro.
	 */
	@Test
	public void replace_validPosition_returnsOldElement()
			throws InvalidOperationException, EmptyTreeException, InvalidPositionException {
		Position<Object> root = binaryTree.createRoot(o1);
		assertEquals("El m�todo replace no retorna el elemento almacenado previamente.", o1,
				binaryTree.replace(root, new Object()));
	}

	/**
	 * Comprueba que el m�todo replace setea correctamente el nuevo elemento a
	 * la posici�n pasada por par�metro.
	 */
	@Test
	public void replace_validPosition_setsNewElement()
			throws InvalidOperationException, EmptyTreeException, InvalidPositionException {
		Position<Object> root = binaryTree.createRoot(new Object());
		binaryTree.replace(root, o1);
		assertEquals("El m�todo replace no setea el nuevo elemento correctamente.", o1, root.element());
	}

	/**
	 * Comprueba que el m�todo root lanza la excepci�n EmptyTreeException sobre
	 * un �rbol vac�o.
	 */
	@Test(expected = EmptyTreeException.class)
	public void root_emptyTree_throwsETE() throws EmptyTreeException {
		binaryTree.root();
	}

	/**
	 * Comprueba que el m�todo root retorna correctamente la posici�n de la ra�z
	 * del �rbol.
	 */
	@Test
	public void root_notEmptyTree_returnsRoot()
			throws InvalidOperationException, EmptyTreeException, InvalidPositionException {
		binaryTree.createRoot(o1);
		Position<Object> root = binaryTree.root();
		assertEquals("El m�todo root no retorna la raiz correctamente.", o1, root.element());
	}

	/**
	 * Comprueba que el m�todo parent lanza la excepci�n
	 * InvalidPositionException al recibir una posici�n nula.
	 */
	@Test(expected = InvalidPositionException.class)
	public void parent_nullPosition_throwsIPE()
			throws InvalidPositionException, InvalidOperationException, BoundaryViolationException {
		binaryTree.createRoot(new Object());
		binaryTree.parent(null);
	}

	/**
	 * Comprueba que el m�todo parent lanza la excepci�n
	 * BoundaryViolationException al recibir la ra�z del �rbol.
	 */
	@Test(expected = BoundaryViolationException.class)
	public void parent_onRoot_throwsBVE()
			throws InvalidPositionException, InvalidOperationException, BoundaryViolationException {
		Position<Object> root = binaryTree.createRoot(new Object());
		binaryTree.parent(root);
	}

	/**
	 * Comprueba que el m�todo parent retorna correctamente el padre de un nodo
	 * dado
	 */
	@Test
	public void parent_validPosition_returnsParent()
			throws InvalidPositionException, InvalidOperationException, BoundaryViolationException {
		Position<Object> parent = binaryTree.createRoot(new Object());
		binaryTree.addLeft(parent, new Object());
		assertEquals("El m�todo parent no retorna correctamente el padre de un nodo.", parent,
				binaryTree.parent(binaryTree.left(parent)));
	}

	/**
	 * Comprueba que el m�todo children lanza la excepci�n
	 * InvalidPositionException al recibir una posici�n nula.
	 */
	@Test(expected = InvalidPositionException.class)
	public void children_nullPosition_throwsIPE() throws InvalidPositionException, InvalidOperationException {
		binaryTree.createRoot(new Object());
		binaryTree.children(null);
	}

	/**
	 * Comprueba que el m�todo children retorna una colecci�n vac�a para un nodo
	 * sin hijos.
	 */
	@Test
	public void children_WithoutChildren_returnsEmptyCollection()
			throws InvalidOperationException, InvalidPositionException {
		Position<Object> positionWithoutChildren = binaryTree.createRoot(new Object());
		Iterable<Position<Object>> children = binaryTree.children(positionWithoutChildren);
		assertFalse("El m�todo children no retorna una colecci�n vac�a para un nodo sin hijos.",
				children.iterator().hasNext());
	}

	/**
	 * Comprueba que el m�todo children retorna correctamente la colecci�n de
	 * hijos para un nodo con solo hijo izquierdo.
	 */
	@Test
	public void children_onlyLeftChild_returnsCollectionWithLeftChild()
			throws InvalidOperationException, InvalidPositionException {
		Position<Object> positionWithLeftChild = binaryTree.createRoot(new Object());
		binaryTree.addLeft(positionWithLeftChild, o1);
		Iterator<Position<Object>> children = binaryTree.children(positionWithLeftChild).iterator();
		assertTrue("El m�todo children retorna una colecci�n vac�a para un nodo con solo hijo izquierdo.",
				children.hasNext());
		assertEquals(
				"El m�todo children no retorna correctamente el hijo izquierdo para un nodo con solo hijo izquierdo.",
				o1, children.next().element());
		assertFalse(
				"El m�todo children no retorna una colecci�n con un solo elemento para un nodo con solo hijo izquierdo.",
				children.hasNext());
	}

	/**
	 * Comprueba que el m�todo children retorna correctamente la colecci�n de
	 * hijos para un nodo con solo hijo derecho.
	 */
	@Test
	public void children_onlyRightChild_returnsCollectionWithRightChild()
			throws InvalidOperationException, InvalidPositionException {
		Position<Object> positionWithRightChild = binaryTree.createRoot(new Object());
		binaryTree.addRight(positionWithRightChild, o1);
		Iterator<Position<Object>> children = binaryTree.children(positionWithRightChild).iterator();
		assertTrue("El m�todo children retorna una colecci�n vac�a para un nodo con solo hijo derecho.",
				children.hasNext());
		assertEquals("El m�todo children no retorna correctamente el hijo derecho para un nodo con solo hijo derecho.",
				o1, children.next().element());
		assertFalse(
				"El m�todo children no retorna una colecci�n con un solo elemento para un nodo con solo hijo derecho.",
				children.hasNext());
	}

	/**
	 * Comprueba que el m�todo children retorna correctamente la colecci�n de
	 * hijos para un nodo con solo hijo izquierdo.
	 */
	@Test
	public void children_twoChildren_returnsCollectionWithChildren()
			throws InvalidOperationException, InvalidPositionException {
		Position<Object> positionWithChildren = binaryTree.createRoot(new Object());
		binaryTree.addLeft(positionWithChildren, o1);
		binaryTree.addRight(positionWithChildren, o2);
		Iterator<Position<Object>> children = binaryTree.children(positionWithChildren).iterator();
		assertTrue("El m�todo children retorna una colecci�n vac�a para un nodo con dos hijos.", children.hasNext());
		assertEquals("El m�todo children no retorna correctamente el hijo izquierdo para un nodo con dos hijos.", o1,
				children.next().element());
		assertTrue("El m�todo children retorna una colecci�n con solo un hijo para un nodo con dos hijos.",
				children.hasNext());
		assertEquals("El m�todo children no retorna correctamente el hijo derecho para un nodo con dos hijos.", o2,
				children.next().element());
		assertFalse("El m�todo children no retorna una colecci�n con solo dos elementos para un nodo con dos hijos.",
				children.hasNext());
	}

	/**
	 * Comprueba que el m�todo isInternal lanza la excepci�n
	 * InvalidPositionException al recibir una posici�n nula.
	 */
	@Test(expected = InvalidPositionException.class)
	public void isInternal_nullPosition_throwsIPE() throws InvalidPositionException, InvalidOperationException {
		binaryTree.createRoot(new Object());
		binaryTree.isInternal(null);
	}

	/**
	 * Comprueba que el m�todo isInternal retorna false en caso de recibir un
	 * nodo externo.
	 */
	@Test
	public void isInternal_onExternalNode_returnsFalse() throws InvalidOperationException, InvalidPositionException {
		Position<Object> externalPosition = binaryTree.createRoot(new Object());
		assertFalse("El m�todo isInternal no retorna false con un nodo externo.",
				binaryTree.isInternal(externalPosition));
	}

	/**
	 * Comprueba que el m�todo isInternal retorna true en caso de recibir un
	 * nodo interno.
	 */
	@Test
	public void isInternal_onInternalNode_returnsTrue() throws InvalidOperationException, InvalidPositionException {
		Position<Object> internalPosition = binaryTree.createRoot(new Object());
		binaryTree.addLeft(internalPosition, new Object());
		assertTrue("El m�todo isInternal no retorna false con un nodo externo.",
				binaryTree.isInternal(internalPosition));
	}

	/**
	 * Comprueba que el m�todo isExternal lanza la excepci�n
	 * InvalidPositionException al recibir una posici�n nula.
	 */
	@Test(expected = InvalidPositionException.class)
	public void isExternal_nullPosition_throwsIPE() throws InvalidPositionException, InvalidOperationException {
		binaryTree.createRoot(new Object());
		binaryTree.isExternal(null);
	}

	/**
	 * Comprueba que el m�todo isExternal retorna false en caso de recibir un
	 * nodo interno.
	 */
	@Test
	public void isExternal_onInternalNode_returnsFalse() throws InvalidOperationException, InvalidPositionException {
		Position<Object> internalPosition = binaryTree.createRoot(o1);
		binaryTree.addLeft(internalPosition, new Object());
		assertFalse("El m�todo isExternal no retorna false con un nodo interno.",
				binaryTree.isExternal(internalPosition));
	}

	/**
	 * Comprueba que el m�todo isExternal retorna true en caso de recibir un
	 * nodo externo.
	 */
	@Test
	public void isExternal_onExternalNode_returnsTrue() throws InvalidOperationException, InvalidPositionException {
		Position<Object> externalPosition = binaryTree.createRoot(new Object());
		assertTrue("El m�todo isExternal no retorna true con un nodo externo.",
				binaryTree.isExternal(externalPosition));
	}

	/**
	 * Comprueba que el m�todo isRoot lanza la excepci�n
	 * InvalidPositionException al recibir una posici�n nula.
	 */
	@Test(expected = InvalidPositionException.class)
	public void isRoot_nullPosition_throwsIPE() throws InvalidPositionException, InvalidOperationException {
		binaryTree.createRoot(new Object());
		binaryTree.isRoot(null);
	}

	/**
	 * Comprueba que el m�todo isRoot retorna true al ser llamado con la ra�z
	 * del �rbol.
	 */
	@Test
	public void isRoot_onRoot_returnsTrue() throws InvalidOperationException, InvalidPositionException {
		Position<Object> root = binaryTree.createRoot(new Object());
		assertTrue("El m�todo isRoot no retorna true al ser llamado con la ra�z.", binaryTree.isRoot(root));
	}

	/**
	 * Comprueba que el m�todo isRoot retorna false al ser llamado con un nodo
	 * distinto a la ra�z del �rbol.
	 */
	@Test
	public void isRoot_notOnRoot_returnsFalse() throws InvalidOperationException, InvalidPositionException {
		Position<Object> root = binaryTree.createRoot(new Object());
		Position<Object> notRoot = binaryTree.addLeft(root, new Object());
		assertFalse("El m�todo isRoot no retorna false al ser llamado con un nodo distinto a la ra�z.",
				binaryTree.isRoot(notRoot));
	}

	/**
	 * Comprueba que el m�todo left lanza la excepci�n InvalidPositionException
	 * al recibir una posici�n nula.
	 */
	@Test(expected = InvalidPositionException.class)
	public void left_nullPosition_throwsIPE()
			throws InvalidPositionException, InvalidOperationException, BoundaryViolationException {
		binaryTree.createRoot(new Object());
		binaryTree.left(null);
	}

	/**
	 * Comprueba que el m�todo left lanza la excepci�n
	 * BoundaryViolationException al recibir una posici�n que no tiene hijo
	 * izquierdo ni derecho.
	 */
	@Test(expected = BoundaryViolationException.class)
	public void left_positionWithoutChildren_throwsBVE()
			throws InvalidPositionException, InvalidOperationException, BoundaryViolationException {
		Position<Object> positionWithoutLeftChild = binaryTree.createRoot(new Object());
		binaryTree.left(positionWithoutLeftChild);
	}

	/**
	 * Comprueba que el m�todo left lanza la excepci�n
	 * BoundaryViolationException al recibir una posici�n que no tiene hijo
	 * izquierdo pero tiene hijo derecho.
	 */
	@Test(expected = BoundaryViolationException.class)
	public void left_positionWithoutLeftChild_throwsBVE()
			throws InvalidPositionException, InvalidOperationException, BoundaryViolationException {
		Position<Object> positionWithoutLeftChild = binaryTree.createRoot(new Object());
		binaryTree.addRight(positionWithoutLeftChild, new Object());
		binaryTree.left(positionWithoutLeftChild);
	}

	/**
	 * Comprueba que el m�todo left retorna correctamente la posici�n del hijo
	 * izquierdo de un nodo que solo tiene hijo izquierdo.
	 */
	@Test
	public void left_positionWithOnlyLeftChild_returnsLeftChild()
			throws InvalidOperationException, InvalidPositionException, BoundaryViolationException {
		Position<Object> positionWithLeftChild = binaryTree.createRoot(new Object());
		binaryTree.addLeft(positionWithLeftChild, o1);
		assertEquals(
				"El m�todo left no retorna correctamente el hijo izquierdo de un nodo que tiene solo hijo izquierdo.",
				o1, binaryTree.left(positionWithLeftChild).element());
	}

	/**
	 * Comprueba que el m�todo left retorna correctamente la posici�n del hijo
	 * izquierdo de un nodo que tiene hijo izquierdo y derecho.
	 */
	@Test
	public void left_positionWithTwoChilds_returnsLeftChild()
			throws InvalidOperationException, InvalidPositionException, BoundaryViolationException {
		Position<Object> positionWithTwoChilds = binaryTree.createRoot(new Object());
		binaryTree.addLeft(positionWithTwoChilds, o1);
		binaryTree.addRight(positionWithTwoChilds, new Object());
		assertEquals(
				"El m�todo left no retorna correctamente el hijo izquierdo de un nodo que tiene hijo izquierdo y derecho.",
				o1, binaryTree.left(positionWithTwoChilds).element());
	}

	/**
	 * Comprueba que el m�todo right lanza la excepci�n InvalidPositionException
	 * al recibir una posici�n nula.
	 */
	@Test(expected = InvalidPositionException.class)
	public void right_nullPosition_throwsIPE()
			throws InvalidPositionException, InvalidOperationException, BoundaryViolationException {
		binaryTree.createRoot(new Object());
		binaryTree.right(null);
	}

	/**
	 * Comprueba que el m�todo right lanza la excepci�n
	 * BoundaryViolationException al recibir una posici�n que no tiene hijo
	 * derecho ni izquierdo.
	 */
	@Test(expected = BoundaryViolationException.class)
	public void right_positionWithoutChildren_throwsBVE()
			throws InvalidPositionException, InvalidOperationException, BoundaryViolationException {
		Position<Object> positionWithoutChildren = binaryTree.createRoot(new Object());
		binaryTree.right(positionWithoutChildren);
	}

	/**
	 * Comprueba que el m�todo right lanza la excepci�n
	 * BoundaryViolationException al recibir una posici�n que no tiene hijo
	 * derecho pero tiene hijo izquierdo.
	 */
	@Test(expected = BoundaryViolationException.class)
	public void right_positionWithoutRightChild_throwsBVE()
			throws InvalidPositionException, InvalidOperationException, BoundaryViolationException {
		Position<Object> positionWithoutRightChild = binaryTree.createRoot(new Object());
		binaryTree.addLeft(positionWithoutRightChild, new Object());
		binaryTree.right(positionWithoutRightChild);
	}

	/**
	 * Comprueba que el m�todo right retorna correctamente la posici�n del hijo
	 * derecho de un nodo que solo tiene hijo derecho.
	 */
	@Test
	public void right_positionWithOnlyRightChild_returnsRightChild()
			throws InvalidOperationException, InvalidPositionException, BoundaryViolationException {
		Position<Object> positionWithRightChild = binaryTree.createRoot(new Object());
		binaryTree.addRight(positionWithRightChild, o1);
		assertEquals("El m�todo right no retorna correctamente el hijo derecho de un nodo que tiene solo hijo derecho.",
				o1, binaryTree.right(positionWithRightChild).element());
	}

	/**
	 * Comprueba que el m�todo right retorna correctamente la posici�n del hijo
	 * derecho de un nodo que tiene hijo izquierdo y derecho.
	 */
	@Test
	public void right_positionWithTwoChilds_returnsRightChild()
			throws InvalidOperationException, InvalidPositionException, BoundaryViolationException {
		Position<Object> positionWithTwoChilds = binaryTree.createRoot(new Object());
		binaryTree.addLeft(positionWithTwoChilds, new Object());
		binaryTree.addRight(positionWithTwoChilds, o1);
		assertEquals(
				"El m�todo right no retorna correctamente el hijo derecho de un nodo que tiene hijo izquierdo y derecho.",
				o1, binaryTree.right(positionWithTwoChilds).element());
	}

	/**
	 * Comprueba que el m�todo hasLeft lanza la excepci�n
	 * InvalidPositionException al recibir una posici�n nula.
	 */
	@Test(expected = InvalidPositionException.class)
	public void hasLeft_nullPosition_throwsIPE() throws InvalidPositionException, InvalidOperationException {
		binaryTree.createRoot(new Object());
		binaryTree.hasLeft(null);
	}

	/**
	 * Comprueba que el m�todo hasLeft retorna true con un nodo que solo tiene
	 * hijo izquierdo.
	 */
	@Test
	public void hasLeft_onlyLeftChild_returnsTrue() throws InvalidPositionException, InvalidOperationException {
		Position<Object> nodeWithLeftChild = binaryTree.createRoot(new Object());
		binaryTree.addLeft(nodeWithLeftChild, new Object());
		assertTrue("El m�todo hasLeft no retorna true con un nodo que solo tiene hijo izquierdo.",
				binaryTree.hasLeft(nodeWithLeftChild));
	}

	/**
	 * Comprueba que el m�todo hasLeft retorna true con un nodo que tiene hijo
	 * izquierdo y derecho.
	 */
	@Test
	public void hasLeft_leftAndRightChildren_returnsTrue() throws InvalidPositionException, InvalidOperationException {
		Position<Object> nodeWithChildren = binaryTree.createRoot(new Object());
		binaryTree.addLeft(nodeWithChildren, new Object());
		binaryTree.addRight(nodeWithChildren, new Object());
		assertTrue("El m�todo hasLeft no retorna true con un nodo que tiene hijo izquierdo y derecho.",
				binaryTree.hasLeft(nodeWithChildren));
	}

	/**
	 * Comprueba que el m�todo hasLeft retorna false con un nodo que no tiene
	 * hijo izquierdo pero tiene hijo derecho.
	 */
	@Test
	public void hasLeft_onlyRightChild_returnsFalse() throws InvalidPositionException, InvalidOperationException {
		Position<Object> nodeWithRightChild = binaryTree.createRoot(new Object());
		binaryTree.addRight(nodeWithRightChild, new Object());
		assertFalse("El m�todo hasLeft no retorna false con un nodo que solo tiene hijo derecho.",
				binaryTree.hasLeft(nodeWithRightChild));
	}

	/**
	 * Comprueba que el m�todo hasLeft retorna false con un nodo que no tiene
	 * hijos.
	 */
	@Test
	public void hasLeft_withoutChildren_returnsFalse() throws InvalidPositionException, InvalidOperationException {
		Position<Object> nodeWithoutChildren = binaryTree.createRoot(new Object());
		assertFalse("El m�todo hasLeft no retorna false con un nodo que no tiene hijos.",
				binaryTree.hasLeft(nodeWithoutChildren));
	}

	/**
	 * Comprueba que el m�todo hasRight lanza la excepci�n
	 * InvalidPositionException al recibir una posici�n nula.
	 */
	@Test(expected = InvalidPositionException.class)
	public void hasRight_nullPosition_throwsIPE() throws InvalidPositionException, InvalidOperationException {
		binaryTree.createRoot(new Object());
		binaryTree.hasRight(null);
	}

	/**
	 * Comprueba que el m�todo hasRight retorna true con un nodo que solo tiene
	 * hijo derecho.
	 */
	@Test
	public void hasRight_onlyRightChild_returnsTrue() throws InvalidPositionException, InvalidOperationException {
		Position<Object> nodeWithRightChild = binaryTree.createRoot(new Object());
		binaryTree.addRight(nodeWithRightChild, new Object());
		assertTrue("El m�todo hasRight no retorna true con un nodo que solo tiene hijo derecho.",
				binaryTree.hasRight(nodeWithRightChild));
	}

	/**
	 * Comprueba que el m�todo hasRight retorna true con un nodo que tiene hijo
	 * izquierdo y derecho.
	 */
	@Test
	public void hasRight_leftAndRightChildren_returnsTrue() throws InvalidPositionException, InvalidOperationException {
		Position<Object> nodeWithChildren = binaryTree.createRoot(new Object());
		binaryTree.addLeft(nodeWithChildren, new Object());
		binaryTree.addRight(nodeWithChildren, new Object());
		assertTrue("El m�todo hasRight no retorna true con un nodo que tiene hijo izquierdo y derecho.",
				binaryTree.hasRight(nodeWithChildren));
	}

	/**
	 * Comprueba que el m�todo hasRight retorna false con un nodo que no tiene
	 * hijo derecho pero tiene hijo izquierdo.
	 */
	@Test
	public void hasRight_onlyLeftChild_returnsFalse() throws InvalidPositionException, InvalidOperationException {
		Position<Object> nodeWithLeftChild = binaryTree.createRoot(new Object());
		binaryTree.addLeft(nodeWithLeftChild, new Object());
		assertFalse("El m�todo hasRight no retorna false con un nodo que solo tiene hijo izquierdo.",
				binaryTree.hasRight(nodeWithLeftChild));
	}

	/**
	 * Comprueba que el m�todo hasRight retorna false con un nodo que no tiene
	 * hijos.
	 */
	@Test
	public void hasRight_withoutChildren_returnsFalse() throws InvalidPositionException, InvalidOperationException {
		Position<Object> nodeWithoutChildren = binaryTree.createRoot(new Object());
		assertFalse("El m�todo hasRight no retorna false con un nodo que no tiene hijos.",
				binaryTree.hasRight(nodeWithoutChildren));
	}

	/**
	 * Comprueba que el m�todo createRoot lanza la excepci�n
	 * InvalidOperationException cuando se invoca sobre un �rbol que ya tiene
	 * ra�z.
	 */
	@Test(expected = InvalidOperationException.class)
	public void createRoot_existingRoot_throwsIOE() throws InvalidOperationException {
		binaryTree.createRoot(new Object());
		binaryTree.createRoot(new Object());
	}

	/**
	 * Comprueba que el m�todo createRoot retorna correctamente la posici�n con
	 * el elemento pasado por par�metro.
	 */
	@Test
	public void createRoot_emptyTree_createsRoot() throws InvalidOperationException {
		Position<Object> root = binaryTree.createRoot(o1);
		assertEquals("El m�todo createRoot retorna una posici�n que no contiene el elemento pasado por par�metro.", o1,
				root.element());
	}

	/**
	 * Comprueba que el tama�o del �rbol es 1 luego de invocar a createRoot.
	 */
	@Test
	public void createRoot_emptyTree_treeSizeEqualsOne() throws InvalidOperationException {
		binaryTree.createRoot(new Object());
		assertEquals("El tama�o del �rbol no es 1 luego de invocar a createRoot.", 1, binaryTree.size());
	}

	/**
	 * Comprueba que el m�todo addLeft lanza InvalidPositionException con una
	 * posici�n nula.
	 */
	@Test(expected = InvalidPositionException.class)
	public void addLeft_nullPosition_throwsIPE() throws InvalidPositionException, InvalidOperationException {
		binaryTree.addLeft(null, new Object());
	}

	/**
	 * Comprueba que el m�todo addLeft lanza InvalidOperationException sobre un
	 * nodo que ya tiene hijo izquierdo.
	 */
	@Test(expected = InvalidOperationException.class)
	public void addLeft_nodeWithLeftChild_throwsIOE()
			throws InvalidOperationException, InvalidPositionException, EmptyTreeException {
		Position<Object> root = binaryTree.createRoot(new Object());
		binaryTree.addLeft(root, new Object());
		binaryTree.addLeft(root, new Object());
	}

	/**
	 * Comprueba que el m�todo addLeft retorna correctamente el hijo a�adido.
	 */
	@Test
	public void addLeft_normalBehavior_returnsNewNode()
			throws EmptyTreeException, InvalidPositionException, InvalidOperationException {
		Position<Object> root = binaryTree.createRoot(new Object());
		Position<Object> leftChild = binaryTree.addLeft(root, o1);
		assertEquals("El m�todo addLeft no retorna el elemento correcto.", o1, leftChild.element());
	}

	/**
	 * Comprueba que luego del m�todo addLeft el nuevo nodo fue agregado al
	 * �rbol.
	 */
	@Test
	public void addLeft_normalBehavior_newNodeExistent()
			throws EmptyTreeException, InvalidPositionException, InvalidOperationException {
		Position<Object> root = binaryTree.createRoot(o1);
		binaryTree.addLeft(root, o2);
		Iterator<Object> nodesIterator = binaryTree.iterator();
		boolean located = false;
		while (nodesIterator.hasNext()) {
			if (nodesIterator.next().equals(o2)) {
				located = true;
				break;
			}
		}
		assertTrue(
				"Luego de a�adir un nodo mediante el m�todo addLeft, el mismo no est� presente en el �rbol seg�n el m�todo iterator.",
				located);
	}

	/**
	 * Comprueba que el m�todo addLeft incrementa el tama�o del �rbol.
	 */
	@Test
	public void addLeft_emptyTree_incrementsSize()
			throws EmptyTreeException, InvalidOperationException, InvalidPositionException {
		Position<Object> root = binaryTree.createRoot(o1);
		binaryTree.addLeft(root, o2);
		assertEquals(
				"Luego de a�adir un nodo mediante el m�todo addLeft a un �rbol con solo 1 elemento el tama�o no es dos.",
				2, binaryTree.size());
	}

	/**
	 * Comprueba que el m�todo addRight lanza InvalidPositionException con una
	 * posici�n nula.
	 */
	@Test(expected = InvalidPositionException.class)
	public void addRight_nullPosition_throwsIPE() throws InvalidPositionException, InvalidOperationException {
		binaryTree.addRight(null, new Object());
	}

	/**
	 * Comprueba que el m�todo addRight lanza InvalidOperationException sobre un
	 * nodo que ya tiene hijo derecho.
	 */
	@Test(expected = InvalidOperationException.class)
	public void addRight_nodeWithRightChild_throwsIOE()
			throws InvalidOperationException, InvalidPositionException, EmptyTreeException {
		Position<Object> root = binaryTree.createRoot(new Object());
		binaryTree.addRight(root, new Object());
		binaryTree.addRight(root, new Object());
	}

	/**
	 * Comprueba que el m�todo addRight retorna correctamente el hijo a�adido.
	 */
	@Test
	public void addRight_normalBehavior_returnsNewNode()
			throws EmptyTreeException, InvalidPositionException, InvalidOperationException {
		Position<Object> root = binaryTree.createRoot(new Object());
		Position<Object> rightChild = binaryTree.addRight(root, o1);
		assertEquals("El m�todo addRight no retorna el elemento correcto.", o1, rightChild.element());
	}

	/**
	 * Comprueba que luego del m�todo addRight el nuevo nodo fue agregado al
	 * �rbol.
	 */
	@Test
	public void addRight_normalBehavior_newNodeExistent()
			throws EmptyTreeException, InvalidPositionException, InvalidOperationException {
		Position<Object> root = binaryTree.createRoot(o1);
		binaryTree.addRight(root, o2);
		Iterator<Object> nodesIterator = binaryTree.iterator();
		boolean located = false;
		while (nodesIterator.hasNext()) {
			if (nodesIterator.next().equals(o2)) {
				located = true;
				break;
			}
		}
		assertTrue(
				"Luego de a�adir un nodo mediante el m�todo addRight, el mismo no est� presente en el �rbol seg�n el m�todo iterator.",
				located);
	}

	/**
	 * Comprueba que el m�todo addRight incrementa el tama�o del �rbol.
	 */
	@Test
	public void addRight_emptyTree_incrementsSize()
			throws EmptyTreeException, InvalidOperationException, InvalidPositionException {
		Position<Object> root = binaryTree.createRoot(o1);
		binaryTree.addRight(root, o2);
		assertEquals(
				"Luego de a�adir un nodo mediante el m�todo addRight a un �rbol con solo un elemento el tama�o no es dos.",
				2, binaryTree.size());
	}

	/**
	 * Comprueba que el m�todo remove lanza InvalidPositionException con una
	 * posici�n nula.
	 */
	@Test(expected = InvalidPositionException.class)
	public void remove_nullPosition_throwsIPE() throws InvalidPositionException, InvalidOperationException {
		binaryTree.remove(null);
	}

	/**
	 * Comprueba que el m�todo remove lanza InvalidOperationException sobre un
	 * nodo con dos hijos.
	 */
	@Test(expected = InvalidOperationException.class)
	public void remove_twoChildren_throwsIOE()
			throws InvalidOperationException, EmptyTreeException, InvalidPositionException {
		Position<Object> root = binaryTree.createRoot(new Object());
		binaryTree.addLeft(root, new Object());
		binaryTree.addRight(root, new Object());
		binaryTree.remove(root);
	}

	/**
	 * Comprueba que el m�todo remove retorna correctamente el elemento
	 * eliminado.
	 */
	@Test
	public void remove_validPosition_returnsRemovedElement()
			throws EmptyTreeException, InvalidOperationException, InvalidPositionException {
		Position<Object> root = binaryTree.createRoot(o1);
		assertEquals("El m�todo remove no retorna el elemento eliminado correctamente.", o1, binaryTree.remove(root));
	}

	/**
	 * Comprueba que luego de invocar al m�todo remove el nodo a eliminar ya no
	 * aparece en el listado de nodos del �rbol.
	 */
	@Test
	public void remove_validPosition_removedNode()
			throws EmptyTreeException, InvalidOperationException, InvalidPositionException {
		Position<Object> root = binaryTree.createRoot(new Object());
		binaryTree.remove(root);
		Iterator<Object> elements = binaryTree.iterator();
		assertFalse("El m�todo iterator no retorna un iterador vac�o luego de eliminar el �nico nodo del �rbol.",
				elements.hasNext());
	}

	/**
	 * Comprueba que luego de invocar al m�todo remove sobre un nodo hoja, el
	 * nodo eliminado ya no aparece en la lista de hijos del padre.
	 */
	@Test
	public void remove_leafNode_removedFromParentChildrenList()
			throws EmptyTreeException, InvalidOperationException, InvalidPositionException {
		Position<Object> root = binaryTree.createRoot(new Object());
		Position<Object> leafChild = binaryTree.addLeft(root, new Object());
		binaryTree.remove(leafChild);
		Iterator<Position<Object>> parentChildren = binaryTree.children(root).iterator();
		assertFalse("El m�todo children no retorna una colecci�n vac�a luego de eliminar el �nico hijo de un nodo.",
				parentChildren.hasNext());
	}

	/**
	 * Comprueba que luego de invocar al m�todo remove sobre un nodo interno, el
	 * nodo eliminado es reemplazado por su hijo en la lista de hijos del padre.
	 */
	@Test
	public void remove_internalNode_replacedInParentChildrenList()
			throws EmptyTreeException, InvalidOperationException, InvalidPositionException {
		Position<Object> root = binaryTree.createRoot(new Object());
		Position<Object> internalNode = binaryTree.addLeft(root, new Object());
		binaryTree.addRight(internalNode, o1);
		binaryTree.remove(internalNode);
		Iterator<Position<Object>> parentChildren = binaryTree.children(binaryTree.root()).iterator();
		assertTrue(
				"Luego de invocar al m�todo remove sobre un nodo interno, la lista de hijos del padre est� vac�a y no se hizo el reemplazo adecuado por el nodo eliminado.",
				parentChildren.hasNext());
		assertEquals(
				"Luego de invocar al m�todo remove para eliminar un nodo interno, el nodo que lo reemplaz� en la jerarqu�a no es el correcto.",
				o1, parentChildren.next().element());
		assertFalse(
				"Luego de invocar al m�todo remove sobre un nodo interno, �nico hijo de su padre, la colecci�n de hijos del padre tiene m�s de un elemento.",
				parentChildren.hasNext());
	}

	/**
	 * Comprueba que el m�todo attach lanza InvalidPositionException con una
	 * posici�n nula.
	 */
	@Test(expected = InvalidPositionException.class)
	public void attach_nullPosition_throwsIPE() throws InvalidPositionException, InvalidOperationException {
		binaryTree.attach(null, leftTree, rightTree);
	}

	/**
	 * Comprueba que el m�todo attach lanza InvalidPositionException si es
	 * invocado sobre un nodo interno.
	 */
	@Test(expected = InvalidPositionException.class)
	public void Attach_internalNode_throwsIPE()
			throws InvalidPositionException, EmptyTreeException, InvalidOperationException {
		binaryTree.createRoot(new Object());
		Position<Object> internalNode = binaryTree.addLeft(binaryTree.root(), new Object());
		binaryTree.addLeft(internalNode, new Object());
		binaryTree.attach(internalNode, leftTree, rightTree);
	}

	/**
	 * Comprueba que luego de un attach el tama�o del �rbol se incrementa acorde
	 * a los sub�rboles a�adidos.
	 */
	@Test
	public void Attach_normalBehavior_sizeIncremented()
			throws EmptyTreeException, InvalidOperationException, InvalidPositionException {
		binaryTree.createRoot(new Object());
		leftTree.createRoot(new Object());
		rightTree.createRoot(new Object());
		leftTree.addLeft(leftTree.root(), new Object());
		binaryTree.attach(binaryTree.root(), leftTree, rightTree);

		Iterator<Object> iteradorNodos = binaryTree.iterator();
		int cantNodos = 0;
		while (iteradorNodos.hasNext()) {
			cantNodos++;
			iteradorNodos.next();
		}
		assertEquals("El m�todo attach no actualiza el tama�o correctamente.", 4, cantNodos);
	}

	/**
	 * Comprueba que luego de un attach el �rbol tiene la nueva estructura.
	 */
	@Test
	public void Attach_normalBehavior_nodesAttached()
			throws EmptyTreeException, InvalidOperationException, InvalidPositionException, BoundaryViolationException {
		Position<Object> root = binaryTree.createRoot(new Object());
		Position<Object> rootT1 = leftTree.createRoot(new Object());
		Position<Object> rootT2 = rightTree.createRoot(new Object());
		leftTree.addLeft(leftTree.root(), new Object());
		binaryTree.attach(root, leftTree, rightTree);

		assertEquals("El m�todo attach no vincula correctamente el sub�rbol izquierdo.", rootT1.element(), binaryTree.left(root).element());
		assertEquals("El m�todo attach no vincula correctamente el sub�rbol izquierdo.", rootT2.element(),
				binaryTree.right(root).element());
	}

}
package TDAGrafoLalo;

//Libreri­as JUNIT
import static org.junit.Assert.*;
import org.junit.After;
import org.junit.Before;
import org.junit.Test;

//Estructura a testear
import TDAGrafo.*;
import java.util.Iterator;

/**
 * Tester desarrollado con JUnit 4 para la estructura Graph (no dirigido).
 * 
 * @author PiterSalvaje aka tobi
 * 
 * @version 1.0 Agosto 2020
 *
 */

public class GraphTest {

	// OBJETOS DE PRUEBA

	private Graph<Object, Object> graph;
	private Object o1;

	// INICIALIZACION Y LIMPIEZA DE OBJETOS PARA LAS PRUEBAS

	/**
	 * Setup inicial de los objetos utilizados en los casos de prueba. (llamado
	 * antes de cada test)
	 */
	@Before
	public void setUp() {
	//	graph = new GrafoMatriz<Object, Object>(105); // cambiar esta li­nea para probar
		 graph = new Grafo_lista_adyacencia<Object, Object>(); // cambiar esta li­nea
		// para probar
		// graph = new GrafoListaDeArcos<Object, Object>(); // cambiar esta li­nea para
		// probar
		// distintas implementaciones
		o1 = new Object();
	}

	/**
	 * Limpieza de los objetos utilizados en los casos de prueba. (llamado despues
	 * de cada test)
	 */
	@After
	public void tearDown() {
		graph = null; // esto es importante ya que los objetos pueden
		// persistir durante la ejecucion de todos los
		// tests, dejando objetos innecesarios en memoria
		// que ya no se esti¡n usando
		o1 = null;
	}

	// TESTS

	/**
	 * Comprueba que el metodo vertices retorna una coleccion iterable vaci­a si no
	 * hay vertices en el grafo.
	 */
	@Test
	public void vertices_emptyGraph_returnsEmptyCollection() {
		Iterable<Vertex<Object>> vertices = graph.vertices();
		Iterator<Vertex<Object>> verticesIterator = vertices.iterator();
		assertFalse("El metodo vertices no retorna una coleccion iterable vaci­a para un grafo sin vertices.",
				verticesIterator.hasNext());
	}

	/**
	 * Comprueba que el metodo edges retorna una coleccion iterable vaci­a si no hay
	 * arcos en el grafo.
	 */
	@Test
	public void edges_emptyGraph_returnsEmptyCollection() {
		Iterable<Edge<Object>> edges = graph.edges();
		Iterator<Edge<Object>> edgesIterator = edges.iterator();
		assertFalse("El metodo edges no retorna una coleccion iterable vaci­a para un grafo sin arcos.",
				edgesIterator.hasNext());
	}

	/**
	 * Comprueba que el metodo incidentEdges lanza InvalidVertexException con un
	 * vertice nulo.
	 */
	@Test(expected = InvalidVertexException.class)
	public void incidentEdges_nullVertex_throwsIVE() throws InvalidVertexException {
		graph.incidentEdges(null);
	}

	/**
	 * Comprueba que el metodo incidentEdges devuelve una coleccion vaci­a si se
	 * llama sobre un vertice sin arcos.
	 */
	@Test
	public void incidentEdges_vertexWithoutEdges_returnsEmptyCollection() throws InvalidVertexException {
		Vertex<Object> v1 = graph.insertVertex(new Object());
		Iterable<Edge<Object>> incidentEdges = graph.incidentEdges(v1);
		Iterator<Edge<Object>> edgesIterator = incidentEdges.iterator();
		assertFalse("El metodo incidentEdges no retorna una coleccion iterable vaci­a para un vertice sin arcos.",
				edgesIterator.hasNext());
	}

	/**
	 * Comprueba que el metodo opposite lanza InvalidVertexException con un vertice
	 * nulo.
	 */
	@Test(expected = InvalidVertexException.class)
	public void opposite_nullVertex_throwsIVE() throws InvalidVertexException, InvalidEdgeException {
		Vertex<Object> v1 = graph.insertVertex(new Object());
		Vertex<Object> v2 = graph.insertVertex(new Object());
		Edge<Object> e = graph.insertEdge(v1, v2, new Object());
		graph.opposite(null, e);
	}

	/**
	 * Comprueba que el metodo opposite lanza InvalidEdgeException con un arco nulo.
	 */
	@Test(expected = InvalidEdgeException.class)
	public void opposite_nullEdge_throwsIEE() throws InvalidVertexException, InvalidEdgeException {
		Vertex<Object> v1 = graph.insertVertex(new Object());
		graph.opposite(v1, null);
	}

	/**
	 * Comprueba que el metodo opposite lanza InvalidEdgeException si el arco no
	 * incide en el vertice.
	 */
	@Test(expected = InvalidEdgeException.class)
	public void opposite_edgeNotConnected_throwsIEE() throws InvalidEdgeException, InvalidVertexException {
		Vertex<Object> v1 = graph.insertVertex(new Object());
		Vertex<Object> v2 = graph.insertVertex(new Object());
		Edge<Object> e = graph.insertEdge(v1, v2, new Object());
		Vertex<Object> v3 = graph.insertVertex(new Object());
		graph.opposite(v3, e);
	}

	/**
	 * Comprueba que el metodo opposite retorna el vertice opuesto de un vertice
	 * insertado como primer pari¡metro del arco (origen).
	 */
	@Test
	public void opposite_predecessorVertex_returnsOpposite() throws InvalidVertexException, InvalidEdgeException {
		Vertex<Object> v1 = graph.insertVertex(new Object());
		Vertex<Object> v2 = graph.insertVertex(new Object());
		Edge<Object> e = graph.insertEdge(v1, v2, new Object());
		assertEquals(
				"El metodo opposite no retorna el opuesto de un vertice insertado como primer pari¡metro del arco.", v2,
				graph.opposite(v1, e));
	}

	/**
	 * Comprueba que el metodo opposite retorna el vertice opuesto de un vertice
	 * insertado como segundo pari¡metro del arco (destino).
	 */
	@Test
	public void opposite_successorEdge_returnsOpposite() throws InvalidVertexException, InvalidEdgeException {
		Vertex<Object> v1 = graph.insertVertex(new Object());
		Vertex<Object> v2 = graph.insertVertex(new Object());
		Edge<Object> e = graph.insertEdge(v1, v2, new Object());
		assertEquals(
				"El metodo opposite no retorna el opuesto de un vertice insertado como primer pari¡metro del arco.", v1,
				graph.opposite(v2, e));
	}

	/**
	 * Comprueba que el metodo endvertices lanza InvalidEdgeException con un arco
	 * nulo.
	 */
	@Test(expected = InvalidEdgeException.class)
	public void endvertices_nullEdge_throwsIEE() throws InvalidEdgeException {
		graph.endvertices(null);
	}

	/**
	 * Comprueba que el metodo endVertices retorna correctamente el arreglo de
	 * vertices para un arco.
	 */
	@Test
	public void endVertices_normalBehavior_returnsEndVertexs() throws InvalidVertexException, InvalidEdgeException {
		Vertex<Object> v1 = graph.insertVertex(new Object());
		Vertex<Object> v2 = graph.insertVertex(new Object());
		Edge<Object> e = graph.insertEdge(v1, v2, new Object());
		Vertex<Object> endVertices[] = graph.endvertices(e);
		assertEquals("El metodo endVertices retorna un arreglo que no tiene exactamente dos vertices.", 2,
				endVertices.length);
		assertEquals(
				"El primer vertice del arreglo retornado por endVertices no coincide con el primer vertice del arco.",
				v1, endVertices[0]);
		assertEquals(
				"El segundo vertice del arreglo retornado por endVertices no coincide con el segundo vertice del arco.",
				v2, endVertices[1]);
	}

	/**
	 * Comprueba que el metodo areAdjacent lanza InvalidVertexException cuando el
	 * primer vertice es nulo.
	 */
	@Test(expected = InvalidVertexException.class)
	public void areAdjacent_firstVertexNull_throwsIVE() throws InvalidVertexException {
		Vertex<Object> v1 = graph.insertVertex(new Object());
		graph.areAdjacent(null, v1);
	}

	/**
	 * Comprueba que el metodo areAdjacent lanza InvalidVertexException cuando el
	 * segundo vertice es nulo.
	 */
	@Test(expected = InvalidVertexException.class)
	public void areAdjacent_secondVertexNull_throwsIVE() throws InvalidVertexException {
		Vertex<Object> v1 = graph.insertVertex(new Object());
		graph.areAdjacent(v1, null);
	}

	/**
	 * Comprueba que el metodo areAdjacent lanza InvalidVertexException cuando ambos
	 * vertices son nulos.
	 */
	@Test(expected = InvalidVertexException.class)
	public void areAdjacent_bothVerticesNull_throwsIVE() throws InvalidVertexException {
		graph.areAdjacent(null, null);
	}

	/**
	 * Comprueba que el metodo areAdjacent retorna true para dos vertices adyacentes
	 * con un iºnico arco entre ellos.
	 */
	@Test
	public void areAdjacent_adjacentsVerticesPredecessorFirst_returnsTrue() throws InvalidVertexException {
		Vertex<Object> v1 = graph.insertVertex(new Object());
		Vertex<Object> v2 = graph.insertVertex(new Object());
		graph.insertEdge(v1, v2, new Object());
		assertTrue("El metodo areAdjacent no retorna true para dos vertices con un arco entre ellos.",
				graph.areAdjacent(v1, v2));
	}

	/**
	 * Comprueba que el metodo areAdjacent retorna true para dos vertices adyacentes
	 * con un iºnico arco entre ellos.
	 */
	@Test
	public void areAdjacent_adjacentsVerticesSuccessorFirst_returnsTrue() throws InvalidVertexException {
		Vertex<Object> v1 = graph.insertVertex(new Object());
		Vertex<Object> v2 = graph.insertVertex(new Object());
		graph.insertEdge(v1, v2, new Object());
		assertTrue("El metodo areAdjacent no retorna true para dos vertices con un arco entre ellos.",
				graph.areAdjacent(v2, v1));
	}

	/**
	 * Comprueba que el metodo areAdjacent retorna true para dos vertices adyacentes
	 * con mi¡s de un arco entre ellos.
	 */
	@Test
	public void areAdjacent_adjacentsVerticesTwoEdges_returnsTrue() throws InvalidVertexException {
		Vertex<Object> v1 = graph.insertVertex(new Object());
		Vertex<Object> v2 = graph.insertVertex(new Object());
		graph.insertEdge(v1, v2, new Object());
		graph.insertEdge(v1, v2, new Object());
		assertTrue("El metodo areAdjacent no retorna true para dos vertices con un arco entre ellos.",
				graph.areAdjacent(v1, v2));
	}

	/**
	 * Comprueba que el metodo areAdjacent retorna false para dos vertices no
	 * adyacentes.
	 */
	@Test
	public void areAdjacent_notAdjacentsVertexs_returnsFalse() throws InvalidVertexException {
		Vertex<Object> v1 = graph.insertVertex(new Object());
		Vertex<Object> v2 = graph.insertVertex(new Object());
		assertFalse("El metodo areAdjacent retorna true para dos vertices no adyacentes.", graph.areAdjacent(v1, v2));
	}

	/**
	 * Comprueba que el metodo replace lanza InvalidVertexException con un vertice
	 * nulo.
	 */
	@Test(expected = InvalidVertexException.class)
	public void replace_nullVertex_throwsIVE() throws InvalidVertexException {
		graph.replace(null, new Object());
	}

	/**
	 * Comprueba que el metodo replace retorna correctamente el elemento previo en
	 * el vertice pasado por pari¡metro.
	 */
	@Test
	public void replace_validVertex_returnsOldElement() throws InvalidVertexException {
		Vertex<Object> v1 = graph.insertVertex(o1);
		assertEquals("El metodo replace no retorna el elemento previo en el vertice pasado por pari¡metro.", o1,
				graph.replace(v1, new Object()));

	}

	/**
	 * Comprueba que el metodo replace setea correctamente el nuevo elemento al
	 * vertice pasado por pari¡metro.
	 */
	@Test
	public void replace_validVertex_setsNewElement() throws InvalidVertexException {
		Vertex<Object> v1 = graph.insertVertex(new Object());
		graph.replace(v1, o1);
		assertEquals("El metodo replace no setea correctamente el nuevo elemento.", o1, v1.element());
	}

	/**
	 * Comprueba que el metodo insertVertex retorna correctamente el vertice
	 * insertado.
	 */
	@Test
	public void insertVertex_emptyGraph_returnsInsertedVertex() throws InvalidVertexException {
		Vertex<Object> v1 = graph.insertVertex(o1);
		assertEquals("El metodo insertVertex no retorna correctamente el vertice insertado.", o1, v1.element());
	}

	/**
	 * Comprueba que un vertice insertado aparece correctamente en la coleccion de
	 * vertices del grafo.
	 */
	@Test
	public void insertVertex_emptyGraph_newVertexInGraph() throws InvalidVertexException {
		Vertex<Object> v1 = graph.insertVertex(o1);
		Iterable<Vertex<Object>> vertices = graph.vertices();
		Iterator<Vertex<Object>> verticesIterator = vertices.iterator();
		assertTrue("Luego de insertar un vertice, se sigue retornando una coleccion vaci­a en el metodo vertices.",
				verticesIterator.hasNext());
		assertEquals(
				"Luego de insertar un vertice, el elemento del mismo no coincide con aquel presente en la coleccion retornada por el metodo vertices.",
				v1, verticesIterator.next());
		assertFalse(
				"Luego de insertar un iºnico vertice, el metodo vertices retorna una coleccion con mi¡s de un elemento.",
				verticesIterator.hasNext());
	}

	/**
	 * Comprueba que el metodo insertEdge lanza InvalidVertexException cuando el
	 * primer vertice es nulo.
	 */
	@Test(expected = InvalidVertexException.class)
	public void insertEdge_firstVertexNull_throwsIVE() throws InvalidVertexException {
		Vertex<Object> v1 = graph.insertVertex(new Object());
		graph.insertEdge(null, v1, new Object());
	}

	/**
	 * Comprueba que el metodo insertEdge lanza InvalidVertexException cuando el
	 * segundo vertice es nulo.
	 */
	@Test(expected = InvalidVertexException.class)
	public void insertEdge_secondVertexNull_throwsIVE() throws InvalidVertexException {
		Vertex<Object> v1 = graph.insertVertex(new Object());
		graph.insertEdge(v1, null, new Object());
	}

	/**
	 * Comprueba que el metodo insertEdge lanza InvalidVertexException cuando ambos
	 * vertices son nulos.
	 */
	@Test(expected = InvalidVertexException.class)
	public void insertEdge_bothVerticesNull_throwsIVE() throws InvalidVertexException {
		graph.insertEdge(null, null, new Object());
	}

	/**
	 * Comprueba que el metodo insertEdge retorna correctamente el arco insertado.
	 */
	@Test
	public void insertEdge_emptyGraph_returnsInsertedEdge() throws InvalidVertexException {
		Vertex<Object> v1 = graph.insertVertex(new Object());
		Vertex<Object> v2 = graph.insertVertex(new Object());
		Edge<Object> e = graph.insertEdge(v1, v2, o1);
		assertEquals("El metodo insertEdge retorna un arco cuyo elemento no coincide con el pasado por pari¡metro.", o1,
				e.element());
	}

	/**
	 * Comprueba que un arco insertado aparece correctamente en la coleccion de
	 * arcos del grafo.
	 */
	@Test
	public void insertEdge_emptyGraph_newEdgeInGraph() throws InvalidVertexException, InvalidEdgeException {
		Vertex<Object> v1 = graph.insertVertex(new Object());
		Vertex<Object> v2 = graph.insertVertex(new Object());
		Edge<Object> e = graph.insertEdge(v1, v2, o1);
		Iterable<Edge<Object>> edges = graph.edges();
		Iterator<Edge<Object>> edgesIterator = edges.iterator();
		assertTrue("Luego de insertar un arco, se sigue retornando una coleccion vaci­a en el metodo edges.",
				edgesIterator.hasNext());
		assertEquals(
				"Luego de insertar un arco, el elemento del mismo no coincide con aquel presente en la coleccion retornada por el metodo edges.",
				e, edgesIterator.next());
		assertFalse("Luego de insertar un iºnico arco, el metodo edges retorna una coleccion con mi¡s de un elemento.",
				edgesIterator.hasNext());
	}

	/**
	 * Comprueba que luego de ai±adir un arco el mismo aparece como arco incidente
	 * en el vertice pasado como primer pari¡metro al arco.
	 */
	@Test
	public void insertEdge_emptyGraph_EdgeInV1AdjacencyList() throws InvalidVertexException, InvalidEdgeException {
		Vertex<Object> v1 = graph.insertVertex(new Object());
		Vertex<Object> v2 = graph.insertVertex(new Object());
		Edge<Object> e = graph.insertEdge(v1, v2, o1);
		Iterable<Edge<Object>> incidentEdgesV1 = graph.incidentEdges(v1);
		Iterator<Edge<Object>> edgesIteratorV1 = incidentEdgesV1.iterator();
		assertTrue(
				"Luego de insertar un arco a partir de un dado vertice, se sigue retornando una coleccion vaci­a en los arcos incidentes de dicho vertice.",
				edgesIteratorV1.hasNext());
		assertEquals(
				"Luego de insertar un arco a partir de un dado vertice, el elemento del arco insertado no coincide con aquel devuelto en la lista de arcos incidentes de dicho vertice.",
				e, edgesIteratorV1.next());
		assertFalse(
				"Luego de insertar un arco a partir de un dado vertice, se retorna una coleccion con mi¡s de un elemento en los arcos incidentes de dicho vertice.",
				edgesIteratorV1.hasNext());
	}

	/**
	 * Comprueba que luego de ai±adir un arco el mismo aparece como arco incidente
	 * en el vertice pasado como segundo pari¡metro al arco.
	 */
	@Test
	public void insertEdge_emptyGraph_EdgeInV2AdjacencyList() throws InvalidVertexException, InvalidEdgeException {
		Vertex<Object> v1 = graph.insertVertex(new Object());
		Vertex<Object> v2 = graph.insertVertex(new Object());
		Edge<Object> e = graph.insertEdge(v1, v2, o1);
		Iterable<Edge<Object>> incidentEdgesV2 = graph.incidentEdges(v2);
		Iterator<Edge<Object>> edgesIteratorV2 = incidentEdgesV2.iterator();
		assertTrue(
				"Luego de insertar un arco a partir de un dado vertice, se sigue retornando una coleccion vaci­a en los arcos incidentes de dicho vertice.",
				edgesIteratorV2.hasNext());
		assertEquals(
				"Luego de insertar un arco a partir de un dado vertice, el elemento del arco insertado no coincide con aquel devuelto en la lista de arcos incidentes de dicho vertice.",
				e, edgesIteratorV2.next());
		assertFalse(
				"Luego de insertar un arco a partir de un dado vertice, se retorna una coleccion con mi¡s de un elemento en los arcos incidentes de dicho vertice.",
				edgesIteratorV2.hasNext());
	}

	/**
	 * Comprueba que el metodo removeVertex lanza InvalidVertexException con un
	 * vertice nulo.
	 */
	@Test(expected = InvalidVertexException.class)
	public void removeVertex_nullVertex_throwsIVE() throws InvalidVertexException {
		graph.removeVertex(null);
	}

	/**
	 * Comprueba que el metodo removeVertex retorna correctamente el elemento del
	 * vertice eliminado.
	 */
	@Test
	public void removeVertex_validVertex_returnsElement() throws InvalidVertexException {
		Vertex<Object> v1 = graph.insertVertex(o1);
		Object element = graph.removeVertex(v1);
		assertEquals("El metodo removeVertex no retorna el elemento del vertice eliminado.", o1, element);
	}

	/**
	 * Comprueba que luego de eliminar un vertice mediante el metodo removeVertex,
	 * el mismo ya no es devuelto en la lista de vertices del grafo.
	 */
	@Test
	public void removeVertex_validVertex_removedFromVerticesList() throws InvalidVertexException {
		Vertex<Object> v1 = graph.insertVertex(o1);
		graph.removeVertex(v1);
		Iterable<Vertex<Object>> vertices = graph.vertices();
		Iterator<Vertex<Object>> verticesIterator = vertices.iterator();
		assertFalse(
				"El metodo vertices no retorna una coleccion iterable vaci­a luego de eliminar el iºnico vertice del grafo.",
				verticesIterator.hasNext());
	}

	/**
	 * Comprueba que luego de eliminar un vertice con un arco mediante el metodo
	 * removeVertex, dicho arco ya no esti¡ presente en la lista de arcos del grafo.
	 */
	@Test
	public void removeVertex_vertexWithEdge_removedEdge() throws InvalidVertexException, InvalidEdgeException {
		Vertex<Object> v1 = graph.insertVertex(new Object());
		Vertex<Object> v2 = graph.insertVertex(new Object());
		graph.insertEdge(v1, v2, new Object());
		graph.removeVertex(v1);
		Iterable<Edge<Object>> edges = graph.edges();
		Iterator<Edge<Object>> edgesIterator = edges.iterator();
		assertFalse(
				"El metodo edges no retorna una coleccion iterable vaci­a luego de eliminar el vertice que teni­a el iºnico arco del grafo.",
				edgesIterator.hasNext());
	}

	/**
	 * Comprueba que el metodo removeEdge lanza InvalidEdgeException con un arco
	 * nulo.
	 */
	@Test(expected = InvalidEdgeException.class)
	public void removeEdge_nullEdge_throwsIEE() throws InvalidEdgeException {
		graph.removeEdge(null);
	}

	/**
	 * Comprueba que el metodo removeEdge retorna correctamente el elemento del arco
	 * eliminado.
	 */
	@Test
	public void removeEdge_validEdge_returnsElement() throws InvalidVertexException, InvalidEdgeException {
		Vertex<Object> v1 = graph.insertVertex(new Object());
		Vertex<Object> v2 = graph.insertVertex(new Object());
		Edge<Object> e = graph.insertEdge(v1, v2, o1);
		Object element = graph.removeEdge(e);
		assertEquals("El metodo removeEdge no retorna el elemento del arco eliminado.", o1, element);
	}

	/**
	 * Comprueba que luego de eliminar un arco mediante el metodo removeEdge, el
	 * mismo ya no es devuelto en la lista de arcos del grafo.
	 */
	@Test
	public void removeEdge_validEdge_removedFromEdgesList() throws InvalidVertexException, InvalidEdgeException {
		Vertex<Object> v1 = graph.insertVertex(new Object());
		Vertex<Object> v2 = graph.insertVertex(new Object());
		Edge<Object> e = graph.insertEdge(v1, v2, new Object());
		graph.removeEdge(e);
		Iterable<Edge<Object>> edges = graph.edges();
		Iterator<Edge<Object>> edgesIterator = edges.iterator();
		assertFalse(
				"El metodo edges no retorna una coleccion iterable vaci­a luego de eliminar el iºnico arco del grafo mediante removeEdge.",
				edgesIterator.hasNext());
	}

	/**
	 * Comprueba que luego de eliminar un arco mediante el metodo removeEdge, el
	 * mismo ya no es devuelvo en la lista de arcos incidentes del predecesor.
	 */
	@Test
	public void removeEdge_validEdge_removedFromPredecessorIncidentEdges()
			throws InvalidVertexException, InvalidEdgeException {
		Vertex<Object> v1 = graph.insertVertex(new Object());
		Vertex<Object> v2 = graph.insertVertex(new Object());
		Edge<Object> e = graph.insertEdge(v1, v2, new Object());
		graph.removeEdge(e);
		Iterable<Edge<Object>> edgesV1 = graph.incidentEdges(v1);
		Iterator<Edge<Object>> edgesIteratorV1 = edgesV1.iterator();
		assertFalse(
				"Luego de eliminar un arco, el mismo sigue apareciendo en la lista de incidentes del vertice predecesor.",
				edgesIteratorV1.hasNext());
	}

	/**
	 * Comprueba que luego de eliminar un arco mediante el metodo removeEdge, el
	 * mismo ya no es devuelvo en la lista de arcos incidentes del sucesor.
	 */
	@Test
	public void removeEdge_validEdge_removedFromSuccessorIncidentEdges()
			throws InvalidVertexException, InvalidEdgeException {
		Vertex<Object> v1 = graph.insertVertex(new Object());
		Vertex<Object> v2 = graph.insertVertex(new Object());
		Edge<Object> e = graph.insertEdge(v1, v2, new Object());
		graph.removeEdge(e);
		Iterable<Edge<Object>> edgesV2 = graph.incidentEdges(v2);
		Iterator<Edge<Object>> edgesIteratorV2 = edgesV2.iterator();
		assertFalse(
				"Luego de eliminar un arco, el mismo sigue apareciendo en la lista de incidentes del vertice sucesor.",
				edgesIteratorV2.hasNext());
	}

	/**
	 * Hace una simulacion de uso general sobre la estructura.
	 */
	@Test
	public void simulation() throws InvalidVertexException, InvalidEdgeException {
		Vertex<Object> v1 = graph.insertVertex(new Object());
		Vertex<Object> v2 = graph.insertVertex(new Object());
		for (int i = 0; i < 100; i++) {
			graph.insertVertex(new Object());
		}
		Vertex<Object> v3 = graph.insertVertex(new Object());
		Vertex<Object> v4 = graph.insertVertex(new Object());

		Iterable<Vertex<Object>> vertices = graph.vertices();
		Iterator<Vertex<Object>> iteradorVertices = vertices.iterator();
		int contadorVertices = 0;
		while (iteradorVertices.hasNext()) {
			iteradorVertices.next();
			contadorVertices++;
		}
		assertEquals("Luego de insertar 104 vertices, la lista de vertices no coincide en tamai±o", 104,
				contadorVertices);

		Edge<Object> e1 = graph.insertEdge(v1, v2, new Object());
		Edge<Object> e2 = graph.insertEdge(v1, v3, new Object());
		Edge<Object> e3 = graph.insertEdge(v3, v4, new Object());

		assertTrue("areAdjacent retorna false para dos vertices adyacentes.", graph.areAdjacent(v1, v2));
		assertTrue("areAdjacent retorna false para dos vertices adyacentes.", graph.areAdjacent(v1, v3));
		assertTrue("areAdjacent retorna false para dos vertices adyacentes.", graph.areAdjacent(v3, v4));
		assertFalse("areAdjacent retorna true para dos vertices no adyacentes.", graph.areAdjacent(v1, v4));
		assertFalse("areAdjacent retorna true para dos vertices no adyacentes.", graph.areAdjacent(v2, v3));
		assertFalse("areAdjacent retorna true para dos vertices no adyacentes.", graph.areAdjacent(v2, v4));

		assertEquals("opposite no retorna el vertice opuesto correctamente.", v3, graph.opposite(v1, e2));
		assertEquals("opposite no retorna el vertice opuesto correctamente.", v1, graph.opposite(v3, e2));
		assertEquals("opposite no retorna el vertice opuesto correctamente.", v1, graph.opposite(v2, e1));
		assertEquals("opposite no retorna el vertice opuesto correctamente.", v2, graph.opposite(v1, e1));
		assertEquals("opposite no retorna el vertice opuesto correctamente.", v4, graph.opposite(v3, e3));
		assertEquals("opposite no retorna el vertice opuesto correctamente.", v3, graph.opposite(v4, e3));

		Iterable<Edge<Object>> arcos = graph.edges();
		Iterator<Edge<Object>> iteradorArcos = arcos.iterator();
		int contadorArcos = 0;
		while (iteradorArcos.hasNext()) {
			iteradorArcos.next();
			contadorArcos++;
		}
		assertEquals("Luego de insertar 3 arcos, la lista de arcos no coincide en tamai±o", 3, contadorArcos);

		arcos = graph.incidentEdges(v1);
		iteradorArcos = arcos.iterator();
		contadorArcos = 0;
		while (iteradorArcos.hasNext()) {
			iteradorArcos.next();
			contadorArcos++;
		}
		assertEquals("El metodo incidentEdges no retorna una coleccion de tamai±o 2 para un vertice con dos arcos.", 2,
				contadorArcos);

		arcos = graph.incidentEdges(v3);
		iteradorArcos = arcos.iterator();
		contadorArcos = 0;
		while (iteradorArcos.hasNext()) {
			iteradorArcos.next();
			contadorArcos++;
		}
		assertEquals("El metodo incidentEdges no retorna una coleccion de tamai±o 2 para un vertice con dos arcos.", 2,
				contadorArcos);

		graph.removeEdge(e1);
		graph.removeEdge(e2);
		assertFalse("areAdjacent retorna true para dos vertices no adyacentes.", graph.areAdjacent(v1, v2));
		assertFalse("areAdjacent retorna true para dos vertices no adyacentes.", graph.areAdjacent(v1, v3));
		arcos = graph.edges();
		iteradorArcos = arcos.iterator();
		contadorArcos = 0;
		while (iteradorArcos.hasNext()) {
			iteradorArcos.next();
			contadorArcos++;
		}
		assertEquals("Luego de insertar 3 arcos y eliminar 2, la lista de arcos no coincide en tamai±o", 1,
				contadorArcos);

		arcos = graph.incidentEdges(v3);
		iteradorArcos = arcos.iterator();
		contadorArcos = 0;
		while (iteradorArcos.hasNext()) {
			iteradorArcos.next();
			contadorArcos++;
		}
		assertEquals("El metodo incidentEdges no retorna una coleccion de tamai±o 1 para un vertice con un solo arcos.",
				1, contadorArcos);

	}

}
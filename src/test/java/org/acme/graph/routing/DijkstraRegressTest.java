package org.acme.graph.routing;

import static org.junit.Assert.assertEquals;
import static org.junit.Assert.assertNotNull;
import static org.junit.Assert.assertNull;

import java.util.List;

import org.acme.graph.TestGraphFactory;
import org.acme.graph.model.Edge;
import org.acme.graph.model.Graph;
import org.junit.Before;
import org.junit.Test;

import javassist.NotFoundException;

/**
 * Tests fonctionnels sur DijkstraPathFinder
 * 
 * @author MBorne
 *
 */
public class DijkstraRegressTest {
	
	public static final double EPSILON = 1.0e-15;

	private Graph graph;

	private DijkstraPathFinder finder;

	@Before
	public void setUp() throws Exception {
		this.graph = TestGraphFactory.createGraph01();
		this.finder = new DijkstraPathFinder(graph);
	}

	@Test
	public void testABFound() {
		List<Edge> path = finder.findPath(graph.findVertex("a"), graph.findVertex("b"));
		assertNotNull(path);
		assertEquals(1, path.size(), EPSILON);
	}

	@Test(expected = org.acme.graph.errors.NotFoundException.class)
	public void testBANotFound() {
		finder.findPath(graph.findVertex("b"), graph.findVertex("a"));
	}

	@Test
	public void testACFoundWithCorrectOrder() {
		List<Edge> path = finder.findPath(graph.findVertex("a"), graph.findVertex("c"));
		assertNotNull(path);
		assertEquals(2, path.size(), EPSILON);

		int index = 0;
		{
			Edge edge = path.get(index++);
			assertEquals("a", edge.getSource().getId());
			assertEquals("b", edge.getTarget().getId());
		}
		{
			Edge edge = path.get(index++);
			assertEquals("b", edge.getSource().getId());
			assertEquals("c", edge.getTarget().getId());
		}
	}
}

package graph;

import java.util.Comparator;

/**
 * Compare to edges by the cost of the edge.
 * @author patriciaribeiro
 *
 */

public class EdgeComparator implements Comparator<Edge>{

	@Override
	public int compare(Edge edge0, Edge edge1) {
		return new Integer(edge0.cost).compareTo(edge1.cost);
	}
	
}
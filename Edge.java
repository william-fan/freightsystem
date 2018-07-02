
public class Edge {
	private Node a;
	private Node b;
	private int travelCost;
	
	/**
	 * @param a A node that the edge is connected to.
	 * @param b A node that the edge is connected to.
	 * @param travelCost The travel cost of the edge.
	 */
	public Edge (Node a, Node b, int travelCost){
		this.a = a;
		this.b = b;
		this.travelCost = travelCost;
	}
	
	/**
	 * Get node A of the edge.
	 * @return Node A of the edge.
	 */
	public Node getA() {
		return this.a;
	}
	
	/**
	 * Set node A of the edge.
	 * @param a Node A of the edge.
	 */
	public void setA(Node a) {
		this.a = a;
	}

	/**
	 * Get node B of the edge.
	 * @return Node B of the edge.
	 */
	public Node getB() {
		return this.b;
	}

	/**
	 * Set node B of the edge.
	 * @param b Node B of the edge.
	 */
	public void setB(Node b) {
		this.b = b;
	}

	/**
	 * Get the travel cost of the edge.
	 * @return The travel cost of the edge.
	 */
	public int getTravelCost() {
		return this.travelCost;
	}
	
	/**
	 * Set the travel cost of the edge.
	 * @param travelCost The travel cost of the edge.
	 */
	public void setTravelCost(int travelCost) {
		this.travelCost = travelCost;
	}
}

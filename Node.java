
public class Node {
	private String name;
	private int unloadCost;
	
	/**
	 * @param name The name of the node.
	 * @param unloadCost The cost to unload at the node.
	 */
	public Node (String name, int unloadCost){
		this.name = name;
		this.unloadCost = unloadCost;
	}

	/**
	 * Get the name of the node.
	 * @return The name of the node.
	 */
	public String getName() {
		return this.name;
	}
	
	/**
	 * Set the name of the node.
	 * @param name The name of the node.
	 */
	public void setName(String name) {
		this.name = name;
	}
	
	/**
	 * Get the unload cost of the node.
	 * @return The unload cost of the node.
	 */
	public int getUnloadCost() {
		return this.unloadCost;
	}
	
	/**
	 * Set the unload cost of the node.
	 * @param unloadCost The unload cost of the node.
	 */
	public void setUnloadCost(int unloadCost) {
		this.unloadCost = unloadCost;
	}
	
}

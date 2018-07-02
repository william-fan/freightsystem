import java.util.ArrayList;
import java.util.Comparator;

public class Graph implements Comparator<Graph> {
	private int totalFcost;
	private int totalGcost;
	private int totalHcost;
	private Node lastVisited;
	private ArrayList<Node> routeTaken;
	private ArrayList<Job> jobsRemaining;
	
	/**
	 * @param totalFcost The total F cost of this graph, where Fcost = Gcost + Hcost.
	 * @param totalGcost The total G cost of this graph, where Gcost is the total distance travel cost.
	 * @param totalHcost The total H cost of this graph, where Hcost is the heuristic cost, based on if jobs are completed.
	 * @param lastVisited The last visited node.
	 * @param routeTaken The route taken by the graph.
	 * @param jobsRemaining The list of jobs that have not been completed.
	 */
	public Graph(int totalFcost, int totalGcost, int totalHcost, Node lastVisited, ArrayList<Node> routeTaken, ArrayList<Job> jobsRemaining) {
		this.totalFcost = totalFcost;
		this.totalGcost = totalGcost;
		this.totalHcost = totalHcost;
		this.lastVisited = lastVisited;
		this.routeTaken = routeTaken;
		this.jobsRemaining = jobsRemaining;
	}
	
	/**
	 * Get the F cost of the graph.
	 * @return The F cost of the graph.
	 */
	public int getTotalFcost() {
		return this.totalFcost;
	}
	
	/**
	 * Set the F cost of the graph.
	 * @param totalFcost The F cost of the graph.
	 */
	public void setTotalFcost(int totalFcost) {
		this.totalFcost = totalFcost;
	}
	
	/**
	 * Get the G cost of the graph.
	 * @return The G cost of the graph.
	 */
	public int getTotalGcost() {
		return this.totalGcost;
	}
	
	/**
	 * Set the G cost of the graph.
	 * @param totalGcost The G cost of the graph.
	 */
	public void setTotalGcost(int totalGcost) {
		this.totalGcost = totalGcost;
	}
	
	/**
	 * Get the H cost of the graph.
	 * @return The H cost of the graph.
	 */
	public int getTotalHcost() {
		return this.totalHcost;
	}
	
	/**
	 * Set the H cost of the graph.
	 * @param totalHcost The H cost of the graph.
	 */
	public void setTotalHcost(int totalHcost) {
		this.totalHcost = totalHcost;
	}
	
	/**
	 * Get the last visited node.
	 * @return The last visited node.
	 */
	public Node getLastVisited() {
		return this.lastVisited;
	}

	/**
	 * Set the last visited node.
	 * @param lastVisited The last visited node.
	 */
	public void setLastVisited(Node lastVisited) {
		this.lastVisited = lastVisited;
	}
	
	/**
	 * Get the route taken by the graph.
	 * @return The route taken.
	 */
	public ArrayList<Node> getRouteTaken() {
		return this.routeTaken;
	}
	
	/**
	 * Set the route taken by the graph.
	 * @param routeTaken The route taken.
	 */
	public void setRouteTaken(ArrayList<Node> routeTaken) {
		this.routeTaken = routeTaken;
	}
	
	/**
	 * Get the list of jobs remaining.
	 * @return The list of jobs remaining.
	 */
	public ArrayList<Job> getJobsRemaining() {
		return this.jobsRemaining;
	}
	
	/**
	 * Set the list of jobs remaining.
	 * @param jobsRemaining The list of jobs remaining.
	 */
	public void setJobsRemaining(ArrayList<Job> jobsRemaining) {
		this.jobsRemaining = jobsRemaining;
	}
	
	/**
	 * Compare two graphs based on their F costs.
	 */
	public int compare(Graph x, Graph y) {
		return (x.getTotalFcost()-y.getTotalFcost());
	}
}

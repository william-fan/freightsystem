import java.util.ArrayList;
import java.util.PriorityQueue;

public class GraphSystem {
	private ArrayList<Node> nodeList = new ArrayList<Node>();
	private ArrayList<Edge> edgeList = new ArrayList<Edge>();
	private ArrayList<Job> jobList = new ArrayList<Job>();
	
	/**
	 * Get the list of nodes.
	 * @return The list of nodes.
	 */
	public ArrayList<Node> getNodeList() {
		return this.nodeList;
	}
	
	/**
	 * Set the list of nodes.
	 * @param nodeList The list of nodes.
	 */
	public void setNodeList(ArrayList<Node> nodeList) {
		this.nodeList = nodeList;
	}
	
	/**
	 * Get the list of edges.
	 * @return The list of edges.
	 */
	public ArrayList<Edge> getEdgeList() {
		return this.edgeList;
	}
	
	/**
	 * Set the list of edges.
	 * @param edgeList The list of edges.
	 */
	public void setEdgeList(ArrayList<Edge> edgeList) {
		this.edgeList = edgeList;
	}
	
	/**
	 * Get the list of jobs.
	 * @return The list of jobs.
	 */
	public ArrayList<Job> getJobList() {
		return this.jobList;
	}
	
	/**
	 * Set the list of jobs.
	 * @param jobList The list of jobs.
	 */
	public void setJobList(ArrayList<Job> jobList) {
		this.jobList = jobList;
	}
	
	/**
	 * Set the unloading cost of a new node and add to nodeList.
	 * @param input The array of strings read as input.
	 */
	public void unloadingCost(String[] input) {
		Node newNode = new Node(input[2], Integer.parseInt(input[1]));
		nodeList.add(newNode);
	}
	
	/**
	 * Set the travel cost of a new edge and add to edgeList.
	 * @param input The array of strings read as input.
	 */
	public void travelCost(String[] input) {
		Node nodeA = findNode(input[2]);
		Node nodeB = findNode(input[3]);
		if (nodeA == null) {  //if no nodes were not specified previously, assume 0 cost
			nodeA = new Node(input[2], 0);
			nodeList.add(nodeA);
		}
		if (nodeB == null) {
			nodeB = new Node(input[3], 0);
			nodeList.add(nodeB);
		}
		Edge newEdge = new Edge(nodeA, nodeB, Integer.parseInt(input[1]));
		edgeList.add(newEdge);
	}
	
	/**
	 * Find a node with specified name.
	 * @param name Name of the node we wish to find.
	 * @return The node with the specified name, returns null if no specified nodes found.
	 */
	public Node findNode(String name) {
		for (Node node : nodeList) {
			if (node.getName().equals(name)) {
				return node;
			}
		}
		return null;
	}
	
	/**
	 * Find an edge with two specified nodes.
	 * @param a A node that lies on the edge.
	 * @param b A node that lies on the edge.
	 * @return The edge with the specified nodes, otherwise returns null if edge is not found.
	 */
	public Edge findEdge(Node a, Node b) {
		for (Edge edge : edgeList) {
			if ((edge.getA().equals(a)&&edge.getB().equals(b))||(edge.getA().equals(b)&&edge.getB().equals(a))) {
				return edge;
			}
		}
		return null;
	}
	
	/**
	 * Create a new job with specified nodes.
	 * @param input The array of strings read as input.
	 */
	public void newJob(String[] input) {
		Node nodeA = findNode(input[1]);
		Node nodeB = findNode(input[2]);
		Job newJob = new Job(nodeA, nodeB);
		jobList.add(newJob);
	}
	
	/**
	 * Create a list of edges that are connected to a specific node.
	 * @param node The node we wish to find connecting edges from.
	 * @return The list of edges that are connected to the node.
	 */
	public ArrayList<Edge> connectingEdges(Node node) {
		ArrayList<Edge> connectedEdges = new ArrayList<Edge>();
		for (Edge tempEdge : edgeList) {
			if (tempEdge.getA().equals(node) || tempEdge.getB().equals(node)) {
				connectedEdges.add(tempEdge);
			}
		}
		return connectedEdges;
	}
	
	/**
	 * Search the map using A* to find an optimal graph and route that completes all jobs.
	 * Also prints how many nodes expanded and total route cost.
	 * @param start The node we wish to start from.
	 * @return The list of nodes that represents the optimal route, returns null if no proper routes found.
	 */
	public ArrayList<Node> searchGraph(Node start) {
		PriorityQueue<Graph> graphList = new PriorityQueue<Graph>(new Graph(0, 0, 0, null, null, null));
		int expanded = 0;
		ArrayList <Node> startingNode = new ArrayList<Node>();
		startingNode.add(start);
		//check if jobs are connected by an edge first, or if any jobs exist
		if (totalJobCost() == 0) {
			return null;
		}
		Graph startingGraph = new Graph(totalJobCost(), 0, totalJobCost(), start, startingNode, jobList);
		graphList.add(startingGraph);
		while (!graphList.isEmpty()) {
			Graph graph = graphList.remove();
			expanded++;
			for (Node neighbour : getNeighbourList(graph.getLastVisited())) { //check each neighbour
				ArrayList <Job> newJobsRemaining = new ArrayList<Job>(graph.getJobsRemaining());
				int tempGcost = getCostBetween(graph.getLastVisited(), neighbour, newJobsRemaining);
				int tempHcost = calculateHeuristic(graph.getLastVisited(), neighbour, newJobsRemaining);
				Job tempJob = findJob(graph.getLastVisited(), neighbour, newJobsRemaining);
				if (tempJob != null) { //if job exists then we completed it
					newJobsRemaining.remove(tempJob);
				}
				int gCost = graph.getTotalGcost() + tempGcost;
				int hCost = graph.getTotalHcost() + tempHcost;
				int fCost = gCost + hCost;
				ArrayList<Node> newRouteTaken = new ArrayList<Node>(graph.getRouteTaken());
				newRouteTaken.add(neighbour);
				Graph newGraph = new Graph (fCost, gCost, hCost, neighbour, newRouteTaken, newJobsRemaining);
				graphList.add(newGraph);
				if (hCost == 0 || graph.getJobsRemaining().isEmpty()) { //if all jobs completed
					int newCost = graph.getTotalFcost(); //add the unload costs
					for (Job job : jobList) {
						newCost += job.getB().getUnloadCost();
					}
					System.out.println(expanded + " nodes expanded\ncost = " + newCost);
					return newGraph.getRouteTaken();
				}
			}
		}
		return null;
	}
	
	/**
	 * Find a specific job.
	 * @param a The node we come from.
	 * @param b The node we arrive at.
	 * @param jobs The list of jobs we are searching.
	 * @return The specified job, returns null if it is not found.
	 */
	public Job findJob(Node a, Node b, ArrayList<Job> jobs) {
		for (Job job : jobs) {
			if (job.getA().equals(a) && job.getB().equals(b)) {
				return job;
			}
		}
		return null;
	}
	
	/**
	 * Find the total cost of all jobs and unload costs.
	 * If a job is not connected by an edge or if no jobs exist, fail by returning 0.
	 * @return The total job cost.
	 */
	public int totalJobCost() {
		int total = 0;
		for (Job job : jobList) {
			Edge edge = findEdge(job.getA(), job.getB());
			if (edge == null) {
				return 0;
			}
			total += edge.getTravelCost();
		}
		
		return total;
	}
	
	/**
	 * Calculate the heuristic cost from current to neighbour, based on the jobs remaining.
	 * When we complete a job during a route, return a negative heuristic based on the cost of the job.
	 * @param current The node we are at.
	 * @param neighbour The neighbour node we are checking.
	 * @param jobsRemaining The list of jobs that are remaining.
	 * @return The heuristic cost.
	 */
	public int calculateHeuristic(Node current, Node neighbour, ArrayList<Job> jobsRemaining) {
		for (Job job : jobsRemaining) {
			if ((job.getA().equals(current) && job.getB().equals(neighbour))) {
				return (-getCostBetween(current, neighbour, jobsRemaining));
			}
		}
		return 0;
	}
	
	/**
	 * Get the travel cost from the current node to the neighbour node, and add the unload cost if it is a job.
	 * @param current The node we are at.
	 * @param neighbour The neighbour node we are checking.
	 * @param jobsRemaining The list of jobs that are remaining.
	 * @return The total cost from current to neighbour.
	 */
	public int getCostBetween(Node current, Node neighbour, ArrayList<Job> jobsRemaining){
		int cost = 0;
		Edge edge = findEdge(current, neighbour);
		cost = edge.getTravelCost();
		return cost;
	}
	
	/**
	 * Get the list of nodes that are connected to the specified node.
	 * @param node The specified node.
	 * @return The list of nodes that are connected to the specified node.
	 */
	public ArrayList<Node> getNeighbourList(Node node) {
		ArrayList<Edge> edgeNeighbour = connectingEdges(node);
		ArrayList<Node> nodeNeighbour = new ArrayList<Node>();
		for (Edge edge : edgeNeighbour) {
			if (edge.getA().equals(node)) {
				nodeNeighbour.add(edge.getB());
			} else {  //must be one or the other as connectingEdges gets connected edges.
				nodeNeighbour.add(edge.getA());
			}
		}
		return nodeNeighbour;
	}
	
	/**
	 * Set the start node and create the proper output for the route taken.
	 */
	public void calculateRoute() {
		ArrayList<Node> route = searchGraph(findNode("Sydney"));  //set starting point
		if (route == null) {
			System.out.println("No solution");
			return;
		}
		//print proper output based on if it is a job
		for (int count = 1; count != route.size(); count++) {
			if (findJob(route.get(count-1), route.get(count), jobList) != null) {
				System.out.println("Job "+route.get(count-1).getName()+" to "+route.get(count).getName());
			} else {
				System.out.println("Empty "+route.get(count-1).getName()+" to "+route.get(count).getName());
			}
		}
	}
}

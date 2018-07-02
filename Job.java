
public class Job {
	private Node a;
	private Node b;
	
	/**
	 * @param a The first node of the job.
	 * @param b The second node of the job.
	 */
	public Job (Node a, Node b){
		this.a = a;
		this.b = b;
	}

	/**
	 * Get the first node of the job.
	 * @return The first node of the job.
	 */
	public Node getA() {
		return this.a;
	}
	
	/**
	 * Set the first node of the job.
	 * @param a The first node of the job.
	 */
	public void setA(Node a) {
		this.a = a;
	}
	
	/**
	 * Get the second node of the job.
	 * @return The second node of the job.
	 */
	public Node getB() {
		return this.b;
	}

	/**
	 * Set the second node of the job.
	 * @param b The second node of the job.
	 */
	public void setB(Node b) {
		this.b = b;
	}

}

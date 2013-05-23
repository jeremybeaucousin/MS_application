package model.base;

public interface DatabaseActions {
	/**
	 * Used for insertion requests.
	 */
	public String INSERT_INTO = "INSERT INTO";
	
	/**
	 * Used for update requests.
	 */
	public String UPDATE = "UPDATE";
	
	/**
	 * Used for insertion requests.
	 */
	public String SELECT = "SELECT";

	/**
	 * Allow to insert this Object in the database
	 */
	public void insert();

	/**
	 * Allow to update this Object in the database
	 */
	public void update();

	/**
	 * Allow to select this Object in the database
	 */
	public void select(String... test);

	/**
	 * Allow to delete this Object in the database
	 */
	public void delete();

}

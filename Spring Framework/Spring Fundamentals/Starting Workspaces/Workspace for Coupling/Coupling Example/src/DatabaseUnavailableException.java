
public class DatabaseUnavailableException extends RuntimeException 
{
	public DatabaseUnavailableException(Throwable root)
	{
		super(root);
	}
	
	public DatabaseUnavailableException()
	{
		super();
	}
}

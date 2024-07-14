package cityparking.model;

public class BadTimeIntervalException extends Exception
{

	private static final long serialVersionUID = 1L;

	public BadTimeIntervalException()
	{
	}

	public BadTimeIntervalException(String arg0)
	{
		super(arg0);
	}

	public BadTimeIntervalException(Throwable arg0)
	{
		super(arg0);
	}

	public BadTimeIntervalException(String arg0, Throwable arg1)
	{
		super(arg0, arg1);
	}

}

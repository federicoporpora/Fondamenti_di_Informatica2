package cityparking.controller;

public class BadTimeFormatException extends Exception
{

	private static final long serialVersionUID = 1L;

	public BadTimeFormatException()
	{
	}

	public BadTimeFormatException(String arg0)
	{
		super(arg0);
	}

	public BadTimeFormatException(Throwable arg0)
	{
		super(arg0);
	}

	public BadTimeFormatException(String arg0, Throwable arg1)
	{
		super(arg0, arg1);
	}

}

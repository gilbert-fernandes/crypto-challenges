public enum EXITCODE
{
    OK(0, "Standard exit without error"),
	FILEOPEN_ERROR(1,  "error : could not open input file");
	
	private final int    _code;
	private final String _description;
	
	private EXITCODE(int code, String description)
	{
		_code = code;
		_description = description;
	}
	
    public int getCode()
    {
        return _code;
    }
	          
	public String getMessage()
	{
	    return _description;
	}
	
	@Override
	public String toString()
	{
	    return _code + " : " + _description;
	}
	
}

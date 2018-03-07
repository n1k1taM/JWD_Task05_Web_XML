package by.tc.task05.service;

public class ServiceException extends Exception {

	/**
	 * 
	 */
	private static final long serialVersionUID = 1L;

	public ServiceException() {
		super();
	}

	public ServiceException(String massege, Throwable exception) {
		super(massege, exception);
	}

	public ServiceException(String massege) {
		super(massege);
	}

	public ServiceException(Throwable massege) {
		super(massege);
	}
	
	
	
}

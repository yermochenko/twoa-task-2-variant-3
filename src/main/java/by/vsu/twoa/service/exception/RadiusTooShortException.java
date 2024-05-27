package by.vsu.twoa.service.exception;

public class RadiusTooShortException extends ServiceException {
	public RadiusTooShortException() {
		super("Given radius is too short");
	}
}

package exception;

public class MyException extends Exception {

	public MyException() {

		super();

		}

		public MyException(String msg) {

		 super(msg);

		}

		public MyException(String msg, Throwable cause) {

		super(msg, cause);

		}

		public MyException(Throwable cause) {

		super(cause);

		}
}

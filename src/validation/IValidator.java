package validation;

import java.util.ArrayList;

public interface IValidator<T> {
	boolean isValid(T entity);
	ArrayList<String> brokenRules(T entity);
}

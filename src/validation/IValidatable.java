package validation;

import java.util.ArrayList;

public interface IValidatable<T> {
    boolean Validate(IValidator<T> validator, ArrayList<String> brokenRules);
}

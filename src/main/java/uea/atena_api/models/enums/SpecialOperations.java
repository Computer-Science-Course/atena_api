package uea.atena_api.models.enums;

import java.util.Arrays;

public enum SpecialOperations {
	DELETE_RELETED;

	public static SpecialOperations findByName(String operation) {
		return Arrays.stream(values()).filter(operationFiltered -> operationFiltered.toString() == operation)
				.findFirst().orElseThrow(IllegalArgumentException::new);
	}
}

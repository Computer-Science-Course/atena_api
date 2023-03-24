package uea.atena_api.models;

import java.io.Serializable;

import uea.atena_api.models.enums.SpecialOperations;

public class SpecialOperation implements Serializable {

	private static final long serialVersionUID = 1L;
	private SpecialOperations specialOperation;

	public SpecialOperation() {
	}

	public SpecialOperation(SpecialOperations specialOperation) {
		super();
		this.specialOperation = specialOperation;
	}

	public SpecialOperations getSpecialOperation() {
		return specialOperation;
	}

	public void setSpecialOperation(SpecialOperations specialOperation) {
		this.specialOperation = specialOperation;
	}

}

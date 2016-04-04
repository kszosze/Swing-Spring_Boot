package com.dev.backend.exception;

import java.io.Serializable;

public class BackendError implements Serializable {

	/**
	 * 
	 */
	private static final long serialVersionUID = -5595210551365681592L;
	private String errorCode;
	private String description;

	public BackendError() {

	}

	public BackendError(String errorCode, String description) {
		this.errorCode = errorCode;
		this.description = description;
	}

	public String getErrorCode() {
		return errorCode;
	}

	public void setErrorCode(String errorCode) {
		this.errorCode = errorCode;
	}

	public String getDescription() {
		return description;
	}

	public void setDescription(String description) {
		this.description = description;
	}

	@Override
	public int hashCode() {
		final int prime = 31;
		int result = 1;
		result = prime * result + ((description == null) ? 0 : description.hashCode());
		result = prime * result + ((errorCode == null) ? 0 : errorCode.hashCode());
		return result;
	}

	@Override
	public boolean equals(Object obj) {
		if (this == obj)
			return true;
		if (obj == null)
			return false;
		if (getClass() != obj.getClass())
			return false;
		BackendError other = (BackendError) obj;
		if (description == null) {
			if (other.description != null)
				return false;
		} else if (!description.equals(other.description))
			return false;
		if (errorCode == null) {
			if (other.errorCode != null)
				return false;
		} else if (!errorCode.equals(other.errorCode))
			return false;
		return true;
	}

	@Override
	public String toString() {
		return "BackendError [errorCode=" + errorCode + ", description=" + description + "]";
	}

}

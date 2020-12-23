package com.backend.fuseapi;

import java.io.Serializable;

public interface IResultCode extends Serializable {

	String getMessage();

    int getCode();
}

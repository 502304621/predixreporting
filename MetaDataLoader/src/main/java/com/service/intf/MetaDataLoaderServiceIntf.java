package com.service.intf;

import java.util.ArrayList;
import java.util.HashMap;

import org.springframework.stereotype.Repository;

import com.model.DatabaseDefinition;

public interface MetaDataLoaderServiceIntf {


	public   HashMap<String, Object>
	getSchemaTabDetails(DatabaseDefinition databaseDefinition);
}

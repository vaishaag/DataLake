/**
 *
 */
package com.bigdata.datalake.location;

import com.bigdata.datalake.jsonobject.SchemaDetails;

/**
 * @author vsnair
 */
public abstract class DataStoreManager {

    public abstract int storeData(SchemaDetails details);
}

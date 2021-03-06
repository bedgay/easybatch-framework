package org.easybatch.integration.mongodb;

import com.mongodb.DBObject;
import org.easybatch.core.api.Header;
import org.easybatch.core.record.GenericRecord;

/**
 * Record having a Mongo {@link DBObject} as payload .
 *
 * @author Mahmoud Ben Hassine (mahmoud@benhassine.fr)
 */
public class MongoDBRecord extends GenericRecord<DBObject> {

    public MongoDBRecord(final Header header, DBObject payload) {
        super(header, payload);
    }

}

package liquibase.ext.spanner.snapshot.jvm;

import liquibase.CatalogAndSchema;
import liquibase.database.AbstractJdbcDatabase;
import liquibase.database.Database;
import liquibase.database.ObjectQuotingStrategy;
import liquibase.database.jvm.JdbcConnection;
import liquibase.diff.compare.DatabaseObjectComparatorFactory;
import liquibase.exception.DatabaseException;
import liquibase.ext.spanner.ICloudSpanner;
import liquibase.snapshot.*;
import liquibase.snapshot.jvm.ForeignKeySnapshotGenerator;
import liquibase.snapshot.jvm.SchemaSnapshotGenerator;
import liquibase.structure.DatabaseObject;
import liquibase.structure.core.Catalog;
import liquibase.structure.core.ForeignKey;
import liquibase.structure.core.Schema;
import liquibase.util.JdbcUtil;

import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.ArrayList;
import java.util.List;

public class CloudSpannerSchemaSnapshotGenerator extends SchemaSnapshotGenerator {

    @Override
    public int getPriority(Class<? extends DatabaseObject> objectClass, Database database) {
        if (database instanceof ICloudSpanner) {
            return PRIORITY_DATABASE;
        }
        return PRIORITY_NONE;
    }



    @Override
    public DatabaseObject snapshot(DatabaseObject example, DatabaseSnapshot snapshot, SnapshotGeneratorChain chain) throws DatabaseException, InvalidExampleException {

        // Check if the object is an instance of Schema
        if (example instanceof Schema) {
            // Remove the catalog attribute
            example.getAttributes().remove("catalog");
        }
        // Continue with the default behavior for other types
        return super.snapshot(example, snapshot, chain);
    }

    @Override
    public Class<? extends SnapshotGenerator>[] replaces() {
        return new Class[]{SchemaSnapshotGenerator.class};
    }

    @Override
    protected DatabaseObject snapshotObject(DatabaseObject example, DatabaseSnapshot snapshot) throws DatabaseException, InvalidExampleException {
        Schema schema = new Schema((String) null, "DEFAULT");
        // Remove the catalog attribute
        example.getAttributes().remove("catalog");

        return schema;
    }
}

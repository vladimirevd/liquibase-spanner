package liquibase.ext.spanner.snapshotgenerator;


import liquibase.diff.compare.DatabaseObjectCollectionComparator;
import liquibase.exception.UnexpectedLiquibaseException;
import liquibase.ext.spanner.ICloudSpanner;
import liquibase.serializer.LiquibaseSerializable;
import liquibase.serializer.SnapshotSerializer;
import liquibase.serializer.core.yaml.YamlSnapshotSerializer;
import liquibase.structure.DatabaseObject;
import liquibase.structure.DatabaseObjectCollection;
import liquibase.structure.core.Column;
import liquibase.structure.core.Schema;
import liquibase.util.ISODateFormat;
import liquibase.util.StringUtil;

import java.util.*;


public class CloudSpannerYamlSnapshotSerializer extends YamlSnapshotSerializer {
    private boolean alreadySerializingObject;

    @Override
    public int getPriority() {

        return PRIORITY_DATABASE + 10;
    }


    @Override
    public Object toMap(final LiquibaseSerializable object) {
        // Custom serialization logic for Cloud Spanner
        if (object instanceof DatabaseObject) {
            if (object instanceof Column && ((Column) object).isForIndex()) {
                //not really a "real" column that has a snapshot to reference, just serialize the ColumnObject
                return super.toMap(object);
            } else if (alreadySerializingObject) {
                String snapshotId = ((DatabaseObject) object).getSnapshotId();
                if (snapshotId == null) {
                    String name = ((DatabaseObject) object).getName();
                    Object table = ((DatabaseObject) object).getAttribute("table", Object.class);
                    if (table == null) {
                        table = ((DatabaseObject) object).getAttribute("relation", Object.class);
                    }
                    if (table != null) {
                        name = table + "." + name;
                    }

                    if (((DatabaseObject) object).getSchema() != null) {
                        name = ((DatabaseObject) object).getSchema().toString() + "." + name;
                    }
                    // Not throw exception if database is spanner
                    if (!(object instanceof ICloudSpanner && object instanceof Schema)) {
                        throw new UnexpectedLiquibaseException("Found a null snapshotId for " + StringUtil.lowerCaseFirst(object.getClass().getSimpleName()) + " " + name);
                    }

                }
                return ((DatabaseObject) object).getClass().getName() + "#" + snapshotId;
            } else {
                alreadySerializingObject = true;
                Object map = super.toMap(object);
                alreadySerializingObject = false;
                return map;
            }
        }
        if (object instanceof DatabaseObjectCollection) {
            SortedMap<String, Object> returnMap = new TreeMap<>();
            for (Map.Entry<Class<? extends DatabaseObject>, Set<? extends DatabaseObject>> entry : ((DatabaseObjectCollection) object).toMap().entrySet()) {
                ArrayList value = new ArrayList(entry.getValue());
                value.sort(new DatabaseObjectCollectionComparator());
                returnMap.put(entry.getKey().getName(), value);
            }
            return returnMap;
        }
        return super.toMap(object);
    }

}

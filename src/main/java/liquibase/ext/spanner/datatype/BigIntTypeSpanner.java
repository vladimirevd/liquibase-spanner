/**
 * Copyright 2020 Google LLC
 *
 * <p>Licensed under the Apache License, Version 2.0 (the "License"); you may not use this file
 * except in compliance with the License. You may obtain a copy of the License at
 *
 * <p>https://www.apache.org/licenses/LICENSE-2.0
 *
 * <p>Unless required by applicable law or agreed to in writing, software distributed under the
 * License is distributed on an "AS IS" BASIS, WITHOUT WARRANTIES OR CONDITIONS OF ANY KIND, either
 * express or implied. See the License for the specific language governing permissions and
 * limitations under the License.
 */
package liquibase.ext.spanner.datatype;

import com.google.cloud.spanner.Dialect;
import liquibase.database.Database;
import liquibase.datatype.DatabaseDataType;
import liquibase.datatype.core.BigIntType;
import liquibase.ext.spanner.ICloudSpanner;

public class BigIntTypeSpanner extends BigIntType {
  private static final DatabaseDataType INT64 = new DatabaseDataType("INT64");
  private static final DatabaseDataType BIGINT = new DatabaseDataType("BIGINT");

  @Override
  public boolean supports(Database database) {
    return database instanceof ICloudSpanner;
  }

  @Override
  public DatabaseDataType toDatabaseDataType(Database database) {
    Dialect dialect = ((ICloudSpanner) database).getDialect();

    if (dialect == Dialect.GOOGLE_STANDARD_SQL) {
      return INT64;
    } else if (dialect == Dialect.POSTGRESQL) {
      return BIGINT;
    }
      return super.toDatabaseDataType(database);
  }

  @Override
  public int getPriority() {
    return PRIORITY_DATABASE;
  }
}

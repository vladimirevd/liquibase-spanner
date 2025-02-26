/**
 * Copyright 2021 Google LLC
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
package liquibase.ext.spanner.change;

import liquibase.change.ChangeMetaData;
import liquibase.change.DatabaseChange;
import liquibase.change.core.CreateTableChange;
import liquibase.database.Database;
import liquibase.ext.spanner.ICloudSpanner;
import liquibase.ext.spanner.sqlgenerator.CreateTableStatementSpanner;

/**
 * Custom implementation for Cloud Spanner that enables the creation of nullable primary key
 * columns.
 */
@DatabaseChange(
    name = "createTable",
    description = "Create Table",
    priority = ChangeMetaData.PRIORITY_DATABASE)
public class CreateTableChangeSpanner extends CreateTableChange {

  @Override
  public boolean supports(Database database) {
    return (database instanceof ICloudSpanner);
  }

  @Override
  protected CreateTableStatementSpanner generateCreateTableStatement() {
    return new CreateTableStatementSpanner(
        getCatalogName(), getSchemaName(), getTableName(), getRemarks(), getTableType());
  }
}

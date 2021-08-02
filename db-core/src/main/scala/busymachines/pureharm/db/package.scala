/*
 * Copyright 2019 BusyMachines
 *
 * Licensed under the Apache License, Version 2.0 (the "License");
 * you may not use this file except in compliance with the License.
 * You may obtain a copy of the License at
 *
 *     http://www.apache.org/licenses/LICENSE-2.0
 *
 * Unless required by applicable law or agreed to in writing, software
 * distributed under the License is distributed on an "AS IS" BASIS,
 * WITHOUT WARRANTIES OR CONDITIONS OF ANY KIND, either express or implied.
 * See the License for the specific language governing permissions and
 * limitations under the License.
 */

package busymachines.pureharm

import busymachines.pureharm.sprout._

/** @author
  *   Lorand Szakacs, https://github.com/lorandszakacs
  * @since 13
  *   Jun 2019
  */
package object db {
  final object DBHost extends SproutSub[String]

  /** Do not include port in the hostname, configured via DBPort
    */
  final type DBHost = DBHost.Type

  final object DBPort extends SproutSub[Int]
  final type DBPort = DBPort.Type

  final object JDBCUrl extends SproutSub[String] {

    def postgresql(host: DBHost, port: DBPort, db: DatabaseName): this.Type =
      this.apply(s"jdbc:postgresql://$host:$port/$db")

    def postgresql(host: DBHost, port: DBPort, db: DatabaseName, schema: SchemaName): this.Type =
      this.apply(s"jdbc:postgresql://$host:$port/$db?currentSchema=$schema")
  }

  final type JDBCUrl = JDBCUrl.Type

  final object DBUsername extends SproutSub[String]
  final type DBUsername = DBUsername.Type

  final object DBPassword extends SproutSub[String]
  final type DBPassword = DBPassword.Type

  final object TableName extends SproutSub[String]
  final type TableName = TableName.Type

  final object DatabaseName extends SproutSub[String]
  final type DatabaseName = DatabaseName.Type

  final object SchemaName extends SproutSub[String] {
    def public: SchemaName = SchemaName("public")
  }
  final type SchemaName = SchemaName.Type
}

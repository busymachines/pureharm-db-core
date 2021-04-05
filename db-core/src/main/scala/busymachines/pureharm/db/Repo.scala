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

package busymachines.pureharm.db

import cats.{Show, Traverse}

/** @tparam E
  *   The type of elements manipulated by this DAO
  * @tparam PK
  *   The "primary key", or "id" if you will, by which elements
  *   of type `E` are identified.
  *
  * @author Lorand Szakacs, https://github.com/lorandszakacs
  * @since 04 Apr 2019
  */
trait Repo[R[_], E, PK] {
  def find(pk: PK): R[Option[E]]

  /** Required to render the PK to return in anomaly
    *
    * @return
    *  Fails with DBEntryNotFoundAnomaly when
    *  the entry is not found
    */
  def retrieve(pk: PK)(implicit show: Show[PK]): R[E]

  def insert(e: E): R[PK]

  @scala.deprecated("Will be removed in 0.3.0", "0.2.0")
  def insertMany(es: Iterable[E]): R[Unit]

  def update(e: E): R[E]

  @scala.deprecated("Will be removed in 0.3.0", "0.2.0")
  def updateMany[M[_]: Traverse](es: M[E]): R[Unit]

  def delete(pk: PK): R[Unit]

  @scala.deprecated("Will be removed in 0.3.0", "0.2.0")
  def deleteMany(pks: Iterable[PK]): R[Unit]

  def exists(pk: PK): R[Boolean]

  @scala.deprecated("Will be removed in 0.3.0", "0.2.0")
  def existsAtLeastOne(pks: Iterable[PK]): R[Boolean]

  @scala.deprecated("Will be removed in 0.3.0", "0.2.0")
  def existAll(pks: Iterable[PK]): R[Boolean]
}

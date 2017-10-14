package io.shedin.crud.lib

import scala.concurrent.ExecutionContext.Implicits.global
import scala.concurrent.Future

trait AsyncCrudOperations[T] {

  def create(resource: T): Future[T]

  def read(resourceId: String): Future[Option[T]]

  def update(resourceId: String, resource: T): Future[Option[T]]

  def update(resourceId: String, updatePayload: String): Future[Option[AnyRef]]

  def delete(resourceId: String): Future[Boolean]

  def exists(resourceId: String): Future[Boolean] = read(resourceId) map (_.isDefined) // TODO use a self type instead

}
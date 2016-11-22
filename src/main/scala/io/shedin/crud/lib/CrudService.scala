package io.shedin.crud.lib

import scala.concurrent.Future

abstract class CrudService[T]
(crudRepository: CrudRepository[T]) extends AsyncCrudOperations[T] {

  override def create(resource: T): Future[T] =
    crudRepository.create(resource)

  override def read(resourceId: String): Future[Option[T]] =
    crudRepository.read(resourceId)

  override def update(resourceId: String, resource: T): Future[Option[T]] =
    crudRepository.update(resourceId, resource)

  override def update(resourceId: String, updatePayload: String): Future[Option[AnyRef]] =
    crudRepository.update(resourceId, updatePayload)

  override def delete(resourceId: String): Future[Boolean] =
    crudRepository.delete(resourceId)

}

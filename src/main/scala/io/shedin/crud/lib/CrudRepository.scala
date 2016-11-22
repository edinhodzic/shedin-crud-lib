package io.shedin.crud.lib

import scala.concurrent.Future

// TODO can probably delete this now?
// TODO add execution context here and remove global import in UserCrudRepository and MongoRepository
trait CrudRepository[T] extends AsyncCrudOperations[T] {

  override def create(resource: T): Future[T]

  override def read(resourceId: String): Future[Option[T]]

  override def update(resourceId: String, resource: T): Future[Option[T]]

  override def update(resourceId: String, updatePayload: String): Future[Option[AnyRef]]

  override def delete(resourceId: String): Future[Boolean]

}

package io.shedin.crud.lib

// TODO can probably delete this now?
// TODO add execution context here and remove global import in UserCrudRepository and MongoRepository
trait CrudRepository[T] extends AsyncCrudOperations[T]
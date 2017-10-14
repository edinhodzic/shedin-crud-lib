[![Build Status](https://travis-ci.org/edinhodzic/shedin-crud-lib.svg?branch=master)](https://travis-ci.org/edinhodzic/shedin-crud-lib)

# About

Asynchronous CRUD operations are modelled in the `AsyncCrudOperations` trait.

```scala
trait AsyncCrudOperations[T] {

  def create(resource: T): Future[T]

  def read(resourceId: String): Future[Option[T]]

  def update(resourceId: String, resource: T): Future[Option[T]]

  def update(resourceId: String, updatePayload: String): Future[Option[AnyRef]]

  def delete(resourceId: String): Future[Boolean]

}
```

Those CRUD operations apply to repositories as modelled by the `CrudRepository` trait.

```scala
trait CrudRepository[T] extends AsyncCrudOperations[T]
```

A CRUD service would delegate to a repository to do the low-level work as modelled by the abstract `CrudService` class.

```scala
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
```

# Usage

Following on from the above and supposing we have a domain object modelling a user:

```scala
case class User(data: String)
```

The below describes a step-by-step process of using this library to create a user repository and service.

## Step 1 - write a generic repository implementation

We implement a generic repository based on a particular persistence technology.

```scala
class MongoCrudRepository[T] extends CrudRepository[T] {
  ...  
}
```

## Step 2 - write a specific repository implementation

We implement a repository specific to our domain model(s).

```scala
class UserCrudRepository extends MongoCrudRepository[User]
```

## Step 3 - write a service implementation

```scala
abstract class UserCrudService
(userCrudRepository: UserCrudRepository) extends CrudService[User]
```

# License

This code is open source software licensed under the [Apache 2.0 License](http://www.apache.org/licenses/LICENSE-2.0.html).

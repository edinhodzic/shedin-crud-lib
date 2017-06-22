package io.shedin.crud.lib

import org.mockito.Mockito.{verify, when}
import org.scalatest.FlatSpec
import org.scalatest.mockito.MockitoSugar

import scala.concurrent.Await.result
import scala.concurrent.Future
import scala.concurrent.Future.successful
import scala.concurrent.duration.{Duration, SECONDS}

class CrudServiceSpec extends FlatSpec with MockitoSugar {

  class CrudServiceTestImpl(crudRepository: CrudRepository[Int]) extends CrudService[Int](crudRepository)

  private val crudRepository = mock[CrudRepository[Int]]
  private val crudService = new CrudServiceTestImpl(crudRepository)
  private val resource = 123
  private val resourceId = "resourceId"

  private implicit val timeout = Duration(2, SECONDS)

  def await[T](future: Future[T])(implicit timeout: Duration) = result(future, timeout)

  "Crud service" should "delegate creation to a repository" in {
    when(crudRepository.create(resource)).thenReturn(successful(resource))
    await(crudService.create(resource))
    verify(crudRepository).create(resource)
  }

  it should "delegate reading to a repository" in {
    when(crudRepository.read(resourceId)).thenReturn(successful(None))
    await(crudService.read(resourceId))
    verify(crudRepository).read(resourceId)
  }

  it should "delegate whole updates to a repository" in {
    when(crudRepository.update(resourceId, resource)).thenReturn(successful(None))
    await(crudService.update(resourceId, resource))
    verify(crudRepository).update(resourceId, resource)
  }

  it should "delegate partial updates to a repository" in {
    when(crudRepository.update(resourceId, "partial payload")).thenReturn(successful(None))
    await(crudService.update(resourceId, "partial payload"))
    verify(crudRepository).update(resourceId, "partial payload")
  }

  it should "delegate deletion to a repository" in {
    when(crudRepository.delete(resourceId)).thenReturn(successful(true))
    await(crudService.delete(resourceId))
    verify(crudRepository).delete(resourceId)
  }

}

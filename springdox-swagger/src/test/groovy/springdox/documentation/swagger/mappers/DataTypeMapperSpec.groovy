package springdox.documentation.swagger.mappers

import spock.lang.Specification
import springdox.documentation.schema.ModelRef
import springdox.documentation.swagger.mixins.MapperSupport

@Mixin(MapperSupport)
class DataTypeMapperSpec extends Specification {

  def "Maps type name of model ref for responseTypeName"() {
    given:
      def sut = dataTypeMapper()
    when:
      def mapped = sut.responseTypeName(modelRef)
    then:
      mapped == typeName
    where:
      modelRef                        | typeName 
      new ModelRef("void")            | "void"
      new ModelRef("List", "String")  | "array"
      null                            | null
  }

  def "Maps type name of model ref for typeFromModelRef"() {
    given:
      def sut = dataTypeMapper()
    when:
      def mapped = sut.typeFromModelRef(modelRef)
    then:
      mapped?.absoluteType == typeName
    
    where:
      modelRef                        | typeName
      new ModelRef("void")            | "void"
      new ModelRef("List", "String")  | "array"
      null                            | null
  }

  def "Maps model ref for operationTypeFromModelRef"() {
    given:
      def sut = dataTypeMapper()
    when:
      def mapped = sut.operationTypeFromModelRef(modelRef)
    then:
      mapped?.absoluteType == typeName

    where:
      modelRef                        | typeName
      new ModelRef("void")            | "void"
      new ModelRef("List", "String")  | "array"
      null                            | null
  }
}
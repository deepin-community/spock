/*
 * Copyright 2012 the original author or authors.
 *
 * Licensed under the Apache License, Version 2.0 (the "License");
 * you may not use this file except in compliance with the License.
 * You may obtain a copy of the License at
 *     http://www.apache.org/licenses/LICENSE-2.0
 * Unless required by applicable law or agreed to in writing, software
 * distributed under the License is distributed on an "AS IS" BASIS,
 * WITHOUT WARRANTIES OR CONDITIONS OF ANY KIND, either express or implied.
 * See the License for the specific language governing permissions and
 * limitations under the License.
 */

package org.spockframework.util

import spock.lang.Specification
import spock.lang.Stepwise

class ReflectionUtilSpec extends Specification {
  def "load existing class"() {
    expect:
    ReflectionUtil.loadClassIfAvailable("java.util.List") == List
  }

  def "load unexisting class"() {
    expect:
    ReflectionUtil.loadClassIfAvailable("not.AvailableClass") == null
  }

  def "check if class exists"() {
    expect:
    ReflectionUtil.isClassAvailable("java.util.List")
    !ReflectionUtil.isClassAvailable("not.AvailableClass")
  }

  def "check if method exists"() {
    expect:
    ReflectionUtil.isMethodAvailable("java.util.List", "size")
    !ReflectionUtil.isMethodAvailable("java.util.List", "mice")
  }

  def "check if annotation of certain type is present"() {
    expect:
    ReflectionUtil.isAnnotationPresent(Stepwise, "org.spockframework.runtime.extension.ExtensionAnnotation")
    !ReflectionUtil.isAnnotationPresent(Stepwise, "foo.bar.Baz")
  }

  def "find method by name"() {
    expect:
    ReflectionUtil.getMethodByName(Derived, "baseMethod") == Base.getDeclaredMethod("baseMethod", String, int)
  }

  def "find declared method by name"() {
    expect:
    ReflectionUtil.getDeclaredMethodByName(Derived, "derivedMethod") == Derived.getDeclaredMethod("derivedMethod", String)
  }

  def "find method by name and parameter types"() {
    expect:
    ReflectionUtil.getMethodBySignature(Derived, "overloadedMethod", int) == Derived.getDeclaredMethod("overloadedMethod", int)
    ReflectionUtil.getMethodBySignature(Derived, "overloadedMethod", List) == null
  }

  def "find declared method by name and parameter types"() {
    expect:
    ReflectionUtil.getDeclaredMethodBySignature(Derived, "derivedMethod", String) == Derived.getDeclaredMethod("derivedMethod", String)
    ReflectionUtil.getDeclaredMethodBySignature(Derived, "derivedMethod", List) == null
  }

  def "find class file for a class"() {
    expect:
    def file = ReflectionUtil.getClassFile(ReflectionUtilSpec)
    file != null
    file.exists()
    file.name == "ReflectionUtilSpec.class"
  }

  def "get default value for a type"() {
    expect:
    ReflectionUtil.getDefaultValue(type) == defaultValue

    where:
    type    | defaultValue
    boolean | false
    byte    | 0
    short   | 0
    char    | 0
    int     | 0
    long    | 0
    float   | 0
    double  | 0
    Object  | null
    void    | null
    Void    | null
  }

  def "check if a value has one of several types"() {
    expect:
    ReflectionUtil.hasAnyOfTypes([1], Map, ArrayList, Set)
    ReflectionUtil.hasAnyOfTypes([1], Map, Collection, Set)
    !ReflectionUtil.hasAnyOfTypes([1], Map, Set)
    !ReflectionUtil.hasAnyOfTypes([1])
  }

  def "get types of several objects"() {
    expect:
    ReflectionUtil.getTypes([1], [a: 1]) == [ArrayList, LinkedHashMap] as Class[]
    ReflectionUtil.getTypes() == [] as Class[]
  }

  def "invoke good method"() {
    def method = InvokeMe.getDeclaredMethod("good", int)

    expect:
    ReflectionUtil.invokeMethod(new InvokeMe(), method, 42) == 42
  }

  def "invoke method that throws unchecked exception"() {
    def method = InvokeMe.getDeclaredMethod("unchecked")

    when:
    ReflectionUtil.invokeMethod(new InvokeMe(), method)

    then:
    thrown(IllegalArgumentException)
  }

  def "invoke method that throws checked exception"() {
    def method = InvokeMe.getDeclaredMethod("checked")

    when:
    ReflectionUtil.invokeMethod(new InvokeMe(), method)

    then:
    thrown(IOException)
  }

  static class Base {
    def baseMethod(String arg1, int arg2) {}
  }

  static class Derived extends Base {
    private derivedMethod(String arg) {}

    void overloadedMethod(String arg) {}

    void overloadedMethod(int arg) {}
  }

  static class InvokeMe {
    def good(int arg) { arg }
    def unchecked() { throw new IllegalArgumentException("oops") }
    def checked() { throw new IOException("ouch") }
  }
}

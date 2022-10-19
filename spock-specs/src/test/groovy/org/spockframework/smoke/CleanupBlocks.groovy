/*
 * Copyright 2009 the original author or authors.
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

package org.spockframework.smoke

import spock.lang.FailsWith
import spock.lang.Specification
import spock.lang.Issue
import org.spockframework.EmbeddedSpecification
import org.codehaus.groovy.runtime.typehandling.GroovyCastException

class CleanupBlocks extends EmbeddedSpecification {
  def "basic usage"() {
    def x
    setup: x = 1
    expect: x == 1
    cleanup: x = 0
  }

  def "may access all previously defined local vars"() {
    def a

    setup:
    def b
    when:
    String c
    then:
    List d

    cleanup:
    a = 0
    b = 0
    c = ""
    d = null
  }

  @FailsWith(IllegalArgumentException)
  def "is executed if no exception is thrown"() {
    def a = 1

    cleanup:
    a = 0
    if (a == 0) throw new IllegalArgumentException()
  }

  @FailsWith(IllegalArgumentException)
  def "is executed if exception is thrown"() {
    def a = 1
    throw new Exception()

    cleanup:
    a = 0
    if (a == 0) throw new IllegalArgumentException()
  }

  @FailsWith(IllegalArgumentException)
  def "if exception is thrown, code between occurrence of exception and cleanup-block is not executed"() {
    def a = 1
    throw new IllegalArgumentException()
    a = 2

    cleanup:
    assert a == 1
  }

  @Issue("http://issues.spockframework.org/detail?id=266")
  def "variable with primitive type can be declared in presence of cleanup-block"() {
    int x = 1

    expect:
    x == 1

    cleanup:
    []
  }

  def "variable with primitive type can be read in cleanup-block"() {
    int x = 1

    cleanup:
    x
  }

  @FailsWith(GroovyCastException)
  def "declared type of variable is kept"() {
    int x = "abc"

    cleanup:
    []
  }
}

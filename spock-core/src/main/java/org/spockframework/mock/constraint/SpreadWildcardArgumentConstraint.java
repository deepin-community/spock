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

package org.spockframework.mock.constraint;

import org.spockframework.mock.IArgumentConstraint;
import org.spockframework.runtime.InvalidSpecException;

/**
 *
 * @author Peter Niederwieser
 */
public class SpreadWildcardArgumentConstraint implements IArgumentConstraint {
  public static final SpreadWildcardArgumentConstraint INSTANCE = new SpreadWildcardArgumentConstraint();

  private SpreadWildcardArgumentConstraint() {}

  public boolean isSatisfiedBy(Object arg) {
    throw new InvalidSpecException("*_ may only appear at the end of an argument list");
  }
}

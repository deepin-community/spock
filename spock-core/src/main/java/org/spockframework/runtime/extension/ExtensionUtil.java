/*
 * Copyright 2010 the original author or authors.
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

package org.spockframework.runtime.extension;

import java.util.ArrayList;
import java.util.List;

import org.junit.internal.runners.model.MultipleFailureException;

public class ExtensionUtil {
  public static void throwAll(List<? extends Throwable> exceptions) throws Throwable {
    if (exceptions.isEmpty()) return;
    if (exceptions.size() == 1) throw exceptions.get(0);

    List<Throwable> unrolled = new ArrayList<Throwable>();
    for (Throwable exception : exceptions) {
      if (exception instanceof MultipleFailureException)
        unrolled.addAll(((MultipleFailureException) exception).getFailures());
      else unrolled.add(exception);
    }
    throw new MultipleFailureException(unrolled);
  }
}

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

package org.spockframework.mock.runtime;

import java.util.List;

import org.spockframework.mock.IMockInvocation;
import org.spockframework.mock.IMockMethod;
import org.spockframework.mock.IMockObject;

public class DelegatingMockInvocation implements IMockInvocation {
  private final IMockInvocation delegate;

  public DelegatingMockInvocation(IMockInvocation delegate) {
    this.delegate = delegate;
  }

  public IMockObject getMockObject() {
    return delegate.getMockObject();
  }

  public IMockMethod getMethod() {
    return delegate.getMethod();
  }

  public List<Object> getArguments() {
    return delegate.getArguments();
  }

  public Object callRealMethod() {
    return delegate.callRealMethod();
  }

  public Object callRealMethodWithArgs(Object... arguments) {
    return delegate.callRealMethodWithArgs(arguments);
  }
}

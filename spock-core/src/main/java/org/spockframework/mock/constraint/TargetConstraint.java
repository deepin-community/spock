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

import org.spockframework.mock.IInteractionAware;
import org.spockframework.mock.IMockInteraction;
import org.spockframework.mock.IMockInvocation;
import org.spockframework.mock.IInvocationConstraint;

/**
 * @author Peter Niederwieser
 */
public class TargetConstraint implements IInvocationConstraint, IInteractionAware {
  private final Object target;
  private IMockInteraction interaction;

  public TargetConstraint(Object target) {
    this.target = target;
  }

  public boolean isSatisfiedBy(IMockInvocation invocation) {
    return invocation.getMockObject().matches(target, interaction);
  }

  public void setInteraction(IMockInteraction interaction) {
    this.interaction = interaction;
  }
}

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

package org.spockframework.runtime.extension.builtin;

import org.junit.rules.TestRule;
import org.junit.runners.model.Statement;

import org.spockframework.runtime.extension.IMethodInvocation;
import org.spockframework.runtime.model.FieldInfo;

import java.util.List;

public class ClassRuleInterceptor extends AbstractRuleInterceptor {
  public ClassRuleInterceptor(List<FieldInfo> ruleFields) {
    super(ruleFields);
  }

  public void intercept(final IMethodInvocation invocation) throws Throwable {
    Statement stat = createBaseStatement(invocation);

    for (FieldInfo field : ruleFields) {
      TestRule rule = (TestRule) getRuleInstance(field, field.isShared() ? invocation.getSharedInstance() : invocation.getInstance());
      stat = rule.apply(stat, invocation.getSpec().getDescription());
    }

    stat.evaluate();
  }
}

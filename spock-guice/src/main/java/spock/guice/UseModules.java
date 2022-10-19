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

package spock.guice;

import java.lang.annotation.*;

import com.google.inject.Module;

import org.spockframework.guice.GuiceExtension;
import org.spockframework.runtime.extension.ExtensionAnnotation;

/**
 * Activates <a href="http://code.google.com/p/google-guice/">Guice</a> integration for a specification.
 * The specified modules will be started before and stopped after the specification's execution.
 * Services will be injected into the specification based on regular Guice annotations.
 */
@Retention(RetentionPolicy.RUNTIME)
@Target(ElementType.TYPE)
@ExtensionAnnotation(GuiceExtension.class)
public @interface UseModules {
  Class<? extends Module>[] value();
}

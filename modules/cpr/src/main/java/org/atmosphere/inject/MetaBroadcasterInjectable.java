/*
 * Copyright 2008-2022 Async-IO.org
 *
 * Licensed under the Apache License, Version 2.0 (the "License"); you may not
 * use this file except in compliance with the License. You may obtain a copy of
 * the License at
 *
 * http://www.apache.org/licenses/LICENSE-2.0
 *
 * Unless required by applicable law or agreed to in writing, software
 * distributed under the License is distributed on an "AS IS" BASIS, WITHOUT
 * WARRANTIES OR CONDITIONS OF ANY KIND, either express or implied. See the
 * License for the specific language governing permissions and limitations under
 * the License.
 */
package org.atmosphere.inject;

import org.atmosphere.cpr.AtmosphereConfig;
import org.atmosphere.cpr.MetaBroadcaster;
import org.atmosphere.inject.annotation.ApplicationScoped;

import java.lang.reflect.Type;

@ApplicationScoped
public class MetaBroadcasterInjectable implements Injectable<MetaBroadcaster> {

    @Override
    public boolean supportedType(Type t) {
        return (t instanceof Class) && MetaBroadcaster.class.isAssignableFrom((Class) t);
    }

    @Override
    public MetaBroadcaster injectable(AtmosphereConfig config) {
        return config.metaBroadcaster();
    }
}

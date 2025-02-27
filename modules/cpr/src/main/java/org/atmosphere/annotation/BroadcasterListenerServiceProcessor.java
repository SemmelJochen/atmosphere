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
package org.atmosphere.annotation;

import org.atmosphere.config.AtmosphereAnnotation;
import org.atmosphere.config.service.BroadcasterListenerService;
import org.atmosphere.cpr.AtmosphereFramework;
import org.atmosphere.cpr.Broadcaster;
import org.atmosphere.cpr.BroadcasterListener;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

import java.util.Collection;

@AtmosphereAnnotation(BroadcasterListenerService.class)
public class BroadcasterListenerServiceProcessor implements Processor<BroadcasterListener> {

    private static final Logger logger = LoggerFactory.getLogger(BroadcasterListenerServiceProcessor.class);

    @Override
    public void handle(AtmosphereFramework framework, Class<BroadcasterListener> annotatedClass) {
        try {
            BroadcasterListener l = framework.newClassInstance(BroadcasterListener.class, annotatedClass);
            framework.addBroadcasterListener(l);
            // We must reconfigure all existing Broadcaster
            Collection<Broadcaster> c = framework.getBroadcasterFactory().lookupAll();
            for (Broadcaster b : c) {
                l.onPostCreate(b);
            }
        } catch (Throwable e) {
            logger.warn("", e);
        }
    }
}

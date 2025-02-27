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

package org.atmosphere.jersey;

import com.ning.http.client.AsyncHttpClient;
import com.ning.http.client.Response;
import org.testng.annotations.Test;

import java.util.concurrent.TimeUnit;

import static org.testng.Assert.assertEquals;
import static org.testng.Assert.assertNotNull;
import static org.testng.Assert.assertTrue;
import static org.testng.Assert.fail;

public class QueryStringTest extends BaseTest {

    String getUrlTarget(int port) {
        return "http://127.0.0.1:" + port + "/jfarcand@apache.org";
    }

    @Test(timeOut = 20000, enabled = true)
    public void testSpecialCharsMapping() {
        logger.info("{}: running test:  testSpecialCharsMapping", getClass().getSimpleName());

        AsyncHttpClient c = new AsyncHttpClient();
        try {
            long t1 = System.currentTimeMillis();
            Response r = c.prepareGet(urlTarget + "/a?a=a&b=b").execute().get(10, TimeUnit.SECONDS);
            assertNotNull(r);
            assertEquals(r.getStatusCode(), 200);
            assertEquals(r.getResponseBody(), "ab");
        } catch (Exception e) {
            logger.error("test failed", e);
            fail(e.getMessage());
        }
        c.close();

    }

}

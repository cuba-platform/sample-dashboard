/*
 * Copyright (c) 2016 Haulmont
 *
 * Licensed under the Apache License, Version 2.0 (the "License");
 * you may not use this file except in compliance with the License.
 * You may obtain a copy of the License at
 *
 * http://www.apache.org/licenses/LICENSE-2.0
 *
 * Unless required by applicable law or agreed to in writing, software
 * distributed under the License is distributed on an "AS IS" BASIS,
 * WITHOUT WARRANTIES OR CONDITIONS OF ANY KIND, either express or implied.
 * See the License for the specific language governing permissions and
 * limitations under the License.
 */
package com.company.dashboard.web.bootstrap;

import com.haulmont.cuba.web.sys.CubaBootstrapListener;
import com.vaadin.server.BootstrapPageResponse;
import org.jsoup.nodes.Element;

public class BootstrapListener extends CubaBootstrapListener {

    @Override
    public void modifyBootstrapPage(BootstrapPageResponse response) {
        super.modifyBootstrapPage(response);

        Element head = response.getDocument().getElementsByTag("head").get(0);
        includeMetaViewport("width=device-width, initial-scale=0.8", response, head);
    }

    protected void includeMetaViewport(String content, BootstrapPageResponse response, Element head) {
        Element meta = response.getDocument().createElement("meta");
        meta.attr("name", "viewport");
        meta.attr("content", content);
        head.appendChild(meta);
    }
}

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
package com.company.dashboard.route;

import com.haulmont.chile.core.annotations.MetaClass;
import com.haulmont.chile.core.annotations.MetaProperty;
import com.haulmont.cuba.core.entity.BaseUuidEntity;

@MetaClass(name = "demo$Route")
public class Route extends BaseUuidEntity {
    private static final long serialVersionUID = 121463765559033258L;

    @MetaProperty
    protected String time;

    @MetaProperty
    protected String arrival;

    @MetaProperty
    protected String departure;

    public Route(String time, String departure, String arrival) {
        this.time = time;
        this.departure = departure;
        this.arrival = arrival;
    }

    public void setTime(String time) {
        this.time = time;
    }

    public String getTime() {
        return time;
    }

    public void setArrival(String arrival) {
        this.arrival = arrival;
    }

    public String getArrival() {
        return arrival;
    }

    public void setDeparture(String departure) {
        this.departure = departure;
    }

    public String getDeparture() {
        return departure;
    }
}
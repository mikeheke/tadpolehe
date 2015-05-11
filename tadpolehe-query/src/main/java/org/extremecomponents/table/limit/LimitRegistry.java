/*
 * Copyright 2004 original author or authors.
 *
 * Licensed under the Apache License, Version 2.0 (the "License");
 * you may not use this file except in compliance with the License.
 * You may obtain a copy of the License at
 *
 *    http://www.apache.org/licenses/LICENSE-2.0
 *
 * Unless required by applicable law or agreed to in writing, software
 * distributed under the License is distributed on an "AS IS" BASIS,
 * WITHOUT WARRANTIES OR CONDITIONS OF ANY KIND, either express or implied.
 * See the License for the specific language governing permissions and
 * limitations under the License.
 */
package org.extremecomponents.table.limit;

import java.util.Map;

import org.extremecomponents.table.context.Context;
import org.extremecomponents.table.core.AbstractRegistry;
import org.extremecomponents.table.state.State;

/**
 * @author Jeff Johnston
 */
public final class LimitRegistry extends AbstractRegistry {
    public LimitRegistry(Context context, String tableId, String prefixWithTableId, String state, String stateAttr) {
        this.context = context;
        this.tableId = tableId;
        this.prefixWithTableId = prefixWithTableId;
        this.state = state;
        this.stateAttr = stateAttr;
        this.autoIncludeParameters = false;
        setParameterMap();
    }
    
    /**
     * If not using the Limit then would normally save the state of the parameters. However,
     * to save the state when using the Limit is not neccessary and could introduce bugs
     * or side effects that are easily avoided.
     */
    protected void handleStateInternal(State state, Map tableParameterMap) {
        //do nothing
    }
}

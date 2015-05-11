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
package org.extremecomponents.table.state;

import java.util.HashMap;
import java.util.Iterator;
import java.util.Map;
import java.util.Set;

import org.extremecomponents.table.context.Context;
import org.extremecomponents.table.core.TableConstants;

/**
 * @author Jeff Johnston
 */
abstract class AbstractState implements State {
    public void saveParameters(Context context, String tableId, Map parameterMap) {
        Map savedAttributes = new HashMap();

        Set keys = parameterMap.keySet();
        for (Iterator iter = keys.iterator(); iter.hasNext();) {
            String key = (String) iter.next();
            Object value = parameterMap.get(key);
            savedAttributes.put(key, value);
        }

        context.setSessionAttribute(TableConstants.STATE + tableId, savedAttributes);
    }
}

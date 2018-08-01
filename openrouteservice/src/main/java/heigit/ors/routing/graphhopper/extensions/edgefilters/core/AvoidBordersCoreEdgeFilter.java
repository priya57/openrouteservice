/*
 *  Licensed to GIScience Research Group, Heidelberg University (GIScience)
 *
 *   http://www.giscience.uni-hd.de
 *   http://www.heigit.org
 *
 *  under one or more contributor license agreements. See the NOTICE file
 *  distributed with this work for additional information regarding copyright
 *  ownership. The GIScience licenses this file to you under the Apache License,
 *  Version 2.0 (the "License"); you may not use this file except in compliance
 *  with the License. You may obtain a copy of the License at
 *
 *       http://www.apache.org/licenses/LICENSE-2.0
 *
 *  Unless required by applicable law or agreed to in writing, software
 *  distributed under the License is distributed on an "AS IS" BASIS,
 *  WITHOUT WARRANTIES OR CONDITIONS OF ANY KIND, either express or implied.
 *  See the License for the specific language governing permissions and
 *  limitations under the License.
 */
package heigit.ors.routing.graphhopper.extensions.edgefilters.core;

import com.graphhopper.routing.util.EdgeFilter;
import com.graphhopper.storage.GraphStorage;
import com.graphhopper.util.EdgeIteratorState;
import heigit.ors.routing.graphhopper.extensions.storages.BordersGraphStorage;
import heigit.ors.routing.graphhopper.extensions.storages.GraphStorageUtils;

public class AvoidBordersCoreEdgeFilter implements EdgeFilter {
    private BordersGraphStorage _storage;

    public AvoidBordersCoreEdgeFilter(GraphStorage graphStorage) {
        this._storage = GraphStorageUtils.getGraphExtension(graphStorage, BordersGraphStorage.class);
    }
    /**
     *Determine whether or not an edge is to be filtered
     * @param iter iterator pointing to a given edge
     * @return <tt>true</tt> iff the edge pointed to by the iterator is not to be filtered
     */
    @Override
    public final boolean accept(EdgeIteratorState iter) {

        if (_storage == null) {
            return true;
        } else {
            return _storage.getEdgeValue(iter.getEdge(), BordersGraphStorage.Property.TYPE) == BordersGraphStorage.NO_BORDER;
        }

    }
}

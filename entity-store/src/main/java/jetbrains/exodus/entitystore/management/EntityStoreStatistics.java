/**
 * Copyright 2010 - 2016 JetBrains s.r.o.
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
package jetbrains.exodus.entitystore.management;

import jetbrains.exodus.entitystore.PersistentEntityStoreImpl;
import jetbrains.exodus.entitystore.PersistentEntityStoreStatistics;
import jetbrains.exodus.management.MBeanBase;
import org.jetbrains.annotations.NotNull;

public class EntityStoreStatistics extends MBeanBase implements EntityStoreStatisticsMBean {

    @NotNull
    private final PersistentEntityStoreImpl store;

    public EntityStoreStatistics(@NotNull final PersistentEntityStoreImpl store) {
        super(getObjectName(store));
        this.store = store;
    }

    @Override
    public long getBlobsDiskUsage() {
        return store.getStatistics().getStatisticsItem(PersistentEntityStoreStatistics.BLOBS_DISK_USAGE).getTotal();
    }

    @Override
    public long getNumberOfCachingJobs() {
        return store.getStatistics().getStatisticsItem(PersistentEntityStoreStatistics.CACHING_JOBS).getTotal();
    }

    @Override
    public double getEntityIterableCacheHitRate() {
        return store.getEntityIterableCache().hitRate();
    }

    @Override
    public double getBlobStringsCacheHitRate() {
        return store.getBlobVault().getStringContentCacheHitRate();
    }

    public static String getObjectName(@NotNull final PersistentEntityStoreImpl store) {
        return OBJECT_NAME_PREFIX + ", location=" + escapeLocation(store.getLocation());
    }
}

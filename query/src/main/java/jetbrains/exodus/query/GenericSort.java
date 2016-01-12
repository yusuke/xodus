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
package jetbrains.exodus.query;


import jetbrains.exodus.entitystore.Entity;
import org.jetbrains.annotations.NotNull;

import java.util.Comparator;

public class GenericSort extends Sort {
    private final Comparator<Entity> cmp;

    public GenericSort(final NodeBase child, Comparator<Entity> cmp, boolean ascending) {
        super(child, ascending);
        this.cmp = cmp;
    }

    public Comparator<Entity> getCmp() {
        return cmp;
    }

    @Override
    public boolean canBeCached() {
        return false;
    }

    @Override
    public NodeBase getClone() {
        return new GenericSort(getChild().getClone(), cmp, getAscending());
    }

    @Override
    public boolean equalAsSort(Object o) {
        if (!(o instanceof GenericSort)) {
            return false;
        }
        GenericSort sort = (GenericSort) o;
        return eq_mvtm2i_a0a2a4(cmp, sort.cmp) && getAscending() == sort.getAscending();
    }

    @Override
    public Iterable<Entity> applySort(String entityType, Iterable<Entity> iterable, @NotNull final SortEngine sortEngine) {
        return sortEngine.sort(iterable, cmp, getAscending());
    }

    @Override
    public StringBuilder getHandle(StringBuilder sb) {
        super.getHandle(sb).append('(').append(cmp).append(' ').append(getAscending()).append(')').append('{');
        child.getHandle(sb);
        return sb.append('}');
    }

    @Override
    public String getSimpleName() {
        return "gs";
    }

    private static boolean eq_mvtm2i_a0a2a4(Object a, Object b) {
        return a != null ? a.equals(b) : a == b;
    }
}

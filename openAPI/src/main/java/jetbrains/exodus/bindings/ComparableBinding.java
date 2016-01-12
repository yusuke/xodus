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
package jetbrains.exodus.bindings;

import jetbrains.exodus.ArrayByteIterable;
import jetbrains.exodus.ByteIterable;
import jetbrains.exodus.util.LightOutputStream;
import org.jetbrains.annotations.NotNull;

import java.io.ByteArrayInputStream;

public abstract class ComparableBinding {

    public final Comparable entryToObject(@NotNull final ByteIterable entry) {
        return readObject(new ByteArrayInputStream(entry.getBytesUnsafe(), 0, entry.getLength()));
    }

    public final ArrayByteIterable objectToEntry(@NotNull final Comparable object) {
        final LightOutputStream output = new LightOutputStream();
        writeObject(output, object);
        return output.asArrayByteIterable();
    }

    public abstract Comparable readObject(@NotNull final ByteArrayInputStream stream);

    public abstract void writeObject(@NotNull final LightOutputStream output, @NotNull final Comparable object);

}

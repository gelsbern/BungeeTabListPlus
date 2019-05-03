/*
 * BungeeTabListPlus - a BungeeCord plugin to customize the tablist
 *
 * Copyright (C) 2014 - 2015 Florian Stober
 *
 * This program is free software: you can redistribute it and/or modify
 * it under the terms of the GNU General Public License as published by
 * the Free Software Foundation, either version 3 of the License, or
 * (at your option) any later version.
 *
 * This program is distributed in the hope that it will be useful,
 * but WITHOUT ANY WARRANTY; without even the implied warranty of
 * MERCHANTABILITY or FITNESS FOR A PARTICULAR PURPOSE.  See the
 * GNU General Public License for more details.
 *
 * You should have received a copy of the GNU General Public License
 * along with this program.  If not, see <http://www.gnu.org/licenses/>.
 */

package codecrafter47.bungeetablistplus.data;

import codecrafter47.bungeetablistplus.api.bungee.Icon;
import codecrafter47.bungeetablistplus.common.network.DataStreamUtils;
import codecrafter47.bungeetablistplus.common.network.TypeAdapter;
import codecrafter47.bungeetablistplus.common.network.TypeAdapterRegistry;
import com.google.common.collect.ImmutableMap;
import de.codecrafter47.data.api.TypeToken;

import java.io.DataInput;
import java.io.DataOutput;
import java.io.IOException;
import java.util.UUID;

public class BTLPDataTypes {

    public static final TypeToken<Icon> ICON = TypeToken.create();

    public static final TypeAdapterRegistry REGISTRY = new TypeAdapterRegistry(ImmutableMap.of(
            ICON, new TypeAdapter<Icon>() {
                @Override
                public Icon read(DataInput input) throws IOException {
                    UUID uuid = null;
                    if (input.readBoolean()) {
                        uuid = DataStreamUtils.readUUID(input);
                    }
                    String value = input.readUTF();
                    String signature = input.readUTF();
                    return new Icon(uuid, new String[][]{{"textures", value, signature}});
                }

                @Override
                public void write(DataOutput output, Icon icon) throws IOException {
                    UUID uuid = icon.getPlayer();
                    output.writeBoolean(uuid != null);
                    if (uuid != null) {
                        DataStreamUtils.writeUUID(output, uuid);
                    }
                    for (String[] property : icon.getProperties()) {
                        if (property.length == 3 && "textures".equals(property[0])) {
                            output.writeUTF(property[1]);
                            output.writeUTF(property[2]);
                            return;
                        }
                    }
                    output.writeUTF("");
                    output.writeUTF("");
                }
            }
    ));
}

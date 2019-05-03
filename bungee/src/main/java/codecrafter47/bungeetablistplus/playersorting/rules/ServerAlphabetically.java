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

package codecrafter47.bungeetablistplus.playersorting.rules;

import codecrafter47.bungeetablistplus.api.bungee.IPlayer;
import codecrafter47.bungeetablistplus.context.Context;
import codecrafter47.bungeetablistplus.player.Player;
import codecrafter47.bungeetablistplus.playersorting.SortingRule;
import de.codecrafter47.data.bungee.api.BungeeData;

import java.text.Collator;
import java.util.Optional;

public class ServerAlphabetically implements SortingRule {

    @Override
    public int compare(Context context, IPlayer player1, IPlayer player2) {
        Optional<String> server1 = ((Player) player1).getOpt(BungeeData.BungeeCord_Server);
        Optional<String> server2 = ((Player) player2).getOpt(BungeeData.BungeeCord_Server);
        if (server1.isPresent() || server2.isPresent()) {
            return Collator.getInstance().compare(server1.orElse(""), server2.orElse(""));
        }
        return 0;
    }
}

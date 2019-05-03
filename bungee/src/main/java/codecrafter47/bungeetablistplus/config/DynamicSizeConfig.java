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

package codecrafter47.bungeetablistplus.config;

import codecrafter47.bungeetablistplus.config.components.Component;
import codecrafter47.bungeetablistplus.playersorting.PlayerSorter;
import com.google.common.base.Preconditions;
import lombok.Getter;
import lombok.Setter;

@Getter
@Setter
public class DynamicSizeConfig extends Config {

    private PlayerSorter playerOrder = new PlayerSorter("alphabetically");

    private String playerSet;

    private Component playerComponent;

    public void setPlayerComponent(Component playerComponent) {
        Preconditions.checkArgument(playerComponent.hasConstantSize(), "playerComponent needs to have a fixed size.");
        this.playerComponent = playerComponent;
    }

    @Override
    public void validate() {
        super.validate();
        Preconditions.checkNotNull(playerSet, "playerSet is null");
        Preconditions.checkNotNull(playerComponent, "playerComponent is null");
    }
}

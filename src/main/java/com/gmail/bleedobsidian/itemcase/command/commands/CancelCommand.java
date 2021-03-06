/*
 * Copyright (C) 2013 Jesse Prescott <BleedObsidian@gmail.com>
 *
 * ItemCase is free software: you can redistribute it and/or modify
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
 * along with this program.  If not, see <http://www.gnu.org/licenses/gpl.html>.
 */

package com.gmail.bleedobsidian.itemcase.command.commands;

import org.bukkit.entity.Player;

import com.gmail.bleedobsidian.itemcase.ItemCase;
import com.gmail.bleedobsidian.itemcase.Language;
import com.gmail.bleedobsidian.itemcase.loggers.PlayerLogger;

public class CancelCommand {
    public static void cancel(ItemCase plugin, Player player, String[] args) {
        if (plugin.getSelectionManager().isPendingSelection(player)) {
            plugin.getSelectionManager().removePendingSelection(player);

            PlayerLogger.message(
                    player,
                    Language.getLanguageFile().getMessage(
                            "Player.Cancel.Canceled"));
            return;
        } else {
            PlayerLogger.message(player,
                    Language.getLanguageFile()
                            .getMessage("Player.Cancel.Error"));
            return;
        }
    }
}

/*  Copyright (C) 2016-2018 Carsten Pfeiffer

    This file is part of Gadgetbridge.

    Gadgetbridge is free software: you can redistribute it and/or modify
    it under the terms of the GNU Affero General Public License as published
    by the Free Software Foundation, either version 3 of the License, or
    (at your option) any later version.

    Gadgetbridge is distributed in the hope that it will be useful,
    but WITHOUT ANY WARRANTY; without even the implied warranty of
    MERCHANTABILITY or FITNESS FOR A PARTICULAR PURPOSE.  See the
    GNU Affero General Public License for more details.

    You should have received a copy of the GNU Affero General Public License
    along with this program.  If not, see <http://www.gnu.org/licenses/>. */
package com.nodomain.freeyourgadget.gadgetbridge.service.btle.profiles.alertnotification;

import com.nodomain.freeyourgadget.gadgetbridge.service.btle.BLETypeConversions;

/**
 * https://www.bluetooth.com/specifications/gatt/viewer?attributeXmlFile=org.bluetooth.characteristic.alert_notification_control_point.xml
 */
public class AlertNotificationControl {
    private AlertCategory category;
    private Command command;

    public void setCategory(AlertCategory category) {
        this.category = category;
    }

    public void setCommand(Command command) {
        this.command = command;
    }

    public AlertCategory getCategory() {
        return category;
    }

    public Command getCommand() {
        return command;
    }

    /**
     * Returns the formatted message to be written to the alert notification control point
     * characteristic
     */
    public byte[] getControlMessage() {
        return new byte[] {
                BLETypeConversions.fromUint8(command.getId()),
                BLETypeConversions.fromUint8(category.getId())
        };
    }
}

/*  Copyright (C) 2017-2018 Andreas Shimokawa, João Paulo Barraca

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
package com.nodomain.freeyourgadget.gadgetbridge.devices.huami.amazfitcor;

import android.bluetooth.BluetoothDevice;
import android.content.Context;
import android.net.Uri;
import android.support.annotation.NonNull;

import com.nodomain.freeyourgadget.gadgetbridge.devices.InstallHandler;
import com.nodomain.freeyourgadget.gadgetbridge.devices.huami.HuamiCoordinator;
import com.nodomain.freeyourgadget.gadgetbridge.impl.GBDevice;
import com.nodomain.freeyourgadget.gadgetbridge.impl.GBDeviceCandidate;
import com.nodomain.freeyourgadget.gadgetbridge.model.DeviceType;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

public class AmazfitCorCoordinator extends HuamiCoordinator {
    private static final Logger LOG = LoggerFactory.getLogger(AmazfitCorCoordinator.class);

    @Override
    public DeviceType getDeviceType() {
        return DeviceType.AMAZFITCOR;
    }

    @NonNull
    @Override
    public DeviceType getSupportedType(GBDeviceCandidate candidate) {
        try {
            BluetoothDevice device = candidate.getDevice();
            String name = device.getName();
            if (name != null && (name.equalsIgnoreCase("Amazfit Band") || name.equalsIgnoreCase("Amazfit Cor"))) {
                return DeviceType.AMAZFITCOR;
            }
        } catch (Exception ex) {
            LOG.error("unable to check device support", ex);
        }
        return DeviceType.UNKNOWN;
    }

    @Override
    public InstallHandler findInstallHandler(Uri uri, Context context) {
        AmazfitCorFWInstallHandler handler = new AmazfitCorFWInstallHandler(uri, context);
        return handler.isValid() ? handler : null;
    }

    @Override
    public boolean supportsHeartRateMeasurement(GBDevice device) {
        return true;
    }

    @Override
    public boolean supportsWeather() {
        return true;
    }
}

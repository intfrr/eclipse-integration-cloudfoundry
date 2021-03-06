/*******************************************************************************
 * Copyright (c) 2012 VMware, Inc.
 * All rights reserved. This program and the accompanying materials
 * are made available under the terms of the Eclipse Public License v1.0
 * which accompanies this distribution, and is available at
 * http://www.eclipse.org/legal/epl-v10.html
 *
 * Contributors:
 *     VMware, Inc. - initial API and implementation
 *******************************************************************************/
package org.cloudfoundry.ide.eclipse.internal.server.ui.editor;

import org.cloudfoundry.ide.eclipse.internal.server.core.CloudFoundryServer;

public enum ServiceViewColumn {
	Name(150), Type(100), Vendor(100), Tunnel(80), Plan(50), Provider(100);
	private int width;

	private ServiceViewColumn(int width) {
		this.width = width;
	}

	public int getWidth() {
		return width;
	}

	public static ServiceViewColumnDescriptor getServiceViewColumnDescriptor(CloudFoundryServer cloudServer) {
		return new ServiceViewColumnDescriptor(cloudServer);
	}

	public static class ServiceViewColumnDescriptor {

		private final CloudFoundryServer cloudServer;

		public ServiceViewColumnDescriptor(CloudFoundryServer cloudServer) {
			this.cloudServer = cloudServer;
		}

		public ServiceViewColumn[] getServiceViewColumn() {
			if (cloudServer.supportsCloudSpaces()) {
				return new ServiceViewColumn[] { Name, Vendor, Provider, Plan, Tunnel };
			}
			else {
				return new ServiceViewColumn[] { Name, Type, Vendor, Tunnel };
			}
		}

	}

}

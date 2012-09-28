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
package org.cloudfoundry.ide.eclipse.internal.server.ui.wizards;

import org.cloudfoundry.ide.eclipse.internal.server.core.CloudFoundryServer;
import org.cloudfoundry.ide.eclipse.internal.server.ui.editor.CloudFoundryCredentialsPart;
import org.cloudfoundry.ide.eclipse.internal.server.ui.editor.CloudSpaceChangeListener;
import org.eclipse.jface.wizard.WizardPage;
import org.eclipse.swt.widgets.Composite;

/**
 * @author Christian Dupuis
 * @author Leo Dos Santos
 * @author Steffen Pingel
 * @author Terry Denney
 */
public class CloudFoundryCredentialsWizardPage extends WizardPage {

	private final CloudFoundryCredentialsPart credentialsPart;

	private CloudSpaceChangeListener cloudSpaceChangeListener;

	protected CloudFoundryCredentialsWizardPage(CloudFoundryServer server) {
		super(server.getServer().getName() + " Credentials");
		cloudSpaceChangeListener = new CloudSpaceChangeListener(server);
		credentialsPart = new CloudFoundryCredentialsPart(server, this, cloudSpaceChangeListener);
	}

	public void createControl(Composite parent) {
		Composite composite = credentialsPart.createComposite(parent);
		setControl(composite);
	}

	@Override
	public boolean isPageComplete() {
		return credentialsPart.isComplete();
	}

	public CloudSpaceChangeListener getSpaceChangeListener() {
		return cloudSpaceChangeListener;
	}

	public boolean supportsSpaces() {
		return cloudSpaceChangeListener.getCurrentSpacesDescriptor() != null
				&& cloudSpaceChangeListener.getCurrentSpacesDescriptor().supportsSpaces();
	}

	public boolean canFlipToNextPage() {
		// There should only be a next page for the spaces page if there is a
		// cloud space descriptor set
		return isPageComplete() && supportsSpaces() && getNextPage() != null;
	}

}

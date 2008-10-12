/*******************************************************************************
 * Copyright (c) 2008 Matthew Hillsdon.
 * All rights reserved. This program and the accompanying materials
 * are made available under the terms of the Eclipse Public License v1.0
 * which accompanies this distribution, and is available at
 * http://www.eclipse.org/legal/epl-v10.html
 *
 * Contributors:
 *     Matthew Hillsdon <matt@hillsdon.net>
 *******************************************************************************/
package net.hillsdon.testlink.plugin;

import org.eclipse.ui.plugin.AbstractUIPlugin;
import org.osgi.framework.BundleContext;

/**
 * Not currently used for anything other than getting at the log.
 */
public class TestlinkPlugin extends AbstractUIPlugin {

  public static final String ID = "net.hillsdon.testlink";
  
	private static TestlinkPlugin _plugin;
	
	public TestlinkPlugin() {
		_plugin = this;
	}

	public void start(final BundleContext context) throws Exception {
		super.start(context);
	}

	public void stop(final BundleContext context) throws Exception {
		super.stop(context);
		_plugin = null;
	}

	public static TestlinkPlugin getDefault() {
		return _plugin;
	}

}

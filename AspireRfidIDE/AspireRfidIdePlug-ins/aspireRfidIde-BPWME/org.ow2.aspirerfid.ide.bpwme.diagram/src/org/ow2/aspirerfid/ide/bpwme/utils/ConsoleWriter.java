

package org.ow2.aspirerfid.ide.bpwme.utils;

import java.io.IOException;

import org.eclipse.ui.console.ConsolePlugin;
import org.eclipse.ui.console.IConsole;
import org.eclipse.ui.console.IConsoleManager;
import org.eclipse.ui.console.IOConsole;
import org.eclipse.ui.console.IOConsoleOutputStream;

/**
 * @author kefnik
 *
 */
public class ConsoleWriter {

	// ----------Console Initialization and Use----------------
	IOConsoleOutputStream peControlProcessActionsOutputConsole;
	IOConsole peControlProcessActionsConfiguratorConsole;
	IConsoleManager manager;
	IConsole[] consolesx;
	
	
	
	public ConsoleWriter(){
		
		initiateConsole();
	}
	
	/**
	 * Initiate the Ale Server Configurator Console
	 */
	private void initiateConsole() {
		manager = ConsolePlugin.getDefault().getConsoleManager();
		consolesx = manager.getConsoles();
		boolean exist = false;
		for (int i = 0; i < consolesx.length; i++) {
			if (consolesx[i].getName().equals("ALE Server Configurator"))
				peControlProcessActionsConfiguratorConsole = (IOConsole) consolesx[i];
			exist = true;
		}
		if (!exist) {
			peControlProcessActionsConfiguratorConsole = new IOConsole(
					"ALE Server Configurator", null);
			manager
					.addConsoles(new IConsole[] { peControlProcessActionsConfiguratorConsole });
		}
		manager.showConsoleView(peControlProcessActionsConfiguratorConsole);
		peControlProcessActionsConfiguratorConsole.clearConsole();
		peControlProcessActionsOutputConsole = peControlProcessActionsConfiguratorConsole
				.newOutputStream();
	}
	
	
	
	/**
	 * Write to the IDE's Console
	 * 
	 * @param message
	 */
	public void writeToConsole(String message) {

		try {
			peControlProcessActionsOutputConsole.write(message + "\n");
		} catch (IOException e) {
			System.out.println("Console writing error:" + e.getMessage());
		}
	}
	
}

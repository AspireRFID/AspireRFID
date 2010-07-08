/*
 *  Copyright (C) Aspire
 *
 *  This library is free software; you can redistribute it and/or
 *  modify it under the terms of the GNU Lesser General Public
 *  License as published by the Free Software Foundation; either
 *  version 2.1 of the License, or (at your option) any later version.
 *
 *  This library is distributed in the hope that it will be useful,
 *  but WITHOUT ANY WARRANTY; without even the implied warranty of
 *  MERCHANTABILITY or FITNESS FOR A PARTICULAR PURPOSE.  See the GNU
 *  Lesser General Public License for more details.
 *
 *  You should have received a copy of the GNU Lesser General Public
 *  License along with this library; if not, write to the Free Software
 *  Foundation, Inc., 51 Franklin Street, Fifth Floor, Boston, MA  02110-1301  USA
 */
package org.ow2.aspirerfid.rocket.command;

import java.io.PrintStream;
import java.util.StringTokenizer;

import org.apache.felix.shell.Command;
import org.osgi.framework.BundleContext;
import org.osgi.framework.ServiceRegistration;
import org.ow2.aspirerfid.rocket.service.RocketService;

/**
 * @author Thomas Calmant
 * 
 */
public class RocketCommand implements Command {

	/** Rocket launcher reference */
	private RocketService m_rocket;

	/** Bundle context */
	private BundleContext m_bundleContext;

	/** Command registration */
	private ServiceRegistration m_cmdReg;

	public RocketCommand(BundleContext context) {
		m_bundleContext = context;
	}

	public void start() {
		System.out.println("Init rocket command");

		m_cmdReg = m_bundleContext.registerService(Command.class.getName(),
				this, null);
	}

	public void stop() {
		System.out.println("Uninit rocket command");

		if (m_cmdReg != null)
			m_cmdReg.unregister();
	}

	public void execute(String cmdLine, PrintStream out, PrintStream err) {
		StringTokenizer tokens = new StringTokenizer(cmdLine, " ");

		if (tokens.countTokens() < 2) {
			out.println(getFullUsage());
			return;
		}

		// Ignore command
		tokens.nextToken();
		String cmd = tokens.nextToken();

		if (cmd.equalsIgnoreCase("fire"))
			fire(tokens, err);
		else if (cmd.equalsIgnoreCase("move"))
			move(tokens, err);
		else
			out.println(getFullUsage());
	}

	public String getName() {
		return "rocket";
	}

	public String getShortDescription() {
		return "Command to control a USB Rocket Launcher";
	}

	public String getUsage() {
		return "rocket help";
	}

	private String getFullUsage() {
		String name = getName() + " ";
		
		String help = name + "fire [all] : Fires a rocket. All rockets if told so.\n";
		help += name + "move <time> <direction:up|down|left|right|upleft|upright|downleft|downright> [fire] ... : moves the tower <direction> during <time> ms. Can fire between/after moves.\n";
		return help;
	}

	private void fire(StringTokenizer tokens, PrintStream err) {
		if (m_rocket == null) {
			err.println("No rocket launcher found");
			return;
		}
		
		m_rocket.fire();
		
		if(tokens.hasMoreElements() && tokens.nextToken().equalsIgnoreCase("all")) {
			for(int i=0; i < 3; i++)
				m_rocket.fire();
		}
	}

	/**
	 * Move the rocket launcher Example : move 10 left 20 right...
	 * 
	 * @param tokens
	 *            Command line, based on the command 'move'
	 * @param err
	 *            Error output
	 */
	private void move(StringTokenizer tokens, PrintStream err) {
		if (m_rocket == null) {
			err.println("No rocket launcher found");
			return;
		}

		long time = 0;
		int argIndex = 0;

		while (tokens.hasMoreTokens()) {
			String arg = tokens.nextToken();

			if (argIndex % 2 == 0) {
				if(arg.equalsIgnoreCase("fire"))
					m_rocket.fire();
				else
				{
					try {
						time = Long.parseLong(arg);
					} catch (NumberFormatException e) {
						err.println("Looking for time, got : " + arg);
						return;
					}
				}
			} else {
				if (arg.equalsIgnoreCase("up"))
					m_rocket.moveUp(time);
				else if (arg.equalsIgnoreCase("down"))
					m_rocket.moveDown(time);
				else if (arg.equalsIgnoreCase("left"))
					m_rocket.moveLeft(time);
				else if (arg.equalsIgnoreCase("right"))
					m_rocket.moveRight(time);
				else if (arg.equalsIgnoreCase("downleft"))
					m_rocket.moveDownLeft(time);
				else if (arg.equalsIgnoreCase("downright"))
					m_rocket.moveDownRight(time);
				else if (arg.equalsIgnoreCase("upleft"))
					m_rocket.moveUpLeft(time);
				else if (arg.equalsIgnoreCase("upright"))
					m_rocket.moveUpRight(time);
				else {
					err.println("Unknown argument : " + arg);
					return;
				}
			}

			argIndex++;
		}
	}
}

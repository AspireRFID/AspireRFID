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
package org.ow2.aspirerfid.rocket.service;

//MissileLauncher interface, allows to send commands to the org.ow2.aspirerfid.rocket.impl launcher

public interface RocketService {

	public void fire();

	//the command stop will be called after "time" milliseconds.
	public void moveLeft(long time);
	public void moveRight(long time);
	public void moveUp(long time);
	public void moveDown(long time);
	public void moveUpLeft(long time);
	public void moveUpRight(long time);
	public void moveDownLeft(long time);
	public void moveDownRight(long time);	
	
    public enum Command {STOP, LEFT, RIGHT, UP, DOWN, UPLEFT, UPRIGHT, DOWNLEFT, DOWNRIGHT, FIRE};

	
}

/*
 * Copyright (C) 2007 ETH Zurich
 *
 * This file is part of Accada (www.accada.org).
 *
 * Accada is free software; you can redistribute it and/or
 * modify it under the terms of the GNU Lesser General Public
 * License version 2.1, as published by the Free Software Foundation.
 *
 * Accada is distributed in the hope that it will be useful,
 * but WITHOUT ANY WARRANTY; without even the implied warranty of
 * MERCHANTABILITY or FITNESS FOR A PARTICULAR PURPOSE. See the GNU
 * Lesser General Public License for more details.
 *
 * You should have received a copy of the GNU Lesser General Public
 * License along with Accada; if not, write to the Free
 * Software Foundation, Inc., 51 Franklin Street, Fifth Floor,
 * Boston, MA  02110-1301  USA
 */

package org.ow2.aspirerfid.reader.rp.hal.impl.sim.multi;

import java.awt.BorderLayout;
import java.awt.Color;
import java.awt.Component;
import java.awt.GridLayout;
import java.awt.Point;
import java.awt.event.MouseAdapter;
import java.awt.event.MouseEvent;
import java.awt.event.MouseMotionAdapter;
import java.net.URL;
import java.util.HashSet;
import java.util.Iterator;
import java.util.Locale;
import java.util.Set;
import java.util.TreeMap;
import java.util.TreeSet;

import javax.swing.JButton;
import javax.swing.JDialog;
import javax.swing.JFrame;
import javax.swing.JLabel;
import javax.swing.JLayeredPane;
import javax.swing.JMenu;
import javax.swing.JMenuBar;
import javax.swing.JMenuItem;
import javax.swing.JPanel;
import javax.swing.JPopupMenu;
import javax.swing.JTextField;

import org.ow2.aspirerfid.reader.rp.hal.impl.sim.graphic.Antenna;
import org.ow2.aspirerfid.reader.rp.hal.impl.sim.graphic.IGraphicSimulator;
import org.ow2.aspirerfid.reader.rp.hal.impl.sim.graphic.Reader;
import org.ow2.aspirerfid.reader.rp.hal.impl.sim.graphic.SelectionComponent;
import org.ow2.aspirerfid.reader.rp.hal.impl.sim.graphic.Tag;
import org.ow2.aspirerfid.reader.rp.hal.impl.sim.graphic.TranslationListener;
import org.ow2.aspirerfid.reader.rp.hal.impl.sim.util.ResourceLocator;
import org.apache.commons.configuration.ConfigurationException;
import org.apache.commons.configuration.XMLConfiguration;
import org.apache.commons.logging.Log;
import org.apache.commons.logging.LogFactory;


/**
 * @author regli
 */
public class GraphicSimulatorServer extends JFrame implements SimulatorServerEngine, IGraphicSimulator {

	/**	the serial version uid */
	private static final long serialVersionUID = 1L;
	/** language settings */
	private static final Locale LOCALE = Locale.ENGLISH;
	/** the logger */
	private static final Log LOG = LogFactory.getLog(GraphicSimulatorServer.class);
	
	/** the resource bundle */
//	private static ResourceBundle guiText;
	/** the localized gui text configuration */
	private static XMLConfiguration guiTextConfig;
	/** the properties */
//	private static Properties props;
	/** the property configuration */
	private static XMLConfiguration propsConfig;

	/** a hash map containing all the readers */
	private final TreeMap readers = new TreeMap();
	/** a hash set containing all the tags */
	private final HashSet tags = new HashSet();
	/** the listener which listen to tag translations */
	private final TranslationListener translationListener = new TranslationListener();
	/** the component for selection */
	private final SelectionComponent selection = new SelectionComponent();

	/** the simulator controller */
	private SimulatorServerController controller;
	/** the menu bar */
	private JMenuBar jJMenuBar;
	/** the layered pane which contains the antennas and tags */
	private JLayeredPane jLayeredPane;
	/** the context menu on the layered pane */
	private JPopupMenu contextMenu;
	/** the dialog window to add a new antenna */
	private JDialog newAntennaDialog;
	/** the dialog window to add a new tag */
	private JDialog newTagDialog;
	/** the active context menu */
	private JPopupMenu activeContextMenu;
	/** the tag id label */
	private JTextField tagIdField;

	/**
	 * implements the initialize method of the SimulatorServerEngine
     * 
     * @param controller 
	 * @throws SimulatorServerException 
	 */
	public void initialize(SimulatorServerController controller, String propFile,
         String defaultPropFile) throws SimulatorServerException {
		this.controller = controller;

      // load language
      String prefix = propFile.substring(0, propFile.lastIndexOf("/")) + "/GUIText_";
      String postfix = ".xml";
      String language = null;
      guiTextConfig = new XMLConfiguration();
      boolean loaded = false;
      
      // try default language
      if (!loaded) {
         language = LOCALE.getLanguage();
         String langFile = prefix + language + postfix;
         URL fileurl = ResourceLocator.getURL(langFile, langFile, this.getClass());
         try {
            guiTextConfig.load(fileurl);
            loaded = true;
         } catch (ConfigurationException ce) {
            throw new SimulatorServerException("Graphic simulator server language file not found.");
         }
      }

		// load properties
		try {
		   propsConfig = new XMLConfiguration();
         URL fileurl = ResourceLocator.getURL(propFile, defaultPropFile, this.getClass());
		   propsConfig.load(fileurl);
		} catch (ConfigurationException ce) {
		   throw new SimulatorServerException("Could not load properties file."); 
		}
		
		// initialize GUI
		initializeGUI();
		LOG.info("GraphicSimulatorServer started");
	}
	
	/**
	 *	initialize the graphic user interface
	 */
	private void initializeGUI() {
		computeProperties();
		this.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
		this.setSize(3000, 2000);
		this.setLayeredPane(getJLayeredPane());
		this.setJMenuBar(getJJMenuBar());
		
		this.setTitle(guiTextConfig.getString("ApplicationTitle"));
		this.setVisible(true);
		
		this.setSize(getProperty("WindowWidth"), getProperty("WindowHeight"));
	}
	

	/**
	 * computes some properties using other properties
	 */
	private void computeProperties() {
		int nbrOfReader = controller.getReaderIds().size();
		int maxNbrOfAntennas = 0;
		Iterator readerIt = controller.getReaderIds().iterator();
		while (readerIt.hasNext()) {
			maxNbrOfAntennas = Math.max(controller.getAntennaIds(((String)readerIt.next())).size(), maxNbrOfAntennas);
		}
		
		// window size, reader pane and antenna pane

	    String value;
      if(!propsConfig.containsKey("WindowWidth")) {
         value = new Integer(2 * getProperty("FramePadding")
               + getProperty("TagWidth") + 2 * getProperty("HorizontalPadding")
               + getProperty("ReaderWidth") + maxNbrOfAntennas * (getProperty("AntennaWidth")
                     + getProperty("HorizontalInterAntennaPadding"))
                     - getProperty("HorizontalInterAntennaPadding")).toString();
         propsConfig.addProperty("WindowWidth", value);
      }
      if(!propsConfig.containsKey("WindowHeight")) {
         value = new Integer(Math.max(getProperty("MinimumWindowHeight"),
               2 * getProperty("FramePadding") + (nbrOfReader * (getProperty("ReaderHeight")
                     + getProperty("InterReaderPadding"))
                     - getProperty("InterReaderPadding")) + 50)).toString();
         propsConfig.addProperty("WindowHeight", value);
      }
      if(!propsConfig.containsKey("ReaderPaneX")) {
         value = new Integer(getProperty("FramePadding") + getProperty("TagWidth")
               + getProperty("HorizontalPadding")).toString();
         propsConfig.addProperty("ReaderPaneX", value);
      }
      if(!propsConfig.containsKey("ReaderPaneY")) {
         value = new Integer(getProperty("FramePadding")).toString();
         propsConfig.addProperty("ReaderPaneY", value);
      }
      if(!propsConfig.containsKey("AntennaPaneX")) {
         value = new Integer(getProperty("ReaderPaneX") + getProperty("ReaderWidth")
               + getProperty("HorizontalPadding")).toString();
         propsConfig.addProperty("AntennaPaneX", value);
      }
      if(!propsConfig.containsKey("AntennaPaneY")) {
         value = new Integer(getProperty("FramePadding")).toString();
         propsConfig.addProperty("AntennaPaneY", value);
      }
	}
	
	/**
	 * creates the menu bar if it does not already exists
	 * 
	 * @return the menu bar
	 */
	private JMenuBar getJJMenuBar() {
		if (jJMenuBar == null) {
			jJMenuBar = new JMenuBar();
			jJMenuBar.add(getFileMenu());
			jJMenuBar.add(getViewMenu());
			jJMenuBar.add(getTagMenu());
			jJMenuBar.add(getHelpMenu());
		}
		return jJMenuBar;
	}
	
	/**
	 * creates the file menu item
	 * 
	 * @return file menu
	 */
	private JMenu getFileMenu() {
		JMenu fileMenu = new JMenu(guiTextConfig.getString("FileMenuItem"));
		
		// exit
		JMenuItem exitMenuItem = new JMenuItem();
		exitMenuItem.setText(guiTextConfig.getString("QuitMenuItem"));
		exitMenuItem.addMouseListener(new MouseAdapter() {
			public void mouseReleased(MouseEvent e) {
				System.exit(0);
			}
		});
		fileMenu.add(exitMenuItem);
		return fileMenu;
	}

	/**
	 * creates the view menu item
	 * 
	 * @return view menu
	 */
	private JMenu getViewMenu() {
		JMenu viewMenu = new JMenu(guiTextConfig.getString("ViewMenuItem"));
		
		// exit
		JMenuItem refreshMenuItem = new JMenuItem();
		refreshMenuItem.setText(guiTextConfig.getString("RefreshMenuItem"));
		refreshMenuItem.addMouseListener(new MouseAdapter() {
			public void mouseReleased(MouseEvent e) {
				updateGUI();
			}
		});
		viewMenu.add(refreshMenuItem);
		return viewMenu;
	}
	
	/**
	 * creates the tag menu item
	 * 
	 * @return tag menu
	 */
	private JMenu getTagMenu() {
		JMenu tagMenu = new JMenu(guiTextConfig.getString("TagMenuItem"));
		
		// new tag
		JMenuItem newTagMenuItem = new JMenuItem();
		newTagMenuItem.setText(guiTextConfig.getString("AddNewTagMenuItem"));
		newTagMenuItem.addMouseListener(new MouseAdapter() {
			public void mouseReleased(MouseEvent e) {
				showAddTagDialog();
			}
		});
		tagMenu.add(newTagMenuItem);
		return tagMenu;
	}

	
	/**
	 * creates the help menu item
	 * 
	 * @return help menu
	 */
	private JMenu getHelpMenu() {
		JMenu helpMenu = new JMenu(guiTextConfig.getString("HelpMenuItem"));

		// about
		JMenuItem aboutMenuItem = new JMenuItem();
		aboutMenuItem.setText(guiTextConfig.getString("AboutMenuItem"));
		aboutMenuItem.addMouseListener(new MouseAdapter() {
			public void mouseReleased(MouseEvent e) {
				JDialog aboutDialog = new JDialog(GraphicSimulatorServer.this, guiTextConfig.getString("AboutDialogTitle"), true);
				Point pos = new Point();
				pos.x = jLayeredPane.getLocationOnScreen().x + (jLayeredPane.getWidth() - getProperty("DialogWindowWidth")) / 2;
				pos.y = jLayeredPane.getLocationOnScreen().y + (jLayeredPane.getHeight() - getProperty("DialogWindowHeight")) / 2;
				aboutDialog.setLocation(pos);
				aboutDialog.setSize(getProperty("DialogWindowWidth"), getProperty("DialogWindowHeight"));
				aboutDialog.setTitle(guiTextConfig.getString("AboutDialogTitle"));
				JLabel text = new JLabel(guiTextConfig.getString("AboutDialogContent"));
				text.setHorizontalAlignment(JLabel.CENTER);
				aboutDialog.add(text);
				aboutDialog.setVisible(true);
			}
		});
		helpMenu.add(aboutMenuItem);
		return helpMenu;
	}
	
	/**
	 * creates the layered pane if it does not already exists
	 * 
	 * @return layered pane
	 */
	private JLayeredPane getJLayeredPane() {
		if (jLayeredPane == null) {
			jLayeredPane = new JLayeredPane();
			jLayeredPane.setLayout(null);
			jLayeredPane.setOpaque(true);
			jLayeredPane.setBackground(Color.WHITE);
			//jLayeredPane.add(getReader(), new Integer(0));
			
			// add readers
			createReaders(controller.getReaderIds());
			
			// add context menu
			jLayeredPane.add(getContextMenu());

			// add mouse listener
			jLayeredPane.addMouseListener(new MouseAdapter() {

				// context menu
				public void mouseReleased(MouseEvent e) {
					hideActiveContextMenuAndSelection();
					if (e.getButton() == MouseEvent.BUTTON3) {
						Point posOnScreen = getJLayeredPane().getLocationOnScreen();
						contextMenu.setLocation(posOnScreen.x + e.getX(), posOnScreen.y + e.getY());
						contextMenu.setVisible(true);
						setActiveContextMenu(contextMenu);
					} else {
						contextMenu.setVisible(false);
						if(e.getButton() == MouseEvent.BUTTON1) {
							selection.select(tags);
						}
					}
				}
				
				// selection
				public void mousePressed(final MouseEvent e) {
					hideActiveContextMenuAndSelection();
					if(e.getButton() == MouseEvent.BUTTON1) {
						selection.setBounds(0, 0, getProperty("WindowWidth"), getProperty("WindowHeight"));
						selection.setStartPoint(e.getPoint());
						jLayeredPane.add(selection, new Integer(2));
					}
				}
			});
			
			// add drag listener
			jLayeredPane.addMouseMotionListener(new MouseMotionAdapter() {
				public void mouseDragged(MouseEvent e) {
					if(selection.isActive()) {
						selection.setCurrentPoint(e.getPoint());
					}
				}
			});
		}
		return jLayeredPane;
	}

	/**
	 * update the layered pane with new reader information
	 * 
	 * @return layered pane
	 */
	private void updateJLayeredPane() {
		Set controllerReaderIds = controller.getReaderIds();
		Set guiReaderIds = readers.keySet();
		
		// get new readers
		Set newReaderIds = new TreeSet(controllerReaderIds);
		newReaderIds.removeAll(guiReaderIds);
		
		// get update readers
		Set updateReaderIds = new TreeSet(controllerReaderIds);
		updateReaderIds.retainAll(guiReaderIds);
		
		// get old readers
		Set oldReaderIds = new TreeSet(guiReaderIds);
		oldReaderIds.removeAll(controllerReaderIds);

		// create, update and delete readers
		createReaders(newReaderIds);
		updateReaders(updateReaderIds);
		removeReaders(oldReaderIds);
		
		jLayeredPane.repaint();
	}
	
	/**
	 * creates the context menu if it does not already exists
	 * 
	 * @return context menu
	 */
	private JPopupMenu getContextMenu() {
		if(contextMenu == null) {
			contextMenu = new JPopupMenu();
			
			// add new tag item 
			JMenuItem newTagContextMenuItem = new JMenuItem(guiTextConfig.getString("AddNewTagMenuItem"));
			newTagContextMenuItem.addMouseListener(new MouseAdapter() {
				public void mouseReleased(MouseEvent e) {
					showAddTagDialog();
					contextMenu.setVisible(false);
				}
			});
			contextMenu.add(newTagContextMenuItem);
		}
		return contextMenu;
	}
	
	/**
	 * shows the dialog menu to add a new tag
	 */
	private void showAddTagDialog() {
		// compute position of the dialog
		final Point pos = new Point();
		pos.x = jLayeredPane.getLocationOnScreen().x + (jLayeredPane.getWidth() - getProperty("DialogWindowWidth")) / 2;
		pos.y = jLayeredPane.getLocationOnScreen().y + (jLayeredPane.getHeight() - getProperty("DialogWindowHeight")) / 2;
		
		// set default id
		String id = "A47033F";
		for(int i = 0; i < 4 - new Integer(tags.size()).toString().length(); i++) {
			id += "0";
		}
		id += tags.size();
		if(tagIdField == null) {
			tagIdField = new JTextField();
		}
		tagIdField.setText(id);
		
		// create tag dialog if it does not already exists
		if(newTagDialog == null) {
			newTagDialog = new JDialog(this, guiTextConfig.getString("AddNewTagDialogTitle"), true);
			newTagDialog.setSize(getProperty("DialogWindowWidth"), getProperty("DialogWindowHeight"));
			newTagDialog.setLayout(new BorderLayout());
			
			// input fields panel
			JLabel epcLabel = new JLabel(guiTextConfig.getString("TagIdLabel") + ": ");
			JPanel inputFields = new JPanel();
			inputFields.setLayout(new GridLayout(2, 2));
			inputFields.add(epcLabel);
			inputFields.add(tagIdField);
			
			// cancel button
			JButton cancelButton = new JButton(guiTextConfig.getString("CancelButton"));
			cancelButton.addMouseListener(new MouseAdapter() {
				public void mouseReleased(MouseEvent e) {
					newTagDialog.setVisible(false);
				}
			});
			
			// add button
			JButton addButton = new JButton(guiTextConfig.getString("AddButton"));
			addButton.addMouseListener(new MouseAdapter() {
				public void mouseReleased(MouseEvent e) {
					newTagDialog.setVisible(false);
					createNewTag(tagIdField.getText());
				}
			});
			
			// buttons panel
			JPanel buttons = new JPanel();
			buttons.add(addButton);
			buttons.add(cancelButton);
			
			// compose all together
			newTagDialog.add(inputFields, BorderLayout.CENTER);
			newTagDialog.add(buttons, BorderLayout.SOUTH);
			newTagDialog.getRootPane().setDefaultButton(addButton);
		}
		newTagDialog.setLocation(pos);
		newTagDialog.setVisible(true);
	}

	/**
	 * updates the GUI with new reader information 
	 */
	private void updateGUI() {
		computeProperties();
		this.setSize(getProperty("WindowWidth"), getProperty("WindowHeight"));
		updateJLayeredPane();
	}
	
	/**
	 * creates readers and add them to the layered pane
	 * 
	 * @param readerIds to add
	 */
	private void createReaders(Set readerIds) {
		Iterator readerIt = readerIds.iterator();
		while (readerIt.hasNext()) {
			createReader((String)readerIt.next());
		}
	}
	
	
	/**
	 * creates a reader and adds it to the layered pane
	 * 
	 * @param id of the reader
	 */
	private Reader createReader(String id) {
		// add reader
		Reader newReader = new Reader(id, controller.getAntennaIds(id), this);
		readers.put(id, newReader);
		updateReader(id);
		jLayeredPane.add(newReader, new Integer(0));

		return newReader;
	}
	
	public void addToJLayeredPane(Component cmp, Integer pos) {
		jLayeredPane.add(cmp, pos);
	}
	
	public void removeFromJLayeredPane(Component cmp) {
		jLayeredPane.remove(cmp);
	}
	
	/**
	 * update existing readers
	 * 
	 * @param readerIds to update
	 */
	private void updateReaders(Set readerIds) {
		Iterator readerIt = readerIds.iterator();
		while (readerIt.hasNext()) {
			updateReader((String)readerIt.next());
		}
	}
	
	/**
	 * update existing reader
	 * 
	 * @param readerId to update
	 */
	private void updateReader(String readerId) {
		// TODO: implement method updateReader
		Reader reader = (Reader)readers.get(readerId);
		reader.setPosition(getReaderPosition(readerId));
	}
	
	/**
	 * removes readers and removes them from the layered pane
	 * 
	 * @param readerIds to reomve
	 */
	private void removeReaders(Set readerIds) {
		Iterator readerIt = readerIds.iterator();
		while (readerIt.hasNext()) {
			removeReader((String)readerIt.next());
		}
	}
	
	/**
	 * deletes a reader and removes it from the layered pane
	 * 
	 * @param id to remove
	 */
	private void removeReader(String id) {
		Reader reader = (Reader)readers.get(id);
		
		// remove antennas
		Antenna[] antennas = (Antenna[])reader.getAntennas().values().toArray(new Antenna[0]);
		for (int i = 0; i < antennas.length; i++) {
			removeAntenna(antennas[i], reader);
		}
		
		// remove reader;
		readers.remove(id);
		jLayeredPane.remove(reader);
	}
	
	public Point getReaderPosition(String readerId) {
		int readerNbr = readers.headMap(readerId).size();
		int x = getProperty("ReaderPaneX");
		int y = readerNbr * (getProperty("ReaderHeight") + getProperty("InterReaderPadding")) + getProperty("ReaderPaneY");
		return new Point(x, y);
	}
	
	/**
	 * deletes an antenna and removes it from the layered pane
	 * 
	 * @param antenna
	 * @param reader which contains the antenna
	 */
	public void removeAntenna(Antenna antenna, Reader reader) {
		reader.removeAntenna(antenna);
	}

	/**
	 * removes the tag from the layered pane
	 * 
	 * @param tag which will be removed
	 */
	public void removeTag(Tag tag) {
		tags.remove(tag);
		jLayeredPane.remove(tag);
		jLayeredPane.repaint();
	}
	
	/**
	 * creates a new tag and adds it to the layered pane
	 * 
	 * @param name of the new tag
	 * @param epc of the new tag
	 */
	private void createNewTag(String id) {
		Point pos = new Point(getProperty("FramePadding"), tags.size() * (getProperty("TagHeight") + getProperty("InterTagPadding")) + getProperty("FramePadding"));
		createNewTag(id, pos);
	}
	
	/**
	 * creates a new tag and adds it to the layered pane
	 * 
	 * @param name of the new tag
	 * @param epc of the new tag
	 * @param pos position on the pane of the new tag
	 */
	private void createNewTag(String id, Point pos) {
		if(!"".equals(id)) {
			Tag newTag = new Tag(id, pos, this);
			tags.add(newTag);
			jLayeredPane.add(newTag, new Integer(1));
			jLayeredPane.repaint();
		}
	}

	/**
	 * returns the translation listener
	 * 
	 * @return translation listener
	 */
	public TranslationListener getTranslationListener() {
		return translationListener;
	}
	
	/**
	 * returns the gui text resource bundle
	 *
	 * @return gui text resource bundle
	 */
	public XMLConfiguration getGuiText() {
		return guiTextConfig;
	}

	/**
	 * returns the simulator properties
	 * 
	 * @return properties
	 */
	public XMLConfiguration getProperties() {
		return propsConfig;
	}
	
	/**
	 * adds an enter event to the simulator controller
	 * 
	 * @param antennaId 
	 * @param epc of the tag
	 */
	public void enterEvent(String readerId, String antennaId, String epc) {
		try {
			controller.add(readerId, antennaId, epc);
		} catch (SimulatorServerException e) {
			e.printStackTrace();
		}
	}

   /**
    * adds an enter event to the simulator controller
    * 
    * @param antennaId 
    * @param tag  the sim.Tag
    */
   public void enterEvent(String readerId, String antennaId,
         org.ow2.aspirerfid.reader.rp.hal.impl.sim.Tag tag) {
      try {
         controller.add(readerId, antennaId, tag.getTagID());
      } catch (SimulatorServerException e) {
         e.printStackTrace();
      }
   }

   /**
    * adds an exit event to the simulator controller
    * 
    * @param antennaId
    * @param epc of the tag
    */
   public void exitEvent(String readerId, String antennaId, String epc) {
      try {
         controller.remove(readerId, antennaId, epc);
      } catch (SimulatorServerException e) {
         e.printStackTrace();
      }
   }
   
	/**
	 * adds an exit event to the simulator controller
	 * 
	 * @param antennaId
	 * @param tag the sim.Tag
	 */
	public void exitEvent(String readerId, String antennaId,
         org.ow2.aspirerfid.reader.rp.hal.impl.sim.Tag tag) {
		try {
			controller.remove(readerId, antennaId, tag.getTagID());
		} catch (SimulatorServerException e) {
			e.printStackTrace();
		}
	}
	
	/**
	 * gets a property value as integer
	 * 
	 * @param key of the property
	 * @return property as integer value
	 */
	public int getProperty(String key) {
      String error;
      if(!propsConfig.containsKey(key)) {
         error =  "Value '" + key + "' not found in properties !";
      } else {
         try {
            return propsConfig.getInt(key);
         } catch (Exception e) {
            error = "Value '" + key + "' is not an integer !";
         }
      }
      throw new NumberFormatException(error);
	}

	/**
	 * sets active context menu
	 * 
	 * @param contextMenu which is active
	 */
	public void setActiveContextMenu(JPopupMenu contextMenu) {
		activeContextMenu = contextMenu;
	}
	
	/**
	 * hide active context menu
	 */
	public void hideActiveContextMenu() {
		if(activeContextMenu != null) {
			activeContextMenu.setVisible(false);
			activeContextMenu = null;
		}
	}
	
	/**
	 * hide active context menu and hide selection
	 */
	public void hideActiveContextMenuAndSelection() {
		hideActiveContextMenu();
		selection.unselect(tags);
	}
}
package main;
import java.awt.Color;
import java.awt.Dimension;
import java.awt.Graphics;
import java.awt.Graphics2D;
import java.awt.GraphicsDevice;
import java.awt.GraphicsEnvironment;
import java.awt.image.BufferedImage;
import java.util.ArrayList;
import java.util.Collections;
import java.util.Comparator;

import javax.swing.JPanel;

import entity.Entity;
import entity.Player;
import entity.Projectile;
import interactive_tiles.InteractiveTiles;
import object.DropChanceSystem;
import tiles.TileManager;

public class GamePanel extends JPanel implements Runnable{
	private static final long serialVersionUID = 1L;
	
	//Game Settings//
	final short FPS = 60;
	public int gameState;
	public final int gameMenu = 0;
	public final int playState = 1;
	public final int pauseState = 2;
	public final int dialougeState = 3;
	public final int viewCharState = 4;
	public final int optionsState = 5;
	
	
	//MAP SETTINGS
	public int maxMap = 20;
	public int currentMap = 1;
	
	//Screen settings//
	//---------------//
	final int originalTileSize = 16;
	final int scale = 3;
	
	public final int tileSize = originalTileSize * scale;
	public final int maxCol = 22; 
	public final int maxRow = 14;
	
	public final int screenHeight = maxRow * tileSize;
	public final int screenWidth = maxCol * tileSize;
	
	//FULL SCREEN SETTINGS
	int screenWidthF = screenWidth;
	int screenHeightF = screenHeight;
	public boolean fullScreen = false;
	BufferedImage tempScreen;
	Graphics2D g2;   
	
	
	//========================================//
	
	//WORLD SETTINGS//
	//---------------//
	public int maxWorldCol = 50;
	public int maxWorldRow = 50;
	public final int worldWidth = maxWorldCol * tileSize;
	public final int worldHeight = maxWorldRow  * tileSize;
	//===============//
	
	
	//Game thread//
	//--------------//
	Thread gameThread;
	//==============//
	
	//Game Controls//
	//--------------------------------//
	public KeyHandler keys = new KeyHandler(this);
	//================================//
	
	//Player//
	//-------
	public Player player = new Player(this, keys);
	//======
	
	//Tile Manager//
	//------------------------//
	TileManager tManager = new TileManager(this);
	//========================//
	
	//Collision Checker//
	//------------------------//
	public CollisionChecker collCheck = new CollisionChecker(this);
	//========================//
	
	//Objects Handler//
	//--------------------------//
	public Entity gameObjs[] = new Entity[20];
	public Entity items[] = new Entity[50];
	public InteractiveTiles IT_Manager[] = new InteractiveTiles[50];
	//==========================//
	
	//GUI Screen//
	//-----------------------------//
	public GUI gui = new GUI(this);
	//=============================//
	
	//HANDLE SOUND//
	//----------------//
	public SoundHandler soundM = new SoundHandler();
	public SoundHandler soundSE = new SoundHandler();
	//=================//
	
	//Manage Entities NPC//
	//------------------------------//
	public Entity npc[] = new Entity[20];
	//==============================//
	
	//Manage Monsters//
	//-------------------------------//
	public Entity monsters[] = new Entity[30];
	//===============================//
	
	//Assets Maker//
	//----------------------------//
	AssetsHandler createAssets =  new AssetsHandler(this);
	//===================================================//
	
	//EventRectangle and EventHadler//
	//-------------------------------//
	public EventRectangle eventRect = new EventRectangle();
	public EventHandler eventHandler = new EventHandler(this);
	//===============================//
	
	//RENDER ORDER//
	//------------//
	ArrayList<Entity> entList = new ArrayList<>();
	//==============//
	
	//MANAGE PROJECTILES
	//------------------------//
	public ArrayList<Entity> projectileList = new ArrayList<>();
	public ArrayList<Entity> particleList = new ArrayList<>();
	//========================//
	
	//Drop chance system//
	//--------------------//
	public DropChanceSystem dcs = new DropChanceSystem();
	//=====================//
	
	//sAVING AND LOADING//
	//-----------------------//
	public Config config = new Config(this);
	//========================//
	
	public void setupGame() {
		createAssets.makeObjects();
		createAssets.makeNpc();
		createAssets.makeMonster();
		createAssets.makeItems();
		createAssets.makeInteractiveTiles();
		gameState = gameMenu;
		
		playMusic(3);
		
		tempScreen = new BufferedImage(screenWidth, screenHeight, BufferedImage.TYPE_INT_ARGB);
		g2 = (Graphics2D) tempScreen.getGraphics();
		
		if(fullScreen) setFullScreen();
	}
	//============================//
	
	//THE GAMEPANEL CONSTRUCTOR//
	//-------------------------//
	public GamePanel() {
		
		this.setPreferredSize(new Dimension(screenWidth, screenHeight));
		this.setBackground(Color.ORANGE);
		this.setDoubleBuffered(true);
		
		this.addKeyListener(keys);
		this.setFocusable(true);
		
	}
	//==========================//
	
	
	public void startGameThread() {
		gameThread = new Thread(this);
		gameThread.start();
	}
	//GAME LOOP//
	//------------------//
	@Override
	public void run() {
		
		
		
		double drawInterval = 1000000000/FPS;
		double nextDraw = System.nanoTime() + drawInterval;
		
		while(gameThread != null) 
		{
			update();
			drawTempScreen();
			drawFullScreen();
			//repaint();
			long remainingTime = (long) (nextDraw - System.nanoTime());
				 remainingTime /= 1000000;
			
				 if (remainingTime < 0) 
					 remainingTime = 0;
				 
				 try {
					
					 Thread.sleep(remainingTime);
					 nextDraw += drawInterval;
					
				} catch (InterruptedException e) {
					// TODO Auto-generated catch block
					e.printStackTrace();
				}
			
			
			
		}
	}
	//====================================================//
	public void update() {
		
		if (gameState == gameMenu) {
			
		}
		if (gameState == playState) {
			player.update();
			for(int i = 0; i < npc.length; i++) {
				if(npc[i] != null) {
					npc[i].update();
				}
			}
			for(int i = 0; i < monsters.length; i++) {
				if(monsters[i] != null) {
					if(monsters[i].alive && !monsters[i].dying) monsters[i].update();
					if(!monsters[i].alive) {
						monsters[i].checkDrop();
						monsters[i] = null;
					}
				}
			}
			for(int i = 0; i < projectileList.size(); i++) {
				if(projectileList.get(i) != null) {
					if(projectileList.get(i).alive) projectileList.get(i).update();
					if(!projectileList.get(i).alive) projectileList.remove(i);
				}
			}
			for(int i = 0; i < particleList.size(); i++) {
				if(particleList.get(i) != null) {
					if(particleList.get(i).alive) particleList.get(i).update();
					if(!particleList.get(i).alive) particleList.remove(i);
				} 
			}
			for(int i = 0; i < IT_Manager.length; i++) {
				if(IT_Manager[i] != null) {
					IT_Manager[i].update();
				}
			}
		}
		if (gameState == pauseState) {
			player.spriteNum = 1;
		}
		if (gameState == dialougeState) {
			player.spriteNum = 1;
		}
		if (gameState == viewCharState) {
			
		}
		
	}
	
	public void drawTempScreen() {
		if (gameState == gameMenu) {
			gui.draw(g2);
		}
		
		else {
			
			//DRAW TILES
			tManager.draw(g2);
			//draw interactive tiles
			for(int i = 0; i < IT_Manager.length; i++) {
				if(IT_Manager[i] !=  null) {
					IT_Manager[i].draw(g2);
				}
			}
			
			
			//Add player
			entList.add(player);
			
			//Add NPC
			for(int i = 0; i < npc.length; i++) {
				if(npc[i] != null) {
					entList.add(npc[i]);
				}
			}
			
			//Add Objects
			for(int i = 0; i < gameObjs.length; i++) {
				if(gameObjs[i] != null) {
					entList.add(gameObjs[i]);
				}
			}
			
			//Add items
			for(int i = 0; i < items.length; i++) {
				if(items[i] != null) {
					entList.add(items[i]);
				}
			}
			
			
			//Add Monsters
			for(int i = 0; i < monsters.length; i++) {
				if(monsters[i] != null) {
					entList.add(monsters[i]);
				}
			}
			
			//Add Prjocetiles	
			for(int i = 0; i < projectileList.size(); i++) {
				if(projectileList.get(i) != null) {
					entList.add(projectileList.get(i)); 
				}
			}	
			
			//Add particles
			for(int i = 0; i < particleList.size(); i++) {
				if(particleList.get(i) != null) {
					entList.add(particleList.get(i)); 
				}
			}
			
			//Sort the entList
			Collections.sort(entList, new Comparator<Entity>() {

				@Override
				public int compare(Entity ent1, Entity ent2) {
					int topEntity = Integer.compare(ent1.worldY, ent2.worldY);
					return topEntity;
				}
				
			});
			
			//DRAW ENTITIES
			for(int i = 0; i < entList.size(); i++) {
				entList.get(i).draw(g2);
			}
			
			//RESET entList 
			entList.clear();
			
			
			//DRAW GUI
			gui.draw(g2);
		}
		

	}
	public void setFullScreen() {
		//GET THE DEVIVE SCREEN INFORMATION
		GraphicsEnvironment ge = GraphicsEnvironment.getLocalGraphicsEnvironment();
		GraphicsDevice gd = ge.getDefaultScreenDevice();
		gd.setFullScreenWindow(main.window);
		
			
		//GET SCREEN WIDTH AND SCEENHEIGHT
		screenWidthF = main.window.getWidth();
		screenHeightF = main.window.getHeight();
		
	}
	public void drawFullScreen() {
		Graphics g = getGraphics();
			g.drawImage(tempScreen, 0, 0, screenWidthF, screenHeightF, null );
			g.dispose();
	}
	public void playMusic(int i) {
		soundM.setAudioFile(i);
		soundM.play();
		soundM.loop();
	}
	public void playSE(int i) {
		soundSE.setAudioFile(i);;
		soundSE.play();
	}
	public void stopMusic() {
		soundM.stop();
	}
	
	
	
	
	
	
//	public void paintComponent(Graphics g) {
//		super.paintComponent(g);
//		Graphics2D g2 = (Graphics2D) g;
//		
//		if (gameState == gameMenu) {
//			gui.draw(g2);
//		}
//		
//		else {
//			
//			//DRAW TILES
//			tManager.draw(g2);
//			//draw interactive tiles
//			for(int i = 0; i < IT_Manager.length; i++) {
//				if(IT_Manager[i] !=  null) {
//					IT_Manager[i].draw(g2);
//				}
//			}
//			
//			
//			//Add player
//			entList.add(player);
//			
//			//Add NPC
//			for(int i = 0; i < npc.length; i++) {
//				if(npc[i] != null) {
//					entList.add(npc[i]);
//				}
//			}
//			
//			//Add Objects
//			for(int i = 0; i < gameObjs.length; i++) {
//				if(gameObjs[i] != null) {
//					entList.add(gameObjs[i]);
//				}
//			}
//			
//			//Add items
//			for(int i = 0; i < items.length; i++) {
//				if(items[i] != null) {
//					entList.add(items[i]);
//				}
//			}
//			
//			
//			//Add Monsters
//			for(int i = 0; i < monsters.length; i++) {
//				if(monsters[i] != null) {
//					entList.add(monsters[i]);
//				}
//			}
//			
//			//Add Prjocetiles	
//			for(int i = 0; i < projectileList.size(); i++) {
//				if(projectileList.get(i) != null) {
//					entList.add(projectileList.get(i)); 
//				}
//			}	
//			
//			//Add particles
//			for(int i = 0; i < particleList.size(); i++) {
//				if(particleList.get(i) != null) {
//					entList.add(particleList.get(i)); 
//				}
//			}
//			
//			//Sort the entList
//			Collections.sort(entList, new Comparator<Entity>() {
//
//				@Override
//				public int compare(Entity ent1, Entity ent2) {
//					int topEntity = Integer.compare(ent1.worldY, ent2.worldY);
//					return topEntity;
//				}
//				
//			});
//			
//			//DRAW ENTITIES
//			for(int i = 0; i < entList.size(); i++) {
//				entList.get(i).draw(g2);
//			}
//			
//			//RESET entList 
//			entList.clear();
//			
//			
//			//DRAW GUI
//			gui.draw(g2);
//		}
//		
//		
//		g2.dispose();
//		
//		
//	}
	

}













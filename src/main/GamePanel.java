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

import AI.PathFinder;
import DataHandling.SaveLoad;
import entity.Entity;
import entity.Player;
import environment.EffectsHandler;
import interactive_tiles.InteractiveTiles;
import object.DropChanceSystem;
import object.ObjectGenerator;
import tiles.Map;
import tiles.TileManager;

public class GamePanel extends JPanel implements Runnable{
	private static final long serialVersionUID = 1L;
	
	//Game Settings//
	public boolean bossBattleOn = false;
	final short FPS = 60;
	public int gameState;
	public final int gameMenu = 0;
	public final int playState = 1;
	public final int pauseState = 2;
	public final int dialogueState = 3;
	public final int viewCharState = 4;
	public final int optionsState = 5;
	public final int transitionState = 6;
	public final int tradingState = 7;
	public final int sleepState = 8;
	public final int viewMapState = 9;
	public final int gameOverState = 10;
	public final int cutSceneState = 11;

	
	
	//MAP SETTINGS
	public final int maxMap = 5;
	public int currentMap = 0;
	
	public int nextArea;
	public int currentArea;
	public final int outside = 50;
	public final int indoor = 51;
	public final int dungeon = 52;
	
	public final int worldMapA = 0;
	public final int merchantHouse = 2;
	public final int dungeonMap_F1 = 1;
	public final int dungeonMap_F2 = 3;

	//Screen settings//
	//---------------//
	final int originalTileSize =24;
	public final int scale = 2;
	
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
	
	//Game Saving and Loading Handler
	public SaveLoad saverLoader = new SaveLoad(this);
	
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
	public TileManager tManager = new TileManager(this);
	//========================//
	
	//Collision Checker//
	//------------------------//
	public CollisionChecker collCheck = new CollisionChecker(this);
	//========================//
	
	//HANDLING THE CUTSCENES//
	//------------------------//
	public CutSceneHandler csHandler = new CutSceneHandler(this);
	//========================//
	
	//Objects Handler//
	//--------------------------//
	public Entity gameObjs[][] = new Entity[maxMap][200];
	public Entity items[][] = new Entity[maxMap][50];
	public InteractiveTiles IT_Manager[][] = new InteractiveTiles[maxMap][50];
	public Entity buildings[][] = new Entity[maxMap][10];
	public ObjectGenerator objGen = new ObjectGenerator(this);
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
	public Entity npc[][] = new Entity[maxMap][20];
	//==============================//
	
	//Manage Monsters//
	//-------------------------------//
	public Entity monsters[][] = new Entity[maxMap][50];
	//===============================//
	
	//Assets Maker//
	//----------------------------//
	public AssetsHandler createAssets =  new AssetsHandler(this);
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
	boolean isRunning;
	
	//MANAGE PROJECTILES
	//------------------------//
	public Entity projectiles[][] = new Entity[maxMap][50];
	public ArrayList<Entity> particleList = new ArrayList<>();
	//========================//
	
	//Drop chance system//
	//--------------------//
	public DropChanceSystem dcs = new DropChanceSystem();
	//=====================//
	
	//Path finding //
	//--------------------//
	public PathFinder pathFinder = new PathFinder(this); 
	//==================//
	
	//Environment effects manager//
	//---------------------------//
	public EffectsHandler fxHandler = new EffectsHandler(this);
	//==========================//
	
	//Game MAP//
	//-----------------//
	public Map map = new Map(this);
	//=================//
	
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
		
		fxHandler.setUp();
		gameState = gameMenu;
		currentArea = outside;
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
		isRunning = true;
		gameThread = new Thread(this);
		gameThread.start();
	}
	
	public void stop() {
		isRunning = false;
		try {
			gameThread.join();
		} catch (InterruptedException e) {
			e.printStackTrace();
		}
	}
	//GAME LOOP//
	//------------------//
	@Override
	public void run() {
		
this.requestFocus();
		
		long lastTime = System.nanoTime();
		double ammountOfTicks = 60.0;
		double ns = 1000000000 / ammountOfTicks;
		double delta = 0;
		long timer = System.currentTimeMillis();
		
		int frames = 0;
		
		while(isRunning) {
			long now = System.nanoTime();
			delta += (now - lastTime) / ns;
			lastTime = now;
			
			while (delta >= 1) { 
				update();
				delta--;
			}
			drawTempScreen();
			drawFullScreen();
			frames++;
			
			if(System.currentTimeMillis() - timer > 1000) {
				timer += 1000;
				frames = 0;
				//updates = 0;
			}
		}
		stop();
		
		
		
	}
	//====================================================//
	public void update() {
		if(gameState == gameOverState) keys.delayCounter++;
		else keys.delayCounter= 0;
		
		if (gameState == gameMenu) {
			
		}
		if (gameState == playState) {
			player.update();
			for(int i = 0; i < npc[1].length; i++) {
				if(npc[currentMap][i] != null) {
					npc[currentMap][i].update();
				}
			}
			for(int i = 0; i < monsters[1].length; i++) {
				if(monsters[currentMap][i] != null) {
					if(monsters[currentMap][i].alive && !monsters[currentMap][i].dying) monsters[currentMap][i].update();
					if(!monsters[currentMap][i].alive) {
						monsters[currentMap][i].checkDrop();
						monsters[currentMap][i] = null;
					}
				}
			}
			for(int i = 0; i < projectiles[1].length; i++) {
				if(projectiles[currentMap][i] != null) {
					if(projectiles[currentMap][i].alive) projectiles[currentMap][i].update();
					if(!projectiles[currentMap][i].alive) projectiles[currentMap][i] = null;
				}
			}
			for(int i = 0; i < particleList.size(); i++) {
				if(particleList.get(i) != null) {
					if(particleList.get(i).alive) particleList.get(i).update();
					if(!particleList.get(i).alive) particleList.remove(i);
				} 
			}
			for(int i = 0; i < IT_Manager[1].length; i++) {
				if(IT_Manager[currentMap][i] != null) {
					IT_Manager[currentMap][i].update();
				}
			}
			for(int i = 0; i < buildings[1].length; i++) {
				if(buildings[currentMap][i] != null) {
					buildings[currentMap][i].update();
				}
			}
			fxHandler.update();
		}
		if (gameState == pauseState) {
			player.spriteNum = 1;
		}
		if (gameState == dialogueState) {
			player.spriteNum = 1;
		}
		if (gameState == viewCharState) {
			
		}
		
	}
	
	public void drawTempScreen() {
		//Menu Screen
		if (gameState == gameMenu) {
			gui.draw(g2);
		}
		//Map Screen
		 if(gameState == viewMapState) {
			map.drawFullMapScreen(g2);	
		}
		//Others
		else {
			
			//DRAW TILES
			tManager.draw(g2);
			
			//DRAW BUILDINGS
			for(int i = 0; i < buildings[1].length; i++) {
				if(buildings[currentMap][i] !=  null) {
					buildings[currentMap][i].draw(g2);
				}
			}
			
			//draw interactive tiles
			for(int i = 0; i < IT_Manager[1].length; i++) {
				if(IT_Manager[currentMap][i] !=  null) {
					IT_Manager[currentMap][i].draw(g2);
				}
			}
			
			
			//Add player
			entList.add(player);
			
			//Add NPC
			for(int i = 0; i < npc[1].length; i++) {
				if(npc[currentMap][i] != null) {
					entList.add(npc[currentMap][i]);
				}
			}
			
			//Add Monsters
			for(int i = 0; i < monsters[1].length; i++) {
				if(monsters[currentMap][i] != null) {
					entList.add(monsters[currentMap][i]);
				}
			}
			
			//Add Objects
			for(int i = 0; i < gameObjs[1].length; i++) {
				if(gameObjs[currentMap][i] != null) {
					entList.add(gameObjs[currentMap][i]);
				}
			}
			
//			//Add items
			for(int i = 0; i < items[1].length; i++) {
				if(items[currentMap][i] != null) {
					entList.add(items[currentMap][i]);
				}
			}
			
			//Add Prjocetiles	
			for(int i = 0; i < projectiles[1].length; i++) {
				if(projectiles[currentMap][i] != null) {
					entList.add(projectiles[currentMap][i]); 
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
			
			//draw environment effects
			fxHandler.draw(g2);
			
			//draw mini map
			map.drawMiniMap(g2);
			
			//draw cutscenes
			csHandler.draw(g2);
			
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
	public void resetStatus(boolean reset) {
 		//set default pos
		removeTempEnt();
		currentArea = outside;
		currentMap = worldMapA;
		bossBattleOn = false;
		csHandler.scenePhase = csHandler.sceneNum = 0; //might bug the scene
		
 		player.worldX = (35*48); 
 		player.worldY = (12*48);
 		player.direction = "down";
		
		//restore life and mana
 		player.life = player.maxLife;
 		player.mana = player.maxMana;
 		player.attacking = false;
 		player.knockBackState = false;
 		player.lightUpdated = true;
 		player.invincible = false;
		
		//reset nps and mobs pos
		createAssets.makeNpc();
		createAssets.makeMonster();
		
		if(reset) {
			player.inventory.clear();
			player.setDefaultValues();
			player.addInventoryItems();
			fxHandler.lighting.resetDay();
			createAssets.makeInteractiveTiles();
			createAssets.makeObjects();
			createAssets.makeItems();
		}
 		
 		
 	}
	public void changeArea() {
		if(currentArea != nextArea) {
			stopMusic();
			
			if(nextArea == outside) {
				//playmusic for this
			}
			if(nextArea == indoor) {
				//playmusic for this
			}
			if(nextArea == dungeon) {
				//playmusic for this
				createAssets.setDungeonRocks();
			}
		}
		
		currentArea = nextArea;
		createAssets.makeMonster();
		//change bg music as transitioning later
	}
	public void removeTempEnt() {
		for(int mapNum = 0; mapNum < maxMap; mapNum++) {
			for(int i = 0; i < gameObjs[1].length; i++) {
				if(gameObjs[mapNum][i] != null && gameObjs[mapNum][i].temp) gameObjs[mapNum][i] = null;
			}
		}
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













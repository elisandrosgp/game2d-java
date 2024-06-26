package tile;

import java.awt.Graphics2D;
import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStream;
import java.io.InputStreamReader;

import javax.imageio.ImageIO;

import main.GamePanel;

public class TileManager {
  GamePanel gp;
  Tile[] tile;
  int mapTileNum[][];

  public TileManager(GamePanel gp) {
    this.gp = gp;
    tile = new Tile[10];
    mapTileNum = new int[gp.maxScreenCol][gp.maxScreenRow];
    getTileImage();
    loadMap();
  }

  public void getTileImage() {
    try {
      tile[0] = new Tile();
      tile[0].image = ImageIO.read(getClass().getResourceAsStream("/res/tiles/new-version/grass01.png"));

      tile[1] = new Tile();
      tile[1].image = ImageIO.read(getClass().getResourceAsStream("/res/tiles/new-version/wall.png"));

      tile[2] = new Tile();
      tile[2].image = ImageIO.read(getClass().getResourceAsStream("/res/tiles/new-version/water01.png"));

      tile[3] = new Tile();
      tile[3].image = ImageIO.read(getClass().getResourceAsStream("/res/tiles/new-version/earth.png"));

      tile[4] = new Tile();
      tile[4].image = ImageIO.read(getClass().getResourceAsStream("/res/tiles/new-version/road00.png"));

      tile[5] = new Tile();
      tile[5].image = ImageIO.read(getClass().getResourceAsStream("/res/tiles/new-version/road00.png"));
    } catch (IOException e) {
      e.printStackTrace();
    }

  }

  public void loadMap() {

    try {
      InputStream is = getClass().getResourceAsStream("/res/maps/fase.txt");
      BufferedReader br = new BufferedReader(new InputStreamReader(is));

      int col = 0;
      int row = 0;

      while (col < gp.maxScreenCol && row < gp.maxScreenRow) {

        String line = br.readLine();

        while (col < gp.maxScreenCol) {
          String numbers[] = line.split(" ");
          int num = Integer.parseInt(numbers[col]);
          // armazena os numeros extraido do txt
          mapTileNum[col][row] = num;
          col++;
        }
        if (col == gp.maxScreenCol) {
          col = 0;
          row++;
        }
      }
      br.close();

    } catch (Exception e) {

    }
  }

  public void draw(Graphics2D g2) {
    // coloca o tile nas posiçoes desejadas.
    // g2.drawImage(tile[0].image,0 ,0, gp.tileSize, gp.tileSize, null);
    // g2.drawImage(tile[1].image,48 ,0, gp.tileSize, gp.tileSize, null);
    // g2.drawImage(tile[2].image,96, 0, gp.tileSize, gp.tileSize, null);

    // automatica renderização dos tiles;
    int col = 0;
    int row = 0;
    int x = 0;
    int y = 0;
    // colocando grama por todo o mapa;
    while (col < gp.maxScreenCol && row < gp.maxScreenRow) {

      // armazenar map num vetor
      int tileNum = mapTileNum[col][row];

      g2.drawImage(tile[tileNum].image, x, y, gp.tileSize, gp.tileSize, null);
      col++;
      x += gp.tileSize;

      if (col == gp.maxScreenCol) {
        col = 0;
        x = 0;
        row++;
        y += gp.tileSize;
      }
    }

  }

}

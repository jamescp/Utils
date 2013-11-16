package Utils;

import java.awt.image.BufferedImage;

/**
 * <p>
 * Classe que implementa Sprites através de uma BufferedImage cada sprite ocupa
 * a mesma área da imagem e suas dimensões são calculadas automaticamente
 * <p>
 * ChangeLog [10/09/2013]
 * <ul>
 * <li>-Nome da Classe alterado para SpriteSheet 
 * <li>-Lista de Sprites substituido por uma matriz
 * <li>-Adicionada uma propriedade baseImage
 * <li>-Removida a herança de Thread
 * <li>-Removidas as propriedades: intervalo, minSprite, maxSprite, spriteAtual
 * </ul>
 * @author Capone
 * @version 1.1 alpha [10/09/2013]
 * @since 9/10/2012
 */
public class SpriteSheet{

    protected BufferedImage[][] sprites;
    protected BufferedImage baseImage;
    protected int qtosSprites;
    protected int linhas, colunas;
    protected int spritesLinha, spritesColuna;
    protected int altura, largura;
    protected int aSprite, lSprite; //dimensões do Sprite = l/colunas e a/linhas
    
    /**
     * Construtor da classe, calcula a altura e a largura de cada sprite baseado
     * no numero de linhas e colunas da imagem e suas dimensões.
     *
     * @param img Imagem contendo os sprites
     * @param linhas quantas linhas de sprites a imagem contem
     * @param colunas quantas colunas de sprites a imagem contem
     */
    public SpriteSheet(BufferedImage img, int linhas, int colunas) {
        sprites = new BufferedImage[linhas][colunas];
        baseImage = img;
        //Calcula as dimensões de cada Sprite
        this.linhas = linhas;
        this.colunas = colunas;
        this.altura = img.getHeight();
        this.largura = img.getWidth();
        this.lSprite = this.largura / this.colunas;
        this.aSprite = this.altura / this.linhas;
        this.spritesLinha = this.largura / this.lSprite;
        this.spritesColuna = this.altura / this.aSprite;
        //O numero de sprites na imagem = area da imagem/ area do Sprite
        this.qtosSprites = (this.altura * this.largura) / (this.aSprite * this.lSprite);

        //Lendo cada sprite da imagem e passando para a matriz
        for (int i = 0; i < this.linhas; i++) {
            for (int j = 0; j < this.colunas; j++) {
                BufferedImage aux;
                aux = img.getSubimage((lSprite * i), (aSprite * j), lSprite, aSprite);
                //X e Y do sprite = Largura ou altura * coluna ou linha atual
                sprites[i][j] = aux;
            }
        }
    }

    /**
     * @return Uma matriz de <code>BufferedImage</code> contendo cada um dos
     * sprites
     */
    public BufferedImage[][] getSprites() 
    {
        return sprites;
    }

    /**
     * Retorna uma imagem especifica nas coordenadas<code>linha,coluna</code>
     * o mesmo que <code>getSprites[linhas][colunas]</code>
     * @param linha a linha do sprite
     * @param coluna a coluna do sprite
     * @return <code>getSprites[linhas][colunas]</code>
     * @throws IndexOutOfBoundsException
     */
    public BufferedImage getImageAt(int linha, int coluna) 
            throws IndexOutOfBoundsException
    {
        return sprites[linha][coluna];
    }

    /**
     * @return A quantidade de sprites dessa sprite sheet
     */
    public int getQtosSprites() 
    {
        return qtosSprites;
    }

    /**
     * @return a imagem usada como base na spritesheet
     */
    public BufferedImage getBaseImage() 
    {
        return baseImage;
    }

    /**
     * @return A altura de cada sprite
     */
    public int getSpriteHeight() {
        return aSprite;
    }

    /**
     * @return A largura de cada sprite
     */
    public int getSpriteWidth() {
        return lSprite;
    }
    
}
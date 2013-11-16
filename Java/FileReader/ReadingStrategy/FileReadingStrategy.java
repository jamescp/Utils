package FileReader.ReadingStrategy;

import java.io.File;

/**
 * @author Victor Capone
 */
public abstract class FileReadingStrategy {
    //Arquivo que será lido
    protected File file;
    
    public FileReadingStrategy(File f) {
        this.file = f;
    }
    
    /**
     * Le uma linha do arquivo
     * @return Um objeto representando a linha lida.
     */
    public abstract Object readNext();
    
    /**
     * Le o arquivo inteiro
     * @return Um vetor de objetos representado todas as linhas lidas.
     */
    public abstract Object[] readFull();
    
    /**
     * @return <code>true</code> se houverem tokens a serem lidos 
     * <code>false</code> caso contrário.
     */
    public abstract boolean hasNext();
    
}

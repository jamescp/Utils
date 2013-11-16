package FileReader;

import FileReader.ReadingStrategy.FileReadingStrategy;

/**
 *
 * @author Victor Capone
 */
public class FileReader{
    private FileReadingStrategy strategy;

    public FileReader(FileReadingStrategy s)  {
        this.strategy = s;
        
    }

    /**
     * Le a próxima linha do arquivo
     * @return Um objeto que representa a linha lida
     */
    public synchronized Object readNext() {
        return strategy.readNext();
    }

    /**
     * Le o arquivo completo
     * @return Um vetor de objetos que representam as linhas do arquivo
     */
    public synchronized Object[] readFull() {
        return strategy.readFull();
    }

    /**
     * Indica se o arquivo possui dados a serem lidos
     * @return <code>true</code> se ainda há dados a serem lidos 
     * <code>false</code> caso contrário
     */
    public synchronized boolean hasNext() {
        return strategy.hasNext();
    }
    
    
}

package FileReader.ReadingStrategy;

import java.io.File;
import java.io.FileNotFoundException;
import java.util.Arrays;

/**
 *
 * @author Victor Capone
 */
public class CSVReadingStrategy extends SimpleReadingStrategy{
    public final static String DEFAULT_SEPARATORS = ";:|";
    protected String delims;

    /**
     * Cria uma nova estratégia de leitura CSV usador os delimitadores especifi
     *cados
     * @param f O arquivo a ser lido
     * @param delims Os Delimitadores
     * @throws FileNotFoundException Se o arquivo não puder ser lido
     */
    
    public CSVReadingStrategy(File f, String delims) throws FileNotFoundException {
        super(f);
        this.delims = delims;
    }
    
    /**
     * Cria uma nova estratégia de leitura CSV usador os delimitadores padrões
     * <b>";",":","|"</b>
     * @param f O arquivo a ser lido
     * @throws FileNotFoundException Se o arquivo não puder ser lido 
     */
    public CSVReadingStrategy(File f) throws FileNotFoundException {
        this(f, DEFAULT_SEPARATORS);
    }

    /**
     * Le uma linha do arquivo CSV
     * @return Um vetor onde cada posição representa uma célula da linha
     */
    @Override
    public synchronized Object readNext() {
        String line = (String)super.readNext();
        return line.split("["+delims+"]");
    }

    /**
     * Le todo o arquivo
     * @return Uma matriz contendo os registros do arquivo CSV, organizados na
     * forma de tabela
     */
    @Override
    public synchronized Object[] readFull() {
        Object[][] table = new String[100][100];
        int qtasLinhas = 0;
        while (reader.hasNext()){
            Object[] row = (Object[])(readNext());
            table[qtasLinhas++]=Arrays.copyOf(row, row.length);
        }
        return Arrays.copyOf(table, qtasLinhas);
    }

    @Override
    public synchronized boolean hasNext() {
        return super.hasNext();
    }
}
